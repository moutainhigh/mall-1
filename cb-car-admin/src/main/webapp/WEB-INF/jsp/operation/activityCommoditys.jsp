<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>活动商品列表</title>
  <script type="application/javascript">
    $(document).ready(function () {
      $("#validateSubmitForm").validationEngine({
        autoHidePrompt: true, scroll: false, showOneMessage: true
      });
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
          <li><a href="#">运营管理</a></li>
          <li><a href="#">活动推广</a></li>
          <li><a href="#">活动管理</a></li>
          <li class="active">活动商品列表</li>
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
          <h2>活动 ${activity.activityName}的商品列表</h2>
        </div>
        <div class="pull-right">
          <a class="btn btn-default " href="activities.do"><i class="fa fa-reply"></i></a>
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
              <h3>活动商品列表</h3>
            </div>
            <div class="pull-right">
              <div class="btn-group">
                <a href="javascript:void(0);" onclick="showCommodityDialog();" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;商品选择</a>
              </div>
            </div>
          </header>
        </div>
        <form:form id="validateSubmitForm" action="addActivityCommodities.do" cssClass="form-horizontal" method="post" commandName="activity">
          <form:hidden path="activityId"/>
        <fieldset>
          <legend>活动商品表单</legend>
          <div class="spacer-10"></div>
          <div class="row">
            <div class="col-sm-12">

              <table id="activityCommoditiesTable" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th scope="col" width="100">商品名称</th>
                  <th scope="col" width="100">数量限制</th>
                  <th scope="col" style="width: 30px"  class="text-center">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${activityCommodities}" var="actCom" varStatus="acState">
                  <tr id="commodity${acState.index}">
                    <td><input type='hidden' name='selectedCommodityId' value='${actCom.commodity.commodityId}'/>${actCom.commodity.commodityName}</td>
                    <td><input type='text' name='limitAmountSize' class='form-control validate[required,custom[number]]' value="${actCom.limitAmountSize}" readonly="true"/></td>
                    <td><a type='button' title='编辑' class='btn btn-default' href="javascript:void(0);" onclick="editItem(${acState.index})"><i class='fa fa-pencil-square-o'></i></a>&nbsp;&nbsp;<a type='button' title='删除' class='btn btn-default' href="javascript:void(0);" onclick="removeActCom(${acState.index})"><i class='fa fa-minus-circle'></i></a> </td>
                  </tr>
                </c:forEach>

                </tbody>
              </table>
            </div>
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
          <jsp:include page="../commodity/chooseCommodities.jsp"/>
      </div>
      <div class="modal-footer">
        <button class="btn btn-default" data-dismiss="modal">关闭</button>
        <button class="btn btn-primary pull-right" onclick="chooseCommodity();">确认</button>
      </div>
    </div>
  </div>
  <script type="application/javascript">

    var idIndex = ${activityCommodities!=null?activityCommodities.size():0};

    function showCommodityDialog() {
      $('#commodityDialog').modal();
    }

    function chooseCommodity() {
      var selectedCommodityIds=$("#commodityGrid input[type='checkbox'][name='selectedCommodityId']:checked");
      if(selectedCommodityIds!=null&&selectedCommodityIds.length>0){
        $.each(selectedCommodityIds,function(n,selectedBox) {
          var gridData = $("#commodityGrid").data("kendoGrid").dataSource;
          var selectedCommodityId=$(selectedBox).attr('value');
          $.each(gridData.data(),function(i,dataItem){
            if(dataItem.commodityId==selectedCommodityId){
              var newRow = "<tr id='commodity" + idIndex + "'><td><input type='hidden' name='selectedCommodityId' value='"+selectedCommodityId+"'/>"+dataItem.commodityName+"</td><td><input type='text' name='limitAmountSize' class='form-control validate[required,custom[number]]'/></td><td><a type='button' title='删除' class='btn btn-default' href='javascript:removeActCom(" + idIndex + ")'><i class='fa fa-minus-circle'></i></a></td></tr>";
              $("#activityCommoditiesTable tr:last").after(newRow);
              idIndex++;
              return ;
            }
          });
        });
      }else{
        alert("请选择商品");
        return ;
      }
      clearCheck();
      $('#commodityDialog').modal("hide");
    }

    function removeActCom(indx) {
      $("#commodity" + indx).remove();
    }

    function editItem(indx) {
      var las = $("#commodity" + indx).find("td:eq(1)").find("input");
      las.removeAttr("readonly");
    }
  </script>
</div>

</body>
</html>
