$("#menu-toggle").click(function () {
    if($("#menu-toggle").attr("class") == "active") {
        $("#menu-toggle").removeAttr("class");
        $("#menu-toggle").html("<a class=\"nav-link\"><i class=\"fas fa-arrow-right\"></i> Kết quả siêu âm</a>");
    } else {
        $("#menu-toggle").attr("class", "active");
        $("#menu-toggle").html("<a class=\"nav-link\"><i class=\"fas fa-arrow-left\"></i> Kết quả siêu âm</a>");
    }
    $("#wrapper").toggleClass("toggled");
});

$("#menu-toggle").click();

$("#report").click(function () {
    ///////////////////////////////////
});