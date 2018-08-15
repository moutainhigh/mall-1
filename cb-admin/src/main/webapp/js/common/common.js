$(function () {
    bootbox.setDefaults({
        locale:"zh_CN"
    });
});

/**
 * Created by Aidy_He on 16/1/16.
 */
/**
 * 获取表格选中行
 * @param gridName
 * @returns {*}
 */
function getSelectedGridItem(gridName) {
    var grid = $("#" + gridName).data("kendoGrid");
    var selectedRows = grid.select();
    if (selectedRows.length > 0) {
        return grid.dataItem(selectedRows[0]);
    }
    commonNotify('请从表格中选择要操作的数据', "error");
    return null;
}

function getSelectedTreeListItem(gridName) {
    var grid = $("#" + gridName).data("kendoTreeList");
    var selectedRows = grid.select();
    if (selectedRows.length > 0) {
        return grid.dataItem(selectedRows[0]);
    }
    commonNotify('请从表格中选择要操作的数据', "error");
    return null;
}

function commonNotify(text, type) {
    $.e_notify.notification({
        text: text,
        position: 'top',
        target: '',
        delay: 0,
        time: 5000,
        speed: 500,
        effect: 'slide',
        sticky: false,
        closable: true,
        className: 'notification-' + type,
        onShow: function () {
        },
        onHide: function () {
        }
    });
}

/**
 * 清空当前表格的查询条件
 * @param gridName
 */
function clearFilters(gridName) {
    $(".grid-filter").each(function () {
        $(this).val("");
    });
    debugger
    var gridData = $("#" + gridName).data("kendoGrid");
    gridData.dataSource._take=10;
    gridData.dataSource.filter({});
}

/**
 * 根据class重置
 * @param className
 */
function clearInput(className){
    $("."+className).each(function () {
        var e = $(this)[0];
        e.value=e.defaultValue;//取消时还原
    });
}
/**
 * 应用表格的查询条件，并查询
 * @param gridName
 * @param filters,operator:eq,neq,gt,gte,lt,lte,startswith,endswith,contains,doesnotcontain
 */
function reloadGridFilters(gridName) {
    var gridData = $("#" + gridName).data("kendoGrid");
    //得到当前表格的过滤器
    var currFilterObj = gridData.dataSource.filter();
    var currentFilters =null;
    //先删除之前存在的过滤字段
    if (currFilterObj != null) {
        currentFilters = currFilterObj.filters;
        if (currentFilters != null && currentFilters.length > 0) {
            currentFilters.splice(0, currentFilters.length);
        }
    }
    if(currentFilters==null){
       currentFilters = [];
    }
    //如果当前过滤器为空，则创建一个新的
    //应用过滤器
    $(".grid-filter").each(function (index) {
        var filterField = $(this).data("filter");
        var filterValue = $.trim($(this).val());
        if (filterValue != "") {
            currentFilters.push({
                field: filterField,
                operator: $(this).data("operator"),
                value: filterValue
            });
        }
    });

    //$.each(filters, function (idx, val) {
    //
    //});
    gridData.dataSource.filter({
        logic: "and",
        filters: currentFilters
    });
}

/**
 * 加法
 * 解决js中小数运算精度丢失
 * @param num1
 * @param num2
 * @returns {string}
 * @author lxc
 * @date 2018-08-09 11:36
 */
function add(num1,num2){
    var r1,r2,m,n;
    try{r1=num1.toString().split(".")[1].length}catch(e){r1=0}
    try{r2=num2.toString().split(".")[1].length}catch(e){r2=0}
    m = Math.pow(10,Math.max(r1,r2));
    n = (r1>=r2)?r1:r2;
    return ((num1*m + num2*m)/m).toFixed(n);
}

/**
 * 乘法
 * 解决js中小数运算精度丢失
 * @param num1
 * @param num2
 * @returns {number}
 * @author lxc
 * @date 2018-08-09 11:36
 */
function mul(num1,num2){
    var m = 0;
    try{m+=num1.toString().split(".")[1].length}catch(e){}
    try{m+=num2.toString().split(".")[1].length}catch(e){}
    return (Number(num1.toString().replace(".",""))*Number(num2.toString().replace(".","")))/Math.pow(10,m)
}