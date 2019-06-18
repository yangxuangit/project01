<%@ page language="java"  pageEncoding="UTF-8"%>
<!-- 导入jstl标准标签库的核心标签：c标签 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 格式化标签 -->
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="sourceCss.jsp" %>
<style>
	.navbar{
		border-radius:0;
	}
	.table td{
		vertical-align: middle;
		white-space:nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
		max-width: 200px;
	}
	.table td:last-child{
		text-align:center;
	}
	.btn-group{
		margin-bottom:10px;
	}
	textarea{
		resize:none;/*禁止改变大小*/
	}
</style>
</head>
<body>
  
<!-- 日报提交模态框 -->
	<div class="modal animated fadeInUp" id="dailyAdd">
		<form action="daily" class="form-horizontal" id="dailyAddForm">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" data-dismiss="modal">&times;</button>
					<h4>日报提交</h4>
				</div>
				<div class="modal-body">
					<!-- 隐藏域用于请求后台指定的方法 -->
					<input type="hidden" name="method" value="submit" />
					<div class="form-group">
						<label for="" class="control-label col-sm-2">今日任务</label>
						<div class="col-sm-8">							
							<textarea name="todaytask" id="" rows="3" class="form-control" required>
							</textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="control-label col-sm-2">完成情况</label>
						<div class="col-sm-8">							
							<textarea name="completeinfo" id="" rows="3" class="form-control" required>
							</textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="control-label col-sm-2">明日计划</label>
						<div class="col-sm-8">							
							<textarea name="nextplan" id="" rows="3" class="form-control" required>
							</textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" data-dismiss="modal">取消</button>
					<button class="btn btn-primary" type="submit">提交</button>
				</div>
			</div>
		</div>
		</form>
	</div>
<!-- 日报详情模态框 -->
	<div class="modal animated bounceIn" id="dailyInfo">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" data-dismiss="modal">&times;</button>
					<h4>日报详情</h4>
				</div>
				<div class="modal-body">
					<dl class="dl-horizontal">
						<dt>今日任务</dt>
						<dd id="d1"></dd>
						<dt>完成情况</dt>
						<dd id="d2"></dd>
						<dt>明日计划</dt>
						<dd id="d3"></dd>
					</dl>
				</div>
				<div class="modal-footer">
					<button class="btn btn-danger" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
<!-- 日报修改模态框 -->
	<div class="modal animated pulse" id="dailyEdit">
		<form action="daily" class="form-horizontal" id="dailyUpdateForm">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" data-dismiss="modal">&times;</button>
					<h4>日报修改</h4>
				</div>
				<div class="modal-body">
					<!-- 隐藏域用于请求后台指定的方法 -->
					<input type="hidden" name="method" value="update" />
					<input type="hidden" name="dailyid" value="" />
					<div class="form-group">
						<label for="" class="control-label col-sm-2">今日任务</label>
						<div class="col-sm-8">							
							<textarea name="todaytask" id="" rows="3" class="form-control" required>
							</textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="control-label col-sm-2">完成情况</label>
						<div class="col-sm-8">							
							<textarea name="completeinfo" id="" rows="3" class="form-control" required>
							</textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="control-label col-sm-2">明日计划</label>
						<div class="col-sm-8">							
							<textarea name="nextplan" id="" rows="3" class="form-control" required>
							</textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" data-dismiss="modal">取消</button>
					<button class="btn btn-primary" type="submit">确认修改</button>
				</div>
			</div>
		</div>
	    </form>
	</div>
<!-- 	导航区 -->

<!-- 	内容区 -->
	<div class="container-fluid">
<!-- 		路径导航 -->
		<ol class="breadcrumb">
		  <li><a href="#">日报管理</a></li>
		  <li class="active">日报列表</li>
		</ol>
		
<!-- 		按钮组 -->
		<div class="btn-group">
			<button class="btn btn-primary"  data-toggle="modal" data-target="#dailyAdd"><span class="glyphicon glyphicon-cloud-upload"></span>提交日报</button>
			<button class="btn btn-danger volumeDel"><span class="glyphicon glyphicon-remove"></span>批量删除</button>
		</div>
		
