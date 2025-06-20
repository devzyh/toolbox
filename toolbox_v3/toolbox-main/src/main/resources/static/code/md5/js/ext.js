layui.use(function () {
    var $ = layui.$

    // 生成
    function md5(upper = false, bit16 = false) {
        let input = $("#md5Input").val()
        let output
        try {
            output = CryptoJS.MD5(input).toString()
            if (bit16) {
                output = output.substring(8, 24)
            }
            if (upper) {
                output = output.toUpperCase()
            }
        } catch (err) {
            output = "错误信息：" + err.message
        }
        $("#md5Output").val(output)
    }

    $("#md5").click(function () {
        md5()
    })

    $("#md5Upper").click(function () {
        md5(true)
    })

    $("#md516").click(function () {
        md5(false, true)
    })

    $("#md516Upper").click(function () {
        md5(true, true)
    })

    // 清空
    $("#md5Clear").click(function () {
        $("#md5Input").val("")
        $("#md5Output").val("")
    })

})
