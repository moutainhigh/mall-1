<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>评价详情</title>

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
          <li><a href="#">评价管理</a></li>
          <li class="active">评价详情</li>
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
          <h2>评价详情</h2>
        </div>
        <div class="pull-right">
          <a class="btn btn-default" href="productEvaluates.do"><i class="fa fa-reply"></i></a>
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
      <div class="inner-padding">
        <div class="comments-toolbar">
          <div class="pull-left">
            <h4>评价信息</h4>
          </div>
        </div>

        <div class="row">
          <div class="col-sm-2">
            <label>商品品名称：</label>
          </div>
          <div class="col-sm-3">
            ${evaluate.commodity.commodityName}
          </div>
          <div class="col-sm-2">
            <label>评分：</label>
          </div>
          <div class="col-sm-3">
            ${evaluate.score}
          </div>
        </div>
        <div class="spacer-10"></div>
        <div class="row">
          <div class="col-sm-2">
            <label>评价人：</label>
          </div>
          <div class="col-sm-3">
            ${evaluate.customer.accountName}
          </div>
          <div class="col-sm-2">
            <label>评价日期：</label>
          </div>
          <div class="col-sm-3">
            ${evaluate.createTime}
          </div>
        </div>
        <div class="spacer-10"></div>
        <div class="row">
          <div class="col-sm-2">
            <label>评价内容：</label>
          </div>
          <div class="col-sm-8">
            ${evaluate.content}
          </div>

        </div>

        <div class="spacer-30"></div>
        <div class="hr"></div>
        <div class="spacer-30"></div>

        <div class="comments-toolbar">
          <div class="pull-left">
            <h4>回复信息</h4>
          </div>
        </div>
        <c:choose>
          <c:when test="${empty productEvaluateReplies}">
            <div class="row">
              <div class="col-sm-2">
                <label>暂无回复</label>
              </div>
            </div>
          </c:when>
          <c:otherwise>
            <c:forEach var="evaluateReply" items="${productEvaluateReplies}">

              <div class="row">
                <div class="col-sm-2">
                  <label>回复人：</label>
                </div>
                <div class="col-sm-3">
                    ${evaluateReply.user.userName}
                </div>
                <div class="col-sm-2">
                  <label>回复时间：</label>
                </div>
                <div class="col-sm-3">
                    ${evaluateReply.createTime}
                </div>
              </div>
              <div class="spacer-10"></div>
              <div class="row">
                <div class="col-sm-2">
                  <label>回复内容：</label>
                </div>
                <div class="col-sm-4">
                    ${evaluateReply.replyContent}
                </div>

              </div>
            </c:forEach>
          </c:otherwise>
        </c:choose>
        <div class="spacer-30"></div>
        <div class="hr"></div>
        <div class="spacer-30"></div>
        <div class="row">
          <div class="col-sm-12">
            <div class="btn-group pull-right">
              <a class="btn btn-default pull-right" href="productEvaluates.do">返回</a>
            </div>
          </div>
        </div>
      </div>
    <div class="spacer-40"></div>
    <div class="hr-totop"><span>Top</span></div>
    <div class="spacer-40"></div>
    <jsp:include page="../layouts/footer.jsp"/>
  </div>
</div>

</div>
</body>
</html>
