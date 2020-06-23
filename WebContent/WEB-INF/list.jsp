<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.PersonVo" %>


<%
	List<PersonVo> pList = (List<PersonVo>)request.getAttribute("personList");	// (List<PersonVo>)를 사용하여 형 변환을 해줍니다.	
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main page</title>
</head>
<body>
	<h1>전화번호 리스트</h1>
	<p>입력한 정보의 내역입니다.</p>
	
	<%----------------------------------------------------------------------------------------------%>

	<%----------------------------------------------------------------------------------------------%>

	<%for(PersonVo vo : pList) {%>
	<table border="1">
		<colgroup>
			<col style="width: 120px;">
			<col style="width: 170px;">
		</colgroup>

		<tbody>
			<tr>
				<td>이름(Name)</td>
				<td><%=vo.getName() %></td>
			</tr>

			<tr>
				<td>핸드폰(Hp)</td>
				<td><%=vo.getHp() %></td>
			</tr>

			<tr>
				<td>회사(Company)</td>
				<td><%=vo.getCompany() %></td>
			</tr>
			
			<tr>
				<td>수정</td>
				<td>삭제</td>
			</tr>

		</tbody>
	</table>

	<br>

	<% } %>
		
		<p>
			<a href="/pb2/pbc?action=wform">추가번호 등록</a>
		</p>
		
</body>
</html>