<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

  <title>新增客户</title>
  <script type="text/javascript">
    $(document).ready(function() {
        $("#validateSubmitForm").validationEngine({
            autoHidePrompt: true, scroll: false, showOneMessage: true
        });
      // $.citySelector.init({
      //   province: "province",
      //   city: "city",
      //   district:"district"
      // });
      //
      // $("#validateSubmitForm").validationEngine({
      //   autoHidePrompt: true, scroll: false, showOneMessage: true,
      //   onValidationComplete: function (form, valid) {
      //     if(valid){
      //       if (null == $("#birthday").val() || "" == $("#birthday").val()) {
      //         bootbox.alert("请选择出生日期!");
      //         return false;
      //       }
      //       return true;
      //     }
      //   }
      // });

    });

    /**
     * 根据证件类型修改验证方式.
     * @param obj   选项内容
     * @author lxc
     */
    function changeCardType(obj){
        $('#customerCardNo').removeClass();
        var cardtype = "chinaId";
        if(obj == '居民身份证'){
            cardtype = "chinaId";
        }else if(obj == '居民户口薄'){
            cardtype = "householdVali";
        }else if(obj == '军人身份证'){
            cardtype = "chinaId";
        }else if(obj == '港澳居民往来内地通行证'){
            cardtype = "hkmcPassVali";
        }else if(obj == '出生证'){
            cardtype = "birthVali";
        }else if(obj == '台湾居民往来内地通行证'){
            cardtype = "taiwanPassVali";
        }else if(obj == '外国护照'){
            cardtype = "passportVali";
        }else if(obj == '外国人永久居留身份证'){
            cardtype = "permanentResidenceVali";
        }else if(obj == '武警身份证'){
            cardtype = "chinaId";
        }else if(obj == '其他证件'){
            cardtype = "onlyLetterNumber";
        }
        $('#customerCardNo').addClass("form-control validate[required,custom["+cardtype+"]]");
    }

  </script>
</head>
<body>


<!-- ********************************************
     * SIDEBAR MAIN:                            *
     *                                          *
     * the part which contains the main         *
     * navigation, logo, search and more...     *
     * (parts can be in both sidebars).         *
     ******************************************** -->

<jsp:include page="../layouts/left.jsp"/>
<!-- End aside -->

