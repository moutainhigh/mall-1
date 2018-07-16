<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>保险产品详情</title>
    <style>
        table {
            border: solid 1px;
        }

        table tr {
            border: solid 1px;
        }

        table td {
            text-align: center;
            border: solid 1px;
            padding: 10px;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true
            });
        });
        //建立一個可存取到該file的url
        function getObjectURL(file) {
            var url = null;
            if (window.createObjectURL != undefined) { // basic
                url = window.createObjectURL(file);
            } else if (window.URL != undefined) { // mozilla(firefox)
                url = window.URL.createObjectURL(file);
            } else if (window.webkitURL != undefined) { // webkit or chrome
                url = window.webkitURL.createObjectURL(file);
            }
            return url;
        }

        function closeInfo(){
            history.go(0);
        }

        function addMatter(prodId,matterId) {
            bootbox.confirm("确认添加吗？", function (result) {
                if (result) {
                    debugger;
                    var formData = new FormData();
                    formData.append("prodId", prodId);
                    formData.append("matterId", matterId);
                    $.ajax({
                        url: "addMetterById.do",
                        type: 'GET',
                        data: {
                            "prodId": prodId,
                            "matterId": matterId
                        },
                        success: function (data) {
                            if (data) {
                                bootbox.alert("成功");
                                loadData();
                            } else {
                                bootbox.alert("失败");
                            }
                        },
                        error: function (err) {
                        }
                    });
                }
            });
        }

        function removeMatter(matterId, prodId) {
            bootbox.confirm("确认删除吗？", function (result) {
                if (result) {
                    debugger;
                    var formData = new FormData();
                    formData.append("prodId", prodId);
                    formData.append("matterId", matterId);
                    $.ajax({
                        url: "removeMetterById.do",
                        type: 'GET',
                        data: {
                            "prodId": prodId,
                            "matterId": matterId
                        },
                        success: function (data) {
                            if (data) {
                                bootbox.alert("成功");
                                history.go(0)
                            } else {
                                bootbox.alert("失败");
                            }
                        },
                        error: function (err) {
                        }
                    });
                }
            });
        }


        function loadData(){
            var matterDescription=$('#matterDescription').val();
            $.ajax({
                url: "getmatterList.do",
                type: 'GET',
                data: {
                    "prodId": ${insuranceProduct.prodId},
                    "matterDescription":matterDescription
                },
                success: function (data) {
                    var html='';
                    for (var i=0;i<data.length;i++)
                    {
                        var type='';
                        if(data[i].matterType==0){
                            type="是否题";
                        }else if(data[i].matterType==0){
                            type="填空题";
                        }
                        html+="<tr><td>"+data[i].matterId+"</td>"
                            +"<td>"+data[i].matterDescription+"</td>"
                            +"<td>"+type+"</td>"
                            +"<td><a href='javascript:void(0);' onclick='addMatter(${insuranceProduct.prodId},"+data[i].matterId+")' style='color:blue'>添加</a></td></tr>";
                    }
                    $("#thb").html(html);
                },
                error: function (err) {
                }
            });
        }
        $(function () {

            $('#addproductMatter').on('click', function() {
                var openNewLink = window.open('/admin/insuranceproduct/toaddMatter.do?prodId=${insuranceProduct.prodId}',
                    '添加', 'height=700, width=1500, top=100, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=false, location=no, status=no'); //参数： url, 名称, 窗体样式
                if(window.focus) {
                    openNewLink.focus();
                }
                return false;
            })

            $('img[name="viewImg"]').click(function () {
                var width = $(this).width();
                if (width == 200) {
                    $(this).width(600);
                    $(this).height(600);
                }
                else {
                    $(this).width(200);
                    $(this).height(200);
                }
            });

            $("#headPic").click(function () {
                $("#upload").click(); //隐藏了input:file样式后，点击头像就可以本地上传
                $("#upload").on("change", function () {
                    var objUrl = getObjectURL(this.files[0]); //获取图片的路径，该路径不是图片在本地的路径
                    if (objUrl) {
                        $("#headPic").attr("src", objUrl); //将图片路径存入src中，显示出图片
                    }
                });
            });

            $("#headPic1").click(function () {
                $("#upload1").click(); //隐藏了input:file样式后，点击头像就可以本地上传
                $("#upload1").on("change", function () {
                    var objUrl = getObjectURL(this.files[0]); //获取图片的路径，该路径不是图片在本地的路径
                    if (objUrl) {
                        $("#headPic1").attr("src", objUrl); //将图片路径存入src中，显示出图片
                    }
                });
            });

            //图片上传
            $("#upload_img").click(function () {
                var formData = new FormData();
                formData.append("file", $('#upload')[0].files[0]);
                $.ajax({
                    url: "/uploads/upload/INSURANCEPRODUCT.do",
                    type: 'POST',
                    cache: false,
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (result) {
                        debugger;
                        alert(result.info);
                        if (result.code == 0) {
                            $('#prodImg').val(result.url);
                        }
                    },
                    error: function (err) {
                    }
                });
            });


            //图片上传
            $("#upload_img1").click(function () {
                var formData = new FormData();
                formData.append("file", $('#upload')[0].files[0]);
                $.ajax({
                    url: "/uploads/upload/INSURANCEPRODUCT.do",
                    type: 'POST',
                    cache: false,
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (result) {
                        alert(result.info);
                        if (result.code == 0) {
                            $('#descriptionImg').val(result.url);
                        }
                    },
                    error: function (err) {
                    }
                });
            });
        });
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
<jsp:include page="../layouts/sidebarRight.jsp"/>
<!-- End aside -->

