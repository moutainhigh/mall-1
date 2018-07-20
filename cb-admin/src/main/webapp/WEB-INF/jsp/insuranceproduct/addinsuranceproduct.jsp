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



        /**
         *上传图片
         */
        function onchangeImg(imgId){
            var formData = new FormData();
            formData.append("file", $('#upload')[0].files[0]);
            $.ajax({
                url: "/admin/uploads/upload/INSURANCEPRODUCT.do",
                type: 'POST',
                cache: false,
                data: formData,
                processData: false,
                contentType: false,
                success: function (result) {
                        $('#'+imgId).val(result.url);
                },
                error: function (err) {
                }
            });
        }
        $(function () {
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
        });

        var idcIndex = 0;

        function removeCommodity(indx) {
            $("#commodity" + indx).remove();
            idcIndex--;
        }

        function showCommodityDialog() {
            $('#commodityDialog').modal();
        }

        function chooseCommodity() {
            var selectedCommodityIds=$("#commodityGrid input[type='checkbox'][name='selectedCommodityId']:checked");
            if(selectedCommodityIds!=null&&selectedCommodityIds.length>0){
                $.each(selectedCommodityIds,function(n,selectedBox) {
                    var gridData = $("#commodityGrid").data("kendoGrid").dataSource;
                    var selectedCommodityId=$(selectedBox).attr('value');
                    $.each(gridData.data(),function(i,dataItem){
                        if(dataItem.matterId==selectedCommodityId){
                            var matterType='';
                            if(dataItem.matterType==0){
                                matterType="是否题";
                            }else{
                                matterType="填空题";
                            }
                            var newRow = "<tr id='commodity" + idcIndex + "'><td><input type='hidden' name='matterIds' value='"+selectedCommodityId+"'/>"+dataItem.matterDescription+"</td>" +
                                "<td>"+dataItem.matterGroup.description+"</td>" +
                                "<td>"+matterType+"</td>" +
                                "<td><a type='button' title='删除' class='btn btn-default' href='javascript:removeCommodity(" + idcIndex + ")'><i class='fa fa-minus-circle'></i></a></td></tr>";
                            $("#commodityTable tr:last").after(newRow);
                            idcIndex++;
                            return ;
                        }
                    });
                });
            }else{
                alert("请选择事项");
                return ;
            }
            clearCheck();
            $('#commodityDialog').modal("hide");
        }
    </script>
</head>
<body>

<jsp:include page="../layouts/left.jsp"/>
<jsp:include page="../layouts/sidebarRight.jsp"/>
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

                <form:form id="validateSubmitForm" action="addInsuranceProduct.do" cssClass="form-horizontal"
                           method="post"
                           commandName="insuranceProduct">
                    <fieldset>
                        <legend>保险产品</legend>
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
                                        <input id="upload" onchange="onchangeImg('prodImg')" name="file" multiple="multiple" accept="image/*" type="file"
                                               style="display: none"/>
                                    </div>
                                    <form:hidden path="prodImg" id="prodImg"/>
                                </div>
                                <div style="float: left;margin-left: 20px">
                                    <div style="padding-left: 5px"><span>产品详情图片</span></div>
                                    <div>
                                        <img id="headPic1" src="${insuranceProduct.descriptionImg}" width="150px"
                                             height="150px" style="padding: 5px">
                                        <input onchange="onchangeImg('descriptionImg')" id="upload1" name="file" multiple="multiple" accept="image/*" type="file"
                                               style="display: none"/>
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
                            <div class="spacer-30"></div>
                            <div class="row">
                                <div class="col-sm-8">
                                    <table id="commodityTable" class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th scope="col">事项描述</th>
                                            <th scope="col" width="140">事项组描述</th>
                                            <th scope="col" width="75">事项类型</th>
                                            <th scope="col" width="60">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="fc" items="${insuranceProduct.insuranceInformedMatters}">
                                            <tr id='commodity${fc.matterId}'>
                                                <td><input type='hidden' name='matterIds' class='form-control' value='${fc.matterId}'/>${fc.matterDescription}</td>
                                                <td>${fc.matterGroup.description}</td>
                                                <td><c:if test="${0 eq fc.matterType}">是否题</c:if>
                                                    <c:if test="${1 eq fc.matterType}">填空题</c:if></td>
                                                <td class='text-center'><a type='button' title='删除' class='btn btn-default' href='javascript:removeCommodity(${fc.matterId})'><i class='fa fa-minus-circle'></i></a></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-sm-2">
                                    <button type="button" onclick="showCommodityDialog();" title="添加" class="btn btn-default">
                                        <i class="fa fa-plus-circle"></i>添加事项
                                    </button>
                                </div>
                            </div>
                        </div>

                    </fieldset>
                </form:form>
                <div class="modal fade" id="commodityDialog" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog" style="width: 1000px;">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title">事项</h4>
                            </div>
                            <div class="modal-body">
                                <jsp:include page="../insuranceproduct/chooseMatter.jsp"/>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button class="btn btn-primary pull-right" onclick="chooseCommodity();">确认</button>
                            </div>
                        </div>
                    </div>
                </div>
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
