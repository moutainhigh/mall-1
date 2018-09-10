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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="../css/prints.css">

    <title></title>

    <script type="text/javascript">
        function daYin() {
            $("#prints").jqprint();
        }
    </script>
</head>
<div>
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
                <!-- Start .第四页第二层-->
                <!-- Start .第二层-->

                <!-- End .第二层-->


                <!-- Start .第四页第一层-->
                <!-- Start .第一层-->
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
                                    <input class="i-cb" type="checkbox"/><span class="body-font"> 家属</span>
                                    <input class="i-cb" type="checkbox"/><span class="body-font"> 亲戚</span>
                                    <input class="i-cb" type="checkbox"/><span class="body-font"> 朋友</span>
                                    <input class="i-cb" type="checkbox"/><span class="body-font"> 其他：</span>
                                </div>
                                <div class="a-input-div" style="width: 570px;">
                                    <input class="l-input" type="text"/>
                                </div>
                            </div>
                        </div>

                        <div class="div-line" style="border-bottom: solid 0px;">
                            <div class="div-line-con-four">
                                <div class="title"><span class="body-font"> &nbsp;2.&nbsp;&nbsp;&nbsp;投保经过：</span></div>
                                <div>
                                    <input class="i-cb" type="checkbox"/> <span class="body-font"> 他人介绍</span>
                                    <input class="i-cb" type="checkbox"/> <span class="body-font"> 陌生拜访</span>
                                    <input class="i-cb" type="checkbox"/> <span class="body-font"> 投保人主动投保</span>
                                    <input class="i-cb" type="checkbox"/> <span class="body-font">  其他：</span>
                                </div>
                                <div class="a-input-div" style="width: 500px;">
                                    <input class="l-input" type="text"/>
                                </div>
                            </div>
                        </div>

                        <div class="div-line" style="border-bottom: solid 0px;">
                            <div class="div-line-con-four">
                                <div class="title"><span class="body-font"> &nbsp;3.&nbsp;&nbsp;&nbsp;据本人观察，被保险人目前是否呈病态、有生理缺陷或因疾病、外伤而治疗中：</span>
                                </div>
                                <div>
                                    <input class="i-cb" type="checkbox"/><span class="body-font"> 是</span>
                                    <input class="i-cb" type="checkbox"/><span class="body-font"> 否</span>
                                </div>
                            </div>
                        </div>
                        <div class="div-line" style="border-bottom: solid 0px;">
                            <div class="div-line-con-four">
                                <div class="title"><span
                                        class="body-font">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如是，请说明：</span></div>
                                <div class="a-input-div" style="width: 849px;">
                                    <input class="l-input" type="text"/>
                                </div>
                            </div>
                        </div>
                        <div class="div-line" style="border-bottom: solid 0px;">
                            <div class="div-line-con-four">
                                <div class="title"><span class="body-font"> &nbsp;4.&nbsp;&nbsp;&nbsp;据本人了解，被保险人有吸烟、嗜酒或服用成瘾性药物?</span>
                                </div>
                                <div>
                                    <input class="i-cb" type="checkbox"/> <span class="body-font">是</span>
                                    <input class="i-cb" type="checkbox"/> <span class="body-font">否，</span>
                                </div>
                                <div>
                                    <div class="title"><span class="body-font">如是，请说明：</span></div>
                                    <div class="a-input-div" style="width: 390px;">
                                        <input class="l-input" type="text"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="div-line" style="border-bottom: solid 0px;">
                            <div class="div-line-con-four">
                                <div class="title"><span
                                        class="body-font">&nbsp;5.&nbsp;&nbsp;&nbsp;据本人了解，被保险人有兼职或从事特殊职业?</span></div>
                                <div>
                                    <input class="i-cb" type="checkbox"/> <span class="body-font">是</span>
                                    <input class="i-cb" type="checkbox"/> <span class="body-font">否，</span>
                                </div>
                                <div>
                                    <div class="title"><span class="body-font">如是，请说明：</span></div>
                                    <div class="a-input-div" style="width: 445px;">
                                        <input class="l-input" type="text"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="div-line" style="border-bottom: solid 0px;">
                            <div class="div-line-con-four">
                                <div class="title"><span class="body-font">&nbsp;6.&nbsp;&nbsp;&nbsp;据本人了解，被保险人有从事具有危险性的运动或爱好?</span>
                                </div>
                                <div>
                                    <input class="i-cb" type="checkbox"/> <span class="body-font">是</span>
                                    <input class="i-cb" type="checkbox"/> <span class="body-font">否，</span>
                                </div>
                                <div>
                                    <div class="title"><span class="body-font">如是，请说明：</span></div>
                                    <div class="a-input-div" style="width: 389px;">
                                        <input class="l-input" type="text"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="div-line" style="border-bottom: solid 0px;">
                            <div class="div-line-con-four">
                                <div class="title"><span class="body-font">&nbsp;7.&nbsp;&nbsp;&nbsp;是否存在其它风险情况?</span>
                                </div>
                                <div>
                                    <input class="i-cb" type="checkbox"/> <span class="body-font">是</span>
                                    <input class="i-cb" type="checkbox"/> <span class="body-font">否，</span>
                                </div>
                                <div>
                                    <div class="title"><span class="body-font">如是，请说明：</span></div>
                                    <div class="a-input-div" style="width: 584px;">
                                        <input class="l-input" type="text"/>
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
                                        <input class="l-input" type="text"/>
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
                                            <input class="l-input" type="text"/>
                                        </div>
                                        <span class="body-font">/月，</span>
                                    </div>
                                    <div class="title"><span class="body-font">估计投保人收入：</span></div>
                                    <div>
                                        <div class="a-input-div" style="width: 75px;">
                                            <input class="l-input" type="text"/>
                                        </div>
                                        <span class="body-font">/月，</span>
                                    </div>
                                    <div class="title"><span class="body-font">估计被保险人财产值：</span></div>
                                    <div>
                                        <div class="a-input-div" style="width: 75px;">
                                            <input class="l-input" type="text"/>
                                        </div>
                                        <span class="body-font">; ，</span>
                                    </div>
                                    <div class="title"><span class="body-font">估计投保人财产值：</span></div>
                                    <div>
                                        <div class="a-input-div" style="width: 85px;">
                                            <input class="l-input" type="text"/>
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
                                    <input class="i-cb" type="checkbox"/> <span class="a-body-font">原件</span>
                                    <input class="i-cb" type="checkbox"/> <span class="a-body-font">复印件</span>
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
                                    <input class="i-cb" type="checkbox"/> <span class="a-body-font">原件</span>
                                    <input class="i-cb" type="checkbox"/> <span class="a-body-font">复印件</span>
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
                                    <input class="i-cb" type="checkbox"/> <span class="a-body-font">原件</span>
                                    <input class="i-cb" type="checkbox"/> <span class="a-body-font">复印件</span>
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
                                    <input class="i-cb" type="checkbox"/> <span class="a-body-font">原件</span>
                                    <input class="i-cb" type="checkbox"/> <span class="a-body-font">复印件</span>
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
                                    <input class="i-cb" type="checkbox"/> <span class="a-body-font">原件</span>
                                    <input class="i-cb" type="checkbox"/> <span class="a-body-font">复印件</span>
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
                <!-- End .第一层-->
            </div>
            <!-- End #content -->
        </div>
        <!-- End #main -->
    </div>
</div>
</body>
</html>
