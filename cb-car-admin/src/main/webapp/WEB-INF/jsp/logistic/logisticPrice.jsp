<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>物流价格管理</title>

    <script type="text/javascript">
        $(document).ready(function () {


        });

        function returnLogistics() {
            window.location.href = "logistics.do";
        }

        function removePrice(priceId) {
            bootbox.confirm("确认删除吗?", function(result) {
                if(result){
                    $.get('removeLogisticPriceById.do', {priceId:priceId}, function(data) {
                        if(data){
                            $("#price"+priceId).remove();
                            commonNotify("删除成功！", "success");
                        }else{
                            commonNotify("物流价格已被关联，删除失败！", "error");
                        }
                    } );
                }
            });
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
                <a href="#" id="logo-small"><h4></h4><h5></h5></a>
            </div>
            <div class="pull-right">
                <a href="#" id="responsive-menu-trigger">
                    <i class="fa fa-bars"></i>
                </a>
            </div>
        </div>
        <div class="header-main-bottom">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">配送管理</a></li>
                    <li><a href="#">物流查询</a></li>
                    <li class="active">物流价格管理</li>
                </ul>
            </div>
            <div class="pull-right">
                <p>Version 1.0.0</p>
            </div>
        </div>
    </header>

    <div id="content" class="clearfix">
        <header id="header-sec">
            <div class="inner-padding">
                <div class="pull-left">
                    <h2>${logistic.logisticName} 价格列表</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default " href="logistics.do"><i class="fa fa-reply"></i></a>
                </div>
            </div>
        </header>
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
            <div class="inner-padding">

                <div class="comments-toolbar">
                    <div class="pull-left">
                        <h4>${logistic.logisticName} 价格列表</h4>
                    </div>
                    <div class="pull-right">
                        <div class="btn-group">
                            <a href="toAddLogisticPrice.do?logisticId=${logistic.logisticId}" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;添加价格</a>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <table class="table table-bordered ">
                            <thead>
                            <th style="width: 200px">首重重量</th>
                            <th style="width: 100px">首重价格</th>
                            <th style="width: 100px">续重价格</th>
                            <th scope="col" style="width: 100px" class="text-center">操作</th>
                            </thead>
                            <tbody>
                            <c:forEach var="price" items="${prices}">
                                <tr id="price${price.priceId}">
                                    <td>${price.weight}</td>
                                    <td>${price.weightPrice}</td>
                                    <td>${price.continuePrice}</td>
                                    <td class="text-center">
                                        <a href="toEditLogisticPrice.do?priceId=${price.priceId}&logisticId=${logistic.logisticId}" title="编辑" class=" btn-less"><i class="fa fa-edit"></i></a>
                                        <a href="javascript:removePrice(${price.priceId});" title="删除" class=" btn-less"><i class="fa fa-trash-o"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="spacer-40"></div>
            <div class="hr-totop"><span>Top</span></div>
            <div class="spacer-40"></div>
        </div>
        <jsp:include page="../layouts/footer.jsp"/>
    </div>
</div>
</body>
</html>
