<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
  <!--<script src="../js/zoomify/zoomify.js"></script>-->
  <script src="http://www.jq22.com/jquery/jquery-migrate-1.2.1.min.js"></script>
  <script src="../js/zoomify/jquery.jqprint-0.3.js"></script>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>保单详情</title>
  <style>
    .th-tab {
      width: 1000px;
    }

    .div-tab {
      width: 100%;
      height: 100%;
      border-radius: 5px;
      border: #000000 solid 1px;
    }

    .div-line {
      text-align: left;
      margin: -1px;
      border-bottom: #000000 solid 1px;
      font-size: 13px;
    }

    .div-line .div-line-con{
      border-left: #000000 solid 1px;
      display: inline-block;
      padding: 5px 5px 5px 10px;
      margin-bottom: -1.5px;
    }

    .div-line .div-line-con div {
      display: inline-block;
    }

    .div-line .div-line-con .i-cb {
      position: relative;
      top: 2px;
    }

    .c-input {
      text-align: center;
      width: 30px;
    }

    .b-input {
      text-align: center;
      width: 40px;
    }

    .mobile-input {
      text-align: center;
      width: 80px;
    }

    .i-cert {
      display: inline-block;
      width: 10px;
      margin-left: -1px;
    }

    .div-row-2 {
      width: 18%;
    }

    .div-row-3 {
      width: 28%;
    }
    .div-row-4 {
      width: 38%;
    }

    /*.div-row-4 .title {*/
    /*width: 20% !important;*/
    /*}*/

    .div-checkbox input{
      display: inline-block;
    }

    .div-line .title {
      display: inline-block;
    }

    input {
      border: unset !important;
      outline:unset;
    }

    .a-input {
      padding-left: 10px;
      width: 60%;
    }


  </style>
  <script type="text/javascript">
      function daYin(){
          $("#prints").jqprint();
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
          <li><a href="#">保单管理</a></li>
          <li class="active">保单详情</li>
        </ul>
        <!-- End .breadcrumb -->
      </div>
      <div class="pull-right">
        <p>Version 1.0.0</p>
      </div>
    </div>
    <!-- End #header-main-bottom -->
  </header>

  <div id="content" class="clearfix">


    <header id="header-sec">
      <div class="inner-padding">
        <div class="pull-left">
          <h2>保单详情</h2>
        </div>
        <div class="pull-right">
          <a class="btn btn-default" href="insurances.do">
            <i class="fa fa-reply"></i>
          </a>

        </div>
      </div>
    </header>
    <div class="window">
      <div class="actionbar">
        <div class="pull-left">
          <ul class="ext-tabs">
            <li class="active">
              <a href="#content-tab-1">保单信息</a> <a class="btn btn-default pull-right" href="javascript:void(0);" onclick="daYin()">打印</a>
            </li>
          </ul>
        </div>
        <div class="pull-right">
          <a class="btn" href="#" id="lockscreen-slider-trigger">
            <i class="fa fa-lock"></i>
          </a>
          <a data-toggle-sidebar="right" class="btn small-toggle-btn" href="#"></a>
        </div>
      </div><!-- End .actionbar-->
      <div class="th-tab" id="prints">
        <div class="div-tab">
          <div class="div-line">
            <div class="div-line-con div-row-2">
              <div class="title">A 1.姓名</div>
              <input class="a-input" type="text"/>
            </div>
            <div class="div-line-con div-row-2">
              <div class="title">A 2.性别</div>
              <div>
                <input class="i-cb" type="checkbox"/>
                男
                <input class="i-cb" type="checkbox"/>
                女
              </div>
            </div>
            <div class="div-line-con div-row-4">
              <div class="title">A 3.出生日期</div>
              <div style="padding-left: 20px;">
                <input class="c-input" type="text"/>
                年
                <input class="c-input" type="text"/>
                月
                <input class="c-input" type="text"/>
                日
              </div>
            </div>
            <div class="div-line-con div-row-2">
              <div class="title">A 4.年龄</div>
              <input class="c-input" type="text"/>
              <div>周岁</div>
            </div>
          </div>

          <div class="div-line">
            <div class="div-line-con div-row-4">
              <div class="title">A 5.证件类型</div>
              <div>
                <input class="i-cb" type="checkbox"/>
                身份证
                <input class="i-cb" type="checkbox"/>
                护照
                <input class="i-cb" type="checkbox"/>
                军官证
                <input class="i-cb" type="checkbox"/>
                其他
                <input type="text" style="border-bottom: #000000 solid 1px;width: 40px">
              </div>
            </div>
            <div class="div-line-con">
              <div class="title">A 5.证件号码</div>
            </div>
            <div style="display: inline-block;float: right;margin-right: 1px;">
              <div class="div-line-con"><input class="i-cert"/></div><div class="div-line-con"><input class="i-cert"/></div><div class="div-line-con"><input class="i-cert"/></div><div class="div-line-con"><input class="i-cert"/></div><div class="div-line-con"><input class="i-cert"/></div><div class="div-line-con"><input class="i-cert"/></div><div class="div-line-con"><input class="i-cert"/></div><div class="div-line-con"><input class="i-cert"/></div><div class="div-line-con"><input class="i-cert"/></div><div class="div-line-con"><input class="i-cert"/></div><div class="div-line-con"><input class="i-cert"/></div><div class="div-line-con"><input class="i-cert"/></div><div class="div-line-con"><input class="i-cert"/></div><div class="div-line-con"><input class="i-cert"/></div><div class="div-line-con"><input class="i-cert"/></div><div class="div-line-con"><input class="i-cert"/></div><div class="div-line-con"><input class="i-cert"/></div><div class="div-line-con"><input class="i-cert"/></div>
            </div>
          </div>

          <div class="div-line">
            <div class="div-line-con div-row-4">
              <div class="title">A 5.证件有效期</div>
              <div>
                <input class="i-cb" type="checkbox"/>
                有效期至
                <div>
                  <input class="c-input" type="text"/>
                  年
                  <input class="c-input" type="text"/>
                  月
                  <input class="c-input" type="text"/>
                  日
                </div>
                <input class="i-cb" type="checkbox"/>
                长期
              </div>
            </div>
            <div class="div-line-con div-row-3">
              <div class="title">A 8.国籍、户籍</div>
              <input class="a-input" type="text"/>
            </div>
            <div class="div-line-con div-row-3">
              <div class="title">A 9.身高</div>
              <input class="b-input" type="text"/>
              厘米，
              <div class="title">体重</div>
              <input class="b-input" type="text"/>
              公斤
            </div>
          </div>
          <div class="div-line">
            <div class="div-line-con div-row-4">
              <div class="title">A 10.婚姻状况</div>
              <div>
                <input class="i-cb" type="checkbox"/>
                未婚
                <input class="i-cb" type="checkbox"/>
                已婚
                <input class="i-cb" type="checkbox"/>
                离异
                <input class="i-cb" type="checkbox"/>
                丧偶
              </div>
            </div>
            <div class="div-line-con div-row-3">
              <div class="title">A 11.固定电话（<span style="font-size: 1px">区号<input class="b-input" type="text"/></span>)-(<input class="mobile-input" type="text"/></span>)</div>
            </div>
            <div class="div-line-con div-row-3">
              <div class="title">A 12.移动电话</div>
              <input class="a-input" type="text"/>
            </div>
          </div>
        </div>
      </div>

    <jsp:include page="../layouts/footer.jsp"/>
  </div>
  <!-- End #content -->
</div>
<!-- End #main -->
</div>

</body>
</html>
