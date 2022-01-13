<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%@ include file="header.jsp" %>
  
	<div class="slider">
    	<div class="images">
    		<img src="images/slide03.JPG">
    		<div class="text">
    			<h1>Sketch Your Daily Life!</h1>
    		</div>
    	</div>
    	<div class="images">
    		<img src="images/slide02.JPG">
    		<div class="text">
    			<h1>Sketch Your Daily Life!</h1>
    		</div>
    	</div>
    	<div class="images">
    		<img src="images/slide01.JPG">
    		<div class="text">
    			<h1>Sketch Your Daily Life!</h1>
    		</div>
    	</div>
 	</div>
 	
 	
 	
 	<div class="contents">
  		<div class="album-list">
  			<h2>Popular Posts</h2>
  		<div class="album">
	  		<div class="index-slick">
	  			<c:forEach var="plist" items="${plist}">
			  		<div class="list">
			  			<img src="${pageContext.request.contextPath}/upload/${plist.imgurl}" alt="인기글 사진">
			  			<div class="list-txt">
			  				<a href="myblogview.do?bno=${plist.bno}&writer=${plist.writer}">
			  					<h3>${plist.title}</h3>
				  				<p>${fn:substring(plist.content,0,30)}...</p>
				  				<span>
									<fmt:parseDate var="regdate" value="${plist.regdate }" pattern="yyyy-MM-dd"/>
									<fmt:formatDate value="${regdate}" pattern="yyyy-MM-dd"/>
								</span>&nbsp;&nbsp;&nbsp;
								<span><i class="fa fa-eye"></i>&nbsp;${plist.viewcount }</span>&nbsp;&nbsp;&nbsp;
							</a>
								<a href="myblog.do?writer=${plist.writer}"><span>by.${plist.nickname}</span></a>
				  			
				  		</div>
			  		</div>
		 	 	</c:forEach>
	  		</div>
  		</div>
  	</div>
  </div>
  <div class="contents">
  		<div class="latestpost">
  			<div class="latest"><!-- 1240 -->
  				<h2>Latest Posts</h2>
  				<c:forEach var="list" items="${list}">
  				<div class="post">
  					<div class="post-left">
  						<img src="${pageContext.request.contextPath}/upload/${list.imgurl}">
  					</div>
  					<div class="post-right">
  						<a href="myblogview.do?bno=${list.bno}&writer=${list.writer}">
  						<h3>${list.title}</h3>
							<p>${fn:substring(list.content,0,150)}...</p>
							<span>
								<fmt:parseDate var="regdate" value="${list.regdate }" pattern="yyyy-MM-dd"/>
								<fmt:formatDate value="${regdate}" pattern="yyyy-MM-dd"/>
							</span>&nbsp;&nbsp;&nbsp;
							<span><i class="fa fa-eye"></i>&nbsp;${list.viewcount }</span>&nbsp;&nbsp;&nbsp;
						</a>
						<a href="myblog.do?writer=${list.writer}"><span>by.${list.nickname}</span></a>	
  						
  					</div>
  				</div>
  				</c:forEach>
  			</div>
  		</div>
  	</div>
  		
  	
  	
  	<script type="text/javascript">
   
      $('.index-slick').slick({
    	  infinite: true,
    	  slidesToShow: 3,
    	  slidesToScroll: 1,
    	  
      });
   
  </script>
  <script>

   
   </script>
  <%@ include file="footer.jsp" %>
  
  
