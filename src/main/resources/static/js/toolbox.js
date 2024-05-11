layui.use(function () {
    let $ = layui.$,
        layer = layui.layer,
        laytpl = layui.laytpl,
        util = layui.util;

    // 配置数据
    let data = null;
    $.ajaxSettings.async = false;
    $.get("/js/data.json", function (resp) {
        data = resp;
    })
    $.ajaxSettings.async = true;

    // 搜索功能
    function search() {
        let key = $("#searchKey").val();
        if (!key) {
            layer.msg("请输入搜索内容！");
            return;
        }

        window.location.href = data.search + key;
    }

    $("#search").click(function () {
        search();
    });

    $("#searchKey").keypress(function (even) {
        if (even.which === 13) {
            search();
        }
    });

    // 渲染数据
    laytpl($("#navs").html()).render(data.navs, function (result) {
        $("#navs").html(result);
    });

    laytpl($("#links").html()).render(data.links, function (result) {
        $("#links").html(result);
    });

    laytpl($("#friends").html()).render(data.friends, function (result) {
        $("#friends").html(result);
    });

    // 返回顶部
    util.fixbar({ bar1: false, bar2: false });

});
