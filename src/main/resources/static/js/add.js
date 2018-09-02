function notify(Header, Content) {
    $("#notify-header").html(Header);
    //Độ dài vượt quá 22 gây tràn modal
    if (Content.length > 22) {
        $(".modal-body h1").attr("style", "font-size: 1.6rem");
    }
    $("#notify-content").html(Content);
    $("#modalNotify").fadeIn("fast");
}

$(".btn-close").on("click", function () {
    $("#modalNotify").fadeOut();
    setTimeout(function () {
        $(".modal-body h1").removeAttr("style");
    }, 500);
});

function applyResizeable() {
    $("#table-content").colResizable({
        liveDrag:true,
        draggingClass: "dragging"
    });
    $("#table-content-2").colResizable({
        liveDrag:true
    });
}

function disabledResizeable() {
    $("#table-content").colResizable({disable: true});
    $("#table-content-2").colResizable({disable: true});
}

function initTextArea() {
    $.each($('textarea'), function () {
        var resizeTextarea = function (el) {
            $(el).css('height', 'auto').css('height', el.scrollHeight);
        };

        $(this).on('keyup input', function () {
            resizeTextarea(this);
        });
    });

    $("table td").on("click", function () {
        $(this).find("textarea").focus();
    });
}

function initTextAreaForFirst() {
    $.each($('textarea'), function () {
        var resizeTextarea = function (el) {
            $(el).css('height', 'auto').css('height', el.scrollHeight);
        };

        resizeTextarea(this);
        $(this).on('keyup input', function () {
            resizeTextarea(this);
        })
    });
    $("textarea").prop('disabled', false);
}

function addRow(index) {
    disabledResizeable();
    var table = document.getElementById("table-content");
    var row = table.insertRow(index);
    var totalCol = $('#table-content tr:first td').length;
    for (var i = 0; i < totalCol; i++) {
        var cell = row.insertCell(i);
        cell.innerHTML = "<textarea rows='1'></textarea>";
    }
    applyResizeable();
    initTextArea();
}

function addCol(index) {
    disabledResizeable();
    var rows = document.querySelectorAll("#table-content tr");
    var cell = rows[0].insertCell(index);
    cell.innerHTML = ".";
    for(var i = 1; i < rows.length; i++) {
        var cell = rows[i].insertCell(index);
        cell.innerHTML = "<textarea rows='1'></textarea>";
    }
    applyResizeable();
    initTextArea();
}

$('#add-row').on("click", function () {
    var totalRow = $('#table-content tr').length;
    var i = $(".tool-edit-title").index(this);
    $(".tool-edit-content").eq(i).find('input').val(totalRow);
});

$('#acept-add-row').on("click", function () {
    var totalRow = $('#table-content tr').length;
    var value = $(this).parent().find("input").val();
    var b = false;
    for (var i = 0; i < value.length; i++) {
        var c = value.substring(i, i + 1);
        if ('0123456789'.indexOf(c) == -1) {
            b = true;
        }
    }
    if(value == "") {
        notify("Lỗi", "Vị trí hàng chưa được nhập");
        return;
    }
    if(b) {
        notify("Lỗi", "Vị trí hàng phải là số");
        return;
    }
    if(value <= 0 || value > totalRow) {
        notify("Lỗi", "Vị trí hàng nhập không đúng");
        return;
    }
    addRow(value);
    var parent = $(this).parent();
    var i = $(".tool-edit-content").index(parent);
    $(".tool-edit-title").eq(i).click();
});

$("#add-col").on("click", function () {
    var totalCol = $('#table-content tr:first td').length;
    totalCol++;
    var i = $(".tool-edit-title").index(this);
    $(".tool-edit-content").eq(i).find('input').val(totalCol);
});

$("#acept-add-col").on("click", function () {
    var totalCol = $('#table-content tr:first td').length;
    var value = $(this).parent().find("input").val();
    var b = false;
    for (var i = 0; i < value.length; i++) {
        var c = value.substring(i, i + 1);
        if ('0123456789'.indexOf(c) == -1) {
            b = true;
        }
    }
    if(value == "") {
        notify("Lỗi", "Vị trí cột chưa được nhập");
        return;
    }
    if(b) {
        notify("Lỗi", "Vị trí cột phải là số");
        return;
    }
    if(value <= 0 || value > totalCol+1) {
        notify("Lỗi", "Vị trí hàng cột không đúng");
        return;
    }
    value--;
    addCol(value);
    var parent = $(this).parent();
    var i = $(".tool-edit-content").index(parent);
    $(".tool-edit-title").eq(i).click();
});

$("#back").on("click", function () {
    window.location.href = "/Phongkham158/Setting";
});

$(".tool-edit-title").on("click", function () {
    var i = $(".tool-edit-title").index(this);
    if($(".tool-edit-content").eq(i).css('display') !== 'none') {
        return;
    }
    $(".tool-edit-content").slideUp();
    $(".tool-edit-content").eq(i).slideDown();
});

