<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <!--<script src="../js/zoomify/zoomify.js"></script>-->
    <script src="../js/zoomify/jquery-1.4.4.min.js"></script>
    <script src="../js/zoomify/jquery.jqprint-0.3.js"></script>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link rel="stylesheet" type="text/css" href="../css/baodan.css"/>

    <title></title>

    <script type="text/javascript">
        function daYin() {
            $("#prints").jqprint();
        }
    </script>
</head>
<body>
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
        <!-- End #header-main-bottom -->
    </header>

    <div id="content" class="clearfix">


        <header id="header-sec">
        </header>
        <div class="window">
            <div class="actionbar">
                <div class="pull-left">
                    <ul class="ext-tabs">
                        <li class="active">
                            <a class="btn btn-default pull-right"
                                                                 href="javascript:void(0);" onclick="daYin()">打印</a>
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
                <div style="position: absolute">

                </div>
                <div class="div-tab">
                    <div class="div-line">
                        <div class="div-line-con div-row-2">
                            <div class="title">A 1.姓名</div>
                            <input class="a-input" type="text" value="${insuranceOrder.insuranceOrderInsured.insuredName}"/>
                        </div>
                        <div class="div-line-con div-sex">
                            <div class="title">A 2.性别</div>
                            <div>
                                <input class="i-cb" type="checkbox"/>
                                男
                                <input class="i-cb" style="margin-left: 30px; " type="checkbox"/>
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
                        <div class="div-line-con div-row-5">
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
                                <input type="text" style="border-bottom: #000000 solid 1px;width: 40px"/>
                            </div>
                        </div>
                        <div class="div-line-con">
                            <div class="title">A 5.证件号码</div>
                        </div>
                        <div style="display: inline-block;float: right;margin-top: 1px;margin-right: 1px;height: 20px">
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                        </div>
                    </div>

                    <div class="div-line">
                        <div class="div-line-con div-row-5">
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
                            <div class="title">A 11.国籍、户籍</div>
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
                        <div class="div-line-con div-row-5">
                            <div class="title">A 5.婚姻状况</div>
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
                            <div class="title">A 11.固定电话（<span style="font-size: 1px">区号<input class="b-input"
                                                                                               type="text"/></span>)-(<input
                                    class="mobile-input" type="text"/></span>)
                            </div>
                        </div>
                        <div class="div-line-con div-row-3">
                            <div class="title">A 12.移动电话</div>
                            <input class="a-input" type="text"/>
                        </div>
                    </div>

                    <div class="div-line">
                        <div class="div-line-con div-row-7">
                            <div class="title">A 10.住址</div>
                            <div>
                                <input class="b-input" type="text"/>
                                省
                                <input class="b-input" type="text"/>
                                市
                                <input class="b-input" type="text"/>
                                区、县
                                <input class="d-input" type="text"/>
                            </div>
                        </div>
                        <div class="div-line-con div-postcode">
                            邮编
                            <div style="margin-left: 20px;font-size: 0">
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                            </div>
                        </div>
                    </div>
                    <div class="div-line">
                        <div class="div-line-con div-row-5">
                            <div class="title">A 5.单位名称</div>
                            <input class="a-input" type="text"/>
                        </div>
                        <div class="div-line-con div-row-3">
                            <div class="title">A 11.职业</div>
                            <input class="a-input" type="text"/>
                        </div>
                        <div class="div-line-con div-row-3">
                            <div class="title">A 11.年收入</div>
                            <input class="a-input" type="text"/>
                        </div>
                    </div>
                    <div class="div-line">
                        <div class="div-line-con div-row-7">
                            <div class="title">A 10.单位地址</div>
                            <div>
                                <input class="b-input" type="text"/>
                                省
                                <input class="b-input" type="text"/>
                                市
                                <input class="b-input" type="text"/>
                                区、县
                                <input class="d-input" type="text"/>
                            </div>
                        </div>
                        <div class="div-line-con div-postcode">
                            邮编
                            <div style="margin-left: 20px;font-size: 0">
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                            </div>
                        </div>
                    </div>
                    <div class="div-line">
                        <div class="div-line-con div-career">
                            <div class="title">A 5.职业代码</div>
                            <input class="b-input" style="border-bottom: #000000 solid 1px" type="text"/>
                            <div class="title">职业类别</div>
                            <input class="b-input" style="border-bottom: #000000 solid 1px" type="text"/>
                        </div>
                        <div class="div-line-con div-email">
                            <div class="title">A 11.职业</div>
                            <input class="a-input" type="text"/>
                        </div>
                        <div class="div-line-con div-part-job">
                            <div class="title">A 11.是否有兼职？</div>
                            <input class="i-cb" style="margin-left: 20px" type="checkbox"/>
                            是
                            <input class="i-cb" type="checkbox"/>
                            否，如是请说明：
                            <input class="c-input" type="text"/>
                        </div>
                    </div>
                </div>
                <div>
                    投保人资料（如投保人为保险人本人，可免填本栏）
                </div>
                <div class="div-tab">
                    <div class="div-line">
                        <div class="div-line-con div-row-2">
                            <div class="title">A 1.姓名</div>
                            <input class="a-input" type="text"/>
                        </div>
                        <div class="div-line-con div-sex">
                            <div class="title">A 2.性别</div>
                            <div>
                                <input class="i-cb" type="checkbox"/>
                                男
                                <input class="i-cb" style="margin-left: 30px; " type="checkbox"/>
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
                        <div class="div-line-con div-row-5">
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
                        <div style="display: inline-block;float: right;margin-top: 1px;margin-right: 1px;height: 20px">
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                            <div class="div-line-cert"></div>
                        </div>
                    </div>

                    <div class="div-line">
                        <div class="div-line-con div-row-5">
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
                            <div class="title">A 11.国籍、户籍</div>
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
                        <div class="div-line-con div-row-5">
                            <div class="title">A 5.婚姻状况</div>
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
                            <div class="title">A 11.固定电话（<span style="font-size: 1px">区号<input class="b-input"
                                                                                               type="text"/></span>)-(<input
                                    class="mobile-input" type="text"/></span>)
                            </div>
                        </div>
                        <div class="div-line-con div-row-3">
                            <div class="title">A 12.移动电话</div>
                            <input class="a-input" type="text"/>
                        </div>
                    </div>

                    <div class="div-line">
                        <div class="div-line-con div-row-7">
                            <div class="title">A 10.住址</div>
                            <div>
                                <input class="b-input" type="text"/>
                                省
                                <input class="b-input" type="text"/>
                                市
                                <input class="b-input" type="text"/>
                                区、县
                                <input class="d-input" type="text"/>
                            </div>
                        </div>
                        <div class="div-line-con div-postcode">
                            邮编
                            <div style="margin-left: 20px;font-size: 0">
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                            </div>
                        </div>
                    </div>
                    <div class="div-line">
                        <div class="div-line-con div-row-5">
                            <div class="title">A 5.单位名称</div>
                            <input class="a-input" type="text"/>
                        </div>
                        <div class="div-line-con div-row-3">
                            <div class="title">A 11.职业</div>
                            <input class="a-input" type="text"/>
                        </div>
                        <div class="div-line-con div-row-3">
                            <div class="title">A 11.年收入</div>
                            <input class="a-input" type="text"/>
                        </div>
                    </div>
                    <div class="div-line">
                        <div class="div-line-con div-row-7">
                            <div class="title">A 10.单位地址</div>
                            <div>
                                <input class="b-input" type="text"/>
                                省
                                <input class="b-input" type="text"/>
                                市
                                <input class="b-input" type="text"/>
                                区、县
                                <input class="d-input" type="text"/>
                            </div>
                        </div>
                        <div class="div-line-con div-postcode">
                            邮编
                            <div style="margin-left: 20px;font-size: 0">
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                                <input class="i-cert-code" style="border: #000000 solid 1px !important"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End #content -->
    </div>
    <!-- End #main -->
</div>

</body>
</html>
