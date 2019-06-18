<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 导入jstl标准标签库的核心标签：c标签 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 格式化标签 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	.table th:last-child{
		text-align:center;
	}
	.btn-group{
		margin-bottom:10px;
	}
	textarea{
		resize:none;/*禁止改变大小*/
	}
	dl>dt,dl>dd{
		padding:5px 0;
	}
</style>
</head>
<body>
	<div class="table-responsive">
		<table class="table table-striped table-bordered table-hover" id="dataTables-example">
			<tr>
			    <th><input type="checkbox" id="selectAll"/></th>
				<th>序号</th>
				<th>标题</th>
				<th>内容</th>
				<th>分类</th>
				<th>提交时间</th>
				<th>操作</th>

			</tr>
			<c:forEach items="${pu.list}" var="d" varStatus="stat">
				<tr>
				    <td><input type="checkbox" name="ids" value="${daily.id}"/></td>
					<td>${pu.startNum + stat.count}</td>
					<td>${d.subject}</td>
					<td>${d.content}</td>
					<td>${d.aid}</td>
					<td>${d.releasetime}</td>
					<td>
						<button class="btn btn-primary btn-sm btnInfo" data-current-daily="${daily}" data-toggle="modal" data-target="#dailyInfo">
							<span class="glyphicon glyphicon-info-sign"></span>详情
						</button>
						<button class="btn btn-info btn-sm btnEdit" data-current-daily="${daily}" data-toggle="modal" data-target="#dailyEdit">
							<span class="glyphicon glyphicon-edit"></span>修改
						</button>
						<button class="btn btn-danger btn-sm btnDel" data-daily-id="${daily.id}">
							<span class="glyphicon glyphicon-remove"></span>删除
						</button>
					</td>

				</tr>
			</c:forEach>
		</table>
		<div class="text-center">	
		<ul class="pagination">
			<li><a href="news?method=findAllMynews&cp=${pu.prev}"><span>&laquo;</span></a></li>
			<c:forEach begin="${pu.start}" end="${pu.end}" var="i">
				<c:choose>
					<c:when test="${pu.currentPage == i}">
						<li class="active"><a href="">${i}</a></li>				
					</c:when>
					<c:otherwise>
						<li><a href="news?method=findAllMynews&cp=${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<li><a href="news?method=findAllMynews&cp=${pu.next}"><span>&raquo;</span></a></li>
		</ul>
	</div>
	</div>

	<%@ include file="sourceJs.jsp"%>
	<script>           
            $(function(){
            	$('.newslist').on('click',function(){
            		location.href='news?method=findMynews';
            		console.info("5555");
            		layer.alert('请至少选择一项删除!');
            	})
            	            	            	
            })
    </script>
</body>
</html>