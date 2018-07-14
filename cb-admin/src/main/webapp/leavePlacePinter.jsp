<%--
  Created by IntelliJ IDEA.
  User: xydn
  Date: 2018/7/13
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>离开投保地问卷</title>
    <style>
        body,div,span,table,tr,td{
            margin: 0;
            padding: 0;
        }
        td,th
        {
            border-collapse: collapse;
            border-spacing: 0;
        }
        .cleanfx{
            width: 936px;
            height: 0px;
            clear: none;
        }
        table{
            font-size: 22px;
            font-family: \5B8B\4F53;
        }
        body{
            width: 936px;
        }
        .background{
            margin: 0 auto;
            width: 936px;
        }
        .hide_div{
            display: none;
        }
        .head{
            height: 70px;
            width: 100%;
        }
        .logo{
            display: block;
            float: left;
            margin-left: 16px;
        }
        .barcode{
            display: block;
            float: right;
            margin-right: 68px;
        }
        .title{
            display: block;
            terxt-align: center;
            width: 936px;
            height: 30px;
        }
        .title span{
            display: block;
            margin:0 auto;
            width: 210px;
            height: 30px;
            font-size: 28px;
            font-family: \5B8B\4F53;
            font-weight: bold;
        }
        .content{
            width: 936px;
            margin-top: 20px;
        }
        .t1{
            width: 936px;
            height: 134px;
            border: 1px solid #000;
        }
        .t1_head{
            height: 64px;
            width: 936px;
            font-size: 21px;
            font-weight: bold;
        }
        .t1_head span{
            display: block;
           margin-top: 20px;
        }
        .border_bottom{
            border-bottom: 1px solid #000;
        }
        .border_left{
            border-left: 1px solid #000;
        }
        .border_right{
            border-right: 1px solid #000;
        }
        .border_top{
            border-top: 1px solid #000;
        }
        .border_none{
            border: none;
        }
        .t1_head_col1{
            height: 64px;
            width: 300px;
            float: left;
        }
        .t1_head_col1_name{
            height: 64px;
            width: 120px;
            float: left;
            text-align: center;
        }
        .t1_head_col1_role{
            height: 64px;
            width: 170px;
            float: left;
        }
        .t1_head_col1_role div{
            height: 30px;
            width: 180px;
            float: left;
        }
        .t1_head_col2{
            height: 64px;
            width: 300px;
            float: left;
            text-align: center;
        }
        .t1_head_col3{
            height: 64px;
            width: 330px;
            float: left;
            text-align: center;
        }
        .t1_body{
            height: 64px;
            width: 936px;
            font-size: 21px;
        }
        .t1_body div{
            height: 69px;
            width: 300px;
            float: left;
        }
        .t1_body span{
            display: block;
            margin-top: 20px;
            text-align: center;
        }
        .t3{
            width: 926px;
            margin: 0 auto;
        }
        .t3_title{
            margin-top: 21px;
            width: 926px;
            font-size: 21px;
        }
        .t3_questions{
            width: 926px;
            height: 780px;
            border: 1px solid #000;
            font-size: 21px;
        }
        .t3_questions div{
            padding-left: 5px;
            padding-right: 10px;
        }
        .t5{
            padding: 0 12px;
        }
        .declare{
            font-size: 21px;
        }
        .declare_text{
            text-indent: 2em;
            font-size: 21px;
        }
        .autograph{
            margin-top: 46px;
            width: 926px;
            height: 110px;
            font-size: 24px;
        }
        .autograph span{
            margin-top: 12px;
            font-size: 18px;
            display: block;
            float: none;
        }
        .autograph1{
            width: 460px;
            height: 110px;
            float: left;
        }
        .autograph2{
            width: 460px;
            height: 110px;
            float: left;
        }
        .firstLine{
            height: 66px;
            margin-bottom: 10px;
        }
        .foreground{
            position: absolute;
            top: 0;
            left: 0;
            z-index: 1;
        }
    </style>
</head>
<body>
<div class="background hide_div">
    <div class="head">
        <div class="logo"><img src="/images/files/logo.png"></div>
        <div class="barcode"><img src="/images/files/barcode2.png" height="70px"></div>
    </div>
    <div class="cleanfx"></div>
    <div class="title">
        <span>离开投保地问卷</span>
    </div>
    <div class="content">
        <div class="t1">
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
            <div class="t3_questions">
                <div>1、您的户籍所在地是哪里？</div>
                <div style="height: 90px">&nbsp;</div>
                <div>2、您目前工作所在城市或地区名？单位名称？工作单位所属行业？您的职务？</div>
                <div style="height: 90px">&nbsp;</div>
                <div>3、请说明您离开投保地的原因？前往何地？出行目的？（如是工作或学习，请提供单位或学校的 名称和地址，并详细告知工作内容）</div>
                <div style="height: 90px">&nbsp;</div>
                <div>4、您一年中平均在投保地逗留的时间多长？每次回投保地的时间间隔多久？您往来投保地和上述 异地之间经常乘坐的交通工具是什么？ </div>
                <div style="height: 90px">&nbsp;</div>
                <div>5、您在投保地或异地是否已落实居住住所？如已落实请简述居住地址、环境？ </div>
                <div style="height: 90px">&nbsp;</div>
                <div>6、是否有其他需要说明事项：□是  □否</div>
                <div style="height: 90px">&nbsp;</div>
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
<div class="foreground">
    <div class="head">
        <div class="logo">&nbsp;</div>
        <div class="barcode">&nbsp;</div>
    </div>
    <div class="cleanfx"></div>
    <div class="title">
        <span>&nbsp;</span>
    </div>
    <div class="content">
        <div class="t1">
            <div class="t1_head ">
                <div class="t1_head_col1">
                    <div class="t1_head_col1_name"><span>&nbsp;</span></div>
                    <div class="t1_head_col1_role ">
                        <div class="">&nbsp;</div>
                        <div>&nbsp;</div>
                    </div>
                </div>
                <div class="t1_head_col2 border_left border_right"><span>&nbsp;</span></div>
                <div class="t1_head_col3"><span>&nbsp;</span></div>
            </div>
            <div class="t1_body">
                <div><span>邓诚刚</span></div>
                <div class="border_left border_right"><span>430482198607270051</span></div>
                <div><span>515456885568</span></div>
            </div>
        </div>
        <div class="t3">
            <div class="t3_title">
                &nbsp;
            </div>
            <div class="t3_questions">
                <div>&nbsp;</div>
                <div style="height: 90px">我是中华人民共和国公民</div>
                <div>&nbsp;</div>
                <div style="height: 90px">abcad fd fas f ddsa f fda     </div>
                <div>&nbsp;</div>
                <div>&nbsp;</div>
                <div style="height: 90px">abcccc</div>
                <div>&nbsp; </div>
                <div>&nbsp; </div>
                <div style="height: 90px">我是中华人民共和国公民</div>
                <div>&nbsp; </div>
                <div style="height: 90px">我是中华人民共和国公民</div>
                <div>&nbsp; </div>
                <div style="height: 90px">我是中华人民共和国公民</div>
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
                        <div class="firstLine">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邓诚刚<br/>

                        </div>
                        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 邓诚刚</div>
                    </div>
                    <div class="autograph2">
                        <div class="firstLine">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邓诚刚 </div>
                        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2018&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;07&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;14  </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
