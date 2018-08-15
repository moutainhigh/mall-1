<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>编辑商品</title>
    <script charset="utf-8" src="../editor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="../editor/lang/zh_CN.js"></script>

    <%--后台页面共用的js--%>
    <script src="../js/common/fixed_common.js"></script>

    <script type="application/javascript">

        var curStep = 0;

        $(document).ready(function () {
            KindEditor.ready(function (K) {
                window.editor = K.create('#editorContent', {
                    uploadJson: '../upload/fileUpload.do',
                    fileManagerJson: '../upload/fileManager.do',
                    allowFileManager: true,
                    afterCreate : function() {
                        this.sync();
                    },
                    afterBlur:function(){
                        this.sync();
                    }
                });
                window.editor = K.create('#editorContent1', {
                    uploadJson: '../upload/fileUpload.do',
                    fileManagerJson: '../upload/fileManager.do',
                    allowFileManager: true,
                    afterCreate : function() {
                        this.sync();
                    },
                    afterBlur:function(){
                        this.sync();
                    }
                });
                window.editor = K.create('#editorContent2', {
                    uploadJson: '../upload/fileUpload.do',
                    fileManagerJson: '../upload/fileManager.do',
                    allowFileManager: true,
                    afterCreate : function() {
                        this.sync();
                    },
                    afterBlur:function(){
                        this.sync();
                    }
                });
            });

            $("#brandId").select2();

            $.citySelector.init({
                province: "province",
                city: "city",
                preProvince: "${commodity.province}",
                preCity: "${commodity.city}"
            });

            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true,
                onValidationComplete: function (form, valid) {
                    if (valid) {
                        if (null == $("#editorContent").val() || "" == $("#editorContent").val()) {
                            bootbox.alert("请填写商品详情内容!");
                            return false;
                        }
                        return true;
                    }
                }
            });
        });

        function loadAttributeGroups(catalogId) {
            $.getJSON("getAttributeGroupsByCatalogId.do", {
                catalogId: catalogId
            }, function (json) {
                var agHtml = "";
                $.each(json, function (date, value) {
                    var groupHtml = '<div class="row"><div class="col-sm-2"><label>' + value.groupName + '：</label></div><div class="col-sm-9">';
                    $.each(value.attributes, function (data, attribute) {
                        groupHtml += '<label style="float:left;"><input type="checkbox" name="attributeId" style="opacity:1" value="' + attribute.attributeId + '"/>&nbsp;' + attribute.attributeName + '&nbsp;&nbsp;&nbsp;&nbsp;</label>';
                    });
                    groupHtml += "</div></div>";
                    agHtml += groupHtml;
                });
                $("#attributeGroupDiv").html(agHtml);
            });
        }

        function loadSpecs(catalogId) {
            $.getJSON("getSpecsByCatalogId.do", {
                catalogId: catalogId
            }, function (json) {
                $("#specTable  tr:not(:first)").empty()
                $.each(json, function (date, value) {
                    var newRow = "<tr><td><input type='hidden' name='specId' value='" + value.specId + "'/>" + value.specName + "</td><td><input type='text' name='specValue' class='form-control'/></td></tr>";
                    $("#specTable tr:last").after(newRow);

                });
            });
        }

        function selectSeller() {
            $('#sellerDialog').modal();
        }

        function chooseSeller() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                var sellerId=dataItem.sellerId;
                $("#sellerId").val(dataItem.sellerId);
                $("#sellerName").val(dataItem.sellerName);
            }else{
                $("#sellerId").val('');
                $("#sellerName").val('');
            }
            $('#sellerDialog').modal("hide")
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
                    <li><a href="#">商品管理</a></li>
                    <li class="active">编辑商品</li>
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
                    <h2>编辑商品</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="commodities.do"><i class="fa fa-reply"></i></a>
                </div>
            </div>
        </header>
        <div class="window">
            <div class="inner-padding">
                <form:form id="validateSubmitForm" action="editCommodity.do" cssClass="form-horizontal" method="post" commandName="commodity">
                    <form:hidden path="commodityId"/>
                    <fieldset>
                        <legend>商品表单</legend>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品分类：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:hidden id="catalogId" path="catalog.catalogId"/>
                                <div class="input-group">
                                    <form:input id="catalogName" readonly="true" cssClass="form-control validate[required]" path="catalog.catalogName" maxlength="32"/>
                                    <span class="input-group-btn">
                                        <button id="catalogNameBtn" class="btn btn-default" type="button">选择</button>
                                    </span>
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品品牌：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:select id="brandId" path="brand.brandId" items="${brands}" itemLabel="brandName" itemValue="brandId"/>
                            </div>
                        </div>

                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品标题：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,minSize[2]]" path="commodityTitle" maxlength="64"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品价格段：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:select path="priceSection.sectionId" cssClass="form-control simpleselect validate[required]">
                                    <c:forEach items="${priceSections}" var="section">
                                        <option value="${section.sectionId}">${section.startPrice} - ${section.endPrice}</option>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品编码：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,minSize[2],custom[onlyLetterNumber]" path="commodityCode" maxlength="32" data-errormessage-custom-error="编码只能输入数字和英文字母"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品简称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,minSize[2]]" path="shortName" maxlength="255"/>
                            </div>

                        </div>

                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品全称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,minSize[2]]" path="commodityName" maxlength="255"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品拼音名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,minSize[2]]" path="commodityPYName" maxlength="64"/>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 一级分类比例配置：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control"  path="" id="oneLevelCatalog" readonly="true" maxlength="12"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 商品比例配置：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[custom[gtOne]]" path="ratio" onkeyup="salePrice_f();" placeholder="商品比例配置不填,则取分类比例配置" maxlength="12"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 成本价：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,custom[number]]" path="costPrice" onkeyup="salePrice_f();" maxlength="12"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 销售价：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,custom[number]]" path="sellPrice" readonly="true" maxlength="12"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 市场价：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,custom[number]]" path="marketPrice" maxlength="12"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品单位：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:select class="form-control simpleselect" path="unit">
                                    <form:options items="${unit}" itemLabel="name"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>重量：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,custom[number]]" path="weight" maxlength="12"/>
                            </div>
                            <div class="col-sm-2">
                                <label>体积：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,custom[number]]" path="volume" maxlength="12"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 产地省份：</label>
                            </div>
                            <div class="col-sm-3">
                                <select class="form-control validate[required]" id="province" name="province">
                                </select>

                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 产地市区：</label>
                            </div>
                            <div class="col-sm-3">
                                <select class="form-control validate[required]" id="city" name="city">
                                </select>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label>SEO关键字：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:input type="text" cssClass="form-control" path="seoKey" maxlength="255"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>SEO标题：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:input type="text" cssClass="form-control" path="seoTitle" maxlength="255"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>SEO描述：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:textarea rows="10" cols="101" path="seoDescription" maxlength="255"></form:textarea>
                            </div>
                        </div>

                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 热门商品：</label>
                            </div>
                            <div class="col-sm-1">
                                <form:checkbox path="popular" cssClass="checkbox-master"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 特惠商品：</label>
                            </div>
                            <div class="col-sm-1">
                                <form:checkbox path="special"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 推荐商品：</label>
                            </div>
                            <div class="col-sm-1">
                                <form:checkbox path="recommend"/>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label>商家：</label>
                            </div>
                            <div class="col-sm-8">
                                <div class="col-sm-3">
                                    <form:input type="hidden" cssClass="form-control" path="seller.sellerId" id="sellerId" />
                                    <form:input type="text" cssClass="form-control" path="seller.sellerName" id="sellerName" disabled="true"/>
                                    <button type="button" onclick="selectSeller();" title="添加" class="btn btn-default">
                                        <i class="fa fa-plus-circle"></i>选择商家
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label>商品规格：</label>
                            </div>
                            <div class="col-sm-8">
                                <table id="specTable" class="table table-bordered table-striped">
                                    <thead id="attribute-table-th">
                                    <tr>
                                        <th scope="col" width="200">规格名称</th>
                                        <th scope="col">值</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${currentSpecs}" var="cSpec">
                                        <tr>
                                            <td><input type='hidden' name='specId' value='${cSpec.spec.specId}'/>${cSpec.spec.specName}</td>
                                            <td><input type='text' name='specValue' class='form-control' value="${cSpec.value}"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>图片</label>
                            </div>
                            <div class="col-sm-9">
                                    <%--图片上传控件--%>
                                <link href="../js/plugins/fileinput/fileinput.min.css" media="all" rel="stylesheet" type="text/css"/>
                                <script src="../js/plugins/fileinput/fileinput.min.js" type="text/javascript"></script>
                                <script src="../js/plugins/fileinput/zh.js" type="text/javascript"></script>
                                <script type="text/javascript">
                                    $(function(){
                                        $("#validateSubmitForm").validationEngine({
                                            autoHidePrompt: true, scroll: false, showOneMessage: true,
                                            onValidationComplete: function (form, valid) {
                                                if (valid) {
                                                    var defaultPicPath = $('input[name="imgurl"]');
                                                    if (defaultPicPath.size()==0) {
                                                        bootbox.alert("请至少选择一张图片!");
                                                        return false;
                                                    } else {
                                                        return true;
                                                    }
                                                }
                                            }
                                        });
                                        var initPreview = new Array();//展示元素
                                        var initPreviewConfig = new Array();//展示设置
                                        //初始化图片上传组件
                                        $("#picUrl").fileinput({
                                            uploadUrl: "/admin/uploads/uploadFile/COMMODITY.do",
                                            showCaption: true,
                                            minImageWidth: 50,
                                            minImageHeight: 50,
                                            showUpload:true, //是否显示上传按钮
                                            showRemove :false, //显示移除按钮
                                            showPreview :true, //是否显示预览
                                            showCaption:false,//是否显示标题
                                            browseOnZoneClick: true,//是否显示点击选择文件
                                            language: "zh" ,
                                            showBrowse : false,
                                            maxFileSize : 2000,
                                            allowedFileExtensions: ["jpg", "png", "gif"],
                                            autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉
                                            overwriteInitial: false,//不覆盖已存在的图片
                                            browseClass:"btn btn-primary", //按钮样式
                                            // layoutTemplates:{
                                            //     actionUpload:''    //设置为空可去掉上传按钮
                                            // },
                                            maxFileCount: 10  //上传的个数
                                        }).on("fileuploaded", function (event, data) {
                                            var response = data.response;
                                            //添加url到隐藏域
                                            var html='<input name="imgurl" type="hidden" id="'+response.timeStr+'" value="'+response.url+','+response.fileName+','+response.timeStr+'">';
                                            $('#imgDiv').html($('#imgDiv').html()+html);
                                            //上传完成回调
                                            var index=0;
                                            if(initPreview.length>0 ){
                                                index=initPreview.length;
                                            }
                                            initPreview[index]  = response.url;
                                            var config = new Object();
                                            config.caption = "";
                                            config.url="/admin/uploads/delete/COMMODITY.do";
                                            config.key=response.timeStr;
                                            initPreviewConfig[index]=config;
                                            $("#picUrl").fileinput('refresh', {
                                                initialPreview: initPreview,
                                                initialPreviewConfig: initPreviewConfig,
                                                initialPreviewAsData: true
                                            });
                                            $(".btn-default").attr("disabled",false);
                                        }).on("filepredelete", function(jqXHR) {
                                            var abort = true;
                                            if (confirm("确定要删除吗？(删除后不会恢复)")) {
                                                abort = false;
                                            }
                                            return abort;
                                        }).on('filedeleted', function(event, id) {
                                            debugger;
                                            $("#"+id).remove();
                                            for (var i=0;i<initPreview.length;i++)
                                            {
                                                if(initPreview[i].indexOf(id) != -1){
                                                    initPreview.splice(i)
                                                    initPreviewConfig.splice(i)
                                                }
                                            }
                                        }).on('filebatchselected', function (event, files) {//选中文件事件
                                            $(".kv-file-upload").click();
                                        });
                                        //加载图片
                                        var a='${listAttachment}';
                                        var json=eval('(' + a + ')')
                                        for(var i=0,l=json.length;i<l;i++){
                                            initPreview[i]  = json[i].filePath;
                                            var config = new Object();
                                            config.caption = "";
                                            config.url="/admin/uploads/delete/COMMODITY.do";
                                            config.key=json[i].inputId;
                                            initPreviewConfig[i]=config;
                                            $("#picUrl").fileinput('refresh', {
                                                initialPreview: initPreview,
                                                initialPreviewConfig: initPreviewConfig,
                                                initialPreviewAsData: true
                                            });
                                            var html='<input name="imgurl" type="hidden" id="'+json[i].inputId+'" value="'+json[i].filePath+','+json[i].fileName+','+json[i].inputId+'">';
                                            $('#imgDiv').html($('#imgDiv').html()+html);
                                        }
                                    })
                                </script>
                                <input id="picUrl" name="file" type="file" class="file-loading" accept="image/*" multiple>
                                <div id="imgDiv">
                                </div>
                                    <%--图片上传控件结束--%>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>商品配置内容：</label>
                            </div>
                            <div class="col-sm-9">
                                <form:textarea cssClass="form-control" id="editorContent2" path="settingContent" cssStyle="height:500px;"></form:textarea>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>商品详情内容：</label>
                            </div>
                            <div class="col-sm-9">
                                <form:textarea cssClass="form-control" id="editorContent" path="content" cssStyle="height:500px;"></form:textarea>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>商品说明内容：</label>
                            </div>
                            <div class="col-sm-9">
                                <form:textarea cssClass="form-control" id="editorContent1" path="explainContent" cssStyle="height:500px;"></form:textarea>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="btn-group pull-right">
                                    <button id="saveBtn" class="btn btn-default" type="submit"><i class="fa fa-save"></i>&nbsp;保&nbsp;存&nbsp;</button>
                                    <button onclick="clearInput('form-control')" type="button"   class="btn btn-default"><i class="fa fa-reply"></i>&nbsp;重&nbsp;置&nbsp;</button>
                                </div>
                            </div>
                        </div>

                    </fieldset>
                </form:form>
                <div class="spacer-40"></div>
                <div class="hr-totop"><span>Top</span></div>
                <div class="spacer-40"></div>
            </div>
        </div>

        <jsp:include page="../layouts/footer.jsp"/>
    </div>
