ace.config.set("basePath", "//cdn.staticfile.net/ace/1.5.3")

const editor = ace.edit("editor")
const cacheKey = "devzyh-xml"
editor.setTheme("ace/theme/sqlserver")
editor.setFontSize("14px")
editor.setOption("wrap", "free")
editor.setShowPrintMargin(false)
editor.getSession().setMode("ace/mode/xml")
editor.getSession().setValue(localStorage.getItem(cacheKey) || "")
editor.getSession().on("change", function () {
    localStorage.setItem(cacheKey, editor.getSession().getValue())
})

layui.use(function () {
    var form = layui.form
    var indent = "  "

    form.on('select(xml-indent)', function (data) {
        indent = data.value
        $("#format").click()
    })

    $("#format").click(function () {
        editor.setValue($.format(editor.getValue(), {method: 'xml', step: indent}))
    })

    $("#compress").click(function () {
        editor.setValue($.format(editor.getValue(), {method: 'xmlmin'}))
    })

    $("#clear").click(function () {
        editor.setValue("")
        localStorage.setItem(cacheKey, "")
    })
})