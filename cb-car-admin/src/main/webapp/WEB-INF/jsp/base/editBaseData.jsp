<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>新增商品分类</title>
    <jsp:include page="../layouts/left.jsp"/>
    <script type="application/javascript">

        var dataMsg='${dataMsg}';
        if(dataMsg!=null&&dataMsg!=""){
            var msgType='${msgType}';
            commonNotify(dataMsg,msgType==null||msgType==''?"error":msgType);
        }

        $("#validateSubmitForm").validationEngine({
            autoHidePrompt: true, scroll: false, showOneMessage: true
        });

    </script>
</head>
<body>

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
                    <li><a href="#">商品分类</a></li>
                    <li class="active">新增商品分类</li>
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
                    <h2>新增商品分类</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="carBaseData.do"><i class="fa fa-reply"></i></a>
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
                <form:form id="validateSubmitForm" action="editBaseData.do" cssClass="form-horizontal" method="post" commandName="baseData">
                    <fieldset>
                        <legend>新增商品分类</legend>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>上级名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:hidden id="id" path="id"/>
                                <form:hidden id="parentBaseDataId" path="parentBaseDataId"/>
                                <div class="input-group">
                                    <form:input id="parentBaseDataName" readonly="true" cssClass="form-control validate[required]" path="parentBaseData.baseDataName" maxlength="32" data-errormessage="请选择上级分类"/>
                                    <span class="input-group-btn">
                                        <button id="selectTree" class="btn btn-default" type="button">选择</button>
                                    </span>
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,minSize[1],maxSize[20]]" path="baseDataName" maxlength="20"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>编号 ：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required],min[0]" path="baseDataCode" maxlength="20"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>显示顺序 ：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required],min[0]" path="sortOrder" maxlength="4"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>是否启用：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:checkbox path="enabled" value="1" cssStyle="margin-top: 8px"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>分类描述 ：</label>
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

<div class="modal fade" id="dataDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">选择商品分类</h4>
            </div>
            <div class="modal-body">
                <div class="row" style="margin-top:-20px;margin-bottom:-20px">
                    <div class="sidebar-module">
                        <kendo:treeView name="treeview" select="onSelect">
                            <kendo:dataSource data="${dataTree}">
                            </kendo:dataSource>
                        </kendo:treeView>
                    </div>
                </div>
            </div>
            <div class="alert alert-warning" id="modalMsg" style="display: none;">
                <strong>提示：</strong>商品分类最多可新建三级！
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i>&nbsp;关闭</button>
                <button class="btn btn-primary pull-right" onclick="confirm();"><i class="fa fa-check"></i>&nbsp;确认</button>
            </div>
        </div>
        <script type="application/javascript">
            var dataId = 0;
            var dataName = "";
            var treeLevel = "";
            $('#selectTree').click(function (e) {
                $('#dataDialog').modal();
                $('#modalMsg').hide();
                e.preventDefault();
            });

            function onSelect(e) {
                var data = $('#treeview').data('kendoTreeView').dataItem(e.node);
                dataId = data.id;
                dataName = data.text;
                treeLevel = data.treeLevel;
            }

            function confirm() {
                if(treeLevel >= 3){
                    $('#modalMsg').show();
                    return;
                }
                $('#modalMsg').hide();
                $('#dataDialog').modal("hide");
                $("#parentBaseDataId").val(dataId);
                $("#parentBaseDataName").val(dataName);
                if(dataId !=1){
                    $('#hidd').hide();
                }else{
                    $('#hidd').show();
                }
            }
        </script>
    </div>
</div>
</body>
</html>
