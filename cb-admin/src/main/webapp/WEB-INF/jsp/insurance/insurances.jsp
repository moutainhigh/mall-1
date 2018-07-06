<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>保单列表</title>
  <script type="application/javascript">

      $(document).ready(function () {
          $("#createTime").kendoDatePicker({
              format: "yyyy-MM-dd",
              culture:"zh-CN",
              parseFormats: ["yyyy-MM-dd"]
          });
      });
      function formatOrderState(orderState) {
          switch (orderState) {
              case "UN_PAID":
                  return "待支付";
              case "ON_PAID":
                  return "已支付";
              case "BEEN_COMPLETED":
                  return "已完成";
              case "UN_SURRENDER":
                  return "退保审核";
              case "ON_SURRENDER":
                  return "已退保";
          }
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
    <!-- End #header-main-bottom -->
  </header>
  <!-- End #header-main -->

  <div id="content" class="clearfix">


    <header id="header-sec">
      <div class="inner-padding">
        <div class="pull-left">
          <h2>保单</h2>
        </div>
        <div class="pull-right">

          <div class="btn-group">
            <a class="btn btn-default" href="#">
              <i class="fa fa-star"></i>
            </a>
            <a class="btn btn-default" href="#" id="modal-update-trigger">
              Modal
            </a>
            <a class="btn btn-default" href="#">
              <i class="fa fa-cog"></i>
            </a>
          </div>
          <!-- End .btn-group -->

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
        <div class="toolbar responsive-helper">
          <form style="width: 100%">
            <div class="pull-left">
              <div class="toolbar-field">
                <strong>保单编号:</strong>
              </div>
              <div class="toolbar-field">
                <input type="text" data-filter="orderCode" data-operator="contains" class="form-control grid-filter" placeholder="请输入保单编号"/>
              </div>
              <div class="toolbar-field">
                <strong>投保时间:</strong>
              </div>
              <div class="toolbar-field">
                <input name="createTime" id="createTime"  data-filter="createTime" data-operator="gte" class="grid-filter"/>
              </div>



              <div class="toolbar-field">
                <strong>保单状态:</strong>
              </div>
              <div class="toolbar-field">
                <select data-filter="orderState" data-operator="eq" class="form-control simpleselect grid-filter">
                  <option value="">全部</option>
                  <option value="UN_PAID">待支付</option>
                  <option value="ON_PAID">已支付</option>
                  <option value="BEEN_COMPLETED">已完成</option>
                  <option value="UN_SURRENDER">退保审核</option>
                  <option value="ON_SURRENDER">已退保</option>
                </select>
              </div>


            </div>
            <div class="pull-left">

              <div class="toolbar-field">
                <strong>合同编号:</strong>
              </div>
              <div class="toolbar-field">
                <input type="text" data-filter="contractNo" data-operator="contains" class="form-control grid-filter" placeholder="请输入合同编号"/>
              </div>

              <div class="toolbar-field">
                <strong>投保人:</strong>
              </div>
              <div class="toolbar-field">
                <input type="text" data-filter="insuranceOrderPolicyholder.policyholderName" data-operator="contains" class="form-control grid-filter" placeholder="请输入投保人"/>
              </div>

              <div class="toolbar-field">
                <strong>被保人:</strong>
              </div>
              <div class="toolbar-field">
                <input type="text" data-filter="insuranceOrderInsured.insuredName" data-operator="contains" class="form-control grid-filter" placeholder="请输入被保人"/>
              </div>

              <div class="toolbar-field">
                <strong>投保人手机号:</strong>
              </div>
              <div class="toolbar-field">
                <input type="text" data-filter="insuranceOrderPolicyholder.policyholderMobile" data-operator="contains" class="form-control grid-filter" placeholder="请输入投保人手机号"/>
              </div>

            </div>
            <!-- End .pull-left -->
            <div class="pull-right">
              <div class="toolbar-field">
                <button type="button" class="btn btn-default" onclick="reloadGridFilters('grid')"><i class="fa fa-search"></i>查询</button>
                &nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-default" onclick="clearFilters('grid')">清空</button>
              </div>
            </div>
            <!-- End .pull-right -->
          </form>
        </div>
        <div class="spacer-10"></div>

        <div class="toolbar responsive-helper">
          <header>
            <div class="pull-left">
              <h3>产品列表</h3>
            </div>

          </header>
        </div>
        <div class="table-wrapper">
          <kendo:grid name="grid" pageable="false" sortable="true" selectable="true" height="450"  filterable="true">
            <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
            <kendo:grid-filterable extra="false">
              <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
              <kendo:grid-filterable-operators>
                <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                <kendo:grid-filterable-operators-date gte="小于" eq="等于" lte="大于"/>
              </kendo:grid-filterable-operators>
            </kendo:grid-filterable>
            <kendo:grid-columns>
              <kendo:grid-column title="保单编号" field="orderCode" width="100"  template="<a href='insuranceOrderDetail.do?orderId=#= orderId#'>#= orderCode#</a>" filterable="true"/>
              <kendo:grid-column title="合同编号" field="contractNo" width="100" filterable="true"/>
              <kendo:grid-column title="投保人" field="insuranceOrderPolicyholder" template="#=insuranceOrderPolicyholder.policyholderName#" width="100"/>
              <kendo:grid-column title="投保人手机" field="insuranceOrderPolicyholder" template="#=insuranceOrderPolicyholder.policyholderMobile#" width="100"/>
              <kendo:grid-column title="保险险种" field="insuranceProduct" template="#=insuranceProduct.prodName#" width="100"/>
              <kendo:grid-column title="保险期间" field="insuranceProduct"  template="#=insuranceProduct.insurePeriod#" width="100"/>
              <kendo:grid-column title="缴费年限" field="insuranceProduct" template="#=insuranceProduct.protectionYear#" width="100"/>
              <kendo:grid-column title="基本保额" field="insuranceProductPrice" template="#=insuranceProductPrice.price#"  width="100"/>
              <kendo:grid-column title="保单状态" field="orderState" template="#=formatOrderState(orderState)#" width="100"/>
              <kendo:grid-column title="投保时间" field="createTime" format="{0:yyyy-MM-dd HH:mm}" width="100"/>
            </kendo:grid-columns>
            <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
              <kendo:dataSource-schema data="content" total="totalElements">
                <kendo:dataSource-schema-model>
                  <kendo:dataSource-schema-model-fields>
                    <kendo:dataSource-schema-model-field name="createTime" type="date"/>
                  </kendo:dataSource-schema-model-fields>
                </kendo:dataSource-schema-model>
              </kendo:dataSource-schema>
              <kendo:dataSource-transport>
                <kendo:dataSource-transport-read url="pageInsuranceOrder.do" type="POST" contentType="application/json"/>
                <kendo:dataSource-transport-parameterMap>
                  <script>
                    function parameterMap(options, type) {
                      return JSON.stringify(options);
                    }
                  </script>
                </kendo:dataSource-transport-parameterMap>
              </kendo:dataSource-transport>
            </kendo:dataSource>
          </kendo:grid>

        </div>
      </div>

      <div class="spacer-30"></div>
      <div class="hr-totop"><span>Top</span></div>
      <div class="spacer-10"></div>

      <!-- End .inner-padding -->
    </div>

    <jsp:include page="../layouts/footer.jsp"/>
    <!-- End #footer-main -->
  </div>
  <!-- End #content -->
</div>
<!-- End #main -->

<script type="text/javascript">
  $(document).ready(function () {
    $("#commodityAuditForm").validationEngine({
      autoHidePrompt: true, scroll: false, showOneMessage: true
    });
  });

</script>

</body>
</html>
