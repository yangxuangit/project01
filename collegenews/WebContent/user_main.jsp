<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>前台界面</title>
<%@ include file="sourceCss.jsp"%>
<style>
 .* {
	padding: 0px;
	margin: 0px;
}
  .navbar {
    position: relative;
    min-height: 50px;
    margin-bottom: 0px;
    border: 1px solid transparent;
   }
.logo {
	heigth: 130px;
	width: 160px
}

.menu {
	float: left;
	margin-bottom: 0;
	margin-top: 0;
	width: 15%;
	height: 550px;
	border: 1px solid red transparent;
}

.main {
	float: left;
	margin-bottom: 0;
	margin-top: 0;
	width: 85%;
	height: 550px;
	border: 1px solid transparent;
}

* {
	margin: 0;
	padding: 0;
}

ul {
	list-style: none;
}

.hmenu>li>a {
	text-decoration: none;
	display: block;
	height: 50px;
	line-height: 50px;
	text-align: center;
	color: #fff;
	background: #3498db;
}

.submenu>li>a {
	text-decoration: none;
	display: block;
	height: 50px;
	line-height: 50px;
	text-align: center;
	color: #fff;
	border-bottom: 1px solid rgba(255, 255, 255, 0.5);
	transition: all .5s;
}

.submenu {
	display: none;
}

.submenu>li>a:hover {
	background: orange;
}

.hmenu>li {
	width: 100%;
	background: #ccc;
	font-size: 25px;
}

.hmenu>li:not (:last-child ){
	border-bottom: 1px solid #fff;
}

.menu {
	background: #F2F2F2;
}

.main {
	background: #F2F2F2;
}

.mybody {
	height: 100%;
}

.iframe-box {
	height: 505px;
}
</style>
</head>
<body>
	<div>
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<img src="img/logo.png" alt="" class="logo" />
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#userInfo">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<div class="navbar-collapse collapse" id="userInfo">
					<ul class="nav navbar-nav navbar-right">
					   <li><a href="#"><span class="glyphicon glyphicon-user"></span></a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">欢迎你，${stu.name}<span class="caret"></span></a>
							<ul class="dropdown-menu" style="min-width: 100%;">
								<li class="text-center"><a href="#"
									style="font-size: 16px;">修改密码</a></li>
								<li class="text-center"><a href="javascript:void(0)"
									id="logout" style="font-size: 16px;">安全退出</a></li>
						  </ul></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>

	<div>
		<div class="menu">
			<ul class="hmenu">
				<li><a href="#">个人中心</a>
					<ul class="submenu">
						<li><a href="user_select.jsp" target="showiframe">查看信息</a></li>
						<li><a href="user_update.jsp" target="showiframe">修改密码</a></li>
					</ul></li>
				<li><a href="#" >新闻管理</a>
					<ul class="submenu">
						<li><a href="news?method=findAllMynews" target="showiframe" class="newslist">新闻列表</a></li>
						<li><a href="newsAdd.jsp" target="showiframe">新闻添加</a></li>
						<li><a href="" target="showiframe">新闻查询</a></li>
					</ul></li>
				<li><a href="#">反馈/回馈</a>
					<ul class="submenu">
						<li><a href="daily?method=findMyDaily" target="showiframe">日志列表</a></li>
						<li><a href="" target="showiframe">接收回馈</a></li>
					</ul></li>
				<li><a href="#">投票管理</a>
					<ul class="submenu">
						<li><a href="vote.jsp" target="showiframe">发起投票</a></li>
						<li><a href="votenews?method=querynews" target="showiframe">投票结果</a></li>
					</ul></li>
			</ul>

		</div>

		<div class="main">
			<div class="mybody">
				<div class="mybody-header">
					<ol class="breadcrumb" style="padding: 0 20px;">
						<li><a href="#">首页</a></li>
						<li class="active">当前页</li>
					</ol>
				</div>
				<div class="iframe-box">
					<iframe src="user_select.jsp" width="100%" height="100%"
						frameborder="0" name="showiframe"></iframe>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="sourceJs.jsp"%>
			<script>
				$(function() {
					//将页面中的所有空链接失效
					$('a[href=""]').attr('href', 'javascript:void(0)');
		
					$('.hmenu>li').hover(function() {
						$(this).children('.submenu').stop().slideDown();
					}, function() {
						$(this).children('.submenu').stop().slideUp();
					})					          			           
				    
                
					//安全退出
					$('#logout').on('click', function() {
						location.replace('stu?method=logout');
					})

				})
			</script>
</body>
</html>