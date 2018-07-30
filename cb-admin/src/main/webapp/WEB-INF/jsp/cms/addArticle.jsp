<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>新增文章</title>
    <script charset="utf-8" src="../editor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="../editor/lang/zh_CN.js"></script>
    <script type="application/javascript">
        $(document).ready(function () {
            $("#publishDate").kendoDatePicker({
                format: "yyyy-MM-dd",
                value:new Date(),
                min: new Date(),
                culture:"zh-CN",
                parseFormats: ["yyyy-MM-dd"]
            });

            $("#orderTime").kendoDateTimePicker({
                format: "yyyy-MM-dd HH:mm:ss",
                value:kendo.toString(new Date($("#publishDate").val()), "yyyy-MM-dd HH:mm:ss"),
                min: kendo.toString(new Date($("#publishDate").val()), "yyyy-MM-dd HH:mm:ss"),
                culture:"zh-CN",
                parseFormats: ["yyyy-MM-dd", "HH:mm:ss"]
            });

            KindEditor.ready(function(K) {
                window.editor = K.create('#editorContent',{
                    uploadJson: '../upload/fileUpload.do',
                    fileManagerJson: '../upload/fileManager.do',
                    allowFileManager : true,
                    afterCreate : function() {
                        this.sync();
                    },
                    afterBlur:function(){
                        this.sync();
                    }
                });
            });

            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true,
                onValidationComplete: function (form, valid) {
                    if(valid){
                        if (null == $("#titleColor").val() || "" == $("#titleColor").val()) {
                            bootbox.alert("请选择标题颜色!");
                            return false;
                        }
                        if (null == $("#publishDate").val() || "" == $("#publishDate").val()) {
                            bootbox.alert("请选择发布日期!");
                            return false;
                        }
                        if (null == $("#orderTime").val() || "" == $("#orderTime").val()) {
                            bootbox.alert("请选择排序时间!");
                            return false;
                        }
                        if (null == $("#editorContent").val() || "" == $("#editorContent").val()) {
                            bootbox.alert("请填写文章正文!");
                            return false;
                        }
                        var defaultPicPath = $('input[name="imgurl"]');
                        if (defaultPicPath.size()==0) {
                            bootbox.alert("请至少选择一张图片!");
                            return false;
                        } else {
                            return true;
                        }
                        return true;
                    }
                }
            });

            onProgramaSelectChange();

            $("#programaSelect").change(function(){
                onProgramaSelectChange();
            });

            $("#stepRow").hide();
            $("#stepEnabled").click(function(){
                if($(this).is(":checked")){
                    $("#stepRow").show();
                }else{
                    $("#stepRow").hide();
                }
            });
        });

        function onProgramaSelectChange(){
            $.getJSON("getSpecialSubjectsByProgramaId.do", {
                programaId: $("#programaSelect").val()
            }, function (json) {
                $("#specialSubjectSelect").html("");//清空专题选项

                if(0 == json){
                    $("#specialSubjectSelect").hide();
                }else{
                    for(var i=0;i<json.length;i++){
                        //添加一个专题
                        $("#specialSubjectSelect").append("<option value='"+json[i].subjectId+"'>"+json[i].subjectName+"</option>");
                    }
                    $("#specialSubjectSelect").show();
                }

            });
        }

    </script>
    <script type="application/javascript">
        /**
         *上传图片
         */
        function onchangeImg(imgId){
            debugger;
            var formData = new FormData();
            formData.append("file", $('#upload'+imgId)[0].files[0]);
            $.ajax({
                url: "/admin/uploads/upload/INSURANCEPRODUCT.do",
                type: 'POST',
                cache: false,
                data: formData,
                processData: false,
                contentType: false,
                success: function (result) {
                    $('#imagePath'+imgId).val(result.url);
                },
                error: function (err) {
                }
            });
        }

        //建立一個可存取到該file的url
        function getObjectURL(file) {
            var url = null;
            if (window.createObjectURL != undefined) { // basic
                url = window.createObjectURL(file);
            } else if (window.URL != undefined) { // mozilla(firefox)
                url = window.URL.createObjectURL(file);
            } else if (window.webkitURL != undefined) { // webkit or chrome
                url = window.webkitURL.createObjectURL(file);
            }
            return url;
        }

        function addImage(indexa){
            $("#upload"+indexa).click(); //隐藏了input:file样式后，点击头像就可以本地上传
            $("#upload"+indexa).on("change", function () {
                var objUrl = getObjectURL(this.files[0]); //获取图片的路径，该路径不是图片在本地的路径
                if (objUrl) {
                    $("#img"+indexa).attr("src", objUrl); //将图片路径存入src中，显示出图片
                }
            });
        }

        var idIndex = 0;
        function chooseStepImage() {
            var nRow=$("<tr></tr>").attr("id","imageTr"+idIndex);
            var img=$("<img id='img"+idIndex+"'    onclick='addImage("+idIndex+")' src='' width='80px' height='80px'>" +
                "<input id='upload"+idIndex+"' onchange='onchangeImg("+idIndex+")' name='file' multiple='multiple' accept='image/*' type='file' style='display: none'/>");
            var ntd0=$("<td style='width:100px;'></td>").append("<input id='imagePath"+idIndex+"'  type='hidden' name='stepPicPath' />")
                .append(img);
            nRow.append(ntd0);
            var ntd1=$("<td></td>").append("<input type='text' name='stepName' class='form-control validate[required]' maxLength='64'/>");
            nRow.append(ntd1);
            var ntd2=$("<td></td>").append("<input type='text' name='stepDescription' class='form-control validate[required]' maxLength='255'/>");
            nRow.append(ntd2);
            var ntd3=$("<td></td>").append("<input type='text' name='stepRemark' class='form-control'  maxLength='255'/>");
            nRow.append(ntd3);
            var ntd4=$("<td></td>").append("<input type='text' name='stepWhen' style='width:40px;' class='form-control validate[required,custom[number]]' maxLength='4'/>");
            nRow.append(ntd4);
            var ntd5=$("<td></td>").append("<input type='text' name='stepOrder' value='"+idIndex+"' style='width:40px;' class='form-control validate[required,custom[number]]' maxLength='4'/>");
            nRow.append(ntd5);
            var ntd6=$("<td></td>").append("<a type='button' title='删除' class='btn btn-default' href='javascript:removeImage(" + idIndex + ")'><i class='fa fa-minus-circle'></i></a>");
            nRow.append(ntd6);

            $("#imageStepTbody").append(nRow);
            idIndex++;
        }

        function removeImage(indx) {
            $("#imageTr" + indx).remove();
            idIndex--;
        }
        function applyStepInfo(){
            var iHtml="";
            $("#imageStepTable tbody tr").each(function(){
                var tdArr = $(this).children();

                var img = tdArr.eq(0).find("img").attr("src");
                var stepName = tdArr.eq(1).find("input").val();
                var stepDescription = tdArr.eq(2).find("input").val();
                var stepRemark = tdArr.eq(3).find("input").val();
                var stepWhen = tdArr.eq(4).find("input").val();
                var stepOrder = tdArr.eq(5).find("input").val();

                iHtml+="<label>步骤名称：</label><b>"+stepName+"</b><br>";
                iHtml+="<label>步骤描述：</label><b>"+stepDescription+"</b><br>";
                iHtml+="<label>步骤备注：</label><b>"+stepRemark+"</b><br>";
                iHtml+="<label>步骤时长：</label><b>"+stepWhen+"</b><br>";
                iHtml+="<label>步骤排序：</label><b>"+stepOrder+"</b><br>";
                iHtml+="<img src='"+img+"'><br>";
            });
            window.editor.insertHtml(iHtml);
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
                        <li><a href="../console/dashboard.do">首页</a></li>
                        <li><a href="#">内容管理</a></li>
                        <li><a href="articles.do">文章管理</a></li>
                        <li class="active">新增文章</li>
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
                        <h2>新增文章</h2>
                    </div>
                    <div class="pull-right">
                        <a class="btn btn-default" href="articles.do">
                            <i class="fa fa-reply"></i>
                        </a>
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
                    <form:form id="validateSubmitForm" action="addArticle.do"  cssClass="form-horizontal" method="post" commandName="article">
                        <form:hidden path="articleId"/>
                        <fieldset>
                            <legend>文章表单</legend>
                            <div class="row"><div class="col-sm-2"></div><div class="col-sm-3"><form:errors path="articleTitle" cssClass="Validform_checktip"/></div></div>
                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span>标题：</label>
                                </div>
                                <div class="col-sm-3">
                                    <form:input cssClass="form-control validate[required,minSize[2]]" path="articleTitle" maxlength="512"/>
                                </div>
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span>文章编码：</label>
                                </div>
                                <div class="col-sm-3">
                                    <form:input cssClass="form-control validate[required,minSize[2],custom[onlyLetterNumber]]" data-errormessage-custom-error="只可输入英文或数字" path="articleCode" maxlength="32"/>
                                </div>
                            </div>
                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span>所属栏目：</label>
                                </div>
                                <div class="col-sm-3">
                                    <form:select id="programaSelect" cssClass="form-control" path="programa.programaId" >
                                        <c:forEach var="channel" items="${articleChannels}">
                                            <optgroup label="${channel.channelName}">
                                                <c:forEach var="programa" items="${programas}">
                                                    <c:if test="${programa.articleChannel.channelId==channel.channelId}">
                                                        <option value="${programa.programaId}">${programa.programaName}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </optgroup>
                                        </c:forEach>
                                    </form:select>
                                </div>
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span>所属专题：</label>
                                </div>
                                <div class="col-sm-3">
                                    <form:select id="specialSubjectSelect" cssClass="form-control" path="specialSubject.subjectId" >
                                    </form:select>
                                </div>
                            </div>
                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span>简短标题：</label>
                                </div>
                                <div class="col-sm-3">
                                    <form:input cssClass="form-control validate[required,minSize[2]]" path="shortTitle" maxlength="128"/>
                                </div>
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span>标题颜色：</label>
                                </div>
                                <div class="col-sm-3">
                                    <kendo:colorPicker id="titleColor" name="titleColor" buttons="false"></kendo:colorPicker>
                                    <%--<form:input cssClass="form-control validate[required]" path="titleColor" cssStyle="width:180px" maxlength="16"/>--%>
                                </div>
                            </div>

                            <div class="spacer-10"></div>
                            <hr>
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
                                                allowedFileExtensions: ["jpg", "png", "gif"],
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
                                        })
                                    </script>
                                    <input id="picUrl" name="file" type="file" class="file-loading" accept="image/*" multiple>
                                    <div id="imgDiv">

                                    </div>
                                        <%--图片上传控件结束--%>
                                </div>
                            </div>
                            <div class="spacer-10"></div>
                            <hr>
                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span>摘要：</label>
                                </div>
                                <div class="col-sm-8">
                                    <form:input cssClass="form-control validate[required]" path="summary" maxlength="512"/>
                                </div>
                            </div>
                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span>作者：</label>
                                </div>
                                <div class="col-sm-3">
                                    <form:input cssClass="form-control validate[required]" path="author" maxlength="64"/>
                                </div>
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span>来源：</label>
                                </div>
                                <div class="col-sm-3">
                                    <form:input cssClass="form-control validate[required]" path="origin" maxlength="64"/>
                                </div>
                            </div>
                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span>发布日期：</label>
                                </div>
                                <div class="col-sm-3">
                                    <form:input path="publishDate"/>
                                </div>
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span>排序时间：</label>
                                </div>
                                <div class="col-sm-3">
                                    <form:input path="orderTime"/>
                                </div>
                            </div>

                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-sm-2">
                                    <label>备注：</label>
                                </div>
                                <div class="col-sm-8">
                                    <form:textarea cssClass="form-control validate[maxSize[255]]" path="remark" ></form:textarea>
                                </div>
                            </div>

                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span> 是否启用：</label>
                                </div>
                                <div class="col-sm-3">
                                    <form:checkbox path="enabled"/>
                                </div>
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span> 启用步骤：</label>
                                </div>
                                <div class="col-sm-3">
                                    <form:checkbox path="stepEnabled" id="stepEnabled"/>
                                </div>
                            </div>
                            <div class="spacer-30"></div>
                            <hr>
                            <div class="spacer-30"></div>
                            <div class="row" id="stepRow">

                                <div class="col-sm-12">
                                    <div class="col-sm-2">
                                        <label>菜谱步骤：</label>
                                    </div>
                                    <a onclick="chooseStepImage()" class="btn btn-default">添加步骤</a>
                                    <a onclick="applyStepInfo()" class="btn btn-default">应用到内容</a>
                                    <div class="spacer-10"></div>
                                    <table id="imageStepTable" class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th class="text-center" width="100px">图片预览</th>
                                            <th class="text-center">步骤名称</th>
                                            <th class="text-center">步骤描述</th>
                                            <th class="text-center">备注</th>
                                            <th class="text-center" width="80px">步骤时长</th>
                                            <th class="text-center" width="60px">排序</th>
                                            <th class="text-center" width="60px">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="imageStepTbody">
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <div class="spacer-30"></div>
                            <hr>
                            <div class="spacer-30"></div>
                            <div class="inner-padding">
                                <div class="spacer-10"></div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <form:textarea cssClass="form-control" id="editorContent" path="content" cssStyle="height:500px;"></form:textarea>
                                    </div>
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


</body>
</html>
