<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

  <title>新增厨电设备</title>

  <script type="text/javascript">
    $(document).ready(function() {

      $("#validateSubmitForm").validationEngine({
        autoHidePrompt: true, scroll: false, showOneMessage: true,
        onValidationComplete: function (form, valid) {
          if (valid) {

            if (null == $("#factoryDate").val() || "" == $("#factoryDate").val()) {
              bootbox.alert("请选择出厂日期!");
              return false;
            }
            return true;

          }
        }
      });

      $("#factoryDate").kendoDatePicker({
        format: "yyyy-MM-dd",
        value:new Date(),
        culture:"zh-CN",
        parseFormats: ["yyyy-MM-dd"]
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
            <li><a href="#">设备管理</a></li>
            <li><a href="#">厨电设备</a></li>
            <li class="active"><a href="#">新增厨电设备</a></li>
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
            <h2>新增厨电设备</h2>
          </div>
          <div class="pull-right">
            <a class="btn btn-default " href="devices.do"><i class="fa fa-reply"></i></a>
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
          <form:form id="validateSubmitForm" cssClass="form-horizontal" action="addDevice.do" method="post" commandName="device" >

            <fieldset>
              <legend>新增厨电设备</legend>
              <div class="row"><div class="col-sm-2"></div><div class="col-sm-3"><form:errors path="deviceCode" cssClass="Validform_checktip"/></div></div>
              <div class="spacer-10"></div>
              <div class="row">
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span>设备类型：</label>
                </div>
                <div class="col-sm-3">
                  <form:select class="form-control simpleselect" path="deviceType">
                    <form:options items="${deviceType}" itemLabel="name"/>
                  </form:select>
                </div>
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span>设备编码：</label>
                </div>
                <div class="col-sm-3">
                  <form:input type="text" cssClass="form-control validate[required]" path="deviceCode"  maxlength="64"/>
                </div>
              </div>
              <div class="spacer-10"></div>

              <div class="row">
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span>PAD设备：</label>
                </div>
                <div class="col-sm-3">
                  <form:select class="form-control simpleselect" path="concent.concentId">
                    <form:options items="${concents}" itemValue="concentId" itemLabel="concentName"/>
                  </form:select>
                </div>

                <div class="col-sm-2">
                  <label><span class="asterisk">*</span>生产日期：</label>
                </div>
                <div class="col-sm-3">
                  <form:input path="factoryDate"/>
                </div>
              </div>
              <div class="spacer-10"></div>
              <div class="row">
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span>设备状态：</label>
                </div>
                <div class="col-sm-3">
                  <form:select class="form-control simpleselect" path="deviceState">
                    <form:options items="${deviceState}" itemLabel="name"/>
                  </form:select>
                </div>
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span>版本号：</label>
                </div>
                <div class="col-sm-3">
                  <form:input type="text" cssClass="form-control validate[required,custom[integer]]" path="version" maxlength="4"/>
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
