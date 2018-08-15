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

  <title>新增商家</title>
  <script type="application/javascript">
    $(document).ready(function(){
      $("#validateSubmitForm").validationEngine({
        autoHidePrompt: true, scroll: false, showOneMessage: true,
        onValidationComplete: function (form, valid) {
              if (valid) {
                  var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
                  if (!myreg.test($('#mobile').val())) {
                      bootbox.alert("手机格式不正确!");
                      return false;
                  } else {
                      return true;
                  }
              }
        }
      });
    });
    function returnSellers(){
      window.location.href = "sellers.do";
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
          <li><a href="#">商家管理</a></li>
          <li><a href="#">商家查询</a></li>
          <li class="active">新增商家</li>
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
          <h2>新增商家</h2>
        </div>
        <div class="pull-right">
          <a class="btn btn-default" href="sellers.do"><i class="fa fa-reply"></i></a>
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
        <form:form id="validateSubmitForm" cssClass="form-horizontal" action="addSeller.do" method="post"  commandName="seller">

          <fieldset>
            <legend>新增商家</legend>

            <div class="row"><div class="col-sm-2"></div><div class="col-sm-3"><form:errors path="sellerName" /></div></div>
            <div class="spacer-10"></div>
            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 商家名称：</label>
              </div>
              <div class="col-sm-3">
                <form:input  cssClass="form-control validate[required,minSize[2]]" path="sellerName" maxlength="32"/>
              </div>
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 商家编码：</label>
              </div>
              <div class="col-sm-3">
                <form:input cssClass="form-control validate[required,minSize[2]]" path="sellerCode" maxlength="32"/>
              </div>

            </div>

            <div class="spacer-10"></div>

            <div class="row">
              <div class="col-sm-2">
                <label> 商家地址：</label>
              </div>
              <div class="col-sm-3">
                <form:input cssClass="form-control" path="sellerAddress" maxlength="255"/>
              </div>
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 商家类型：</label>
              </div>
              <div class="col-sm-3">
                <form:select path="sellerType" cssClass="form-control simpleselect">
                  <form:options items="${sellerType}" itemLabel="name"/>
                </form:select>
              </div>
            </div>

            <div class="spacer-10"></div>

            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 联系人：</label>
              </div>
              <div class="col-sm-3">
                <form:input cssClass="form-control validate[required]" path="linkman" maxlength="32"/>
              </div>
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 手机：</label>
              </div>
              <div class="col-sm-3">
                <form:input cssClass="form-control validate[required,custom[phone]]" path="mobile" id="mobile" maxlength="11"/>
              </div>
            </div>

            <div class="spacer-10"></div>

            <div class="row">
              <div class="col-sm-2">
                <label> 联系电话：</label>
              </div>
              <div class="col-sm-3">
                <form:input cssClass="form-control" path="telephone" maxlength="16"/>
              </div>
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 邮箱：</label>
              </div>
              <div class="col-sm-3">
                <form:input cssClass="form-control validate[required,custom[email]]" path="email" maxlength="32"/>
              </div>
            </div>

            <div class="spacer-30"></div>
            <hr>
            <div class="spacer-30"></div>

            <div class="row">
              <div class="col-sm-2">
                <label> QQ：</label>
              </div>
              <div class="col-sm-3">
                <form:input cssClass="form-control validate[custom[number]]" path="qq" maxlength="16"/>
              </div>
              <div class="col-sm-2">
                <label> 商家微信：</label>
              </div>
              <div class="col-sm-3">
                <form:input cssClass="form-control" path="wechat" maxlength="32"/>
              </div>
            </div>

            <div class="spacer-10"></div>

            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 商家支付平台类型：</label>
              </div>
              <div class="col-sm-3">
                <form:select path="channelType" cssClass="form-control simpleselect">
                  <form:options items="${channelType}" itemLabel="name"/>
                </form:select>
              </div>
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 商家支付平台号：</label>
              </div>
              <div class="col-sm-3">
                <form:input cssClass="form-control validate[required]" path="channelAccount" maxlength="32"/>
              </div>
            </div>

            <div class="spacer-10"></div>

            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 营业执照名称：</label>
              </div>
              <div class="col-sm-3">
                <form:input cssClass="form-control validate[required]" path="busName" maxlength="128"/>
              </div>
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 营业执照注册号：</label>
              </div>
              <div class="col-sm-3">
                <form:input cssClass="form-control validate[required]" path="buslicenseNo" maxlength="128"/>
              </div>
            </div>

            <div class="spacer-30"></div>
            <hr>
            <div class="spacer-30"></div>

            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 银行户口：</label>
              </div>
              <div class="col-sm-3">
                <form:input cssClass="form-control validate[required]" path="accountName" maxlength="128"/>
              </div>
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 对公户名：</label>
              </div>
              <div class="col-sm-3">
                <form:input cssClass="form-control validate[required]" path="publicAccount" maxlength="128"/>
              </div>
            </div>

            <div class="spacer-10"></div>

            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 开户银行：</label>
              </div>
              <div class="col-sm-3">
                <form:input cssClass="form-control validate[required]" path="bankAccount" maxlength="64"/>
              </div>
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 开户银行地址：</label>
              </div>
              <div class="col-sm-3">
                <form:input cssClass="form-control validate[required]" path="bankAccountAddress" maxlength="255"/>
              </div>
            </div>

            <div class="spacer-10"></div>

            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 身份证号：</label>
              </div>
              <div class="col-sm-3">
                <form:input cssClass="form-control validate[required,custom[onlyLetterNumber]]" path="idCardNum" maxlength="18"/>
              </div>
            </div>


            <div class="spacer-30"></div>
            <hr>
            <div class="spacer-30"></div>

            <div class="row">
              <div class="col-sm-2">
                <label> 备注：</label>
              </div>
              <div class="col-sm-8">
                <form:textarea cssClass="form-control" path="remark" maxlength="255"/>
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
      <!-- End .inner-padding -->
    </div>
    <!-- End .window -->


    <jsp:include page="../layouts/footer.jsp"/>
    <!-- End #footer-main -->
  </div>
  <!-- End #content -->
</div>
<!-- End #main -->

</body>
</html>
