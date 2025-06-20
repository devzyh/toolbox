function initUI() {
    let target = document.getElementById("view")
    target.innerHTML = ""
    let mv = CodeMirror.MergeView(target, {
        value: "",
        orig: "",
        lineNumbers: true,
        highlightDifferences: true,
        collapseIdentical: false,
        allowEditingOriginals: true
    })

    let height = "99.8vh"
    if (mv.leftOriginal()) {
        mv.leftOriginal().setSize(null, height)
    }
    mv.editor().setSize(null, height)
    if (mv.rightOriginal()) {
        mv.rightOriginal().setSize(null, height)
    }
    mv.wrap.style.height = height
}