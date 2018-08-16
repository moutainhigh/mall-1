<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>频道栏目管理</title>
    <script type="application/javascript">
        function testItem() {
            $.get("getArticleChannelsForMobile.do", function (data) {
                if (data != null) {
                    console.log(data);
                } else {
                    commonNotify("获取失败!", "error");
                }
            });

        }

        function formatChannelPosition(channelPosition){
            switch(channelPosition) {
                case "HEARDER":{
                    return "头部";
                }
                case "BANNER":{
                    return "横福";
                }
                case "FOOTER":{
                    return "脚部";
                }
            }
        }

        function formatChannelStyle(channelStyle){
            switch(channelStyle) {
                case "DETAIL":{
                    return "详细显示";
                }
                case "CATEGORY":{
                    return "分类显示";
                }
                case "BANNER":{
                    return "横幅显示";
                }
                case "LIST":{
                    return "列表显示";
                }
                case "SIMPLE":{
                    return "简单显示";
                }
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
                    <li><a href="#">内容管理</a></li>
                    <li class="active">频道栏目管理</li>
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
                    <h2>频道栏目管理</h2>
                </div>
                <div class="pull-right">

                    <!-- End .dropdown -->
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
            <!-- End .inner-padding -->
        </header>
        <!-- End #header-sec -->
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
                                <strong>频道栏目名称:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="channelName" data-operator="contains" class="form-control grid-filter" placeholder="请输入频道栏目名称"/>
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
                            <h3>频道栏目列表</h3>
                        </div>
                        <div class="pull-right">
                            <div class="btn-group">
                                <%--<a href="javascript:void(0);" onclick="testItem()" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 测试</a>--%>
                                <a href="toAddArticleChannel.do" class="btn btn-default"><i class="fa fa-plus"></i>&nbsp;新增频道</a>
                                <a href="toAddPrograma.do" class="btn btn-default"><i class="fa fa-plus"></i>&nbsp;新增栏目</a>
                            </div>
                        </div>
                    </header>
                </div>
                <div class="table-wrapper">
                    <%
                        HashMap<String, Object> data = new HashMap<String, Object>();
                        data.put("channelId", "#=channelId#");
                    %>
                    <kendo:grid name="grid" pageable="false" sortable="true" selectable="false" height="510" detailTemplate="template">
                        <kendo:grid-columns>
                            <kendo:grid-column title="频道名称" field="channelName" width="350px"/>
                            <kendo:grid-column title="频道编码" field="channelCode"/>
                            <kendo:grid-column title="频道关键字" field="channelKey"/>
                            <kendo:grid-column title="频道描述" field="description"/>
                            <kendo:grid-column title="频道位置" field="channelPosition" template="#=formatChannelPosition(channelPosition)#"/>
                            <kendo:grid-column title="显示风格" field="channelStyle" template="#=formatChannelStyle(channelStyle)#"/>
                            <kendo:grid-column title="是否启用" field="enabled" template="#= enabled ? '是' : '否' #"/>
                            <kendo:grid-column title="创建日期" field="createTime" format="{0:yyyy-MM-dd HH:mm}"/>
                            <kendo:grid-column title="&nbsp;" width="186px">
                                <kendo:grid-column-command>
                                    <kendo:grid-column-commandItem text="编辑" className="grid-button">
                                        <kendo:grid-column-commandItem-click>
                                            <script>
                                                function editItem(e) {
                                                    var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
                                                    window.location.href = "toEditArticleChannel.do?channelId=" + dataItem.channelId;
                                                }
                                            </script>
                                        </kendo:grid-column-commandItem-click>
                                    </kendo:grid-column-commandItem>
                                    <kendo:grid-column-commandItem text="删除" name="deleteChannel" className="grid-button">
                                        <kendo:grid-column-commandItem-click>
                                            <script>
                                                function deletePrimaryItem(e) {
                                                    var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
                                                    bootbox.confirm("确认删除吗？", function (result) {
                                                        if (result) {
                                                            $.get("removeArticleChannel.do", {
                                                                channelId: dataItem.channelId
                                                            }, function (data) {
                                                                if ("success"  == data) {
                                                                    $("#grid").data("kendoGrid").dataSource.read();
                                                                    commonNotify("删除成功！", "success");
                                                                } else {
                                                                    commonNotify("删除失败!当前频道下存在栏目或数据错误！", "error");
                                                                }
                                                            });
                                                        }
                                                    });
                                                }
                                            </script>
                                        </kendo:grid-column-commandItem-click>
                                    </kendo:grid-column-commandItem>
                                </kendo:grid-column-command>
                            </kendo:grid-column>
                        </kendo:grid-columns>
                        <kendo:grid-dataBound>
                            <script>
                                function onDataBound(arg) {
                                }
                            </script>
                        </kendo:grid-dataBound>
                        <kendo:dataSource serverPaging="false" serverFiltering="false" serverSorting="false">
                            <kendo:dataSource-schema>
                                <kendo:dataSource-schema-model>
                                    <kendo:dataSource-schema-model-fields>
                                        <kendo:dataSource-schema-model-field name="createTime" type="date"/>
                                    </kendo:dataSource-schema-model-fields>
                                </kendo:dataSource-schema-model>
                            </kendo:dataSource-schema>
                            <kendo:dataSource-transport>
                                <kendo:dataSource-transport-read url="getArticleChannels.do" type="POST" contentType="application/json"/>
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
                        <kendo:grid name="grid_#=channelId#" pageable="false" selectable="false" sortable="true" scrollable="false">
                            <kendo:grid-columns>
                                <kendo:grid-column title="栏目名称" field="programaName" width="340px"/>
                                <kendo:grid-column title="栏目编码" field="programaCode"/>
                                <kendo:grid-column title="描述" field="description"/>
                                <kendo:grid-column title="是否启用" field="enabled" template="#= enabled ? '是' : '否' #"/>
                                <%--<kendo:grid-column title="是否推荐" field="recommend" template="#= recommend ? '是' : '否' #"/>--%>
                                <kendo:grid-column title="文章数量" field="articleAmount"/>
                                <kendo:grid-column title="创建时间" field="createTime" width="140px"/>
                                <kendo:grid-column title="&nbsp;" width="186px">
                                    <kendo:grid-column-command>
                                        <kendo:grid-column-commandItem text="编辑" name="editPrograma" className="grid-button">
                                            <kendo:grid-column-commandItem-click>
                                                <script>
                                                    function editProgramaItem(e) {
                                                        var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
                                                        window.location.href = "toEditPrograma.do?programaId=" + dataItem.programaId;
                                                    }
                                                </script>
                                            </kendo:grid-column-commandItem-click>
                                        </kendo:grid-column-commandItem>
                                        <kendo:grid-column-commandItem text="删除" name="deletePrograma"  className="grid-button">
                                            <kendo:grid-column-commandItem-click>
                                                <script>
                                                    function deleteProgramaItem(e){
                                                        var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
                                                        var ds=this.dataSource;
                                                        console.log(this);
                                                        bootbox.confirm("确认删除吗？", function (result) {
                                                            if (result) {
                                                                $.get("removePrograma.do", {
                                                                    programaId: dataItem.programaId
                                                                }, function (data1) {
                                                                    if ("success" == data1) {
                                                                        ds.read();
                                                                        commonNotify("删除成功！", "success");
                                                                    } else {
                                                                        commonNotify("删除失败!", "error");
                                                                    }
                                                                });
                                                            }
                                                        });
                                                    }
                                                </script>
                                            </kendo:grid-column-commandItem-click>
                                        </kendo:grid-column-commandItem>
                                    </kendo:grid-column-command>
                                </kendo:grid-column>
                            </kendo:grid-columns>
                            <kendo:dataSource serverPaging="false" serverSorting="false">
                                <kendo:dataSource-transport>
                                    <kendo:dataSource-transport-read url="getProgramasByChannelId.do" data="<%=data %>" type="POST" contentType="application/json"/>
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
