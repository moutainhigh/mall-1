<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row" style="margin-top:-20px;margin-bottom:20px">
    <div class="toolbar responsive-helper">
        <form>
            <div class="pull-left">
                <div class="toolbar-field">
                    <strong>事项描述:</strong>
                </div>
                <div class="toolbar-field">
                    <input type="text" data-filter="matterDescription" data-operator="contains" class="form-control grid-filter" placeholder="事项描述"/>
                </div>
            </div>
            <div class="pull-left">
                <div class="toolbar-field">
                    <strong>事项描述:</strong>
                </div>
                <div class="toolbar-field">
                    <div class="toolbar-field">
                        <script>
                            $.ajax({
                                url: "/admin/insuranceInformedGroup/getInsuranceInformedMatterGroupList.do",
                                type: 'POST',
                                cache: false,
                                data: '',
                                processData: false,
                                contentType: false,
                                success: function (result) {
                                    var html=$("#group").html();
                                    for (var i=0;i<result.length;i++)
                                    {
                                        html+='<option value="'+result[i].groupId+'">'+result[i].description+'</option>'
                                    }
                                    $("#group").html(html);
                                },
                                error: function (err) {
                                }
                            });
                        </script>
                        <select id="group" data-filter="matterGroup.groupId"  data-operator="eq"
                                class="form-control simpleselect grid-filter">
                            <option value="">全部</option>
                        </select>
                    </div>
                </div>
            </div>
            <!-- End .pull-left -->
            <div class="pull-right">
                <div class="toolbar-field">
                    <button type="button" class="btn btn-default" onclick="reloadGridFilters('commodityGrid')"><i class="fa fa-search"></i>查询</button>
                    &nbsp;&nbsp;&nbsp;
                    <button type="button" class="btn btn-default" onclick="clearFilters('commodityGrid')">清空</button>
                </div>
            </div>
            <!-- End .pull-right -->
        </form>
    </div>
    <div class="spacer-10"></div>

    <div class="toolbar responsive-helper">
        <header>
            <div class="pull-left">
                <h3>事项列表</h3>
            </div>
            <div class="pull-right">
            </div>
        </header>
    </div>
    <div class="table-wrapper">
        <kendo:grid name="commodityGrid" pageable="true" sortable="true" filterable="true" selectable="true" height="500">
            <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="8"/>
            <kendo:grid-filterable extra="false">
                <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
                <kendo:grid-filterable-operators>
                    <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                    <kendo:grid-filterable-operators-date gte="小于" eq="等于" lte="大于"/>
                </kendo:grid-filterable-operators>
            </kendo:grid-filterable>
            <kendo:grid-columns>
                <kendo:grid-column title="All&nbsp;<input type='checkbox' id='checkall'/>" field="matterId" width="20" template="<input type='checkbox' id='#: matterId #' name='selectedCommodityId' value='#: matterId #' />" sortable="false" filterable="false"/>
                <kendo:grid-column title="事项描述" filterable="false" field="matterDescription" width="180"/>
                <kendo:grid-column title="事项组描述" filterable="false" field="matterGroup.description" width="80"/>
                <kendo:grid-column title="事项类型" filterable="false" field="matterType" template="#= matterType ? '是否题' : '填空题' #" width="40"/>
            </kendo:grid-columns>
            <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                <kendo:dataSource-schema data="content" total="totalElements">
                </kendo:dataSource-schema>
                <kendo:dataSource-transport>
                    <kendo:dataSource-transport-read url="../insuranceInformedMatter/pageaddMatter.do?prodId=${prodId}" type="POST" contentType="application/json"/>
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
                $("#commodityGrid input[type='checkbox'][name='selectedCommodityId']").each(function() {
                    $(this).prop("checked","checked");
                });
            }else{
                $("#commodityGrid input[type='checkbox'][name='selectedCommodityId']").each(function() {
                    $(this).removeAttr("checked");
                });
            }
        });
    });

    function clearCheck(){
        $("#commodityFormId :checkbox").removeAttr("checked");
        clearFilters('commodityGrid')
    }
</script>