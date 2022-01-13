<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../header.jsp" %>


	<div class="group">
	  <div class="sub-contents">
		 <div class="sub-left">
			 <h2><a href="faq.do">よくある質問</a></h2>
		 		<div class="search_wrap">
					<div class="record_group" id="record_group">
						<p>件数&nbsp;<span>${count }</span>&nbsp;件</p><!-- ?=$count? php언어 ?= ?가 좌측깔대기퍼센트골뱅이 -->
					</div>
	  				<div class="select">
		  				<form name="search" method="get" action="faq.do">
		  					<select name="sel">
		  						<option value="fq">質問</option>
		  						<option value="fa">回答</option>
		  					<!--  <option value="title-content">タイトル+内容</option>--> 
		  					</select>
		  					<input type="text" name="word" class="key">
		  					<input type="submit" class="btn ok" value="検索" style="padding:10px 20px;">
		  				</form>
	  				</div>
  			</div>
  			
  			
		<div class="faq-group">
		<ul>
		
			<c:forEach var="list" items="${faqlist}">
			<li>
				<button class="accordion">
				${list.fq}
				</button>
				<div class="panel">
					<p>${list.fa}</p>
					<c:choose>
 						<c:when test ="${not empty adminId}">
							<div class="btn-faq-wrap">
								<button type="button" class="btn btn-info btn-faq" onclick="location.href='faqmodify.do?bno=${list.bno}'">修正</button>
								<button type="button" class="btn btn-success btn-faq" onclick="faqDeleteOpen(${list.bno})">削除</button>
							</div>
						</c:when>
					</c:choose>
				</div>
			</li>
			</c:forEach>
		</ul>
		</div>

	<script>
			$(document).ready(function(){
			  $(".panel").hide();
			  $("ul li .accordion").click(function(){
			    $(this).next().slideToggle(300);
			    $("ul li .accordion").not(this).next().slideUp(300);
			    return false;
			  });
			  $("ul li .accordion").eq(0).trigger("click");
			});
	</script>

		<div class="pagination formtable">
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
 						<c:when test ="${not empty adminId}">
		 					<div>
		 						<button class="btn submit write btn-faq-write" onclick="location.href='faqwrite.do'">投稿</button>
		 					</div>
		 				</c:when>
 					</c:choose>
		</div>
		
			<form id="actionForm" action="faq.do" method="get">
				<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
				<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
				<input type="hidden" name="sel" value="${pageMaker.cri.type}">
				<input type="hidden" name="word" value="${pageMaker.cri.keyword}">
  			</form>
		
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
	
	function faqDeleteOpen(bno){
		console.log(bno);
		var msg = confirm("本当に削除しますか");
		if(msg == true){
			deletefaq(bno);
		}
	}

	function deletefaq(bno){
		
		 $.ajax({
             
             url: "faqdelete.do",
             type: "post",
             data:{bno : bno},
             success:function(result){
            	 $("#record_group p span").html(result);
                 alert("削除しました");
                 location.href = "faq.do";
             },
             error:function(error){
                 console.log(error);
             }
             
         });
	}
</script>
<%@ include file="../footer.jsp" %>