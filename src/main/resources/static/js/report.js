new Cleave('#inputDayVisit', {
    date: true,
    datePattern: ['d', 'm', 'Y']
});

$("html, body").animate({
    scrollTop: 0
}, 'slow');

function removeSign(str) {
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
    console.log("insd");
    var thisId = $(this).attr("id");
    var autoId = "#auto-" + thisId;
    var value = $(this).val();
    value = removeSign(value.trim());
    $.ajax({
        url: document.location.origin + "/customer/SearchContent",
        type: 'GET',
        dataType: 'json',
        data: {
            search: thisId,
            value: value
        }
    }).done(function (result) {
        console.log(result);
        importIntoAuto(result, value, autoId);
    });
    $(autoId).attr("style", "display: block");
});

function importIntoAuto(json, value, autoId) {
    var length = json.length;
    value = removeSign(value);
    var count = 0;
    var str = "";
    while (true) {
        if (count == length || count == 10) {
            break;
        }
        var jsonContent = json[count];
        var strTemp = jsonContent.toLowerCase();
        strTemp = removeSign(strTemp);
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
    $("input").val("");
});

$("#btn-search").on("click", function () {
    var Name = $("#inputName").val();
    var NameS = removeSign(Name.trim());
    var age = $("#inputAge").val();
    var YOB = "";
    if(age != "") {
        YOB = age < 100 ? (new Date()).getFullYear() - age : age;
    }
    var AddressCus = $("#inputAddress").val();
    var AddressCusS = removeSign(AddressCus.trim());
    var DayVisitstr = $("#inputDayVisit").val();
    if(DayVisitstr != "" && DayVisitstr.length != 10) {
        notify("Lỗi", "Ngày không được nhập chính xác");
        return;
    }
    var DayVisit = DayVisitstr == "" ? null : DayVisitstr;
    $.ajax({
        url: document.location.origin + "/customer/Search",
        type: 'GET',
        dataType: 'html',
        data: {
            nameSearch: NameS,
            yob: YOB,
            addressSearch: AddressCusS,
            dayVisit: DayVisit
        },
        error: function(){
            notify("Lỗi", "Không thể xử lí dữ liệu");
        }
    }).done(function (result) {
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