<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>修改栏目</title>
    <script type="application/javascript">
        $(document).ready(function () {
            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true,
                onValidationComplete: function (form, valid) {
                    if(valid){
                        var defaultPicPath = $('input[name="imgurl"]');
                        if (defaultPicPath.size()==0) {
                            bootbox.alert("请至少选择一张图片!");
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
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
                    <li><a href="#">内容管理</a></li>
                    <li><a href="#">频道栏目</a></li>
                    <li class="active">修改栏目</li>
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
                    <h2>修改栏目</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default " href="channelProgramas.do"><i class="fa fa-reply"></i></a>
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
                <form:form action="editPrograma.do" cssClass="form-horizontal" method="post" commandName="programa">
                    <form:hidden path="programaId"/>
                    <fieldset>
                        <legend>栏目表单</legend>
                        <div class="row"><div class="col-sm-2"></div><div class="col-sm-3"><form:errors path="programaName" cssClass="Validform_checktip"/></div></div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>栏目名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,minSize[2]]" path="programaName" maxlength="64"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>栏目编码：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,minSize[2],custom[onlyLetterNumber]]" data-errormessage-custom-error="只可输入英文或数字" path="programaCode" maxlength="32" />
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>频道：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:select cssClass="form-control" path="articleChannel.channelId" items="${articleChannels}" itemLabel="channelName" itemValue="channelId"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>排序：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,custom[integer],min[0]]" path="sortOrder" maxlength="4"/>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>模板名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <div class="input-group">
                                    <form:input id="templateName" readonly="true" cssClass="form-control" path="templateName" maxlength="32" data-errormessage="请选择模板"/>
                                    <span class="input-group-btn">
                                        <button id="templateNameBtn" class="btn btn-default" type="button">选择</button>
                                        <button id="templateCancelBtn" class="btn btn-default" type="button">取消</button>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>栏目描述：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:textarea cssClass="form-control validate[required,maxSize[512]]" path="description"></form:textarea>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>SEO关键字：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:input cssClass="form-control" path="seoKey" maxlength="128"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>SEO标题：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:input cssClass="form-control" path="seoTitle" maxlength="256"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>SEO描述：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:textarea cssClass="form-control validate[maxSize[512]]" path="seoDescription"></form:textarea>
                            </div>
                        </div>

                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>是否启用：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:checkbox path="enabled"/>
                            </div>
                            <div class="col-sm-2">
                                <label>是否推荐：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:checkbox path="recommend"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 宣传图片：</label>
                            </div>
                            <div class="col-sm-9">
                                    <%--图片上传控件--%>
                                <link href="../js/plugins/fileinput/fileinput.min.css" media="all" rel="stylesheet" type="text/css"/>
                                <script src="../js/plugins/fileinput/fileinput.min.js" type="text/javascript"></script>
                                <script src="../js/plugins/fileinput/zh.js" type="text/javascript"></script>
                                <script type="text/javascript">
                                    $(function(){
                                        var initPreview = new Array();//展示元素
                                        var initPreviewConfig = new Array();//展示设置
                                        //初始化图片上传组件
                                        $("#picUrl").fileinput({
                                            uploadUrl: "/admin/uploads/uploadFile/ARTICLE.do",
                                            showCaption: true,
                                            minImageWidth: 50,
                                            minImageHeight: 50,
                                            showUpload:true, //是否显示上传按钮
                                            showRemove :false, //显示移除按钮
                                            showPreview :true, //是否显示预览
                                            showCaption:false,//是否显示标题
                                            browseOnZoneClick: true,//是否显示点击选择文件
                                            language: "zh" ,
                                            showBrowse : false,
                                            maxFileSize : 2000,
                                            autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉
                                            overwriteInitial: false,//不覆盖已存在的图片
                                            browseClass:"btn btn-primary", //按钮样式
                                            // layoutTemplates:{
                                            //     actionUpload:''    //设置为空可去掉上传按钮
                                            // },
                                            maxFileCount: 10  //上传的个数
                                        }).on("fileuploaded", function (event, data) {
                                            var response = data.response;
                                            //添加url到隐藏域
                                            var html='<input name="imgurl" type="hidden" id="'+response.timeStr+'" value="'+response.url+','+response.fileName+','+response.timeStr+'">';
                                            $('#imgDiv').html($('#imgDiv').html()+html);
                                            //上传完成回调
                                            var index=0;
                                            if(initPreview.length>0 ){
                                                index=initPreview.length;
                                            }
                                            initPreview[index]  = response.url;
                                            var config = new Object();
                                            config.caption = "";
                                            config.url="/admin/uploads/delete/ARTICLE.do";
                                            config.key=response.timeStr;
                                            initPreviewConfig[index]=config;
                                            $("#picUrl").fileinput('refresh', {
                                                initialPreview: initPreview,
                                                initialPreviewConfig: initPreviewConfig,
                                                initialPreviewAsData: true
                                            });
                                            $(".btn-default").attr("disabled",false);
                                        }).on("filepredelete", function(jqXHR) {
                                            var abort = true;
                                            if (confirm("确定要删除吗？(删除后不会恢复)")) {
                                                abort = false;
                                            }
                                            return abort;
                                        }).on('filedeleted', function(event, id) {
                                            $("#"+id).remove();
                                            for (var i=0;i<initPreview.length;i++)
                                            {
                                                if(initPreview[i].indexOf(id) != -1){
                                                    initPreview.splice(i)
                                                    initPreviewConfig.splice(i)
                                                }
                                            }
                                        }).on('filebatchselected', function (event, files) {//选中文件事件
                                            $(".kv-file-upload").click();
                                        });
                                        //加载图片
                                        var a='${listAttachment}';
                                        var json=eval('(' + a + ')')
                                        for(var i=0,l=json.length;i<l;i++){
                                            initPreview[i]  = json[i].filePath;
                                            var config = new Object();
                                            config.caption = "";
                                            config.url="/admin/uploads/delete/ARTICLE.do";
                                            config.key=json[i].inputId;
                                            initPreviewConfig[i]=config;
                                            $("#picUrl").fileinput('refresh', {
                                                initialPreview: initPreview,
                                                initialPreviewConfig: initPreviewConfig,
                                                initialPreviewAsData: true
                                            });
                                            var html='<input name="imgurl" type="hidden" id="'+json[i].inputId+'" value="'+json[i].filePath+','+json[i].fileName+','+json[i].inputId+'">';
                                            $('#imgDiv').html($('#imgDiv').html()+html);
                                        }
                                    })
                                </script>
                                <input id="picUrl" name="file" type="file" class="file-loading" accept="image/*" multiple>
                                <div id="imgDiv">
                                </div>
                                    <%--图片上传控件结束--%>
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
        <!-- End #footer-main -->
    </div>
</div>


<div class="modal fade" id="templateNameDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">选择模板</h4>
            </div>
            <div class="modal-body">
                <div class="row" style="margin-top:-20px;margin-bottom:-20px">
                    <div class="widget">
                        <header>
                            <h2>模板目录</h2>
                        </header>
                        <div>
                            <div class="inner-padding" style="height: 300px;padding: 10px 5px 10px 5px">
                                <input type="hidden" id="currentFilePath"/>
                                <kendo:treeView name="treeview" dataTextField="name" select="onSelect" dataSpriteCssClassField="nodeType">
                                    <kendo:dataSource>
                                        <kendo:dataSource-transport>
                                            <kendo:dataSource-transport-read url="getTemplateNodesByPath.do" type="POST" contentType="application/json"/>
                                            <kendo:dataSource-transport-parameterMap>
                                                <script>
                                                    function parameterMap(options, type) {
                                                        return JSON.stringify(options);
                                                    }
                                                </script>
                                            </kendo:dataSource-transport-parameterMap>
                                        </kendo:dataSource-transport>
                                        <kendo:dataSource-schema>
                                            <kendo:dataSource-schema-hierarchical-model id="path" hasChildren="hasChildren"/>
                                        </kendo:dataSource-schema>
                                    </kendo:dataSource>
                                </kendo:treeView>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary pull-right" onclick="chooseCatalog();">确认</button>
            </div>
        </div>
        <script type="application/javascript">
            var catalogId = 0;
            var catalogName = "";
            var templateName="";
            $('#templateNameBtn').focusin(function (e) {
                $('#templateNameDialog').modal();
                e.preventDefault();
            });


            function onSelect(e) {
                var data = $('#treeview').data('kendoTreeView').dataItem(e.node);
                templateName = data.name;
            }

            function chooseCatalog() {
                if(templateName!=""){
                    if(templateName.lastIndexOf(".ftl")==-1){
                        alert("选择无效，请选择模板文件");
                        return ;
                    }
                    $('#templateNameDialog').modal("hide");
                    $("#templateName").val(templateName);
                }else{
                    alert("请选择模板");
                }
            }

            $("#templateCancelBtn").click(function(){
                templateName="";
                $("#templateName").val("");
            });
        </script>
    </div>
</div>

</body>
</html>
