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
    $("#notifyModal .modal-title").html(title);
    $("#notifyModal .modal-body").html(message);
    $("#notifyModal").modal({backdrop: "static"});
}; // Show notify modal

