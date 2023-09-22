<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <%@ include file="./viewFragment/header.jspf" %>
  <body>
    <div class="container-fluid">
      <div class="row d-flex d-md-block flex-nowrap wrapper">
        <%@ include file="./viewFragment/navigation.jspf" %>
        <main id="main" class="col-md-9 float-left col pl-md-5 pt-3 main">
          <div class="page-header mt-3">
            <h2>회원 정보 수정</h2>
          </div>
          <p class="lead">사용자 정보를 수정합니다.</p>
          <hr>
          <form action="./userEditAction.reservation" method="POST" class="pt-3" style="max-width:720px;">
            <div class="form-group">
              <label>이름</label>
              <div>${userName}</div>
            </div>
            <div class="form-group">
              <label>전화번호</label>
              <div>${userPhone}</div>
            </div>
            <div class="form-group">
              <label>주소</label>
              <div>${userAddress}</div>
            </div>
            <div class="form-group">
              <label>이메일</label>
              <div>${userEmail}</div>
            </div>
          </form>
        </main>
      </div>
    </div>
    <%@ include file="./viewFragment/footer.jspf" %>
    <%@ include file="./viewFragment/modal.jspf" %>
  </body>
</html>