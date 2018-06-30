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
                    uploadJson : '../editor/jsp/upload_json.jsp',
                    fileManagerJson : '../editor/jsp/file_manager_json.jsp',
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
                        if (null == $("#imagePath").val() || "" == $("#imagePath").val()) {
                            bootbox.alert("请选择宣传图片!");
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
                                <div class="col-sm-3">
                                    <a id="chooseImageBtn" class="btn btn-default">选择</a>
                                    <form:hidden path="picPath" id="imagePath"/>
                                </div>
                            </div>
                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-sm-2">
                                    <label>图片预览：</label>
                                </div>
                                <div class="col-sm-8">
                                    <img id="previewImagePath" src="" style="max-width:600px ">
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
                                    <a id="chooseStepImageBtn" class="btn btn-default">添加步骤</a>
                                    <a id="applyStepInfoBtn" class="btn btn-default">应用到内容</a>
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

<div class="modal fade" id="imageDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 1100px;height: 600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">选择图片</h4>
            </div>
            <div class="modal-body">
                <iframe id="imageFrame" src="../media/chooseMedias.do" style="width: 100%;height: 500px;border: none"></iframe>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary pull-right" onclick="chooseImage();">确认</button>
            </div>
        </div>
        <script type="application/javascript">
            $('#chooseImageBtn').click(function (e) {
                $('#imageDialog').modal().css({
                    width: 'auto'
                });
                e.preventDefault();
            });

            function chooseImage() {
                var imagePath = $("#imageFrame")[0].contentWindow.getSelectedPath();
                if (imagePath != null) {
                    $("#previewImagePath").attr("src", ".."+PIC_PATH + imagePath);
                    $("#imagePath").val(imagePath);
                    $('#imageDialog').modal("hide");
                } else {
                    alert("请选择图片");
                }
            }
        </script>
    </div>
</div>

<div class="modal fade" id="imageStepDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 1100px;height: 600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">选择图片</h4>
            </div>
            <div class="modal-body">
                <iframe id="imageStepFrame" src="../media/chooseMedias.do" style="width: 100%;height: 500px;border: none"></iframe>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary pull-right" onclick="chooseStepImage();">确认</button>
            </div>
        </div>
        <script type="application/javascript">

            var idIndex = 0;

            $('#chooseStepImageBtn').click(function (e) {
                $('#imageStepDialog').modal().css({
                    width: 'auto'
                });
                ;
                e.preventDefault();
            });


            function chooseStepImage() {
                var imagePath = $("#imageStepFrame")[0].contentWindow.getSelectedPath();
                if (imagePath != null) {
                    var nRow=$("<tr></tr>").attr("id","imageTr"+idIndex);
                    var img=$("<img src='../data/pics/" + imagePath + "' style='height:50px;width:80px;'/>");
                    var ntd0=$("<td style='width:100px;'></td>").append("<input type='hidden' name='stepPicPath' value='" + imagePath + "'/>")
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
                    $('#imageStepDialog').modal("hide");
                } else {
                    alert("请选择图片");
                }
            }

            function removeImage(indx) {
                $("#imageTr" + indx).remove();
                idIndex--;
            }

            $('#applyStepInfoBtn').click(function (e) {
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
            });
        </script>
    </div>
</div>

</body>
</html>
