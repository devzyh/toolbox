/**
 * 全局常量
 */
const AUTH_KEY = "SYS_AUTH"

/**
 * 全局功能
 */
layui.use(function () {
    let $ = layui.$, element = layui.element, layer = layui.layer, util = layui.util, laypage = layui.laypage

    // 导航菜单高亮
    $(".layui-side ul li a").each(function () {
        if (window.location.href.indexOf(this.href) !== -1) {
            return $(this).parent().addClass("layui-this")
        }
    });

    // 分页组件渲染
    let page = $("#page");
    if (page) {
        // 地址栏分页参数查找正则
        let param = "page="
        let re = eval('/(' + param + ')([^&]*)/gi')
        laypage.render({
            elem: page,
            curr: $("#curr").val(),
            limit: $("#limit").val(),
            count: $("#count").val(),
            jump: function (obj, first) {
                // 首次不执行
                if (!first) {
                    let url = location.href;
                    if (url.indexOf(param) === -1) {
                        if (url.indexOf("?") === -1) {
                            url = url + "?" + param + obj.curr
                        } else {
                            url = url + "&" + param + obj.curr
                        }
                        location.href = url
                    } else {
                        location.href = url.replace(re, param + obj.curr)
                    }
                }
            }
        });
    }
});

/**
 * 忽略路径
 * @type {string[]}
 */
const ignorePath = ["/", "/login"]

/**
 * 记住上页地址
 */
function rememberPrevUrl() {
    let key = location.href
    let val = sessionStorage.getItem(key)
    let url = document.referrer
    if (!val && key !== url && ignorePath.indexOf(url.replace(location.origin, "")) === -1) {
        sessionStorage.setItem(key, url)
    }
}

/**
 * 返回上页地址
 */
function backPrevUrl() {
    let key = location.href
    let val = sessionStorage.getItem(key)
    if (val) {
        location.href = val
    } else {
        location.href = "./"
    }
    sessionStorage.removeItem(key)
}

