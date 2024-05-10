layui.use(function () {
    let $ = layui.$, layer = layui.layer

    // 保存数据
    function save(id, val) {
        layer.prompt({title: "请输入新标签名称", value: val}, function (value, index, elem) {
            if (value === '') {
                return elem.focus()
            }

            let formData = {
                id: id, name: value
            }

            $.post(location.pathname, formData, function (data) {
                if (data === "success") {
                    location.reload()
                    layer.close(index)
                } else {
                    layer.msg(data, {icon: 5})
                }
            })
        })
    }

    $("#add").click(function () {
        save()
    })

    $("button[name=rename]").on("click", function () {
        let id = this.attributes["row-id"].value
        let name = this.attributes["row-name"].value
        save(id, name)
    })

    // 删除数据
    $("button[name=remove]").on("click", function () {
        let id = this.attributes["row-id"].value;
        let name = this.attributes["row-name"].value;
        layer.confirm("是否删除[" + name + "]的数据？", {
            btn: ['确定', '关闭']
        }, function () {
            $.ajax({
                type: "DELETE", url: "/admin/tag/" + id, success: function (data) {
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