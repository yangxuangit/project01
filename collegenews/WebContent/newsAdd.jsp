<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE >
<html>
  <head>

    <title>My JSP 'newsAdd.jsp' starting page</title>  
     
	<%@ include file="sourceCss.jsp"%>


<style>
a.regIcon {
	background-color: #0075D2;
	margin: 0 10px 0 0;
	padding: 8px 18px;
	color: #FFF;
	font-size:15pt;
	font-family: Georgia, "Times New Roman", Times, serif;
	text-decoration: none;
	height: 30px;
	width: 120px;
}

a.regIcon:hover {
	background-color: #c075D2;
	margin: 0 10px 0 0;
	padding: 8px 18px;
	color: #FFF;
	font-size:15pt;
	font-family: Georgia, "Times New Roman", Times, serif;
	text-decoration: none;
	height: 30px;
	width: 120px;
}

.input{
	width:90%;
	height:25px;
	font-size:12pt;
	border: 1px solid #808080;
	}
	#outter{
		width:100%;
		height:100%;
		border:1px solid #CCC;	
	}
	
	.table>tr>td{
         border: 0px solid transparent;
    }
    #outter {
    width: 100%;
    height: 100%;
    border: 0px solid #CCC;
}
</style>
</head>
<body topmargin="0" leftmargin="0">
<div id="outter">
新闻管理&gt;&gt;新闻发布
<hr size="2px">
<form id="form1" name="form1" method="post" action="news" enctype="multipart/form-data">
  <input type="hidden" name="method" value="insertNews"/>
  <table class="table" >  
    <tr>
      <td width="13%" align="right" valign="top">标题</td>
      <td width="87%"><label for="title"></label>
      <input type="text"  name="subject" id="title" class="form-control"/></td>
    </tr>
    <tr>
      <td align="right" valign="top">板块</td>
      <td><label for="block"></label>
		<input type="text"  name="cid" id="title" class="form-control" /></td>
	  </td>
    </tr>
    <tr>
      <td align="right" valign="top">发布人</td>
      <td><label for="publish"></label>
      <input type="text" name="publish" id="publish" class="form-control" value="${user.account}" disabled="disabled" class="input"/>
      <input type="hidden" name="cid" value="${user.aid}" class="form-control"/>
      </td>
    </tr>
    <tr align="left">
      <td align="right" valign="top">内容</td>
      <td><label for="content"></label>
      <textarea name="content" id="content"  class="form-control" rows="4"></textarea></td>
    </tr>
    <tr align="right" valign="top">
      <td align="right">图片</td>
      <td align="left">
      	 <input type="file" name="imgpath"/>
          <input style="margin-top:5px" type="button" value="add more..." onclick="add()" ><br>
          <span id="sp"></span>
      </td>
    </tr>
    <tr align="right" valign="top">
      <td colspan="2" align="center">
      	<a href="javascript:form1.submit();" class="regIcon" type="submit">发布</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:form1.reset();" class="regIcon">清空</a>
      </td>
    </tr>
  </table>
</form>
</div>
</body>
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
</html>
