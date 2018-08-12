$("#btn-result").click(function () {
    if ($(this).attr("class") === "navtop--active") {
        $(this).removeAttr("class");
        $("#btn-result>span").attr("class", "fas fa-angle-down");
        $("#tool").fadeOut(500);
    } else {
        $(this).attr("class", "navtop--active");
        $("#btn-report").removeAttr("class");
        $("#btn-result>span").attr("class", "fas fa-angle-up");
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

    setTimeout(function () {
        $(".spinner").attr("style", "display: none");
    }, 300);
});

$("#navside>ul>li").click(function () {
    $("#navside>ul>li").removeAttr("class");
    $(this).attr("class", "navside--active");
    $(".spinner").attr("style", "display: flex");
    var id = $(this).attr("id");
    console.log(id);
});