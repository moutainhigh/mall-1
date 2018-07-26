<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>订单管理</title>
  <script type="text/javascript">
    $(document).ready(function(){


    });

    function viewItem() {
      var dataItem = getSelectedGridItem("grid");
      if (dataItem) {
        window.location.href = "getOrderDetailById.do?orderId=" + dataItem.orderId;
      }
    }

    function formatState(state){
      switch (state){
        case "PENDING_PAYMENT":{
          return "待付款";
        }
        case "PAID_PAYMENT":{
          return "待发货";
        }
        case "OUT_STOCK":{
          return "已发货";
        }
        case "RECEIVED":{
          return "已签收";
        }
        case "REFUSE":{
          return "拒签收";
        }
        case "RETURN_GOODS":{
          return "退货";
        }
        case "CHANGE_GOODS":{
          return "换货";
        }
        case "CANCELED":{
          return "已取消";
        }
        case "WAIT_EVALUATE":{
          return "待评价";
        }
        case "TIMEOUT":{
          return "超时";
        }
        case "SUCCESS":{
          return "交易成功";
        }
      }
    }

    function formatPayType(ptype){
      switch (ptype){
        case "ALIPAY":{
          return "支付宝";
        }
        case "TENPAY":{
          return "财付通";
        }
        case "UNIONPAY":{
          return "银联";
        }
        case "AFTERREVICED":{
          return "货到付款";
        }default :{
         return "未付款";
        }
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
      <div class="header-main-bottom">
        <div class="pull-left">
          <ul class="breadcrumb">
            <li><a href="#">首页</a></li>
            <li><a href="#">市场销售</a></li>
            <li><a href="#">订单管理</a></li>
            <li class="active"><a href="#">订单查询</a></li>
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
            <h2>订单管理</h2>
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
                  <strong>订单编码:</strong>
                </div>
                <div class="toolbar-field">
                  <input type="text" data-filter="orderCode" data-operator="contains" class="form-control grid-filter" placeholder="请输入订单编码"/>
                </div>
                <div class="toolbar-field">
                  <strong>订单状态:</strong>
                </div>
                <div class="toolbar-field">
                  <select class="form-control  grid-filter" data-filter="orderState" data-operator="eq">
                    <option value="">全部</option>
                    <option value="PENDING_PAYMENT">待付款</option>
                    <option value="PAID_PAYMENT">待发货</option>
                    <option value="OUT_STOCK">已发货</option>
                    <option value="RECEIVED">已签收</option>
                  </select>
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
          <!-- End .toolbar -->

          <div class="spacer-10"></div>

          <div class="toolbar responsive-helper">
            <header>
              <div class="pull-left">
                <h3>订单列表</h3>
              </div>
              <div class="pull-right">
                <div class="btn-group">
                  <a href="javascript:viewItem()" class="btn btn-default"><i class="fa fa-edit"></i>&nbsp;详情</a>
                  <a href="javascript:changePriceItem();"  class="btn btn-default"><i class="fa fa-edit"></i>&nbsp;调价</a>
                  <a href="javascript:initLogistic();"  class="btn btn-default"><i class="fa fa-edit"></i>&nbsp; 设置物流</a>
                </div>
              </div>
            </header>
          </div>
          <div class="table-wrapper">
            <kendo:grid name="grid" pageable="true" sortable="true" filterable="true" selectable="true" height="450" resizable="true">
              <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
              <kendo:grid-filterable extra="false">
                <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
                <kendo:grid-filterable-operators>
                  <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                  <kendo:grid-filterable-operators-date gte="小于" eq="等于" lte="大于"/>
                </kendo:grid-filterable-operators>
              </kendo:grid-filterable>
              <kendo:grid-columns>
                <kendo:grid-column title="订单编码" field="orderCode" width="150" template="<a href='getOrderDetailById.do?orderId=#=orderId#'>#=orderCode#</a>"/>
                <kendo:grid-column title="买家名称" field="customer.accountName" width="130"/>
                <%--<kendo:grid-column title="商家名称" field="seller.sellerName" width="130"/>--%>
                <kendo:grid-column title="创建时间" field="createTime" width="130" format="{0:yyyy-MM-dd HH:mm}"/>
                <kendo:grid-column title="订单金额" field="totalPrice" width="130"/>
                <kendo:grid-column title="运费金额" field="deliveryFeeTotal" width="130"/>
                <kendo:grid-column title="订单总额" field="feeTotal" width="130"/>
                <kendo:grid-column title="付款方式" field="paymentType" width="130" template="#=formatPayType(paymentType)#"/>
                <kendo:grid-column title="订单状态" field="orderState" width="130" template="#=formatState(orderState)#"/>
                <kendo:grid-column title="买家留言" field="buyerMessage" width="130" filterable="false"/>
                <kendo:grid-column title="备注" field="remark" width="150" filterable="false"/>
                <%--<kendo:grid-column title="&nbsp;" width="300px">--%>
                  <%--<kendo:grid-column-command>--%>
                    <%--<kendo:grid-column-commandItem name="orderTrace" className="grid-button">--%>
                      <%--<kendo:grid-column-commandItem-click>--%>
                        <%--<script>--%>
                          <%--function detail(e) {--%>
                            <%--var dataItem = this.dataItem($(e.currentTarget).closest("tr"));--%>
                            <%--var status = dataItem.orderState;--%>
                            <%--if(status == "PENDING_PAYMENT"){--%>
                              <%--$("#orderTrace").text("调价");--%>
                              <%--changePriceItem();--%>
                            <%--}else if(status=="PAID_PAYMENT") {--%>
                              <%--$("#orderTrace").text("设置物流");--%>
                              <%--initLogistic();--%>
                            <%--}--%>
                          <%--}--%>
                        <%--</script>--%>
                      <%--</kendo:grid-column-commandItem-click>--%>

                    <%--</kendo:grid-column-commandItem>--%>
                  <%--</kendo:grid-column-command>--%>
                <%--</kendo:grid-column>--%>
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
                  <kendo:dataSource-filterItem field="seller.sellerId" value="${seller.sellerType=='SELF_OPERATION'?null:seller.sellerId}" operator="eq"/>
                </kendo:dataSource-filter>
                <kendo:dataSource-transport>
                  <kendo:dataSource-transport-read url="pageOrders.do" type="POST" contentType="application/json"/>
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
  <div class="modal fade" id="changePriceDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">订单调价</h4>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-sm-5">
              <label>订单号：</label>
            </div>
            <div class="col-sm-7 col-label">
              <span style="color:#002a80" id="orderCodeSpan"></span>
            </div>
          </div>

          <div class="row">
            <div class="col-sm-5">
              <label>买家：</label>
            </div>
            <div class="col-sm-7 col-label">
              <span id="customerSpan"></span>
            </div>
          </div>

          <div class="row">
            <div class="col-sm-5">
              <label>订单金额：</label>
            </div>
            <div class="col-sm-7 col-label">
              <span id="totalPriceSpan"></span>
            </div>
          </div>

          <div class="row">
            <div class="col-sm-5">
              <label>调价订单金额：</label>
            </div>
            <div class="col-sm-7 col-label">
              <input type="hidden" id="changeTotalPriceHid" maxlength="12"/>
              <input type="text" id="changeTotalPriceValue" maxlength="12"/>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn btn-default" data-dismiss="modal">关闭</button>
          <button class="btn btn-primary pull-right" onclick="changeOrderPrice();" id="btnComfrim">确认</button>
        </div>
      </div>
    </div>
  </div>

  <div class="modal fade" id="logisticDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">物流设置</h4>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-sm-5">
              <label>物流公司：</label>
            </div>
            <div class="col-sm-7 col-label">
              <select id="logisticSel">

              </select>
            </div>
          </div>

          <div class="row">
            <div class="col-sm-5">
              <label>快递单号：</label>
            </div>
            <div class="col-sm-7 col-label">
              <input id="courierNumber" maxlength="16"/>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn btn-default" data-dismiss="modal">关闭</button>
          <button class="btn btn-primary pull-right" onclick="submitOrderLogistic();" id="btnLogisticComfrim">确认</button>
        </div>
      </div>
    </div>
  </div>
  <script type="text/javascript">

    function changePriceItem() {
      var dataItem = getSelectedGridItem("grid");
      if (dataItem) {
        $('#changePriceDialog').modal();
        var orderId = dataItem.orderId;
        $("#orderCodeSpan").html(dataItem.orderCode);
        $("#customerSpan").html(dataItem.customer.accountName);
        $("#totalPriceSpan").html(dataItem.totalPrice);
        $("#changeTotalPriceHid").val(orderId);
        $("#changeTotalPriceValue").val("");
      }
    }

    function changeOrderPrice(){
      var orderId=$("#changeTotalPriceHid").val();
      var newVal=$("#changeTotalPriceValue").val();
      if(newVal==null || newVal==undefined){
        alert("调节的价格不可为空");
      }else{
        $.post("changeOrderPrice.do",{orderId:orderId,changePrice:newVal},function(result){
          if(result){
            $("#btnComfrim").attr("disabled", true);
            window.location.href = "orders.do";
          }else{
            alert("调价失败！");
          }
        });
      }
    }

    function initLogistic(){
      $('#logisticDialog').modal();
      $.post("../logistic/findLogisticsByEnable.do",function(result){
        if(result!=null){
          $.each(result,function(){
            $("<option value='"+this.logisticId+"'>"+this.logisticName+"</option>").appendTo($("#logisticSel"));
          });
        }else{
          alert("获取失败！");
        }
      });
    }

    function submitOrderLogistic(){
      var dataItem = getSelectedGridItem("grid");
      if (dataItem){
        if(dataItem.orderState == "PAID_PAYMENT" ||dataItem.orderState =="CHANGE_GOODS"){
          var cNum = $("#courierNumber").val();
          if(null ==cNum || ""==cNum ||undefined ==cNum){
            alert("请填写物流单号");
            return ;
          }
          var lId = $("#logisticSel").find("option:selected").val();
          $.post("editOrderLogistic.do",{orderId:dataItem.orderId,logisticId:lId,courierNumber:cNum},function(result){
            if(result){
              $("#btnLogisticComfrim").attr("disabled", true);
              window.location.href = "orders.do";
            }else{
              alert("设置失败！");
            }
          });
        }
      }
    }
  </script>
</body>
</html>
