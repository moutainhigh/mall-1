<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>商品组合</title>
  <script type="application/javascript">
    $(document).ready(function () {
    });

    function removeItem(combinationId) {

      bootbox.confirm("确定删除吗？", function (result) {
        if (combinationId) {
          $.get("removeCombinationById.do", {
            combinationId: combinationId
          }, function (data) {
            if (data) {
              $("#combinationId").remove();
              bootbox.confirm("删除成功！", function (result) {
                if (result) {
                  window.location.reload();
                }
              });
            } else {
              bootbox.confirm("删除失败！", function (result) {
                if (result) {
                  window.location.reload();
                }
              });
            }
          });
        }
      });

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
        <a href="#" id="logo-small"><h4></h4><h5></h5></a>
      </div>
      <div class="pull-right">
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
          <li><a href="#">商品管理</a></li>
          <li class="active">商品组合</li>
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
          <h2>商品${commodity.commodityName}的组合</h2>
        </div>
        <div class="pull-right">
          <a class="btn btn-default" href="commodities.do"><i class="fa fa-reply"></i></a>
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


      <div class="spacer-10"></div>

      <div class="inner-padding">
        <div class="toolbar responsive-helper">
          <header>
            <div class="pull-left">
              <h3>组合列表</h3>
            </div>
            <div class="pull-right">
              <div class="btn-group">
                <a href="javascript:void(0);" onclick="showCommodityDialog();" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;添加商品</a>
              </div>
            </div>
          </header>
        </div>
        <div class="row">
          <div class="col-sm-12">
            <table id="combinedCommoditiesTable" class="table table-bordered table-striped">
              <thead >
              <tr>
                <th scope="col" width="100">商品编码</th>
                <th scope="col" width="100">商品名称</th>
                <th scope="col" width="100">商品分类</th>
                <th scope="col" width="100">商品品牌</th>
                <th scope="col" width="100">创建时间</th>
                <th scope="col" style="width: 10px"  class="text-center">操作</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach var="combination" items="${combinations}">
                <tr id="${combination.combinationId}">
                  <td>${combination.combinedCommodity.commodityCode}</td>
                  <td>${combination.combinedCommodity.commodityName}</td>
                  <td>${combination.combinedCommodity.catalog.catalogName}</td>
                  <td>${combination.combinedCommodity.brand.brandName}</td>
                  <td>${combination.combinedCommodity.createTime}</td>
                  <td class="text-center">
                    <a href="javascript:removeItem(${combination.combinationId});" title="删除" class=" btn-less"><i class="fa fa-trash-o"></i></a>
                  </td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <jsp:include page="../layouts/footer.jsp"/>
  </div>
</div>

<div class="modal fade" id="commodityDialog" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" style="width: 1000px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">选择商品</h4>
      </div>
      <div class="modal-body">
        <form id="commodityFormId">
          <input type="hidden" name="commodityId" value="${commodity.commodityId}"/>
          <jsp:include page="../commodity/chooseCommodities.jsp"/>
        </form>
      </div>
      <div class="modal-footer">
        <button class="btn btn-default" data-dismiss="modal">关闭</button>
        <button class="btn btn-primary pull-right" onclick="chooseCommodity();">确认</button>
      </div>
    </div>
  </div>
  <script type="application/javascript">

    function showCommodityDialog() {
      $('#commodityDialog').modal();
    }

    function chooseCommodity() {
      var serializeArray=$("#commodityFormId").serialize();
      if(serializeArray.indexOf("selectedCommodityId")==-1){
        alert("请选择商品");
        return;
      }
      $.post(
          'addCombinationCommodities.do',
          serializeArray,
          function(data) {
              if(data){
                bootbox.confirm("添加商品成功！", function (result) {
                  if (result) {
                    window.location.reload();
                  }
                });
              }else{
                bootbox.confirm("添加商品失败！组合已存在或相同商品不能组合！", function (result) {
                  if (result) {
                    window.location.reload();
                  }
                });
              }
          }
      );
      clearCheck();
      $('#commodityDialog').modal("hide");
    }
  </script>
</div>

</body>
</html>
