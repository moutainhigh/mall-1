<jsp:directive.page contentType="text/html;charset=UTF-8" />
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="refresh" content="3;url=../${param.reurl}">
	<title>操作失败</title>
</head>
<body>

<jsp:include page="../layouts/left.jsp"/>
<jsp:include page="../layouts/sidebarRight.jsp"/>

<div id="main" class="clearfix">

	<!-- ********************************************
         * MAIN HEADER:                             *
         *                                          *
         * the part which contains the breadcrumbs, *
         * dropdown menus, toggle sidebar button    *
         ******************************************** -->

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
					<li class="active">操作失败</li>
				</ul>
				<!-- End .breadcrumb -->
			</div>
			<div class="pull-right">
				<p>Version 1.0.0</p>
			</div>
		</div>
		<!-- End #header-main-bottom -->
	</header>
	<!-- End #header-main -->

	<div id="content" class="clearfix">


		<header id="header-sec">
			<div class="inner-padding">
				<div class="pull-left">
					<h2>操作失败</h2>
				</div>
				<div class="pull-right">
					<div class="dropdown">
						<a class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#">
							<i class="fa fa-comments"></i>
							<span class="indicator-dot">3</span>
						</a>


					</div>
					<!-- End .dropdown -->

					<!-- End .dropdown -->
				</div>
			</div>
			<!-- End .inner-padding -->
		</header>
		<!-- End #header-sec -->

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
			<!-- End .actionbar-->
			<div class="inner-padding">

				<div class="spacer-40"></div>
				<div class="hr-totop"><span>Top</span></div>
				<div class="spacer-10"></div>
				<div class="span12">
					<div class="head">
						<div class="isw-documents"></div>
						<h2>${msgTitle}</h2>
						<div class="clear"></div>
					</div>
					<div class="block-fluid" style="height: 500px;">
						<div class="control-group" style="padding-left: 300px;padding-top: 100px;font-size: large;">
							<div class="controls">
								<div class="error-bg">
									<div class="error-s">
										<div class="error-text" style="width: 600px;height: 200px;">${msgContent }</div>
										<div class="error-buttons">
											<a href="../console/dashboard.do" class="btn btn-primary btn-icon glyphicons home"><i></i> 返回首页</a>
											<a href="../${param.reurl}" class="btn btn-success btn-icon glyphicons unshare"><i></i>返回前一页</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- End .inner-padding -->
		</div>
		<!-- End .window -->

		<jsp:include page="../layouts/footer.jsp"/>
		<!-- End #footer-main -->
	</div>
	<!-- End #content -->
</div>
<!-- End #main -->

</body>
</html>



