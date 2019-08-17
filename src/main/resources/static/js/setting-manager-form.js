let notify = (title, message, alert) => {
    if (alert) {
        $("#notify-modal").css("color", "#fff");
        $("#notify-modal .modal-header").css("background-color", "#FF323F");
        $("#notify-modal .modal-body").css("background-color", "#FF323F");
        $("#notify-modal .modal-footer").css("background-color", "#FF323F");
    } else {
        $("#notify-modal").css("color", "#000");
        $("#notify-modal .modal-header").css("background-color", "");
        $("#notify-modal .modal-body").css("background-color", "");
        $("#notify-modal .modal-footer").css("background-color", "");
    }
    $("#notify-modal .modal-title").html(title);
    $("#notify-modal .modal-body").html(message);
    $("#notify-modal").modal({backdrop: "static"});
}; // Show notify modal

let listButtonDelete = document.getElementsByClassName("btn-delete");

for (let btn of listButtonDelete) {
    btn.onclick = (event) => {
        $("#notify-modal-confirm .modal-title").html("Bạn có muốn xóa mẫu báo cáo này không?");
        $("#notify-modal-confirm").modal({backdrop: "static"});
        let confirmBtn = document.getElementById("confirm-ok"); //see more on templates/fragments/notify.html
        confirmBtn.onclick = null;
        confirmBtn.onclick = () => {
            let id = event.target.getAttribute("data-report-id");
            $.ajax({
                url: document.location.origin + "/setting/manager-form/" + id,
                type: 'DELETE'
            }).done(function () {
                let btnCloseModelNotify = document.getElementById("notify-ok"); //see more on templates/fragments/notify.html
                btnCloseModelNotify.onclick = null;
                btnCloseModelNotify.onclick = () => {
                    location.reload();
                };
                notify('Thông báo', 'Xóa mẫu báo cáo thành công', false);
            }).fail(function () {
                notify('Lỗi', 'Không thể xử lý yêu cầu', true);
            });
        }
    }
}