$("#delete-row").on("click", function () {
    var value = $(this).parent().find("input").val();
    var totalRow = $('#table-content tr').length;
    if (totalRow == 2) {
        notify("Lỗi", "Không thể xóa toàn bộ hàng");
        return;
    }
    var b = false;
    for (var i = 0; i < value.length; i++) {
        var c = value.substring(i, i + 1);
        if ('0123456789'.indexOf(c) == -1) {
            b = true;
        }
    }
    if(value == "") {
        notify("Lỗi", "Vị trí hàng chưa được nhập");
        return;
    }
    if(b) {
        notify("Lỗi", "Vị trí hàng phải là số");
        return;
    }
    if(value <= 0 || value >= totalRow) {
        notify("Lỗi", "Vị trí hàng nhập không đúng");
        return;
    }
    document.getElementById("table-content").deleteRow(value);
});

//indexRow = 0 if want to delete at all row
function deleteCell(indexRow, indexCell) {
    disabledResizeable();
    var rows = document.querySelectorAll("#table-content tr");
    if(indexRow == 0) {
        for(var i = 0; i < rows.length; i++) {
            rows[i].deleteCell(indexCell);
        }
    } else {
        rows[indexRow].deleteCell(indexCell);
    }
    var totalCell = $('#table-content tr:first td').length;
    if(totalCell == 0) {
        $("#table-content").html("");
    }
    applyResizeable();
}

$("#delete-col").on("click", function () {
    var value = $(this).parent().find("input").val();
    var totalCell = $('#table-content tr:first td').length;
    if(totalCell == 1) {
        notify("Lỗi", "Không thể xóa toàn bộ cột");
        return;
    }
    var b = false;
    for (var i = 0; i < value.length; i++) {
        var c = value.substring(i, i + 1);
        if ('0123456789'.indexOf(c) == -1) {
            b = true;
        }
    }
    if(value == "") {
        notify("Lỗi", "Vị trí cột chưa được nhập");
        return;
    }
    if(b) {
        notify("Lỗi", "Vị trí cột phải là số");
        return;
    }
    if(value <= 0 || value > totalCell) {
        notify("Lỗi", "Vị trí cột nhập không đúng");
        return;
    }
    value--;
    deleteCell(0, value);
});

$("#btn-print").on("click", function () {
    disabledResizeable();
    $.each($('textarea'), function () {
        var value = $(this).val();
        $(this).html(value);
    });
    var content = $("#page").html();
    content = content.replace(/textarea/g, 'pre');
    $("#print").html(content);
    $("#print").printThis({importCSS: false});
    setTimeout(function () {
        applyResizeable();
        $("#print").html("");
    }, 1000);
});

$("#merge-row").on("click", function () {
    disabledResizeable();
    var row = $(this).parents().find('.merge input').eq(0).val();
    var col = $(this).parents().find('.merge input').eq(1).val();
    var n = $("#inp-tRow").val();
    var b = false;
    for (var i = 0; i < row.length; i++) {
        var c = row.substring(i, i + 1);
        if ('0123456789'.indexOf(c) == -1) {
            b = true;
        }
    }
    if(b) {
        notify("Lỗi", "Số hàng phải là số");
        return;
    }
    for (var i = 0; i < col.length; i++) {
        var c = col.substring(i, i + 1);
        if ('0123456789'.indexOf(c) == -1) {
            b = true;
        }
    }
    if(b) {
        notify("Lỗi", "Số cột phải là số");
        return;
    }
    for (var i = 0; i < n.length; i++) {
        var c = n.substring(i, i + 1);
        if ('0123456789'.indexOf(c) == -1) {
            b = true;
        }
    }
    if(b) {
        notify("Lỗi", "Số ô gộp phải là số");
        return;
    }
    var totalRow = $('#table-content tr').length;;
    var totalCol = $('#table-content tr:first td').length;
    if(row <= 0 || row >= totalRow) {
        notify("Lỗi", "Số hàng được nhập không đúng");
        return;
    }
    if(col <= 0 || col >= totalCol) {
        notify("Lỗi", "Số cột được nhập không đúng");
        return;
    }
    if(n <= 0 || n > (totalCol - col + 1)) {
        notify("Lỗi", "Số ô gộp được nhập không đúng");
        return;
    }
    col--;
    var old = $("#table-content").find('tr').eq(row).find('td').eq(col).attr("colspan");
    var count = n;
    if(typeof (old) !== "undefined") {
        n = parseInt(n) + parseInt(old) - 1;
    }
    $("#table-content").find('tr').eq(row).find('td').eq(col).attr("colspan", n);
    for(var i = 1; i < count; i++) {
        deleteCell(row, col+1);
    }
    applyResizeable();
});

