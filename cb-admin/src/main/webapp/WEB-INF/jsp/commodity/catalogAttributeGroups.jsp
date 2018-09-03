<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>属性组管理</title>
    <script type="application/javascript">
        function editItem(e) {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "toEditCatalogAttributeGroup.do?groupId=" + dataItem.groupId;
            }
        }

        function removeItem(e) {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                bootbox.confirm("确定删除吗？", function (result) {
                    if (result) {
                        $.get("removeCatalogAttributeGroup.do", {
                            groupId: dataItem.groupId
                        }, function (data) {
                            if (data=="success") {
                                commonNotify("删除成功！", "success");
                                $("#grid").data("kendoGrid").dataSource.read();
                            } else {
                                commonNotify("删除失败!", "error");
                            }
                        });
                    }
                });
            }
        }
    </script>
</head>
<body>
<aside id="sidebar-main" class="sidebar">
    <jsp:include page="../layouts/sidebar_logo.jsp"/>
    <ul class="ext-tabs-sidebar">
        <li>
            <a href="#sidebar-tab-1"><i class="fa fa-bars"></i> 导航</a>
        </li>
        <li class="active">
            <a href="#sidebar-tab-2"><i class="fa fa-folder"></i> 商品分类</a>
        </li>
    </ul>
    <div class="tab-content">
        <jsp:include page="../layouts/menu.jsp">
            <jsp:param name="active" value="active"/>
        </jsp:include>
        <div id="sidebar-tab-2" class="tab-pane active clearfix">
            <%--<input type="hidden" id="catalogId" data-filter="catalog.catalogId" data-operator="eq" class="grid-filter" value="0"/>--%>
            <div class="sidebar-module">
                <ul class="easyfiletree">
                    <c:set var="instanceUnderCatalog" value="catalogAttributeGroup" scope="request"/>
                    <jsp:include page="catalogTree.jsp"/>
                </ul>
            </div>
        </div>
    </div>
    <div class="sidebar-line"></div>
</aside>
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
                    <li><a href="#">商品管理</a></li>
                    <li><a href="#">属性管理</a></li>
                    <li class="active">属性组管理</li>
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
                    <h2>属性组管理</h2>
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
                    </div>--%>
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
                    <form style="width: 100%">
                        <div class="pull-left">
                            <div class="toolbar-field">
                                <strong>属性组名称:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="groupName" data-operator="contains" class="form-control grid-filter" placeholder="请输入属性组名称"/>
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
                            <h3>属性组列表</h3>
                        </div>
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="toAddCatalogAttributeGroup.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
                                <a href="javascript:void(0);" onclick="editItem()" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
                                <a href="javascript:void(0);" onclick="removeItem()" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>
                            </div>
                        </div>
                    </header>
                </div>
                <div class="table-wrapper">
                    <%
                        HashMap<String, Object> data = new HashMap<String, Object>();
                        data.put("groupId", "#=groupId#");
                    %>
                    <kendo:grid name="grid" pageable="false" sortable="true" selectable="true" height="510" detailTemplate="template">
                        <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
                        <kendo:grid-columns>
                            <kendo:grid-column title="属性组名" field="groupName" width="300px"/>
                            <kendo:grid-column title="分类" field="catalog.catalogName"/>
                            <%--<kendo:grid-column title="是否以图片显示" field="showAsImage" width="150px" template="#= showAsImage ? '是' : '否' #"/>--%>
                            <kendo:grid-column title="创建时间" field="createTime" width="200" format="{0:yyyy-MM-dd HH:mm:ss}"/>
                        </kendo:grid-columns>
                        <kendo:grid-dataBound>
                            <script>
                                function onDataBound(arg) {
                                }
                            </script>
                        </kendo:grid-dataBound>
                        <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                            <kendo:dataSource-schema data="content" total="totalElements">
                                <kendo:dataSource-schema-model>
                                    <kendo:dataSource-schema-model-fields>
                                        <kendo:dataSource-schema-model-field name="createTime" type="date"/>
                                    </kendo:dataSource-schema-model-fields>
                                </kendo:dataSource-schema-model>
                            </kendo:dataSource-schema>
                            <kendo:dataSource-transport>
                                <kendo:dataSource-transport-read url="pageCatalogAttributeGroups.do" type="POST" contentType="application/json"/>
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

                    <kendo:grid-detailTemplate id="template">
                        <kendo:grid name="grid_#=groupId#" pageable="false" selectable="true" sortable="true" scrollable="false">
                            <kendo:grid-columns>
                                <kendo:grid-column title="属性名" field="attributeName" width="350px"/>
                                <kendo:grid-column title="排序" field="sortOrder"/>
                            </kendo:grid-columns>
                            <kendo:dataSource serverPaging="false" serverSorting="false">
                                <kendo:dataSource-transport>
                                    <kendo:dataSource-transport-read url="getAttributesByGroupId.do" data="<%=data %>" type="POST" contentType="application/json"/>
                                    <kendo:dataSource-transport-parameterMap>
                                        <script>
                                            function parameterMap(options) {
                                                return JSON.stringify(options);
                                            }
                                        </script>
                                    </kendo:dataSource-transport-parameterMap>
                                </kendo:dataSource-transport>
                            </kendo:dataSource>
                        </kendo:grid>
                    </kendo:grid-detailTemplate>
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
