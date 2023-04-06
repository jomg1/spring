<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Board Read Page
            </div>
            
            <div class="panel-body">
            <div class="form-group">
                	  <label>Bno</label>
                	  <input class="form-control" type="text" name="bno" value="${board.bno }" readonly>
                	</div>
                	<div class="form-group">
                	  <label>Title</label>
                	  <input class="form-control" type="text" name="title" value="${board.title }" readonly>
                	</div>
                	<div class="form-group">
                	  <label>Writer</label>
                	  <input class="form-control" type="text" name="writer" value="${board.writer }" readonly>
                	</div>
                	<div class="form-group">
                	  <label>Text Area</label>
                	  <textarea class="form-control" name="content">${board.content }</textarea>
                	</div>
                	<button data-oper="modify" class="btn btn-default">Modify</button> <!-- data-oper라는 attribute 추가 -->
                	<button data-oper="list" onclick="location.href='list'" class="btn btn-default">List</button>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<jsp:include page="../includes/footer.jsp"></jsp:include>