new Cleave('#ExpectedDOB', {
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
        url: document.location.origin + "/customerHidden",
        type: 'POST',
        dataType: 'html',
        contentType: 'application/json',
        data: JSON.stringify(customer)
    });
    localStorage.clear();
}

sendRequestHidden();

function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
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

$("#menu-toggle").click();

$("#btn-print").on("click", function () {
    sendRequestHidden();
    var Name = $("#name").val();
    var AgeString = $("#age").val();
    var Age = "";
    for (var i = 0; i < AgeString.length; i++) {
        var c = AgeString.substring(i, i + 1);
        if ('0123456789'.indexOf(c) !== -1) {
            Age += AgeString.substring(i, i + 1);
        }
    }
    var YOB = Age == "" ? 0 : (new Date()).getFullYear() - Age;
    var AddressCus = $("#address").val();
    var DayVisit = formatDate(new Date());
    var Result = $("#result").val();
    $.each($('textarea'), function () {
        var value = $(this).val();
        $(this).html(value);
    });
    var content = $("#page").html();
    content = content.replace(/textarea/g, 'pre');
    $("#print").html(content);
    $("#print").printThis({importCSS: false});
    var Report = $("#print").html();
    localStorage.setItem('Status', "True");
    localStorage.setItem('Name', Name);
    localStorage.setItem('YOB', YOB);
    localStorage.setItem('AddressCus', AddressCus);
    localStorage.setItem('DayVisit', DayVisit);
    localStorage.setItem('Result', Result);
    localStorage.setItem('Report', Report);
    setTimeout(function () {
        $("#print").html("");
    }, 500);
});

$("#btn-save").on("click", function () {
    $("#acceptSaveNotify").modal({backdrop: "static"});
});

$("#btn-reload").on("click", function () {
    location.reload();
});

$("#BtnAcceptSave").on("click", function () {
    notify("Thông báo", "Đang xử lí");
    $.each($('textarea'), function () {
        var value = $(this).val();
        $(this).html(value);
    });
    var content = $("#page").html();
    content = content.replace(/textarea/g, 'pre');
    $("#print").html(content);

    var Name = $("#name").val();
    if (Name === "") {
        $("#modalSave").fadeOut();
        notify("Lỗi", "Chưa nhập tên");
        return;
    }
    Name = Name.trim();
    var NameS = removeSign(Name);
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
    var AddressCus = $("#address").val();
    if (AddressCus === "") {
        $("#modalSave").fadeOut();
        notify("Lỗi", "Chưa nhập địa chỉ");
        return;
    }
    AddressCus = AddressCus.trim();
    var AddressCusS = removeSign(AddressCus);
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
        nameS : NameS,
        yob: YOB,
        addressCus: AddressCus,
        addressCusS: AddressCusS,
        dayVisit: DayVisit,
        expectedDOB: ExpectedDOB,
        result: Result,
        note: Note,
        report: Report
    };
    $.ajax({
        url: document.location.origin + "/customer",
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