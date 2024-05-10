layui.use(function () {
    let $ = layui.$, form = layui.form, layer = layui.layer

    // 自定义验证规则
    form.verify({
        // 确认密码
        confirmPassword: function (value, item) {
            var passwordValue = $('#reg-password').val()
            if (value !== passwordValue) {
                return '两次密码输入不一致'
            }
        }
    });

    // 修改密码
    $("#change-password").click(function () {
        let formData = {
            originalPassword: $("#password input[name=originalPassword]").val(),
            password: $("#password input[name=password]").val()
        };
        $.post(location.pathname, formData, function (data) {
            if (data === "success") {
                localStorage.removeItem(AUTH_KEY)
                location.reload()
            } else {
                layer.msg(data, {icon: 5})
            }
        })
    })


    // 保存设置
    $("#save-setting").click(function () {
        let formData = {
            title: $("#setting input[name=title]").val(),
            url: $("#setting input[name=url]").val(),
            git: $("#setting input[name=git]").val(),
            roadmap: $("#setting input[name=roadmap]").val(),
            cdn: $("#setting input[name=cdn]").val(),
            dashboard: $("#setting input[name=dashboard]").val()
        }
        $.post(location.pathname, formData, function (data) {
            if (data === "success") {
                location.reload()
            } else {
                layer.msg(data, {icon: 5})
            }
        })
    })

})