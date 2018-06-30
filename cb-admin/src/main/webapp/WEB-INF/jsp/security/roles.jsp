<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>角色管理</title>
    <script type="text/javascript">
        function removeItem(roleId) {
            bootbox.confirm("确定删除吗？", function (result) {
                if (result) {
                    $.get("removeRoleById.do", {
                        roleId: roleId,
                        rad: Math.random()
                    }, function (data) {
                        if (data) {
                            commonNotify("删除成功！", "success");
                            $("#roleTr"+roleId).remove();
                        } else {
                            commonNotify("删除失败!", "error");
                        }
                    });
                }
            });
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
                    <li><a href="#">系统管理</a></li>
                    <li class="active"><a href="#">角色管理</a></li>
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
                    <h2>角色管理</h2>
                </div>
                <div class="pull-right">

                    <!-- End .btn-group -->

                    <!-- End .dropdown -->
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
                    <header>
                        <div class="pull-left">
                            <h3>角色列表</h3>
                        </div>
                        <div class="pull-right">
                                <a href="toAddRole.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
                        </div>
                    </header>
                </div>
                <div class="table-wrapper">
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th scope="col">
                                编码
                            </th>
                            <th scope="col">
                                名称
                            <th scope="col">
                                备注
                            </th>
                            <th scope="col" class="th-4-action-btn">
                                操作
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="role" items="${roles}">
                            <tr id="roleTr${role.roleId}">
                                <td>${role.roleCode}</td>
                                <td>${role.roleName}</td>
                                <td>${role.remark}</td>
                                <td>
                                    <a href="toEditRole.do?roleId=${role.roleId}" class="btn-less"><i class="fa fa-pencil"></i></a>
                                    <a href="javascript:void(0)" onclick="removeItem(${role.roleId})" class="btn-less"><i class="fa fa-trash-o"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

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
