<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../header.jsp" %>

	<div class="group">
	<div class="sub-contents">
		<h2 style="border-bottom:2px solid #333333;">GUEST BOOK</h2>
		<div class="contents-lefts" style="width:1000px; margin:0 auto;">
			<div class="col-md-9">
				<div class="writer-info">
					<h2 style="padding-bottom:30px;" >${view.title }</h2>
					<div class="col-md-12">
						<span>${view.writer }&nbsp;&nbsp;&nbsp;|</span>&nbsp;&nbsp;&nbsp;
						<span>
						${fn:substring(view.regdate,0,10) }&nbsp;&nbsp;&nbsp;|
						</span>&nbsp;&nbsp;&nbsp;
						<span><i class="fa fa-eye"></i>&nbsp;${view.viewcount} </span>&nbsp;&nbsp;&nbsp;
					<!--<span><i class="fa fa-heart"></i>2</span>&nbsp;&nbsp;&nbsp;
						<span><i class="fa fa-comment"></i>0</span>
					 -->
					</div>
				</div>
					<div class="board-body text-center">
						<c:if test="${view.imgurl != null }">
							<img src="${pageContext.request.contextPath}/upload/${view.imgurl}" alt="" class="board-pic" >
						</c:if>
						<div class="text-info">
							<p style="text-align:left">
							${view.content}
							</p>
						</div>
					</div>
			<div class="prev_next">
			<c:if test="${prev != null}"><!-- 이전글이 있으면 -->
				<a href="guestbookview.do?bno=${prev.bno }" class="btn_prev">
					<i class="fa fa-angle-left"></i>
					<span class="prev_wrap">
						<strong>previous</strong>
						<br>
						<span>${prev.title}</span>
					</span>
				</a>
			</c:if>
				<div class="btn_3wrap">
						<a href="guestbookmodify.do?bno=${view.bno }" class="btn btn-primary" >修正</a>
						<a href="guestbookdelete.do?bno=${view.bno }"class="btn btn-success" onclick="confirm('本当に削除しますか?')">削除</a>
						<a href="guestbook.do" class="btn btn-info" >リスト</a>
					</div>
			<c:if test="${next != null }">
				<a href="guestbookview.do?bno=${next.bno }" class="btn_next">
					<span class="next_wrap">
						<strong>next</strong>
						<br>
						<span>${next.title}</span>
					</span>
					<i class="fa fa-angle-right"></i>
				</a>
			</c:if>
			</div>
					
				</div><!-- 9컬럼 끝 -->
		</div><!-- left end -->
		
	</div>
	</div>
	
  
  <%@ include file="../footer.jsp" %>
  
