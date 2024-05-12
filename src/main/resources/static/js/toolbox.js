layui.use(function () {
    let $ = layui.$,
        layer = layui.layer,
        util = layui.util;

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

    // 返回顶部
    util.fixbar({ bar1: false, bar2: false });

});
