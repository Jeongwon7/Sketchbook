<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../header.jsp" %>


	<div class="group">
	<div class="sub-contents">
		
			<h2><a href="myblog.do?writer=${myblogList[0].writer}">${myblogList[0].nickname}'s Blog</a></h2>
		
			<div class="record_group">
				<p>件数&nbsp;<span>${count }</span>&nbsp;件</p><!-- ?=$count? php언어 ?= ?가 좌측깔대기퍼센트골뱅이 -->
			</div>
		<div class="contents-left">
		<c:set var="num" value="${count - ((pageMaker.cri.pageNum-1) * 10)}"/>
			<c:forEach var="list" items="${myblogList}" >
				<div class="contents-post">
					
						<img src="${pageContext.request.contextPath}/upload/${list.imgurl}" alt="" class="contents-pic">
					
					<div class="contents-text">
						<a href="myblogview.do?bno=${list.bno}&pageNum=${pageMaker.cri.pageNum}&amount=${pageMaker.cri.amount}&writer=${list.writer}">
						<h3>${list.title }</h3>
						<p>${fn:substring(list.content,0,80)}...</p>
						<span>
							<fmt:parseDate var="regdate" value="${list.regdate }" pattern="yyyy-MM-dd"/>
							<fmt:formatDate value="${regdate}" pattern="yyyy-MM-dd"/>
						</span>&nbsp;&nbsp;&nbsp;
						<span><i class="fa fa-eye"></i>&nbsp;${list.viewcount }</span>
						</a>	
					</div>
				</div>
			<c:set var="num" value="${num-1}" />
			</c:forEach>
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
 					
				</div>
				<form id="actionForm" action="myblog.do" method="get">
						<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
						<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
						<input type="hidden" name="sel" value="${pageMaker.cri.type}">
						<input type="hidden" name="word" value="${pageMaker.cri.keyword}">
						<input type="hidden" name="writer" value="${myblogList[0].writer}">
  				</form>
		</div>
		<div class="contents-right">	
			<div class="select">
		  		<form name="myblog_search" method="get" action="myblog.do">
		  			<select name="sel" id="sel" style="height:39px;">
		  				<option value="title">タイトル</option>
		  				<option value="content">内容</option>
		  				<!--  <option value="title-content">タイトル+内容</option>--> 
		  			</select>
		  				<input type="text" name="word" id="word" class="key" style="width:280px;">
		  				<input type="hidden" name="writer" value="${myblogList[0].writer}">
		  				<input type="submit" class="btn ok" value="検索"  style="padding:11px 20px;">
		  		</form>
	  		</div>
  			<div class="content-popular">
  			<h2>Popular Posts</h2>
  				<c:forEach var="plist" items="${plist}">
  					<div class="post">
  						
  							<img src="${pageContext.request.contextPath}/upload/${plist.imgurl}" alt="popular post イメージ"class="photo">
  						
  						<div class="post-right">
  							<a href="myblogview.do?bno=${plist.bno}&writer=${plist.writer}">
  							<h3>${plist.title}</h3>
  							<span>
  								<fmt:parseDate var="regdate" value="${plist.regdate}" pattern="yyyy-MM-dd"/>
								<fmt:formatDate value="${regdate}" pattern="yyyy-MM-dd"/>
  							</span>
  							</a>
  						</div>
  					</div>
  				</c:forEach>
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
  
