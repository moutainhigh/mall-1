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

  <title>产品统计</title>
  <script type="application/javascript">
    $(document).ready(function(){
      initConsumeChart();
    });

    function initConsumeChart() {
      var optionsLastThirtyDayConsume = {
        chart: {
          type: 'column'
        },
        title: {
          text: "大家电统计"
        },
        subtitle: {
          text: "平板电视 智能电视"
        },
        xAxis: {
          categories:[]
        },
        yAxis: {
          title: {
            text: "单位:件"
          }
        },
        legend: {
          enabled: false
        },
        tooltip: {
          headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
          pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
          '<td style="padding:0"><b>{point.y} 件</b></td></tr>',
          footerFormat: '</table>',
          shared: true,
          useHTML: true
        },
        series: [{
          name: "库存",
          color: '#f7cc04'
        }]
      };

        var dataAs = [["2016-02-25",207.3996],["2016-03-25",67.3996],["2016-04-25",107.3996],["2016-05-25",99.3996],["2016-06-25",222.3996]];
        var categories=["2016-02-25","2016-03-25","2016-04-25","2016-04-25","2016-05-25"];
        optionsLastThirtyDayConsume.series[0].data = dataAs;
        optionsLastThirtyDayConsume.xAxis.categories = categories;
        $('#visitors-stats').highcharts(optionsLastThirtyDayConsume);

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
            <li><a href="#">统计分析</a></li>
            <li class="active"><a href="#">产品统计</a></li>
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
            <h2>产品统计</h2>
          </div>
          <div class="pull-right">
            <div class="dropdown">
              <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-comments"></i>
                <span class="indicator-dot">3</span>
              </a>

              <div role="menu" class="dropdown-menu pull-center ext-dropdown-comments">
                <div class="ext-dropdown-header">
                  <i class="fa fa-comments"></i>
                  <h5>Comments</h5>
                  <a href="#" class="btn btn-default btn-sm delete-master"><i class="fa fa-trash-o"></i></a>
                  <span class="indicator-dot">3</span>
                </div>
                <div class="ext-dropdown-comments-content">
                  <div>
                    <img src="../images/users/user-1.jpg" alt="" class="avatar"/>
                    <a href="#">Karma, a good thing</a>
                    <ul>
                      <li><span>Posted by:</span> <a href="#">Steven</a></li>
                      <li><span>Date:</span> Dec 11, 2012</li>
                      <li>
                        <span>Actions:</span>
                        <a href="#">Read</a> -
                        <a href="#" class="delete">Delete</a>
                      </li>
                    </ul>
                  </div>
                  <div>
                    <img src="../images/users/user-4.jpg" alt="" class="avatar"/>
                    <a href="#">A simple, fast way to reduce stress</a>
                    <ul>
                      <li><span>Posted by:</span> <a href="#">Linda</a></li>
                      <li><span>Date:</span> Dec 3, 2012</li>
                      <li>
                        <span>Actions:</span>
                        <a href="#">Read</a> -
                        <a href="#" class="delete">Delete</a>
                      </li>
                    </ul>
                  </div>
                  <div>
                    <img src="../images/users/user-6.jpg" alt="" class="avatar"/>
                    <a href="#">Blog: karma and revenge</a>
                    <ul>
                      <li><span>Posted by:</span> <a href="#">Debra Hopper</a></li>
                      <li><span>Date:</span> Nov 18, 2012</li>
                      <li>
                        <span>Actions:</span>
                        <a href="#">Read</a> -
                        <a href="#" class="delete">Delete</a>
                      </li>
                    </ul>
                  </div>
                  <span>No new comments</span>
                </div>
                <div class="ext-dropdown-footer">
                  <p>Updated: 11/12/2012 - 14:12</p>
                  <a href="#" class="btn btn-default btn-sm"><i class="fa fa-caret-right"></i></a>
                </div>
              </div>
            </div>
            <!-- End .dropdown -->
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
            <div class="dropdown">
              <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-inbox"></i>
                <span class="indicator-dot">3</span>
              </a>

              <div role="menu" class="dropdown-menu pull-right ext-dropdown-inbox">
                <div class="ext-dropdown-header">
                  <h5>Inbox</h5>
                  <a href="#" class="btn btn-default btn-sm delete-master"><i class="fa fa-trash-o"></i></a>
                  <span class="indicator-dot">3</span>
                </div>
                <div class="ext-dropdown-inbox-content">
                  <div>
                    <a href="#">He did you get my new blog post?</a>
                    <ul class="nav">
                      <li><span>Send by:</span> <a href="#">Debra Hopper</a></li>
                      <li><span>Date:</span> Dec 12, 2012 - 14:03:09</li>
                      <li>
                        <span>Actions:</span>
                        <a href="#">Reply</a> -
                        <a href="#">Read</a> -
                        <a href="#">Spam</a> -
                        <a href="#" class="delete">Delete</a>
                      </li>
                    </ul>
                  </div>
                  <div>
                    <a href="#">I really love your karma theme</a>
                    <ul class="nav">
                      <li><span>Send by:</span> <a href="#">Nathan Rupertson</a></li>
                      <li><span>Date:</span> Dec 3, 2012 - 22:44:12</li>
                      <li>
                        <span>Actions:</span>
                        <a href="#">Reply</a> -
                        <a href="#">Read</a> -
                        <a href="#">Spam</a> -
                        <a href="#" class="delete">Delete</a>
                      </li>
                    </ul>
                  </div>
                  <div>
                    <a href="#">Feedback of a happy customer</a>
                    <ul class="nav">
                      <li><span>Send by:</span> <a href="#">Steven Watson</a></li>
                      <li><span>Date:</span> Dec 11, 2012 - 10:53:59</li>
                      <li>
                        <span>Actions:</span>
                        <a href="#">Reply</a> -
                        <a href="#">Read</a> -
                        <a href="#">Spam</a> -
                        <a href="#" class="delete">Delete</a>
                      </li>
                    </ul>
                  </div>
                  <span>No new emails</span>
                </div>
                <div class="ext-dropdown-footer">
                  <div class="progress bar-small">
                    <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                      <span class="sr-only">60% Complete</span>
                    </div>
                  </div>
                  <p>60%</p>
                  <a href="#" class="btn btn-default btn-sm"><i class="fa fa-caret-right"></i></a>
                </div>
              </div>
            </div>
            <!-- End .dropdown -->
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
          <div class="toolbar responsive-helper">
            <form>
              <div class="pull-left">
                <div class="toolbar-field">
                  <strong>产品名称:</strong>
                </div>
                <div class="toolbar-field">
                  <input type="text" class="form-control" placeholder="请输入产品名称"/>
                </div>

              </div>
              <!-- End .pull-left -->
              <div class="pull-right">
                <div class="toolbar-field">
                  <button type="button" class="btn btn-default"><i class="fa fa-search"></i>查询</button>
                  <button type="button" class="btn btn-default">清空</button>
                </div>
              </div>
              <!-- End .pull-right -->
            </form>
          </div>
          <!-- End .toolbar -->

          <div class="spacer-10"></div>

          <div class="toolbar responsive-helper">
            <header>
              <div class="pull-left">
                <h3>产品统计图表</h3>
              </div>
              <div class="pull-right">
                <div class="dropdown">
                  <a href="#" data-toggle="dropdown" class="btn btn-default dropdown-toggle">
                    操作
                    <i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="dropdown-menu ext-dropdown-icons-right pull-right" role="menu">
                    <%--<li>--%>
                    <%--<a href="../security/addUser.jsp"><i class="fa fa-plus"></i>新增</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                    <%--<a href="../security/editUser.jsp"><i class="fa fa-edit"></i>修改</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                    <%--<a href="#"><i class="fa fa-trash-o"></i>删除</a>--%>
                    <%--</li>--%>
                  </ul>
                </div>
              </div>
            </header>
          </div>
          <div class="table-wrapper">
            <%--<div style="width: 100%; height: 500px; " id="chart-mixed-1"><canvas class="flot-base" style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 1381px; height: 240px;" width="1381" height="240"></canvas><div class="flot-text" style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; font-size: smaller; color: rgb(84, 84, 84);"><div class="flot-x-axis flot-x1-axis xAxis x1Axis" style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;"><div style="position: absolute; max-width: 98px; top: 219px; left: 33px; text-align: center;" class="flot-tick-label tickLabel">1</div><div style="position: absolute; max-width: 98px; top: 219px; left: 136px; text-align: center;" class="flot-tick-label tickLabel">2</div><div style="position: absolute; max-width: 98px; top: 219px; left: 239px; text-align: center;" class="flot-tick-label tickLabel">3</div><div style="position: absolute; max-width: 98px; top: 219px; left: 342px; text-align: center;" class="flot-tick-label tickLabel">4</div><div style="position: absolute; max-width: 98px; top: 219px; left: 444px; text-align: center;" class="flot-tick-label tickLabel">5</div><div style="position: absolute; max-width: 98px; top: 219px; left: 547px; text-align: center;" class="flot-tick-label tickLabel">6</div><div style="position: absolute; max-width: 98px; top: 219px; left: 650px; text-align: center;" class="flot-tick-label tickLabel">7</div><div style="position: absolute; max-width: 98px; top: 219px; left: 753px; text-align: center;" class="flot-tick-label tickLabel">8</div><div style="position: absolute; max-width: 98px; top: 219px; left: 856px; text-align: center;" class="flot-tick-label tickLabel">9</div><div style="position: absolute; max-width: 98px; top: 219px; left: 956px; text-align: center;" class="flot-tick-label tickLabel">10</div><div style="position: absolute; max-width: 98px; top: 219px; left: 1059px; text-align: center;" class="flot-tick-label tickLabel">11</div><div style="position: absolute; max-width: 98px; top: 219px; left: 1161px; text-align: center;" class="flot-tick-label tickLabel">12</div><div style="position: absolute; max-width: 98px; top: 219px; left: 1264px; text-align: center;" class="flot-tick-label tickLabel">13</div><div style="position: absolute; max-width: 98px; top: 219px; left: 1367px; text-align: center;" class="flot-tick-label tickLabel">14</div></div><div class="flot-y-axis flot-y1-axis yAxis y1Axis" style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;"><div style="position: absolute; top: 204px; left: 25px; text-align: right;" class="flot-tick-label tickLabel">0</div><div style="position: absolute; top: 163px; left: 7px; text-align: right;" class="flot-tick-label tickLabel">5000</div><div style="position: absolute; top: 122px; left: 1px; text-align: right;" class="flot-tick-label tickLabel">10000</div><div style="position: absolute; top: 82px; left: 1px; text-align: right;" class="flot-tick-label tickLabel">15000</div><div style="position: absolute; top: 41px; left: 1px; text-align: right;" class="flot-tick-label tickLabel">20000</div><div style="position: absolute; top: 0px; left: 1px; text-align: right;" class="flot-tick-label tickLabel">25000</div></div></div><canvas class="flot-overlay" style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 1381px; height: 240px;" width="1381" height="240"></canvas><div class="legend"><div style="position: absolute; width: 80px; height: 88px; top: 15px; right: 13px; background-color: rgb(255, 255, 255); opacity: 0.85;"> </div><table style="position:absolute;top:15px;right:13px;;font-size:smaller;color:#333"><tbody><tr><td class="legendColorBox"><div style="border:1px solid #ccc;padding:1px"><div style="width:4px;height:0;border:5px solid #bbb;overflow:hidden"></div></div></td><td class="legendLabel">ThemeForest</td></tr><tr><td class="legendColorBox"><div style="border:1px solid #ccc;padding:1px"><div style="width:4px;height:0;border:5px solid #666;overflow:hidden"></div></div></td><td class="legendLabel">CodeCanyon</td></tr><tr><td class="legendColorBox"><div style="border:1px solid #ccc;padding:1px"><div style="width:4px;height:0;border:5px solid #ccc;overflow:hidden"></div></div></td><td class="legendLabel">GaphicRiver</td></tr><tr><td class="legendColorBox"><div style="border:1px solid #ccc;padding:1px"><div style="width:4px;height:0;border:5px solid #999;overflow:hidden"></div></div></td><td class="legendLabel">PhotoDune</td></tr></tbody></table></div></div>--%>
              <div id="visitors-stats" style="height:370px;" class="demo-graph graph-1"></div>
          </div>

        </div>

        <div class="spacer-30"></div>
        <div class="hr-totop"><span>Top</span></div>
        <div class="spacer-10"></div>

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
