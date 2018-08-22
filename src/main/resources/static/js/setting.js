var b = true;

$(".home").on("click", function () {
    if(b) {
        window.location.href = "/Phongkham158";
    } else {
        b = true;
        window.location.reload(false);
        $("footer").removeAttr("style");
    }
});

$(".grid-item").on("click", function () {
   $(".setting-bar").fadeOut("fast");
   $(".grid-item").css("background-color", "rgba(255, 255, 255, 0.8)");
   $(this).find(".setting-bar").fadeIn("fast");
   $(this).css("background-color", "#F0F0F0");
});

$(".btn-delete").on("click", function () {
    var cf = confirm("Bạn có muốn xóa file này?");
    if(cf == false) return;
    var id = $(this).parent().parent().attr("id");
    $.ajax({
        url: "http://" + window.location.host + "/Setting/Delete",
        type: 'POST',
        dataType: 'html',
        data: {
            id: id
        },
        error: function(){
            notify("Lỗi", "Không thể xử lí dữ liệu");
        }
    }).done(function (result) {
        alert(result);
        window.location.reload(false);
    });
});

$(".btn-edit").on("click", function () {
    var id = $(this).parent().parent().attr("id");
    $.ajax({
        url: "http://" + window.location.host + "/Setting/Edit",
        type: 'GET',
        dataType: 'html',
        data: {
            id: id
        },
        error: function(){
            notify("Lỗi", "Không thể xử lí dữ liệu");
        }
    }).done(function (result) {
        $("#container").html(result);
        $("footer").attr("style", "position: static; bottom: auto");
        b = false;
    });
});

$(".item-add").on("click", function () {
    $.ajax({
        url: "http://" + window.location.host + "/Setting/Add",
        type: 'GET',
        dataType: 'html',
        error: function(){
            notify("Lỗi", "Không thể xử lí dữ liệu");
        }
    }).done(function (result) {
        $("#container").html(result);
        $("footer").attr("style", "position: static; bottom: auto");
        b = false;
    });
});