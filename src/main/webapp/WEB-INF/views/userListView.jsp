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
            <h2>회원 관리</h2>
          </div>
          <p class="lead">가입한 회원들을 관리할 수 있습니다.</p>
          <hr>
          <table class="table table-striped" style="max-width:1080px;">
            <thead>
              <tr>
                <th class="mobile" style="width:140px; text-align:center;">회원 아이디</th>
                <th style="text-align:center;">회원 이름</th>
                <th class="mobile" style="width:100px; text-align:center;">수정</th>
              </tr>
            </thead>
            <tbody>
	            <c:forEach items="${ userList }" var="user">
                    <c:if test="${ user.userType ne 0 }"><%--운영자 외 회원 표정 --%>
                        <tr>
                            <td style="text-align: center;">${ user.userID }</td>
                            <td style="text-align: center;">${ user.userName }</td>
                            <td onclick="location.href = './managerUserEditView.reservation?userID=${ user.userID }';" class="btn btn-primary btn-block">수정</td>
                        </tr>
                    </c:if>
	            </c:forEach>
             </tbody>
          </table>

            <!-- Paging -->
            <div class="container mt-4" style="max-width: 1080px; margin-left: 0; display: flex; justify-content: center;">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <c:forEach var="pageNumber" begin="1" end="${totalPage}">
                            <li class="page-item"><a class="page-link" href="/userListView.reservation?page=${pageNumber}">${pageNumber}</a></li>
                        </c:forEach>

                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>

        </main>
      </div>
    </div>
    <%@ include file="./viewFragment/footer.jspf" %>
    <%@ include file="./viewFragment/modal.jspf" %>
  </body>
</html>
