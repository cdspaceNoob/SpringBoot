<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<!-- body -->
<div class="container">

	<form action="/action_page.php">
		<div class="form-group">
			<label for="username">User Name:</label> <input type="text" class="form-control" placeholder="Enter user Name" id="username">
		</div>

		<div class="form-group">
			<label for="email">Email:</label> <input type="email" class="form-control" placeholder="Enter Email" id="email">
		</div>

		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>

		<button type="submit" class="btn btn-primary">가입 완료</button>
	</form>
	
</div>

<%@ include file="../layout/footer.jsp"%>