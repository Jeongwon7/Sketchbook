<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

 <c:if test="${adminId ne 'admin'}">
	<script>
		alert("ID、PASSWORDを確認してください");
		location.href='main.do';
	</script>
 </c:if>
	  <div class="member-contents">
  	<div class="member-group">
  		<div class="member">
  			<h2>Administration - 会員情報修正</h2>
  			<div class="formtable">
  			<form name="member" method="post" enctype="multipart/form-data" action="adminusermodify.do" id="member" onsubmit="return check()">
  				<table >
  					<colgroup>
  						<col width="160px">
  						<col width="auto">
  					</colgroup>
  					<tbody>
  						<tr>
  							<th scope="col" ><label for="name">名前</label></th>
  							<td class="text-left">
  								<input type="text" name="name" id="name" value="${upmember.name}" class="w300">
  								<span id="namemsg"></span>
  							</td>			
  						</tr>
  						<tr>
  							<th scope="col" ><label for="nickname">ニックネーム</label></th>
  							<td class="text-left">
  								<input type="text" name="nickname" id="nickname" value="${upmember.nickname}"class="w300">
  								<span id="nicknamemsg"></span>
  							</td>			
  						</tr>
  						<tr>
  							<th scope="col" ><label for="id">ID</label></th>
  							<td class="text-left">
  								<input type="text" name="id" id="id" value="${upmember.id}" class="w300" readonly>
  								<span id="idmsg"></span>
  							</td>			
  						</tr>
  						<tr>
  							<th scope="col" ><label for="pw1">PASSWORD</label></th>
  							<td class="text-left">
  								<input type="password" name="pw1" id="pw1" class="w300">
  							   <p class="guideTxt"><span class="tc_point"></p>		
  							</td>	
  						</tr>
  						<tr>
  							<th scope="col" ><label for="pw2">PASSWORD確認</label></th>
  							<td class="text-left">
	  							 <input type="password" name="pw2" id="pw2" class="w300">
	  							 <p class="guideTxt"><span class="tc_point">入力しない場合、既存のPASSWORDが維持されます</span></p>
  							</td>			
  						</tr>
  						<tr>
  							<th scope="col" ><label for="email">メールアドレス</label></th>
  							<td class="text-left">
  								<input type="text" name="email" id="email" class="w300" value="${upmember.email}">
  								<span id="emailmsg"></span>
  							</td>			
  						</tr>
  						<tr>
  							<th scope="col" ><label for="phone">電話番号</label></th>
  							<td class="text-left">
  								<input type="text" name="phone" id="phone" class="w300" value="${upmember.phone}">
  								<span id="phonemsg"></span>
  							</td>			
  						</tr>
  						<tr>
  							<th>プロフィール画像</th>
  							<td><input type="file" name="profileimg" accept="image/jpeg, image/png, image/jpg,  image/JPG" id="isFile" value="${upmember.profileimg}"></td>			
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
  					<input type="submit" class="btn submit" id="btn_ok" value="会員修正">
  					<input type="button" onclick="location.href='adminlist.do'" class="btn reset" value="リスト">
				</div>
  				</form>
  			</div>
  		</div>
  	</div>	
  </div>
  
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
								  $("#nickname").val("");
								  $("#nickname").focus();
							  	
							  }
						  }
					     
					  },
					  error : function(xhr,status,error) {
					      alert("통신에러")
					  },
					  
					});
			})
			
			
			$("#email").blur(function(){
				if(!$("#email").val()){
					$("#emailmsg").html("<span style='color:#f00;'>メールアドレスを入力してください</span>");
					
				}else{
					$("#emailmsg").html("");
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