</div>
<div class="modal fade" id="catalogDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">选择商品分类</h4>
            </div>
            <div class="modal-body">
                <div class="row" style="margin-top:-20px;margin-bottom:-20px">
                    <div class="sidebar-module">
                        <kendo:treeView name="treeview" select="onSelect">
                            <kendo:dataSource data="${catalogTree}">
                            </kendo:dataSource>
                        </kendo:treeView>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary pull-right" onclick="chooseCatalog();">确认</button>
            </div>
        </div>
        <script type="application/javascript">
            var catalogId = 0;
            var catalogName = "";
            var RATIO = 1;//分类比例配置
            $('#catalogNameBtn').click(function (e) {
                $('#catalogDialog').modal();
                e.preventDefault();
            });

            function onSelect(e) {
                var data = $('#treeview').data('kendoTreeView').dataItem(e.node);
                catalogId = data.id;
                catalogName = data.text;
                RATIO = data.ratio;
            }

            function chooseCatalog() {
                $('#catalogDialog').modal("hide");
                $("#catalogId").val(catalogId);
                $("#catalogName").val(catalogName);
                $("#catalogRatio").val(RATIO);
                salePrice_f();//设置销售价
                loadSpecs(catalogId);
            }
            $('#oneLevelCatalog').val(${oneLevelCatalog.ratio});//一级分类比例
        </script>

    </div>
</div>
<div class="modal fade" id="sellerDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">选择商家</h4>
            </div>
            <div class="modal-body">
                <jsp:include page="../seller/chooseSeller.jsp"/>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary pull-right" onclick="chooseSeller();">确认</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
