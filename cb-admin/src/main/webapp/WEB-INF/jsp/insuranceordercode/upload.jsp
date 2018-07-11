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

  <title>导入合同编号</title>

  <script type="text/javascript">
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
            <h2>导入合同编号</h2>
          </div>
          <div class="pull-right">
            <a class="btn btn-default " href="brands.do"><i class="fa fa-reply"></i></a>
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
        <div class="tab-content">
        <form id="uploadForm" action="uploadPayerCreditInfoExcel.do" enctype="multipart/form-data" method="post">
          <input id="upfile" type="file" name="upfile">
          <input type="submit" class="btn btn-default"  value="导入"  name="btn">
        </form>
        </div>
      </div>
    </div>
  </div>

</body>
</html>
