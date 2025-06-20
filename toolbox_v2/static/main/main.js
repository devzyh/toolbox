layui.use(['element', 'layer'], function () {
    let $ = layui.$,
        laytpl = layui.laytpl,
        element = layui.element,
        layer = layui.layer,
        util = layui.util;

    // 渲染基础数据
    $.get("/data/main.json", function (data) {
        // 顶部链接
        $("#blog").attr("href", data.blog);
        $("#repo").attr("href", data.repo);
        $("#roadmap").attr("href", data.roadmap);
        $("#mail").attr("href", data.mail);

        // 友情链接
        let friends = data.friends;
        laytpl($("#friendTpl").html()).render(friends, function (html) {
            $("#friendLinks").html("友情链接：" + html);
        })
    });

    // 返回顶部
    util.fixbar({bar1: false, bar2: false});

    // 搜索功能
    function search() {
        let key = $("#searchKey").val().trim();
        if (!key) {
            layer.msg("请输入搜索内容！");
            return;
        }
        window.location.href = "/link/?k=" + key;
    }

    $("#search").click(function () {
        search();
    });

    $("#searchKey").keypress(function (even) {
        if (even.which === 13) {
            search();
        }
    });

});
