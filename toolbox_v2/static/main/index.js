layui.use(['element', 'layer'], function () {
    let $ = layui.$, element = layui.element, laytpl = layui.laytpl;
    let limit = 10;
    let linkTpl = laytpl($("#linkTpl").html());

    // 渲染数据编辑数据
    $.get("/data/editor.json", function (data) {
        data = data.slice(0, limit);
        linkTpl.render(data, function (html) {
            $("#editorLinks").html(html);
        })
    });

    // 渲染辅助工具数据
    $.get("/data/helper.json", function (data) {
        data = data.slice(0, limit);
        linkTpl.render(data, function (html) {
            $("#helperLinks").html(html);
        })
    });

    // 渲染文档链接数据
    $.get("/data/document.json", function (data) {
        data = data.slice(0, limit);
        linkTpl.render(data, function (html) {
            $("#documentLinks").html(html);
        })
    });

    // 渲染技术博客数据
    $.get("/data/blog.json", function (data) {
        data = data.slice(0, limit);
        linkTpl.render(data, function (html) {
            $("#blogLinks").html(html);
        })
    });
});
