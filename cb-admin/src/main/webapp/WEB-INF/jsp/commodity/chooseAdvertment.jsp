<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row" style="margin-top:-20px;margin-bottom:20px">
    <div class="toolbar responsive-helper">
        <form>
            <div class="pull-left">
                <div class="toolbar-field">
                    <strong>广告编码:</strong>
                </div>
                <div class="toolbar-field">
                    <input type="text" data-filter="advertCode" data-operator="contains" class="form-control grid-filter" placeholder="请输入品牌编码"/>
                </div>
                <div class="toolbar-field">
                    <strong>广告标题:</strong>
                </div>
                <div class="toolbar-field">
                    <input type="text" data-filter="advertTitle" data-operator="contains" class="form-control grid-filter" placeholder="请输入广告标题"/>
                </div>
            </div>
            <!-- End .pull-left -->
            <div class="pull-right">
                <div class="toolbar-field">
                    <button type="button" class="btn btn-default" onclick="reloadGridFilters('advertGrid')"><i class="fa fa-search"></i>查询</button>
                    &nbsp;&nbsp;&nbsp;
                    <button type="button" class="btn btn-default" onclick="clearFilters('advertGrid')">清空</button>
                </div>
            </div>
            <!-- End .pull-right -->
        </form>
    </div>
    <div class="spacer-10"></div>

    <div class="toolbar responsive-helper">
        <header>
            <div class="pull-left">
                <h3>广告列表</h3>
            </div>
            <div class="pull-right">
            </div>
        </header>
    </div>
    <div class="table-wrapper">
        <kendo:grid name="advertGrid" pageable="true" sortable="true" filterable="true" selectable="true" height="350">
            <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
            <kendo:grid-filterable extra="false">
                <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
                <kendo:grid-filterable-operators>
                    <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                    <kendo:grid-filterable-operators-date gte="小于" eq="等于" lte="大于"/>
                </kendo:grid-filterable-operators>
            </kendo:grid-filterable>
            <kendo:grid-columns>
                <kendo:grid-column title="<input type='checkbox' id='checkall'>全选</input>" field="advertId" width="20" template="<input type='checkbox' id='#: advertId #' name='selectedAdvertId' value='#: advertId #' />" sortable="false" filterable="false"/>
                <kendo:grid-column title="广告编码" field="advertCode" width="80"/>
                <kendo:grid-column title="广告标题" field="advertTitle" width="80"/>
            </kendo:grid-columns>
            <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                <kendo:dataSource-schema data="content" total="totalElements">
                </kendo:dataSource-schema>
                <kendo:dataSource-transport>
                    <kendo:dataSource-transport-read url="../cms/chooseAdvertment.do" type="POST" contentType="application/json"/>
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
                $("#advertGrid input[type='checkbox'][name='selectedAdvertId']").each(function() {
                    $(this).prop("checked","checked");
                });
            }else{
                $("#advertGrid input[type='checkbox'][name='selectedAdvertId']").each(function() {
                    $(this).removeAttr("checked");
                });
            }
        });
    });

    function clearCheck(){
        $("#advertGrid :checkbox").removeAttr("checked");
        clearFilters('advertGrid')
    }
</script>