<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<c:if test="${empty userid }">
	<script>
		alert("ログインしてください");
		location.href="login.do";
	</script>
</c:if>
 <div class="group" style="padding-bottom:220px;">
  	<div class="sub-contents">
  			<h2 style="width:100%; margin-bottom:60px;">退会を検討されている方へ</h2>
  			<div class="withdraw-group">
  			<p>退会手続きをされますと、Sketchbookのサービスすべてがご利用できなくなります。</p>
  			<p>今までご投稿したポスト、コメント、お問い合わせはすべて削除されます。</p>
  			<p>再度ご利用いただくには、また会員登録が必要となります。</P>
  			<p>本当に退会しますか。</p>
  			</div>
  			<div class="pagination">
  					<input type="button" onclick="withdrawOpen()" class="btn btn-success" value="退会する">
  					<input type="button" class="btn btn-info" id="btn-wdCancel" value="退会しない" onclick="location.href='main.do'">
			</div>
  </div>
  </div>

<script>
	function withdrawOpen(){
		var msg = confirm("本当に退会しますか");
		if(msg == true){
			withdrawCmt();
		}
		
	}
	
	function withdrawCmt(){
		var id = '<%=(String)session.getAttribute("userid")%>';
		location.href="memberwithdrawpro.do?id="+id;
	}
</script>
<%@ include file="../footer.jsp" %>