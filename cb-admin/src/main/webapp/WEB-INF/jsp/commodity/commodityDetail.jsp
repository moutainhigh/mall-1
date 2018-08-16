<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>商品详情</title>
</head>
<body>
<jsp:include page="../layouts/left.jsp"/>
<jsp:include page="../layouts/sidebarRight.jsp"/>
<div id="main" class="clearfix">
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
                    <li><a href="#">首页</a></li>
                    <li><a href="#">商品管理</a></li>
                    <li class="active">商品详情</li>
                </ul>
                <!-- End .breadcrumb -->
            </div>
            <div class="pull-right">
                <p>Version 1.0.0</p>
            </div>
        </div>
        <!-- End #header-main-bottom -->
    </header>

    <div id="content" class="clearfix">


        <header id="header-sec">
            <div class="inner-padding">
                <div class="pull-left">
                    <h2>商品详情</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="commodities.do">
                        <i class="fa fa-reply"></i>
                    </a>

                </div>
            </div>
        </header>
        <div class="window">
            <div class="actionbar">
                <div class="pull-left">
                    <ul class="ext-tabs">
                        <li class="active">
                            <a href="#content-tab-1">基本信息</a>
                        </li>
                        <li>
                            <a href="#content-tab-2">规格</a>
                        </li>
                        <li >
                            <a href="#content-tab-3">图片</a>
                        </li>
                        <li >
                            <a href="#content-tab-4">货品</a>
                        </li>
                        <li >
                            <a href="#content-tab-5">详细信息</a>
                        </li>
                        <li >
                            <a href="#content-tab-6">说明内容</a>
                        </li>
                    </ul>
                </div>
                <div class="pull-right">
                    <a class="btn" href="#" id="lockscreen-slider-trigger">
                        <i class="fa fa-lock"></i>
                    </a>
                    <a data-toggle-sidebar="right" class="btn small-toggle-btn" href="#"></a>
                </div>
            </div><!-- End .actionbar-->
            <div class="tab-content">
                <div id="content-tab-1" class="tab-pane active">
                    <div class="inner-padding">
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品分类：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                ${commodity.catalog.catalogName}
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品品牌：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                ${commodity.brand.brandName}
                            </div>
                        </div>

                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品标题：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                ${commodity.commodityTitle}
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品价格段：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                ${commodity.priceSection.endPrice}
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品编码：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                ${commodity.commodityCode}
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品简称：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                ${commodity.shortName}
                            </div>

                        </div>

                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品全称：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                ${commodity.commodityName}
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品拼音名称：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                ${commodity.commodityPYName}
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 成本价(元)：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                ${commodity.costPrice}
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 销售价(元)：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                ${commodity.sellPrice}
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 市场价(元)：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                ${commodity.marketPrice}
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品单位：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                ${commodity.unit.name}
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <script type="text/javascript">
                                var province=$.citySelector.getAddress("${commodity.province}")
                                var city=$.citySelector.getAddress("${commodity.city}")
                                $(function(){
                                    $('#province').html(province);
                                    $('#city').html(city);
                                })
                            </script>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 产地省份：</label>
                            </div>
                            <div class="col-sm-3 col-label" id="province">
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 产地市区：</label>
                            </div>
                            <div class="col-sm-3 col-label" id="city">
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>重量(kg)：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                ${commodity.weight}
                            </div>
                            <div class="col-sm-2">
                                <label>体积(m³)：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                ${commodity.volume}
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>SEO关键字：</label>
                            </div>
                            <div class="col-sm-8 col-label">
                                ${commodity.seoKey}
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>SEO标题：</label>
                            </div>
                            <div class="col-sm-8 col-label">
                                ${commodity.seoTitle}
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>SEO描述：</label>
                            </div>
                            <div class="col-sm-8 col-label">
                                ${commodity.seoDescription}
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 热门商品：</label>
                            </div>
                            <div class="col-sm-1 col-label">
                                ${commodity.popular==true?'是':'否'}
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 特惠商品：</label>
                            </div>
                            <div class="col-sm-1 col-label">
                                ${commodity.special==true?'是':'否'}
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span> 推荐商品：</label>
                            </div>
                            <div class="col-sm-1 col-label">
                                ${commodity.recommend==true?'是':'否'}
                            </div>
                        </div>
                        <%--<div class="spacer-10"></div>--%>
                        <%--<div class="row">--%>

                            <%--<div class="col-sm-2">--%>
                                <%--<label><span class="asterisk">*</span>赠品：</label>--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-1 col-label">--%>
                                <%--${commodity.giveaway==true?'是':'否'}--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-2">--%>
                                <%--<label><span class="asterisk">*</span> 换购：</label>--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-1 col-label">--%>
                                <%--${commodity.barter==true?'是':'否'}--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-2">--%>
                                <%--<label><span class="asterisk">*</span> 预售：</label>--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-1 col-label">--%>
                                <%--${commodity.preSell==true?'是':'否'}--%>
                            <%--</div>--%>
                        <%--</div>--%>

                    </div><!-- End .inner-padding -->
                </div>
                <div id="content-tab-2" class="tab-pane">
                    <div class="inner-padding">
                        <div class="subheading">
                            <h3>商品规格</h3>
                        </div>
                        <div class="row">
                            <div class="col-sm-8">
                                <table id="specTable" class="table table-bordered table-striped">
                                    <thead id="attribute-table-th">
                                    <tr>
                                        <th scope="col" width="200">规格名称</th>
                                        <th scope="col">值</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${commoditySpecs}" var="cSpec">
                                        <tr>
                                            <td><input type='hidden' name='specId' value='${cSpec.spec.specId}'/>${cSpec.spec.specName}</td>
                                            <td>${cSpec.value}</td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div><!-- End .inner-padding -->
                </div>
                <div id="content-tab-3" class="tab-pane ">
                    <div class="inner-padding">
                        <div class="subheading">
                            <h3>商品图片</h3>
                        </div>
                        <div class="spacer-25"></div>
                        <div class="col-sm-9">
                            <%--图片上传控件--%>
                            <link href="../js/plugins/fileinput/fileinput.min.css" media="all" rel="stylesheet" type="text/css"/>
                            <script src="../js/plugins/fileinput/fileinput.min.js" type="text/javascript"></script>
                            <script src="../js/plugins/fileinput/zh.js" type="text/javascript"></script>
                            <script type="text/javascript">
                                $(function(){
                                    var initPreview = new Array();//展示元素
                                    var initPreviewConfig = new Array();//展示设置
                                    //初始化图片上传组件
                                    $("#picUrl").fileinput({
                                        uploadUrl: "/admin/uploads/uploadFile/COMMODITY.do",
                                        showCaption: true,
                                        minImageWidth: 50,
                                        minImageHeight: 50,
                                        showUpload:false, //是否显示上传按钮
                                        showRemove :false, //显示移除按钮
                                        showPreview :true, //是否显示预览
                                        showCaption:false,//是否显示标题
                                        browseOnZoneClick: false,//是否显示点击选择文件
                                        language: "zh" ,
                                        showBrowse : false,
                                        maxFileSize : 2000,
                                        allowedFileExtensions: ["jpg", "png", "gif"],
                                        autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉
                                        overwriteInitial: false,//不覆盖已存在的图片
                                        browseClass:"btn btn-primary", //按钮样式
                                        layoutTemplates:{
                                            actionUpload:'',    //设置为空可去掉上传按钮
                                            actionDelete:''
                                        },
                                        maxFileCount: 10  //上传的个数
                                    });
                                    //加载图片
                                    var a='${listAttachment}';
                                    var json=eval('(' + a + ')')
                                    for(var i=0,l=json.length;i<l;i++){
                                        initPreview[i]  = json[i].filePath;
                                        var config = new Object();
                                        config.caption = "";
                                        config.url="/admin/uploads/delete/COMMODITY.do";
                                        config.key=json[i].timeStr;
                                        initPreviewConfig[i]=config;
                                        $("#picUrl").fileinput('refresh', {
                                            initialPreview: initPreview,
                                            initialPreviewConfig: initPreviewConfig,
                                            initialPreviewAsData: true
                                        });
                                        var html='<input name="imgurl" type="hidden" id="'+json.inputId+'" value="'+json[i].filePath+','+json[i].fileName+','+json[i].inputId+'">';
                                        $('#imgDiv').html($('#imgDiv').html()+html);
                                    }
                                })
                            </script>
                            <input id="picUrl"  name="file" type="file" class="file-loading" accept="image/*" multiple>
                            <div id="imgDiv">
                            </div>
                            <%--图片上传控件结束--%>
                        </div>
                    </div>
                </div>
                <div id="content-tab-4" class="tab-pane ">
                    <div class="inner-padding">
                        <div class="row">
                            <div class="col-sm-12">
                                <table id="attributeTable" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th scope="col" width="140">货品名称</th>
                                        <th scope="col">货品属性</th>
                                        <th scope="col" width="100">进货价</th>
                                        <th scope="col" width="100">销售价</th>
                                        <th scope="col" width="100">市场价</th>
                                        <th scope="col" width="140">仓库名称</th>
                                        <th scope="col" width="100">库存</th>
                                        <th scope="col" width="140">状态</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="product" items="${products}">
                                        <tr>
                                            <td>${product.productName}</td>
                                            <td>
                                                <c:forEach var="pa" items="${product.productAttributes}">
                                                    ${pa.attribute.attributeGroup.groupName}:${pa.attribute.attributeName}&nbsp;&nbsp;
                                                </c:forEach>
                                            </td>
                                            <td>${product.costPrice}</td>
                                            <td>${product.salePrice}</td>
                                            <td>${product.marketPrice}</td>
                                            <td>${product.store.storeName}</td>
                                            <td>${product.storeNum}</td>
                                            <td>${product.productState}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="content-tab-5" class="tab-pane ">
                    <div class="inner-padding">
                        <div class="subheading">
                            <h3>详细信息</h3>
                        </div>
                        <div class="spacer-25"></div>
                        <div class="row">
                            <div class="col-sm-12">
                                ${commodity.content}
                            </div>
                        </div>
                    </div>
                </div>
                <div id="content-tab-6" class="tab-pane ">
                    <div class="inner-padding">
                        <div class="subheading">
                            <h3>详细信息</h3>
                        </div>
                        <div class="spacer-25"></div>
                        <div class="row">
                            <div class="col-sm-12">
                                ${commodity.explainContent}
                            </div>
                        </div>
                    </div>
                </div>
                <div class="spacer-30"></div>
                <hr>
                <div class="spacer-30"></div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="btn-group pull-right">
                            <a class="btn btn-default pull-right" href="commodities.do">返回</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="../layouts/footer.jsp"/>
    </div>
    <!-- End #content -->
</div>
<!-- End #main -->
</div>

</body>
</html>
