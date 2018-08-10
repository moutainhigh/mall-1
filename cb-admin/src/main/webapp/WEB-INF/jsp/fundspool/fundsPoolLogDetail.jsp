<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="ie ie6 lte9 lte8 lte7 no-js"> <![endif]-->
<!--[if IE 7]> <html class="ie ie7 lte9 lte8 lte7 no-js"> <![endif]-->
<!--[if IE 8]> <html class="ie ie8 lte9 lte8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie ie9 lte9 no-js"> <![endif]-->
<!--[if gt IE 9]> <html class="no-js"> <![endif]-->
<!--[if !IE]><!-->
<html class="no-js">                       <!--<![endif]-->
<head>
  <script src="../js/zoomify/viewer.min.js"></script>
  <script src="../js/zoomify/viewer-jquery.min.js"></script>
  <link rel="stylesheet" href="../js/zoomify/viewer.min.css">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>客户详情</title>
  <script type="text/javascript">
    $(document).ready(function() {
      <%--var p = $.citySelector.getProvince("${customer.province}");--%>
      <%--var c = $.citySelector.getCity("${customer.city}");--%>
      <%--var d = $.citySelector.getDistrict("${customer.district}");--%>
        var province=$.citySelector.getAddress("${customer.province}")
        var city=$.citySelector.getAddress("${customer.city}")
        var district=$.citySelector.getAddress("${customer.district}")
        var address='${customer.address}';
      $("#pdcDetail").html(province+city+district+address);
      $("#region").html(province+city+district);
      $("#region1").html(province+city+district);
        for (var i=1;i<4;i++){
            $('#example'+i).viewer({
                url: 'data-original',
            });
        }
    });


    function formatSex(sex) {
        switch (sex) {
            case true:
                return "男";
            case false:
                return "女";
        }
    }
  </script>
</head>
<body>



  <jsp:include page="../layouts/left.jsp"/>
  <!-- End aside -->


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
            <li><a href="#">资金池管理</a></li>
            <li><a href="#">资金池明细</a></li>
            <li class="active">资金池明细详情</li>
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
            <h2>资金池明细详情</h2>
          </div>
          <div class="pull-right">
            <a class="btn btn-default" href="fundsPoolLogs.do"><i class="fa fa-reply"></i></a>
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
          <ul class="ext-tabs">
            <li class="active">
              <a href="#content-tab-6-a">详情信息</a>
            </li>

          </ul><!-- End .ext-tabs -->
          <div class="tab-content">
            <div id="content-tab-6-a" class="tab-pane active">
              <div class="inner-padding" style="margin-left: -20px">
                  <!-- * data-asf-time = seconds, data-asf-expireafter = minutes * -->
                  <fieldset>
                    <legend>详情</legend>
                    <div class="row">
                      <div class="inline-labels">
                        <div class="col-sm-2">
                          <label>一级分类名称：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${fundsPoolLog.catalog.catalogName}
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-2">
                          <label>资金池名称：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${fundsPoolLog.poolName}
                        </div>
                        <div class="col-sm-1"></div>
                      </div>
                    </div>
                    <div class="spacer-10"></div>

                    <div class="row">
                      <div class="inline-labels">

                        <div class="col-sm-2">
                          <label>金额：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${fundsPoolLog.funds}
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-2">
                          <label>类型：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                            <c:forEach var="tempMap" items="${fundsPoolLogTypeMap}">
                              <c:if test="${tempMap.key == fundsPoolLog.type}">${tempMap.value}</c:if>
                            </c:forEach>
                        </div>
                        <div class="col-sm-1"></div>
                      </div>
                    </div>
                    <div class="spacer-10"></div>

                    <div class="row">
                      <div class="inline-labels">
                        <div class="col-sm-2">
                          <label>交易ID：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${fundsPoolLog.transactionId}
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-2">
                          <label>商品名称：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${fundsPoolLog.product.commodity.commodityName}
                        </div>
                        <div class="col-sm-1"></div>
                      </div>
                    </div>

                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="inline-labels">
                        <div class="col-sm-2">
                          <label>操作金额：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${fundsPoolLog.amount}
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-2">
                          <label>操作时间：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${fn:substring(fundsPoolLog.createTime, 0, 19)}
                        </div>
                        <div class="col-sm-1"></div>
                      </div>
                    </div>

                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="inline-labels">
                        <div class="col-sm-2">
                          <label>版本号：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${fundsPoolLog.version}
                        </div>
                        <div class="col-sm-1"></div>
                      </div>
                    </div>
                  </fieldset>

              </div>
            </div>




          </div><!-- End .tab-content -->
          <div class="spacer-40"></div>
          <div class="hr-totop"><span>Top</span></div>
          <div class="spacer-40"></div>

        </div>
        <!-- End .inner-padding -->
      </div>
      <!-- End .window -->

      <!-- ********************************************
           * FOOTER MAIN:                             *
           *                                          *
           * the part which contains things like      *
           * chat, buttons, copyright and             *
           * dropup menu(s).                          *
           ******************************************** -->

      <jsp:include page="../layouts/footer.jsp"/>
      <!-- End #footer-main -->
    </div>
    <!-- End #content -->
  </div>
  <!-- End #main -->


</body>
</html>
