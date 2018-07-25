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

  <title>贷款申请管理</title>
  <script type="text/javascript">
    $(document).ready(function(){


    });

    function viewItem() {
      var dataItem = getSelectedGridItem("grid");
      if (dataItem) {
        window.location.href = "getOrderDetailById.do?orderId=" + dataItem.order.orderId;
      }
    }

    function formatLoanState(bstate){
      switch(bstate){
        case "WAIT_LOAN":{
          return "申请中";
        }
        case "APPLY_SUCCESS":{
          return "申请通过";
        }
        case "APPLY_FAILURE":{
          return "申请失败";
        }
        case "CANCELED":{
          return "已取消";
        }

      }
    }

    function formatAuditState(state){
      switch(state){
        case "WAIT_AUDIT":{
          return "待审核";
        }
        case "AUDITED":{
          return "审核通过";
        }
        case "NOT_AUDIT":{
          return "审核未通过";
        }
      }
    }


  </script>
</head>
<body>

<jsp:include page="../layouts/left.jsp"/>
<!-- End aside -->
<jsp:include page="../layouts/sidebarRight.jsp"/>
<!-- End #sidebar-sec -->

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
          <li><a href="#">市场销售</a></li>
          <li><a href="#">贷款服务</a></li>
          <li class="active"><a href="#">贷款申请管理</a></li>
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
          <h2>贷款申请管理</h2>
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
          <form>
            <div class="pull-left">
              <div class="toolbar-field">
                <strong>贷款编码:</strong>
              </div>
              <div class="toolbar-field">
                <input type="text"  data-filter="loanCode" data-operator="contains" class="form-control grid-filter" placeholder="请输入贷款编码"/>
              </div>

            </div>
            <!-- End .pull-left -->
            <div class="pull-right">
              <div class="toolbar-field">
                <button type="button" class="btn btn-default" onclick="reloadGridFilters('grid')"><i class="fa fa-search"></i>查询</button>
                <button type="button" class="btn btn-default" onclick="clearFilters('grid')">清空</button>
              </div>
            </div>
            <!-- End .pull-right -->
          </form>
        </div>
        <!-- End .toolbar -->

        <div class="spacer-10"></div>

        <div class="toolbar responsive-helper">
          <header>
            <div class="pull-left">
              <h3>贷款申请列表</h3>
            </div>
            <div class="pull-right">
              <div class="dropdown">
                <div class="btn-group">
                  <a href="javascript:viewItem()" class="btn btn-default"><i class="fa fa-edit"></i>&nbsp;详情</a>
                  <a href="javascript:void(0);" onclick="auditItem()" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;审核</a>
                </div>
              </div>
            </div>
          </header>
        </div>
        <div class="table-wrapper">
          <kendo:grid name="grid" pageable="true" sortable="true" filterable="true" selectable="true" height="500" resizable="true">
            <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
            <kendo:grid-filterable extra="false">
              <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
              <kendo:grid-filterable-operators>
                <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                <kendo:grid-filterable-operators-date gte="小于" eq="等于" lte="大于"/>
              </kendo:grid-filterable-operators>
            </kendo:grid-filterable>
            <kendo:grid-columns>
              <kendo:grid-column title="订单编码" field="order.orderCode" width="150" template="<a href='getOrderDetailById.do?orderId=#=order.orderId#'>#=order.orderCode#</a>"/>
              <kendo:grid-column title="贷款编码" field="loanCode" width="130"/>
              <kendo:grid-column title="买家名称" field="customer.accountName" width="130"/>
              <kendo:grid-column title="购买时间" field="order.createTime" width="150" format="{0:yyyy-MM-dd HH:mm}"/>
              <kendo:grid-column title="申请时间" field="createTime" width="150" format="{0:yyyy-MM-dd HH:mm}"/>
              <kendo:grid-column title="贷款金额" field="loanPrice" width="130"/>
              <kendo:grid-column title="贷款状态" field="loanState" width="130" template="#=formatLoanState(loanState)#"/>
              <kendo:grid-column title="审核状态" field="auditState" width="130" template="#=formatAuditState(auditState)#"/>
              <kendo:grid-column title="审核备注" field="auditRemark" width="150" filterable="false"/>

            </kendo:grid-columns>
            <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
              <kendo:dataSource-schema data="content" total="totalElements">
                <kendo:dataSource-schema-model>
                  <kendo:dataSource-schema-model-fields>
                    <kendo:dataSource-schema-model-field name="createTime" type="date" />
                  </kendo:dataSource-schema-model-fields>
                </kendo:dataSource-schema-model>
              </kendo:dataSource-schema>
              <kendo:dataSource-filter>
                <kendo:dataSource-filterItem field="order.seller.sellerId" value="${seller.sellerType=='SELF_OPERATION'?null:seller.sellerId}" operator="eq"/>
              </kendo:dataSource-filter>
              <kendo:dataSource-transport>
                <kendo:dataSource-transport-read url="pageLoanOrders.do" type="POST" contentType="application/json"/>
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

      <div class="spacer-40"></div>
      <div class="hr-totop"><span>Top</span></div>
      <div class="spacer-40"></div>

      <!-- End .inner-padding -->
    </div>
    <!-- End .window -->

    <jsp:include page="../layouts/footer.jsp"/>
    <!-- End #footer-main -->
  </div>
  <!-- End #content -->
</div>
<!-- End #main -->
<div class="modal fade" id="auditDialog" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">贷款审核</h4>
      </div>
      <div class="modal-body">
        <form id="auditForm">
          <div class="row">
            <div class="col-sm-4">
              <label>贷款编码：</label>
            </div>
            <div class="col-sm-8">
              <input type="hidden" id="loanIdHid" name="loanId">
              <span style="color:#073980" id="loanCodeSpan"></span>
            </div>
          </div>
          <div class="spacer-10"></div>
          <div class="row">
            <div class="col-sm-4">
              <label>审核：</label>
            </div>
            <div class="col-sm-8">
              <input type="radio" name="auditState" value="AUDITED"/>通过
              <input type="radio" name="auditState" value="NOT_AUDIT" checked/>不通过
            </div>
          </div>
          <div class="spacer-10"></div>
          <div class="row">
            <div class="col-sm-4">
              <label>备注：</label>
            </div>
            <div class="col-sm-8">
              <textarea id="auditRemark" name="auditRemark" rows="8" cols="50" class='form-control validate[maxSize[255]]'></textarea>
            </div>
          </div>

        </form>
        <div class="clear"></div>

      </div>
      <div class="modal-footer">
        <button class="btn btn-default" data-dismiss="modal">关闭</button>
        <button class="btn btn-primary pull-right" onclick="submitAudit();" id="btnComfrim">确认</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">

  function auditItem() {
    var dataItem = getSelectedGridItem("grid");
    if (dataItem) {
      if(dataItem.auditState == "AUDITED"){
        alert("已通过审核，请勿重复提交");
        return ;
      }
      $('#auditDialog').modal();
      $("#loanIdHid").val(dataItem.loanId);
      $("#loanCodeSpan").html(dataItem.loanCode);
    }
  }

  function submitAudit(){
    if($("input[name='auditState']:checked").val()=="NOT_AUDIT" && $("#auditRemark").val() == ""){
      bootbox.alert("请填写审核不通过原因!");
      return false;
    }
    $.get("orderLoanApplyAudit.do",$("#auditForm").serialize(),function(result){
      if(result){
        $("#auditForm")[0].reset();
        $('#auditDialog').modal("hide");
        $("#grid").data("kendoGrid").dataSource.read();
      }else{
        alert("审核失败！");
      }
    });
  }
</script>
</body>
</html>
