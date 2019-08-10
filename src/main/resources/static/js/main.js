document.getElementById('menu-toggle').onclick = () => {
    let target = document.getElementById('menu-toggle');
    if (target.getAttribute("class") === 'active') {
        target.removeAttribute("class");
        let child = target.getElementsByTagName('I')[0];
        child.setAttribute('class', 'fas fa-arrow-right');
    } else {
        target.setAttribute('class', 'active');
        let child = target.getElementsByTagName('I')[0];
        child.setAttribute('class', 'fas fa-arrow-left');
    }
    $("#wrapper").toggleClass("toggled");
}; // Toggle sidebar menu

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

let removeSignAndLowerCase = (str) => {
    str = str.replace(/[àáạảãâầấậẩẫăằắặẳẵ]/g, "a");
    str = str.replace(/[èéẹẻẽêềếệểễ]/g, "e");
    str = str.replace(/[ìíịỉĩ]/g, "i");
    str = str.replace(/[òóọỏõôồốộổỗơờớợởỡ]/g, "o");
    str = str.replace(/[ùúụủũưừứựửữ]/g, "u");
    str = str.replace(/[ỳýỵỷỹ]/g, "y");
    str = str.replace(/đ/g, "d");
    str = str.replace(/[ÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴ]/g, "a");
    str = str.replace(/[ÈÉẸẺẼÊỀẾỆỂỄ]/g, "e");
    str = str.replace(/[ÌÍỊỈĨ]/g, "i");
    str = str.replace(/[ÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠ]/g, "o");
    str = str.replace(/[ÙÚỤỦŨƯỪỨỰỬỮ]/g, "u");
    str = str.replace(/[ỲÝỴỶỸ]/g, "y");
    str = str.replace(/Đ/g, "d");
    str = str.toLowerCase();
    return str;
}; //remove sign and lower case string
