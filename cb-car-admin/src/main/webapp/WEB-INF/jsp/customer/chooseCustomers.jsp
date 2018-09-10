<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
    <div class="toolbar responsive-helper">
        <form>
            <div class="pull-left">
                <div class="toolbar-field">
                    <strong>账户名:</strong>
                </div>
                <div class="toolbar-field">
                    <input type="text" data-filter="accountName" data-operator="contains" class="form-control grid-filter" placeholder="请输入账户名"/>
                </div>
                <div class="toolbar-field">
                    <strong>姓名:</strong>
                </div>
                <div class="toolbar-field">
                    <input type="text" data-filter="realName" data-operator="contains" class="form-control grid-filter" placeholder="请输入姓名"/>
                </div>
                <div class="toolbar-field">
                    <strong>手机号:</strong>
                </div>
                <div class="toolbar-field">
                    <input type="text" data-filter="mobile" data-operator="contains" class="form-control grid-filter" placeholder="请输入手机号"/>
                </div>
            </div><!-- End .pull-left -->
            <div class="pull-right">
                <div class="toolbar-field">
                    <button type="button" class="btn btn-default" onclick="reloadGridFilters('customerGrid')"><i class="fa fa-search"></i>查询</button>
                    &nbsp;&nbsp;&nbsp;
                    <button type="button" class="btn btn-default" onclick="clearFilters('customerGrid')">清空</button>
                </div>
            </div><!-- End .pull-right -->
        </form>
    </div>
    <div class="spacer-10"></div>

    <div class="toolbar responsive-helper">
        <header>
            <div class="pull-left">
                <h3>客户列表</h3>
            </div>
            <div class="pull-right">
            </div>
        </header>
    </div>
    <div class="table-wrapper">
        <kendo:grid name="customerGrid" pageable="true" sortable="true" filterable="true" selectable="true" height="350">
            <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
            <kendo:grid-filterable extra="false">
                <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
                <kendo:grid-filterable-operators>
                    <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                    <kendo:grid-filterable-operators-date gte="小于" eq="等于" lte="大于"/>
                </kendo:grid-filterable-operators>
            </kendo:grid-filterable>
            <kendo:grid-columns>
                <kendo:grid-column title="<input type='checkbox' id='checkall'>全选</input>" field="customerId" width="20" template="<input type='checkbox' id='#: customerId #' name='selectedCustomerId' value='#: customerId #' customName='#: accountName #' />" sortable="false" filterable="false"/>
                <kendo:grid-column title="账户名" field="accountName" width="80" />
                <kendo:grid-column title="手机号" field="mobile" width="100px"/>
                <kendo:grid-column title="真实姓名" field="realName" width="80"/>
            </kendo:grid-columns>
            <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                <kendo:dataSource-schema data="content" total="totalElements">
                </kendo:dataSource-schema>
                <kendo:dataSource-transport>
                    <kendo:dataSource-transport-read url="../customer/pageCustomers.do" type="POST" contentType="application/json"/>
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
        $("#checkall").click(function(){
            if(this.checked){
                $("#customerGrid input[type='checkbox'][name='selectedCustomerId']").each(function() {
                    $(this).attr("checked","checked");
                });
            }else{
                $("#customerGrid input[type='checkbox'][name='selectedCustomerId']").each(function() {
                    $(this).removeAttr("checked");
                });
            }
        });
    });

    function clearCheck(){
        $("#customerFormId :checkbox").removeAttr("checked");
        clearFilters('customerGrid');
    }
</script>