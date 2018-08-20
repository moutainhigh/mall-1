<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>规格过滤器配置</title>
    <script type="text/javascript">

        function detailItem(){
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "specFilterDetail.do?filterId=" + dataItem.filterId;
            }
        }

        function editItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "toEditSpecFilter.do?filterId=" + dataItem.filterId;
            }
        }

        function removeItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                bootbox.confirm("确认删除吗？", function (result) {
                    if (result) {
                        $.get("removeSpecFilterById.do", {
                            filterId: dataItem.filterId
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

    </script>
</head>
<body>
<jsp:include page="../layouts/left.jsp"/>
<jsp:include page="../layouts/sidebarRight.jsp"/>
<div id="main" class="clearfix">


    <header id="header-main">
        <div class="header-main-top">
            <div class="pull-left">
                <!-- * This is the responsive logo * -->
                <a href="#" id="logo-small"><h4></h4><h5></h5></a>
            </div>
            <div class="pull-right">
                <!-- * This is the trigger that will show/hide the menu * -->
                <!-- * if the layout is in responsive mode              * -->
                <a href="#" id="responsive-menu-trigger">
                    <i class="fa fa-bars"></i>
                </a>
            </div>
        </div>
        <!-- End #header-main-top -->
        <div class="header-main-bottom">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">运营管理</a></li>
                    <li><a href="#">规格过滤器配置</a></li>
                </ul>
                <!-- End .breadcrumb -->
            </div>
            <div class="pull-right">
                <p>Version 1.0.0</p>
            </div>
        </div>
        <!-- End #header-main-bottom -->
    </header>
    <!-- End #header-main -->

    <div id="content" class="clearfix">


        <header id="header-sec">
            <div class="inner-padding">
                <div class="pull-left">
                    <h2>过滤器配置</h2>
                </div>
                <div class="pull-right">

                    <div class="btn-group">
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
            <!-- End .actionbar-->
            <div class="inner-padding">
                <div class="toolbar responsive-helper">
                    <form style="width: 100%">
                        <div class="pull-left">
                            <div class="toolbar-field">
                                <strong>规格过滤器名称:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="filterName" data-operator="contains" class="form-control grid-filter" placeholder="请输入过滤器名称"/>
                            </div>
                        </div>
                        <!-- End .pull-left -->
                        <div class="pull-right">
                            <div class="toolbar-field">
                                <button type="button" class="btn btn-default" onclick="reloadGridFilters('grid')"><i class="fa fa-search"></i>查询</button>
                                &nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-default" onclick="clearFilters('grid')">清空</button>
                            </div>
                        </div>
                        <!-- End .pull-right -->
                    </form>
                </div>
                <!-- End .toolbar -->

                <div class="spacer-10"></div>

                <div class="toolbar responsive-helper">
                    <header>
                        <div class="pull-left">
                            <h3>过滤器列表</h3>
                        </div>
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="javascript:void(0);" onclick="detailItem()" class="btn btn-default"><i class="fa fa-info-circle"></i>&nbsp;详情</a>
                                <a href="toAddSpecFilter.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
                                <a href="javascript:void(0);" onclick="editItem()" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
                                <a href="javascript:void(0);" onclick="removeItem()" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>
                            </div>
                        </div>
                    </header>
                </div>
                <div class="table-wrapper">
                    <kendo:grid name="grid" pageable="false" sortable="true" selectable="true" height="450">
                        <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
                        <kendo:grid-columns>
                            <kendo:grid-column title="规格过滤器名称" field="filterName" width="350px"/>
                            <kendo:grid-column title="排序" field="sortOrder"/>
                            <kendo:grid-column title="启用" field="enabled" template="#= enabled ? '是' : '否' #"/>
                            <kendo:grid-column title="创建时间" field="createTime" format="{0:yyyy-MM-dd HH:mm}"/>
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
                                <kendo:dataSource-transport-read url="pageSpecFilters.do" type="POST" contentType="application/json"/>
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

            <!-- End .inner-padding -->
        </div>

        <jsp:include page="../layouts/footer.jsp"/>
        <!-- End #footer-main -->
    </div>
    <!-- End #content -->
</div>
<!-- End #main -->


</body>
</html>
