<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>投票互动</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/main.css" />
<%@ include file="sourceCss.jsp" %>
<style type="text/css">
.single_block{
	font-size: 10pt;
}
a.regIcon {
	background-color: #0075D2;
	margin: 0 10px 0 0;
	padding: 6px;
	color: #FFF;
	font-family: Georgia, "Times New Roman", Times, serif;
	text-decoration: none;
	height: 30px;
	width: 120px;
}

a.regIcon:hover {
	background-color: #c075D2;
	margin: 0 10px 0 0;
	padding: 6px;
	color: #FFF;
	font-family: Georgia, "Times New Roman", Times, serif;
	text-decoration: none;
	height: 30px;
	width: 120px;
}
.progressbar{
	border:0;
	height: 20px;
}
.name{
    text-a
   }
   .name{
    font-weight: bold;

}
  #table{
     margin-left:100px;
  }
</style>
</head>

<body>
 <%String[] colors = {"#8A2BE2","#1E90FF","#FFD700","#E6E6FA","#BA55D3","#708090","#A52A2A","#4B0082","#4682B4","#9ACD32",
		 				"#FFF8DC","#0000FF","#F0FFFF","#00FF7F","#F08080","#228B22","#7FFFD4","#3CB371","#FFE4B5","#800000"}; %>
 
		<c:forEach items="${subject}" var="d" varStatus="stat">
				<tr>
					<td><h2 class="name">${d.sname}<small>（必须选一个，否则无法投票）</small></h2></td>

				</tr>
		</c:forEach>			
	 <form action="votenews">
	   <input type="hidden" name="method" value="Vote" />		
		<c:forEach var="op" items="${options}" varStatus="stat">
		<div class="" data-toggle="buttons">
         <table width="80%" id = "table">
			<tr> 
				 <td  style="text-align:right;">				  
	                <input  type="radio" value="${op.oid}" name="options"> 	                
				 </td>
				<td width=7%>
					${op.oname}
				</td>
				<!-- 图形显示百分比 -->
				<td align="left" width="50%">
				<c:if test="${votecount != 0}">
					<div class="progressbar" style="width:${(op.votecount/v)*100}%;background-color:<%=colors[new Random().nextInt(colors.length)] %>"></div>
				</c:if>
				<span>
				<c:if test="${votecount != 0}">
					<fmt:formatNumber value="${(op.votecount/v)*100}" pattern="##.##"></fmt:formatNumber>%
				</c:if>
				</span>
				</td>
				<!-- 显示投票数 -->
				<td align="left"  width="20%">
					<c:if test="${op.votecount eq null}">0</c:if>
					<c:if test="${!(op.votecount eq null)}">${op.votecount}</c:if>
				</td>
			</tr>
		</table>
		</div>
		</c:forEach>
		<div  style="text-align: center;"> 
		<button class="btn btn-default" type="submit">发起投票</button>
		 </div>
	</form>
	<div class="container-fluid" style="margin-left: 80%;">
	
			
		<a   href="javascript:history.back();" class="regIcon">返回</a>

   </div>
   <%@ include file="sourceJs.jsp"%>
   	<script>
		$(function(){
			if('${msg}'){
				//仿 toast
				//layer.msg('${msg}');
				
				layer.alert('${msg}',{
					icon:6
				})
			}
			
		})
	</script>
   
   
</body>
</html>