<!-- 		数据库区 -->
		<div class="data-container">
			<table class="table table-striped table-bordered">
				<tr>
					<th><input type="checkbox" id="selectAll"/></th>
					<th>序号</th>
					<th>今日任务</th>
					<th>完成情况</th>
					<th>明日计划</th>
					<th>提交时间</th>
					<th>更新时间</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${dailys}" var="daily" varStatus="stat">
				<tr>
					<td><input type="checkbox" name="ids" value="${daily.id}"/></td>
					<td>${stat.count}</td>
					<td title="${daily.todaytask}">${daily.todaytask}</td>
					<td title="${daily.completeinfo}">${daily.completeinfo}</td>
					<td title="${daily.nextplan}">${daily.nextplan}</td>
					<td>
						<fmt:formatDate value="${daily.subtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<fmt:formatDate value="${daily.lastupdatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<button class="btn btn-primary btn-sm btnInfo"  data-current-daily="${daily}" data-toggle="modal" data-target="#dailyInfo"><span class="glyphicon glyphicon-info-sign"></span>详情</button>
						<button class="btn btn-info btn-sm btnEdit" data-current-daily="${daily}" data-toggle="modal" data-target="#dailyEdit"><span class="glyphicon glyphicon-edit"></span>修改</button>
						<button class="btn btn-danger btn-sm btnDel" data-daily-id="${daily.id}"><span class="glyphicon glyphicon-remove"></span>删除</button>
					</td>
				</tr>
				</c:forEach>
			</table>
<!-- 			分页标签 -->
			<div class="text-right">
				<ul class="pagination">
					<c:choose>
						<c:when test="${cp == 1}">
							<li class="disabled"><a href="#"><span>&laquo;</span></a></li>
						</c:when>
						<c:otherwise>						
							<li><a href="daily?method=findMyDaily&cp=${cp-1}"><span>&laquo;</span></a></li>
						</c:otherwise>
					</c:choose>
					
					<c:forEach begin="1" end="${tp}"  var="i">
						<c:choose>
							<c:when test="${i == cp}">
								<li class="active"><a href="#">${i}</a></li>												
							</c:when>
							<c:otherwise>
								<li><a href="daily?method=findMyDaily&cp=${i}">${i}</a></li>	
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
					<c:choose>
						<c:when test="${cp == tp}">
							<li class="disabled"><a href="#"><span>&raquo;</span></a></li>
						</c:when>
						<c:otherwise>						
							<li><a href="daily?method=findMyDaily&cp=${cp+1}"><span>&raquo;</span></a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			
		</div>
	</div>
		
<%@ include file="sourceJs.jsp" %>
<script>
	$(function(){
		//根据是否返回消息，在页面提示信息
		if('${msg}'){
			layer.msg('${msg}');
		}
		
		//表单验证
		$('#dailyAddForm,#dailyUpdateForm').bootstrapValidator({
			message:'输入的字段信息有误',
			feedbackIcons:{
				valid:'glyphicon glyphicon-ok',
				invalid:'glyphicon glyphicon-remove',
				validating:'glyphicon glyphicon-refresh',	
			},
			fields:{
				todaytask:{
					validators:{
						stringLength:{
							min:20,
							message:'输入内容不能少于20个字符'
						}
					}
				},
				completeinfo:{
					validators:{
						stringLength:{
							min:20,
							message:'输入内容不能少于20个字符'
						}
					}
				},
				nextplan:{
					validators:{
						stringLength:{
							min:20,
							message:'输入内容不能少于20个字符'
						}
					}
				}
			}
			
		});
		
		//为详情按钮绑定事件{"":""}  {'':''}
		$('.btnInfo').on('click',function(){
			//获取按钮中的data-current-daily数据
			var daily = $(this).data('currentDaily');
			daily = eval('('+daily+')');
			//将日报详情在指定区域显示
			$('#d1').text(daily.todaytask);
			$('#d2').text(daily.completeinfo);
			$('#d3').text(daily.nextplan);
		})
		
		//为编辑按钮绑定事件
		$('.btnEdit').on('click',function(){
			//获取按钮中的data-current-daily数据
			var daily = $(this).data('currentDaily');
			//this.dataset.currentDaily
			//将获取的日报json数据转换为js对象
// 			daily = JSON.parse(daily);
			daily = eval('('+daily+')');
			
			$('#dailyUpdateForm textarea[name=todaytask]').val(daily.todaytask);
			$('#dailyUpdateForm textarea[name=completeinfo]').val(daily.completeinfo);
			$('#dailyUpdateForm textarea[name=nextplan]').val(daily.nextplan);
			//将日报id设置到隐藏域中
			$('#dailyUpdateForm input[name=dailyid]').val(daily.id);
		});
		
		//根据id删除
		$('.btnDel').on('click',function(){
			//data-daily-id
			var id = $(this).data('dailyId');
			layer.confirm('确认删除这条日志？',{
				btn:['确认','取消']
			},function(){
				//daily?method=del&id=XX
				location.href='daily?method=del&id='+id;
			});
		})
		//批量删除
		$('.volumeDel').on('click',function(){
			if($('input[name=ids]:checked').length>0){
				//获取所有被选中的复选框集合
				var cks = $('input[name=ids]:checked');
				var ids = [];
				//遍历
				$.each(cks,function(i,n){
					ids.push($(n).val());
				})
				layer.confirm('确定删除这 '+ids.length+' 条数据？',{
					btn:['确认','取消'],
					icon:0
				},function(){					
					//ids = ids.join(',');
					location.href='daily?method=volumeDel&ids='+ids;
				})
			}else{
				layer.alert('请至少选择一项删除!');
			}
		})
		
		//全选 保持其他复选框的选中状态与全选按钮的选中状态一致
		$('#selectAll').on('click',function(){
			//将所有input元素中name为ids的元素的选中状态设置为全选复选框的选中状态
			$('input[name=ids]').prop('checked',$(this).prop('checked'))
		})
		
		//安全退出
		$('#logout').on('click',function(){
			location.replace('stu?method=logout');
		})
	})
</script>
</body>
</html>