layui.use(function () {
    let $ = layui.$, layer = layui.layer

    const AUTH_KEY = "SYS_AUTH"

    let pwd = localStorage.getItem(AUTH_KEY)
    if (pwd) {
        $("input[name=password]").val(pwd)
        $("input[name=remember]")[0].checked = true
        layui.form.render()
    }

    function login() {
        let formData = {
            password: $("input[name=password]").val()
        }
        $.post(location.pathname, formData, function (data) {
            let redirect = "redirect:"
            if (data.startsWith(redirect)) {
                // 记住密码
                if ($("input[name=remember]:checked").length === 0) {
                    localStorage.removeItem(AUTH_KEY)
                } else {
                    localStorage.setItem(AUTH_KEY, formData.password)
                }
                location.href = data.replace(redirect, "")
            } else {
                layer.msg(data, {icon: 5})
            }
        })
    }

    $("#login").click(function () {
        login()
    })

    $(document).keyup(function (event) {
        if (event.keyCode === 13) {
            login()
        }
    })

})