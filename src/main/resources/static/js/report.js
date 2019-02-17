new Cleave('#inputDayVisit', {
    date: true,
    datePattern: ['d', 'm', 'Y']
});

$("html, body").animate({
    scrollTop: 0
}, 'slow');

function removeSignAndLowerCase(str) {
    str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a");
    str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
    str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
    str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, "o");
    str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
    str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
    str = str.replace(/đ/g, "d");
    str = str.replace(/À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ/g, "a");
    str = str.replace(/È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ/g, "e");
    str = str.replace(/Ì|Í|Ị|Ỉ|Ĩ/g, "i");
    str = str.replace(/Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ/g, "o");
    str = str.replace(/Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ/g, "u");
    str = str.replace(/Ỳ|Ý|Ỵ|Ỷ|Ỹ/g, "y");
    str = str.replace(/Đ/g, "d");
    str = str.toLowerCase();
    return str;
}

function notify(title, message) {
    $("#notifyModal .modal-title").html(title);
    $("#notifyModal .modal-body").html(message);
    $("#notifyModal").modal({backdrop: "static"});
}

$("#menu-toggle").on("click", function () {
    if($("#menu-toggle").attr("class") == "active") {
        $("#menu-toggle").removeAttr("class");
        $("#menu-toggle").html("<a class=\"nav-link\"><i class=\"fas fa-arrow-right\"></i> Kết quả siêu âm</a>");
    } else {
        $("#menu-toggle").attr("class", "active");
        $("#menu-toggle").html("<a class=\"nav-link\"><i class=\"fas fa-arrow-left\"></i> Kết quả siêu âm</a>");
    }
    $("#wrapper").toggleClass("toggled");
});

document.addEventListener("click", function () {
    $(".autoInput").removeAttr("style");
});

$(".grid input").on('input', function () {
    var thisId = $(this).attr("id");
    var autoId = "#auto-" + thisId;
    var value = $(this).val();
    value = removeSignAndLowerCase(value.trim());
    $.ajax({
        url: document.location.origin + "/customer/SearchContent",
        type: 'GET',
        dataType: 'json',
        data: {
            search: thisId,
            value: value
        }
    }).done(function (result) {
        importIntoAuto(result, value, autoId);
    });
    $(autoId).attr("style", "display: block");
});

function importIntoAuto(json, value, autoId) {
    var length = json.length;
    value = removeSignAndLowerCase(value);
    var count = 0;
    var str = "";
    while (true) {
        if (count == length || count == 10) {
            break;
        }
        var jsonContent = json[count];
        var strTemp = jsonContent.toLowerCase();
        strTemp = removeSignAndLowerCase(strTemp);
        var indexStart = strTemp.indexOf(value);
        var indexEnd = indexStart + value.length;
        str += "<div id=\"input-item-";
        str += count;
        str += "\" class=\"input-item\" name=\"";
        str += jsonContent;
        str += "\">";
        str += jsonContent.substring(0, indexStart);
        str += "<strong>";
        str += jsonContent.substring(indexStart, indexEnd);
        str += "</strong>";
        str += jsonContent.substring(indexEnd);
        str += "</div>";
        count++;
    }
    $(autoId).html(str);
    $(".input-item").on("click", function () {
        var parentId = $(this).parent().attr("id");
        var value = $(this).attr("name");
        var inpId = parentId.substring(5, parentId.length);
        $("#" + inpId).val(value);
    });
}

$(".grid input").on("keydown", function (event) {
    var thisId = $(this).attr("id");
    var autoId = "#auto-" + thisId;
    var autoItemLength = $(autoId + " div").length;
    if (event.which == 40) {
        var isActive = false;
        for (var i = 0; i < autoItemLength; i++) {
            var classCurr = $(autoId + " #input-item-" + i).attr("class");
            if (classCurr == "input-item input-item-active") {
                isActive = true;
                var j = i + 1;
                if (j == autoItemLength) {
                    break;
                }
                $(autoId + " #input-item-" + i).attr("class", "input-item");
                $(autoId + " #input-item-" + j).attr("class", "input-item input-item-active");
                break;
            }
        }
        if (!isActive) {
            $(autoId + " #input-item-0").attr("class", "input-item input-item-active");
        }
    }
    if (event.which == 38) {
        for (var i = 0; i < autoItemLength; i++) {
            var classCurr = $(autoId + " #input-item-" + i).attr("class");
            if (classCurr == "input-item input-item-active") {
                var j = i - 1;
                if (j < 0) {
                    break;
                }
                $(autoId + " #input-item-" + i).attr("class", "input-item");
                $(autoId + " #input-item-" + j).attr("class", "input-item input-item-active");
                break;
            }
        }
    }
    if (event.which == 13) {
        for (var i = 0; i < autoItemLength; i++) {
            var classCurr = $(autoId + " #input-item-" + i).attr("class");
            if (classCurr == "input-item input-item-active") {
                var value = $(autoId + " #input-item-" + i).attr("name");
                $("#" + thisId).val(value);
                break;
            }
        }
        $(".autoInput").removeAttr("style");
    }
    if (event.which == 9) {
        $(".autoInput").removeAttr("style");
    }
});

$("#btn-reload").on('click', function () {
    $("#inputName").val("");
    $("#inputAge").val("");
    $("#inputAddress").val("");
    $("#fromDate").val("");
    $("#toDate").val("");
    $("#inputDayVisit").val("");
    $("input[value='fromDay']").click();
});

