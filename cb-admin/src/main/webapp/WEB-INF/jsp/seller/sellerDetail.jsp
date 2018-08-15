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

  <title>商家详情</title>
  <script type="application/javascript">
    $(document).ready(function(){

    });
    function returnSellers(){
      window.location.href = "sellers.do";
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
          <li><a href="#">商家管理</a></li>
          <li><a href="#">商家查询</a></li>
          <li class="active">商家详情</li>
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
          <h2>商家详情</h2>
        </div>
        <div class="pull-right">
          <a class="btn btn-default" href="sellers.do"><i class="fa fa-reply"></i></a>
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
          <fieldset>
            <legend>商家详情</legend>

            <div class="spacer-10"></div>
            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 商家名称：</label>
              </div>
              <div class="col-sm-3">
                ${seller.sellerName}
              </div>
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 商家编码：</label>
              </div>
              <div class="col-sm-3">
                ${seller.sellerCode}
              </div>

            </div>

            <div class="spacer-10"></div>

            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 商家地址：</label>
              </div>
              <div class="col-sm-3">
                <span style="word-break:break-all">${seller.sellerAddress}</span>
              </div>
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 商家类型：</label>
              </div>
              <div class="col-sm-3">
                ${seller.sellerType.name}
              </div>
            </div>

            <div class="spacer-10"></div>

            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 联系人：</label>
              </div>
              <div class="col-sm-3">
                ${seller.linkman}
                </div>
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 手机：</label>
              </div>
              <div class="col-sm-3">
                ${seller.mobile}
                </div>
            </div>

            <div class="spacer-10"></div>

            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 联系电话：</label>
              </div>
              <div class="col-sm-3">
                ${seller.telephone}
                </div>
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 邮箱：</label>
              </div>
              <div class="col-sm-3">
                ${seller.email}
                </div>
            </div>

            <div class="spacer-30"></div>
            <hr>
            <div class="spacer-30"></div>

            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> QQ：</label>
              </div>
              <div class="col-sm-3">
                ${seller.qq}
                </div>
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 商家微信：</label>
              </div>
              <div class="col-sm-3">
                ${seller.wechat}
                </div>
            </div>

            <div class="spacer-10"></div>

            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 商家支付平台类型：</label>
              </div>
              <div class="col-sm-3">
                ${seller.channelType.name}
              </div>
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 商家支付平台号：</label>
              </div>
              <div class="col-sm-3">
                ${seller.channelAccount}
                </div>
            </div>

            <div class="spacer-10"></div>

            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 营业执照名称：</label>
              </div>
              <div class="col-sm-3">
                <span style="word-break:break-all">${seller.busName}</span>
              </div>
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 营业执照注册号：</label>
              </div>
              <div class="col-sm-3">
                ${seller.buslicenseNo}
                </div>
            </div>

            <div class="spacer-30"></div>
            <hr>
            <div class="spacer-30"></div>

            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 银行户口：</label>
              </div>
              <div class="col-sm-3">
                ${seller.accountName}
                </div>
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 对公户名：</label>
              </div>
              <div class="col-sm-3">
                ${seller.publicAccount}
                </div>
            </div>

            <div class="spacer-10"></div>

            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 开户银行：</label>
              </div>
              <div class="col-sm-3">
                ${seller.bankAccount}
                </div>
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 开户银行地址：</label>
              </div>
              <div class="col-sm-3">
                ${seller.bankAccountAddress}
                </div>
            </div>

            <div class="spacer-10"></div>

            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span> 身份证号：</label>
              </div>
              <div class="col-sm-3">
                ${seller.idCardNum}
                </div>
            </div>


            <div class="spacer-30"></div>
            <hr>
            <div class="spacer-30"></div>

            <div class="row">
              <div class="col-sm-2">
                <label> 备注：</label>
              </div>
              <div class="col-sm-8">
                ${seller.remark}
                </div>
            </div>

            <div class="spacer-20"></div>
            <div class="row">
              <div class="col-sm-3"></div>
              <div class="col-sm-9">

                <button class="btn btn-default pull-right" type="reset" onclick="returnSellers()">返回</button>
              </div>
            </div>
          </fieldset>
        <div class="spacer-40"></div>
        <div class="hr-totop"><span>Top</span></div>
        <div class="spacer-10"></div>
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
