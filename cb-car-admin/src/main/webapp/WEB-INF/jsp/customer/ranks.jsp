<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>客户等级管理</title>
  <script type="text/javascript">
    function editItem(rankId) {
      window.location.href = "toEditRank.do?rankId=" + rankId;
    }

    function removeItem(rankId) {
      bootbox.confirm("确认删除吗？", function (result) {
        if (result) {
          $.get("removeRankById.do", {
            rankId: rankId
          }, function (data) {
            if (data) {
              bootbox.alert("成功",function(){
                window.location.href = "ranks.do";
              });

            } else {
              bootbox.alert("失败");
            }
          });
        }
      });
    }

    function setDefaultItem(rankId){
      bootbox.confirm("确认将该等级设为默认等级吗？", function (result) {
        if (result) {
          $.get("setDefaultRankByRankId.do", {
            rankId: rankId
          }, function (data) {
            if (data) {
              bootbox.alert("成功",function(){
                window.location.href = "ranks.do";
              });

            } else {
              bootbox.alert("失败");
            }
          });
        }
      });
    }
  </script>
</head>
<body>



<jsp:include page="../layouts/left.jsp"/>
<!-- End aside -->

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
          <li><a href="#">客户管理</a></li>
          <li class="active">客户等级管理</li>
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
          <h2>客户等级管理</h2>
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
          <header>
            <div class="pull-left">
              <h3>等级列表</h3>
            </div>
            <div class="pull-right">
              <div class="btn-group">
                <a href="toAddRank.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
              </div>
            </div>
          </header>
        </div>
        <div class="table-wrapper">
          <table class="table table-bordered table-striped">
            <thead>
            <tr>
              <th scope="col" data-rt-column="code">等级名称</th>
              <th scope="col" data-rt-column="code">积分</th>
              <th scope="col" data-rt-column="name">默认等级</th>
              <th scope="col" data-rt-column="startTime">备注</th>
              <th scope="col" data-rt-column="operate">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="rank" items="${ranks}">
              <tr>
                <td>${rank.rankName}</td>
                <td>${rank.integral}</td>
                <td>${rank.defaultRank==true?"是":"否"}</td>
                <td>${rank.remark}</td>
                <td>
                  <a href="javascript:void(0);" onclick="setDefaultItem('${rank.rankId}')">设置默认</a>&nbsp;
                  <a href="javascript:void(0);" onclick="editItem('${rank.rankId}')">编辑</a>&nbsp;
                  <a href="javascript:void(0);" onclick="removeItem('${rank.rankId}')">删除</a>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
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


</body>
</html>
