<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>负债管理</title>
    <script type="text/javascript">
        function confirm(){
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                $('#showLiabilitiesListDialog').modal();
            }
        }
    </script>
</head>
<body>
<jsp:include page="../../layouts/left.jsp"/>
<jsp:include page="../../layouts/sidebarRight.jsp"/>
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
                    <li><a href="#">财务管理</a></li>
                    <li class="active"><a href="#">负债管理</a></li>
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
                    <h2>负债管理</h2>
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
                <div class="toolbar responsive-helper">
                    <form style="width: 100%">
                        <div class="pull-left">
                            <div class="toolbar-field">
                                <strong>借款人:</strong>
                                <input type="hidden" id="withdrawIdHid" name="withdrawId">
                            </div>
                            <div class="toolbar-field">
                                <input type="text"  data-filter="customer.realName" data-operator="contains" class="form-control grid-filter" placeholder="请输入借款人"/>
                            </div>
                            <div class="toolbar-field">
                                <strong>借款人手机号 :</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text"  data-filter="customer.mobile" data-operator="contains" class="form-control grid-filter" placeholder="请输入借款人手机号"/>
                            </div>
                            <%--<div class="toolbar-field">
                                <strong>借款金额 :</strong>
                            </div>
                            <div class="toolbar-field">
                                <table>
                                    <tr>
                                        <td><input type="number" data-filter="amount" data-operator="gte" class="form-control grid-filter" style="width: 60px" placeholder="最小"/></td>
                                        <td>-</td>
                                        <td><input type="number" data-filter="amount" data-operator="lte" class="form-control grid-filter" style="width: 60px" placeholder="最大"/></td>
                                    </tr>
                                </table>
                            </div>--%>
                        </div>
                        <!-- End .pull-left -->
                        <div class="pull-right">
                            <div class="toolbar-field">
                                <button type="button" class="btn btn-default" onclick="reloadGridFilters('grid')"><i class="fa fa-search"></i>查询</button>
                                &nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-default" onclick="clearFilters('grid')">清空</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="spacer-10"></div>
                <div class="toolbar responsive-helper">
                    <header>
                        <div class="pull-left">
                            <h3>负债列表</h3>
                        </div>
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="javascript:void(0);" onclick="confirm()" class="btn btn-default"><i class="fa fa-info-circle"></i>&nbsp;查看</a>
                            </div>
                        </div>
                    </header>
                </div>
                <div class="table-wrapper">
                    <kendo:grid name="grid" pageable="true" sortable="true" filterable="true" selectable="true" height="450" resizable="true">
                        <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
                        <kendo:grid-filterable extra="false">
                            <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
                            <kendo:grid-filterable-operators>
                                <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                            </kendo:grid-filterable-operators>
                        </kendo:grid-filterable>
                        <kendo:grid-columns>
                            <kendo:grid-column field="loanBillId" width="350px" hidden="true"/>
                            <kendo:grid-column title="负债人" filterable="false" field="customer.realName" width="100px"/>
                            <kendo:grid-column title="负债人手机号" filterable="false" field="customer.mobile" width="100px"/>
                            <kendo:grid-column title="当前负债总金额" filterable="false" field="debtCredit+debtCar" width="100px" />
                            <kendo:grid-column title="我的负债" filterable="false" field="debtCredit" width="100px" />
                            <kendo:grid-column title="汽车贷款" filterable="false" field="debtCar" width="100px" />
                            <kendo:grid-column title="累计借款次数" filterable="false" field="frequency" width="100px" />
                            <kendo:grid-column title="累计借款金额" filterable="false" field="sumAmount" width="100px" />
                            <kendo:grid-column title="累计借款利息" filterable="false" field="loanInterest" width="100px" />
                        </kendo:grid-columns>
                        <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                            <kendo:dataSource-schema data="content" total="totalElements">
                            </kendo:dataSource-schema>
                            <kendo:dataSource-transport>
                                <kendo:dataSource-transport-read url="pageLiabilitiesList.do" type="POST" contentType="application/json"/>
                                <kendo:dataSource-transport-parameterMap>
                                    <script>
                                        function parameterMap(options, type) {
                                            return JSON.stringify(options);
                                        }
                                    </script>
                                </kendo:dataSource-transport-parameterMap>
                            </kendo:dataSource-transport>
                        </kendo:dataSource>
                    </kendo:grid>
                </div>
            </div>
            <div class="spacer-40"></div>
            <div class="hr-totop"><span>Top</span></div>
            <div class="spacer-40"></div>
        </div>
        <jsp:include page="../../layouts/footer.jsp"/>
    </div>
</div>
<div class="modal fade" id="showLiabilitiesListDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">还款明细</h4>
            </div>
            <div class="modal-body">
                <form>
                    <input type="hidden" data-filter="customerId" data-operator="contains" class="form-control grid-filter" value="${customer.customerId}" />
                    <input type="hidden" data-filter="type" data-operator="contains" class="form-control grid-filter" value="SUBTRACT" />
                <jsp:include page="../../finance/wallet/financialWalletDetail.jsp"/>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary pull-right" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
