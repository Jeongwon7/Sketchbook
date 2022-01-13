<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../header.jsp" %>

  <div class="member-contents">
  	<div class="member-group">
  		<div class="member" style="width:500px;">
  			<h2>ADMIN LOGIN</h2>
  			<div class="formtable"><!-- enctype="multipart/form-data":첨부파일 있을 때 꼭 적어주기 -->
  			<form name="login" id="admin" method="post" action="admin.do">
  				<table>
  					<colgroup>
  						<col width="*">
  						<col width="15%">
  					</colgroup>
  					<tbody>
  						<tr>
  							<td style="border-bottom:none; padding-bottom: 4px;">
  								<input type="text" name="id" id="id" placeholder="id">
  							</td>
  							<td rowspan="2">
  								<input type="submit" id ="btn_ok" onclick="return fn_login()" class="btn login" value="ログイン">		
  							</td>
  						</tr>
  						<tr>
  							<td style="padding-top:4px;">
  								<input type="password" name="pw"  id="pw" placeholder="password">
  							</td>
  						</tr>
  						
  						<tr>
  							<td colspan="3" style="text-align:center;">
  								<a href="" onclick="fn_develope()">IDを忘れた場合</a> / 
  								<a href="" onclick="fn_develope()">PASSWORDを忘れた場合</a>
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
			
			var msg = "${msg}";
			if(msg){
				alert("${msg}");//msg 값을 띄움
			}
			
			
		});
	</script>
 	<script type="text/javascript">
    
    function fn_login(){
    	if(admin.id.value==""){
    		alert("IDを入力してください");
    		login.id.focus();
    		return false;
    	}
    	if(admin.pw.value==""){
    		alert("PASSWORDを入力してください");
    		login.pw.focus();
    		return false;
    	}
    	
    	return true;
    }
    
    function fn_develope(){
    	alert("開発中");
    }
  </script> 
  

  <%@ include file="../footer.jsp" %>
  
