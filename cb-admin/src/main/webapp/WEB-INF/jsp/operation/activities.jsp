<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>活动管理</title>
    <script type="text/javascript">


        function editItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "toEditActivity.do?activityId=" + dataItem.activityId;
            }
        }

        function removeItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                bootbox.confirm("确认删除吗？", function (result) {
                    if (result) {
                        $.get("removeActivityById.do", {
                            activityId: dataItem.activityId
                        }, function (data) {
                            if (data) {
                                bootbox.alert("成功",function(){
                                    $("#grid").data("kendoGrid").dataSource.read();
                                });
                            } else {
                                bootbox.alert("失败");
                            }
                        });
                    }
                });
            }
        }

        function toActivityCommodities() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "toActivityCommodities.do?activityId=" + dataItem.activityId;
            }
        }

        function addActivityCommoditys(){
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "addActivityCommoditys.do?activityId=" + dataItem.activityId;
            }
        }

        function effectOrDiscontinueActivity(aState){
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                bootbox.confirm("确认生效/终止该活动吗？", function (result) {
                    if (result) {
                        $.get("effectOrDiscontinueActivity.do", {
                            activityId: dataItem.activityId,
                            activityState : aState
                        }, function (data) {
                            if (data) {
                                bootbox.alert("成功");
                                $("#grid").data("kendoGrid").dataSource.read();
                            } else {
                                bootbox.alert("失败,活动未启用或状态相同！");
                            }
                        });
                    }
                });
            }
        }

        function formatActivityScope(activityScope) {
            switch (activityScope) {
                case "COMMODITY":
                    return "商品";
            }
        }

        function formatActivityType(activityType) {
            switch (activityType) {
                case "TG":
                    return "团购";
            }
        }

        function formatActivityState(activityState) {
            switch (activityState) {
                case "WAIT_TAKE_EFFECT":
                    return "待生效";
                case "TAKE_EFFECT":
                    return "生效";
                case "DISCONTINUE":
                    return "中止";
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
                    <li><a href="#">活动推广</a></li>
                    <li class="active"><a href="#">活动管理</a></li>
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
                    <h2>活动管理</h2>
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
                    <!-- End .btn-group -->
                </div>
            </div>
            <!-- End .inner-padding -->
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
                    <form>
                        <div class="pull-left">
                            <div class="toolbar-field">
                                <strong>活动编码:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="activityCode" data-operator="contains" class="form-control grid-filter" placeholder="请输入活动编码"/>
                            </div>
                            <div class="toolbar-field">
                                <strong>活动名称:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="activityName" data-operator="contains" class="form-control grid-filter" placeholder="请输入活动名称"/>
                            </div>
                            <div class="toolbar-field">
                                <strong>活动状态:</strong>
                            </div>
                            <div class="toolbar-field">
                                <select class="form-control simpleselect">
                                    <option value="">全部</option>
                                    <option value="TAKE_EFFECT">生效</option>
                                    <option value="DISCONTINUE">终止</option>
                                </select>
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
                            <h3>活动列表</h3>
                        </div>
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="toAddActivity.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
                                <a href="javascript:editItem();"  class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
                                <a href="javascript:removeItem();"  class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>
                            </div>
                            <div class="btn-group">
                                <a href="javascript:void(0);" onclick="effectOrDiscontinueActivity('TAKE_EFFECT')" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;生效</a>
                                <a href="javascript:void(0);" onclick="effectOrDiscontinueActivity('DISCONTINUE')" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp;终止</a>
                            </div>
                            <div class="btn-group">
                                <a href="javascript:toActivityCommodities();" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;添加活动商品</a>
                            </div>
                        </div>
                    </header>
                </div>
                <div class="table-wrapper">
                    <kendo:grid name="grid" pageable="true" sortable="true" filterable="true" selectable="true" height="450" resizable="true">
                        <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
                        <kendo:grid-filterable extra="false">
                            <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
                            <kendo:grid-filterable-operators>
                                <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                                <kendo:grid-filterable-operators-date gte="小于" eq="等于" lte="大于"/>
                            </kendo:grid-filterable-operators>
                        </kendo:grid-filterable>
                        <kendo:grid-columns>
                            <kendo:grid-column title="图片" field="picPath" width="140px" template="<img src='../images/#=picPath#_192_69.jpg'  width='120px' height='60px'/>" sortable="false" filterable="false"/>
                            <kendo:grid-column title="活动编码" field="activityCode" width="130"/>
                            <kendo:grid-column title="活动名称" field="activityName" width="130"/>
                            <kendo:grid-column title="开始时间" field="startTime" width="140" format="{0:yyyy-MM-dd HH:mm}"/>
                            <kendo:grid-column title="结束时间" field="endTime" width="140" format="{0:yyyy-MM-dd HH:mm}"/>
                            <kendo:grid-column title="活动范围" field="activityScope" width="120" template="#=formatActivityScope(activityScope)#"/>
                            <kendo:grid-column title="活动类型" field="activityType" width="130" template="#=formatActivityType(activityType)#"/>
                            <kendo:grid-column title="活动状态" field="activityState" width="130" template="#=formatActivityState(activityState)#"/>
                            <%--<kendo:grid-column title="规则互斥" field="ruleMutex" width="130" template="#= ruleMutex ? '是' : '否' #"/>--%>
                            <%--<kendo:grid-column title="支持优惠券" field="supportCoupon" width="130" template="#= supportCoupon ? '是' : '否' #"/>--%>
                            <kendo:grid-column title="备注" field="remark" width="150" filterable="false"/>
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
                                <kendo:dataSource-transport-read url="pageActivities.do" type="POST" contentType="application/json"/>
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
        <!-- End .window -->

        <!-- ********************************************
             * FOOTER MAIN:                             *
             *                                          *
             * the part which contains things like      *
             * chat, buttons, copyright and             *
             * dropup menu(s).                          *
             ******************************************** -->

        <jsp:include page="../layouts/footer.jsp"/>
        <!-- End #footer-main -->
    </div>
    <!-- End #content -->
</div>
<!-- End #main -->

</body>
</html>
