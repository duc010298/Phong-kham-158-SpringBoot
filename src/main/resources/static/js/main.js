sendRequestHidden();

function sendRequestHidden() {
    if(localStorage.getItem('Status') != "True") {
        return;
    }
    var Name = localStorage.getItem('Name');
    var YOB = localStorage.getItem('YOB');
    var AddressCus = localStorage.getItem('AddressCus');
    var DayVisit = localStorage.getItem('DayVisit');
    var Result = localStorage.getItem('Result');
    var Report = localStorage.getItem('Report');
    var customer = {
        name: Name,
        yob: YOB,
        addressCus: AddressCus,
        dayVisit: DayVisit,
        result: Result,
        report: Report
    };
    $.ajax({
        // For test
        url: "http://" + window.location.host + "/Phongkham158/CustomerHidden",
        // url: "http://" + window.location.host + "/CustomerHidden",
        type: 'POST',
        dataType: 'html',
        contentType: 'application/json',
        data: JSON.stringify(customer)
    });
    localStorage.clear();
}

$("#btn-result").on("click", function () {
    if ($(this).attr("class") === "navtop--active") {
        $(this).removeAttr("class");
        $("#btn-result>span").attr("class", "fas fa-angle-down");
        $("main, .spinner-content, .footer-content, .content-default p").removeAttr("style");
    } else {
        $(this).attr("class", "navtop--active");
        $("#btn-report").removeAttr("class");
        $("#btn-result>span").attr("class", "fas fa-angle-up");
        $("main, .footer-content").attr("style", "margin-left: 14.375rem");
        $(".spinner-content").attr("style", "padding-right: 14.375rem;");
        $(".content-default p").attr("style", "padding-right: 14.375rem;");
    }
    $("#navside").animate({
        height: 'toggle'
    }, 350);
});

$("#btn-report").on("click", function () {
    sendRequestHidden();
    $(this).attr("class", "navtop--active");
    $("#btn-result>span").attr("class", "fas fa-angle-down");
    $("#btn-result, #navside>ul>li").removeAttr("class");
    $("main, .spinner-content, footer, .footer-content, .content-default p").removeAttr("style");
    $("#tool").fadeOut(500);
    $("#navside").animate({
        height: 'hide'
    }, 350);
    $(".spinner").attr("style", "display: flex");
    // For test
    $("#container").load("http://" + window.location.host + "/Phongkham158/Report", function(response, status) {
    // $("#container").load("http://" + window.location.host + "/Report", function(response, status) {
        setTimeout(function () {
            $(".spinner").removeAttr("style");
        }, 300);
        if ( status == "error" ) {
            notify("Lỗi", "Không thể tải dữ liệu");
        }
    });
});

$("#navside>ul>li").on("click", function () {
    sendRequestHidden();
    $("#navside>ul>li").removeAttr("class");
    $(this).attr("class", "navside--active");
    $(".spinner").attr("style", "display: flex");
    $("#tool").fadeIn(500);
    var id = $(this).attr("id");
    setTimeout(function () {
        $.ajax({
            url: "http://" + window.location.host + "/Phongkham158/Form",
            type: 'GET',
            dataType: 'html',
            data: {
                id: id
            },
            error: function(){
                notify("Lỗi", "Không thể tải dữ liệu");
                $(".spinner").attr("style", "display: none");
            }
        }).done(function (result) {
            $(".spinner").attr("style", "display: none");
            $("#container").html(result);
            var d = new Date(),
                month = '' + (d.getMonth() + 1),
                day = '' + d.getDate(),
                year = d.getFullYear();
            $("#date").html("Ngày " + day + " tháng " + month + " năm " + year);
            $("html, body").animate({
                scrollTop: 0
            }, "slow");
            $("footer").attr("style", "position: static; bottom: auto");
            $('.page input, .page textarea').on("keydown", function (e) {
                if (e.which === 13 || e.which === 40) {
                    var i = $('.page input, .page textarea').index(this) + 1;
                    $('.page input, .page textarea').eq(i).focus();
                }
                if (e.which === 38) {
                    var i = $('.page input, .page textarea').index(this) - 1;
                    $('.page input, .page textarea').eq(i).focus();
                }
            });
        });
    }, 300);
});

