<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../header.jsp"%>

	<div class="group">
	<div class="sub-contents">
		<div class="sub-left">
  			<h2 style="margin-bottom:40px;"><a href="qna.do">お問い合わせ</a></h2>
  			 <div class="search_wrap">
					<div class="record_group">
						<p>件数&nbsp;<span>${count }</span>&nbsp;件</p><!-- ?=$count? php언어 ?= ?가 좌측깔대기퍼센트골뱅이 -->
					</div>
	  				<div class="select">
		  				<form name="search" method="get" action="qna.do">
		  					<select name="sel">
		  						<option value="title">タイトル</option>
		  						<option value="qcontent">内容</option>
		  					<!-- <option value="title-content">タイトル+内容</option> 
		  						<option value="m.nickname">書き手</option>-->
		  					</select>
		  					<input type="text" name="word" class="key">
		  					<input type="submit" class="btn ok" value="検索" style="padding:10px 20px;">
		  				</form>
	  				</div>
  			</div>
  			<div class="formtable">
  				<table>
  					<colgroup>
				<col width="10%">
				<col width="*">
				<col width="10%">
				<col width="10%">
				<col width="10%">
				
			</colgroup>
			<thead>
				<tr>
					<th>番号</th>
					<th>タイトル</th>
					<th>回答状況</th>
					<th>書き手</th>
					<th>日時</th>
					
				</tr>
			</thead>
  					<tbody>
  					<c:set var="num" value="${count -((pageMaker.cri.pageNum - 1) * 10)}"/>
	  					<c:forEach var="list" items="${list}">
	  						<tr>
  							<td>${num}</td>
  							<td><a href="qnaview.do?qbno=${list.qbno }">${list.title }</a></td>
  							<c:if test="${list.status == 0 }">
  							<td><span class="waiting">回答待ち</span></td>
  							</c:if>
  							<c:if test="${list.status == 1 }">
  							<td><span class="complete">回答済み</span></td>
  							</c:if>
  							<td>${list.nickname }</td>
  							<td>
  								<fmt:parseDate var="regdate" value="${list.regdate }" pattern="yyyy-MM-dd"/>
  								<fmt:formatDate value="${regdate }" pattern="yyyy-MM-dd"/>
  							</td>
  							
  						</tr>
  					<c:set var="num" value="${num-1 }"/>
  					</c:forEach>
  					</tbody>
  				</table>
  				 <div class="pagination">
  					<c:if test="${pageMaker.prev}">
  						<a href="${pageMaker.startPage-1}">&laquo;</a>
  					</c:if>
  					<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
  						<a href="${num}" class="${pageMaker.cri.pageNum == num?'active':''}">${num}</a>
  					</c:forEach>
 					<c:if test="${pageMaker.next}">
 						 <a href="${pageMaker.endPage+1}">&raquo;</a>
 					</c:if>
 					<c:choose>
 						<c:when test="${not empty userid}">
 							<div>
 								<button class="btn submit write" onclick="location.href='qawrite.do'">投稿</button>
 							</div>
 						</c:when>
 					</c:choose>
				</div>
  					<form id="actionForm" action="qna.do" method="get">
						<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
						<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
						<input type="hidden" name="sel" value="${pageMaker.cri.type}">
						<input type="hidden" name="word" value="${pageMaker.cri.keyword}">
  					</form>
  			</div>
  		</div>
		
		
	</div>
	</div>
	
	<script>
		$(function(){
			var actionForm = $("#actionForm");
			
			$(".pagination > a").on("click", function(e){
				e.preventDefault();
				actionForm.find("input[name='pageNum']").val($(this).attr("href"));
				actionForm.submit();
			})
		})
	</script>	

		<script>
		$(function() {
			
			$("#btn_qawrite").on("click", function(){
				//var userid = "${sessionScope.userid}";//세션영역에 존재하는 userid 값 저장
				var userid = "${userid}";
				if(!userid){
					alert("로그인 하세요");
					$(location).attr("href","login.do");
				}else{
					$(location).attr("href","qawrite.do");
				}
				
			})
			
		});
	</script>
	

<%@ include file="../footer.jsp"%>