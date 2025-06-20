layui.use(function () {
    var $ = layui.$

    // 编码
    $("#baseEncode").click(function () {
        let input = $("#baseInput").val()
        let output
        try {
            output = btoa(input)
        } catch (err) {
            output = "错误信息：" + err.message
        }
        $("#baseOutput").val(output)
    })

    // 解码
    $("#baseDecode").click(function () {
        let input = $("#baseInput").val()
        let output
        try {
            output = atob(input)
        } catch (err) {
            output = "错误信息：" + err.message
        }
        $("#baseOutput").val(output)
    })

    // 交换
    $("#baseExchange").click(function () {
        let tmp = $("#baseInput").val()
        $("#baseInput").val($("#baseOutput").val())
        $("#baseOutput").val(tmp)
    })

    // 清空
    $("#baseClear").click(function () {
        $("#baseInput").val("")
        $("#baseOutput").val("")
    })

})
