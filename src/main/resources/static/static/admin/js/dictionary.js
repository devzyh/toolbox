layui.use(function () {
    let $ = layui.$, layer = layui.layer

    // 保存数据
    $("#save").click(function () {
        let formData = {
            code: $("#detail input[name=code]").val(),
            data: $("#detail input[name=data]").val(),
            type: $("#detail select[name=type]").val(),
            remark: $("#detail textarea[name=remark]").val()
        }
        $.post(location.pathname, formData, function (data) {
            if (data === "success") {
                location.reload();
            } else {
                layer.msg(data, {icon: 5})
            }
        })
    })


    // 删除数据
    $("button[name=remove]").on("click", function () {
        let code = this.attributes["row-id"].value;
        layer.confirm("是否删除[" + code + "]的数据？", {
            btn: ['确定', '关闭']
        }, function () {
            $.ajax({
                type: "DELETE",
                url: "/admin/dictionary/" + code,
                success: function (data) {
                    if (data === "success") {
                        location.reload()
                    } else {
                        layer.msg(data, {icon: 5})
                    }
                }
            })
        })
    })

})