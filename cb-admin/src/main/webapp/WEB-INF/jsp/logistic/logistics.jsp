<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>物流公司管理</title>
    <script type="application/javascript">
        function editItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "../logistic/toEditLogistic.do?logisticId=" + dataItem.logisticId;
            }
        }

        function itemPrice() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "logisticPrice.do?logisticId=" + dataItem.logisticId;
            }
        }

        function removeItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                bootbox.confirm("确认删除吗？", function (result) {
                    if (result) {
                        $.get("removeLogisticById.do", {
                            logisticId: dataItem.logisticId
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
                $.get("enableLogisticById.do", {
                    logisticId: dataItem.logisticId,
                    enabled: enabled,
                    rad: Math.random()
                }, function (data) {
                    if (data) {
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
                    <li><a href="#">配送管理</a></li>
                    <li><a href="#">物流查询</a></li>
                </ul>
                <!-- End .breadcrumb -->
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
                    <h2>物流公司管理</h2>
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
            <div class="inner-padding">
                <div class="toolbar responsive-helper">
                    <form>
                        <div class="pull-left">
                            <div class="toolbar-field">
                                <strong>物流公司编码:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="logisticCode" data-operator="contains" class="form-control grid-filter" placeholder="请输入物流公司编码"/>
                            </div>
                            <div class="toolbar-field">
                                <strong>物流公司名称:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="logisticName" data-operator="contains" class="form-control grid-filter" placeholder="请输入物流公司名称"/>
                            </div>
                            <div class="spacer-10"></div>
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
                            <h3>物流公司列表</h3>
                        </div>
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="javascript:itemPrice();" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;物流价格</a>
                                <a href="toAddLogistic.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
                                <a href="javascript:editItem();" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
                                <a href="javascript:removeItem();" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>
                            </div>
                            <div class="btn-group">
                                <a href="javascript:void(0);" onclick="enabledItem(true)" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;启用</a>
                                <a href="javascript:void(0);" onclick="enabledItem(false)" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 停用</a>
                            </div>
                        </div>
                    </header>
                </div>
                <div class="table-wrapper">
                    <kendo:grid name="grid" pageable="false" sortable="true" selectable="true" height="450">
                        <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
                        <kendo:grid-columns>
                            <kendo:grid-column title="物流公司名称" field="logisticName" width="150px"/>
                            <kendo:grid-column title="物流公司编码" field="logisticCode" width="100px"/>
                            <kendo:grid-column title="物流公司地址" field="logisticAddress" width="150px"/>
                            <kendo:grid-column title="物流公司网址" field="url" width="150px"/>
                            <kendo:grid-column title="联系人" field="linkman" width="100px"/>
                            <kendo:grid-column title="联系电话" field="telephone" width="100px"/>
                            <kendo:grid-column title="联系手机" field="mobile" width="100px"/>
                            <kendo:grid-column title="邮箱" field="email" width="100px"/>
                            <kendo:grid-column title="微信公众号" field="weChatNo" width="100px"/>
                            <kendo:grid-column title="QQ" field="qq" width="100px"/>
                            <kendo:grid-column title="启用" field="enabled" width="100" template="#= enabled ? '是' : '否' #"/>
                            <kendo:grid-column title="备注" field="remark" width="150px"/>
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
                                <kendo:dataSource-transport-read url="pageLogistics.do" type="POST" contentType="application/json"/>
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
