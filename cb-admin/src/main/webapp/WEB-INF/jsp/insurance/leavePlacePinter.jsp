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
    <link rel="stylesheet" type="text/css" href="../css/printer2.css">
    <title>离开投保地问卷</title>
    <script type="text/javascript">
    function foreground() {
    $("#foreground").jqprint();
    }
    function printAll() {
        $("#content").jqprint();
    }
    </script>
</head>
<body><c:choose><c:when test="${orderOffsite==null}"><div class="noorderOffsite">异地投保问卷为空，<br/>无需打印，请核实 </div></c:when><c:otherwise>
    <div class="btn"><button onclick="foreground()">填空打印</button><button onclick="printAll()">完全打印</button></div>
<div id="content">
<div class="background">
    <div class="head">
        <div class="logo"><img src="../images/orderHeader/newlogo.png" height="51px"></div>
        <div class="barcode">
            <img src="data:image/jpeg;base64,${insuranceOrder.barCode}" height="50px">
            <div class="barcode_num">${insuranceOrder.orderCode}</div>
        </div>
    </div>
    <div class="cleanfx"></div>
    <div class="title">
        <span>离开投保地问卷</span>
    </div>
    <div class="content">
        <div class="t1 border_all">
            <div class="t1_head border_bottom">
                <div class="t1_head_col1">
                    <div class="t1_head_col1_name"><span>姓名</span></div>
                    <div class="t1_head_col1_role border_left">
                        <div class="border_bottom">□投保人</div>
                        <div>□被保险人</div>
                    </div>
                </div>
                <div class="t1_head_col2 border_left border_right"><span>证件号码</span></div>
                <div class="t1_head_col3"><span>投保单编号/保险合同号码</span></div>
            </div>
            <div class="t1_body">
                <div >&nbsp;</div>
                <div class="border_left border_right">&nbsp;</div>
                <div>&nbsp;</div>
            </div>
        </div>
        <div class="t3">
            <div class="t3_title">
                请逐条回答下列问题，并将答案写在相应问题后面。
            </div>
            <div class="t3_questions border_all">
                <div>1、您的户籍所在地是哪里？</div>
                <div class="indent" style="height: 90px">&nbsp;</div>
                <div>2、您目前工作所在城市或地区名？单位名称？工作单位所属行业？您的职务？</div>
                <div class="indent" style="height: 90px">&nbsp;</div>
                <div>3、请说明您离开投保地的原因？前往何地？出行目的？（如是工作或学习，请提供单位或学校的 名称和地址，并详细告知工作内容）</div>
                <div class="indent" style="height: 90px">&nbsp;</div>
                <div>4、您一年中平均在投保地逗留的时间多长？每次回投保地的时间间隔多久？您往来投保地和上述 异地之间经常乘坐的交通工具是什么？ </div>
                <div class="indent" style="height: 90px">&nbsp;</div>
                <div>5、您在投保地或异地是否已落实居住住所？如已落实请简述居住地址、环境？ </div>
                <div class="indent" style="height: 90px">&nbsp;</div>
                <div>6、是否有其他需要说明事项：□是  □否</div>
                <div class="indent" style="height: 90px">&nbsp;</div>
            </div>
            <div class="t5">
                <div class="declare">
                    <strong>投保人 / 被保险人声明：</strong>
                </div>
                <div class="declare_text">
                    <strong >本人谨此代表本人及被保险人声明及同意：对此问卷的各项要求均已了解，所填各事项均属 事实并确无欺瞒。上述一切陈述及本声明将成为签发保单和保全、理赔批单的依据，并与投保单 保全、理赔批单一起作为保险合同的组成部分。</strong>
                </div>
                <div class="autograph">
                    <div class="autograph1">
                        <div class="firstLine">被保险人签名：<br/>
                            <span>（或其法定监护人签名）</span>
                        </div>
                        <div>投保人签名：</div>
                    </div>
                    <div class="autograph2">
                        <div class="firstLine">见证业务员签名： </div>
                        <div>签署日期：______年 ___ 月 ___ 日 </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="foreground" id="foreground">
    <div class="head">
        <div class="logo">&nbsp;</div>
        <div class="barcode">&nbsp;
            <div class="barcode_num">&nbsp;</div>
        </div>
    </div>
    <div class="cleanfx"></div>
    <div class="title">
        <span>&nbsp;</span>
    </div>
    <div class="content">
        <div class="t1">
            <div class="t1_head">
                <div class="t1_head_col1">
                    <div class="t1_head_col1_name"><span>&nbsp;</span></div>
                    <div class="t1_head_col1_role ">
                        <div class="">√</div>
                        <div>
                            <c:choose><c:when test="${insuranceOrder.insuranceOrderPolicyholder.policyholderName==insuranceOrder.insuranceOrderInsured.insuredName}">√</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose>
                        </div>
                    </div>
                </div>
                <div class="t1_head_col2  "><span>&nbsp;</span></div>
                <div class="t1_head_col3"><span>&nbsp;</span></div>
            </div>
            <div class="t1_body">
                <div><span>${insuranceOrder.insuranceOrderPolicyholder.policyholderName}</span></div>
                <div class=" "><span>${insuranceOrder.insuranceOrderPolicyholder.policyholderCardNo}</span></div>
                <div><span>${insuranceOrder.contractNo}</span></div>
            </div>
        </div>
        <div class="t3">
            <div class="t3_title">
                &nbsp;
            </div>
            <div class="t3_questions">
                <div>&nbsp;</div>
                <div class="indent">${orderOffsite.sensue}</div>
                <div>&nbsp;</div>
                <div class="indent" >${orderOffsite.workplace}</div>
                <div>&nbsp;</div>
                <div>&nbsp;</div>
                <div  class="indent" >${orderOffsite.leaveReason}</div>
                <div>&nbsp; </div>
                <div>&nbsp; </div>
                <div class="indent">${orderOffsite.stayTime}</div>
                <div>&nbsp; </div>
                <div class="indent">${orderOffsite.offsiteAddress}</div>
                <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <c:choose><c:when test="${orderOffsite.otherMatter==null||orderOffsite.otherMatter==''}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;√</c:when><c:otherwise>√</c:otherwise></c:choose>
                </div>
                <div class="indent">${orderOffsite.otherMatter}</div>
            </div>
            <div class="t5">
                <div class="declare">
                    <strong>&nbsp;</strong>
                </div>
                <div class="declare_text">
                    <div><strong >&nbsp;</strong></div>
                    <div><strong >&nbsp;</strong></div>
                    <div><strong >&nbsp;</strong></div>
                </div>
                <div class="autograph">
                    <div class="autograph1">
                        <div class="firstLine">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>

                        </div>
                        <div style="overflow: hidden; height:40px" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="${insuranceOrder.insuranceOrderPolicyholder.policyholderSign}" style="transform:rotate(-90deg);position:  absolute;margin-top:  -20px;" height="67px" alt=""></div>
                    </div>
                    <div class="autograph2">
                        <div class="firstLine">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </div>
                        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
    </c:otherwise>
</c:choose>

</body>
</html>
