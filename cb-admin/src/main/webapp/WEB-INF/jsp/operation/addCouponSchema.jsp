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

  <title>新增优惠券方案</title>

  <script type="text/javascript">
    $(document).ready(function() {

      $("#validateSubmitForm").validate({
        rules : {
          schemeNo:{
            required : true,
            minlength : 2,
            maxlength : 32
          },
          schemeName : {
            required : true,
            minlength : 2,
            maxlength : 128
          },
          remark : {
            maxlength : 255
          }
        },
        onkeyup:false,
        onfocusout: function(e) {
          $(e).valid();
        }
      });

    });


  </script>
</head>
<body>


  <jsp:include page="../layouts/left.jsp"/>
  <jsp:include page="../layouts/sidebarRight.jsp"/>
  <div id="main" class="clearfix">

    <!-- ********************************************
         * MAIN HEADER:                             *
         *                                          *
         * the part which contains the breadcrumbs, *
         * dropdown menus, toggle sidebar button    *
         ******************************************** -->

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
            <li><a href="#">活动推广</a></li>
            <li><a href="#">优惠券管理</a></li>
            <li><a href="#">优惠券方案管理</a></li>
            <li class="active"><a href="#">新增优惠券方案</a></li>
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

      <!-- ********************************************
           * HEADER SEC:                              *
           *                                          *
           * the part which contains the page title,  *
           * buttons and dropdowns.                   *
           ******************************************** -->

      <header id="header-sec">
        <div class="inner-padding">
          <div class="pull-left">
            <h2>新增优惠券方案</h2>
          </div>
          <div class="pull-right">
            <a class="btn btn-default " href="couponSchemas.do"><i class="fa fa-reply"></i></a>
          </div>
        </div>
        <!-- End .inner-padding -->
      </header>
      <!-- End #header-sec -->

      <!-- ********************************************
           * WINDOW:                                  *
           *                                          *
           * the part which contains the main content *
           ******************************************** -->

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
          <form:form id="validateSubmitForm" data-asf-expireafter="1" data-asf-time="10" action="addCouponSchema.do" method="post" commandName="couponSchema" >

            <!-- * data-asf-time = seconds, data-asf-expireafter = minutes * -->
            <fieldset>
              <legend>新增优惠券方案</legend>

              <div class="row">
                <div class="col-sm-2">
                  <label>方案编码：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:input path="schemeNo" type="text" class="form-control" maxlength="32"/>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <label>方案名称：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:input type="text" class="form-control" required="true" path="schemeName" aria-required="true" maxlength="128"/>
                </div>
                <div class="col-sm-1"></div>
              </div>
              <div class="spacer-10"></div>

              <div class="row">
                <div class="col-sm-2">
                  <label>发放方式：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:select class="form-control simpleselect" path="distributionMethod">
                    <form:options items="${distributionMethod}" itemLabel="name"/>
                  </form:select>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <label>面值：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:input type="text" class="form-control" required="true" path="faceValue" aria-required="true" maxlength="12"/>
                </div>
                <div class="col-sm-1"></div>
              </div>
              <div class="spacer-10"></div>
              <div class="row">
                <div class="col-sm-2">
                  <label>发放数量：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:input path="distributionNum" type="text" class="form-control" maxlength="32"/>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <label>最低消费：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:input type="text" class="form-control" path="lowestConsume" maxlength="128"/>
                </div>
                <div class="col-sm-1"></div>
              </div>

              <div class="spacer-30"></div>
              <hr>
              <div class="spacer-30"></div>

              <div class="row">
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span> 开始时间：</label>
                </div>
                <div class="col-sm-3">
                  <kendo:dateTimePicker id="startTime" name="startTime" format="yyyy-MM-dd HH:mm:ss" parseFormats="['yyyy-MM-dd','HH:mm:ss']" culture="zh-CN"></kendo:dateTimePicker>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span> 结束时间：</label>
                </div>
                <div class="col-sm-3">
                  <kendo:dateTimePicker id="endTime" name="endTime" format="yyyy-MM-dd HH:mm:ss" parseFormats="['yyyy-MM-dd','HH:mm:ss']" culture="zh-CN"></kendo:dateTimePicker>
                </div>
                <div class="col-sm-1"></div>
              </div>

              <div class="spacer-10"></div>
              <div class="row">
                <div class="col-sm-2">
                  <label>限定商品：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:checkbox path="limitCommodity" cssClass="checkbox-master"/>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <label>是否启用：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:checkbox path="enabled" cssClass="checkbox-master"/>
                </div>
                <div class="col-sm-1"></div>
              </div>

              <div class="spacer-30"></div>
              <hr>
              <div class="spacer-30"></div>

              <div class="row">
                <div class="col-sm-2">
                  <label>备注</label>
                </div>
                <div class="col-sm-9">
                  <form:textarea class="form-control" path="remark" aria-required="true" maxlength="255"></form:textarea>
                </div>
                <div class="col-sm-1"></div>
              </div>

              <div class="spacer-30"></div>
              <hr>
              <div class="spacer-30"></div>
              <div class="row">
                <div class="col-sm-3"></div>
                <div class="col-sm-9">

                  <button class="btn btn-default pull-right" type="reset">返回</button>
                  <button class="btn btn-primary pull-right" type="submit">提交</button>
                </div>
              </div>
            </fieldset>
          </form:form>
          <div class="spacer-40"></div>
          <div class="hr-totop"><span>Top</span></div>
          <div class="spacer-10"></div>

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
