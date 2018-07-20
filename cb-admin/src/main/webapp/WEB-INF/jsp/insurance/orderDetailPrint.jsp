<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <!--<script src="../js/zoomify/zoomify.js"></script>-->
    <script src="../js/zoomify/jquery-3.3.1.min.js" type="text/javascript"></script>
    <script src="../js/district/district.js" type="text/javascript"></script>
    <script src="../js/profession/profession.js" type="text/javascript"></script>
    <script src="../js/zoomify/jquery.jqprint-0.3.js"></script>
    <script src="../js/zoomify/html2canvas.js"></script>
    <script src="../js/zoomify/jsPdf.debug.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.1.0.js"></script>

    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link rel="stylesheet" type="text/css" href="../css/insurance.css"/>
    <link rel="stylesheet" type="text/css" href="../css/prints.css">

    <title></title>
    <script type="text/javascript">
        var insuredProvince = $.citySelector.getProvince('${map.insuranceOrder.insuranceOrderInsured.insuredProvince}');
        insuredProvince = insuredProvince.replace("省", "").replace("市", "");
        var insuredCity = $.citySelector.getCity(${map.insuranceOrder.insuranceOrderInsured.insuredCity});
        insuredCity = insuredCity.replace("市", "");
        var insuredDistrict = $.citySelector.getDistrict(${map.insuranceOrder.insuranceOrderInsured.insuredDistrict});
        insuredDistrict = insuredDistrict.replace("区", "").replace("县", "");
        var policyholderProvince = $.citySelector.getProvince(${map.insuranceOrder.insuranceOrderPolicyholder.policyholderProvince});
        policyholderProvince = policyholderProvince.replace("省", "").replace("市", "");
        var policyholderCity = $.citySelector.getCity(${map.insuranceOrder.insuranceOrderPolicyholder.policyholderCity});
        policyholderCity = policyholderCity.replace("市", "");
        var policyholderDistrict = $.citySelector.getDistrict(${map.insuranceOrder.insuranceOrderPolicyholder.policyholderDistrict});
        policyholderDistrict = policyholderDistrict.replace("区", "").replace("县", "");
        var insuredCareer = $.profession.getProfession('${map.insuranceOrder.insuranceOrderInsured.insuredCareer}');
        var policyholderCareer = $.profession.getProfession('${map.insuranceOrder.insuranceOrderPolicyholder.policyholderCareer}');
        //  $(document).ready(function () {

        $(function () {

            $("#insuredProvince").val(insuredProvince);

            $("#insuredCity").val(insuredCity);

            $("#insuredDistrict").val(insuredDistrict);

            $("#policyholderProvince").val(policyholderProvince);
            $("#policyholderCity").val(policyholderCity);
            $("#policyholderDistrict").val(policyholderDistrict);

            $("#policyholderProvinces").val(policyholderProvince);
            $("#policyholderCitys").val(policyholderCity);
            $("#policyholderDistricts").val(policyholderDistrict);

            $("#insuredCareer").html(insuredCareer);

            $("#policyholderCareer").val(policyholderCareer);

        });


        function jqPrints() {
            // $(".hidden-prints").css("display", "none");
            $(".prints").jqprint();
            // $(".hidden-prints").css("display", "block");
        }
        function downPdf(){
            html2canvas(
                document.getElementById("exportToPdf"),
                {
                    dpi: 172,//导出pdf清晰度
                    onrendered: function (canvas) {
                        var contentWidth = canvas.width;
                        var contentHeight = canvas.height;

                        //一页pdf显示html页面生成的canvas高度;
                        var pageHeight = contentWidth / 592.28 * 841.89;
                        //未生成pdf的html页面高度
                        var leftHeight = contentHeight;
                        //pdf页面偏移
                        var position = 0;
                        //html页面生成的canvas在pdf中图片的宽高（a4纸的尺寸[595.28,841.89]）
                        var imgWidth = 595.28;
                        var imgHeight = 592.28 / contentWidth * contentHeight;

                        var pageData = canvas.toDataURL('image/jpeg', 1.0);
                        var pdf = new jsPDF('', 'pt', 'a4');

                        //有两个高度需要区分，一个是html页面的实际高度，和生成pdf的页面高度(841.89)
                        //当内容未超过pdf一页显示的范围，无需分页
                        if (leftHeight < pageHeight) {
                            pdf.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight);
                        } else {
                            while (leftHeight > 0) {
                                pdf.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight)
                                leftHeight -= pageHeight;
                                position -= 841.89;
                                //避免添加空白页
                                if (leftHeight > 0) {
                                    pdf.addPage();
                                }
                            }
                        }
                        pdf.save('content.pdf');
                    },
                    //背景设为白色（默认为黑色）
                    background: "#fff"
                })

        }
        // var downPdf = document.getElementById("exportToPdf");
        // downPdf.onclick = function () {
        //     html2canvas(
        //         document.getElementById("exportToPdf"),
        //         {
        //             dpi: 172,//导出pdf清晰度
        //             onrendered: function (canvas) {
        //                 var contentWidth = canvas.width;
        //                 var contentHeight = canvas.height;
        //
        //                 //一页pdf显示html页面生成的canvas高度;
        //                 var pageHeight = contentWidth / 592.28 * 841.89;
        //                 //未生成pdf的html页面高度
        //                 var leftHeight = contentHeight;
        //                 //pdf页面偏移
        //                 var position = 0;
        //                 //html页面生成的canvas在pdf中图片的宽高（a4纸的尺寸[595.28,841.89]）
        //                 var imgWidth = 595.28;
        //                 var imgHeight = 592.28 / contentWidth * contentHeight;
        //
        //                 var pageData = canvas.toDataURL('image/jpeg', 1.0);
        //                 var pdf = new jsPDF('', 'pt', 'a4');
        //
        //                 //有两个高度需要区分，一个是html页面的实际高度，和生成pdf的页面高度(841.89)
        //                 //当内容未超过pdf一页显示的范围，无需分页
        //                 if (leftHeight < pageHeight) {
        //                     pdf.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight);
        //                 } else {
        //                     while (leftHeight > 0) {
        //                         pdf.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight)
        //                         leftHeight -= pageHeight;
        //                         position -= 841.89;
        //                         //避免添加空白页
        //                         if (leftHeight > 0) {
        //                             pdf.addPage();
        //                         }
        //                     }
        //                 }
        //                 pdf.save('content.pdf');
        //             },
        //             //背景设为白色（默认为黑色）
        //             background: "#fff"
        //         })
        // }

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
                    <%--<ul class="ext-tabs">--%>
                        <%--<li class="active">--%>
                            <%--<a class="btn btn-default pull-right"--%>
                               <%--href="javascript:void(0);" onclick="jqPrints()">打印</a>--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                </div>
                <div class="pull-right">
                    <a class="btn" href="#" id="lockscreen-slider-trigger">
                        <i class="fa fa-lock"></i>
                    </a>
                    <a data-toggle-sidebar="right" class="btn small-toggle-btn" href="#"></a>
                </div>
            </div><!-- End .actionbar-->


            <div class="th-tab prints" id="exportToPdf" onclick="downPdf()">
                <%--第一页--%>
                <div style="height: 356px;width: 100%;z-index: 20;position: absolute;"></div>
                <div class="hidden-prints">
                    <div style="height: 1488px;">
                        <div class="header">
                            <img src="../images/orderHeader/newlogo.png" width="729px" height="92px"/>
                            <%--<h1 style="font-size: 40px">富德生命人寿保险股份有限公司</h1>--%>
                            <%--<h2>FUNDE SINO LIFE INSUANCE CO.,LTD.</h2>--%>
                            <h2>个人保险投保单（经代渠道）</h2>
                            <div class="header-contract">
                                <div  style="width: 400px;float: left;display: inline-block;margin-top: 10px;"></div>
                                <div class="contract" style="width: 450px;"></div>
                                <div class="contract">
                                    <p>（本单所示金额单位：人民币元）</p>
                                    <div class="contract-num">
                                        <div style="width: 100px;vertical-align: text-bottom;margin-left: 5px;border: 0;">
                                            保险合同号码
                                        </div>
                                        <div></div>
                                        <div></div>
                                        <div></div>
                                        <div></div>
                                        <div></div>
                                        <div></div>
                                        <div></div>
                                        <div></div>
                                        <div></div>
                                        <div></div>
                                        <div></div>
                                        <div></div>
                                        <div></div>
                                        <div></div>
                                        <div></div>
                                        <div style="padding-right: 4px;"></div>
                                    </div>
                                    <p>本次同时投保共<input/>单，第<input/>单</p>
                                </div>
                                <div class="info">
                                    <div style="display: inline-block;vertical-align: top;">
                                        投保须知:
                                    </div>
                                    <div style="display: inline-block">
                                        1、请您在仔细阅读人身保险投保提示书、产品说明书、保险条款后用蓝、黑墨水笔填写本投保单，并在合适的回答方框内打√。
                                        <br/>2、您必须在此投保单上真实填写一切有关事实，并亲笔签名。保险合同将以此为依据，否则可能影响所签合同的法律效力。
                                        <br/>3、富德生命人寿保险股份有限公司承诺未经客户同意，不会将客户信息用于人身保险公司和第三方机构的销售活动。
                                    </div>
                                    <div style="float: right;color: green;margin-top: 35px;">绿色栏由业务员填写。
                                    </div>
                                </div>
                                <div style="margin-left: 5px;font-weight: bold;font-size: 18px;">A.基本资料</div>
                                <div class="div-title">被保险人资料</div>
                            </div>
                        </div>
                        <div class="div-tab">
                            <div class="div-line">
                                <div class="div-line-con div-row-2">
                                    <div class="title">A 1.姓名&nbsp;${map.insuranceOrder.insuranceOrderInsured.insuredName}</div>
                                </div>
                                <div class="div-line-con div-sex">
                                    <div class="title">A 2.性别</div>
                                    <div>
                                        <input readonly class="i-cb" type="checkbox" style="margin-left: 15px;" <c:if test="${map.insuranceOrder.insuranceOrderInsured.insuredGender=='true'}">checked</c:if>/>
                                        男
                                        <input readonly class="i-cb" style="margin-left: 35px; " type="checkbox"  <c:if test="${map.insuranceOrder.insuranceOrderInsured.insuredGender=='false'}">checked</c:if>/>
                                        女
                                    </div>
                                </div>
                                <div class="div-line-con div-row-4">
                                    <div class="title">A 3.出生日期</div>
                                    <div style="padding-left: 40px;margin-left: 30px;">
                                        <input readonly class="b-input" type="text" value="${map.insurance_b_year}"/>
                                        年
                                        <input readonly class="b-input" type="text" value="${map.insurance_b_month}"/>
                                        月
                                        <input readonly class="b-input" type="text" value="${map.insurance_b_day}"/>
                                        日
                                    </div>
                                </div>
                                <div class="div-line-con div-row-2">
                                    <div class="title">A 4.年龄</div>
                                    <input readonly class="c-input" type="text" value="${map.age}"/>
                                    <div>周岁</div>
                                </div>
                            </div>

                            <div class="div-line">
                                <div class="div-line-con div-row-5">
                                    <div class="title">A 5.证件类型</div>
                                    <div>
                                        <input readonly class="i-cb" type="checkbox" <c:if test="${map.insuranceOrder.insuranceOrderInsured.insuredMarriage=='居民身份证'}">checked</c:if>/>
                                        身份证
                                        <input readonly class="i-cb" type="checkbox" style="margin-left:15px; " <c:if test="${map.insuranceOrder.insuranceOrderInsured.insuredMarriage=='护照'}">checked</c:if>/>
                                        护照
                                        <input readonly class="i-cb" type="checkbox" style="margin-left:15px; " <c:if test="${map.insuranceOrder.insuranceOrderInsured.insuredMarriage=='军官证'}">checked</c:if>/>
                                        军官证
                                        <input readonly class="i-cb" type="checkbox" style="margin-left:15px; " <c:if test="${map.insuranceOrder.insuranceOrderInsured.insuredMarriage!='军官证'&&map.insuranceOrder.insuranceOrderInsured.insuredMarriage!='护照'&&map.insuranceOrder.insuranceOrderInsured.insuredMarriage!='居民身份证'}">checked</c:if>/>
                                        其他
                                    </div>
                                </div>
                                <div class="div-line-con">
                                    <div class="title">A 6.证件号码</div>
                                </div>
                                <div style="display: inline-block;float: right;margin-top: 1px;margin-right: 1px;height: 20px">
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderInsured.insuredCardNo, 0, 1)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderInsured.insuredCardNo, 1, 2)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderInsured.insuredCardNo, 2, 3)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderInsured.insuredCardNo, 3, 4)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderInsured.insuredCardNo, 4, 5)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderInsured.insuredCardNo, 5, 6)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderInsured.insuredCardNo, 6, 7)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderInsured.insuredCardNo, 7, 8)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderInsured.insuredCardNo, 8, 9)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderInsured.insuredCardNo, 9, 10)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderInsured.insuredCardNo, 10, 11)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderInsured.insuredCardNo, 11, 12)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderInsured.insuredCardNo, 12, 13)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderInsured.insuredCardNo, 13, 14)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderInsured.insuredCardNo, 14, 15)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderInsured.insuredCardNo, 15, 16)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderInsured.insuredCardNo, 16, 17)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderInsured.insuredCardNo, 17, 18)}</div>
                                </div>
                            </div>

                            <div class="div-line">
                                <div class="div-line-con div-row-5">
                                    <div class="title">A 7.证件有效期</div>
                                    <div>
                                        <input readonly class="i-cb" type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_p_year!=null&&map.insurance_p_year!=''}">checked</c:if>/>
                                        有效期至
                                        <div>
                                            <input readonly class="c-input" type="text" style="margin-left: 10px;" value="${map.insurance_p_year}"/>
                                            年
                                            <input readonly class="c-input" type="text" value="${map.insurance_p_month}"/>
                                            月
                                            <input readonly class="c-input" type="text" value="${map.insurance_p_day}"/>
                                            日
                                        </div>
                                        <input readonly class="i-cb" style="margin-left: 15px" type="checkbox" ${map.insurance_p_year==null||map.insurance_p_year==''}/>
                                        长期
                                    </div>
                                </div>
                                <div class="div-line-con div-row-3">
                                    <div class="title">A 8.国籍、户籍</div>
                                    <input readonly class="a-input" type="text" value="${map.insuranceOrder.insuranceOrderInsured.insuredCountry}"/>
                                </div>
                                <div class="div-line-con div-row-3">
                                    <div class="title">A 9.身高</div>
                                    <input readonly class="b-input" type="text" style="margin-left: 10px;" value="${map.insuranceOrder.insuranceOrderInsured.insuredHeight}"/>
                                    厘米，
                                    <div class="title">体重</div>
                                    <input readonly class="b-input" type="text" style="margin-left: 5px;" value="${map.insuranceOrder.insuranceOrderInsured.insuredBodyWeight}"/>
                                    公斤
                                </div>
                            </div>
                            <div class="div-line">
                                <div class="div-line-con div-row-5">
                                    <div class="title">A 10.婚姻状况</div>
                                    <div>
                                        <input readonly class="i-cb" type="checkbox" style="margin-left: 14px;" <c:if test="${map.insuranceOrder.insuranceOrderInsured.insuredMarriage=='未婚'}">checked</c:if>/>
                                        未婚
                                        <input readonly class="i-cb" type="checkbox" style="margin-left: 20px;" <c:if test="${map.insuranceOrder.insuranceOrderInsured.insuredMarriage=='已婚'}">checked</c:if>/>
                                        已婚
                                        <input readonly class="i-cb" type="checkbox" style="margin-left: 20px;" <c:if test="${map.insuranceOrder.insuranceOrderInsured.insuredMarriage=='离异'}">checked</c:if>/>
                                        离异
                                        <input readonly class="i-cb" type="checkbox" style="margin-left: 25px;" <c:if test="${map.insuranceOrder.insuranceOrderInsured.insuredMarriage=='丧偶'}">checked</c:if>/>
                                        丧偶
                                    </div>
                                </div>
                                <div class="div-line-con div-row-3">
                                    <div class="title">A 11.固定电话（<input readonly class="b-input" type="text" value="${map.insurance_q_tel}"/>)-(<input
                                            class="mobile-input" type="text" value="${map.insurance_h_tel}"/></span>)
                                    </div>
                                </div>
                                <div class="div-line-con div-row-3">
                                    <div class="title">A 12.移动电话</div>
                                    <input readonly class="a-input" type="text" value="${map.insuranceOrder.insuranceOrderInsured.insuredMobile}"/>
                                </div>
                            </div>

                            <div class="div-line">
                                <div class="div-line-con div-row-7">
                                    <div class="title">A 13.住址</div>
                                    <div>
                                        <input readonly class="b-input" type="text" style="margin-left: 10px;" id="insuredProvince"/>
                                        省
                                        <input readonly class="b-input" type="text" style="margin-left: 10px;" id="insuredCity"/>
                                        市
                                        <input readonly class="b-input" type="text" style="margin-left: 25px;" id="insuredDistrict"/>
                                        区/县
                                        <div style="vertical-align: top">
                                            <div style="width: 380px;position: absolute;word-break: break-all;">${map.insuranceOrder.insuranceOrderInsured.insuredAddress}</div>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line-con div-postcode" style="margin-right: 40px;">
                                    邮编
                                    <div style="margin-left: 20px;font-size: 0;margin-top: -10px;">
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="5"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="1"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="8"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="0"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="0"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="0"/>
                                    </div>
                                </div>
                            </div>
                            <div class="div-line">
                                <div class="div-line-con div-row-5">
                                    <div class="title">A 14.单位名称</div>
                                    <input readonly class="a-input" type="text"/>
                                </div>
                                <div class="div-line-con div-row-3">
                                    <div class="title" style="float: left;">A 15.职业&nbsp;</div>
                                    <%--<input readonly class="a-input" type="text" value="" id=""/>--%>
                                    <div  style="float: left;width: 200px;font-size: 10px;position: absolute" id="insuredCareer"></div>
                                </div>
                                <div class="div-line-con div-row-3">
                                    <div class="title">A 16.年均收入</div>
                                    <input readonly class="a-input" type="text" value="${map.insuranceOrder.insuranceOrderInsured.insuredIncome}"/>
                                </div>
                            </div>
                            <div class="div-line">
                                <div class="div-line-con div-row-7">
                                    <div class="title">A 17.单位地址</div>
                                    <div>
                                        <input readonly class="b-input" type="text"/>
                                        省
                                        <input readonly class="b-input" type="text" style="margin-left: 10px;"/>
                                        市
                                        <input readonly class="b-input" type="text" style="margin-left: 25px;"/>
                                        区/县
                                        <div style="vertical-align: top">
                                            <div style="width: 380px;position: absolute;word-break: break-all;"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line-con div-postcode" style="margin-right: 40px;">
                                    邮编
                                    <div style="margin-left: 20px;font-size: 0;margin-top: -10px;">
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="5"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important"  value="1"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important"  value="8"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important"  value="0"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important"  value="0"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important"  value="0"/>
                                    </div>
                                </div>
                            </div>
                            <div class="div-line">
                                <div class="div-line-con div-career">
                                    <div class="title">A 18.职业代码</div>
                                    <input readonly class="b-input" style="border-bottom: #000000 solid 1px" type="text"/>
                                    <div class="title" style="margin-left: 25px;">职业类别</div>
                                </div>
                                <div class="div-line-con div-email">
                                    <div class="title">A 19.&nbsp;E-mail</div>
                                    <input readonly class="a-input" type="text" value="${map.insuranceOrder.insuranceOrderInsured.insuredEmail}"/>
                                </div>
                                <div class="div-line-con div-part-job">
                                    <div class="title">A 20.是否有兼职？</div>
                                    <input readonly class="i-cb" style="margin-left: 15px" type="checkbox"/>
                                    是
                                    <input readonly class="i-cb" style="margin-left: 15px" type="checkbox"/>
                                    否，如是请说明：
                                </div>
                            </div>
                        </div>
                        <div class="div-title">
                            投保人资料（如投保人为被保险人本人，可免填本栏）
                        </div>
                        <div class="div-tab">
                            <div class="div-line">
                                <div class="div-line-con div-row-2">
                                    <div class="title">A 21.姓名&nbsp;${map.insuranceOrder.insuranceOrderPolicyholder.policyholderName}</div>

                                </div>
                                <div class="div-line-con div-sex">
                                    <div class="title">A 22.性别</div>
                                    <div>
                                        <input readonly class="i-cb" type="checkbox" style="margin-left: 10px; " <c:if test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderGender=='true'}">checked</c:if>/>
                                        男
                                        <input readonly class="i-cb" style="margin-left: 30px; " type="checkbox" <c:if test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderGender=='false'}">checked</c:if>/>
                                        女
                                    </div>
                                </div>
                                <div class="div-line-con div-row-4">
                                    <div class="title">A 23.出生日期</div>
                                    <div style="padding-left: 0; margin-left: 55px;">
                                        <input readonly class="b-input" type="text" value="${map.policy_b_year}"/>
                                        年
                                        <input readonly class="b-input" type="text" value="${map.policy_b_month}"/>
                                        月
                                        <input readonly class="b-input" type="text" value="${map.policy_b_day}"/>
                                        日
                                    </div>
                                </div>
                                <div class="div-line-con div-row-2">
                                    <div class="title">A 24.是被保险人的(<span style="font-size: 8px;">关系</span>)</div>
                                    <input readonly class="c-input" type="text" style="" value="${map.insuranceOrder.insuranceOrderInsured.insuredRelation}"/>
                                    <div></div>
                                </div>
                            </div>

                            <div class="div-line">
                                <div class="div-line-con div-row-5">
                                    <div class="title">A 25.证件类型</div>
                                    <div>
                                        <input readonly class="i-cb" type="checkbox" <c:if test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='居民身份证'}">checked</c:if>/>
                                        身份证
                                        <input readonly class="i-cb" type="checkbox" style="margin-left:15px; "  <c:if test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='护照'}">checked</c:if>/>
                                        护照
                                        <input readonly class="i-cb" type="checkbox" style="margin-left:15px; "  <c:if test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='军官证'}">checked</c:if>/>
                                        军官证
                                        <input readonly class="i-cb" type="checkbox" style="margin-left:15px; "  <c:if test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage!='军官证'&&map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage!='护照'&&map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage!='居民身份证'}">checked</c:if>/>
                                        其他
                                    </div>
                                </div>
                                <div class="div-line-con">
                                    <div class="title">A 26.证件号码</div>
                                </div>
                                <div style="display: inline-block;float: right;margin-top: 1px;margin-right: 1px;height: 20px">
                                    <div class="div-line-cert">
                                        ${fn:substring(map.insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo, 0, 1)}
                                    </div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo, 1, 2)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo, 2, 3)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo, 3, 4)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo, 4, 5)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo, 5, 6)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo, 6, 7)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo, 7, 8)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo, 8, 9)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo, 9, 10)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo, 10, 11)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo, 11, 12)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo, 12, 13)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo, 13, 14)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo, 14, 15)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo, 15, 16)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo, 16, 17)}</div>
                                    <div class="div-line-cert">${fn:substring(map.insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo, 17, 18)}</div>
                                </div>
                            </div>

                            <div class="div-line">
                                <div class="div-line-con div-row-5">
                                    <div class="title">A 27.证件有效期</div>
                                    <div>
                                        <input readonly class="i-cb" type="checkbox" style="margin-left: 10px;" <c:if test="${map.policy_p_year!=null&&map.policy_p_year!=''}">checked</c:if>/>
                                        有效期至
                                        <div style="margin-left: 10px;">
                                            <input readonly class="c-input" type="text" value="${map.policy_p_year}"/>
                                            年
                                            <input readonly class="c-input" type="text" value="${map.policy_p_month}"/>
                                            月
                                            <input readonly class="c-input" type="text" value="${map.policy_p_day}"/>
                                            日
                                        </div>
                                        <input readonly class="i-cb" style="margin-left: 10px" type="checkbox" <c:if test="${map.policy_p_year==null&&map.policy_p_year==''}">checked</c:if>/>
                                        长期
                                    </div>
                                </div>
                                <div class="div-line-con div-row-3">
                                    <div class="title">A 28.国籍、户籍</div>
                                    <input readonly class="a-input" type="text" value="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderCountry}"/>
                                </div>
                                <div class="div-line-con div-row-3">
                                    <div class="title">A 29.身高</div>
                                    <input readonly class="b-input" type="text" style="margin-left: 10px;" value="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderHeight}"/>
                                    厘米，
                                    <div class="title">体重</div>
                                    <input readonly class="b-input" type="text" style="margin-left: 10px;" value="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderBodyWeight}"/>
                                    公斤
                                </div>
                            </div>
                            <div class="div-line">
                                <div class="div-line-con div-row-5">
                                    <div class="title">A 30.婚姻状况</div>
                                    <div>
                                        <input readonly class="i-cb" type="checkbox" style="margin-left: 15px;" <c:if test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='未婚'}">checked</c:if>/>
                                        未婚
                                        <input readonly class="i-cb" type="checkbox" style="margin-left: 23px;" <c:if test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='已婚'}">checked</c:if>/>
                                        已婚
                                        <input readonly class="i-cb" type="checkbox" style="margin-left: 23px;" <c:if test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='离异'}">checked</c:if>/>
                                        离异
                                        <input readonly class="i-cb" type="checkbox" style="margin-left: 23px;" <c:if test="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMarriage=='丧偶'}">checked</c:if>/>
                                        丧偶
                                    </div>
                                </div>
                                <div class="div-line-con div-row-3">
                                    <div class="title">A 31.固定电话（<input readonly class="b-input"
                                                                        type="text" value="${map.policy_q_tel}"/>)-(<input
                                            class="mobile-input" type="text" id="${map.policy_h_tel}"/></span>)
                                    </div>
                                </div>
                                <div class="div-line-con div-row-3">
                                    <div class="title">A 32.移动电话</div>
                                    <input readonly class="a-input" type="text" value="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMobile}"/>
                                </div>
                            </div>

                            <div class="div-line">
                                <div class="div-line-con div-row-7">
                                    <div class="title">A 33.住址</div>
                                    <div>
                                        <input readonly class="b-input" type="text" style="margin-left:40px; " id="policyholderProvince"/>
                                        省
                                        <input readonly class="b-input" type="text" style="margin-left: 30px;" id="policyholderCity"/>
                                        市
                                        <input readonly class="b-input" type="text" style="margin-left: 20px;" id="policyholderDistrict"/>
                                        区/县
                                        <div style="vertical-align: top">
                                            <div style="width: 380px;position: absolute;word-break: break-all;">${map.insuranceOrder.insuranceOrderPolicyholder.policyholderAddress}</div>
                                        </div>
                                        <%--<input readonly class="d-input" type="text" value=""/>--%>
                                    </div>
                                </div>
                                <div class="div-line-con div-postcode" style="margin-right: 40px;">
                                    邮编
                                    <div style="margin-left: 20px;font-size: 0;margin-top: -10px;">
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="5"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="1"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="8"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="0"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="0"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="0"/>
                                    </div>
                                </div>
                            </div>
                            <div class="div-line">
                                <div class="div-line-con div-row-5">
                                    <div class="title">A 34.单位名称</div>
                                    <input readonly class="a-input" type="text" value=""/>
                                </div>
                                <div class="div-line-con div-row-3">
                                    <div class="title"  style="float: left">A 35.职业&nbsp;</div>
                                    <div  style="float: left;width: 200px;font-size: 10px;"id="policyholderCareer"></div>
                                </div>
                                <div class="div-line-con div-row-3">
                                    <div class="title">A 36.年均收入</div>
                                    <input readonly class="a-input" type="text" value="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderIncome}"/>
                                </div>
                            </div>
                            <div class="div-line">
                                <div class="div-line-con div-row-7">
                                    <div class="title">A 37.单位地址</div>
                                    <div>
                                        <input readonly class="b-input" type="text"/>
                                        省
                                        <input readonly class="b-input" type="text" style="margin-left: 10px;"/>
                                        市
                                        <input readonly class="b-input" type="text" style="margin-left: 25px;"/>
                                        区/县
                                        <div style="vertical-align: top">
                                            <div style="width: 380px;position: absolute;word-break: break-all;"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line-con div-postcode" style="margin-right: 40px;">
                                    邮编
                                    <div style="margin-left: 20px;font-size: 0;margin-top: -10px;">
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="5"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="1"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="8"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="0"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="0"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="0"/>
                                    </div>
                                </div>
                            </div>
                            <div class="div-line">
                                <div class="div-line-con div-career">
                                    <div class="title">A 38.职业代码</div>
                                    <input readonly class="b-input" style="border-bottom: #000000 solid 1px" type="text"/>
                                    <div class="title" style="margin-left: 25px;">职业类别</div>
                                </div>
                                <div class="div-line-con div-email">
                                    <div class="title">A 39. E-mail</div>
                                    <input readonly class="a-input" type="text" value="${map.insuranceOrder.insuranceOrderPolicyholder.policyholderEmail}"/>
                                </div>
                                <div class="div-line-con div-part-job">
                                    <div class="title">A 40.其它联系电话</div>
                                    <input readonly class="a-input" type="text"/>
                                </div>
                            </div>
                        </div>
                        <div class="div-title">本单联系地址（如此栏空缺，将以投保人住址为准</div>
                        <div class="div-tab">
                            <div class="div-line">
                                <div class="div-line-con" style="padding: 7px 5px 7px 10px;">
                                    A 41. 信函寄往
                                    <div > <input readonly class="a-input" type="text" id="policyholderProvinces" style="width: 60px;"/>省</div>
                                    <div><input readonly class="a-input" type="text" id="policyholderCitys"  style="width: 80px;"/>市</div>
                                    <div><input readonly class="a-input" type="text" id="policyholderDistricts"  style="width: 70px;"/>区/县</div>
                                    &nbsp; ${map.insuranceOrder.insuranceOrderPolicyholder.policyholderAddress}
                                </div>
                                <div class="div-line-con div-postcode" style="margin-right: 120px;">
                                    电话&nbsp;${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMobile}
                                </div>
                                <div class="div-line-con div-postcode" style="margin-right:  35px;">
                                    邮编
                                    <div style="margin-left: 20px;font-size: 0;margin-top: -10px;">
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="5"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="1"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="8"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="0"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="0"/>
                                        <input readonly class="i-cert-code" style="border: #000000 solid 1px !important" value="0"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="div-title">短信服务（未选默认为不需要）</div>
                        <div class="div-tab">
                            <div class="div-line">
                                <div class="div-line-con div-row-6" style="padding: 7px 5px 7px 10px;">
                                    A 42. 投保人是否需要短信服务
                                    <div>
                                        <input readonly class="i-cb" type="checkbox"/>
                                        需要
                                        <input readonly class="i-cb" style="margin-left: 15px; " type="checkbox"/>
                                        不需要
                                    </div>
                                </div>
                                <div class="div-line-con">
                                    A 43. 移动电话&nbsp;${map.insuranceOrder.insuranceOrderPolicyholder.policyholderMobile}
                                </div>
                            </div>
                        </div>
                        <div class="div-title" style="line-height: 1.5;"><span>身故保险金受益人：</span>（投保养老年金保险产品需要指定剩余养老保险年金受益人时，请填写“投保与合同变更补充声明”告知剩余养老保险年金受益人，如未补充告知，则视剩余养老保险年金受益人与身故保险金受益人为同一人）
                            <br/>说明：1.指定受益人时，同一受益顺序的受益份额合计必须等于100%。2.后一受益顺序的受益人只有在前一受益顺序所有受益人丧失或放弃受益权后才能享有受益权。3.若未填写受益份额，同一顺序的保险金受益人按照相等份额享有保险金。4.若未指定受益人，或者受益人指定不明无法确定的，保险金将作为被保险人的遗产按照《中华人民共和国继承法》的规定进行分配。5.投保无身故保险利益的保险产品时无需填写本栏，填写亦视为无效。
                        </div>
                        <div class="div-tab" style="height: 240px;width: 1000px;">
                            <div class="table-line">
                                <div class="div-table-1">A 44.姓名</div>
                                <div class="div-table-1">A 45.性别</div>
                                <div class="div-table-1">
                                    <div style="display: inline-block;vertical-align: top;">A 46.</div>
                                    <div style="display: inline-block;">受益<br/>顺序</div>
                                </div>
                                <div class="div-table-1">
                                    <div style="display: inline-block;vertical-align: top;">A 47.</div>
                                    <div style="display: inline-block;">受益<br/>份额</div>
                                </div>
                                <div class="div-table-1">
                                    <div style="display: inline-block;vertical-align: top;">A 48.</div>
                                    <div style="display: inline-block;">出生<br/>日期</div>
                                </div>
                                <div class="div-table-1" style="width: 100px;">A 49.是被保险人的<span
                                        style="font-size: 10px;">（关系）</span></div>
                                <div class="div-table-2">A 50.证件类型及号码</div>
                            </div>
                            <div class="table-line">
                                <div class="div-table-1">${map.beneficiaryList[0].beneficiaryName}</div>
                                <div class="div-table-1">
                                    <c:if test="${map.beneficiaryList[0].beneficiaryGender=='true'}">男</c:if>
                                    <c:if test="${map.beneficiaryList[0].beneficiaryGender=='false'}">女</c:if>
                                </div>
                                <div class="div-table-1">${map.beneficiaryList[0].beneficiaryOrder}</div>
                                <div class="div-table-1">${map.beneficiaryList[0].beneficiaryProportion}</div>
                                <div class="div-table-1">
                                    <fmt:formatDate value="${map.beneficiaryList[0].beneficiaryBirthday}" pattern="yyyy-MM-dd" type="date" dateStyle="long"/>
                                </div>
                                <div class="div-table-1" style="width: 100px;">${map.beneficiaryList[0].insuredRelation}</div>
                                <div class="div-table-2">
                                    <div class="t-line" style="padding: 5px 6px;">
                                        <input readonly type="checkbox" <c:if test="${map.beneficiaryList[0].beneficiaryCardType=='居民身份证'}">checked</c:if>/>
                                        <div>身份证</div>
                                        <input readonly type="checkbox" style="margin-left: 15px;" <c:if test="${map.beneficiaryList[0].beneficiaryName!=null&&''!=map.beneficiaryList[0].beneficiaryName&&map.beneficiaryList[0].beneficiaryCardType!='居民身份证'}">checked</c:if>/>
                                        <div>其他</div>
                                        <div style="margin:0 60px;">有效期至
                                            <c:if test="${map.beneficiaryList[0].beneficiaryName!=null&&''!=map.beneficiaryList[0].beneficiaryName&&map.beneficiaryList[0].beneficiaryCardType!='长期'}">
                                                <fmt:formatDate value="${map.beneficiaryList[0].beneficiaryCardPeroid}"  pattern="yyyy-MM-dd" type="date" dateStyle="long"/>
                                            </c:if>
                                        </div>
                                        <input readonly class="i-cb" style="margin-left: 55px;" type="checkbox" <c:if test="${map.beneficiaryList[0].beneficiaryCardType=='长期'}">checked</c:if>/>
                                        长期
                                    </div>
                                    <div class="t-line" style="display: block;height: 31px;">
                                        <div style="display: inline-block;height: 30px;font-size: 0;border-top: #000000 solid 1px;width: 100%;margin:0 -1px;text-align: left">
                                            <div class="t-line-cert" style="border: 0">
                                                ${fn:substring(map.beneficiaryList[0].beneficiaryCardNo, 0, 1)}
                                            </div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[0].beneficiaryCardNo, 1, 2)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[0].beneficiaryCardNo, 2, 3)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[0].beneficiaryCardNo, 3, 4)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[0].beneficiaryCardNo, 4, 5)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[0].beneficiaryCardNo, 5, 6)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[0].beneficiaryCardNo, 6, 7)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[0].beneficiaryCardNo, 7, 8)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[0].beneficiaryCardNo, 8, 9)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[0].beneficiaryCardNo, 9, 10)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[0].beneficiaryCardNo, 10, 11)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[0].beneficiaryCardNo, 11, 12)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[0].beneficiaryCardNo, 12, 13)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[0].beneficiaryCardNo, 13, 14)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[0].beneficiaryCardNo, 14, 15)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[0].beneficiaryCardNo, 15, 16)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[0].beneficiaryCardNo, 16, 17)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[0].beneficiaryCardNo, 17, 18)}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="table-line">
                                <div class="div-table-1">${map.beneficiaryList[1].beneficiaryName}</div>
                                <div class="div-table-1">
                                    <c:if test="${map.beneficiaryList[1].beneficiaryGender=='true'}">男</c:if>
                                    <c:if test="${map.beneficiaryList[1].beneficiaryGender=='false'}">女</c:if>

                                </div>
                                <div class="div-table-1">${map.beneficiaryList[1].beneficiaryOrder}</div>
                                <div class="div-table-1">${map.beneficiaryList[1].beneficiaryProportion}</div>
                                <div class="div-table-1"><fmt:formatDate value="${map.beneficiaryList[1].beneficiaryBirthday}" pattern="yyyy-MM-dd" type="date" dateStyle="long"/></div>
                                <div class="div-table-1" style="width: 100px;">${map.beneficiaryList[1].insuredRelation}</div>
                                <div class="div-table-2">
                                    <div class="t-line" style="padding: 5px 6px;">
                                        <input readonly type="checkbox" <c:if test="${map.beneficiaryList[1].beneficiaryCardType=='居民身份证'}">checked</c:if>/>
                                        <div>身份证</div>
                                        <input readonly type="checkbox" style="margin-left: 15px;" <c:if test="${map.beneficiaryList[1].beneficiaryName!=null&&''!=map.beneficiaryList[1].beneficiaryName&&map.beneficiaryList[1].beneficiaryCardType!='居民身份证'}">checked</c:if>/>
                                        <div>其他</div>
                                        <div style="margin:0 60px;">有效期至
                                            <c:if test="${map.beneficiaryList[1].beneficiaryName!=null&&''!=map.beneficiaryList[1].beneficiaryName&&map.beneficiaryList[1].beneficiaryCardType!='长期'}">
                                                <fmt:formatDate value="${map.beneficiaryList[1].beneficiaryCardPeroid}"  pattern="yyyy-MM-dd" type="date" dateStyle="long"/>
                                            </c:if>
                                        </div>
                                        <input readonly class="i-cb" style="margin-left: 55px;" type="checkbox" <c:if test="${map.beneficiaryList[1].beneficiaryCardType=='长期'}">checked </c:if>/>
                                        长期
                                    </div>
                                    <div class="t-line" style="display: block;height: 31px;">
                                        <div style="display: inline-block;height: 30px;font-size: 0;border-top: #000000 solid 1px;border-left: 0;width: 100%;margin:0 -1px;text-align: left">
                                            <div class="t-line-cert" style="border: 0">
                                                ${fn:substring(map.beneficiaryList[1].beneficiaryCardNo, 0, 1)}
                                            </div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[1].beneficiaryCardNo, 1, 2)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[1].beneficiaryCardNo, 2, 3)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[1].beneficiaryCardNo, 3, 4)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[1].beneficiaryCardNo, 4, 5)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[1].beneficiaryCardNo, 5, 6)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[1].beneficiaryCardNo, 6, 7)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[1].beneficiaryCardNo, 7, 8)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[1].beneficiaryCardNo, 8, 9)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[1].beneficiaryCardNo, 9, 10)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[1].beneficiaryCardNo, 10, 11)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[1].beneficiaryCardNo, 11, 12)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[1].beneficiaryCardNo, 12, 13)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[1].beneficiaryCardNo, 13, 14)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[1].beneficiaryCardNo, 14, 15)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[1].beneficiaryCardNo, 15, 16)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[1].beneficiaryCardNo, 16, 17)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[1].beneficiaryCardNo, 17, 18)}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="table-line">
                                <div class="div-table-1">${map.beneficiaryList[2].beneficiaryName}</div>
                                <div class="div-table-1">
                                    <c:if test="${map.beneficiaryList[2].beneficiaryGender=='true'}">男</c:if>
                                    <c:if test="${map.beneficiaryList[2].beneficiaryGender=='false'}">女</c:if>


                                </div>
                                <div class="div-table-1"> ${map.beneficiaryList[2].beneficiaryOrder}</div>
                                <div class="div-table-1"> ${map.beneficiaryList[2].beneficiaryProportion}</div>
                                <div class="div-table-1"><fmt:formatDate value="${map.beneficiaryList[2].beneficiaryBirthday}" pattern="yyyy-MM-dd" type="date" dateStyle="long"/></div>
                                <div class="div-table-1" style="width: 100px;">${map.beneficiaryList[2].insuredRelation}</div>
                                <div class="div-table-2">
                                    <div class="t-line" style="padding: 6px;">
                                        <input readonly type="checkbox" <c:if test="${map.beneficiaryList[2].beneficiaryCardType=='居民身份证'}">checked</c:if>/>
                                        <div>身份证</div>
                                        <input readonly type="checkbox" style="margin-left: 15px;" <c:if test="${map.beneficiaryList[2].beneficiaryName!=null&&''!=map.beneficiaryList[2].beneficiaryName&&map.beneficiaryList[2].beneficiaryCardType!='居民身份证'}">checked</c:if>/>
                                        <div>其他</div>
                                        <div style="margin:0 60px;">有效期至
                                            <c:if test="${map.beneficiaryList[2].beneficiaryName!=null&&''!=map.beneficiaryList[2].beneficiaryName&&map.beneficiaryList[2].beneficiaryCardType!='长期'}">
                                                <fmt:formatDate value="${map.beneficiaryList[2].beneficiaryCardPeroid}"  pattern="yyyy-MM-dd" type="date" dateStyle="long"/>
                                            </c:if>
                                        </div>
                                        <input readonly class="i-cb" style="margin-left: 55px;" type="checkbox" <c:if test="${map.beneficiaryList[2].beneficiaryCardType=='长期'}">checked </c:if>/>
                                        长期
                                    </div>
                                    <div class="t-line" style="display: block;height: 31px;">
                                        <div style="display: inline-block;height: 30px;font-size: 0;border-top: #000000 solid 1px;width: 100%;margin:0 -1px;text-align: left">
                                            <div class="t-line-cert" style="border: 0">
                                                ${fn:substring(map.beneficiaryList[2].beneficiaryCardNo, 0, 1)}
                                            </div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[2].beneficiaryCardNo, 1, 2)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[2].beneficiaryCardNo, 2, 3)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[2].beneficiaryCardNo, 3, 4)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[2].beneficiaryCardNo, 4, 5)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[2].beneficiaryCardNo, 5, 6)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[2].beneficiaryCardNo, 6, 7)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[2].beneficiaryCardNo, 7, 8)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[2].beneficiaryCardNo, 8, 9)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[2].beneficiaryCardNo, 9, 10)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[2].beneficiaryCardNo, 10, 11)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[2].beneficiaryCardNo, 11, 12)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[2].beneficiaryCardNo, 12, 13)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[2].beneficiaryCardNo, 13, 14)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[2].beneficiaryCardNo, 14, 15)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[2].beneficiaryCardNo, 15, 16)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[2].beneficiaryCardNo, 16, 17)}</div>
                                            <div class="t-line-cert">${fn:substring(map.beneficiaryList[2].beneficiaryCardNo, 17, 18)}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="div-title" style="font-size: 12px;margin-bottom: 10px;">
                            注：当受益人为被保险人法定继承人以外的指定受益人时，须在C16项勾选“是”，并填写身故受益人的姓名、国籍、职业名称、联系方式、住址地或者工作单位地址。
                        </div>
                    </div>
                    <%--第二页--%>
                    <div style="height:1490px;">
                        <div>B.投保内容</div>
                        <div class="div-title" style="display: inline-block">
                            <div style="display: inline-block;vertical-align: top">注意:</div>
                            <div style="display: inline-block">
                                1.若主保险合同的交费周期为“趸交”，则附加“一年期”保险合同的交费周期必须为“年交”
                                <br/>2.溢交退费或留存本公司的保险费不计利息。
                            </div>
                        </div>
                        <div class="div-tab">
                            <%--表格层--%>
                            <div class="div-line">
                                <div class="div-line-con" style="width: 480px;padding: 10px 5px 10px 10px;">
                                    <div class="div-block">B1.交费周期：<input readonly type="checkbox"/>趸交<input
                                            style="margin-left: 25px;"
                                            type="checkbox"/>年交<input
                                            style="margin-left: 25px;" type="checkbox"/>半年交<input readonly style="margin-left: 30px;"
                                                                                                  type="checkbox"/>季交<input
                                            style="margin-left: 25px;" type="checkbox"/>月交
                                    </div>
                                    <div class="div-block tip">（半年交、季交、月交必须采用银行转账）</div>
                                    <div class="div-block">B3.保险费溢交转下期：<input readonly type="checkbox"/>同意<input
                                            style="margin-left: 20px;"
                                            type="checkbox"/>不同意<span class="tip">（未选默认为同意）</span>
                                    </div>
                                </div>
                                <div class="div-line-con" style="width: 520px;">
                                    <div class="div-block">
                                        <div>
                                            <div style="vertical-align: top">B2.交费方式：</div>
                                            <div>
                                                <div class="div-block"><input readonly type="checkbox"/>首期自行交费；续期银行转账</div>
                                                <div class="div-block"><input readonly type="checkbox"/>全部银行转账</div>
                                            </div>

                                        </div>
                                        <div class="div-block">B4.生存保险金领取方式 <input readonly type="checkbox"/>累积生息<input
                                                type="checkbox"/>现金领取<span class="tip">（未选默认为累积生息）</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="div-title">主保险合同</div>
                        <div class="div-tab">
                            <div class="table-line" style="height: 50px;">
                                <div class="div-table-2" style="width: 210px;height: 50px;">B5.主保险合同名称
                                    <div class="tip" style="display: block;line-height: 1">(请填写完整险种名称)</div>
                                </div>
                                <div class="div-table-2" style="width: 128px;height: 50px;">B6.交费期限</div>
                                <div class="div-table-2" style="width: 226px;height: 50px;">B7.保险期间
                                    <div class="tip" style="display: block;line-height: 1">(养老年金产品，请直接填写B11项)</div>
                                </div>
                                <div class="div-table-2" style="width: 138px;height: 50px;">B8.基本保险金额<br/>或养老年金领取金额</div>
                                <div class="div-table-2" style="width: 145px;height: 50px;">B9.红利领取方式
                                    <div class="tip" style="display: block;line-height: 1">(非分红险及保额<br/>分红险无需勾选)
                                    </div>
                                </div>
                                <div class="div-table-2" style="width: 153px;height: 50px;">B10.每期标准保险费</div>
                            </div>
                            <div class="table-line">
                                <div class="div-table-2" style="width: 210px;"></div>
                                <div class="div-table-2" style="width: 128px;font-size: 14px">
                                    <div style="text-align: left;display: inline-table;">
                                        <div class="div-block"><input readonly type="checkbox"><input readonly type="text"
                                                                                                      style="border-bottom: 1px #000000 solid !important;width: 50px;">年
                                        </div>
                                        <div class="div-block"><input readonly type="checkbox">至<input readonly type="text"
                                                                                                       style="border-bottom: 1px #000000 solid !important;width: 50px;">周岁
                                        </div>
                                        <div class="div-block"><input readonly type="checkbox">终身</div>
                                    </div>
                                </div>
                                <div class="div-table-2" style="width: 226px;font-size: 14px">
                                    <div style="text-align: left;display: inline-table;">
                                        <div class="div-block"><input readonly type="checkbox"><input
                                                style="border-bottom: 1px #000000 solid !important;width: 50px;"
                                                type="text">年
                                        </div>
                                        <div class="div-block"><input readonly type="checkbox">至<input
                                                style="border-bottom: 1px #000000 solid !important;width: 50px;"
                                                type="text">周岁
                                        </div>
                                        <div class="div-block"><input readonly type="checkbox">终身</div>
                                    </div>
                                </div>
                                <div class="div-table-2" style="width: 138px;"></div>
                                <div class="div-table-2" style="width: 145px;">
                                    <div class="div-block"><input readonly type="checkbox">累积生息</div>
                                    <div class="div-block"><input readonly type="checkbox">现金领取</div>
                                    <div class="div-block" style="font-size: 13px;color: red;">(未选择默认为积累生息)</div>
                                </div>
                                <div class="div-table-2" style="width: 153px;"></div>
                            </div>
                            <div class="table-line" style="height: 30px;">
                                <div style="padding: 5px">B11.养老年金领取</div>
                            </div>
                            <div class="table-line" style="height: 30px;">
                                <div class="div-table-2" style="width: 210px;height: 30px;">养老年金开始领取时间</div>
                                <div class="div-table-2" style="width: 128px;height: 30px;">养老年金领取方式</div>
                                <div class="div-table-2" style="width: 662px;height: 30px;">养老年金给付方式</div>
                            </div>
                            <div class="table-line" style="height: 110px;">
                                <div class="div-table-2" style="width: 210px;height: 120px;text-align: left;">
                                    <div class="div-block" style="margin-left:10px; padding: 5px 0;"><input
                                            type="checkbox"/>自被保险人55周岁始
                                    </div>
                                    <div class="div-block" style="margin-left:10px; padding: 5px 0;"><input
                                            type="checkbox"/>自被保险人60周岁始
                                    </div>
                                    <div class="div-block" style="margin-left:10px; padding: 5px 0;"><input
                                            type="checkbox"/>自被保险人<input
                                            style="border-bottom: 1px #000000 solid !important;width: 50px;" type="text">周岁始
                                    </div>
                                </div>
                                <div class="div-table-2" style="width: 790px;height: 120px;vertical-align: top">
                                    <div style="height: 82px;width: 790px;border-bottom: #000000 1px solid;">
                                        <div style="width: 129px;height: 82px;border-right: #000000 1px solid;box-sizing: border-box;text-align: left;">
                                            <div class="div-block" style="margin-left:10px; padding: 4px 0;"><input
                                                    type="checkbox"/>年领
                                            </div>
                                            <div class="div-block" style="margin-left:10px; padding: 4px 0;"><input
                                                    type="checkbox"/>月领
                                            </div>
                                            <div class="div-block" style="margin-left:10px; padding: 4px 0;"><input
                                                    type="checkbox"/>其他<input
                                                    style="border-bottom: 1px #000000 solid !important;width: 50px;"
                                                    type="text"></div>
                                        </div>
                                        <div style="width: 662px;height: 80px;box-sizing: border-box;text-align: left;">
                                            <div class="div-block" style="margin-left:10px;"><input
                                                    type="checkbox"/>保证给付年限方式<span style="margin-left: 30px;">(保证给付<input
                                                    style="border-bottom: 1px #000000 solid !important;width: 50px;"
                                                    type="text">年)</span></div>
                                            <div class="div-block" style="margin-left:10px;"><input readonly type="checkbox"/>身故返还本金方式
                                            </div>
                                            <div class="div-block" style="margin-left:10px;"><input
                                                    type="checkbox"/>保证给付总额方式<span style="margin-left: 30px;">必须选择保证年限<input
                                                    type="checkbox" style="margin-left: 5px;"/>30年<input
                                                    type="checkbox" style="margin-left: 5px;"/>40年  (两者选一)</span></div>
                                            <div class="div-block" style="margin-left:10px;"><input
                                                    type="checkbox"/>其他<input
                                                    style="border-bottom: 1px #000000 solid !important;width: 200px;"
                                                    type="text"></div>
                                        </div>
                                    </div>
                                    <div style="display: block;vertical-align: middle;text-align: left;margin-left: 10px;margin-top: 2px;">
                                        <input readonly type="checkbox">一次性给付方式（如为趸交保险费，不得选择一次性给付方式）
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="div-title">附加保险合同 <span
                                style="font-size: 13px">(“保险期间”为“一年“的附加保险合同为非保证续保产品，期满前由保险公司通知是否续保）</span></div>
                        <div class="div-tab">
                            <div class="table-line" style="height: 30px;">
                                <div class="div-table-2" style="width: 338px;height: 30px;">B12.附加保险合同/可选责任名称</div>
                                <div class="div-table-2" style="width: 156px;height: 30px;">B13.交费期限</div>
                                <div class="div-table-2" style="width: 156px;height: 30px;">B14.保险期间</div>
                                <div class="div-table-2" style="width: 175px;height: 30px;">B15.基本保险金额</div>
                                <div class="div-table-2" style="width: 175px;height: 30px;">B16.每期标准保险费</div>
                            </div>
                            <div class="table-line" style="height: 40px;">
                                <div class="div-table-2" style="width: 338px;height: 40px;"></div>
                                <div class="div-table-2" style="width: 156px;height: 40px;"></div>
                                <div class="div-table-2" style="width: 156px;height: 40px;"></div>
                                <div class="div-table-2" style="width: 175px;height: 40px;"></div>
                                <div class="div-table-2" style="width: 175px;height: 40px;"></div>
                            </div>
                            <div class="table-line" style="height: 40px;">
                                <div class="div-table-2" style="width: 338px;height: 40px;"></div>
                                <div class="div-table-2" style="width: 156px;height: 40px;"></div>
                                <div class="div-table-2" style="width: 156px;height: 40px;"></div>
                                <div class="div-table-2" style="width: 175px;height: 40px;"></div>
                                <div class="div-table-2" style="width: 175px;height: 40px;"></div>
                            </div>
                            <div class="table-line" style="height: 40px;">
                                <div class="div-table-2" style="width: 338px;height: 40px;"></div>
                                <div class="div-table-2" style="width: 156px;height: 40px;"></div>
                                <div class="div-table-2" style="width: 156px;height: 40px;"></div>
                                <div class="div-table-2" style="width: 175px;height: 40px;"></div>
                                <div class="div-table-2" style="width: 175px;height: 40px;"></div>
                            </div>
                            <div class="table-line" style="height: 40px;">
                                <div class="div-table-2" style="width: 338px;height: 40px;"></div>
                                <div class="div-table-2" style="width: 156px;height: 40px;"></div>
                                <div class="div-table-2" style="width: 156px;height: 40px;"></div>
                                <div class="div-table-2" style="width: 175px;height: 40px;"></div>
                                <div class="div-table-2" style="width: 175px;height: 40px;"></div>
                            </div>
                        </div>
                        <div class="div-tab" style="margin-top: 10px;">
                            <div class="div-line">
                                <div class="div-line-con" style="width: 210px;box-sizing: border-box">B17.期交/趸交保险费总计：</div>
                                <div class="div-line-con" style="border: 0;">
                                    <div>(大写）<span style="margin-left: 60px">仟</span><span
                                            style="margin-left: 55px">佰</span><span style="margin-left: 55px">拾</span><span
                                            style="margin-left: 55px">万</span><span style="margin-left: 55px">仟</span><span
                                            style="margin-left: 55px">佰</span><span style="margin-left: 55px">拾</span><span
                                            style="margin-left: 55px">元(小写)</span><span style="margin-left: 100px">元</span>
                                    </div>
                                </div>
                            </div>
                            <div class="div-line">
                                <div class="div-line-con" style="width: 210px;box-sizing: border-box">B18.追加保险费：</div>
                                <div class="div-line-con" style="border: 0;">
                                    <div>(大写）<span style="margin-left: 60px">仟</span><span
                                            style="margin-left: 55px">佰</span><span style="margin-left: 55px">拾</span><span
                                            style="margin-left: 55px">万</span><span style="margin-left: 55px">仟</span><span
                                            style="margin-left: 55px">佰</span><span style="margin-left: 55px">拾</span><span
                                            style="margin-left: 55px">元(小写)</span><span style="margin-left: 100px">元</span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div>C.可保资料告知</div>
                        <div class="div-title">请投保人告知被保险人的健康状况并对被保险人栏中的答案进行勾选；如保险条款中涉及对投保人承担保险责任事项、投保人栏也须填写:</div>
                        <div class="div-tab">
                            <div class="table-line" style="height: 40px;">
                                <div class="div-table-2" style="width: 636px;height: 40px;">问题及健康告知</div>
                                <div class="div-table-2" style="width: 83px;height: 40px;">被保险人</div>
                                <div class="div-table-2" style="width: 83px;height: 40px;">投保人</div>
                                <div class="div-table-2" style="width: 198px;height: 40px;text-align: left;">若“是”请说明<span
                                        class="tip">(原因、日期、医院名称及诊治结果等。)</span></div>
                            </div>
                            <div class="table-line" style="height: 53px;">
                                <div class="div-table-2"
                                     style="width: 636px;height: 53px;text-align: left;vertical-align: middle">
                                    <div style="display: inline-block;vertical-align: top;">C1.</div>
                                    <div style="display: inline-block;width: 600px;">
                                        您是否正在申请或已经拥有任何保险公司的保险合同？若是，请说明：承保公司、保险品种、保险金额总和、因被保险人死亡给付的保险金总和、住院每日补贴日额及保险合同生效日期。
                                    </div>
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 53px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[0].insuredResult=='true'}">checked</c:if>  >是
                                    <input readonly type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[0].insuredResult=='false'}">checked</c:if>   >否
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 53px;border-right:#000000 1px solid;">
                                    <input
                                            type="checkbox" <c:if test="${map.insurance_matterList[0].policyholderResult=='true'}">checked</c:if> >是<input readonly type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[0].policyholderResult=='false'}">checked</c:if>>否
                                </div>
                            </div>
                            <div class="table-line" style="height: 53px;">
                                <div class="div-table-2"
                                     style="width: 636px;height: 53px;text-align: left;vertical-align: middle">
                                    <div style="display: inline-block;vertical-align: top;">C2.</div>
                                    <div style="display: inline-block;width: 600px;">
                                        您的人寿保险、人身意外或健康保险的投保申请是否曾被拒保、推迟、加费或作限制保障权益？是否有解除保险合同？是否曾向任何保险公司提出索赔申请？若“是”，请说明。
                                    </div>
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 53px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[1].insuredResult=='true'}">checked</c:if>>是<input
                                        type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[1].insuredResult=='false'}">checked</c:if>>否
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 53px;border-right:#000000 1px solid;">
                                    <input
                                            type="checkbox" <c:if test="${map.insurance_matterList[1].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[1].policyholderResult=='false'}">checked</c:if>>否
                                </div>
                            </div>
                            <div class="table-line" style="height: 53px;">
                                <div class="div-table-2"
                                     style="width: 636px;height: 53px;text-align: left;vertical-align: middle">
                                    <div style="display: inline-block;vertical-align: top;">C3.</div>
                                    <div style="display: inline-block;width: 600px;">
                                        是否计划出国或改变居住地或工作地点？正在或试图参加私人性质飞行，或携带氧气瓶潜水、或登山、或从事危险性的运动？若“是”，请填妥相关问卷，连同此投保单一并交回本公司。
                                    </div>
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 53px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[2].insuredResult=='true'}">checked</c:if>>是<input
                                        type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[2].insuredResult=='false'}">checked</c:if>>否
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 53px;border-right:#000000 1px solid;">
                                    <input
                                            type="checkbox" <c:if test="${map.insurance_matterList[2].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[2].policyholderResult=='false'}">checked</c:if>>否
                                </div>
                            </div>
                            <div class="table-line" style="height: 25px;">
                                <div class="div-table-2"
                                     style="width: 636px;height: 25px;text-align: left;vertical-align: middle">
                                    <div style="display: inline-block;vertical-align: top;">C4.</div>
                                    <div style="display: inline-block;width: 600px;">是否持有有效摩托车驾照？</div>
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 25px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[3].insuredResult=='true'}">checked</c:if>>是<input
                                        type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[3].insuredResult=='false'}">checked</c:if>>否
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 25px;border-right:#000000 1px solid;">
                                    <input
                                            type="checkbox" <c:if test="${map.insurance_matterList[3].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[3].policyholderResult=='false'}">checked</c:if>>否
                                </div>
                            </div>
                            <div class="table-line" style="height: 60px;">
                                <div class="div-table-2"
                                     style="width: 636px;height: 60px;text-align: left;vertical-align: middle">
                                    <div style="display: inline-block;vertical-align: top;">C5.</div>
                                    <div style="display: inline-block;width: 600px;">a.&nbsp;&nbsp; 是否吸烟? &nbsp;&nbsp;&nbsp;
                                        若“是”， 吸烟${map.insurance_matter_value[0].m_value0}年${map.insurance_matter_value[0].m_value1}支/天；&nbsp;&nbsp;若现已停止吸烟，&nbsp;&nbsp;停止吸烟原因<br/>及时间${map.insurance_matter_value[0].m_value2}。b.
                                        是否饮酒？&nbsp;&nbsp;&nbsp;&nbsp;若“是”，&nbsp;&nbsp;&nbsp;饮酒${map.insurance_matter_value[0].m_value3}年，种类${map.insurance_matter_value[0].m_value4}，数<br/>量${map.insurance_matter_value[0].m_value5}（两/周）；&nbsp;&nbsp;&nbsp;&nbsp;若现已停止饮酒，停止饮酒原因及时间${map.insurance_matter_value[0].m_value6}。
                                    </div>
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 60px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[4].insuredResult=='true'}">checked</c:if>>是<input
                                        type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[4].insuredResult=='false'}">checked</c:if>>否
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 60px;border-right:#000000 1px solid;">
                                    <input
                                            type="checkbox" <c:if test="${map.insurance_matterList[4].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[4].policyholderResult=='false'}">checked</c:if>>否
                                </div>
                            </div>
                            <div class="table-line" style="height: 30px;">
                                <div class="div-table-2"
                                     style="width: 636px;height: 30px;text-align: left;vertical-align: middle">
                                    <div style="display: inline-block;vertical-align: top;">C6.</div>
                                    <div style="display: inline-block;width: 600px;">是否曾经或正在使用镇静安眠剂、可成瘾药物、麻醉剂或接受戒毒、戒酒治疗？
                                    </div>
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 30px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[5].insuredResult=='true'}">checked</c:if>>是<input
                                        type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[5].insuredResult=='false'}">checked</c:if>>否
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 30px;border-right:#000000 1px solid;">
                                    <input
                                            type="checkbox" <c:if test="${map.insurance_matterList[5].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[5].policyholderResult=='false'}">checked</c:if>>否
                                </div>
                            </div>
                            <div class="table-line" style="height: 30px;">
                                <div class="div-table-2"
                                     style="width: 636px;height: 30px;text-align: left;vertical-align: middle">
                                    <div style="display: inline-block;vertical-align: top;">C7.</div>
                                    <div style="display: inline-block;width: 600px;">最近六个月内是否有医生建议您服药、住院、接受诊疗、手术或其他医疗方案？
                                    </div>
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 30px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[6].insuredResult=='true'}">checked</c:if>>是<input
                                        type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[6].insuredResult=='false'}">checked</c:if>>否
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 30px;border-right:#000000 1px solid;">
                                    <input
                                            type="checkbox" <c:if test="${map.insurance_matterList[6].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[6].policyholderResult=='false'}">checked</c:if>>否
                                </div>
                            </div>
                            <div class="table-line" style="height: 60px;">
                                <div class="div-table-2"
                                     style="width: 636px;height: 60px;text-align: left;vertical-align: middle">
                                    <div style="display: inline-block;vertical-align: top;">C8.</div>
                                    <div style="display: inline-block;width: 600px;">最近五年内，是否曾经作下列之一的检查，有无异常？
                                        核磁共振(MRI)、心电图、胃镜、纤维结肠镜、气管镜、CT、超声波、X光、眼底检查、脑电图、肝功能、肾功能、病理活检及其它特殊检查。
                                    </div>
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 60px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[7].insuredResult=='true'}">checked</c:if>>是<input
                                        type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[7].insuredResult=='false'}">checked</c:if>>否
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 60px;border-right:#000000 1px solid;">
                                    <input
                                            type="checkbox" <c:if test="${map.insurance_matterList[7].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[7].policyholderResult=='false'}">checked</c:if>>否
                                </div>
                            </div>
                            <div class="table-line" style="height: 160px;">
                                <div class="div-table-2"
                                     style="width: 636px;height: 160px;text-align: left;vertical-align: middle">
                                    <div style="display: inline-block;vertical-align: top;">C9.</div>
                                    <div style="display: inline-block;width: 600px;">是否有下列身体残障状况：
                                        <br/>a 脊柱、胸廓、四肢、手指或手掌、足趾或足部缺损畸形、两上肢或两下肢长度不等、跛行？
                                        b 眼、耳、鼻、舌或其它颜面部软组织缺损畸形？牙齿脱落、上下颌骨缺失、颞下颌关节强直？肋骨骨折或缺失？颈部或腰部活动受限？肢体肌力下降？
                                        c 睾丸萎缩或缺失？阴茎缺失？输精管闭锁或缺失？（男性）
                                        d 子宫切除？阴道闭锁？乳房切除？（女性）
                                        e 视力、听力、语言、咀嚼、吞咽、嗅觉、触觉等功能障碍或中枢神经系统障碍？
                                        f 精神、智能障碍或性格行为异常？
                                        g 脾、肺、胃、小肠、结肠、直肠、胰腺、肝、肾、膀胱切除？心脏的结构损伤或功能障碍？输尿管闭锁或缺失？其它内脏或身体器官缺损、摘除或移植？
                                    </div>
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 160px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[8].insuredResult=='true'}">checked</c:if>>是<input
                                        type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[8].insuredResult=='false'}">checked</c:if>>否
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 160px;border-right:#000000 1px solid;">
                                    <input readonly type="checkbox" <c:if test="${map.insurance_matterList[8].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[8].policyholderResult=='false'}">checked</c:if>>否
                                </div>
                            </div>
                            <div class="table-line" style="height: 53px;">
                                <div class="div-table-2"
                                     style="width: 636px;height: 53px;text-align: left;vertical-align: middle">
                                    <div style="display: inline-block;vertical-align: top;">C10.</div>
                                    <div style="display: inline-block;width: 600px;">a
                                        您及您的配偶是否曾接受或试图接受与艾滋病(AIDS)有关的医疗咨询、检验或治疗，或艾滋病病毒(HIV)呈阳性反应？
                                        b 是否曾经验血而得知为乙肝表面抗原(HbsAg)阳性反应或不宜献血？
                                    </div>
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 53px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[9].insuredResult=='true'}">checked</c:if>>是<input
                                        type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[9].insuredResult=='false'}">checked</c:if>>否
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 53px;border-right:#000000 1px solid;">
                                    <input
                                            type="checkbox" <c:if test="${map.insurance_matterList[9].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[9].policyholderResult=='false'}">checked</c:if>>否
                                </div>
                            </div>
                        </div>
                    </div>


                    <%--第三页--%>
                    <div style="height:1490px;">
                        <div class="div-tab">
                            <div class="table-line" style="height: 58px;">
                                <div class="div-table-2"
                                     style="width: 639px;height:58px;text-align: left;vertical-align: middle">
                                    <div style="display: inline-block;vertical-align: top;">C11.</div>
                                    <div style="display: inline-block;width: 600px;">若为16周岁(含)以上女性，&nbsp;&nbsp;&nbsp;请告知：&nbsp;&nbsp;&nbsp;a.目前是否怀孕？&nbsp;&nbsp;&nbsp;若是，&nbsp;&nbsp;&nbsp;已怀孕${map.insurance_matter_value[1].m_value0}
                                        周？
                                        b.&nbsp;&nbsp;（曾）患子宫、卵巢、乳房或其它生殖器官疾病？c.（曾）异常妊娠、阴道异常出血或接受下腹部手术？d.母亲、姐妹中是否有人（曾）患乳腺、子宫、卵巢等生殖器官恶性肿瘤？
                                    </div>
                                </div>
                                <div class="div-table-2" style="width: 83px;height:58px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[10].insuredResult=='true'}">checked</c:if>>是<input
                                        type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[10].insuredResult=='false'}">checked</c:if>>否
                                </div>
                                <div class="div-table-2" style="width: 83px;height:58px;border-right:#000000 1px solid;">
                                    <input
                                            type="checkbox" <c:if test="${map.insurance_matterList[10].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[10].policyholderResult=='false'}">checked</c:if>>否
                                </div>
                            </div>
                            <div class="table-line" style="height: 465px;">
                                <div class="div-table-2"
                                     style="width: 639px;height: 465px;text-align: left;vertical-align: middle">
                                    <div style="display: inline-block;vertical-align: top;">C12.</div>
                                    <div style="display: inline-block;width: 600px;line-height: 1.3;">是否患有或曾经患有以下疾病：
                                        <br/>a.最近六个月内，是否有下列疾患或自觉症状?
                                        <br/>.不明原因皮肤出血点或瘀斑、鼻衄、反复齿龈出血?
                                        <br/>.不明原因的声嘶、关节红肿酸痛、难以愈合的舌、皮肤溃疡，持续低热，体重显著减轻(短期内5公斤以上)，痣的形态、大小或颜色改变、黄疸?
                                        <br/>.咳嗽、痰中有血块或血丝?眼睛胀痛、视力或听力明显下降、视物不清?
                                        <br/>.持续一周以上的吞咽困难、食欲不振、盗汗、腹泻、腹部不适?
                                        <br/>.紫绀、胸闷、心慌、气急、心前区疼痛、反复头痛、头晕?
                                        <br/>.小便困难、蛋白尿、血尿、便血、黑便、粘液便?
                                        <br/>b.视神经病变、白内障、青光眼、视网膜出血或剥离、近视800度以上?
                                        <br/>C.脑脊液鼻漏或耳漏、脑血管意外及后遗症、蛛网膜下腔出血、癫痫病、帕金森氏综合症、精神病、神经麻痹、心脏病、高血压、高脂血症、血管瘤、血管疾病?
                                        <br/>d.胸膜炎、肺炎、哮喘、肺结核、慢性支气管炎、支气管扩张症、肺气肿、气胸、尘肺、矽肺?
                                        <br/>e. 慢性胃肠炎、结肠炎、消化性溃疡、消化道出血穿孔、胰腺炎、肝炎、脂肪肝、肝硬化、肝脓肿、胆道结石、胆囊炎、腹膜炎、脾肿大、肛肠疾病?
                                        <br/>f.肾炎、肾病综合症、尿毒症、急性肾功能衰竭、尿路结石、尿道狭窄、肾囊肿、肾下垂、反复尿路感染、性病?
                                        <br/>g.糖尿病、垂体、甲状腺、肾上腺疾病等内分泌系统疾病?
                                        <br/>h.贫血、再生障碍性贫血、白血病、紫癜症、血友病?
                                        <br/>i.风湿热、 关节炎、类风湿性关节炎、 痛风、颈椎病、椎间盘突出症、 红斑狼疮、硬皮病、皮肌炎、重症肌无力、肌肉萎缩症、 其他结缔组织疾病?
                                        <br/>j.肿瘤(包括任何良性、恶性或尚未定性的肿瘤)、息肉、囊肿或增生物?
                                        <br/>k.先天性疾病、遗传性疾病?
                                        <br/>l.身体是否有瘢痕？
                                        <br/>m.除上述以外的其它疾病、症状或意外受伤史?
                                    </div>
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 465px;vertical-align: top">
                                    <div style="display: block;margin-top:25px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[11].insuredResult=='true'}">checked</c:if>>是<input
                                            type="checkbox"
                                            style="margin-left: 10px;" <c:if test="${map.insurance_matterList[11].insuredResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;margin-top:125px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[12].insuredResult=='true'}">checked</c:if>>是<input
                                            type="checkbox"
                                            style="margin-left: 10px;" <c:if test="${map.insurance_matterList[12].insuredResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;margin-top:0;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[13].insuredResult=='true'}">checked</c:if>>是<input readonly type="checkbox"
                                                                                                                                                                                                        style="margin-left: 10px;" <c:if test="${map.insurance_matterList[13].insuredResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;margin-top:15px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[14].insuredResult=='true'}">checked</c:if>>是<input
                                            type="checkbox"
                                            style="margin-left: 10px;" <c:if test="${map.insurance_matterList[14].insuredResult=='true'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[15].insuredResult=='true'}">checked</c:if>>是<input readonly type="checkbox"
                                                                                                                                                                                           style="margin-left: 10px;" <c:if test="${map.insurance_matterList[15].insuredResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;margin-top:15px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[16].insuredResult=='true'}">checked</c:if>>是<input
                                            type="checkbox"
                                            style="margin-left: 10px;" <c:if test="${map.insurance_matterList[16].insuredResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;margin-top:15px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[17].insuredResult=='true'}">checked</c:if>>是<input
                                            type="checkbox"
                                            style="margin-left: 10px;" <c:if test="${map.insurance_matterList[17].insuredResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[18].insuredResult=='true'}">checked</c:if>>是<input readonly type="checkbox"
                                                                                                                                                                                           style="margin-left: 10px;" <c:if test="${map.insurance_matterList[18].insuredResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[19].insuredResult=='true'}">checked</c:if>>是<input readonly type="checkbox"
                                                                                                                                                                                           style="margin-left: 10px;" <c:if test="${map.insurance_matterList[19].insuredResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;margin-top:15px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[20].insuredResult=='true'}">checked</c:if>>是<input
                                            type="checkbox"
                                            style="margin-left: 10px;" <c:if test="${map.insurance_matterList[20].insuredResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[21].insuredResult=='true'}">checked</c:if>>是<input readonly type="checkbox"
                                                                                                                                                                                           style="margin-left: 10px;" <c:if test="${map.insurance_matterList[21].insuredResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[22].insuredResult=='true'}">checked</c:if>>是<input readonly type="checkbox"
                                                                                                                                                                                           style="margin-left: 10px;" <c:if test="${map.insurance_matterList[22].insuredResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[23].insuredResult=='true'}">checked</c:if>>是<input readonly type="checkbox"
                                                                                                                                                                                           style="margin-left: 10px;" <c:if test="${map.insurance_matterList[23].insuredResult=='false'}">checked</c:if>>否
                                    </div>
                                </div>
                                <div class="div-table-2"
                                     style="width: 83px;height: 465px;vertical-align: top;border-right:#000000 1px solid;">
                                    <div style="display: block;margin-top:25px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[11].policyholderResult=='true'}">checked</c:if>>是<input
                                            type="checkbox"
                                            style="margin-left: 10px; " <c:if test="${map.insurance_matterList[11].policyholderResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;margin-top:125px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[12].policyholderResult=='true'}">checked</c:if>>是<input
                                            type="checkbox"
                                            style="margin-left: 10px;" <c:if test="${map.insurance_matterList[12].policyholderResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;margin-top:0;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[13].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox"
                                                                                                                                                                                                             style="margin-left: 10px;" <c:if test="${map.insurance_matterList[13].policyholderResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;margin-top:15px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[14].policyholderResult=='true'}">checked</c:if>>是<input
                                            type="checkbox"
                                            style="margin-left: 10px;" <c:if test="${map.insurance_matterList[14].policyholderResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[15].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox"
                                                                                                                                                                                                style="margin-left: 10px;" <c:if test="${map.insurance_matterList[15].policyholderResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;margin-top:15px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[16].policyholderResult=='true'}">checked</c:if>>是<input
                                            type="checkbox"
                                            style="margin-left: 10px;" <c:if test="${map.insurance_matterList[16].policyholderResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;margin-top:15px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[17].policyholderResult=='true'}">checked</c:if>>是<input
                                            type="checkbox"
                                            style="margin-left: 10px;" <c:if test="${map.insurance_matterList[17].policyholderResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[18].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox"
                                                                                                                                                                                                style="margin-left: 10px;" <c:if test="${map.insurance_matterList[18].policyholderResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[19].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox"
                                                                                                                                                                                                style="margin-left: 10px;" <c:if test="${map.insurance_matterList[19].policyholderResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;margin-top:15px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[20].policyholderResult=='true'}">checked</c:if>>是<input
                                            type="checkbox"
                                            style="margin-left: 10px;" <c:if test="${map.insurance_matterList[20].policyholderResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[21].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox"
                                                                                                                                                                                                style="margin-left: 10px;" <c:if test="${map.insurance_matterList[21].policyholderResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[22].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox"
                                                                                                                                                                                                style="margin-left: 10px;" <c:if test="${map.insurance_matterList[22].policyholderResult=='false'}">checked</c:if>>否
                                    </div>
                                    <div style="display: block;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[23].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox"
                                                                                                                                                                                                style="margin-left: 10px;" <c:if test="${map.insurance_matterList[23].policyholderResult=='false'}">checked</c:if>>否
                                    </div>
                                </div>
                            </div>
                            <div class="table-line" style="height: 40px;">
                                <div class="div-table-2"
                                     style="width: 639px;height: 40px;text-align: left;vertical-align: middle">
                                    <div style="display: inline-block;vertical-align: top;">C13.</div>
                                    <div style="display: inline-block;width: 600px;">
                                        直系亲属中，是否患有或曾经患有高血压、肾病、心脏病、肝炎、肝肾囊肿、肝硬化、糖尿病、精神病、癌症或早于60周岁因病身故者？
                                    </div>
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 40px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[24].insuredResult=='true'}">checked</c:if>>是<input
                                        type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[24].insuredResult=='false'}">checked</c:if>>否
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 40px;border-right:#000000 1px solid;">
                                    <input
                                            type="checkbox" <c:if test="${map.insurance_matterList[24].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[24].policyholderResult=='false'}">checked</c:if>>否
                                </div>
                            </div>
                            <div class="table-line" style="height: 197px;">
                                <div class="div-table-2"
                                     style="width: 639px;height: 197px;text-align: left;vertical-align: middle">
                                    <div style="display: inline-block;vertical-align: top;">C14.</div>
                                    <div style="display: inline-block;width: 600px;">若为2周岁(不含)以下婴儿，请告知：
                                        <br/>a. &nbsp;&nbsp;被保险人出生时身长${map.insurance_matter_value[2].m_value0}厘米，&nbsp;&nbsp;&nbsp;体重${map.insurance_matter_value[2].m_value1}公斤，&nbsp;&nbsp;&nbsp;出生医院${map.insurance_matter_value[2].m_value2}，出生时留院天数${map.insurance_matter_value[2].m_value3}天，如超过7天，请详细说明
                                        <br/>b. 出生时是否有早产、难产、窒息等情况？是否使用产钳等辅助器械？
                                        <br/>c. 出生时是否有抢救史？
                                        <br/>d. 是否未按要求接受预防接种？
                                        <br/>e.是否曾进行婴幼儿体检且结果异常？
                                        <br/>f.是否经常患腹痛、婴幼儿腹泻等消化系统疾病？
                                        <br/>g.是否曾患哮喘、肺炎、扁桃体炎等呼吸系统疾病？
                                        <br/>h.是否曾患疝气？
                                        i.是否曾出现“高热惊厥”？
                                    </div>
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 197px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[25].insuredResult=='true'}">checked</c:if>>是<input
                                        type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[25].insuredResult=='false'}">checked</c:if>>否
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 197px;border-right:#000000 1px solid;">
                                    <input readonly type="checkbox" <c:if test="${map.insurance_matterList[25].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[25].policyholderResult=='false'}">checked</c:if>>否
                                </div>
                            </div>
                            <div class="table-line" style="height: 28px;">
                                <div class="div-table-2"
                                     style="width: 639px;height: 28px;text-align: left;vertical-align: middle">
                                    <div style="display: inline-block;vertical-align: top;">C15.</div>
                                    <div style="display: inline-block;width: 600px;">是否已参加公费医疗或社会医疗保险。</div>
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 28px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[26].insuredResult=='true'}">checked</c:if>>是<input
                                        type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[26].insuredResult=='false'}">checked</c:if>>否
                                </div>
                                <!--  <div class="div-table-2" style="width: 83px;height: 28px;border-right:#000000 1px solid;"><input readonly type="checkbox">是<input readonly type="checkbox" style="margin-left: 10px;">否</div>-->
                            </div>
                            <div class="table-line" style="height: 28px;">
                                <div class="div-table-2"
                                     style="width: 639px;height: 28px;text-align: left;vertical-align: middle">
                                    <div style="display: inline-block;vertical-align: top;">C16.</div>
                                    <div style="display: inline-block;width: 600px;">您是否有其他事项告知本公司？</div>
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 28px;"><input readonly type="checkbox" <c:if test="${map.insurance_matterList[27].insuredResult=='true'}">checked</c:if>>是<input
                                        type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[27].insuredResult=='false'}">checked</c:if>>否
                                </div>
                                <div class="div-table-2" style="width: 83px;height: 28px;border-right:#000000 1px solid;">
                                    <input
                                            type="checkbox" <c:if test="${map.insurance_matterList[27].policyholderResult=='true'}">checked</c:if>>是<input readonly type="checkbox" style="margin-left: 10px;" <c:if test="${map.insurance_matterList[27].policyholderResult=='false'}">checked</c:if>>否
                                </div>
                            </div>
                        </div>
                        <div class="hidden-prints">
                            <div style="padding-left: 10px;font-size: 16px;">
        <span style="color: red;" class="three-span">
        投保人、被保险人向保险公司声明并同意下列事项:
        </span>
                            </div>
                            <div class="div-tab" style="margin-top: 5px;background-color: #f6f9f2;">
                                <div class="div-line" style="border-bottom: solid 0px;padding-left: 5px;padding-top: 5px;">
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="left-span-div">
                                            <span class="three-span">1.</span>
                                        </div>
                                        <div class="right-span-div">
                    <span class="three-span">
                        投保人和被保险人对于投保单各栏目中的所有陈述均属真实，
                        <span style="font-weight: bold">并亲笔签名</span>
                        ，如投保人和被保险人故意或者因重大过失未履行如实告知义务，足以影响富德生命人寿保险股份有限公同决定是否同意承保或者提高保险费率的，富德生命人寿保险股份有限公司有权依法解除保险合同，并对保险合同解除前发生的保险事故，不承担赔偿或给付保险金的责任
                    </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line" style="border-bottom: solid 0px;padding-left: 5px;padding-top: 5px;">
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="left-span-div">
                                            <span style="color: red;" class="three-span">2.</span>
                                        </div>
                                        <div class="right-span-div">
                    <span style="color: red;" class="three-span">
                        本人已收到且认真阅读并完全理解人身保险投保提示书及产品说明书、保险条款，对于保险合同条款各项内容特别是有关保险责任、责任免除、免
                        赔额、免赔率、比例赔付或给付或者减轻保险人责任的条款、保险期间、犹豫期、合同撤销和解除、保险合同的现金价值及父母为未成年子女投保以死亡为给付保险金条件人身保险的政策规定等内容均已了解和认可。
                    </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line" style="border-bottom: solid 0px;padding-left: 5px;padding-top: 5px;">
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="left-span-div">
                                            <span class="three-span">3.</span>
                                        </div>
                                        <div class="right-span-div">
                    <span class="three-span">
                        非经保险合同双方在保险单或业务批单上书面约定，任何人包括业务人员的任何与保险合同条款相违背或增减保险合同条款内容的书面或口头承诺，富德生命人寿保险股份有限公司有权不认可其法律效力。
                    </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line" style="border-bottom: solid 0px;padding-left: 5px;padding-top: 5px;">
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="left-span-div">
                                            <span class="three-span">4.</span>
                                        </div>
                                        <div class="right-span-div">
                    <span class="three-span">
                        同意富德生命人寿保险股份有限公司根据业务需要，对被保险人或投保人进行相关体检，或直接向被保险人或投保人就诊医院及其他知情机构、人士查询有关诊疗记录、病历及其他资料。
                    </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line" style="border-bottom: solid 0px;padding-left: 5px;padding-top: 5px;">
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="left-span-div">
                                            <span class="three-span">5.</span>
                                        </div>
                                        <div class="right-span-div">
                    <span class="three-span">
                         按本投保单所签订的保险合同，只有在富德生命人寿保险股份有限公司收到投保人交纳的首期保险费并签发保险单，及自申请之日起至富德生命人寿保险股份有限公司签发保险单之日被保险人和投保人健康状况无明显改变后才能生效。如无其他特别要求或说明，则保险合同自富德生命人寿保险股份有限公司收取首期保险费并签发保险单的当日二十四时起生效。
                    </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line" style="border-bottom: solid 0px;padding-left: 5px;padding-top: 5px;">
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="left-span-div">
                                            <span class="three-span">6.</span>
                                        </div>
                                        <div class="right-span-div" style="height: 20px;">
                    <span class="three-span">
                        &nbsp;若本人接受富德生命人寿保险股份有限公司签发的包含批注或任何附加及更改的保险合同及任何经本人签署的文件，均视为本人已承认。
                        &nbsp;&nbsp;&nbsp;&nbsp;
                    </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line" style="border-bottom: solid 0px;padding-left: 5px;padding-top: 5px;">
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="left-span-div">
                                            <span style="color: red;" class="three-span">7.</span>
                                        </div>
                                        <div class="right-span-div">
                    <span style="color: red;" class="three-span">
                        父母为其未成年人子女投保的人身保险，在被保险人成年之前，各保险合同约定的被保险人死亡给付的保险金额总和、被保险人死亡时各保险公司实际给付的保险金总和不得超过保险监督管理机构规定的限额(被保险人不满10周岁的，不得超过人民币20万元；被保险人已满10周岁但未满18周岁的，不得超过人民币50万元)。若投保多份保险合同，存在超额情形的，本人同意按照保单生效日的先后顺序予以赔付或对超额部分无息退费。
                    </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line" style="border-bottom: solid 0px;padding-left: 5px;padding-top: 5px;">
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="left-span-div">
                                            <span class="three-span">8.</span>
                                        </div>
                                        <div class="right-span-div">
                    <span class="three-span">
                        在中国法律允许或要求的范围内，本人同意或授权承保公同将其个人信息(包括联系电话和联系地址)及保单信息提供给当地健康保险信息平台、 监管机构、行业协会做合理利用
                        <span style="color: red;"> (包括但不限于计算保费、核保、寄送保单和客户回访等)</span> 。
                    </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line" style="border-bottom: solid 0px;padding-left: 5px;padding-top: 5px;">
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="left-span-div">
                                            <span class="three-span">9.</span>
                                        </div>
                                        <div class="right-span-div">
                    <span class="three-span">
                        如您投保的是投资连结保险、万能保险、分红保险等新型产品，
                        <span style="color: red;" class="three-span">请亲笔抄录：</span>
                    <span style="font-weight: bold;" class="three-span">本人已阅读保险条款、产品说明书和投保提示书，了解本产品的特点和保单利益的不确定性。</span>
                    </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line"
                                     style="border-bottom: solid 0px;padding-left: 15px;margin-top: 3px;margin-bottom: 3px;height: 16px">
                                    <div class="div-line-con-three div-row-5" style="border-left: solid 0px;">
                                        <div class="a-input-div" style="width: 970px;height:15px ">
                                            <span class="f-body-font-three">&nbsp;</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line"
                                     style="border-bottom: solid 0px;padding-left: 15px;margin-top: 3px;margin-bottom: 3px;height: 16px">
                                    <div class="div-line-con-three div-row-5" style="border-left: solid 0px;">
                                        <div class="a-input-div" style="width: 970px;height:15px ">
                                            <span class="f-body-font-three">&nbsp;</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line" style="border-bottom: solid 0px;padding-left: 5px;padding-top: 5px;">
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="left-span-div">
                                            <span class="three-span">10.</span>
                                        </div>
                                        <div class="right-span-div">
                    <span class="three-span">
                        投保人同意并授权富德生命人寿保险股份有限公司及银行(或邮局)从本人以下账户中以约定的交费方式及金额按期收取各期保险费；如有溢交或其他支付也退还至下述授权帐号。
                        (账户开户名必须是投保人的姓名)
                    </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line" style="border-bottom: solid 0px;padding-left: 5px;padding-top: 10px;">
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="title">
                                            <span class="three-span">银行（全称）：</span>
                                        </div>
                                        <div class="a-input-div" style="width: 100px;">
                                            <span class="f-body-font-three">&nbsp;${map.insuranceOrder.insuranceOrderPolicyholderBank.accountBank}</span>
                                        </div>

                                    </div>
                                    <div class="div-line-con-three" style="border-left: solid 00px;">
                                        <div class="title">
                                            <span class="three-span">银行</span>
                                        </div>
                                        <div class="a-input-div" style="width: 100px;">
                                            <span class="three-span">&nbsp;</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="title">
                                            <span class="three-span">分行</span>
                                        </div>
                                        <div class="a-input-div" style="width: 100px;">
                                            <span class="three-span">&nbsp;</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="title">
                                            <span class="three-span">支行</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-three" style="border-left: solid 0px;margin-left: 20px;">
                                        <div class="title">
                                            <span class="three-span">邮局（全称）：</span>
                                        </div>
                                        <div class="a-input-div" style="width: 100px;">
                                            <span class="three-span">&nbsp;</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="title">
                                            <span class="three-span">分局</span>
                                        </div>
                                        <div class="a-input-div" style="width: 100px;">
                                            <span class="three-span">&nbsp;</span>
                                        </div>
                                        <div class="title">
                                            <span class="three-span">支局</span>
                                        </div>
                                    </div>
                                </div>

                                <div class="div-line" style="border-bottom: solid 0px;padding-left: 5px;padding-top: 15px;padding-bottom: 5px;">
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="div-line-con-three div-postcode">
                                            授权活期账户号码&nbsp;/&nbsp;借记卡号码&nbsp;：&nbsp;
                                            <div style="margin-left: 30px;font-size: 0">
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 0, 1)}"/>
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 1, 2)}"/>
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 2, 3)}"/>
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 3, 4)}"/>
                                            </div>
                                            <div style="margin-left: 20px;font-size: 0">
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 4, 5)}"/>
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 5, 6)}"/>
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 6, 7)}"/>
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 7, 8)}"/>
                                            </div>
                                            <div style="margin-left: 20px;font-size: 0">
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 8, 9)}"/>
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 9, 10)}"/>
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 10, 11)}"/>
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 11, 12)}"/>
                                            </div>
                                            <div style="margin-left: 20px;font-size: 0">
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 12, 13)}"/>
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 13, 14)}"/>
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 14, 15)}"/>
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 15, 16)}"/>
                                            </div>
                                            <div style="margin-left: 20px;font-size: 0">
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 16, 17)}"/>
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 17, 18)}"/>
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 18, 19)}"/>
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2"value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 19, 20)}"/>
                                            </div>
                                            <div style="margin-left: 20px;font-size: 0">
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 20, 21)}"/>
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 21, 22)}"/>
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 22, 23)}"/>
                                                <input readonly class="i-cert-code-three"
                                                       style="border: #000000 solid 1px !important;background-color: #f6f9f2" value="${fn:substring(map.insuranceOrder.insuranceOrderPolicyholderBank.accountNo, 23, 24)}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="div-tab" style="border: #FFFFFF solid 1px;">
                                <div class="div-line" style="border-bottom: solid 0px;padding-left: 5px;padding-top: 15px;">
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="title">
                                            <span class="d-body-font-three">投保人签名：</span>
                                        </div>
                                        <div class="a-input-div" style="width: 130px;">
                                            <span class="f-body-font-three">&nbsp;</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-three" style="border-left: solid 00px;">
                                        <div class="title">
                                            <span class="d-body-font-three">被保险人（或其法定监护人）签名：</span>
                                        </div>
                                        <div class="a-input-div" style="width: 150px;">
                                            <span class="body-font">&nbsp;</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="title">
                                            <span class="d-body-font-three">签署日期：</span>
                                        </div>
                                        <div class="a-input-div" style="width: 40px;">
                                            <span class="body-font">&nbsp;</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="title">
                                            <span class="d-body-font-three">年</span>
                                            <div class="a-input-div" style="width: 40px;">
                                                <span class="body-font">&nbsp;</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="div-line-con-three" style="border-left: solid 0px;margin-left: 20px;">
                                        <div class="title">
                                            <span class="d-body-font-three">月</span>
                                        </div>
                                        <div class="a-input-div" style="width: 50px;">
                                            <span class="body-font">&nbsp;</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="title">
                                            <span class="d-body-font-three">日</span>
                                        </div>
                                    </div>
                                </div>

                                <div class="div-line" style="border-bottom: solid 0px;padding-left: 5px;padding-top: 15px;">
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="title">
                                            <span class="c-body-font-three">业务人员签名：</span>
                                        </div>
                                        <div class="a-input-div" style="width: 150px;">
                                            <span class="f-body-font-three">&nbsp;</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-three" style="border-left: solid 00px;">
                                        <div class="title">
                                            <span class="c-body-font-three">业务人员电话：</span>
                                        </div>
                                        <div class="a-input-div" style="width: 200px;">
                                            <span class="body-font">&nbsp;</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-three" style="border-left: solid 0px;">
                                        <div class="title">
                                            <span class="c-body-font-three">签署地：</span>
                                        </div>
                                        <div class="a-input-div" style="width: 300px;">
                                            <span class="body-font">&nbsp;</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div style="height:1465px;">
                        <div class="hidden-prints">
                            <div style="padding-left: 400px;padding-top:40px;font-size: 25px;">
                                <span>
                                业务人员报告书
                                </span>
                            </div>
                            <div style="height: 50px;">

                            </div>
                            <div style="padding-left: 10px;font-size: 16px;">
                                <span>
                                以下内容并非保险合同组成部分，为本公司业务用栏，投保人无须填写
                                </span>
                            </div>
                            <div class="div-tab" style="margin-top: 5px;">
                                <div class="div-line" style="border-bottom: solid 0px;padding-left: 5px;padding-top: 5px;">
                                    <div class="div-line-con-four div-row-5" style="border-left: solid 0px;">
                                        <div class="title">
                                            <span class="f-body-font">被&nbsp;保&nbsp;险&nbsp;人&nbsp;姓&nbsp;名：</span>
                                        </div>
                                        <div class="a-input-div" style="width: 260px;">
                                            <span class="f-body-font">&nbsp;</span>
                                        </div>
                                        ;
                                    </div>
                                    <div class="div-line-con-four div-row-5" style="border-left: solid 0px;">
                                        <div class="title">
                                            <span class="f-body-font">投&nbsp;保&nbsp;人&nbsp;姓&nbsp;名：</span>
                                        </div>
                                        <div class="a-input-div" style="width: 260px;">
                                            <span class="body-font">&nbsp;</span>
                                        </div>
                                        ;
                                    </div>
                                </div>

                                <div class="div-line" style="border-bottom: solid 0px;">
                                    <div class="div-line-con-four">
                                        <div class="title"><span class="body-font">&nbsp;1.&nbsp;&nbsp;&nbsp;本人与投保人关系：</span></div>
                                        <div>
                                            <input readonly class="i-cb" type="checkbox"/><span class="body-font"> 家属</span>
                                            <input readonly class="i-cb" type="checkbox"/><span class="body-font"> 亲戚</span>
                                            <input readonly class="i-cb" type="checkbox"/><span class="body-font"> 朋友</span>
                                            <input readonly class="i-cb" type="checkbox"/><span class="body-font"> 其他：</span>
                                        </div>
                                        <div class="a-input-div" style="width: 570px;">
                                            <input readonly class="l-input" type="text"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="div-line" style="border-bottom: solid 0px;">
                                    <div class="div-line-con-four">
                                        <div class="title"><span class="body-font"> &nbsp;2.&nbsp;&nbsp;&nbsp;投保经过：</span></div>
                                        <div>
                                            <input readonly class="i-cb" type="checkbox"/> <span class="body-font"> 他人介绍</span>
                                            <input readonly class="i-cb" type="checkbox"/> <span class="body-font"> 陌生拜访</span>
                                            <input readonly class="i-cb" type="checkbox"/> <span class="body-font"> 投保人主动投保</span>
                                            <input readonly class="i-cb" type="checkbox"/> <span class="body-font">  其他：</span>
                                        </div>
                                        <div class="a-input-div" style="width: 500px;">
                                            <input readonly class="l-input" type="text"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="div-line" style="border-bottom: solid 0px;">
                                    <div class="div-line-con-four">
                                        <div class="title"><span class="body-font"> &nbsp;3.&nbsp;&nbsp;&nbsp;据本人观察，被保险人目前是否呈病态、有生理缺陷或因疾病、外伤而治疗中：</span>
                                        </div>
                                        <div>
                                            <input readonly class="i-cb" type="checkbox"/><span class="body-font"> 是</span>
                                            <input readonly class="i-cb" type="checkbox"/><span class="body-font"> 否</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line" style="border-bottom: solid 0px;">
                                    <div class="div-line-con-four">
                                        <div class="title"><span
                                                class="body-font">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如是，请说明：</span></div>
                                        <div class="a-input-div" style="width: 849px;">
                                            <input readonly class="l-input" type="text"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line" style="border-bottom: solid 0px;">
                                    <div class="div-line-con-four">
                                        <div class="title"><span class="body-font"> &nbsp;4.&nbsp;&nbsp;&nbsp;据本人了解，被保险人有吸烟、嗜酒或服用成瘾性药物?</span>
                                        </div>
                                        <div>
                                            <input readonly class="i-cb" type="checkbox"/> <span class="body-font">是</span>
                                            <input readonly class="i-cb" type="checkbox"/> <span class="body-font">否，</span>
                                        </div>
                                        <div>
                                            <div class="title"><span class="body-font">如是，请说明：</span></div>
                                            <div class="a-input-div" style="width: 390px;">
                                                <input readonly class="l-input" type="text"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line" style="border-bottom: solid 0px;">
                                    <div class="div-line-con-four">
                                        <div class="title"><span
                                                class="body-font">&nbsp;5.&nbsp;&nbsp;&nbsp;据本人了解，被保险人有兼职或从事特殊职业?</span></div>
                                        <div>
                                            <input readonly class="i-cb" type="checkbox"/> <span class="body-font">是</span>
                                            <input readonly class="i-cb" type="checkbox"/> <span class="body-font">否，</span>
                                        </div>
                                        <div>
                                            <div class="title"><span class="body-font">如是，请说明：</span></div>
                                            <div class="a-input-div" style="width: 445px;">
                                                <input readonly class="l-input" type="text"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line" style="border-bottom: solid 0px;">
                                    <div class="div-line-con-four">
                                        <div class="title"><span class="body-font">&nbsp;6.&nbsp;&nbsp;&nbsp;据本人了解，被保险人有从事具有危险性的运动或爱好?</span>
                                        </div>
                                        <div>
                                            <input readonly class="i-cb" type="checkbox"/> <span class="body-font">是</span>
                                            <input readonly class="i-cb" type="checkbox"/> <span class="body-font">否，</span>
                                        </div>
                                        <div>
                                            <div class="title"><span class="body-font">如是，请说明：</span></div>
                                            <div class="a-input-div" style="width: 389px;">
                                                <input readonly class="l-input" type="text"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line" style="border-bottom: solid 0px;">
                                    <div class="div-line-con-four">
                                        <div class="title"><span class="body-font">&nbsp;7.&nbsp;&nbsp;&nbsp;是否存在其它风险情况?</span>
                                        </div>
                                        <div>
                                            <input readonly class="i-cb" type="checkbox"/> <span class="body-font">是</span>
                                            <input readonly class="i-cb" type="checkbox"/> <span class="body-font">否，</span>
                                        </div>
                                        <div>
                                            <div class="title"><span class="body-font">如是，请说明：</span></div>
                                            <div class="a-input-div" style="width: 584px;">
                                                <input readonly class="l-input" type="text"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line" style="border-bottom: solid 0px;">
                                    <div class="div-line-con-four">
                                        <div>
                                            <div class="title"><span class="body-font">&nbsp;8.&nbsp;&nbsp;&nbsp;如受益人非被保险人的直系亲属，请向投保人和被保险人了解原因：</span>
                                            </div>
                                            <div class="a-input-div" style="width: 525px;">
                                                <input readonly class="l-input" type="text"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line" style="border-bottom: solid 0px;">
                                    <div class="div-line-con-four">
                                        <div>
                                            <div class="title"><span class="body-font">&nbsp;9.&nbsp;&nbsp;&nbsp;估计被保险收入：</span>
                                            </div>
                                            <div>
                                                <div class="a-input-div" style="width: 75px;">
                                                    <input readonly class="l-input" type="text"/>
                                                </div>
                                                <span class="body-font">/月，</span>
                                            </div>
                                            <div class="title"><span class="body-font">估计投保人收入：</span></div>
                                            <div>
                                                <div class="a-input-div" style="width: 75px;">
                                                    <input readonly class="l-input" type="text"/>
                                                </div>
                                                <span class="body-font">/月，</span>
                                            </div>
                                            <div class="title"><span class="body-font">估计被保险人财产值：</span></div>
                                            <div>
                                                <div class="a-input-div" style="width: 75px;">
                                                    <input readonly class="l-input" type="text"/>
                                                </div>
                                                <span class="body-font">; ，</span>
                                            </div>
                                            <div class="title"><span class="body-font">估计投保人财产值：</span></div>
                                            <div>
                                                <div class="a-input-div" style="width: 85px;">
                                                    <input readonly class="l-input" type="text"/>
                                                </div>
                                                <span class="body-font">。</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line" style="border-bottom: solid 0px;">
                                    <div class="div-line-con-four" style="padding-top: 30px;">
                                        <div>
                                            <div class="title"><span class="body-font">&nbsp;&nbsp;&nbsp;备注：</span></div>
                                            <div style="width: 80%;height: 190px;"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div style="height: 10px;">

                            </div>
                            <div class="div-tab" style="background-color: #f6f9f2;">
                                <div class="div-line">
                                    <div class="div-line-con-four">
                                        <div style="padding-bottom:8px;">
                                            <div style="float:left; "><span class="body-font">业务人员声明：</span></div>
                                            <div style="float:left; width: 89%;">
                                                <span class="c-body-font">
                                                 本人确认已就投保单中投保告知和健康告知的所有内容当面向投保人、被保险人询问和说明，见证经由投保人、被保险人亲自告知并亲笔签名无误；同时已向投保人提供了投保险种的保险条款，确认已就条款中各项内容特别是有关保险责任免除、免赔额、免赔率、比例赔付或给付或者减轻保险人责任的条款、保险期间、保险责任等待期、保险合同犹豫期、投保人相关权
                                                                                                                       利义务、是否提供保证续保以及续保有效时间、理赔程序以及理赔文件要求、欠款扣除、解除保险合同及保险合同的现金价值、
                                                </span>
                                                <span class="c-body-font" style="color: red;">
                                                                                                                      产品说明书
                                                </span>
                                                <span class="c-body-font">
                                                                                                                      、 人身保险投保提示书等向投保人作了详细说明。本栏告知属实。如有代签名、不实见证或虚假报告等情形而招致纠纷，本人愿承担由此引起的一切法律责任。
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line">
                                    <div class="div-line-con-four" style="padding-top: 20px;">
                                        <div class="div-line-con-four div-row-3" style="border-left: solid 0px;">
                                            <div class="title"><span class="body-font"> 业务人员编号：</span></div>
                                        </div>
                                        <div class="div-line-con-four div-row-3" style="border-left: solid 0px;">
                                            <div class="title"><span class="body-font"> 业务人员签名：</span></div>
                                        </div>
                                        <div class="div-line-con-four div-row-3" style="border-left: solid 0px;">
                                            <div class="title"><span class="body-font"> 日期:</span></div>
                                            <div style="padding-left: 40px;">
                                                <div style="width: 40px;">
                                                </div>
                                                <span class="body-font">年</span>
                                                <div style="width: 40px;">
                                                </div>
                                                <span class="body-font">月</span>
                                                <div style="width: 40px;">
                                                </div>
                                                <span class="body-font">日</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="div-line-con-four">
                                        <div class="div-line-con-four div-row-3" style="border-left: solid 0px;padding-top: 10px;">
                                            <div class="title"><span class="body-font">中介公司签章：</span></div>
                                            <div style="width: 80%;height: 25px;"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style="height: 15px;">

                            </div>

                            <div style="padding-left: 420px;padding-top: 30px;padding-bottom: 10px;">
                                <h3><span style="font-size: 25px;">附&nbsp;件&nbsp;签&nbsp;收&nbsp;栏</span></h3></div>
                            <div class="div-tab">
                                <div class="div-line">
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title" style="padding-left: 60px;"><span class="b-body-font">附件名称</span></div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title " style="padding-left: 60px;"><span class="b-body-font">种类</span></div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title " style="padding-left: 60px;"><span class="b-body-font">页数</span></div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title" style="padding-left: 60px;"><span class="b-body-font">接收人签名</span></div>
                                    </div>
                                </div>
                                <div class="div-line">
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title" style="padding-left: 60px;"><span class="a-body-font">&nbsp;</span></div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title " style="padding-left: 30px;">
                                            <input readonly class="i-cb" type="checkbox"/> <span class="a-body-font">原件</span>
                                            <input readonly class="i-cb" type="checkbox"/> <span class="a-body-font">复印件</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title " style="padding-left: 60px;"><span class="a-body-font">&nbsp;</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title " style="padding-left: 60px;"><span class="a-body-font">&nbsp;</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line">
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title" style="padding-left: 60px;"><span class="a-body-font">&nbsp;</span></div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title " style="padding-left: 30px;">
                                            <input readonly class="i-cb" type="checkbox"/> <span class="a-body-font">原件</span>
                                            <input readonly class="i-cb" type="checkbox"/> <span class="a-body-font">复印件</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title " style="padding-left: 60px;"><span class="a-body-font">&nbsp;</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title " style="padding-left: 60px;"><span class="a-body-font">&nbsp;</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line">
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title" style="padding-left: 60px;"><span class="a-body-font">&nbsp;</span></div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title " style="padding-left: 30px;">
                                            <input readonly class="i-cb" type="checkbox"/> <span class="a-body-font">原件</span>
                                            <input readonly class="i-cb" type="checkbox"/> <span class="a-body-font">复印件</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title " style="padding-left: 60px;"><span class="a-body-font">&nbsp;</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title " style="padding-left: 60px;"><span class="a-body-font">&nbsp;</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line">
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title" style="padding-left: 60px;"><span class="a-body-font">&nbsp;</span></div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title " style="padding-left: 30px;">
                                            <input readonly class="i-cb" type="checkbox"/> <span class="a-body-font">原件</span>
                                            <input readonly class="i-cb" type="checkbox"/> <span class="a-body-font">复印件</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title " style="padding-left: 60px;"><span class="a-body-font">&nbsp;</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title " style="padding-left: 60px;"><span class="a-body-font">&nbsp;</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line">
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title" style="padding-left: 60px;"><span class="a-body-font">&nbsp;</span></div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title " style="padding-left: 30px;">
                                            <input readonly class="i-cb" type="checkbox"/> <span class="a-body-font">原件</span>
                                            <input readonly class="i-cb" type="checkbox"/> <span class="a-body-font">复印件</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title " style="padding-left: 60px;"><span class="a-body-font">&nbsp;</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title " style="padding-left: 60px;"><span class="a-body-font">&nbsp;</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="div-line">
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title" style="padding-left: 60px;"><span class="d-body-font">&nbsp;&nbsp;&nbsp;总计</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title " style="padding-left: 60px;"><span class="d-body-font">&nbsp;</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title " style="padding-left: 60px;"><span class="d-body-font">&nbsp;</span>
                                        </div>
                                    </div>
                                    <div class="div-line-con-four div-win-2">
                                        <div class="title" style="padding-left: 60px;"><span class="d-body-font">&nbsp;</span></div>
                                    </div>
                                </div>
                            </div>

                            <div style="padding-top: 60px;padding-left: 300px;">
                                <span style="font-size: 25px;">全国统一客服热线：&nbsp;&nbsp;&nbsp;95535/4008-200-035</span>
                            </div>
                        </div>
                    </div>
                    <!-- 第四页-->


                    <%--附加页--%>
                    <c:forEach items="${map.matter_remark}" var="matterRemark">
                    <div style="height: 1460px">
                        <div style="font-family: 宋体;font-size: 20px;">
                            <div class="header">
                                <img src="../images/orderHeader/newlogo.png" width="650px"/>
                                <h2 style="margin-top: 20px;">
                                    <div style="position:absolute;margin-left: 60px;"></div>
                                    <div style="display: inline-block;letter-spacing: 8px;">
                                        投保或合同变更补充声明
                                    </div>
                                </h2>
                            </div>
                            <div style="margin-top: 60px;text-align: center;">
                                <div class="s-head-name">
                                    <div class="s-name">
                                        <div>被保险人姓名</div>
                                        <div class="s-name-content">${map.insuranceOrder.insuranceOrderInsured.insuredName}</div>
                                    </div>
                                    <div class="s-name" style="text-align: right">
                                        <div>投保人姓名</div>
                                        <div class="s-name-content">${map.insuranceOrder.insuranceOrderPolicyholder.policyholderName}</div>
                                    </div>
                                </div>
                                <div>
                                    <div class="s-contact-title">投保单编号/保险合同号码</div>
                                    <div class="s-contact-num">
                                        <div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div style="padding-right: 5px;"></div>
                                    </div>
                                </div>

                                <div class="s-insure-content">
                                    <div style="height: 90px;border: #000000 1px solid;background-color: #FFFBD6;margin: -1px;box-sizing: border-box;">
                                        <div style="text-align: left;padding: 18px;line-height: 1.5;font-size: 18px;text-indent: 2rem;">
                                            本人对福德生命人寿保险股份有限公司的投保或合同变更告知有下列补充和修改。本人明白并同意这些补充和修改是保险合同成立或变更的依据，并成为保险合同的组成部分。
                                        </div>
                                    </div>
                                    <div>
                                        <div style="height: 910px;box-sizing: border-box;">
                                            <c:forEach items="${matterRemark}" var="matterRemarks">
                                            <div style="display: flex;padding: 17px 10px;font-size: 15px;text-align: left;">
                                                <div style="display: flex; flex: 0 0 20px;">
                                                        <%--C1.--%>
                                                        ${matterRemarks.title}
                                                </div>
                                                <div>
                                                    <div style="display: flex">
                                                        <div style="display: inline-block;vertical-align: top;">
                                                                <%--被保人：--%>
                                                                ${matterRemarks.person}
                                                        </div>
                                                        <div style="display: inline-block;width: 803px;height:34px">
                                                                <%--哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈或或或或哈哈哈哈哈哈哈哈哈哈或或或或哈哈哈哈哈哈哈哈哈哈哈哈哈或或或或哈哈哈哈哈哈哈哈哈哈或或或或哈哈哈哈哈哈哈哈哈哈--%>
                                                                ${matterRemarks.remark}
                                                        </div>
                                                    </div>
                                                        <%--<div style="display: flex">--%>
                                                        <%--<div style="display: inline-block;vertical-align: top;">--%>
                                                        <%--投保人：--%>
                                                        <%--</div>--%>
                                                        <%--<div style="display: inline-block;width: 803px;;height:34px">--%>
                                                        <%--哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈或或或或哈哈哈哈哈哈哈哈哈哈或或或或哈哈哈哈哈哈哈哈哈哈哈哈哈或或或或哈哈哈哈哈哈哈哈哈哈或或或或哈哈哈哈哈哈哈哈哈哈--%>
                                                        <%--</div>--%>
                                                        <%--</div>--%>
                                                </div>
                                            </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>

                                <div class="s-insure-end">
                                    <div class="s-head-name">
                                        <div class="s-name" style="width: 600px;">
                                            <div>被保险人签名（或者法定监护人签名）</div>
                                            <div class="s-name-content" style="width: 200px;">&nbsp;</div>
                                        </div>
                                        <div class="s-name" style="text-align: right;width: 300px;">
                                            <div>投保人签名</div>
                                            <div class="s-name-content" style="width: 150px;">&nbsp;</div>
                                        </div>
                                    </div>
                                    <div class="s-head-name" style="margin-top: 20px;">
                                        <div class="s-name" style="width: 600px;">
                                            <div>见证销售人员签名</div>
                                            <div class="s-name-content" style="width: 200px;">&nbsp;</div>
                                        </div>
                                        <div class="s-name" style="text-align: right;width: 300px;">
                                            <div>签署日期</div>
                                            <div class="s-name-date" style="width: 80px;">&nbsp;</div>
                                            <div>年</div>
                                            <div class="s-name-date" style="width: 40px;">&nbsp;</div>
                                            <div>月</div>
                                            <div class="s-name-date" style="width: 40px;">&nbsp;</div>
                                            <div>日</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                            <%--<%@ include file="thirdPages.jsp" %>--%>
                            <%--<%@ include file="fourthPages.jsp" %>--%>
                            <%--<%@ include file="supplementPinter.jsp" %>--%>
                    </div>
                    </c:forEach>
                    </div>

            </div>
        </div>
        <!-- End #content -->
    </div>
    <!-- End #main -->
</div>

</body>
</html>
