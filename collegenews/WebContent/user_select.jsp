<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<%@ include file="sourceCss.jsp"%>
<style>
.menu {
	position: absolute;
	top: 50%;
	left: 50%;
	margin:-190px 0 0 -170px;
	width: 340px;
	height: 380px;
	border: 1px solid #000000 transparent;
	text-align: center;
	background: #ddd;
}
.box{
	position: absolute;
	left: 0;
	right: 0;
	top:0;
	bottom: 0;
	background: #ccc;
}
</style>
</head>
<body>
	<div class="box animated flip">
		<div class="menu">
			<div class="page-header">
				<h1>个人信息</h1>
			</div>
			<div class="col-sm-10 col-sm-offset-1">
				<form class="form-horizontal">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<span><label class="control-label">用户号:</label></span>
							</div>
							<input readonly type="text" name="username" id="" class="form-control"
								value="${stu.name}" />
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<span><label class="control-label">性&emsp;别:</label> </span>
							</div>
							<input readonly type="text" name="sex" id="" class="form-control"
								value="${stu.sex}" />
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<span><label class="control-label">注&emsp;册:</label></span>
							</div>
							<input readonly type="text" name="age" id="" class="form-control"
								value="${stu.regtime}" />
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<span><label class="control-label">手机号:</label></span>
							</div>
							<input readonly type="text" name="iphone" id="" class="form-control"
								value="${stu.phone}" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>