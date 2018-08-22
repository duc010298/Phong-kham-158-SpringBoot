$("#home").on("click", function () {
   window.location.href = "/Phongkham158";
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
    $("#container").load("http://localhost:8080/Setting/Edit")
});