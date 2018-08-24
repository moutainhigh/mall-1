<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>用户管理</title>
    <script type="text/javascript">
        function editItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "toEditUser.do?userId=" + dataItem.userId;
            }
        }

        function removeItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                if(dataItem.userId==1&&dataItem.userName=='admin'){
                    bootbox.alert("admin不能被删除!");
                    return false;
                }
                bootbox.confirm("确定删除吗？", function (result) {
                    if (result) {
                        $.get("removeUserById.do", {
                            userId: dataItem.userId,
                            rad: Math.random()
                        }, function (data) {
                            if (data) {
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

        function enabledItem(enabled) {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                $.get("enableUserById.do", {
                    userId: dataItem.userId,
                    enabled: enabled,
                    rad: Math.random()
                }, function (data) {
                    if (data != 0) {
                        commonNotify("操作成功！", "success");
                        $("#grid").data("kendoGrid").dataSource.read();
                    } else {
                        commonNotify("操作失败!", "error");
                    }
                });
            }
        }

        function formatRoles(roles) {
            console.log(roles)

            var roleNames="";
            $(roles).each(function(){
                roleNames+=this.roleName+",";
            });
            return roleNames.substring(0,roleNames.lastIndexOf(","));
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
                    <li><a href="#">系统管理</a></li>
                    <li class="active"><a href="#">用户管理</a></li>
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
                    <h2>用户管理</h2>
                </div>
                <div class="pull-right">

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
                                <strong>手机号码:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="mobile" data-operator="contains" class="form-control grid-filter" placeholder="请输入手机号码"/>
                            </div>
                            <div class="toolbar-field">
                                <strong>用户名称:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="userName" data-operator="contains" class="form-control grid-filter" placeholder="请输入用户名称"/>
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
                            <h3>用户列表</h3>
                        </div>
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="toAddUser.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
                                <a href="javascript:void(0);" onclick="editItem()" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
                                <a href="javascript:void(0);" onclick="removeItem()" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>
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
                            <kendo:grid-column title="用户名称" field="userName" width="80"/>
                            <kendo:grid-column title="真实姓名" field="realName" width="80"/>
                            <kendo:grid-column title="手机号码" field="mobile" width="80"/>
                            <kendo:grid-column title="邮箱" field="email" width="80"/>
                            <kendo:grid-column title="性别" field="sex" width="80" template="#= sex ? '男' : '女' #" filterable="false" />
                            <kendo:grid-column title="角色" field="roles" width="120" template="#=formatRoles(roles)#" filterable="false" />
                            <kendo:grid-column title="创建时间" field="createTime" width="130" format="{0:yyyy-MM-dd HH:mm}" filterable="false" />
                            <kendo:grid-column title="最后登录时间" field="lastTime" width="130" format="{0:yyyy-MM-dd HH:mm}" filterable="false" />
                            <kendo:grid-column title="状态" field="enabled" width="100px" template="#= enabled ? '启用' : '停用' #" filterable="false" />
                            <kendo:grid-column title="备注" field="remark" width="150" filterable="false"/>
                        </kendo:grid-columns>
                        <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                            <kendo:dataSource-schema data="content" total="totalElements">
                            </kendo:dataSource-schema>
                            <kendo:dataSource-transport>
                                <kendo:dataSource-transport-read url="pageUsers.do" type="POST" contentType="application/json"/>
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