<!-- ********************************************
     * SIDEBAR SEC:                             *
     *                                          *
     * the part which contains things like      *
     * calendar, users, lists, blocks and       *
     * much more.                               *
     ******************************************** -->

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
          <li><a href="#">客户管理</a></li>
          <li><a href="#">客户查询</a></li>
          <li class="active">新增客户</li>
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

    <!-- ********************************************
         * HEADER SEC:                              *
         *                                          *
         * the part which contains the page title,  *
         * buttons and dropdowns.                   *
         ******************************************** -->

    <header id="header-sec">
      <div class="inner-padding">
        <div class="pull-left">
          <h2>新增客户</h2>
        </div>
        <div class="pull-right">
          <a class="btn btn-default" href="customers.do"><i class="fa fa-reply"></i></a>
        </div>
        <%--<div class="pull-right">--%>
          <%--<div class="dropdown">--%>
            <%--<a class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#">--%>
              <%--<i class="fa fa-comments"></i>--%>
              <%--<span class="indicator-dot">3</span>--%>
            <%--</a>--%>

            <%--<div role="menu" class="dropdown-menu pull-center ext-dropdown-comments">--%>
              <%--<div class="ext-dropdown-header">--%>
                <%--<i class="fa fa-comments"></i>--%>
                <%--<h5>Comments</h5>--%>
                <%--<a href="#" class="btn btn-default btn-sm delete-master"><i class="fa fa-trash-o"></i></a>--%>
                <%--<span class="indicator-dot">3</span>--%>
              <%--</div>--%>
              <%--<div class="ext-dropdown-comments-content">--%>
                <%--<div>--%>
                  <%--<img src="../images/users/user-1.jpg" alt="" class="avatar"/>--%>
                  <%--<a href="#">Karma, a good thing</a>--%>
                  <%--<ul>--%>
                    <%--<li><span>Posted by:</span> <a href="#">Steven</a></li>--%>
                    <%--<li><span>Date:</span> Dec 11, 2012</li>--%>
                    <%--<li>--%>
                      <%--<span>Actions:</span>--%>
                      <%--<a href="#">Read</a> ---%>
                      <%--<a href="#" class="delete">Delete</a>--%>
                    <%--</li>--%>
                  <%--</ul>--%>
                <%--</div>--%>
                <%--<div>--%>
                  <%--<img src="../images/users/user-4.jpg" alt="" class="avatar"/>--%>
                  <%--<a href="#">A simple, fast way to reduce stress</a>--%>
                  <%--<ul>--%>
                    <%--<li><span>Posted by:</span> <a href="#">Linda</a></li>--%>
                    <%--<li><span>Date:</span> Dec 3, 2012</li>--%>
                    <%--<li>--%>
                      <%--<span>Actions:</span>--%>
                      <%--<a href="#">Read</a> ---%>
                      <%--<a href="#" class="delete">Delete</a>--%>
                    <%--</li>--%>
                  <%--</ul>--%>
                <%--</div>--%>
                <%--<div>--%>
                  <%--<img src="../images/users/user-6.jpg" alt="" class="avatar"/>--%>
                  <%--<a href="#">Blog: karma and revenge</a>--%>
                  <%--<ul>--%>
                    <%--<li><span>Posted by:</span> <a href="#">Debra Hopper</a></li>--%>
                    <%--<li><span>Date:</span> Nov 18, 2012</li>--%>
                    <%--<li>--%>
                      <%--<span>Actions:</span>--%>
                      <%--<a href="#">Read</a> ---%>
                      <%--<a href="#" class="delete">Delete</a>--%>
                    <%--</li>--%>
                  <%--</ul>--%>
                <%--</div>--%>
                <%--<span>No new comments</span>--%>
              <%--</div>--%>
              <%--<div class="ext-dropdown-footer">--%>
                <%--<p>Updated: 11/12/2012 - 14:12</p>--%>
                <%--<a href="#" class="btn btn-default btn-sm"><i class="fa fa-caret-right"></i></a>--%>
              <%--</div>--%>
            <%--</div>--%>
          <%--</div>--%>
          <%--<!-- End .dropdown -->--%>
          <%--<div class="btn-group">--%>
            <%--<a class="btn btn-default" href="#">--%>
              <%--<i class="fa fa-star"></i>--%>
            <%--</a>--%>
            <%--<a class="btn btn-default" href="#" id="modal-update-trigger">--%>
              <%--Modal--%>
            <%--</a>--%>
            <%--<a class="btn btn-default" href="#">--%>
              <%--<i class="fa fa-cog"></i>--%>
            <%--</a>--%>
          <%--</div>--%>
          <%--<!-- End .btn-group -->--%>
          <%--<div class="dropdown">--%>
            <%--<a class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#">--%>
              <%--<i class="fa fa-inbox"></i>--%>
              <%--<span class="indicator-dot">3</span>--%>
            <%--</a>--%>

            <%--<div role="menu" class="dropdown-menu pull-right ext-dropdown-inbox">--%>
              <%--<div class="ext-dropdown-header">--%>
                <%--<h5>Inbox</h5>--%>
                <%--<a href="#" class="btn btn-default btn-sm delete-master"><i class="fa fa-trash-o"></i></a>--%>
                <%--<span class="indicator-dot">3</span>--%>
              <%--</div>--%>
              <%--<div class="ext-dropdown-inbox-content">--%>
                <%--<div>--%>
                  <%--<a href="#">He did you get my new blog post?</a>--%>
                  <%--<ul class="nav">--%>
                    <%--<li><span>Send by:</span> <a href="#">Debra Hopper</a></li>--%>
                    <%--<li><span>Date:</span> Dec 12, 2012 - 14:03:09</li>--%>
                    <%--<li>--%>
                      <%--<span>Actions:</span>--%>
                      <%--<a href="#">Reply</a> ---%>
                      <%--<a href="#">Read</a> ---%>
                      <%--<a href="#">Spam</a> ---%>
                      <%--<a href="#" class="delete">Delete</a>--%>
                    <%--</li>--%>
                  <%--</ul>--%>
                <%--</div>--%>
                <%--<div>--%>
                  <%--<a href="#">I really love your karma theme</a>--%>
                  <%--<ul class="nav">--%>
                    <%--<li><span>Send by:</span> <a href="#">Nathan Rupertson</a></li>--%>
                    <%--<li><span>Date:</span> Dec 3, 2012 - 22:44:12</li>--%>
                    <%--<li>--%>
                      <%--<span>Actions:</span>--%>
                      <%--<a href="#">Reply</a> ---%>
                      <%--<a href="#">Read</a> ---%>
                      <%--<a href="#">Spam</a> ---%>
                      <%--<a href="#" class="delete">Delete</a>--%>
                    <%--</li>--%>
                  <%--</ul>--%>
                <%--</div>--%>
                <%--<div>--%>
                  <%--<a href="#">Feedback of a happy customer</a>--%>
                  <%--<ul class="nav">--%>
                    <%--<li><span>Send by:</span> <a href="#">Steven Watson</a></li>--%>
                    <%--<li><span>Date:</span> Dec 11, 2012 - 10:53:59</li>--%>
                    <%--<li>--%>
                      <%--<span>Actions:</span>--%>
                      <%--<a href="#">Reply</a> ---%>
                      <%--<a href="#">Read</a> ---%>
                      <%--<a href="#">Spam</a> ---%>
                      <%--<a href="#" class="delete">Delete</a>--%>
                    <%--</li>--%>
                  <%--</ul>--%>
                <%--</div>--%>
                <%--<span>No new emails</span>--%>
              <%--</div>--%>
              <%--<div class="ext-dropdown-footer">--%>
                <%--<div class="progress bar-small">--%>
                  <%--<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">--%>
                    <%--<span class="sr-only">60% Complete</span>--%>
                  <%--</div>--%>
                <%--</div>--%>
                <%--<p>60%</p>--%>
                <%--<a href="#" class="btn btn-default btn-sm"><i class="fa fa-caret-right"></i></a>--%>
              <%--</div>--%>
            <%--</div>--%>
          <%--</div>--%>
          <%--<!-- End .dropdown -->--%>
        <%--</div>--%>
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
        <form:form id="validateSubmitForm" cssClass="form-horizontal" data-asf-expireafter="1" data-asf-time="10" action="addCustomer.do" method="post" commandName="customer" >

          <!-- * data-asf-time = seconds, data-asf-expireafter = minutes * -->
          <fieldset>
            <legend>新增客户</legend>
            <div class="row">
              <div class="inline-labels">
                <div class="col-sm-2">
                  <label>真实姓名：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:input path="realName" cssClass="form-control validate[required,minSize[1]]" maxlength="14" />
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <label>手机号：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:input cssClass="form-control validate[required,custom[phone]]" path="mobile" maxlength="11"/>
                </div>
                <div class="col-sm-1"></div>
              </div>
            </div>

            <div class="spacer-10"></div>

            <div class="row">
              <div class="inline-labels">
                <div class="col-sm-2">
                  <label>证件类型：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:select path="cardType" cssClass="form-control validate[required]" onchange="changeCardType(this.value)">
                      <form:option value="居民身份证">居民身份证</form:option>
                    <form:option value="居民户口薄">居民户口薄</form:option>
                    <form:option value="军人身份证">军人身份证</form:option>
                    <form:option value="港澳居民往来内地通行证">港澳居民往来内地通行证</form:option>
                    <form:option value="出生证">出生证</form:option>
                    <form:option value="台湾居民往来内地通行证">台湾居民往来内地通行证</form:option>
                    <form:option value="外国护照">外国护照</form:option>
                    <form:option value="外国人永久居留身份证">外国人永久居留身份证</form:option>
                    <form:option value="武警身份证">武警身份证</form:option>
                    <form:option value="其他证件">其他证件</form:option>

                  </form:select>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <label>证件号码：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:input cssClass="form-control validate[required,custom[onlyLetterNumber]]" path="customerCardNo" maxlength="25" />
                </div>
                <div class="col-sm-1"></div>
              </div>
            </div>

            <div class="spacer-10"></div>


            <div class="row">
              <div class="inline-labels">
                <div class="col-sm-2">
                  <label>Email：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:input path="email" cssClass="form-control validate[required,minSize[1]]" maxlength="30" />
                </div>
                <div class="col-sm-1"></div>

              </div>
            </div>

            <%--<div class="row">--%>
              <%--<div class="inline-labels">--%>

                <%--<div class="col-sm-2">--%>
                  <%--<label>邮箱：<span class="asterisk">*</span></label>--%>
                <%--</div>--%>
                <%--<div class="col-sm-3">--%>
                  <%--<form:input cssClass="form-control validate[required]" path="email" maxlength="64"/>--%>
                <%--</div>--%>
                <%--<div class="col-sm-1"></div>--%>
                <%--<div class="col-sm-2">--%>
                  <%--<label>真实姓名：</label>--%>
                <%--</div>--%>
                <%--<div class="col-sm-3">--%>
                  <%--<form:input cssClass="form-control" path="realName" maxlength="64"/>--%>
                <%--</div>--%>
                <%--<div class="col-sm-1"></div>--%>
              <%--</div>--%>
            <%--</div>--%>

            <div class="spacer-10"></div>

            <%--<div class="row">--%>
              <%--<div class="inline-labels">--%>
                <%--<div class="col-sm-2">--%>
                  <%--<label>邮编：</label>--%>
                <%--</div>--%>
                <%--<div class="col-sm-3">--%>
                  <%--<div class="inline-labels">--%>
                    <%--<form:input cssClass="form-control" path="postCode" maxlength="6"/>--%>
                  <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-sm-1"></div>--%>
                <%--<div class="col-sm-2">--%>
                  <%--<label>固定电话：</label>--%>
                <%--</div>--%>
                <%--<div class="col-sm-3">--%>
                  <%--<form:input cssClass="form-control" path="telephone" maxlength="14"/>--%>
                <%--</div>--%>
                <%--<div class="col-sm-1"></div>--%>
              <%--</div>--%>
            <%--</div>--%>

            <div class="spacer-30"></div>
            <hr>
            <div class="spacer-30"></div>

            <%--<div class="row">--%>
              <%--<div class="inline-labels">--%>
                <%--<div class="col-sm-2">--%>
                  <%--<label>出生日期：<span class="asterisk">*</span></label>--%>
                <%--</div>--%>
                <%--<div class="col-sm-3">--%>
                  <%--<kendo:datePicker id="birthday" name="birthday" format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" culture="zh-CN"></kendo:datePicker>--%>
                <%--</div>--%>
                <%--<div class="col-sm-1"></div>--%>
                <%--<div class="col-sm-2">--%>
                  <%--<label>性别：<span class="asterisk">*</span></label>--%>
                <%--</div>--%>
                <%--<div class="col-sm-3">--%>
                  <%--<form:radiobutton path="sex" value="1"/>男--%>
                  <%--<form:radiobutton path="sex" value="0"/>女--%>
                <%--</div>--%>
                <%--<div class="col-sm-1"></div>--%>
              <%--</div>--%>
            <%--</div>--%>
            <div class="spacer-10"></div>
            <%--<div class="row">--%>
              <%--<div class="col-sm-2">--%>
                <%--<label>所在地区：<span class="asterisk">*</span></label>--%>
              <%--</div>--%>
              <%--<div class="col-sm-3">--%>
                <%--<select class="form-control" id="province" name="province"></select>--%>
              <%--</div>--%>
              <%--<div class="col-sm-3">--%>
                <%--<select class="form-control" id="city" name="city"></select>--%>
              <%--</div>--%>
              <%--<div class="col-sm-3">--%>
                <%--<select class="form-control" id="district" name="district"></select>--%>
              <%--</div>--%>
            <%--</div>--%>

            <div class="spacer-10"></div>

            <%--<div class="row">--%>
              <%--<div class="col-sm-2">--%>
                <%--<label>详细地址：<span class="asterisk">*</span></label>--%>
              <%--</div>--%>
              <%--<div class="col-sm-9">--%>
                <%--<form:input cssClass="form-control validate[required]" path="address" maxlength="255"/>--%>
              <%--</div>--%>

            <%--</div>--%>

            <div class="spacer-30"></div>
            <hr>
            <div class="spacer-30"></div>

            <div class="row">
              <div class="col-sm-2">
                <label>备注</label>
              </div>
              <div class="col-sm-9">
                <form:textarea cssClass="form-control" path="remark"  maxlength="1024"></form:textarea>
              </div>
              <div class="col-sm-1"></div>
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
      <!-- End .inner-padding -->
    </div>
    <!-- End .window -->


    <jsp:include page="../layouts/footer.jsp"/>
    <!-- End #footer-main -->
  </div>
  <!-- End #content -->
</div>
<!-- End #main -->
</body>
</html>
