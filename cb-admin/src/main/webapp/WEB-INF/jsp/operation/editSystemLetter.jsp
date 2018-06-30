<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

  <title>修改站内信</title>
  <script type="application/javascript">
    $(document).ready(function(){
      onPublishTypeSelectChange();

      $("#validateSubmitForm").validationEngine({
        autoHidePrompt: true, scroll: false, showOneMessage: true,
        onValidationComplete: function (form, valid) {
          if(valid){
            if( ("SPECFIC_CUSTOMER" == $("#publishTypeSelect").val())&&(null == $("#recipientInput").val() || "" == $("#recipientInput").val())){
              bootbox.alert("请选择收件人!");
              return false;
            }
            return true;
          }
        }
      });

      $("#publishTypeSelect").change(function(){
        onPublishTypeSelectChange();
      });
    })

    function onPublishTypeSelectChange(){
      var publishType = $("#publishTypeSelect").val();
      switch(publishType){
        // 所有用户
        case "ALL_CUSTOMER":{
          $("#recipientDiv").attr("hidden",true);
          $("#recipientInput").attr("value","");
          break;
        }
        // 指定用户
        case "SPECFIC_CUSTOMER":{
          $("#recipientDiv").attr("hidden",false);
          break;
        }
      }

    }

    function returnSystemLetters(){
      window.location.href = "systemLetters.do";
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
          <li><a href="#">运营管理</a></li>
          <li><a href="#">站内信管理</a></li>
          <li class="active">修改站内信</li>
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
          <h2>修改站内信</h2>
        </div>
        <div class="pull-right">
          <a class="btn btn-default " href="systemLetters.do"><i class="fa fa-reply"></i></a>
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
        <form:form id="validateSubmitForm" cssClass="form-horizontal"  action="editSystemLetter.do" method="post"  commandName="systemLetter">
          <form:hidden path="letterId"/>
          <fieldset>
            <legend>修改站内信</legend>

            <div class="row">
              <div class="col-sm-2">
                <label>发布类型：</label>
              </div>
              <div class="col-sm-2">
                <form:select id="publishTypeSelect" path="publishType" cssClass="form-control simpleselect" >
                  <form:options items="${publishType}" itemLabel="typeName"></form:options>
                </form:select>
              </div>
            </div>
            <div class="spacer-10"></div>
            <div id="recipientDiv" class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span>&nbsp;收件人：</label>
              </div>
              <div  class="col-sm-4">
                <form:input cssClass="form-control" path="recipient" id="recipientInput"/>
              </div>
              <div  class="col-sm-2">
                <button type="button" onclick="showCustomerDialog();" title="选择收件人" class="btn btn-default">
                  <i class="fa fa-plus-circle"></i>选择收件人
                </button>
              </div>
            </div>
            <div class="spacer-10"></div>
            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span>&nbsp;标题：</label>
              </div>
              <div class="col-sm-4">
                <form:input cssClass="form-control validate[required]"  path="title" maxlength="128"/>
              </div>
            </div>
            <div class="spacer-10"></div>
            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span>&nbsp;内容：</label>
              </div>
              <div class="col-sm-6" >
                <form:textarea cssClass="form-control validate[required]" rows="8" path="content" maxlength="3000"></form:textarea>
              </div>
            </div>
            <div class="spacer-30"></div>
            <hr>
            <div class="spacer-30"></div>
            <div class="row">
              <div class="col-sm-12">
                <button class="btn btn-default pull-right" type="reset" onclick="returnSystemLetters()">返回</button>
                <button class="btn btn-primary pull-right" type="submit">保存</button>
              </div>
            </div>
          </fieldset>
        </form:form>
        <div class="spacer-30"></div>
        <div class="hr-totop"><span>Top</span></div>
        <div class="spacer-30"></div>

      </div>
      <!-- End .inner-padding -->
    </div>
    <!-- End .window -->

    <jsp:include page="../layouts/footer.jsp"/>
    <!-- End #footer-main -->
  </div>
  <!-- End #content -->
</div>
<!-- End #main -->

<div class="modal fade" id="customerDialog" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" style="width: 1000px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">选择用户</h4>
      </div>
      <div class="modal-body">
        <form id="customerFormId">
          <jsp:include page="../customer/chooseCustomers.jsp"/>
        </form>
      </div>
      <div class="modal-footer">
        <button class="btn btn-default" data-dismiss="modal">关闭</button>
        <button class="btn btn-primary pull-right" onclick="chooseCustomer();">确认</button>
      </div>
    </div>
  </div>
  <script type="application/javascript">
    function showCustomerDialog() {
      $('#customerDialog').modal();
    }

    function chooseCustomer() {
      var recipientStr = '';
      $("#recipientInput").attr("value","");
      $("input[name='selectedCustomerId']:checked").each(function() {
        var accountName=$(this).attr("customName");
        if(0 == recipientStr.length ){
          recipientStr += accountName ;
        }else{
          recipientStr +=',' + accountName ;
        }
      });
      $("#recipientInput").attr("value",recipientStr);
      $('#customerDialog').modal("hide");
    }
  </script>
</div>

</body>
</html>