$("#btn-print").on("click", function () {
    sendRequestHidden();
    var Name = $("#name").val().trim();
    var AgeString = $("#age").val();
    var Age = "";
    for (var i = 0; i < AgeString.length; i++) {
        var c = AgeString.substring(i, i + 1);
        if ('0123456789'.indexOf(c) !== -1) {
            Age += AgeString.substring(i, i + 1);
        }
    }
    var YOB = Age == "" ? 0 : (new Date()).getFullYear() - Age;
    var AddressCus = $("#address").val().trim();
    var DayVisit = formatDate(new Date());
    var Result = $("#result").val();
    $.each($('textarea'), function () {
        var value = $(this).val();
        $(this).html(value);
    });
    var content = $("#page").html();
    content = content.replace(/textarea/g, 'pre');
    $("#print").html(content);
    var Report = $("#print").html();
    localStorage.setItem('Status', "True");
    localStorage.setItem('Name', Name);
    localStorage.setItem('YOB', YOB);
    localStorage.setItem('AddressCus', AddressCus);
    localStorage.setItem('DayVisit', DayVisit);
    localStorage.setItem('Result', Result);
    localStorage.setItem('Report', Report);
    $("#print").printThis({importCSS: false});
    setTimeout(function () {
        $("#print").html("");
    }, 500);
});

function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}

$("#btn-save").on("click", function () {
    $("#modalSave").fadeIn();
});

$("#btn-reload").on("click", function () {
    $(".navside--active").click();
});

$('.modal-body input').on("keydown", function (e) {
    if (e.which === 13 || e.which === 40) {
        var i = $('.modal-body input').index(this) + 1;
        $('.modal-body input').eq(i).focus();
    }
    if (e.which === 38) {
        var i = $('.modal-body input').index(this) - 1;
        $('.modal-body input').eq(i).focus();
    }
});

$("#btn-acept-save").on("click", function () {
    notify("Thông báo", "Đang xử lí");
    $.each($('textarea'), function () {
        var value = $(this).val();
        $(this).html(value);
    });
    var content = $("#page").html();
    content = content.replace(/textarea/g, 'pre');
    $("#print").html(content);
    var Name = $("#name").val().trim();
    if (Name === "") {
        $("#modalSave").fadeOut();
        notify("Lỗi", "Chưa nhập tên");
        return;
    }
    var AgeString = $("#age").val();
    var Age = "";
    for (var i = 0; i < AgeString.length; i++) {
        var c = AgeString.substring(i, i + 1);
        if ('0123456789'.indexOf(c) !== -1) {
            Age += AgeString.substring(i, i + 1);
        }
    }
    if (Age === "") {
        $("#modalSave").fadeOut();
        notify("Lỗi", "Chưa nhập tuổi");
        return;
    }
    var YOB = (new Date()).getFullYear() - Age;
    var AddressCus = $("#address").val().trim();
    if (AddressCus === "") {
        $("#modalSave").fadeOut();
        notify("Lỗi", "Chưa nhập địa chỉ");
        return;
    }
    var DayVisit = new Date();
    var ExpectedDOBstr = $("#ExpectedDOB").val();
    if(ExpectedDOBstr.length != 10 && ExpectedDOBstr != "") {
        notify("Lỗi", "Ngày sinh dự kiến được nhập không chính xác");
        return;
    }
    var parts;
    parts = ExpectedDOBstr.split("/");
    var ExpectedDOB = new Date(parts[2], parts[1] - 1, parts[0]);
    var Result = $("#result").val();
    var Note = $("#Note").val();
    var Report = $("#print").html();
    var customer = {
        name: Name,
        yob: YOB,
        addressCus: AddressCus,
        dayVisit: DayVisit,
        expectedDOB: ExpectedDOB,
        result: Result,
        note: Note,
        report: Report
    };

    $.ajax({
        //For test
        url: "http://" + window.location.host + "/Phongkham158/Customer",
        // url: "http://" + window.location.host + "/Customer",
        type: 'POST',
        dataType: 'html',
        contentType: 'application/json',
        data: JSON.stringify(customer),
        error: function(){
            notify("Lỗi", "Không thể xử lí dữ liệu");
        }
    }).done(function (result) {
        notify("Thông báo", result);
    });
    localStorage.clear();
    $("#print").html("");
});

$(".btn-close").on("click", function () {
    $("#modalSave, #modalNotify").fadeOut();
    $(".modal-body>input").val("");
    setTimeout(function () {
        $(".modal-body h1").removeAttr("style");
    }, 500);
});

function notify(Header, Content) {
    $("#notify-header").html(Header);
    //Độ dài vượt quá 22 gây tràn modal
    if (Content.length > 22) {
        $(".modal-body h1").attr("style", "font-size: 1.6rem");
    }
    $("#notify-content").html(Content);
    $("#modalNotify").fadeIn("fast");
}

new Cleave('#ExpectedDOB', {
    date: true,
    datePattern: ['d', 'm', 'Y']
});

$("#setting").on("click", function () {
    sendRequestHidden();
    //For test
    window.location.href = "http://" + window.location.host + "/Phongkham158/Setting";
    // window.location.href = "http://" + window.location.host + "/Setting";
});

$("#btn-result").click();