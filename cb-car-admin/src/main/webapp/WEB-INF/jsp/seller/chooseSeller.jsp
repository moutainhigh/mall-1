<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row" style="margin-top:-20px;margin-bottom:20px">
    <div class="toolbar responsive-helper">
        <form>
            <div class="pull-left">
              <div class="toolbar-field">
                <strong>商家编码:</strong>
              </div>
              <div class="toolbar-field">
                <input type="text" data-filter="sellerCode" data-operator="contains" class="form-control grid-filter" placeholder="请输入商家编码"/>
              </div>
              <div class="toolbar-field">
                <strong>商家名称:</strong>
              </div>
              <div class="toolbar-field">
                <input type="text" data-filter="sellerName" data-operator="contains" class="form-control grid-filter" placeholder="请输入商家名称"/>
              </div>
              <div class="spacer-10"></div>
            </div>
            <!-- End .pull-left -->
            <div class="pull-right">
              <div class="toolbar-field">
                <button type="button" class="btn btn-default" onclick="reloadGridFilters('grid')"><i class="fa fa-search"></i>查询</button>
                &nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-default" onclick="clearFilters('grid')">清空</button>
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
        <kendo:grid name="grid" pageable="false" sortable="true" selectable="true" height="400px"  >
          <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
          <kendo:grid-columns>
            <kendo:grid-column title="商家名称" field="sellerName" width="100"/>
            <kendo:grid-column title="商家编码" field="sellerCode" width="100" filterable="false"/>
            <kendo:grid-column title="商家类型" field="sellerType" width="100" template="#=formatSellerType(sellerType)#"/>
            <kendo:grid-column title="商家地址" field="sellerAddress" width="150"/>
            <kendo:grid-column title="联系人" field="linkman" width="100"/>
            <kendo:grid-column title="手机" field="mobile" width="100"/>
            <kendo:grid-column title="联系电话" field="telephone" width="100"/>
            <kendo:grid-column title="邮箱" field="email" width="120"/>
            <kendo:grid-column title="QQ" field="qq" width="100"/>
            <kendo:grid-column title="商家微信" field="wechat" width="100"/>
            <kendo:grid-column title="商家支付平台类型" field="channelType" width="120" template="#=formatChannelType(channelType)#"/>
            <kendo:grid-column title="创建时间" field="createTime" width="120" format="{0:yyyy-MM-dd HH:mm}"/>
          </kendo:grid-columns>
          <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
            <kendo:dataSource-schema data="content" total="totalElements">
              <kendo:dataSource-schema-model>
                <kendo:dataSource-schema-model-fields>
                  <kendo:dataSource-schema-model-field name="createTime" type="date"/>
                </kendo:dataSource-schema-model-fields>
              </kendo:dataSource-schema-model>
            </kendo:dataSource-schema>
            <kendo:dataSource-transport>
              <kendo:dataSource-transport-read url="../seller/pageSellers.do" type="POST" contentType="application/json"/>
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

    function formatSellerType(sellerType) {
        switch (sellerType) {
            case "SELF_OPERATION":
                return "平台自营";
            case "SELLER":
                return "商家";
        }
    }

    function formatChannelType(channelType) {
        switch (channelType) {
            case "UNIONPAY":
                return "银联";
            case "ALIPAY":
                return "支付宝";
            case "TENPAY":
                return "财付通";
        }
    }

    function clearCheck(){
        $("#commodityFormId :checkbox").removeAttr("checked");
        clearFilters('commodityGrid')
    }
</script>
