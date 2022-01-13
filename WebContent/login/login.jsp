<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../header.jsp" %>

  <div class="member-contents">
  	<div class="member-group">
  		<div class="member" style="width:500px;">
  			<h2>LOGIN/ログイン</h2>
  			<div class="formtable"><!-- enctype="multipart/form-data":첨부파일 있을 때 꼭 적어주기 -->
  			<form name="login" id="login" method="post">
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
  								<input type="button" id ="btn_ok" onclick="javascript:fn_login();" class="btn login" value="ログイン">		
  							</td>	
  						</tr>
  						<tr>
  							
  							<td style="padding-top:4px;">
  								<input type="password" name="pw"  id="pw" placeholder="password">
  							</td>
  						</tr>
  						
  						<tr>
  							<td colspan="3" style="text-align:center;">
  								<a href="memberterms.do">会員登録</a> /
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
 	<script type="text/javascript">
    
    function fn_login(){
    	if(login.id.value==""){
    		alert("IDを入力してください");
    		login.id.focus();
    		return false;
    	}
    	if(login.pw.value==""){
    		alert("PASSWORDを入力してください");
    		login.pw.focus();
    		return false;
    	}
    	
    	var form = document.login;
    	form.method='post';
    	form.action='login.do';
    	form.submit();
    	
    }
    
	var msg = "${msg}";
	
	if(msg){
		alert("${msg}");//msg 값을 띄움
	}
    
    function fn_develope(){
    	alert("開発中");
    }
  </script> 
  

  <%@ include file="../footer.jsp" %>
  
