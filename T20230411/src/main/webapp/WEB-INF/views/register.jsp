<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 등록</title>
</head>
<body>

	<div class="wrap_container">
		<header>
			<h3 class="header-item">도서대여관리</h3>
		</header>
		<nav>
			<ul class="container">
				<li><a href="">도서 등록</a></li>
				<li><a href="">도서 조회/수정</a></li>
				<li><a href="">대여현황조회</a></li>
				<li><a href="">홈으로</a></li>
			</ul>
		</nav>
		<section>
			<h2 style="text-align: left">도서등록</h2>
		<form role="form" action="register" method="post">
			<div class="form-group">
			</div>
			<div class="form-group">
				<label>도서번호</label> <input class="form-control" type="text" name="bookNo" value="${bookNo }">
			</div>
			<div class="form-group">
				<label>도서명</label> <input class="form-control" type="text" name="bookName">
			</div>
			<div class="form-group">
				<label>도서표지</label> <input class="form-control" type="text" name="bookCoverimg">
			</div>
			<div class="form-group">
				<label>금액</label>
				<input class="form-control" type="text" name="bookPrice">
			</div>
			<div class="form-group">
				<label>출판사</label>
				<input class="form-control" type="text" name="bookPublisher">
			</div>
			<div class="form-group">
				<label>도서소개</label>
				<textarea class="form-control" name="bookInfo"></textarea>
			</div>
			<button type="submit" class="btn btn-default">등록</button>
			<button type="submit" class="btn btn-default">조회</button>
		</section>
		<footer> HRDKOREA Copyright©2016 All rights reserved. Human
			Resources Development Service of Korea. </footer>
	</div>

</body>
</html>