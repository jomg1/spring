<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Board Modify</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
               Board Modify Page
            </div>
            
            <div class="panel-body">
            <form action="/board/modify" method="post"><!-- action:form submit하면 수행 될 기능 -->
              <input type="hidden" name="pageNum" value="${cri.pageNum }">
              <input type="hidden" name="pageNum" value="${cri.amount }">
            <div class="form-group">
                	  <label>Bno</label>
                	  <input class="form-control" type="text" name="bno" value="${board.bno }" >
                	</div>
                	<div class="form-group">
                	  <label>Title</label>
                	  <input class="form-control" type="text" name="title" value="${board.title }" >
                	</div>
                	<div class="form-group">
                	  <label>Writer</label>
                	  <input class="form-control" type="text" name="writer" value="${board.writer }" >
                	</div>
                	<div class="form-group">
                	  <label>Text Area</label>
                	  <textarea class="form-control" name="content">${board.content }</textarea>
                	</div>
                	<button type="submit" data-oper="modify" class="btn btn-default">Modify</button> <!-- data-oper라는 attribute 추가 -->
                	<button type="submit" data-oper="remove" class="btn btn-default">Remove</button>
                	<button type="submit" data-oper="list" class="btn btn-default">List</button>
            </form>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<script>
	$(document).ready(function(){
		var formObj = $('form');
		
		$('button').on('click', function(e){
			e.preventDefault(); // 기본기능 차단
			var operation = $(this).data('oper');
			console.log(operation);
			
			if (operation == 'remove'){
				formObj.attr('action', '/board/remove'); // attr 어트리뷰트 변경 시 사용
			} else if (operation == 'list'){
				//self.location = '/board/list';
				//return; //여기서 함수를 끝내겠다. 삭제는 submit으로 이벤트 발생시켜야하기때문에 list는 여기서 끝냄
				// pageNum, amount => clone() -> empty() -> append(clone item)
				var pageNumTag = formObj.find('input[name="pageNum"]').clone()
				var amountTag = formObj.find('input[name="amount"]').clone()
				formObj.attr('action', '/board/list').attr('method', 'get')
				formObj.empty(); // 파라미터 제거(목록으로 갈 경우 파라미터 필요하지 않음)
				formObj.append(pageNumTag);
				formObj.append(amountTag);
			}
			formObj.submit();//submit 호출
		})
	})

</script>
<jsp:include page="../includes/footer.jsp"></jsp:include>