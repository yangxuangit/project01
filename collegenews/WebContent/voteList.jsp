<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE >
<html>
  <head>   
    <title>投票主题列表</title>
	<%@ include file="sourceCss.jsp" %>
   <style>
a.regIcon {
	background-color: #0075D2;
	margin: 0 10px 0 0;
	padding: 6px;
	color: #FFF;
	font-family: Georgia, "Times New Roman", Times, serif;
	text-decoration: none;
}
a.regIcon:hover {
	width: 60px;
	height: 35px;
	background-color: #3292DE;
	text-decoration: none;
}
.input{
	width:70%;
	font-size:12pt;
	}
	#outter{
		width:100%;
		height:100%;
		border:0px;	
	}
	table{
		margin:10px;
		padding:10px;
		border:1px solid #CCC;
		width:95%;
		border-collapse:collapse;/*合并相邻边框 */
		font-size: 10pt;
	}
	table th{
		background-color: #ccc;/*设置标题行的背景色 */
		border:1px solid #fff;
	}
	table td,table th{
		padding:5px;/*设置单元格中的内容和单元格边框之间的距离*/
		text-align: center;
	}
</style>
</head>
<body leftmargin="5px" topmargin="10px">
<div id="outter"> 
投票管理&gt;&gt;投票列表
<hr size="2px">
<div class="table-responsive">
		<table class="table table-striped table-bordered table-hover" id="dataTables-example">
			<tr>
			    <th><input type="checkbox" id="selectAll"/></th>
				<th>序号</th>
				<th>标题</th>
				<th>操作</th>

			</tr>
			<c:forEach items="${pu.list}" var="d" varStatus="stat">
				<tr>
				    <td><input type="checkbox" name="ids" value="${daily.id}"/></td>
					<td>${d.sid}</td>
					<td>${d.sname}</td>
					<td>
						<button class="btn btn-sm btnInfo" data-current-daily="${daily}" data-toggle="modal" data-target="#dailyInfo">
							<a href="votenews?method=queryVotecount&sid=${d.sid}">查看结果</a>
						</button>
						<button class="btn btn-info btn-sm btnEdit" data-current-daily="${daily}" data-toggle="modal" data-target="#dailyEdit">
							  <a href="">参与投票</a>
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
			<li><a href="votenews?method=querynews&cp=${pu.prev}"><span>&laquo;</span></a></li>
			<c:forEach begin="${pu.start}" end="${pu.end}" var="i">
				<c:choose>
					<c:when test="${pu.currentPage == i}">
						<li class="active"><a href="">${i}</a></li>				
					</c:when>
					<c:otherwise>
						<li><a href="votenews?method=querynews&cp=${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<li><a href="votenews?method=querynews&cp=${pu.next}"><span>&raquo;</span></a></li>
		</ul>
	</div>
	</div>
</div>
<%@ include file="sourceJs.jsp"%>
</body>
</html>