<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

    <div class="member-contents">
  	<div class="member-group">
  		<div class="member">
  			<h2>JOIN/会員登録</h2>
  			<div class="formtable">
  			<form name="member" method="post" enctype="multipart/form-data" action="member.do" id="member" onsubmit="return check()">
  				<table >
  					<colgroup>
  						<col width="160px">
  						<col width="auto">
  					</colgroup>
  					<tbody>
  						<tr>
  							<th scope="col" ><label for="name">名前</label></th>
  							<td class="text-left">
  								<input type="text" name="name" id="name" class="w300">
  								<span id="namemsg"></span>
  							</td>			
  						</tr>
  						<tr>
  							<th scope="col" ><label for="nickname">ニックネーム</label></th>
  							<td class="text-left">
  								<input type="text" name="nickname" id="nickname" class="w300">
  								<span id="nicknamemsg"></span>
  							</td>			
  						</tr>
  						<tr>
  							<th scope="col" ><label for="id">ID</label></th>
  							<td class="text-left">
  								<input type="text" name="id" id="id" class="w300">
  								<span id="idmsg"></span>
  							</td>			
  						</tr>
  						<tr>
  							<th scope="col" ><label for="pw1">PASSWORD</label></th>
  							<td class="text-left">
  								<input type="password" name="pw1" id="pw1" class="w300">
  							   <p class="guideTxt"><span class="tc_point"></span></p>		
  							</td>	
  						</tr>
  						<tr>
  							<th scope="col" ><label for="pw2">PASSWORD確認</label></th>
  							<td class="text-left">
	  							 <input type="password" name="pw2" id="pw2" class="w300">
	  							 <p class="guideTxt" id ="pw2msg" ><span class="tc_point"></span></p>
  							</td>			
  						</tr>
  						<tr>
  							<th scope="col" ><label for="email">メールアドレス</label></th>
  							<td class="text-left">
  								<input type="text" name="email" id="email" class="w300" placeholder="メールアドレス">
  								<input type="button" id ="btn_email" class="btn btn_gray" value="認証番号転送" style="width:25%;">	
  									<input type="password" name="certinumber" class="w300 guideTxt"  id="certinumber" placeholder="認証番号">
  									<input type="button" name="email_ok" id="email_ok" class="btn btn_gray " value="番号認証" style="width:25%;">
  									<p class="guideTxt" id="emailmsg"><span ></span></p>
  							</td>
  						</tr>
  						<tr>
  							<th scope="col" ><label for="phone">電話番号</label></th>
  							<td class="text-left">
  								<input type="text" name="phone" id="phone" class="w300">
  								<span id="phonemsg"></span>
  							</td>			
  						</tr>
  						<tr>
  							<th>プロフィール画像</th>
  							<td><input type="file" name="profileimg" accept="image/jpeg, image/png, image/jpg,  image/JPG" id="isFile"></td>			
  						</tr>
  					<!-- <tr>
  							<th scope="col" >住所</th>
  							<td style="text-align:left;">
  								<input type="text" name="post" style="width:120px;">
  								<a href="" class="btn ok">郵便番号</a><br>
  								<input type="text" name="address1"><br>
  								<input type="text" name="address2" placeholder="詳細住所をご入力ください">
  							</td>			
  						</tr>-->
  						
  						
  					</tbody>
  				</table>
  				<div class="pagination">
  					<input type="submit" class="btn submit" id="btn_ok" value="会員登録">
  					<input type="reset" class="btn reset" value="書き直し">
				</div>
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
			$("#email_ok").on("click", function(){
				var certinumber = $("#certinumber").val();
				//입력받은 인증번호
				if(certinumber == ""){
					alert("認証番号を入力してください");
					$("#certinumber").focus();
				}else{
					$.ajax({
						type:"post",
						data:{"certinumber":certinumber},//인풋에서 입력받은 거랑 인증번호랑 맞는지?
						url:"emailcerticheck.do",
						dataType:"json",
						//서버에서 넘어오는 데이터 형식 JSON{name:"value"}
						success:function(data){
							$("#emailmsg").html("<span>"+data.msg+"</span>");
							if(data.check == "nok"){
								$("#emailmsg").html("<span style='color:#f00;'>"+data.msg+"</span>");
								//emailmsg에 .html
						//		location.href="memberupdate.do"
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
  
 
  
  	<script>
		$(function() {
			
			$("#name").blur(function(){
				if(!$("#name").val()){//네임 값이 없으면
					$("#namemsg").html("<span style='color:#f00;'>名前を入力してください</span>");
					
				}else{
					$("#namemsg").html("");
				}
			})
			
			
				$("#nickname").blur(function(){
				if(!$("#nickname").val()){
					$("#nicknamemsg").html("<span style='color:#f00;'>ニックネームを入力してください</span>");
				
				}else{
					$("#nicknamemsg").html("");
				}
			
				$.ajax({

					  type:'post',
					  url:'memberNickCheck.do',
					  data:{nickname:$("#nickname").val()},
					  
					  success:function(data) {
						  if(data == -1){//중복됐을 경우 1 중복아닐 때 -1
							  if($("nickname").val() != ""){//and 조건
								  $("#nicknamemsg").html("使用可能なニックネームです。");
							  }
						  }else if(data == 1){//data(result)가 1이면
							  if($("nickname").val() != ""){
								  $("#nicknamemsg").html("<span style='color:#f00;'>使用中のニックネームです</span>");
								 
								  $("#nickname").focus();
							  	
							  }
						  }
					     
					  },
					  error : function(xhr,status,error) {
					      alert("통신에러")
					  },
					  
					});
			})
			
			
			$("#id").blur(function(){//input id 위치를 벗어났을 때
				if(!$("#id").val()){
					$("#idmsg").html("<span style='color:#f00;'>IDを入力してください</span>");
				
				}else{
					$("#idmsg").html("");
				}
			
				$.ajax({

					  type:'post',
					  url:'memberIdCheck.do',
					  data:{id:$("#id").val()},
					  
					  success:function(data) {
						  if(data != 1){
							  if($("id").val() != ""){//and 조건
								  $("#idmsg").html("使用可能なIDです");
							  }
						  }else{//data(result)가 0이면
							  if($("id").val() != ""){
								  $("#idmsg").html("<span style='color:#f00;'>使用中、もしくは脱退した会員のIDです</span>");
								  $("#id").val("");
								  $("#id").focus();
							  	
							  }
						  }
					     
					  },
					  error : function(xhr,status,error) {
					      alert("통신에러")
					  },
					  
					});
			})
			
			$("#email").blur(function(){
				var email = $("#email").val();
				if(email == ""){
					$("#emailmsg").html("<span style='color:#f00;'>メールアドレスを入力してください</span>");
				}
			})
			
			$("#pw2").blur(function(){
				var pw1 = $("#pw1").val();
				var pw2 = $("#pw2").val();
				
				if(pw1 != pw2){
					$("#pw2msg").html("<span style='color:#f00;'>パスワードを確認してください</span>");
				}else{
					$("#pw2msg").html("<span class='tc_point'>パスワードが一致しています</span>");
				}
			})
			
				$("#certinumber").blur(function(){//인증번호 위치 벗어났을 때
					var certinumber = $("#certinumber").val();
					//입력받은 인증번호
					if(certinumber == ""){
						$("#emailmsg").html("<span style='color:#f00;'>認証番号を入力してください</span>");
						
					}else{
						$.ajax({
							type:"post",
							data:{"certinumber":certinumber},//인풋에서 입력받은 거랑 인증번호랑 맞는지?
							url:"emailcerticheck.do",
							dataType:"json",
							//서버에서 넘어오는 데이터 형식 JSON{name:"value"}
							success:function(data){
								$("#emailmsg").html("<span>"+data.msg+"</span>");
								if(data.check == "nok"){
									$("#emailmsg").html("<span style='color:#f00;'>"+data.msg+"</span>");
									//emailmsg에 .html
							//		location.href="memberupdate.do"
									//href 속성에 멤버업데이트.두 넣고 location 실행 ->  멤버업데이트.두가 실행된다
								}
							},error:function(){
								alert("通信エラー");
							}
						})
					}
			})
			
			
			$("#phone").blur(function(){
				if(!$("#phone").val()){
					$("#phonemsg").html("<span style='color:#f00;'>電話番号を入力してください</span>");
					
				}else{
					$("#phonemsg").html("");
				}
			})
			
			
		});//end
	</script>
	
	 <script>
		function check() {
		
			var imgFile = $('#isFile').val();
			var fileForm = /(.*?)\.(JPG|jpg|jpeg|png|gif|bmp|pdf)$/;
			var maxSize = 20 * 1024 * 1024;
			var fileSize;
		
			if($('#isFile').val() == "") {
				alert("プロフィール画像を添付してください");
			    $('#isFile').focus();
			    return false;
			}

			if(imgFile != "" && imgFile != null) {
				fileSize = document.getElementById("isFile").files[0].size;
			    
			    if(!imgFile.match(fileForm)) {
			    	alert("イメージファイルのみ添付可能です");
			        return false;
			    } else if(fileSize >= maxSize) {
			    	alert("ファイルサイズは最大20MBです");
			        return false;
			    }
			}
			
			
			return true;
		}
	</script>
 <%@ include file="../footer.jsp" %>
  
