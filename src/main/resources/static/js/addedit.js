function notify(title, message) {
    $("#notifyModal .modal-title").html(title);
    $("#notifyModal .modal-body").html(message);
    $("#notifyModal").modal({backdrop: "static"});
}

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

function fillInput() {
    var totalRow = $('#table-content tr').length;
    var totalCol = ++$('#table-content tr:first td').length;
    $("#number-add-row").val(totalRow);
    $("#number-add-col").val(totalCol);
    $("#number-delete-row").val(--totalRow);
    $("#number-delete-col").val(--totalCol);
}

//call function for first
applyResizeable();
initTextAreaForFirst();
fillInput();

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

$("#add-row").on('click', function () {
    var totalRow = $('#table-content tr').length;
    var value = $("#number-add-row").val();
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
    fillInput();
});

$("#add-col").on('click', function () {
    var totalCol = $('#table-content tr:first td').length;
    var value = $("#number-add-col").val();
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
    fillInput();
});

$("#delete-row").on("click", function () {
    var value = $("#number-delete-row").val();
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
    fillInput();
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
    var value = $("#number-delete-col").val();
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
    fillInput();
});

$("#merge-row").on("click", function () {
    disabledResizeable();
    var row = $("#number-merge-row-row").val();
    var col = $("#number-merge-row-col").val();
    var n = $("#number-merge-row-total").val();
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
    $("#number-merge-row-row").val("");
    $("#number-merge-row-col").val("");
    $("#number-merge-row-total").val("");
    fillInput();
});

$("#merge-col").on("click", function () {
    disabledResizeable();
    var row = $("#number-merge-col-row").val();
    var col = $("#number-merge-col-col").val();
    var n = $("#number-merge-col-total").val();
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
    $("#number-merge-col-row").val("");
    $("#number-merge-col-col").val("");
    $("#number-merge-col-total").val("");
    fillInput();
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

function disableInput() {
    $(".b5, .b7").prop('disabled', true);
}

$("#btn-save").on("click", function () {
    disabledResizeable();
    disableInput();
    var name = prompt("Nhập tên cho file này:", "Nhập tên");
    if(name == null) return;
    notify("Thông báo", "Đang xử lí");
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
    $.ajax({
        url: document.location.origin + "/setting/manager-form/add",
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
        notify("Thông báo", result);
    });
    applyResizeable();
});

$("#btn-save-2").on("click", function () {
    notify("Thông báo", "Đang xử lí");
    disabledResizeable();
    disableInput();
    var id = $("#content").attr("name");
    $.each($('textarea'), function () {
        var value = $(this).val();
        $(this).html(value);
    });
    $("#print").html("");
    $("#name").html("");
    $("#address").html("");
    var content = $("#content").html();
    $.ajax({
        url: document.location.origin + "/setting/manager-form/edit",
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
        notify("Thông báo", result);
    });
    applyResizeable();
});