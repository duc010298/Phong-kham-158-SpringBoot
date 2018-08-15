$("#btn-result").click(function () {
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

$("#btn-report").click(function () {
    $(this).attr("class", "navtop--active");
    $("#btn-result, #navside>ul>li").removeAttr("class");
    $("main, .spinner-content, footer, .footer-content, .content-default p").removeAttr("style");
    $("#tool").fadeOut(500);
    $("#navside").animate({
        height: 'hide'
    }, 350);
    $(".spinner").attr("style", "display: flex");
    $("#container").load("http://" + window.location.host + "/Report");
    setTimeout(function () {
        $(".spinner").removeAttr("style");
    }, 300);
});

$("#navside>ul>li").click(function () {
    $("#navside>ul>li").removeAttr("class");
    $(this).attr("class", "navside--active");
    $(".spinner").attr("style", "display: flex");
    $("#tool").fadeIn(500);
    var id = $(this).attr("id");
    setTimeout(function () {
        $.ajax({
            url: "http://" + window.location.host + "/UltraSoundResult",
            type: 'GET',
            dataType: 'html',
            data: {
                id: id
            },
            error: function(){
                alert('Không thể tải dữ liệu!');
                $(".spinner").attr("style", "display: none");
            }
        }).done(function (result) {
            $(".spinner").attr("style", "display: none");
            $("#container").html(result);
            $("html, body").animate({
                scrollTop: 0
            }, "slow");
            $("footer").attr("style", "position: static; bottom: auto");
        });
    }, 300);
});

$("#btn-print").click(function () {
    pageToPrint();
    $("#print-container").printThis();
});

function pageToPrint() {
    var totalInput = $(".page input, .page textarea").length;
    for(var i = 0; i < totalInput; i++) {
        $("#output" + i).html($("#input" + i).val());
    }
}

$("#btn-save").click(function () {
    $("#modalSave").fadeIn();
});

$("#btn-reload").click(function () {
    $(".navside--active").click();
});

$('.modal-body input').keydown(function (e) {
    if (e.which === 13 || e.which === 40) {
        var i = $('.modal-body input').index(this) + 1;
        $('.modal-body input').eq(i).focus();
    }
    if (e.which === 38) {
        var i = $('.modal-body input').index(this) - 1;
        $('.modal-body input').eq(i).focus();
    }
});

$("#btn-acept-save").click(function () {
    notify("Thông báo", "Đang xử lí");
    pageToPrint();
    var totalInput = $(".page input, .page textarea").length;
    var indexOfResult = Math.floor(totalInput);
    var Name = $("#input0").val();
    if (Name === "") {
        $("#modalSave").fadeOut();
        notify("Lỗi", "Chưa nhập tên");
        return;
    }
    var AgeString = $("#input1").val();
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
    var AddressCus = $("#input2").val();
    if (AddressCus === "") {
        $("#modalSave").fadeOut();
        notify("Lỗi", "Chưa nhập địa chỉ");
        return;
    }
    var DayVisit = new Date();
    var ExpectedDOBstr = $("#ExpectedDOB").val();
    if (!validateDate(ExpectedDOBstr) && ExpectedDOBstr != "") {
        $("#modalSave").fadeOut();
        notify("Lỗi", "Ngày sinh dự kiến không được nhập chính xác");
        return;
    }
    var parts;
    if (ExpectedDOBstr.indexOf("/") != -1) {
        parts = ExpectedDOBstr.split("/");
    } else {
        parts = ExpectedDOBstr.split("-");
    }
    var ExpectedDOB = new Date(parts[2], parts[1] - 1, parts[0]);
    var Result = $("#input" + indexOfResult).val();
    if (typeof (Result) === "undefined") {
        indexOfResult--;
        Result = $("#input" + indexOfResult).val();
    }
    var Note = $("#Note").val();
    var Report = $("#print-container").html();
    var customer = {
        name: Name,
        yob: YOB,
        addressCus: AddressCus,
        dayVisit: DayVisit,
        expectedDOB: ExpectedDOB,
        result: Result,
        note: Note,
        report: Report
    }

    $.ajax({
        url: "http://" + window.location.host + "/Customer",
        type: 'PUT',
        dataType: 'html',
        contentType: 'application/json',
        data: JSON.stringify(customer)
    }).done(function (result) {
        notify("Thông báo", result);
    });
});

$(".btn-close").click(function () {
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

function validateDate(date) {
    //input must be dd/MM/yyyy or dd-MM-yyyy
    var arr;
    if (date.indexOf("/") != -1) {
        arr = date.split("/");
    } else {
        arr = date.split("-");
    }
    if (arr.length !== 3) {
        return false;
    }

    var day = arr[0];
    var month = arr[1];
    var year = arr[2];

    var isLeapYear = false;
    if ((year % 4 == 0) && (year % 100 != 0)) {
        isLeapYear = true;
    }
    if (month < 1 || month > 12) {
        return false;
    }
    if ((month == 4 || month == 6 || month == 9 || month == 11) && (day < 0 || day > 30)) {
        return false;
    }
    if ((month != 2) && (day < 0 || day > 31)) {
        return false;
    }
    if (isLeapYear && month == 2 && (day < 0 || day > 29)) {
        return false;
    }
    if (!isLeapYear && month == 2 && (day < 0 || day > 28)) {
        return false;
    }
    if (year < 1500 || year > 2100) {
        return false;
    }

    return true;
}