$("#merge-col").on("click", function () {
    disabledResizeable();
    var row = $(this).parents().find('.merge input').eq(2).val();
    var col = $(this).parents().find('.merge input').eq(3).val();
    var n = $("#inp-tCol").val();
    var b = false;
    for (var i = 0; i < row.length; i++) {
        var c = row.substring(i, i + 1);
        if ('0123456789'.indexOf(c) == -1) {
            b = true;
        }
    }
    if(b) {
        notify("Lỗi", "Số hàng phải là số");
        return;
    }
    for (var i = 0; i < col.length; i++) {
        var c = col.substring(i, i + 1);
        if ('0123456789'.indexOf(c) == -1) {
            b = true;
        }
    }
    if(b) {
        notify("Lỗi", "Số cột phải là số");
        return;
    }
    for (var i = 0; i < n.length; i++) {
        var c = n.substring(i, i + 1);
        if ('0123456789'.indexOf(c) == -1) {
            b = true;
        }
    }
    if(b) {
        notify("Lỗi", "Số ô gộp phải là số");
        return;
    }
    var totalRow = $('#table-content tr').length;
    var totalCol = $('#table-content tr:first td').length;
    if(row <= 0 || row >= totalRow) {
        notify("Lỗi", "Số hàng được nhập không đúng");
        return;
    }
    if(col <= 0 || col >= totalCol) {
        notify("Lỗi", "Số cột được nhập không đúng");
        return;
    }
    if(n <= 0 || n > (totalRow - row + 1)) {
        notify("Lỗi", "Số ô gộp được nhập không đúng");
        return;
    }
    col--;
    var old = $("#table-content").find('tr').eq(row).find('td').eq(col).attr("rowspan");
    var count = n;
    if(typeof (old) !== "undefined") {
        n = parseInt(n) + parseInt(old) - 1;
    }
    $("#table-content").find('tr').eq(row).find('td').eq(col).attr("rowspan", n);
    row++;
    for(var i = 1; i < count; i++) {
        deleteCell(row++, col);
    }
    applyResizeable();
});

function disableInput() {
    $(".b5, .b11, .b7").prop('disabled', true);
}

$("#save").on("click", function () {
    disabledResizeable();
    disableInput();
    var name = prompt("Nhập tên cho file này:", "Nhập tên");
    if(name == null) return;
    if(name == "") {
        notify("Lỗi", "Chưa nhập tên file");
        return;
    }
    $.each($('textarea'), function () {
        var value = $(this).val();
        $(this).html(value);
    });
    $("#print").html("");
    $("#name").html("");
    $("#address").html("");
    var content = $("#content").html();
    $(".spinner").attr("style", "display: flex");
    $.ajax({
        //For test
        url: "http://" + window.location.host + "/Phongkham158/Setting/Add",
        // url: "http://" + window.location.host + "/Setting/Add",
        type: 'POST',
        dataType: 'html',
        data: {
            name: name,
            content: content
        },
        error: function(){
            notify("Lỗi", "Không thể xử lí dữ liệu");
        }
    }).done(function (result) {
        $(".spinner").removeAttr("style");
        notify("Thông báo", result);
    });
    applyResizeable();
});

$("#save2").on("click", function () {
    disabledResizeable();
    disableInput();
    $.each($('textarea'), function () {
        var value = $(this).val();
        $(this).html(value);
    });
    var id = $("main").attr("id");
    $("#print").html("");
    $("#name").html("");
    $("#address").html("");
    var content = $("#content").html();
    $(".spinner").attr("style", "display: flex");
    $.ajax({
        //For test
        url: "http://" + window.location.host + "/Phongkham158/Setting/Edit",
        // url: "http://" + window.location.host + "/Setting/Edit",
        type: 'POST',
        dataType: 'html',
        data: {
            id: id,
            content: content
        },
        error: function(){
            notify("Lỗi", "Không thể xử lí dữ liệu");
        }
    }).done(function (result) {
        $(".spinner").removeAttr("style");
        notify("Thông báo", result);
    });
    applyResizeable();
});

$("#disable").on("click", function () {
    disabledResizeable();
    var row = $(this).parents().find('.merge input').eq(4).val();
    var col = $(this).parents().find('.merge input').eq(5).val();
    var b = false;
    for (var i = 0; i < row.length; i++) {
        var c = row.substring(i, i + 1);
        if ('0123456789'.indexOf(c) == -1) {
            b = true;
        }
    }
    if(b) {
        notify("Lỗi", "Số hàng phải là số");
        return;
    }
    for (var i = 0; i < col.length; i++) {
        var c = col.substring(i, i + 1);
        if ('0123456789'.indexOf(c) == -1) {
            b = true;
        }
    }
    if(b) {
        notify("Lỗi", "Số cột phải là số");
        return;
    }
    var totalRow = $('#table-content tr').length;
    var totalCol = $('#table-content tr:first td').length;
    if(row <= 0 || row >= totalRow) {
        notify("Lỗi", "Số hàng được nhập không đúng");
        return;
    }
    if(col <= 0 || col > totalCol) {
        notify("Lỗi", "Số cột được nhập không đúng");
        return;
    }
    col--;
    $("#table-content").find('tr').eq(row).find('td').eq(col).find("textarea").prop('disabled', true);
});

applyResizeable();
initTextAreaForFirst();