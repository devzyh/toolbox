layui.use(function () {
    let $ = layui.$,
        laydate = layui.laydate,
        layer = layui.layer

    laydate.render({
        elem: '#inputTime',
        type: 'datetime'
    })

    // 系统时间戳
    setInterval(function () {
        $("#nowUnix").val(Math.round(new Date()));
    }, 100)

    // 获取秒
    $("#getSecond").click(function () {
        $("#inputUnix").val(Math.floor($("#nowUnix").val() / 1000))
    })

    // 获取毫秒
    $("#getMilliSecond").click(function () {
        $("#inputUnix").val($("#nowUnix").val())
    })

    // Unix时间戳转北京时间，支持毫秒
    function toTime(timestamp) {
        let time = new Date(Number(timestamp))
        let y = time.getFullYear()
        let M = (time.getMonth() + 1) > 9 && (time.getMonth() + 1) || ('0' + (time.getMonth() + 1))
        let d = time.getDate() > 9 && time.getDate() || ('0' + time.getDate())
        let h = time.getHours() > 9 && time.getHours() || ('0' + time.getHours())
        let m = time.getMinutes() > 9 && time.getMinutes() || ('0' + time.getMinutes())
        let s = time.getSeconds() > 9 && time.getSeconds() || ('0' + time.getSeconds())
        let ms = time.getMilliseconds()
        let result = y + '-' + M + '-' + d + ' ' + h + ':' + m + ':' + s
        if (ms > 0) {
            result = result + "." + ms
        }
        return result
    }

    // Unix时间戳转北京时间
    $("#toTime").click(function () {
        let timestamp = $("#inputUnix").val()
        if (timestamp <= 9999999999) {
            timestamp *= 1000
        }
        let time = toTime(timestamp)
        if (time.indexOf("NaN") > 0) {
            layer.msg("Unix时间戳格式错误")
            return;
        }
        $("#inputTime").val(time)
    })

    // 北京时间转Unix时间戳
    $("#toUnix").click(function () {
        let timestamp = new Date($("#inputTime").val())
        if (timestamp == "Invalid Date") {
            layer.msg("请选择时间")
            return
        }
        $("#inputUnix").val(Math.floor(timestamp / 1000))
    })

})
