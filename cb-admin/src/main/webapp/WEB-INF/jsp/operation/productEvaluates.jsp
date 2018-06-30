<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>评价管理</title>
  <script type="application/javascript">

    function detailItem(){
      var dataItem = getSelectedGridItem("grid");
      if (dataItem) {
        window.location.href = "evaluateDetails.do?evaluateId=" + dataItem.evaluateId;
      }
    }

    function removeItem() {
      var dataItem = getSelectedGridItem("grid");
      if (dataItem) {
        bootbox.confirm("确定删除吗？", function (result) {
          if (result) {
            $.get("removeProductEvaluateById.do", {
              evaluateId: dataItem.evaluateId,
              rad: Math.random()
            }, function (data) {
              if (data != 0) {
                commonNotify("删除成功！", "success");
                $("#grid").data("kendoGrid").dataSource.read();
              } else {
                commonNotify("删除失败!", "error");
              }
            });
          }
        });
      }
    }

    function formatCommodityLevel(commodityLevel) {
      switch (commodityLevel) {
        case "ONE":
          return "1";
        case "TWO":
          return "2";
        case "THREE":
          return "3";
        case "FOUR":
          return "4";
        case "FIVE":
          return "5";
        case "SIX":
          return "6";
        case "SEVEN":
          return "7";
        case "EIGHT":
          return "8";
        case "NINE":
          return "9";
      }
    }

      function formatReplyStatus(replyStatus){
          if(replyStatus){
              return "是";
          }else{
              return "否";
          }
      }
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
          <li><a href="#">评价管理</a></li>
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
          <h2>评价管理</h2>
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
        <%--<div class="toolbar responsive-helper">--%>
          <%--<form>--%>
            <%--<div class="pull-left">--%>
              <%--<div class="toolbar-field">--%>
                <%--<strong>仓库名称:</strong>--%>
              <%--</div>--%>
              <%--<div class="toolbar-field">--%>
                <%--<input type="text" class="form-control" placeholder="请输入仓库名称"/>--%>
              <%--</div>--%>
              <%--<div class="toolbar-field">--%>
                <%--<strong>仓库编码:</strong>--%>
              <%--</div>--%>
              <%--<div class="toolbar-field">--%>
                <%--<input type="text" class="form-control" placeholder="请输入仓库编码"/>--%>
              <%--</div>--%>
              <%--<div class="spacer-10"></div>--%>
            <%--</div>--%>
            <%--<!-- End .pull-left -->--%>
            <%--<div class="pull-right">--%>
              <%--<div class="toolbar-field">--%>
                <%--<button type="button" class="btn btn-default"><i class="fa fa-search"></i>查询</button>--%>
                <%--<button type="button" class="btn btn-default">清空</button>--%>
              <%--</div>--%>
            <%--</div>--%>
            <%--<!-- End .pull-right -->--%>
          <%--</form>--%>
        <%--</div>--%>
        <!-- End .toolbar -->

        <div class="spacer-10"></div>

        <div class="toolbar responsive-helper">
          <header>
            <div class="pull-left">
              <h3>评价列表</h3>
            </div>
            <div class="pull-right">
              <div class="btn-group">
                <a href="javascript:void(0);"  onclick="detailItem()" class="btn btn-default"><i class="fa fa-info-circle"></i>&nbsp;详情</a>
                  <a href="javascript:void(0);" onclick="replyEvaluateItem()" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;回复</a>
                <%--<a href="javascript:void(0);" onclick="removeItem()" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>--%>
              </div>
            </div>
          </header>
        </div>
        <div class="table-wrapper">
          <kendo:grid name="grid" pageable="false" sortable="true" selectable="true" height="450"  >
            <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
            <kendo:grid-columns>
              <kendo:grid-column title="商品名称" field="commodity.commodityName" width="220"/>
              <kendo:grid-column title="评分" field="score" width="50"/>
              <kendo:grid-column title="内容" field="content" width="200"/>
              <kendo:grid-column title="客户账户名" field="customer.accountName" width="80"/>
              <kendo:grid-column title="客户真实姓名" field="customer.realName" width="80"/>
              <kendo:grid-column title="是否已回复" field="replyStatus" width="80" template="#=formatReplyStatus(replyStatus)#"/>
              <kendo:grid-column title="创建时间" field="createTime" width="100" format="{0:yyyy-MM-dd HH:mm}"/>
            </kendo:grid-columns>
            <kendo:grid-dataBound>
              <script>
                function onDataBound(arg) {
                }
              </script>
            </kendo:grid-dataBound>
            <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
              <kendo:dataSource-schema data="content" total="totalElements">
                <kendo:dataSource-schema-model>
                  <kendo:dataSource-schema-model-fields>
                    <kendo:dataSource-schema-model-field name="createTime" type="date"/>
                  </kendo:dataSource-schema-model-fields>
                </kendo:dataSource-schema-model>
              </kendo:dataSource-schema>
              <kendo:dataSource-transport>
                <kendo:dataSource-transport-read url="pageProductEvaluates.do" type="POST" contentType="application/json"/>
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

    <jsp:include page="../layouts/footer.jsp"/>
    <!-- End #footer-main -->
  </div>
  <!-- End #content -->
</div>
<!-- End #main -->
<div class="modal fade" id="replyEvaluateDialog" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">评价回复</h4>
      </div>
      <div class="modal-body">
        <input type="hidden" id="evaluateHid"/>
        <div class="row">
          <div class="col-sm-3">
            <label>货品名称：</label>
          </div>
          <div class="col-sm-9 col-label">
            <span style="color:#002a80" id="productNameSpan"></span>
          </div>
        </div>

        <div class="row">
          <div class="col-sm-3">
            <label>评价内容：</label>
          </div>
          <div class="col-sm-9 col-label">
            <span id="evaluateContentSpan"></span>
          </div>
        </div>

        <div class="row">
          <div class="col-sm-3">
            <label>回复内容：</label>
          </div>
          <div class="col-sm-9 col-label">
            <textarea id="evaluateContent" rows="10" cols="50"></textarea>
          </div>
        </div>

      </div>
      <div class="modal-footer">
        <button class="btn btn-default" data-dismiss="modal">关闭</button>
        <button class="btn btn-primary pull-right" onclick="replyEvaluate();" id="btnComfrim">确认</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">

  function replyEvaluateItem() {
    var dataItem = getSelectedGridItem("grid");
    if (dataItem) {
      $('#replyEvaluateDialog').modal();
      var evaluateId = dataItem.evaluateId;
      $("#productNameSpan").html(dataItem.commodity.commodityName);
      $("#evaluateContentSpan").html(dataItem.content);
      $("#evaluateHid").val(evaluateId);
    }
  }

  function replyEvaluate(){
    var evaluateId=$("#evaluateHid").val();
    var replyContent=$("#evaluateContent").val();
    if(replyContent=="" ||replyContent==null || replyContent==undefined){
      alert("回复内容不可为空");
      return;
    }else{
      $.post("productEvaluateReply.do",{evaluateId:evaluateId,replyContent:replyContent},function(result){
        if(result){
          commonNotify("回复成功！", "success");
          $('#replyEvaluateDialog').modal("hide");
          $("#evaluateContent").val("");
          $("#grid").data("kendoGrid").dataSource.read();
        }else{
          commonNotify("回复失败!该评价已回复，请勿重复回复", "error");
        }
      });
    }
  }
</script>
</body>
</html>
