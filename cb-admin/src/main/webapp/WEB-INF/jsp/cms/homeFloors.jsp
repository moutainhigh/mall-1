<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if !IE]><!-->
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>楼层管理</title>

    <script type="text/javascript">


        function editItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "toEditHomeFloor.do?floorId=" + dataItem.floorId;
            }
        }

        function removeItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                bootbox.confirm("确认删除吗？", function (result) {
                    if (result) {
                        $.get("removeHomeFloorById.do", {
                            floorId: dataItem.floorId
                        }, function (data) {
                            if (data) {
                                bootbox.alert("成功");
                                $("#grid").data("kendoGrid").dataSource.read();
                            } else {
                                bootbox.alert("失败");
                            }
                        });
                    }
                });
            }
        }

        function enabledItem(enabled) {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                $.get("enableHomeFloorById.do", {
                    floorId: dataItem.floorId,
                    enabled: enabled,
                    rad: Math.random()
                }, function (data) {
                    if ("success" == data) {
                        commonNotify("操作成功！", "success");
                        $("#grid").data("kendoGrid").dataSource.read();
                    } else {
                        commonNotify("操作失败!", "error");
                    }
                });
            }
        }

    </script>
</head>
<body>
<jsp:include page="../layouts/left.jsp"/>
<jsp:include page="../layouts/sidebarRight.jsp"/>
<div id="main" class="clearfix">

    <header id="header-main">
        <div class="header-main-top">
            <div class="pull-left">
                <a href="#" id="logo-small"><h4></h4><h5></h5></a>
            </div>
            <div class="pull-right">
                <a href="#" id="responsive-menu-trigger">
                    <i class="fa fa-bars"></i>
                </a>
            </div>
        </div>
        <div class="header-main-bottom">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">内容管理</a></li>
                    <li class="active"><a href="#">首页楼层管理</a></li>
                </ul>
            </div>
            <div class="pull-right">
                <p>Version 1.0.0</p>
            </div>
        </div>
    </header>

    <div id="content" class="clearfix">

        <header id="header-sec">
            <div class="inner-padding">
                <div class="pull-left">
                    <h2>首页楼层管理</h2>
                </div>
                <div class="pull-right">

                    <%--<div class="btn-group">
                        <a class="btn btn-default" href="#">
                            <i class="fa fa-star"></i>
                        </a>
                        <a class="btn btn-default" href="#" id="modal-update-trigger">
                            Modal
                        </a>
                        <a class="btn btn-default" href="#">
                            <i class="fa fa-cog"></i>
                        </a>
                    </div>
--%>
                </div>
            </div>
        </header>

        <div class="window">
            <div class="actionbar">
                <div class="pull-left">
                    <a href="#" class="btn small-toggle-btn" data-toggle-sidebar="left"></a>
                    <a href="#" id="lockscreen-slider-trigger" class="btn">
                        <i class="fa fa-lock"></i>&nbsp; Lock screen
                    </a>
                </div>

                <div class="pull-right">
                    <a data-toggle-sidebar="right" class="btn small-toggle-btn" href="#"></a>
                </div>
            </div>
            <div class="inner-padding">
                <div class="toolbar responsive-helper">
                    <form>
                        <div class="pull-left">
                            <div class="toolbar-field">
                                <strong>楼层名称:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')" type="text" data-filter="floorName" data-operator="contains" class="form-control grid-filter" placeholder="请输入楼层名称"/>
                            </div>
                            <div class="toolbar-field">
                                <strong>状态:</strong>
                            </div>
                            <div class="toolbar-field">
                                <select data-filter="enabled" data-operator="eq" class="form-control  grid-filter">
                                    <option value="">全部</option>
                                    <option value="true">启用</option>
                                    <option value="false">停用</option>
                                </select>
                            </div>
                        </div>
                        <div class="pull-right">
                            <div class="toolbar-field">
                                <button type="button" class="btn btn-default" onclick="reloadGridFilters('grid')"><i class="fa fa-search"></i>查询</button>
                                &nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-default" onclick="clearFilters('grid')">清空</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="spacer-10"></div>
                <div class="toolbar responsive-helper">
                    <header>
                        <div class="pull-left">
                            <h3>首页楼层列表</h3>
                        </div>
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="toAddHomeFloor.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
                                <a href="javascript:void(0);" onclick="editItem()" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
                                <a href="javascript:void(0);" onclick="removeItem()" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>
                            </div>
                            <div class="btn-group">
                                <a href="javascript:void(0);" onclick="enabledItem(true)" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;启用</a>
                                <a href="javascript:void(0);" onclick="enabledItem(false)" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 停用</a>
                            </div>
                        </div>
                    </header>
                </div>
                <div class="table-wrapper">
                    <kendo:grid name="grid" pageable="true" sortable="true" filterable="true" selectable="true" height="500" resizable="true">
                        <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
                        <kendo:grid-filterable extra="false">
                            <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
                            <kendo:grid-filterable-operators>
                                <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                                <kendo:grid-filterable-operators-date gte="小于" eq="等于" lte="大于"/>
                            </kendo:grid-filterable-operators>
                        </kendo:grid-filterable>
                        <kendo:grid-columns>
                            <kendo:grid-column title="楼层名称" field="floorName" width="150px"/>
                            <kendo:grid-column title="排序" field="sortOrder" width="100px"/>
                            <kendo:grid-column title="品牌数量" field="brandAmount" width="100px"/>
                            <kendo:grid-column title="类别数量" field="categoryAmount" width="100px"/>
                            <kendo:grid-column title="商品数量" field="commodityAmount" width="100px"/>
                            <%--<kendo:grid-column title="模板" field="templateId" width="100px"/>--%>
                            <kendo:grid-column title="备注" field="remark" filterable="false" width="200px"/>
                            <kendo:grid-column title="状态" field="enabled" template="#= enabled ? '启用' : '停用' #" width="80"/>
                        </kendo:grid-columns>
                        <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                            <kendo:dataSource-schema data="content" total="totalElements">
                                <kendo:dataSource-schema-model>
                                    <kendo:dataSource-schema-model-fields>
                                        <kendo:dataSource-schema-model-field name="createTime" type="date"/>
                                    </kendo:dataSource-schema-model-fields>
                                </kendo:dataSource-schema-model>
                            </kendo:dataSource-schema>
                            <kendo:dataSource-transport>
                                <kendo:dataSource-transport-read url="pageHomeFloors.do" type="POST" contentType="application/json"/>
                                <kendo:dataSource-transport-parameterMap>
                                    <script>
                                        function parameterMap(options, type) {
                                            return JSON.stringify(options);
                                        }
                                    </script>
                                </kendo:dataSource-transport-parameterMap>
                            </kendo:dataSource-transport>
                        </kendo:dataSource>
                    </kendo:grid>
                </div>

            </div>

            <div class="spacer-40"></div>
            <div class="hr-totop"><span>Top</span></div>
            <div class="spacer-40"></div>

        </div>

        <jsp:include page="../layouts/footer.jsp"/>
    </div>
</div>
</body>
</html>
