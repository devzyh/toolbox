// 配置界面
ace.config.set("basePath", "//cdn.staticfile.net/ace/1.5.3")

const xsltKey = "devzyh-xslt"
const xsltEditor = ace.edit("xsltEditor")
xsltEditor.setTheme("ace/theme/sqlserver")
xsltEditor.setFontSize("14px")
xsltEditor.setOption("wrap", "free")
xsltEditor.setShowPrintMargin(false)
xsltEditor.getSession().setMode("ace/mode/xml")
xsltEditor.getSession().setValue(localStorage.getItem(xsltKey) || "")
xsltEditor.getSession().on("change", function () {
    localStorage.setItem(xsltKey, xsltEditor.getSession().getValue())
})

const xmlKey = "devzyh-xslt-xml"
const sourceEditor = ace.edit("sourceEditor")
sourceEditor.setTheme("ace/theme/sqlserver")
sourceEditor.setFontSize("14px")
sourceEditor.setOption("wrap", "free")
sourceEditor.setShowPrintMargin(false)
sourceEditor.getSession().setMode("ace/mode/xml")
sourceEditor.getSession().setValue(localStorage.getItem(xmlKey) || "")
sourceEditor.getSession().on("change", function () {
    localStorage.setItem(xmlKey, sourceEditor.getSession().getValue())
})

const resultEditor = ace.edit("resultEditor")
resultEditor.setFontSize("14px")
resultEditor.setOption("wrap", "free")
resultEditor.setShowPrintMargin(false)
resultEditor.setReadOnly(true)

layui.use(function () {
    var $ = layui.$

    $("#convert").click(function () {
        try {
            resultEditor.setValue(convertOriginalData(xsltEditor.getValue(), sourceEditor.getValue()))
        } catch (err) {
            resultEditor.setValue("错误信息：" + err.message)
        }
    })

    $("#formatSource").click(function () {
        sourceEditor.setValue($.format(sourceEditor.getValue(), {method: 'xml'}))
    })

    $("#clear").click(function () {
        resultEditor.setValue("")
    })
})

// 屏蔽Ctrl+S
window.addEventListener("keydown", function (e) {
    if (e.ctrlKey && e.code == 83) {
        e.preventDefault()
    }
})

// 转换原始数据
function convertOriginalData(xslt, source) {
    try {
        let processor = new XSLTProcessor()
        processor.importStylesheet(new DOMParser().parseFromString(xslt, "text/xml"))
        let result = processor.transformToDocument(new DOMParser().parseFromString(source, "text/xml"))
        return result.body.getElementsByTagName("pre")[0].innerHTML
    } catch (err) {
        return "错误信息：" + err.message
    }
}