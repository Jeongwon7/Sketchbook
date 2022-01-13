<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../header.jsp" %>
<c:if test="${empty userid }">
	<script>
		alert("ログインしてください");
		location.href="login.do";
	</script>
</c:if>
  <div class="member-contents">
  	<div class="member-group">
  		<div class="member" style="width:500px;">
  			<h2>メール認証</h2>
  			<div class="formtable"><!-- enctype="multipart/form-data":첨부파일 있을 때 꼭 적어주기 -->
  			<form class="mail" name="mail" method="post">
  				<table>
  					<colgroup>
  						<col width="*">
  						<col width="15%">
  					</colgroup>
  					<tbody>
  						<tr>
  							<td class="border_bottom_none">
  								<input type="text" name="email" id="email" placeholder="メールアドレス">
  							</td>
  							<td class="border_bottom_none">	
  								<input type="button" id ="btn_email" class="btn btn_gray" value="認証番号転送">		
  							</td>	
  						</tr>
  						<tr>
  							<td >
  								<input type="password" name="certinumber"  id="certinumber" placeholder="認証番号">
  							</td>
  							<td>
  								<input type="button" name="btn_ok" id="btn_ok" class="btn btn_gray" value="番号認証">
  							</td>
  						</tr>
  					</tbody>
  				</table>
  				</form>
  			</div>
  		</div>
  	</div>	
  </div>
 	<script>
		$(function() {
			
			//이메일인증
				$("#btn_email").on("click", function(){
					var email = $("#email").val();
					if(email == ""){
						alert("メールアドレスを入力してください");
						$("#email").focus();
					}else {
						$.ajax({
							type:'post',
							url:'emailsend.do',
							data:{"email":$("#email").val()},
							dataType:'json',//서버로부터 응답받는 데이터 타입
							success:function(data){
								alert(data.msg);
								
							},
							error:function(){
								alert("通信エラー");
							}
							
						})
					}
				})
				
				
			//인증확인 버튼 이벤트 처리
			$("#btn_ok").on("click", function(){
				var certinumber = $("#certinumber").val();
				//입력받은 인증번호
				if(certinumber == ""){
					alert("認証番号を入力してください");
					$("#certinumber").focus();
				}else{
					$.ajax({
						type:"post",
						data:{"certinumber":certinumber},//인풋에서 입력받은 거랑 인증번호랑 맞는지?
						url:"mypage.do",
						dataType:"json",
						//서버에서 넘어오는 데이터 형식 JSON{name:"value"}
						success:function(data){
							alert(data.msg);
							if(data.check == "ok"){
						//		location.href="memberupdate.do"
								$(location).attr("href", "memberupdate.do");
								//href 속성에 멤버업데이트.두 넣고 location 실행 ->  멤버업데이트.두가 실행된다
							}
						},error:function(){
							alert("通信エラー");
						}
					})
				}
				
			})
			});
	</script>
 
  

  
  <%@ include file="../footer.jsp" %>
  
