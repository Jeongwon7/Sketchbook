<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../header.jsp" %>
 <c:if test="${adminId ne 'admin'}">
	<script>
		alert("ID、PASSWORDを確認してください");
		location.href='admin.do';
	</script>
 </c:if>
	  <div class="content">
  	<div class="group">
  		<div class="sub-left">
  			<h2>FAQ MODIFY</h2>
  			<div class="formtable"><!-- enctype="multipart/form-data":첨부파일 있을 때 꼭 적어주기 -->
  			<form name="faq" method="post" action="faqmodify.do" onsubmit="return check()">
  			<input type="hidden" name="bno" value="${faqmodify.bno}">
  				<table>
  					<colgroup>
  						<col width="12%">
  						<col width="*">
  					</colgroup>
  					<tbody>
  						<tr>
  							<th scope="col" >質問</th>
  							<td><input type="text" name="fq" value="${faqmodify.fq}"></td>			
  						</tr>
  						<tr>
  							<th scope="col" >回答</th>
  							<td>
  								<textarea name="fa" style="width:100%; height:300px;">${faqmodify.fa}</textarea>
  							</td>			
  						</tr>
  					</tbody>
  				</table>
  				<div class="pagination">
  					<input type="submit" class="btn submit" value="保存">
  					<input type="button" onclick="location.href='faq.do'"class="btn reset" value="取り消す">
				</div>
  				</form>
  			</div>
  		</div>
  	</div>	
  </div>

<script>
		function check() {
			if(faq.fq.value=="") {
				alert("質問をご入力ください");
				guestbook.writer.focus();
				return false;
			}
			if(faq.fa.value=="") {
				alert("回答をご入力ください");
				guestbook.title.focus();
				return false;
			}
			return true;
		}
	</script>


<%@ include file="../footer.jsp" %>