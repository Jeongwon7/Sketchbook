<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../header.jsp" %>

 <c:if test="${adminId ne 'admin'}">
	<script>
		alert("ID、PASSWORDを確認してください");
		location.href='main.do';
	</script>
 </c:if>
	<div class="group">
	<div class="sub-contents">
		<div class="sub-left">
  			<h2 style="margin-bottom:40px;"><a href="adminlist.do">Administration - 会員管理</a></h2>
  			 <div class="search_wrap">
					<div class="record_group">
						<p>ユーザー数:&nbsp;<span>${count }</span>&nbsp;人</p><!-- ?=$count? php언어 ?= ?가 좌측깔대기퍼센트골뱅이 -->
					</div>
	  				<div class="select">
		  				<form name="g_search" method="get" action="adminlist.do">
		  					<select name="sel">
		  						<option value="id">ID</option>
		  						<option value="nickname">ニックネーム</option>
		  					<!--  <option value="title-content">タイトル+内容</option>--> 
		  						<option value="name">名前</option>
		  					</select>
		  					<input type="text" name="word" class="key">
		  					<input type="submit" class="btn ok" value="検索" style="padding:10px 20px;">
		  				</form>
	  				</div>
  			</div>
  			<div class="formtable">
  				<table>
  					<colgroup>
  						<col width="12%">
  						<col width="12%">
  						<col width="12%">
  						<col width="12%">
  						<col width="*">
  						<col width="12%">
  						<col width="12%">
  					</colgroup>
  					<thead>
  						<tr>
  							<th>番号</th>
  							<th>ID</th>
  							<th>名前</th>
  							<th>ニックネーム</th>
  							<th>e-mail</th>
  							<th>電話番号</th>
  							<th>管理</th>
  						</tr>
  					</thead>
  					<tbody>
  					<c:set var="num" value="${count -((pageMaker.cri.pageNum - 1) * 10)}"/>
	  					<c:forEach var="list" items="${UserList}">
	  						<tr>
	  							<td>${num}</td>
	  							<td>${list.id}</td>
	  							<td>${list.name}</td>
	  							<td>${list.nickname}</td>
	  							<td>${list.email}</td>
	  							<td>${list.phone}</td>
	  							<td>
	  								<button name="user_login" onclick="location.href='adminuserlogin.do?id=${list.id}'">ログイン </button>
	  								<button name="user_modify" onclick="location.href='adminusermodify.do?id=${list.id}'">修正</button>
	  								<button name="user_delete" onclick="user_DeleteOpen('${list.id}')">削除</button>
	  								<!--  <input type="hidden" name="id" id="${list.id}" value="${list.id}"> -->
	  							</td>
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
				</div>
					<form id="actionForm" action="admin.do" method="get">
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
	function user_DeleteOpen(id){
		var msg = confirm("本当に削除しますか");
		if(msg == true){
			deleteUser(id);
		}
	}

	function deleteUser(id){
		
		 $.ajax({
             url: "Userdelete.do",
             type: "POST",
             data:{id : id},
             success:function(){
                 alert("削除しました");
                location.href="adminlist.do";
             },
             error:function(error){
                 console.log(error);
             }
             
         });
	}
</script>

<%@ include file="../footer.jsp" %>