<!-- ********************************************
     * SIDEBAR SEC:                             *
     *                                          *
     * the part which contains things like      *
     * calendar, users, lists, blocks and       *
     * much more.                               *
     ******************************************** -->

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

    <!-- * Tabs can be removed, if so dont forget * -->
    <!-- * to remove the .tab-pane divs(wrapper). * -->

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

            <!-- ********** -->
            <!-- NEW MODULE -->
            <!-- ********** -->

            <div class="sidebar-module">
                <ul class="mini-list">
                    <li>
                        <img src="../images/users/user-1.jpg" id="" alt="" class="avatar"/>
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
                                <label class="line-through">Start project X <input type="checkbox" name=""
                                                                                   checked/><span></span></label>
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
                                <label class="line-through">Buy new apple<input type="checkbox" name=""
                                                                                checked/><span></span></label>
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
                <input class="knob" data-width="100" data-cursor="false" data-fgColor="#aaaaaa" data-bgColor="#cccccc"
                       data-thickness=".20"
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
                <input class="knob" data-width="100" data-cursor="false" data-fgColor="#aaaaaa" data-bgColor="#cccccc"
                       data-thickness=".20"
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
                <input class="knob" data-width="100" data-cursor="false" data-fgColor="#aaaaaa" data-bgColor="#cccccc"
                       data-thickness=".20"
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
                    <li><a href="#">首页 </a></li>
                    <li><a href="#">保单管理 </a></li>
                    <li><a href="#">保险产品管理</a></li>
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
                    <h2>保险产品</h2>
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
        <div style="display: none" id="matterDiv"></div>
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
                <!-- * data-asf-time = seconds, data-asf-expireafter = minutes * -->

                <form:form id="validateSubmitForm" action="updateInsuranceProduct.do" cssClass="form-horizontal"
                           method="post"
                           commandName="insuranceProduct">
                    <fieldset>
                        <legend>保险产品</legend>
                        <form:hidden path="prodId"/>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>产品名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input path="prodName" value="" cssClass="form-control validate[required,minSize[2]]"
                                            maxlength="32"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>保障年限：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input path="protectionYear"
                                            cssClass="form-control validate[required,minSize[1]]"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>保险期间：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input path="insurePeriod" cssClass="form-control validate[required,minSize[1]]"/>
                            </div>
                            <div class="col-sm-2">
                                <label>标签：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input path="tags" cssClass="form-control validate[required,minSize[1]]"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>投保须知：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input path="instruction" cssClass="form-control validate[required,minSize[2]]"/>
                            </div>
                            <div class="col-sm-2">
                                <label>产品描述：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:textarea path="description" cssClass="form-control validate[required,minSize[2]]"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="spacer-10"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label>图片：</label>
                            </div>
                            <div class="col-sm-3">
                                <div style="float: left">
                                    <div style="padding-left: 5px"><span>产品图片</span></div>
                                    <div>
                                        <img id="headPic" src="${insuranceProduct.prodImg}" width="150px" height="150px"
                                             style="padding: 5px">
                                        <input id="upload" name="file" multiple="multiple" accept="image/*" type="file"
                                               style="display: none"/>
                                    </div>
                                    <div style="margin-top: 10px;padding-left: 5px">
                                        <button id="upload_img" type="button">确定上传</button>
                                    </div>
                                    <form:hidden path="prodImg" id="prodImg"/>
                                </div>
                                <div style="float: left;margin-left: 20px">
                                    <div style="padding-left: 5px"><span>产品详情图片</span></div>
                                    <div>
                                        <img id="headPic1" src="${insuranceProduct.descriptionImg}" width="150px"
                                             height="150px" style="padding: 5px">
                                        <input id="upload1" name="file" multiple="multiple" accept="image/*" type="file"
                                               style="display: none"/>
                                    </div>
                                    <div style="margin-top: 10px;padding-left: 5px">
                                        <button id="upload_img1" type="button">确定上传</button>
                                    </div>
                                    <form:hidden path="descriptionImg" id="descriptionImg"/>
                                </div>

                                <div class="spacer-30"></div>
                                <hr>
                                <div class="spacer-30"></div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="btn-group pull-right">
                                            <button class="btn btn-default"><i class="fa fa-save"></i>&nbsp;保&nbsp;存&nbsp;
                                            </button>
                                            <button type="reset" class="btn btn-default"><i class="fa fa-reply"></i>&nbsp;重&nbsp;置&nbsp;
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>

                        <legend>产品告知事项</legend>
                        <div class="row" style="margin-left: 15px;">
                            <button type="button" onclick="loadData()" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                                添加告知事项
                            </button>
                            <!-- 模态框 -->
                            <div class="modal fade" id="myModal">
                                <div class="modal-dialog">
                                    <div class="modal-content" style="width: 1700px;margin-left: -540px">
                                        <!-- 模态框头部 -->
                                        <div class="modal-header">
                                            <button type="button" class="close" onclick="closeInfo()" data-dismiss="modal">&times;</button>
                                        </div>
                                        <!-- 模态框主体 -->
                                        <div class="modal-body">
                                            <div class="row" style="margin-top: 20px">
                                                <div class="col-sm-2">
                                                    <label>事项描述：</label>
                                                </div>
                                                <div class="col-sm-3">
                                                    <input type="texr"  id="matterDescription" ></input>
                                                </div>
                                                <div class="col-sm-3">
                                                    <button type="button" class="btn btn-default" onclick="loadData()"><i class="fa fa-search"></i>查询</button>
                                                </div>

                                            </div>
                                            <table style="margin-top: 5px">
                                                <tr>
                                                    <td style="width: 100px">事项Id</td>
                                                    <td>事项描述</td>
                                                    <td style="width: 100px">事项类型</td>
                                                    <td style="width: 100px">操作</td>
                                                </tr>
                                                <tbody id="thb">
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row" style="margin-top: 15px;margin-left: 15px;;">
                            <table>
                                <tr>
                                    <td>事项Id</td>
                                    <td style="width: 900px">事项描述</td>
                                    <td>事项类型</td>
                                    <td>操作</td>
                                </tr>
                                <c:forEach items="${insuranceProduct.insuranceInformedMatters}" var="matter">
                                    <tr>
                                        <td>${matter.matterId}</td>
                                        <td>
                                                ${matter.matterDescription}
                                        </td>
                                        <td>
                                            <c:if test="${0 eq matter.matterType}">是否题</c:if>
                                            <c:if test="${1 eq matter.matterType}">填空题</c:if>
                                        </td>
                                        <td>
                                            <a href='/insuranceInformedMatter/toEditMatter.do?matterId=${matter.matterId}'
                                               style='color:blue'>查看</a>
                                            <a href="javascript:void(0);"
                                               onclick="removeMatter('${matter.matterId}','${insuranceProduct.prodId}')"
                                               style='color:blue'>删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
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
