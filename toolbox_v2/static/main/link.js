// 获取页面参数
function getUrlParam(name) {
    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    let r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) {
        return decodeURIComponent(r[2]);
    }
    return null; //返回参数值
}

// 模糊搜索
function filterData(data, key) {
    if (!key) {
        return data;
    }

    return data.filter(function (item) {
        if (item.name.toLowerCase().includes(key)) {
            return true;
        }
        if (item.remark != null && item.remark.toLowerCase().includes(key)) {
            return true;
        }
        return false;
    });
}

// 页面加载
layui.use(['element', 'layer'], function () {
    let $ = layui.$, element = layui.element, laytpl = layui.laytpl;

    // 页面参数
    let types = ["editor", "helper", "document", "blog"]
    let type = getUrlParam("t");
    let key = getUrlParam("k");
    if (type) {
        types = types.filter(t => t === type);
    }
    if (key) {
        key = key.trim().toLowerCase();
    }

    // 检索数据
    let linkTpl = laytpl($("#linkTpl").html());
    $.each(types, function (index, value) {
        $.get("/data/" + value + ".json", function (data) {
            data = filterData(data, key);
            linkTpl.render(data, function (html) {
                $("#links").append(html);
            })
        });
    });

});
