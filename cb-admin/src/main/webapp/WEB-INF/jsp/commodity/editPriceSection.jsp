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

  <title>编辑价格段</title>

  <script type="text/javascript">
    $(document).ready(function() {

        var errerMsg='${errerMsg}';
        if(errerMsg!=null&&errerMsg!=""){
            commonNotify(errerMsg,"error");
        }

      $("#validateSubmitForm").validationEngine({
        autoHidePrompt: true, scroll: false, showOneMessage: true
      });

    });
    function checkPrice(){
        if(''!=$('#endPrice').val()&&'0'!=$('#endPrice').val()&&parseInt($('#startPrice').val())>parseInt($('#endPrice').val())){
            bootbox.alert("最大价格不能小于最小价格!");
            $('#endPrice').val('');
        }
    }

  </script>
</head>
<body>


  <!-- ********************************************
       * SIDEBAR MAIN:                            *
       *                                          *
       * the part which contains the main         *
       * navigation, logo, search and more...     *
       * (parts can be in both sidebars).         *
       ******************************************** -->

  <jsp:include page="../layouts/left.jsp"/>
  <!-- End aside -->

  <!-- ********************************************
       * SIDEBAR SEC:                             *
       *                                          *
       * the part which contains things like      *
       * calendar, users, lists, blocks and       *
       * much more.                               *
       ******************************************** -->

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
            <li><a href="#">商品管理</a></li>
            <li><a href="#">价格段管理</a></li>
            <li class="active">编辑价格段</li>
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
            <h2>编辑价格段</h2>
          </div>
          <div class="pull-right">
            <a class="btn btn-default " href="priceSections.do"><i class="fa fa-reply"></i></a>
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
          <form:form id="validateSubmitForm" cssClass="form-horizontal" action="editPriceSection.do" method="post" commandName="priceSection" >

            <form:hidden path="sectionId"/>
            <!-- * data-asf-time = seconds, data-asf-expireafter = minutes * -->
            <fieldset>
              <legend>编辑价格段</legend>
              <div class="row">
                <div class="col-sm-2">
                  <label>起始价格：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3" style="position:relative;">
                  <form:input path="startPrice" onkeyup="value=value.replace(/[^\d]/g,'')" id="startPrice" onchange="checkPrice()" cssClass="form-control validate[required,custom[number]]" maxlength="11"/>
                  <span style="position: absolute;top:4px;right: -3px;">元</span>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <label>结束价格：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3" style="position:relative;">
                  <form:input path="endPrice" onkeyup="value=value.replace(/[^\d]/g,'')" id="endPrice" onchange="checkPrice()" cssClass="form-control validate[required,custom[number]]" maxlength="11"/>
                  <span style="position: absolute;top:4px;right: -3px;">元</span>
                </div>
              </div>
              <div class="spacer-10"></div>

              <div class="row">
                <div class="col-sm-2">
                  <label>价格段编码：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <div class="inline-labels">
                    <form:input path="sectionNo" readonly="true" cssClass="form-control validate[required,minSize[2]]" maxlength="32"/>
                  </div>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <label>是否启用：</label>
                </div>
                <div class="col-sm-3">
                  <div class="inline-labels">
                    <form:checkbox path="enabled"/>
                  </div>
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
                  <form:textarea cssClass="form-control" path="remark" maxlength="255"></form:textarea>
                </div>
                <div class="col-sm-1"></div>
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
