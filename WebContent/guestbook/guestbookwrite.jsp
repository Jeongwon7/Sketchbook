<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../header.jsp" %>
	
	  <div class="content">
  	<div class="group">
  		<div class="sub-left">
  			<h2>WRITING</h2>
  			<div class="formtable"><!-- enctype="multipart/form-data":첨부파일 있을 때 꼭 적어주기 -->
  			<form name="guestbook" method="post" enctype="multipart/form-data" action="guestbookwritepro.do" onsubmit="return check()">
  				<table>
  					<colgroup>
  						<col width="12%">
  						<col width="*">
  					</colgroup>
  					<tbody>
  						<tr>
  							<th scope="col" >書き手</th>
  							<td><input type="text" name="writer"></td>			
  						</tr>
  						
  						<tr>
  							<th scope="col" >タイトル</th>
  							<td><input type="text" name="title"></td>			
  						</tr>
  						<tr>
  							<th scope="col" >内容</th>
  							<td>
  								<textarea name="content" style="width:100%; height:300px;"></textarea>
  							</td>			
  						</tr>
  					  	<tr>
  							<th scope="col" >添付ファイル</th>
  							<td><input type="file" name="imgurl" accept="image/jpeg, image/png, image/jpg" id="isFile"></td>			
  						</tr>
  						
  					</tbody>
  				</table>
  				<div class="pagination">
  					<input type="submit" class="btn submit" value="保存">
  					<input type="reset" class="btn reset" value="書き直し">
  					<button type="button" class="btn btn-info" onclick="location.href='guestbook.do'">リスト</button>
				</div>
  				</form>
  			</div>
  		</div>
  	</div>	
  </div>

<script>
		function check() {
			if(guestbook.writer.value=="") {
				alert("書き手をご入力ください");
				guestbook.writer.focus();
				return false;
			}
			if(guestbook.title.value=="") {
				alert("タイトルをご入力ください");
				guestbook.title.focus();
				return false;
			}
			if(guestbook.content.value=="") {
				alert("内容をご入力ください");
				guestbook.content.focus();
				return false;
			}
			
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

