<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 格式化标签 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="sourceCss.jsp"%>
</head>
<style>           
			.logo {
				margin-top: -31px;
				width:250px;
			    height:50px;
			    
			}						
			.dowm {
				margin-top: 8px;
			}
			
			.inputss {
				border-radius: 10px !important;
			}
			.container-fluid-nav {
				   background-color: #02488E;
				   margin-left:30px
				}
			.container-fluid-nav a {
				   color: #FFF;
				}
				.container-fluid-nav a:hover{
				   color: #317EF3;
				}	
		
			
			.img-rounded>img {
				height: 18px;
			}
			
			.down {
				display: none;
				width: 100px;
				height: 165px;
				position: absolute;
				left: 0;
				top: 80px;
				background-color: #fff;
				background-image: url(fp/img/app-link-icon.3bc8fd38f5eb0cfcf9909122baaaa720.png);
				background-position: 37px 14px;
				background-size: 82px 82px;
				background-repeat: no-repeat;
				border: 1px solid #dcd4d7;
			  				
			}
			.down-user{
			    display: none;	
				position: absolute;
				left: 0;
				top: 80px;
			
			}
			.navbar-nav>li>.dropdown-menu {
					    margin-top: -30px;
					    border-top-left-radius: 0;
					    border-top-right-radius: 0;
					}
			
			
			.b {
				margin-top: 100px;
				text-align: center;
				font-size: 14px;
				color: #000;
				margin: 0;
			}
			
			.down-content {
				margin-top: 100px;
				text-align: center;
				font-size: 14px;
				color: #999;
				margin: 0;
			}
			
			.element {
				text-shadow: 2px 2px #939390 !important;
			}
			
			.a {
				color: white;
			}
			
			.aside {
				float: right;
				width: 360px;
				margin-right:50px;
			}
			
			.main {
				float: left;
				margin-left:20px
			}
			
			a {
				color: #333;
			}
			

			
			.bangshou {
				padding: 0px;
			}
			
			.center {
				margin-top: 15px;
			}
			
			h1 {
				display: inline;
			}
			
			.active:hover {
				color: white;
			}
			
			.caption:hover {
				background-color: #EF4238;
			}
			
			p {
				font-size: 26px;
				color: #ef4238!important;
			}
			
			.text-danger {
				margin-top: 20px;
			}
			
			.head {
				font-size: 24px;
			}			
			.navbar {
				padding: 0px !important;
				margin-top: 0px;
				margin-bottom: 0px;
			}
			
			.lunbo{
				width: 100%;
				margin-top:-2px;
				
			}
			
			.lunbotu{
				width: 100%;
				height:400px !important;
			}
			.content{
				margin-top: 15px;
			}
			.thumbnail img{
				width: 200px;
				height: 150px;
			}

		    .subject td{
				font-size: 13px;
			    padding-top: 10px;
			    line-height: 21px;
			     vertical-align: middle;
				white-space:nowrap;
				overflow: hidden;
				text-overflow: ellipsis;
				max-width: 700px;
			}
		</style>
