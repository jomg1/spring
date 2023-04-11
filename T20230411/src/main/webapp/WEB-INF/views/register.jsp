<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 <div class="wrap_container">
    <header>
        <h3 class="header-item">도서대여관리</h3>
    </header>    
    <nav>
        <ul class="container">
            <li><a href="" >도서 등록</a></li>
            <li><a href="2_bookRent.html">도서 조회/수정</a></li>
            <li><a href="3_bookReturn.html">대여현황조회</a></li>
            <li><a href="1_home.html">홈으로</a></li>
        </ul>
    </nav>
    <section >
            <h2 style="text-align: left">도서대여</h2>
            
            <form action="">  
                <table style="width:70%;margin:auto;">
                    <tr>
                        <td class="col1_title">도서번호</td>
                        <td><input type="text" name="rent_no" value="자동생성"></td> 
                    </tr>
                    <tr>
                        <td class="col1_title">도서명</td>
                        <td><input type="text" name="rent_idx" value="회원번호 입력"></td> 
                    </tr>
                    <tr>
                        <td class="col1_title">도서표지</td>
                        <td><input type="text" name="bcode" value="도서코드 입력"></td> 
                    </tr>
                    <tr>
                        <td class="col1_title">출판일자</td>
                        <td> <input type="date" name="rent_date" onchange="endOutput()"> <button>대여</button></td> 
                    </tr>
                    <tr>
                        <td class="col1_title">반납예정일자</td>
                        <td> <input type="date" name="return_date" onchange="endOutput()"> </td> 
                    </tr>
                
                </table>
            </form>
    </section>
    <footer>
        HRDKOREA Copyright©2016 All rights reserved. Human Resources Development Service of Korea. 
    </footer>
</div>

</body>
</html>