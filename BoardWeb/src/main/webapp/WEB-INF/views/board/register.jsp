<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Board Register
            </div>
            
            <div class="panel-body">
                <form role="form" action="/board/register" method="post">
                	<div class="form-group">
                	  <label>Title</label>
                	  <input class="form-control" type="text" name="title"><!-- name 속성:파라미터 -->
                	</div>
                	<div class="form-group">
                	  <label>Writer</label>
                	  <input class="form-control" type="text" name="writer">
                	</div>
                	<div class="form-group">
                	  <label>Text Area</label>
                	  <textarea class="form-control" name="content"></textarea>
                	</div>
                	<button type="submit" class="btn btn-default">Submit</button>
                	<button type="reset" class="btn btn-default">Reset</button>
                </form>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<jsp:include page="../includes/footer.jsp"></jsp:include>