<body>
     <div class="row nav-justified">
			<nav class="navbar navbar-static-top">
				<div class="container-fluid-nav">
					<div class=" navbar-brand">
						<a href="" class="navbar-brand">
							<img src="fp/img/logo.jpg" class="logo img-responsive">
						</a>
					</div>
					<div class="collapse navbar-collapse">
						<div class="row navbar-brand">
							<div class="dropdown">
								<button class="btn btn-default btn-block btn-xs" data-toggle="dropdown">
							武汉<span class="caret"></span>
						</button>
								<ul class="dropdown-menu ">
									<li>
										<a href="">北京</a>
									</li>
									<li>
										<a href="">上海</a>
									</li>
									<li>
										<a href="">深圳</a>
									</li>
								</ul>
							</div>
						</div>
				    <div class="head">

							<ul class="nav navbar-nav navbar-left col-sm-offset-1">
								<li>
									&nbsp;
								</li>
								<li class="">
									<a href="news?method=findMynews" class="active">首页</a>
								</li>
								<li>
									<a href="news?method=fpfindAllMynews2&cid=2">实时热点</a>
								</li>
								<li>
									<a href="news?method=fpfindAllMynews3&cid=3">军事</a>
								</li>
								<li>
									<a href="news?method=fpfindAllMynews4&cid=4">榜单</a>
								</li>
								<li>
									<a href="votenews?method=fpquerynews">投票互动</a>
								</li>
							</ul>
						</div>

						<form action="" class="navbar-form navbar-left">
							<div class="form-group">
								<div class="input-group input-group-sm ">
									<div class="input-group">
										<input type="text" class="form-control" placeholder="Search for...">
										<span class="input-group-btn">
                                      <button class="btn btn-default" type="button">Go!</button>
                                   </span>
									</div>
								</div>
							</div>
						</form>
					<div class="dowm btn-group  navbar">
						<ul class="nav navbar-nav navbar-left">						 
						    <li class="dropdown">
						    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
						    <span class="glyphicon glyphicon-qrcode"></span>     app二维码下载<span class="caret"></span></a>
							<ul class="dropdown-menu down" style="min-width: 100%;">
								<li class="a">aa</li>
								<li class="a">aa</li>
								<li class="a">aa</li>
								<li class="a">aa</li>
								<li class="a">aa</li>
								<li class="b">扫码下载app</li>
								<li class="down-content">选座更优惠</li>
							</ul>
			
							</li>
						</ul>
							<ul class="nav navbar-nav navbar-right">
								<li><a href="#"><span class="glyphicon glyphicon-user"></span></a></li>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown">欢迎你，${stu.name}<span class="caret"></span></a>
									<ul class="dropdown-menu down-user" style="min-width: 100%;">
										<li class="text-center"><a href="#"
											style="font-size: 16px;">修改密码</a></li>
										<li class="text-center"><a href="javascript:void(0)"
											id="logout" style="font-size: 16px;">安全退出</a></li>
									</ul>
								</li>
							</ul>

					</div>
                         
					</div>
                     
				</div>

		   </nav>
     </div>   	
        <div class="container-fluid">
        	<div class="content">
				<div class="row">
					<div class="aside">
						<p>通知公告</p>
						<div class="thumbnail">
							<table class="">
								<tr>
									<td class="bangshou col-sm-8"><img class="img-responsive img-rounded" src="fp/img/2018062002.jpg" alt="通用的占位符缩略图"></td>
									<td class="col-sm-4">
										<table class="">
											<tr>
												<th>
													<h4>湖北工程学院2018年专项公开招聘工作人员公告</h4></th>
											</tr>
											<tr>
												<td>31.11万
													<td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<table class="table center">

								<thead>
									<tr>
										<th>
											<a href="">关于在防空警报试鸣期间集中组织开展国防教育的通知</a>
										</th>
										<th class="text-right">26万</th>
									</tr>
								</thead>
								<thead>
									<tr>
										<th>
											<a href="">关于开展2018年度科研实验室安全检查工作的通知</a>
										</th>
										<th class="text-right">26万</th>
									</tr>
								</thead>
								<thead>
									<tr>
										<th>
											<a href="">2018年9月14日校领导接待日安排通知</a>
										</th>
										<th class="text-right">24万</th>
									</tr>
								</thead>
								<thead>
									<tr>
										<th>
											<a href="">关于开展2018年“湖北产业教授”申报工作的通知</a>
										</th>
										<th class="text-right">26万</th>
									</tr>
								</thead>						
							</table>
						</div>
						<div class="media">
							<a class="media-left" href="#">
								<img class="media-object" src="img/图片1.png" alt="媒体对象">
							</a>
							<div class="media-body">
								<p class="media-heading">
									<h1 class="">7801.1万</h1>
									<a href="" class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查看更多<span class="glyphicon glyphicon-chevron-right"></span></</a>

								</p>
								<h4 class="text-danger  text-justify">北京时间17：09：01 猫眼专业版实时票房数据</h4>
							</div>
						</div>
					</div>
					<div class="main col-sm-8">
						<p>
							新闻列表
							<a href="" class="navbar-right">全部 <span class="glyphicon glyphicon-chevron-right"></span></a>
						</p>
                     <c:forEach var="pu" items="${pu.list}" varStatus="stat">
						<div class="col-md-12">
							<div class="row">
								<div class="col-sm-12">
									<div class="thumbnail">
										<table class="">
											<tr>
												<td  class="bangshou col-sm-3"><img
													class="img-responsive img-rounded"
													src="../../headicon/${pu.imgpath}"
													alt="通用的占位符缩略图"></td>
												<td class="col-sm-8">
													<table class="subject">
														<tr>
															<th>
																<h4><a href="#">${pu.subject}</a></h4>
															</th>
														</tr>
														<tr>
															<td>${pu.content }
															<td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
					<div class="text-center">	
				  </div>
				</div>
			</div>	
			<div class="" style="text-align: center;">
		<ul class="pagination" >
			<li><a href="news?method=fpfindAllMynews2&cid=2&cp=${pu.prev}"><span>&laquo;</span></a></li>
			<c:forEach begin="${pu.start}" end="${pu.end}" var="i">
				<c:choose>
					<c:when test="${pu.currentPage == i}">
						<li class="active"><a href="">${i}</a></li>				
					</c:when>
					<c:otherwise>
						<li><a href="news?method=fpfindAllMynews2&cp=${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<li><a href="news?method=fpfindAllMynews2&cid=2&cp=${pu.next}"><span>&raquo;</span></a></li>
		</ul>													
			</div> 
		</div>
	</div>

<%@ include file="sourceJs.jsp"%>
<script>
$(function(){
                $('#myCarousel').carousel({interval:2000});
            })
</script> 
</body>
</html>