<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

  <title>账单统计</title>
  <script type="application/javascript">
    $(document).ready(function(){
      initDate();
      reloadCharts1();
      reloadCharts2();
    });

    function reloadCharts1() {
        var options = {
            chart: {
                type: 'column',
                zoomType: 'x'
            },
            title: {
                text: "月度账单统计"
            },
            subtitle: {
                text: $("#yearSelect").val() + " 年" + $("#monthSelect").val() + " 月"
            },
            xAxis: {
                categories: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31],
                title: {
                    text: "时间:日"
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: "单位: 元 RMB"
                },
                alternateGridColor: '#FDFFD5'
            },
            plotOptions: {
                series: {
                    borderWidth: 0,
                    dataLabels: {
                        enabled: true,
                        format: '{point.y:.2f}'
                    },
                    enableMouseTracking: false
                }
            },
            legend: {
                enabled: true
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} 元 RMB</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            series: [
                {
                    name: "收入",
                    color: '#029402'
                },
                {
                    name: "支出",
                    color: '#ff0000'
                }
            ]

        };

        $.getJSON('getDayBill.do', {
            year: $("#yearSelect").val(),
            month: $("#monthSelect").val(),
        }, function (json) {
            var datasA = [];
            var datasB = [];
            var categories = [];
            $.each(json, function (date, value) {
                if (value.type == 1) {
                    datasA.push([value.day, value.amount]);
                } else if (value.type == 2) {
                    datasB.push([value.day, value.amount]);
                }
                if ($.inArray(value.day, categories) <= 0) {
                  categories.push(value.day);
                }
            });
            options.series[0].data = datasA;
            options.series[1].data = datasB;
            options.xAxis.categories = categories;
            $('#chartContainer').highcharts(options);
        });

    }

    function reloadCharts2() {
        var options = {

            chart: {
                type: 'column',
                zoomType: 'x'
            },
            title: {
                text: "年度账单统计"
            },
            subtitle: {
                text: $("#yearSelect2").val() + " 年"
            },
            xAxis: {
//          type: 'datetime',
//          dateTimeLabelFormats: { // don't display the dummy year
//            day: '%m' + "月" + '%e' + '日'
//          }
                categories: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
                title: {
                    text: "时间:月"
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: "单位: 元 RMB"
                },
                alternateGridColor: '#FDFFD5'
            },
            plotOptions: {
                series: {
                    borderWidth: 0,
                    dataLabels: {
                        enabled: true,
                        format: '{point.y:.2f}'
                    },
                    enableMouseTracking: false
                }
            },
            legend: {
                enabled: true
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} 元 RMB</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            series: [
                {
                    name: "收入",
                    color: '#029402'
                },
                {
                    name: "支出",
                    color: '#ff0000'
                }
            ]

        };

        $.getJSON('getMonthBill.do', {
            year: $("#yearSelect2").val()
        }, function (json) {
            var datasA = [];
            var datasB = [];
            var categories = [];
            $.each(json, function (date, value) {
                if (value.type == 1) {
                    datasA.push([value.month, value.amount]);
                } else if (value.type == 2) {
                    datasB.push([value.month, value.amount]);
                }
                if ($.inArray(value.month, categories) <= 0) {
                    categories.push(value.month);
                }
            });
            options.series[0].data = datasA;
            options.series[1].data = datasB;
            options.xAxis.categories = categories;
            $('#chartContainer2').highcharts(options);
        });

    }

    function initDate(){
      var mydate = new Date();

      var year = mydate.getFullYear(); //获取当前年份(2位)
      var month = mydate.getMonth()+1; //获取当前月份(0-11,0代表1月)
      $("#yearSelect > option[value=" + year + "]").attr("selected","selected");
      $("#monthSelect > option[value=" + month + "]").attr("selected","selected");
      $("#yearSelect2 > option[value=" + year + "]").attr("selected","selected");
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
            <li class="active"><a href="moneyStatistics.do">金额统计</a></li>
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
            <h2>金额统计</h2>
          </div>
        </div>
        <!-- End .inner-padding -->
      </header>
      <!-- End #header-sec -->


      <div class="window">
        <div class="actionbar">
          <div class="pull-left">
            <ul class="ext-tabs">
              <li class="active">
                <a href="#content-tab-1">月度统计</a>
              </li>
              <li>
                <a href="#content-tab-2">年度统计</a>
              </li>
            </ul>
          </div>
          <div class="pull-right">
            <a class="btn" href="#" id="lockscreen-slider-trigger">
              <i class="fa fa-lock"></i>
            </a>
            <a data-toggle-sidebar="right" class="btn small-toggle-btn" href="#"></a>
          </div>
        </div>
        <!-- End .actionbar-->
        <div class="tab-content">
          <div id="content-tab-1" class="tab-pane active">
            <div class="inner-padding">
              <div class="toolbar responsive-helper">
                <form>
                  <div class="pull-left">
                    <div class="toolbar-field">
                      <strong>时间:</strong>
                    </div>

                    <div class="toolbar-field">
                      <div class="row">
                        <div class="col-md-4">
                          <select id="yearSelect" name="year" class="form-control input-sm">
                            <c:forEach var="i" begin="2015" end="2050">
                              <option <c:if test="${year==i}"> selected="selected"</c:if> value="${i}"> ${i}</option>
                            </c:forEach>
                          </select>
                        </div>
                        <div class="col-md-2"><strong>年</strong></div>
                        <div class="col-md-4">
                          <select id="monthSelect" name="month" class="form-control input-sm">
                            <c:forEach var="i" begin="1" end="12">
                              <option <c:if test="${month==i}"> selected="selected"</c:if> value="${i}">${i}</option>
                            </c:forEach>
                          </select>
                        </div>
                        <div class="col-md-2"><strong>月</strong></div>


                      </div>

                    </div>

                  </div>
                  <!-- End .pull-left -->
                  <div class="pull-right">
                    <div class="toolbar-field">
                      <button type="button" class="btn btn-default" onclick="reloadCharts1();"><i class="fa fa-search"></i>查询</button>
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
                    <h3>月度统计图表</h3>
                  </div>
                </header>
              </div>
              <div class="table-wrapper">
                <div id="chartContainer" style="min-width:90% ;max-width: 95%; height: 420px; margin: 0 auto"></div>
              </div>

            </div>
          </div>
          <div id="content-tab-2" class="tab-pane">
            <div class="inner-padding">
              <div class="toolbar responsive-helper">
                <form>
                  <div class="pull-left">
                    <div class="toolbar-field">
                      <strong>时间:</strong>
                    </div>

                    <div class="toolbar-field">
                      <div class="row">
                        <div class="col-md-8">
                          <select id="yearSelect2" name="year" class="form-control input-sm">
                            <c:forEach var="i" begin="2015" end="2050">
                              <option <c:if test="${year==i}"> selected="selected"</c:if> value="${i}"> ${i}</option>
                            </c:forEach>
                          </select>
                        </div>
                        <div class="col-md-2"><strong>年</strong></div>


                      </div>

                    </div>

                  </div>
                  <!-- End .pull-left -->
                  <div class="pull-right">
                    <div class="toolbar-field">
                      <button type="button" class="btn btn-default" onclick="reloadCharts2();"><i class="fa fa-search"></i>查询</button>
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
                    <h3>月度统计图表</h3>
                  </div>
                </header>
              </div>
              <div class="table-wrapper">
                <div id="chartContainer2" style="min-width:90% ;max-width: 95%; height: 420px; margin: 0 auto"></div>
              </div>

            </div>
          </div>
        </div>


        <div class="spacer-30"></div>
        <div class="hr-totop"><span>Top</span></div>
        <div class="spacer-10"></div>

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