function isValidDate(str) {
    var d = moment(str,'D/M/YYYY');
    if(d == null || !d.isValid()) return false;

    return str.indexOf(d.format('D/M/YYYY')) >= 0
        || str.indexOf(d.format('DD/MM/YYYY')) >= 0;
}

$("#btn-search").on("click", function () {
    var Name = $("#inputName").val();
    var NameS = removeSignAndLowerCase(Name.trim());
    var age = $("#inputAge").val();
    var YOB = "";
    if(age != "") {
        YOB = age < 100 ? (new Date()).getFullYear() - age : age;
    }
    var AddressCus = $("#inputAddress").val();
    var AddressCusS = removeSignAndLowerCase(AddressCus.trim());
    var value = $('input[name=chooseDate]:checked').val();
    var mode,
        DayVisit,
        fromDate,
        toDate;
    if(value === "fromDay" || value === "today") {
        var DayVisitStr = $("#inputDayVisit").val();
        if (!isValidDate(DayVisitStr) && DayVisitStr.length !== 0) {
            $(".modal-body").css("background-color", "red");
            notify("Lỗi", "Ngày không được nhập chính xác");
            return;
        }
        DayVisit = DayVisitStr === "" ? null : DayVisitStr;
        fromDate = null;
        toDate = null;
        mode = "ByDay";
    } else {
        DayVisit = null;
        var fromDateStr = $("#fromDate").val();
        var toDateStr = $("#toDate").val();
        if ((!isValidDate(fromDateStr) || !isValidDate(toDateStr)) && (fromDateStr.length !== 0 || toDateStr.length !== 0)) {
            $(".modal-body").css("background-color", "red");
            notify("Lỗi", "Ngày không được nhập chính xác");
            return;
        }
        fromDate = fromDateStr === "" ? null : fromDateStr;
        toDate = toDateStr === "" ? null : toDateStr;
        mode = "FromDay";
    }
    $.ajax({
        url: document.location.origin + "/customer/Search",
        type: 'GET',
        dataType: 'html',
        data: {
            mode: mode,
            nameSearch: NameS,
            yob: YOB,
            addressSearch: AddressCusS,
            dayVisit: DayVisit,
            fromDate: fromDate,
            toDate: toDate
        },
        error: function(){
            $(".modal-body").css("background-color", "red");
            notify("Lỗi", "Không thể xử lí dữ liệu");
        }
    }).done(function (result) {
        if(result.includes("Lỗi")) {
            $(".modal-body").css("background-color", "red");
        } else {
            $(".modal-body").css("background-color", "");
        }
        $("#display-result").html(result);
    });
});

function PopupCenter(url, title, w, h) {
    // Fixes dual-screen position                         Most browsers      Firefox
    var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : window.screenX;
    var dualScreenTop = window.screenTop != undefined ? window.screenTop : window.screenY;

    var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
    var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

    var left = ((width / 2) - (w / 2)) + dualScreenLeft;
    var top = ((height / 2) - (h / 2)) + dualScreenTop;
    var newWindow = window.open(url, title, 'scrollbars=yes, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);

    // Puts focus on the newWindow
    if (window.focus) {
        newWindow.focus();
    }
}

$("input[name='chooseDate']").on('click', function () {
    var value = $(this).val();
    if(value === "fromDay" || value === "today") {
        $(".grid").css("grid-template-columns","auto auto auto auto");
        $(".fromTo").fadeOut("fast");
        $(".fromTo").val("");
        $(".fromDay").fadeIn("fast");
    } else {
        $(".grid").css("grid-template-columns","auto auto auto auto auto");
        $(".fromTo").fadeIn("fast");
        $(".fromDay").fadeOut("fast");
        $(".fromDay").val("");
    }
    var d = new Date(),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();
    switch(value) {
        case "fromDay":
            $("#inputDayVisit").val("");
            break;
        case "today":
            $("#inputDayVisit").val([day, month, year].join('/'));
            break;
        case "3daysago":
            var fromDate = new Date();
            fromDate.setDate(fromDate.getDate() - 2);
            var monthF = '' + (fromDate.getMonth() + 1),
                dayF = '' + fromDate.getDate(),
                yearF = fromDate.getFullYear();
            $("#fromDate").val([dayF, monthF, yearF].join('/'));
            $("#toDate").val([day, month, year].join('/'));
            break;
        case "thisweek":
            var dayMon = d.getDay();
            var diff = d.getDate() - dayMon + (dayMon == 0 ? -6:1);
            var monday = new Date(d.setDate(diff)),
                monthM = '' + (monday.getMonth() + 1),
                dayM = '' + monday.getDate(),
                yearM = monday.getFullYear();

            $("#fromDate").val([dayM, monthM, yearM].join('/'));
            $("#toDate").val([day, month, year].join('/'));
            break;
        case "thismonth":
            $("#fromDate").val([1, month, year].join('/'));
            $("#toDate").val([day, month, year].join('/'));
            break;
        case "3monthsago":
            var fromDate = new Date();
            fromDate.setMonth(fromDate.getMonth() - 2);
            var monthF = '' + (fromDate.getMonth() + 1),
                dayF = '' + fromDate.getDate(),
                yearF = fromDate.getFullYear();
            $("#fromDate").val([dayF, monthF, yearF].join('/'));
            $("#toDate").val([day, month, year].join('/'));
            break;
        case "thisyear":
            $("#fromDate").val([1, 1, year].join('/'));
            $("#toDate").val([day, month, year].join('/'));
            break;
        case "lastyear":
            $("#fromDate").val([1, 1, year-1].join('/'));
            $("#toDate").val([31, 12, year-1].join('/'));
            break;
    }
});
