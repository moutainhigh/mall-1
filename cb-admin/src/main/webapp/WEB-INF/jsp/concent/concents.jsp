<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>PAD设备</title>
  <script type="application/javascript">

    function formatPadState(padState) {
      switch (padState) {
        case "WAIT_AUDITED":
          return "待审核";
        case "AUDITED":
          return "审核通过";
        case "NOT_AUDITED":
          return "审核未通过";
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
          <li><a href="#">设备管理</a></li>
          <li><a href="#">PAD设备</a></li>
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
          <h2>PAD设备</h2>
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

        <div class="spacer-10"></div>

        <div class="toolbar responsive-helper">
          <header>
            <div class="pull-left">
              <h3>PAD设备列表</h3>
            </div>
            <div class="pull-right">
              <div class="btn-group">
                <a href="javascript:void(0);" onclick="commodityAuditItem()" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;审核</a>
              </div>

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
              <kendo:grid-column title="名称" field="concentName" width="100" filterable="true"/>
              <kendo:grid-column title="编码" field="concentCode" width="100" filterable="true"/>
              <kendo:grid-column title="审核状态" field="padState" width="100" filterable="true" template="#=formatPadState(padState)#"/>
              <kendo:grid-column title="用户账户名" field="customer.accountName" width="100" filterable="true"/>
              <kendo:grid-column title="用户真实姓名" field="customer.realName" width="100" filterable="true"/>
              <kendo:grid-column title="备注" field="remark" width="100"/>
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
                <kendo:dataSource-transport-read url="pageConcents.do" type="POST" contentType="application/json"/>
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

<div class="modal fade" id="commodityAuditDialog" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">PAD审核</h4>
      </div>
      <div class="modal-body">
        <form id="commodityAuditForm">
          <div class="row">
            <div class="col-sm-4">
              <label>PAD名称：</label>
            </div>
            <div class="col-sm-8">
              <input type="hidden" id="commodityIdHid" name="concentId">
              <span style="color:#073980" id="commodityNameSpan"></span>
            </div>
          </div>
          <div class="spacer-10"></div>
          <div class="row">
            <div class="col-sm-4">
              <label>审核：</label>
            </div>
            <div class="col-sm-8">
              <input type="radio" name="padState" value="AUDITED"/>通过
              <input type="radio" name="padState" value="NOT_AUDITED" checked/>不通过
            </div>
          </div>
          <div class="spacer-10"></div>
          <div class="row">
            <div class="col-sm-4">
              <label>备注：</label>
            </div>
            <div class="col-sm-8">
              <textarea id="remark" name="remark" rows="8" cols="50" class='form-control validate[maxSize[255]]'></textarea>
            </div>
          </div>

        </form>
        <div class="clear"></div>

      </div>
      <div class="modal-footer">
        <button class="btn btn-default" data-dismiss="modal">关闭</button>
        <button class="btn btn-primary pull-right" onclick="submitCommodityAudit();" id="btnComfrim">确认</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
  $(document).ready(function () {
    $("#commodityAuditForm").validationEngine({
      autoHidePrompt: true, scroll: false, showOneMessage: true
    });
  });

  function commodityAuditItem() {
    var dataItem = getSelectedGridItem("grid");
    if (dataItem) {
      if(dataItem.commodityState == "AUDITED"){
        alert("该PAD已通过审核");
        return ;
      }
      $('#commodityAuditDialog').modal();
      $("#commodityIdHid").val(dataItem.concentId);
      $("#commodityNameSpan").html(dataItem.concentName);
    }
  }

  function submitCommodityAudit(){
    if($("input[name='padState']:checked").val()=="NOT_AUDITED" && $("#remark").val() == ""){
      bootbox.alert("请填写审核不通过原因!");
      return false;
    }
    $.get("concentAudit.do",$("#commodityAuditForm").serialize(),function(result){
      if(result){
        $("#commodityAuditForm")[0].reset();
        $('#commodityAuditDialog').modal("hide");
        $("#grid").data("kendoGrid").dataSource.read();
      }else{
        alert("审核失败！");
      }
    });
  }
</script>

</body>
</html>
