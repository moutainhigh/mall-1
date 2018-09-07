<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row" style="margin-top:-20px;margin-bottom:20px">
    <div class="toolbar responsive-helper">
        <form>
            <div class="pull-left">
                <div class="toolbar-field">
                    <strong>还款类型:</strong>
                </div>
                <div class="toolbar-field">
                    <select data-filter="transactionType" data-operator="eq" class="form-control  grid-filter">
                        <option value="">全部</option>
                        <option value="INSURANCE_REPAYMENT">保险返利还款</option>
                        <option value="PRODUCT_RB_REPAYMENT">商品报账还款</option>
                        <option value="MANUAL_REPAYMENT">手动还款</option>
                        <option value="CAR_REPAYMENT">汽车返利还款</option>
                    </select>
                </div>
                <input type="hidden" data-filter="type" data-operator="contains" class="form-control grid-filter" value="SUBTRACT" hidden />
            </div>
            <!-- End .pull-left -->
            <div class="pull-right">
                <div class="toolbar-field">
                    <button type="button" class="btn btn-default" onclick="reloadGridFilters('financialLoanBillGrid')"><i class="fa fa-search"></i>查询</button>
                    &nbsp;&nbsp;&nbsp;
                    <button type="button" class="btn btn-default" onclick="clearFilters('financialLoanBillGrid')">清空</button>
                </div>
            </div>
            <!-- End .pull-right -->
        </form>
    </div>
    <div class="spacer-10"></div>
<%--
    <div class="toolbar responsive-helper">
        <header>
            <div class="pull-left">
                <h3>商品列表</h3>
            </div>
            <div class="pull-right">
            </div>
        </header>
    </div>--%>
    <div class="table-wrapper">
        <kendo:grid name="financialLoanBillGrid" pageable="true" sortable="true" filterable="true" selectable="true" height="350">
            <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="5"/>
            <kendo:grid-filterable extra="false">
                <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
                <kendo:grid-filterable-operators>
                    <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                    <kendo:grid-filterable-operators-date gte="小于" eq="等于" lte="大于"/>
                </kendo:grid-filterable-operators>
            </kendo:grid-filterable>
            <kendo:grid-columns>
                <kendo:grid-column title="还款人" filterable="false" field="customer.realName" width="100px"/>
                <kendo:grid-column title="还款人手机号" filterable="false" field="customer.mobile" width="100px"/>
                <kendo:grid-column title="还款金额" filterable="false" field="amount" width="100px" />
                <kendo:grid-column title="备注" filterable="false" field="transactionDesc" width="100px" />
                <kendo:grid-column title="还款类型" filterable="false" field="transactionType" width="100px" template="#=getTypeName(transactionType)#"/>
                <kendo:grid-column title="还款时间" filterable="false" field="createTime" width="100px" />
            </kendo:grid-columns>
            <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                <kendo:dataSource-schema data="content" total="totalElements">
                </kendo:dataSource-schema>
                <kendo:dataSource-filter>
                    <kendo:dataSource-filterItem field="customer.customerId" value="${customer.customerId}" operator="eq"/>
                </kendo:dataSource-filter>
                <kendo:dataSource-transport>
                    <kendo:dataSource-transport-read url="../liabilities/choosePagedLiabilities.do" type="POST" contentType="application/json"/>
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

<script type="text/javascript">
    $(document).ready(function(){
        reloadGridFilters('financialLoanBillGrid');

    });
    function getTypeName(data){
        switch (data){
            case "INSURANCE_REBATE":{
                return "保险返利";
            }
            case "INSURANCE_PURCHASE":{
                return "保险购买";
            }
            case "MANUAL_REPAYMENT":{
                return "手动还款";
            }
            case "INSURANCE_REPAYMENT":{
                return "保险返利自动还款";
            }
            case "INTEREST":{
                return "借款利息";
            }
            case "PRODUCT_RB_REPAYMENT":{
                return "商品报帐自动还款";
            }
            case "LOAN":{
                return "借款";
            }
            case "CAR_REPAYMENT":{
                return "汽车返利还款";
            }
        }
        return state;
    }

</script>