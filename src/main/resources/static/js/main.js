$("#btn-result").click(function () {
    if ($(this).attr("class") === "navtop--active") {
        $(this).removeAttr("class");
        $("#btn-result>span").attr("class", "fas fa-angle-down");
        $("main, .spinner-content, .footer-content").removeAttr("style");
        $("#tool").fadeOut(500);
    } else {
        $(this).attr("class", "navtop--active");
        $("#btn-report").removeAttr("class");
        $("#btn-result>span").attr("class", "fas fa-angle-up");
        $("main").attr("style", "margin-left: 14.375rem");
        $(".spinner-content").attr("style", "padding-right: 14.375rem;");
        $(".footer-content").attr("style", "margin-left: 14.375rem");
        $("#tool").fadeIn(500);
    }
    $("#navside").animate({
        height: 'toggle'
    }, 350);
});

$("#btn-report").click(function () {
    $(this).attr("class", "navtop--active");
    $("#btn-result").removeAttr("class");
    $("#navside>ul>li").removeAttr("class");
    $("#tool").fadeOut(500);
    $("#navside").animate({
        height: 'hide'
    }, 350);
    $(".spinner").attr("style", "display: flex");
    $("main, .spinner-content, .footer-content").removeAttr("style");
    setTimeout(function () {
        $(".spinner").removeAttr("style");
    }, 300);
});

$("#navside>ul>li").click(function () {
    $("#navside>ul>li").removeAttr("class");
    $(this).attr("class", "navside--active");
    $(".spinner").attr("style", "display: flex");
    var id = $(this).attr("id");

});