var firstClick = true;

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
    firstClick = true;
});

$("#navside>ul>li").click(function () {
    $("#navside>ul>li").removeAttr("class");
    $(this).attr("class", "navside--active");
    $(".spinner").attr("style", "display: flex");
    var id = $(this).attr("id");
    setTimeout(function () {
        $.ajax({
            url: "http://" + window.location.host + "/UltraSoundResult",
            type: 'GET',
            dataType: 'html',
            data: {
                id: id
            },
            error: function(){
                alert('Không thể tải dữ liệu!');
                $(".spinner").attr("style", "display: none");
            }
        }).done(function (result) {
            $(".spinner").attr("style", "display: none");
            $("#container").html(result);
            setCurrentDate();
            $("html, body").animate({
                scrollTop: 0
            }, "slow");
            if (firstClick) {
                $("footer").attr("style", "position: static; bottom: auto");
                firstClick = false;
            }
        });
    }, 300);
});

function setCurrentDate() {
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1; //January is 0!
    var yyyy = today.getFullYear();
    var Str = "Ngày " + dd + " tháng " + mm + " năm" + yyyy;
    $(".date").html(Str);
}
