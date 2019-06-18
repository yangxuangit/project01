<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>测试</h2>

	<c:forEach var="pu" items="${pu.list}" varStatus="stat">
		<div class="row">
			<div class="aside col-sm-4">
				<div class="thumbnail">
					<table class="">
						<tr>
							<td class="bangshou col-sm-8"><img
								class="img-responsive img-rounded"
								src="../../headicon/${pu.imgpath}"
								alt="通用的占位符缩略图"></td>
							<td class="col-sm-4">
								<table class="">
									<tr>
										<th>
											<h4>${pu.subject}</h4>
										</th>
									</tr>
									<tr>
										<td>3158.11万
										<td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<img src="" alt="通用的占位符缩略图">
	</c:forEach>
	<%@ include file="sourceJs.jsp"%>
</body>
</html>