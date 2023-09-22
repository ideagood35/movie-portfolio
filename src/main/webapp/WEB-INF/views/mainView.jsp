<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<!-- echart import test -->
<script src="./js/echarts.js"></script>
<!-- 헤더 파일 불러오기 -->
<%@ include file="./viewFragment/header.jspf" %>
<body>
<div class="container-fluid">
    <div class="row d-flex d-md-block flex-nowrap wrapper">
        <%@ include file="./viewFragment/navigation.jspf" %>
        <main id="main" class="col-md-9 float-left col pl-md-5 pt-3 main">
            <div class="page-header mt-3">
                <h2>영화 예매 사이트</h2>
            </div>
            <p class="lead">이곳은 현재 상영중인 영화 티켓을 예매할 수 있는 사이트입니다.</p>
            <hr>

            <div id="test" style="width: 600px;height:400px;"></div>
            <script type="text/javascript">
                // based on prepared DOM, initialize echarts instance
                var myChart = echarts.init(document.getElementById('test'));

                // specify chart configuration item and data
                const resultList = [3372,2307,1447,1290,1102];
                const gigwanList = ["국립","물향","국립","국립","강원"];
                const styleOption = {
                    title : "aaaa",
                    tooltip: {

                    },
                }

                // 		 		const legend = [1,23,3,4,5]

                var option = {
// 		        	...styleOption,
                    title: {
                    },
                    tooltip: {},
                    legend: {
                        data:['Sales']
                    },
                    xAxis: {
                        data: gigwanList
                    },
                    yAxis: {

                    },
                    series: [{
                        type: 'bar',
                        data: resultList,
                        itemStyle: {
                            color: "#91cc75",
                            borderColor: "#3ba272"
                        }
                    }]
                };

                // use configuration item and data specified to show chart
                myChart.setOption(option);
            </script>



            <!-- 영화 예고편을 동영상 형태로 볼 수 있게 미디어 태그 이용 -->
            <br></br>
            <p class="lead mt-4 mb-3 pt-4 pb-2">개봉작 예고편</p>
            <iframe width="50%" height="50%" src="https://www.youtube.com/embed/xUDhdCsLkjU" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
            <br></br>
            <p class="lead mt-4 mb-3 pt-4 pb-2">개봉 예정작 예고편</p>
            <iframe width="50%" height="50%" src="https://www.youtube.com/embed/MoMd7ae98hk" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
            <section class="mt-4 mb-3 pt-4 pb-4" style="max-width:1080px;">
                <br></br>
                <p class="lead">공지사항</p>
                <hr>
                <!-- 테이블 형태의 공지사항 작성 -->
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th class="mobile" style="width:55px; text-align:center;">번호</th>
                        <th style="text-align:center;">제목</th>
                        <th class="mobile" style="width:80px; text-align:center;">작성자</th>
                        <th class="mobile" style="width:120px; text-align:center;">날짜</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <!-- 글의 순서에 따라 번호를 붙이는 게시물 형식 만들기 -->
                        <td style="text-align: center;">3</td>
                        <td>신규 영화 시사회 참여자를 모집합니다.</td>
                        <td style"text-align: center;">운영자</td>
                        <td style="text-align: center;">2018-05-05</td>
                    </tr>
                    <tr>
                        <td style="text-align: center;">2</td>
                        <td>영화 예매 웹 사이트에 오신 것을 환영합니다.</td>
                        <td style="text-align: center;">운영자</td>
                        <td style="text-align: center;">2018-05-04</td>
                    </tr>
                    <tr>
                        <td style="text-align: center;">1</td>
                        <td>영화 예매 웹 사이트가 개설되었습니다.</td>
                        <td style="text-align: center;">운영자</td>
                        <td style="text-align: center;">2018-05-03</td>
                    </tr>
                    </tbody>
                </table>
            </section>
        </main>
    </div>
</div>
<!-- footer, modal 파일 불러오기 -->
<%@ include file="./viewFragment/footer.jspf" %>
<%@ include file="./viewFragment/modal.jspf" %>
</body>
</html>
