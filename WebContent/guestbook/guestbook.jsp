<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../header.jsp" %>

<c:if test="${empty userid }"><!-- 로그인 안했을 경우 이 페이지로 못들어오게 , 헤더의 코어 뭐시기가 먼저 실행되게해야 함-->
	<script>
		location.href="login.do";
	</script>
</c:if>
	<div class="group">
	<div class="sub-contents">
		<div class="sub-left">
  			<h2 style="margin-bottom:40px;">GUEST BOOK</h2>
  			 <div class="search_wrap">
					<div class="record_group">
						<p>件数&nbsp;<span>${count }</span>&nbsp;件</p><!-- ?=$count? php언어 ?= ?가 좌측깔대기퍼센트골뱅이 -->
					</div>
	  				<div class="select">
		  				<form name="g_search" method="get" action="guestbook.do">
		  					<select name="sel">
		  						<option value="title">タイトル</option>
		  						<option value="content">内容</option>
		  					<!--  <option value="title-content">タイトル+内容</option>--> 
		  						<option value="writer">書き手</option>
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
  						<col width="12%">
  						<col width="12%">
  						<col width="12%">
  						
  					</colgroup>
  					<thead>
  						<tr>
  							<th>番号</th>
  							<th>タイトル</th>
  							<th>書き手</th>
  							<th>日時</th>
  							<th>クリック数</th>
  						</tr>
  					</thead>
  					<tbody>
  					<c:set var="num" value="${count -((pageMaker.cri.pageNum - 1) * 10)}"/>
	  					<c:forEach var="list" items="${guestList }">
	  						<tr>
	  							<td>${num}</td>
	  							<td><a href="guestbookview.do?bno=${list.bno }&pageNum=${pageMaker.cri.pageNum}&amount=${pageMaker.cri.amount}">${list.title }</a></td>
	  							<td>${list.writer }</td>
	  							<td>
	  								<fmt:parseDate var="regdate" value="${list.regdate }" pattern="yyyy-MM-dd"/>
	  								<fmt:formatDate value="${regdate }" pattern="yyyy-MM-dd"/>
	  							</td>
	  							<td>${list.viewcount }</td>
	  						</tr>
	  					<c:set var="num" value="${num-1}"/>
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
 					<div>
 						<button class="btn submit write" onclick="location.href='guestbookwrite.do'">投稿</button>
 					</div>
				</div>
					<form id="actionForm" action="guestbook.do" method="get">
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
  
 <%@ include file="../footer.jsp" %>