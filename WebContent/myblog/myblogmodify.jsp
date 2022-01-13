<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../header.jsp" %>
<c:if test="${empty userid }">
	<script>
		alert("ログインしてください");
		location.href="login.do";
	</script>
</c:if>	
	  <div class="content">
  	<div class="group">
  		<div class="sub-left">
  			<h2>書き直し</h2>
  			<div class="formtable"><!-- enctype="multipart/form-data":첨부파일 있을 때 꼭 적어주기 -->
  			<form name="myblog" method="post" enctype="multipart/form-data" action="myblogmodify.do?bno=${modify.bno}" onsubmit="return check()">
  				<input type="hidden" name="writer" value="${userid}">
  				<table>
  					<colgroup>
  						<col width="12%">
  						<col width="*">
  					</colgroup>
  					<tbody>
  						
  						<tr>
  							<th scope="col" >タイトル</th>
  							<td><input type="text" name="title" value="${modify.title }"></td>			
  						</tr>
  						<tr>
  							<th scope="col" >内容</th>
  							<td>
  								<textarea name="content"style="width:100%; height:300px;">${modify.content}</textarea>
  							</td>			
  						</tr>
  					<!--	<tr>
							<th scope="col">添付ファイル</th>
							<th class="filebox">
								 <label for="ex_file">アップロード</label> 
								<input type="file" name="imgurl" accept="image/jpeg, image/png, image/jpg" id="isFile">
							</th>
						</tr>-->
						<tr>
  							<th scope="col" >添付ファイル</th>
  							<td>
  								<input type="file" name="imgurl" accept="image/jpeg, image/png, image/jpg" id="isFile">
  								<span id="imgurlmsg">画像を修正しない場合、添付は不要です。</span>
  							</td>			
  						</tr>
  						
  					</tbody>
  				</table>
  				<div class="pagination">
  					<input type="submit" class="btn submit" value="これに直す">
  					<input type="reset" class="btn reset" value="書き直す">
  					<button type="button" class="btn btn-info" onclick="location.href='myblog.do?writer=${userid}'">リスト</button>
				</div>
  				</form>
  			</div>
  		</div>
  	</div>	
  </div>

	<script>
		function check() {
			
			if(myblog.title.value=="") {
				alert("タイトルを入力してください");
				myblog.title.focus();
				return false;
			}
			if(myblog.content.value=="") {
				alert("内容を入力してください");
				myblog.content.focus();
				return false;
			}
			
			var imgFile = $('#isFile').val();
			var fileForm = /(.*?)\.(JPG|jpg|jpeg|png|gif|bmp|pdf)$/;
			var maxSize = 20 * 1024 * 1024;
			var fileSize;

			if(imgFile != "" && imgFile != null) {
				fileSize = document.getElementById("isFile").files[0].size;
			    
			    if(!imgFile.match(fileForm)) {
			    	alert("画像ファイルのみアップロードしてください");
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

