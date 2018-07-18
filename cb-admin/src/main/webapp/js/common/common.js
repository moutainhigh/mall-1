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

    var gridData = $("#" + gridName).data("kendoGrid");
    gridData.dataSource.filter({});
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