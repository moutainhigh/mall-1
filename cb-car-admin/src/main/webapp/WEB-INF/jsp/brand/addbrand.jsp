<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="ie ie6 lte9 lte8 lte7 no-js"> <![endif]-->
<!--[if IE 7]> <html class="ie ie7 lte9 lte8 lte7 no-js"> <![endif]-->
<!--[if IE 8]> <html class="ie ie8 lte9 lte8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie ie9 lte9 no-js"> <![endif]-->
<!--[if gt IE 9]> <html class="no-js"> <![endif]-->
<!--[if !IE]><!-->
<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>新增品牌</title>
</head>
<body>
<jsp:include page="../layouts/left.jsp"/>
<script type="text/javascript">
    function changeOtherMatter(flag) {
        if(flag){
            $("#sort").show();
        }else {
            $("#sort").hide();
        }
    }
    function submitForm() {
        var flag = $("input[name='otherMatter']:checked").val();
        if(flag=="false"){
            $("#hotSort").val(0);
        }
        return true;
    }
</script>
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
          <li><a href="#">品牌管理</a></li>
          <li class="active">新增品牌</li>
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
          <h2>新增品牌</h2>
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
        <form:form id="validateSubmitForm" cssClass="form-horizontal" action="addBrand.do" method="post" commandName="carBrand" onsubmit="return submitForm();">
          <fieldset>
            <legend>新增品牌</legend>
            <div class="row">
              <div class="inline-labels">
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span>选择国家 ：</label>
                </div>
                <div class="col-sm-3">
                  <form:select path="catalogId" cssClass="form-control validate[required]" onchange="changeCardType(this.value)">
                    <form:option value="1">中国</form:option>
                    <form:option value="2">日本</form:option>
                  </form:select>
                </div>
              </div>
              <div class="inline-labels">
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span>品牌名称 ：</label>
                </div>
                <div class="col-sm-3">
                  <form:input path="brandName" id="brandName" type="text" cssClass=" form-control validate[required,minSize[2]]]" maxlength="32" />
                </div>
              </div>
            </div>
            <div class="spacer-10"></div>
            <div class="row">
              <div class="inline-labels">
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span>品牌编码 ：</label>
                </div>
                <div class="col-sm-3">
                  <form:input path="brandNo" id="brandNo" readonly="true" type="text" cssClass=" form-control" maxlength="32" />
                </div>
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span>是否启用：</label>
                </div>
                <div class="col-sm-3">
                  <div class="inline-labels">
                    <form:radiobutton path="isDisplay" value="1"/>是
                    <form:radiobutton path="isDisplay" value="0"/>否
                  </div>
                </div>
              </div>
            </div>
            <div class="spacer-10"></div>
            <div class="row">
              <div class="inline-labels">
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span>是否热门：</label>
                </div>
                <div class="col-sm-3">
                  <div class="inline-labels">
                    <form:radiobutton path="isHot" value="1"/>是
                    <form:radiobutton path="isHot" value="0"/>否
                  </div>
                </div>
              </div>
            </div>
            <div class="spacer-10"></div>
            <div class="row">
              <div class="col-sm-2">
                <label>品牌描述：</label>
              </div>
              <div class="col-sm-9">
                <form:textarea cssClass=" form-control" path="remark" maxlength="512"></form:textarea>
              </div>
              <div class="col-sm-1"></div>
            </div>
            <div class="spacer-30"></div>
            <hr>
            <div class="spacer-30"></div>
            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span>图片:</label>
              </div>
              <div class="col-sm-9">
                  <%--图片上传控件--%>
                <link href="../js/plugins/fileinput/fileinput.min.css" media="all" rel="stylesheet" type="text/css"/>
                <script src="../js/plugins/fileinput/fileinput.min.js" type="text/javascript"></script>
                <script src="../js/plugins/fileinput/zh.js" type="text/javascript"></script>
                <script type="text/javascript">
                    $(function(){
                        $("#validateSubmitForm").validationEngine({
                            autoHidePrompt: true, scroll: false, showOneMessage: true,
                            onValidationComplete: function (form, valid) {
                                if (valid) {
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
                        var initPreview = new Array();//展示元素
                        var initPreviewConfig = new Array();//展示设置
                        //初始化图片上传组件
                        $("#picUrl").fileinput({
                            uploadUrl: "/car-admin/uploads/uploadFile/CARBRAND.do",
                            showCaption: true,
                            minImageWidth: 50,
                            minImageHeight: 50,
                            showUpload:true, //是否显示上传按钮
                            showRemove :false, //显示移除按钮
                            showPreview :true, //是否显示预览
                            showCaption:false,//是否显示标题
                            browseOnZoneClick: true,//是否显示点击选择文件
                            language: "zh" ,
                            showClose: false,
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
                            config.url="/admin/uploads/delete/CARBRAND.do";
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
            <div class="spacer-30"></div>
            <hr>
            <div class="spacer-30"></div>
            <div class="row">
              <div class="col-sm-12">
                <div class="btn-group pull-right">
                  <button id="saveBtn" class="btn btn-default" type="submit"><i class="fa fa-save"></i>&nbsp;保&nbsp;存&nbsp;</button>
                  <button onclick="clearInput('form-control')" type="button"  class="btn btn-default"><i class="fa fa-reply"></i>&nbsp;重&nbsp;置&nbsp;</button>
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