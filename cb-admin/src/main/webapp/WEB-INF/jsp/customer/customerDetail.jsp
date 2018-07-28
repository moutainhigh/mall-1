<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="ie ie6 lte9 lte8 lte7 no-js"> <![endif]-->
<!--[if IE 7]> <html class="ie ie7 lte9 lte8 lte7 no-js"> <![endif]-->
<!--[if IE 8]> <html class="ie ie8 lte9 lte8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie ie9 lte9 no-js"> <![endif]-->
<!--[if gt IE 9]> <html class="no-js"> <![endif]-->
<!--[if !IE]><!-->
<html class="no-js">                       <!--<![endif]-->
<head>
  <script src="../js/zoomify/viewer.min.js"></script>
  <script src="../js/zoomify/viewer-jquery.min.js"></script>
  <link rel="stylesheet" href="../js/zoomify/viewer.min.css">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>客户详情</title>
  <script type="text/javascript">
    $(document).ready(function() {
      <%--var p = $.citySelector.getProvince("${customer.province}");--%>
      <%--var c = $.citySelector.getCity("${customer.city}");--%>
      <%--var d = $.citySelector.getDistrict("${customer.district}");--%>
        var province=$.citySelector.getAddress("${customer.province}")
        var city=$.citySelector.getAddress("${customer.city}")
        var district=$.citySelector.getAddress("${customer.district}")
        var address='${customer.address}';
      $("#pdcDetail").html(province+city+district+address);
        for (var i=1;i<4;i++){
            $('#example'+i).viewer({
                url: 'data-original',
            });
        }
    });

  </script>
