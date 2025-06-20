layui.use(function () {
    var $ = layui.$

    function ascii(str) {
        var value = ''
        for (var i = 0; i < str.length; i++) {
            value += '\&#x' + left_zero_4(parseInt(str.charCodeAt(i)).toString(16)) + ''
        }
        return value
    }

    function unicode(str) {
        var value = ''
        for (var i = 0; i < str.length; i++) {
            value += '\\u' + left_zero_4(parseInt(str.charCodeAt(i)).toString(16))
        }
        return value
    }

    function left_zero_4(str) {
        if (str != null && str != '' && str != 'undefined') {
            if (str.length == 2) {
                return '00' + str
            }
        }
        return str
    }

    function unicode1(str) {
        var value = ''
        for (var i = 0; i < str.length; i++) {
            value += '&#' + str.charCodeAt(i) + ''
        }
        return value
    }

    function reconvert(str) {
        str = str.replace(/(\\u)(\w{1,4})/gi, function ($0) {
            return (String.fromCharCode(parseInt((escape($0).replace(/(%5Cu)(\w{1,4})/g, "$2")), 16)))
        })
        str = str.replace(/(&#x)(\w{1,4})/gi, function ($0) {
            return String.fromCharCode(parseInt(escape($0).replace(/(%26%23x)(\w{1,4})(%3B)/g, "$2"), 16))
        })
        str = str.replace(/(&#)(\d{1,6})/gi, function ($0) {
            return String.fromCharCode(parseInt(escape($0).replace(/(%26%23)(\d{1,6})(%3B)/g, "$2")))
        })

        return str
    }

    // 执行转换
    function unicodeOpt(opt) {
        let input = $("#uniInput").val()
        let output
        switch (opt) {
            case "CONVERT_FMT1":
                output = ascii(input)
                break
            case "CONVERT_FMT2":
                output = unicode(input)
                break
            case "CONVERT_FMT3":
                output = unicode1(input)
                break
            case "RECONVERT":
                output = reconvert(input)
                break
            default:
                output = ""
                break
        }
        $("#uniOutput").val(output)
    }

    // 中文转Unicode
    $("#uniCONVERT_FMT2").click(function () {
        unicodeOpt("CONVERT_FMT2")
    })

    // Unicode转中文
    // ASCII转Unicode
    $("#uniRECONVERT").click(function () {
        unicodeOpt("RECONVERT")
    })
    $("#uniRECONVERT1").click(function () {
        unicodeOpt("RECONVERT")
    })

    // Unicode转ASCII
    $("#uniCONVERT_FMT3").click(function () {
        unicodeOpt("CONVERT_FMT3")
    })

    // 中文转&amp#XXXX
    $("#uniCONVERT_FMT1").click(function () {
        unicodeOpt("CONVERT_FMT1")
    })

    // 交换
    $("#uniExchange").click(function () {
        let tmp = $("#urlInput").val()
        $("#uniInput").val($("#uniOutput").val())
        $("#uniOutput").val(tmp)
    })

    // 清空
    $("#uniClear").click(function () {
        $("#uniInput").val("")
        $("#uniOutput").val("")
    })

})
