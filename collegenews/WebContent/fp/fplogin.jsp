<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="sourceCss.jsp" %>
<style>
	.btn-reg{
		margin-left:50%;
		width:50%;
	}
</style>
</head>
<body>
	<div class="container animated bounceInRight">
		<div class="page-header">
			<h3>学生登录</h3>
		</div>
		<div class="col-sm-5">		
			<form action="../stu" method="post" class="form-horizontal form-login">
<!-- 				隐藏域用于传递操作指令到后台 -->
				<input type="hidden" name="method" value="fplogin"/>				
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">
							<span class="glyphicon glyphicon-phone"></span>
						</div>
						<input type="text" name="phone"  required pattern="^1[3578]\d{9}$" class="form-control" value="${phone}" placeholder="请输入手机号"/>
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">
							<span class="glyphicon glyphicon-lock"></span>
						</div>
						<input type="password" name="pwd"  required class="form-control" value="${pwd}" placeholder="请输入密码"/>
					</div>
				</div>
				<div class="form-group">
					<div class="row">					
						<div class="col-sm-1">
							<img class="img-rounded validate-code" src="../ValidateCode" class="validate-code" alt="验证码" title="看不清，换一张" />
						</div>
						<div class="col-sm-9 col-sm-offset-2">						     
							 <input class="form-control" type="text" name="vcode" id="vcode" placeholder="输入验证码" />
						</div>
					</div>
				</div>
				<div class="form-group text-right">
					<span>还没有账号？立即<a href="reg.jsp">注册</a></span>
				</div>
				<div class="form-group text-right">
					<button class="btn btn-primary btn-block btn-reg" type="submit">登录</button>
				</div>
			</form>
		</div>
	</div>
	<%@ include file="sourceJs.jsp" %>
	<script>
		$(function(){
			$('.form-login').bootstrapValidator({
				message:'输入的字段信息有误',
				feedbackIcons:{
					valid:'glyphicon glyphicon-ok',
					invalid:'glyphicon glyphicon-remove',
					validating:'glyphicon glyphicon-refresh',	
				}
			});
			$('.validate-code').css('cursor','pointer')
			   .on('click',function(){
					$(this).prop('src','ValidateCode?r='+Math.random());
				});

			
			//
			if('${msg}'){
				//仿 toast
				layer.msg('${msg}',{icon:5});
				
// 				layer.alert('${msg}',{
// 					icon:5
// 				})
       
			}
			
		})
	</script>
</body>
</html>