</head>
<body>



  <jsp:include page="../layouts/left.jsp"/>
  <!-- End aside -->


  <div id="sidebar-sec" class="sidebar">

    <div class="sidebar-sec-top"></div>

    <!-- ********** -->
    <!-- NEW MODULE -->
    <!-- ********** -->

    <div class="sidebar-module">
      <form class="input-group">
        <input type="text" name="" class="form-control" placeholder="Type A head..." id="typeahead-sidebar-search"/>

        <div class="input-group-btn">
          <!-- can NOT be used with typeahead
          <a href="#" class="clear-input"><i class="fa fa-times-circle"></i></a>
          -->
          <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
        </div>
      </form>
    </div>
    <!-- End .sidebar-module -->

    <div class="sidebar-line"><!-- A seperator line --></div>


    <ul class="ext-tabs-sidebar">
      <li class="active">
        <a href="#sidebar-tab-3"><i class="fa fa-group"></i> Users</a>
      </li>
      <li>
        <a href="#sidebar-tab-4"><i class="fa fa-check"></i> ToDo</a>
      </li>
    </ul>
    <!-- End .ext-tabs-sidebar -->
    <div class="tab-content">
      <div id="sidebar-tab-3" class="tab-pane active">


        <div class="sidebar-module">
          <ul class="mini-list">
            <li>
              <img src="../images/users/user-1.jpg" alt="" class="avatar"/>
              <ul>
                <li><a href="#" class="bold">Steven Watson</a></li>
                <li><a href="#">dummyemail@mail.com</a></li>
              </ul>
            </li>
            <li>
              <img src="../images/users/user-2.jpg" alt="" class="avatar"/>
              <ul>
                <li><a href="#" class="bold">Maris Bradley</a></li>
                <li><a href="#">dummyemail@mail.com</a></li>
              </ul>
            </li>
            <li>
              <img src="../images/users/user-3.jpg" alt="" class="avatar"/>
              <ul>
                <li><a href="#" class="bold">Wyatt Brooke</a></li>
                <li><a href="#">dummyemail@mail.com</a></li>
              </ul>
            </li>
            <li>
              <img src="../images/users/user-4.jpg" alt="" class="avatar"/>
              <ul>
                <li><a href="#" class="bold">Elly Martel</a></li>
                <li><a href="#">dummyemail@mail.com</a></li>
              </ul>
            </li>
            <li>
              <img src="../images/users/user-5.jpg" alt="" class="avatar"/>
              <ul>
                <li><a href="#" class="bold">Martin Gardenar</a></li>
                <li><a href="#">dummyemail@mail.com</a></li>
              </ul>
            </li>
            <li>
              <img src="../images/users/user-6.jpg" alt="" class="avatar"/>
              <ul>
                <li><a href="#" class="bold">Debra Hopper</a></li>
                <li><a href="#">dummyemail@mail.com</a></li>
              </ul>
            </li>
            <li>
              <img src="../images/users/user-7.jpg" alt="" class="avatar"/>
              <ul>
                <li><a href="#" class="bold">Nathan Rupertson</a></li>
                <li><a href="#">dummyemail@mail.com</a></li>
              </ul>
            </li>
          </ul>
          <!-- End .mini-list -->
        </div>
        <!-- End .sidebar-module -->
      </div>
      <div id="sidebar-tab-4" class="tab-pane">

        <!-- ********** -->
        <!-- NEW MODULE -->
        <!-- ********** -->

        <div class="sidebar-module">
          <div class="sidebar-todo">
            <div class="sidebar-todo-day">
              <h5>Due Today</h5>
              <ul>
                <li>
                  <label class="line-through">Start project X <input type="checkbox" name="" checked/><span></span></label>
                </li>
                <li>
                  <label>Email the invoice<input type="checkbox" name=""/><span></span></label>
                </li>
                <li>
                  <label>Call client T<input type="checkbox" name=""/><span></span></label>
                </li>
                <li>
                  <label>Take a coffe break<input type="checkbox" name=""/><span></span></label>
                </li>
              </ul>
            </div>
            <!-- End .sidebar-todo-day -->
            <div class="sidebar-todo-day">
              <h5>Due Tomorrow <span class="indicator-pill">32</span></h5>
              <ul>
                <li>
                  <label>Meeting with client T<input type="checkbox" name=""/><span></span></label>
                </li>
                <li>
                  <label>Meeting with client X<input type="checkbox" name=""/><span></span></label>
                </li>
                <li>
                  <label class="line-through">Buy new apple<input type="checkbox" name="" checked/><span></span></label>
                </li>
              </ul>
            </div>
            <!-- End .sidebar-todo-day -->
            <div class="sidebar-todo-day">
              <h5>Due Next Week</h5>
              <ul>
                <li>
                  <label>Start project T<input type="checkbox" name=""/><span></span></label>
                </li>
                <li>
                  <label>Buy new headphones<input type="checkbox" name=""/><span></span></label>
                </li>
              </ul>
            </div>
            <!-- End .sidebar-todo-day -->
          </div>
          <!-- End .sidebar-todo -->
        </div>
        <!-- End .sidebar-module -->
      </div>
    </div>
    <!-- End .tab-content -->

    <div class="sidebar-line"><!-- A seperator line --></div>

    <!-- ********** -->
    <!-- NEW MODULE -->
    <!-- ********** -->

    <div class="sidebar-module">
      <div class="circular-stats">
        <div class="circular-stats-inner">
          <div class="circular-stats-data">
            <strong>2779</strong>
            <span>+ 31%</span>
          </div>
          <input class="knob" data-width="100" data-cursor="false" data-fgColor="#aaaaaa" data-bgColor="#cccccc" data-thickness=".20"
                 value="31" data-readOnly="true" data-angleOffset="-0" data-displayInput="false">
        </div>
      </div>
      <!-- End .circular-stats -->
      <div class="circular-stats-infobox">
        <strong>This day</strong>
        <span>Lorem ipsum</span>
        <a href="#" class="btn btn-default">View</a>
      </div>
      <!-- End .circular-stats-infobox -->
      <div class="spacer-20"></div>
      <div class="circular-stats">
        <div class="circular-stats-inner">
          <div class="circular-stats-data">
            <strong>12899</strong>
            <span>+ 77%</span>
          </div>
          <input class="knob" data-width="100" data-cursor="false" data-fgColor="#aaaaaa" data-bgColor="#cccccc" data-thickness=".20"
                 value="77" data-readOnly="true" data-angleOffset="-0" data-displayInput="false">
        </div>
      </div>
      <!-- End .circular-stats -->
      <div class="circular-stats-infobox">
        <strong>This month</strong>
        <span>Lorem ipsum</span>
        <a href="#" class="btn btn-default">View</a>
      </div>
      <!-- End .circular-stats-infobox -->
      <div class="spacer-20"></div>
      <div class="circular-stats">
        <div class="circular-stats-inner">
          <div class="circular-stats-data">
            <strong>82229</strong>
            <span>+ 89%</span>
          </div>
          <input class="knob" data-width="100" data-cursor="false" data-fgColor="#aaaaaa" data-bgColor="#cccccc" data-thickness=".20"
                 value="89" data-readOnly="true" data-angleOffset="-0" data-displayInput="false">
        </div>
      </div>
      <!-- End .circular-stats -->
      <div class="circular-stats-infobox">
        <strong>This year</strong>
        <span>Lorem ipsum</span>
        <a href="#" class="btn btn-default">View</a>
      </div>
      <!-- End .circular-stats-infobox -->
    </div>
    <!-- End .sidebar-module -->
  </div>
  <!-- End #sidebar-sec -->

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
            <li><a href="#">客户查询</a></li>
            <li class="active">客户详情</li>
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
            <h2>客户详情</h2>
          </div>
          <div class="pull-right">
            <a class="btn btn-default" href="customers.do"><i class="fa fa-reply"></i></a>
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
          <ul class="ext-tabs">
            <li class="active">
              <a href="#content-tab-6-a">客户基本信息</a>
            </li>
            <li>
              <a href="#content-tab-6-b">收货地址</a>
            </li>
          </ul><!-- End .ext-tabs -->
          <div class="tab-content">
            <div id="content-tab-6-a" class="tab-pane active">
              <div class="inner-padding" style="margin-left: -20px">
                  <!-- * data-asf-time = seconds, data-asf-expireafter = minutes * -->
                  <fieldset>
                    <legend>客户详情</legend>
                    <div class="row">
                      <div class="inline-labels">
                        <div class="col-sm-2">
                          <label>账户名：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${customer.accountName}
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-2">
                          <label>手机号：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${customer.mobile}
                        </div>
                        <div class="col-sm-1"></div>
                      </div>
                    </div>
                    <div class="spacer-10"></div>

                    <div class="row">
                      <div class="inline-labels">

                        <div class="col-sm-2">
                          <label>昵称：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${customer.nickName}
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-2">
                          <label>真实姓名：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${customer.realName}
                        </div>
                        <div class="col-sm-1"></div>
                      </div>
                    </div>

                    <div class="spacer-10"></div>

                    <div class="row">
                      <div class="inline-labels">
                        <div class="col-sm-2">
                          <label>邮箱：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${customer.email}
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-2">
                          <label>固定电话：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${customer.telephone}
                        </div>
                        <div class="col-sm-1"></div>
                      </div>
                    </div>


                    <div class="row">
                      <div class="inline-labels">
                        <div class="col-sm-2">
                          <label>邮编：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${customer.postCode}
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-2">
                          <label>邀请码：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${customer.invitationCode}
                        </div>
                        <div class="col-sm-1"></div>
                      </div>
                    </div>


                    <div class="spacer-30"></div>
                    <hr>
                    <div class="spacer-30"></div>

                    <div class="row">
                      <div class="inline-labels">
                        <div class="col-sm-2">
                          <label>出生日期：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                           ${fn:substring(customer.birthday, 0, 10)}
                          </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-2">
                          <label>性别：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${customer.sex==true?'男':'女'}
                        </div>
                        <div class="col-sm-1"></div>
                      </div>
                    </div>


                    <div class="row">
                      <div class="inline-labels">
                        <div class="col-sm-2">
                          <label>证件类型：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${customer.cardType}
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-2">
                          <label>证件号码：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${customer.customerCardNo}
                        </div>
                        <div class="col-sm-1"></div>
                      </div>
                    </div>


                    <div class="row">
                      <div class="inline-labels">
                        <div class="col-sm-2">
                          <label>国籍：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${customer.customerCountry}
                        </div>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-2">
                          <label>证件有效期：<span class="asterisk"></span></label>
                        </div>
                        <div class="col-sm-2 col-label">
                          ${customer.customerCardPeroid}
                        </div>
                        <div class="col-sm-1"></div>
                      </div>
                    </div>

                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-2">
                        <label>详细地址：<span class="asterisk"></span>
                          <span id="district"></span>
                        </label>
                      </div>
                      <div class="col-sm-9" id="pdcDetail">

                      </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-2">
                        <label>备注：<span class="asterisk"></span>
                          <span id=""></span>
                        </label>
                      </div>
                      <div class="col-sm-2 col-label">
                        ${customer.remark}
                      </div>
                    </div>

                    <div class="spacer-30"></div>
                    <hr>
                    <div class="spacer-30"></div>

                    <div class="row">
                      <div class="col-sm-2">
                        <label><span class="asterisk"></span> 银行卡正面 ：</label>
                      </div>
                      <div class="col-sm-2 col-label" id="example1">
                        <c:if test="${customer.bankCardImg!=null&&customer.bankCardImg!=''}">
                          <img data-original="${customer.bankCardImg}"  src="${customer.bankCardImg}" alt="投保人银行卡正面" width="200" height="150"/>
                        </c:if>
                      </div>
                      <div class="col-sm-2">
                        <label><span class="asterisk"></span> 身份证正面 ：</label>
                      </div>
                      <div class="col-sm-2 col-label"  id="example2">
                      <c:if test="${customer.cardPositiveImg!=null&&customer.cardPositiveImg!=''}">
                        <img data-original="${customer.cardPositiveImg}" src="${customer.cardPositiveImg}" alt="投保人身份证正面" width="200" height="150"/>
                      </c:if>
                      </div>

                      <div class="col-sm-2">
                        <label><span class="asterisk"></span> 身份证反面 ：</label>
                      </div>
                      <div class="col-sm-2 col-label"  id="example3">
                      <c:if test="${customer.cardNegativeImg!=null&&customer.cardNegativeImg!=''}">
                        <img data-original="${customer.cardNegativeImg}" src="${customer.cardNegativeImg}" alt="投保人身份证反面" width="200" height="150"/>
                      </c:if>
                      </div>
                    </div>

                    <div class="spacer-30"></div>
                    <hr>
                    <div class="spacer-30"></div>

                    <div class="row">
                      <div class="col-sm-3"></div>
                      <div class="col-sm-9">

                        <a class="btn btn-default pull-right" href="customers.do">返回</a>
                      </div>
                    </div>
                  </fieldset>

              </div>
            </div>
            <div id="content-tab-6-b" class="tab-pane">
              <div class="spacer-10"></div>
              <div class="toolbar responsive-helper">
                <header>
                  <div class="pull-left">
                    <h3>收货地址列表</h3>
                  </div>

                </header>
              </div>
              <table class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th scope="col" data-rt-column="def">默认</th>
                  <th scope="col" data-rt-column="code">收货人姓名</th>
                  <th scope="col" data-rt-column="name">手机号</th>
                  <th scope="col" data-rt-column="address">地址</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="address" varStatus="statu" items="${deliveryAddresses}">
                  <tr>
                    <td>${address.defaultAddress==true?'是':'否'}</td>
                    <td>${address.consigneeName}</td>
                    <td>${address.consigneeMobile}</td>
                    <td id="address${statu.index}">
                      <script type="text/javascript">
                        var pa = $.citySelector.getProvince("${address.province}");
                        var ca = $.citySelector.getCity("${address.city}");
                        var da = $.citySelector.getDistrict("${address.district}");
                        $("#address"+${statu.index}).html(pa+" "+ca+" "+da);
                      </script>
                    ${address.consigneeAddress}</td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>

              <div class="spacer-30"></div>
              <hr>
              <div class="spacer-30"></div>

              <div class="row">
                <div class="col-sm-12">
                  <div class="btn-group pull-right">
                    <a class="btn btn-default pull-right" href="customers.do">返回</a>
                  </div>
                </div>
              </div>
            </div>

          </div><!-- End .tab-content -->
          <div class="spacer-40"></div>
          <div class="hr-totop"><span>Top</span></div>
          <div class="spacer-40"></div>

        </div>
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
