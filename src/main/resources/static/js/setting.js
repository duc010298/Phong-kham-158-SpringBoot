function notify(Header, Content) {
    $("#notify-header").html(Header);
    //Độ dài vượt quá 22 gây tràn modal
    if (Content.length > 22) {
        $(".modal-body h1").attr("style", "font-size: 1.6rem");
    }
    $("#notify-content").html(Content);
    $("#modalNotify").fadeIn("fast");
}

$("#back").on("click", function () {
    window.location.href = "/Phongkham158";
});

$(".grid-item").on("click", function () {
    var i = $(".grid-item").index(this);
    if($(".setting-bar").eq(i).css('display') !== 'none') {
        return;
    }
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
        //For test
        // url: "http://" + window.location.host + "/Phongkham158/Setting/Delete",
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
        notify("Thông báo", result);
        $(".btn-close").on("click", function () {
            window.location.reload(false);
        });
    });
});

$(".btn-edit").on("click", function () {
    var id = $(this).parent().parent().attr("id");
    window.location.href = "Setting/Edit/" + id;
});

$(".item-add").on("click", function () {
    window.location.href = "/Setting/Add";
});