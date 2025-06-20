layui.use(function () {
    var $ = layui.$

    // TTX加密
    $("#ttxEncrypt").click(function () {
        let data = {
            "input": $("#ttxInput").val(),
            "secret": $("#ttxSecret").val()
        }
        $.post("/open/ttx/encrypt", data, function (res) {
            $("#ttxOutput").val(res)
        })
    })

    // TTX解密
    $("#ttxDecrypt").click(function () {
        let data = {
            "input": $("#ttxInput").val(),
            "secret": $("#ttxSecret").val()
        }
        $.post("/open/ttx/decrypt", data, function (res) {
            $("#ttxOutput").val(res)
        })
    })

    // TTX清空
    $("#ttxClear").click(function () {
        $("#ttxInput").val("")
        $("#ttxSecret").val("")
        $("#ttxOutput").val("")
    })

})
