<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>新增角色</title>
    <script type="text/javascript">
        $(document).ready(function() {

            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true
            });

        });
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
        <!-- End #header-main-top -->
        <div class="header-main-bottom">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">系统管理</a></li>
                    <li><a href="#">角色管理</a></li>
                    <li class="active"><a href="#">新增角色</a></li>
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
                    <h2>新增角色</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default " href="roles.do"><i class="fa fa-reply"></i></a>
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
                <form:form id="roleForm" action="addRole.do" cssClass="form-horizontal" method="post" commandName="role">
                    <fieldset>
                        <legend>新增角色</legend>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>角色编码：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,minSize[2]]" path="roleCode" maxlength="64"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>角色名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,minSize[2]]" path="roleName" maxlength="32"/>
                                <form:errors path="roleName" cssClass="Validform_checktip"/>
                            </div>
                        </div>

                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>

                        <div class="row">
                            <div class="col-sm-2">

                            </div>
                            <div class="col-sm-8">
                                <input type="checkbox" id="chbAll" onchange="chbAllOnChange()" />全选/反选
                                <kendo:treeView name="treeview" check="onCheck">
                                    <kendo:treeView-checkboxes checkChildren="true"/>
                                    <kendo:dataSource data="${roleRescTree}">
                                    </kendo:dataSource>
                                </kendo:treeView>
                            </div>
                        </div>

                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label>备注</label>
                            </div>
                            <div class="col-sm-8">
                                <form:textarea class="form-control" path="remark" maxlength="255"></form:textarea>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="btn-group pull-right">
                                    <button id="saveBtn" class="btn btn-default" type="submit"><i class="fa fa-save"></i>&nbsp;保&nbsp;存&nbsp;</button>
                                    <button type="reset" class="btn btn-default"><i class="fa fa-reply"></i>&nbsp;重&nbsp;置&nbsp;</button>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </form:form>
                <div class="spacer-40"></div>
                <div class="hr-totop"><span>Top</span></div>
                <div class="spacer-40"></div>

            </div>
        </div>
        <jsp:include page="../layouts/footer.jsp"/>
    </div>
</div>
<script>

    function checkedNodeIds(nodes, checkedNodes) {
        for (var i = 0; i < nodes.length; i++) {
            if (nodes[i].checked) {
                var id = nodes[i].id;
                var pid=id.substr(0,1);
                if(checkedNodes.indexOf(pid) ==-1 && id!=pid){
                    checkedNodes.push(pid);
                    checkedNodes.push(id);
                }else{
                    checkedNodes.push(id);
                }
            }

            if (nodes[i].hasChildren) {
                checkedNodeIds(nodes[i].children.view(), checkedNodes);
            }
        }
    }

    function onCheck() {
        var checkedNodes = [],
                treeView = $("#treeview").data("kendoTreeView"),
                message;

        checkedNodeIds(treeView.dataSource.view(), checkedNodes);

        if (checkedNodes.length > 0) {
            $("#roleForm input[type='hidden']").remove();
            for(var i=0;i<checkedNodes.length;i++){
                var ids="<input type='hidden' name='rescCodes' value='"+checkedNodes[i]+"' />";
                $("#roleForm").append(ids);
            }
        } else {
            $("#roleForm input[type='hidden']").remove();
        }
    }

    function checkUncheckAllNodes(nodes, checked) {
        for (var i = 0; i < nodes.length; i++) {
            nodes[i].set("checked", checked);
            var ids="<input type='hidden' name='rescCodes' value='"+nodes[i].id+"' />";
            $("#roleForm").append(ids);
            if (nodes[i].hasChildren) {
                checkUncheckAllNodes(nodes[i].children.view(), checked);
            }
        }
    }

    function chbAllOnChange() {
        var checkedNodes = [];
        var treeView = $("#treeview").data("kendoTreeView");
        var isAllChecked = $('#chbAll').prop("checked");

        checkUncheckAllNodes(treeView.dataSource.view(), isAllChecked)

        if (!isAllChecked){
            $("#roleForm input[type='hidden']").remove();
        }
    }
</script>
</body>
</html>
