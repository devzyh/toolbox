layui.use(function () {
    let $ = layui.$,
        layer = layui.layer,
        util = layui.util

    // 搜索功能
    $("#search").click(function () {
        let key = $("#searchKey").val()
        if (!key) {
            layer.msg("请输入搜索内容！")
            return
        }

        window.location.href = $("#searchKey").attr("search") + key
    })

    $("#searchKey").keypress(function (even) {
        if (even.which === 13) {
            $("#search").click()
        }
    })

    $("#searchKey").focus()

    // 返回顶部
    util.fixbar({bar1: false, bar2: false})

})
