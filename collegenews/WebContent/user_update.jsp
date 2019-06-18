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
	margin: -190px 0 0 -170px;
	width: 340px;
	height: 380px;
	border: 1px solid #000000 transparent;
	text-align: center;
	background: #ddd;
}

.box {
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
	background: #ccc;
}
</style>
</head>
<body>
	<div class="box animated flip">
		<div class="menu">
			<div class="page-header">
				<h1>修改密码</h1>
			</div>
			<div class="col-sm-10 col-sm-offset-1">
				<form action="stu" method="post" class="form-horizontal form-reg">
				<input type="hidden" name="method" value="updatapwd"/>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<span><label class="control-label">原密码:</label> </span>
							</div>
							<input type="password" name="pwds"  class="form-control" />
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<span><label class="control-label">新密码:</label> </span>
							</div>
							<input type="password"  name="pwd"  required class="form-control" />
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<span><label class="control-label">密&emsp;码:</label> </span>
							</div>
							<input type="password" required name="repwd" 
								class="form-control" />
						</div>
					</div>
					<div class="form-group text-right">
						<button class="btn btn-primary btn-block btn-reg" type="submit">确定</button>
					</div>


				</form>
			</div>
		</div>
	</div>


	<%@ include file="sourceJs.jsp"%>
	<script>
		$(function(){
			$('.form-reg').bootstrapValidator({
				message:'输入的字段信息有误',
				feedbackIcons:{
					valid:'glyphicon glyphicon-ok',
					invalid:'glyphicon glyphicon-remove',
					validating:'glyphicon glyphicon-refresh',	
				},
				fields:{
					pwd:{
						message:'密码输入有误',
						validators:{							
							stringLength:{
								message:'密码必须保持在6~16之间',
								min:6,
								max:16
							}
						}
					},
					repwd:{
						message:'两次密码不一致',
						validators:{
							identical:{
								field:'pwd',
								message:'两次密码不一致'
							} 
						}
						
					}
					
				}
			});
			
			if('${msg}'){
				layer.msg('${msg}');
			}
		})
</script>
</body>
</html>