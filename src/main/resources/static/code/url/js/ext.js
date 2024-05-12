layui.use(function () {
    var $ = layui.$

    // 编码
    $("#urlEncode").click(function () {
        let input = $("#urlInput").val()
        let output
        try {
            output = encodeURIComponent(input)
        } catch (err) {
            output = "错误信息：" + err.message
        }
        $("#urlOutput").val(output)
    })

    // 解码
    $("#urlDecode").click(function () {
        let input = $("#urlInput").val()
        let output
        try {
            output = decodeURIComponent(input)
        } catch (err) {
            output = "错误信息：" + err.message
        }
        $("#urlOutput").val(output)
    })

    // 交换
    $("#urlExchange").click(function () {
        let tmp = $("#urlInput").val()
        $("#urlInput").val($("#urlOutput").val())
        $("#urlOutput").val(tmp)
    })

    // 清空
    $("#urlClear").click(function () {
        $("#urlInput").val("")
        $("#urlOutput").val("")
    })

})
