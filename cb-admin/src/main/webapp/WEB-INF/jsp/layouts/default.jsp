<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <title>水晶球 - <sitemesh:write property='title'/></title>

    <!-- // IOS webapp icons // -->

    <meta name="apple-mobile-web-app-title" content="Karma Webapp">
    <link rel="apple-touch-icon-precomposed" sizes="152x152" href="../images/mobile/apple-touch-icon-152x152.png"/>
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../images/mobile/apple-touch-icon-144x144.png">
    <link rel="apple-touch-icon-precomposed" sizes="120x120" href="../images/mobile/apple-touch-icon-120x120.png"/>
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../images/mobile/apple-touch-icon-114x114.png"/>
    <link rel="apple-touch-icon-precomposed" sizes="76x76" href="../images/mobile/apple-touch-icon-76x76.png"/>
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../images/mobile/apple-touch-icon-72x72.png"/>
    <link rel="apple-touch-icon-precomposed" href="../images/mobile/apple-touch-icon.png"/>
    <link rel="shortcut icon" href="../images/favicons/favicon.ico"/>

    <!-- // IOS webapp splash screens // -->

    <link rel="apple-touch-startup-image" media="(device-width: 768px) and (device-height: 1024px) and (orientation: portrait) and (-webkit-device-pixel-ratio: 2)"
          href="../images/mobile/apple-touch-startup-image-1536x2008.png"/>
    <link rel="apple-touch-startup-image" media="(device-width: 768px) and (device-height: 1024px) and (orientation: landscape) and (-webkit-device-pixel-ratio: 2)"
          href="../images/mobile/apple-touch-startup-image-1496x2048.png"/>
    <link rel="apple-touch-startup-image" media="(device-width: 768px) and (device-height: 1024px) and (orientation: portrait) and (-webkit-device-pixel-ratio: 1)"
          href="../images/mobile/apple-touch-startup-image-768x1004.png"/>
    <link rel="apple-touch-startup-image" media="(device-width: 768px) and (device-height: 1024px) and (orientation: landscape) and (-webkit-device-pixel-ratio: 1)"
          href="../images/mobile/apple-touch-startup-image-748x1024.png"/>
    <link rel="apple-touch-startup-image" media="(device-width: 320px) and (device-height: 568px) and (-webkit-device-pixel-ratio: 2)"
          href="../images/mobile/apple-touch-startup-image-640x1096.png"/>
    <link rel="apple-touch-startup-image" media="(device-width: 320px) and (device-height: 480px) and (-webkit-device-pixel-ratio: 2)"
          href="../images/mobile/apple-touch-startup-image-640x920.png"/>
    <link rel="apple-touch-startup-image" media="(device-width: 320px) and (device-height: 480px) and (-webkit-device-pixel-ratio: 1)"
          href="../images/mobile/apple-touch-startup-image-320x460.png"/>

    <!-- // Windows 8 tile // -->

    <meta name="application-name" content="Karma Webapp">
    <meta name="msapplication-TileColor" content="#333333"/>
    <meta name="msapplication-TileImage" content="images/mobile/windows8-icon.png"/>

    <!-- // Handheld devices misc // -->

    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="HandheldFriendly" content="true"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>

    <!-- // Stylesheets // -->

    <link rel="stylesheet" href="../bootstrap/core/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../bootstrap/select2/select2.css"/>
    <link rel="stylesheet" href="../bootstrap/datepicker/css/datepicker.css"/>
    <link rel="stylesheet" href="../bootstrap/fileupload/bootstrap-fileupload.min.css"/>
    <link rel="stylesheet" href="../bootstrap/typeahead/typeahead.min.css"/>
    <link rel="stylesheet" href="../bootstrap/colorpicker/css/colorpicker.css"/>
    <link rel="stylesheet" href="../bootstrap/timepicker/css/bootstrap-timepicker.min.css"/>
    <link rel="stylesheet" href="../fontawesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="../css/bootstrap-custom.css"/>
    <link rel="stylesheet" href="../css/bootstrap-extended.css"/>
    <link rel="stylesheet" href="../css/animate.min.css"/>
    <link rel="stylesheet" href="../css/helpers.css"/>
    <link rel="stylesheet" href="../css/custom.css"/>
    <link rel="stylesheet" href="../css/light-theme.css"/>
    <link rel="stylesheet" href="../css/mediaqueries.css"/>
    <link rel="stylesheet" href="../css/base.css"/>
    <link rel="stylesheet" href="../js/plugins/jquery-treetable/jquery.treetable.css"/>
    <link rel="stylesheet" href="../js/plugins/jquery-treetable/jquery.treetable.theme.default.css"/>

    <!-- // Helpers // -->

    <script src="../js/plugins/modernizr.min.js"></script>
    <script src="../js/plugins/mobiledevices.js"></script>

    <!-- // jQuery core // -->

    <script src="../js/libs/jquery-1.11.0.min.js"></script>
    <script src="../js/libs/jquery-ui.min.js"></script>
    <!-- // Bootstrap // -->

    <script src="../bootstrap/core/dist/js/bootstrap.min.js"></script>
    <script src="../bootstrap/select2/select2.min.js"></script>
    <script src="../bootstrap/bootboxjs/bootboxjs.min.js"></script>
    <script src="../bootstrap/holder/holder.min.js"></script>
    <script src="../bootstrap/typeahead/typeahead.min.js"></script>
    <script src="../bootstrap/datepicker/js/bootstrap-datepicker.min.js"></script>
    <script src="../bootstrap/fileupload/bootstrap-fileupload.min.js"></script>
    <script src="../bootstrap/inputmask/bootstrap-inputmask.min.js"></script>
    <script src="../bootstrap/colorpicker/js/bootstrap-colorpicker.min.js"></script>
    <script src="../bootstrap/timepicker/js/bootstrap-timepicker.min.js"></script>

    <!-- // Custom/premium plugins // -->

    <script src="../js/plugins/responsivetables.1.0.min.js"></script>
    <script src="../js/plugins/responsivehelper.1.0.min.js"></script>
    <script src="../js/plugins/mainmenu.1.0.min.js"></script>
    <script src="../js/plugins/easyfiletree.1.0.min.js"></script>
    <script src="../js/plugins/autosaveforms.1.0.min.js"></script>
    <script src="../js/plugins/chainedinputs.1.0.min.js"></script>
    <script src="../js/plugins/checkboxtoggle.1.0.min.js"></script>
    <script src="../js/plugins/bootstraptabsextend.1.0.min.js"></script>
    <script src="../js/plugins/lockscreen.1.0.min.js"></script>
    <script src="../js/plugins/autoexpand.1.0.min.js"></script>
    <script src="../js/plugins/notify.1.0.min.js"></script>
    <script src="../js/plugins/nanogress.1.0.min.js"></script>
    <script src="../js/plugins/powerwizard.1.0.min.js"></script>
    <script src="../js/plugins/simpleselect.1.0.min.js"></script>
    <script src="../js/plugins/tinycontextmenu.1.0.min.js"></script>

    <!-- // Third-party plugins // -->

    <script src="../js/plugins/tinyscrollbar.min.js"></script>
    <script src="../js/plugins/jquery.knob.js"></script>
    <script src="../js/plugins/prism.min.js"></script>
    <script src="../js/plugins/h5f.min.js"></script>
    <script src="../js/plugins/jquery.tablesorter.min.js"></script>
    <script src="../js/plugins/jquery.tablesorter.widgets.min.js"></script>
    <script src="../js/plugins/jquery.tablesorter.pager.min.js"></script>
    <script src="../js/plugins/fullcalendar.min.js"></script>
    <script src="../js/plugins/hogan-2.0.0.js"></script>
    <script src="../js/plugins/jquery.nouislider.min.js"></script>
    <script src="../js/plugins/jquery.autosize-min.js"></script>
    <script src="../js/plugins/jquery.magnific-popup.min.js"></script>
    <script src="../js/plugins/jquery.pwstrength.min.js"></script>
    <script src="../js/plugins/jquery.mixitup.min.js"></script>
    <script src="../js/plugins/jquery.vticker.min.js"></script>
    <script src="../js/flot/jquery.flot.min.js"></script>
    <script src="../js/flot/jquery.flot.resize.min.js"></script>
    <script src="../js/flot/excanvas.min.js"></script>
    <script src="../js/plugins/layout.min.js"></script>
    <script src="../js/plugins/masonry.pkgd.min.js"></script>
    <script src="../js/plugins/json2.js"></script>

    <script src="../js/plugins/jquery-tmpl/jquery.tmpl.min.js"></script>
    <script type="application/javascript" src="../js/ajaxfileupload.js"></script>
    <!-- // Custom //-->

    <script src="../js/plugins/plugins.js"></script>
    <script src="../js/plugins/demo.js"></script>
    <script src="../js/plugins/main.js"></script>
    <script type="text/javascript" src="../kendo/js/kendo.web.min.js"></script>
    <script type="text/javascript" src="../kendo/js/cultures/kendo.culture.zh-CN.min.js"></script>
    <link href="../kendo/styles/kendo.common.min.css" rel="stylesheet"/>
    <link href="../kendo/styles/kendo.bootstrap.min.css" rel="stylesheet"/>

    <script src="../js/plugins/validation/js/languages/jquery.validationEngine-zh_CN.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/plugins/validation/js/jquery.validationEngine.min.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="../js/plugins/validation/css/validationEngine.jquery.css" type="text/css"/>
    <script src="../js/plugins/jquery-treetable/jquery.treetable.js" type="text/javascript"></script>

    <script src="../js/common/common.js"></script>

    <script type="text/javascript" src="../highcharts/highcharts.js"></script>
    <script type="text/javascript" src="../highcharts/modules/exporting.js"></script>

    <script src="../js/district/district.js" type="text/javascript"></script>
    <script src="../js/profession/profession.js" type="text/javascript"></script>
    <script type="application/javascript">
        //全局后台图片库存放相对目录
        var PIC_PATH="${PIC_PATH}";

        Array.prototype.indexOf = function(val) {
            for (var i = 0; i < this.length; i++) {
                if (this[i] == val) return i;
            }
            return -1;
        };

        Array.prototype.remove = function(val) {
            var index = this.indexOf(val);
            if (index > -1) {
                this.splice(index, 1);
            }
        };
    </script>
    <sitemesh:write property='head'/>
</head>
<body class="hide-right-sidebar">
<div id="container" class="clearfix">
    <sitemesh:write property='body'/>
</div>
<!-- Lockscreen -->
<div class="lockscreen" id="lockscreen-slider">
    <div class="lockscreen-overlay"></div>
    <div class="lockscreen-modal">
        <img src="../images/users/user-1.jpg" alt="" class="lockscreen-avatar"/>

        <div class="lockscreen-placeholder"></div>
    </div>
</div>
</body>
</html>
