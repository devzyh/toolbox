layui.use(function () {
    $ = layui.$

    const key = "devzyh-json"
    const editor = new JSONEditor(document.getElementById("jsoneditor"), {
        mode: "code",
        onChange: () => {
            localStorage.setItem(key, editor.getText())
        }
    })
    editor.setText(localStorage.getItem(key) || "")

    // 自定义按钮
    $("button.jsoneditor-redo").each(function () {
        $(this).after("<button type=\"button\" class=\"jsoneditor-clear\" title=\"清空编辑器数据\"></button>")
        $(this).after("<button type=\"button\" class=\"jsoneditor-escape\" title=\"转义JSON数据为字符串\"></button>")
        $(this).after("<button type=\"button\" class=\"jsoneditor-restore\" title=\"去除JSON字符串转义\"></button>")
        return
    })

    /**
     * JSON字符串处理
     * @param e
     * 2: 转义JSON字符串
     * 4: 去除JSON字符串转义符
     */
    function optJson(e) {
        var t = editor.getText();
        if (t = t.replace(/[\r\n]/g, ""), 1 == e || 3 == e) {
            for (var n = [], o = !1, i = 0, r = t.length; r > i; i++) {
                var a = t.charAt(i);
                o && a === o ? "\\" !== t.charAt(i - 1) && (o = !1) : o || '"' !== a && "'" !== a ? o || " " !== a && "	" !== a || (a = "") : o = a, n.push(a)
            }
            t = n.join("")
        }
        (2 == e || 3 == e) && (t = t.replace(/\\/g, "\\\\").replace(/\"/g, '\\"')), 4 == e && (t = t.replace(/\\\\/g, "\\").replace(/\\\"/g, '"')), editor.setText(t)
    }

    $("button.jsoneditor-escape").on("click", function () {
        optJson(2)
    })

    $("button.jsoneditor-restore").on("click", function () {
        optJson(4)
    })

    $("button.jsoneditor-clear").on("click", function () {
        editor.setText("")
        localStorage.setItem(key, "")
    })
})