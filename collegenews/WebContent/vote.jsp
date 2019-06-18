<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<%@ include file="sourceCss.jsp"%>
<style>
		.nav {
			text-align: center;
		}
		
		.input-group {
			margin-top: 15px;
		}
		
		.input-group {
			width: 100%;
		}
		.btn-group{
			float: right;
			margin-top: 20px;
		}
	</style>

	<body>
		<ol class="breadcrumb">
			<li>
				<a href="#">Home</a>
			</li>
			<li>
				<a href="#">Library</a>
			</li>
			<li class="active">投票</li>
		</ol>
		<h2 class="nav">发起投票</h2>
		<div class="container">
			<form action="votenews"  method="get" class="form-group">
			 <input type="hidden" name="method" value="insertVote"/>
				<div class="input-group">
					<div class="input-group input-group-lg">
						<span class="input-group-addon" id="sizing-addon1">投票新闻</span>
						<input name="newname" type="text" class="form-control" placeholder="Username" aria-describedby="sizing-addon1">
					</div>
					<div class="input-group input-group-lg">
						<span class="input-group-addon" id="sizing-addon1">投票原因</span>
						<input name="summary" type="text" class="form-control" placeholder="Username" aria-describedby="sizing-addon1">
					</div>
					<div class="input-group input-group-lg">
						<span class="input-group-addon" id="sizing-addon1">投票选项2</span>
						<input type="text" class="form-control" placeholder="Username" aria-describedby="sizing-addon1">
					</div>
				</div>
				<div class="container">
					<div class="btn-group " role="group" aria-label="...">
						<button  class="btn btn-primary" type="submit">发起投票</button>			
					</div>
		
				</div>
			</form>

		</div>
		<%@ include file="sourceJs.jsp"%>
          	<script>
		$(function(){
						
			if('${msg}'){
				layer.msg('${msg}');
			}
		})
</script>
	</body>

</html>