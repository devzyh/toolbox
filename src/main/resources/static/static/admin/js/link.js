layui.use(function () {
    let $ = layui.$, layer = layui.layer

    // 保存数据
    $("#save").click(function () {
        let formData = {
            id: $("#detail input[name=id]").val(),
            title: $("#detail input[name=title]").val(),
            url: $("#detail input[name=url]").val(),
            type: $("#detail select[name=type]").val(),
            open: $("#detail select[name=open]").val(),
            sort: $("#detail input[name=sort]").val(),
            remark: $("#detail textarea[name=remark]").val()
        };
        $.post(location.pathname, formData, function (data) {
            if (data === "success") {
                location.reload()
            } else {
                layer.msg(data, {icon: 5})
            }
        })
    })

    // 删除数据
    $("button[name=remove]").on("click", function () {
        let id = this.attributes["row-id"].value
        let title = this.attributes["row-title"].value
        layer.confirm("是否删除[" + title + "]的数据？", {
            btn: ['确定', '关闭']
        }, function () {
            $.ajax({
                type: "DELETE",
                url: "/admin/link/" + id,
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