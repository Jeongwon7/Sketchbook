<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../header.jsp" %>

	<div class="group">
	<div class="sub-contents">
		<h2><a href="qna.do">お問い合わせ</a></h2>
		<div class="contents-lefts" style="width:1000px; margin:0 auto;">
			<div class="col-md-9">
				<div class="writer-info">
					<h2 style="padding-bottom:30px;" >${view.title }</h2>
					<div class="col-md-12">
						<a href="myblog.do?writer=${view.writer}"> <span>${view.nickname }&nbsp;&nbsp;&nbsp;|</span></a>&nbsp;&nbsp;&nbsp;
						<span>
						${fn:substring(view.regdate,0,10) }&nbsp;&nbsp;&nbsp;|
						</span>&nbsp;&nbsp;&nbsp;
						<span><i class="fa fa-eye"></i>&nbsp;${view.viewcount} </span>&nbsp;&nbsp;&nbsp;
					<!--<span><i class="fa fa-heart"></i>2</span>&nbsp;&nbsp;&nbsp;
						<span><i class="fa fa-comment"></i>0</span>
					 -->
					</div>
				</div>
					<div class="board-body text-center">
						<c:if test="${view.imgurl != null }">
							<img src="${pageContext.request.contextPath}/upload/${view.imgurl}" alt="" class="board-pic" >
						</c:if>
						<div class="text-info">
							<p style="text-align:left">
							${view.qcontent}
							</p>
						</div>
					</div>
			<div class="prev_next">
			<c:if test="${prev != null}"><!-- 이전글이 있으면 -->
				<a href="qnaview.do?qbno=${prev.qbno }" class="btn_prev">
					<i class="fa fa-angle-left"></i>
					<span class="prev_wrap">
						<strong>previous</strong>
						<br>
						<span>${fn:substring(prev.title,0,10)}...</span>
					</span>
				</a>
			</c:if>
			<c:choose>
				<c:when test="${view.writer eq userid or not empty adminId}">
					<div class="btn_3wrap">
						<a href="qnamodifypro.do?qbno=${view.qbno }" class="btn btn-primary" >修正</a>
						<a href="qnadelete.do?qbno=${view.qbno }" class="btn btn-success" onclick="return confirm('本当に削除しますか?')">削除</a>
						<a href="qna.do" class="btn btn-info" >リスト</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="btn_3wrap">
					<a href="qna.do" class="btn btn-info" >リスト</a>
					</div>
				</c:otherwise>
			</c:choose>
			<c:if test="${next != null }">
				<a href="qnaview.do?qbno=${next.qbno }" class="btn_next">
					<span class="next_wrap">
						<strong>next</strong>
						<br>
						<span>${fn:substring(next.title,0,10)}...</span>
					</span>
					<i class="fa fa-angle-right"></i>
				</a>
			</c:if>
			</div>
					<div class="containaer">
			      <div class="cmt-container">
			         <div class="cmtCount" id="Cmtcount">Answers: <span>${count}</span></div>
			         <div class="cmt-box">
			            <textarea class="cmt-area" id="placeholder" rows="4" cols="" placeholder="回答を入力してください"></textarea>
			         </div>
			         <c:choose>
 						<c:when test ="${not empty adminId}">
					        <div class="cmt-buttons">
					           <button type="button" id="btnCmt" class="info cmt-insert" onclick="cmtWrite();">回答登録</button>
					        </div>
					    </c:when>
					    <c:otherwise>
					    	 <div class="cmt-buttons">
					           <button type="button" id="btnCmt" class="info cmt-insert" onclick="alert('管理者のみ登録可能です');">回答登録</button>
					        </div>
					    </c:otherwise>
					 </c:choose>
			         <div  class="cmtList-box">
			            <ul id="addcmt">
			            <c:forEach var = "list" items = "${cmtList }">
			               <li class="reply">
			                 <span><img src="${pageContext.request.contextPath}/upload/${list.profileimg}" alt="" class="cmt-icon thumb_profile"></span>  
				                  <span>
				                     <span class="cmtWriter" id="CmtId">${list.nickname}</span>
				                     <span class="cmtDate">${list.wdate }</span>
				                     <span class="cmtContent" id="${list.num}">${list.answertext }</span>
				                       <c:if test="${not empty adminId}">
			        				     <button class="Cmtdelete Cmtbtn" onclick="cmtDeleteOpen(${list.num})">削除 </button>
			       						 <button class="Cmtmodify Cmtbtn" onclick="qnaModifyOpen(${list.num})">修正</button>
			        			 	   </c:if>
				                  </span>
			               </li>
			            </c:forEach>
			            </ul>
			         </div>
			        </div>
			   </div>
				</div>
		</div><!-- left end -->
		
	</div>
	</div>

<script>
	function cmtWrite(){
		var qbno = "${view.qbno}";
		var cmtContent =$(".cmt-area").val();
		var writer = "${sessionScope.adminId}";
		
		if(cmtContent == ""){
			alert("回答を入力してください");
			return false;
		}
		
		if(writer == ""){
			alert("ログインしてください");
			return false;
		}
		
		var cdData = {//제이슨 형식 데이터 ☆★
				qbno:qbno,
				writer:writer,
				content:cmtContent
		}
		
		$.ajax({
			type:'post',
			url:'answer.do',
			data:cdData,
			success: function(result){
				if(result >= 1){
					$("#Cmtcount span").html(result);
					getCmtList();//자바스크립트에서 함수 호출
					var placeholder = document.getElementById("placeholder");
					placeholder.value = null;
				}else{
					alert("댓글 저장 실패");
					return false;
				}
			}
		})
		
	}
	
	function getCmtList(){
		var output = "";
		var logId = "${sessionScope.adminId}";
		var qbno = "${view.qbno}";
		var cdData= {
				qbno:qbno,
		}
		
		$.ajax({
			type:'get',
			url:'answer.do',
			data:cdData,//보내는 데이터 타입
			dataType:"json",//받는 데이터 타입
			success:function(result){
				for(var i in result){//향상된 for문
					 output += '<ul id="addcmt">';
		             output += '<li class="reply">';
		             output += '<span><img src=" ${pageContext.request.contextPath}/upload/'+result[i].profileimg+'" alt="" class="cmt-icon thumb_profile"></span>';
		             output += '<span>';
		             output += ' <span class="cmtWriter">'+result[i].nickname+'</span>';
		             output += '<span class="cmtDate">'+result[i].wdate+'</span>';
		             output += '<span class="cmtContent" id="'+result[i].num+'">'+result[i].answertext+'</span>';
		             if(result[i].id == logId){
			             output += ' <button class="Cmtdelete Cmtbtn" onclick="cmtDeleteOpen('+result[i].num+')">削除</button>';
		            	 output += ' <button class="Cmtmodify Cmtbtn" onclick="qnaModifyOpen('+result[i].num+')">修正</button>';
		             }
		             output += ' </span>';
		             output += ' </li>';
		             output += '</ul>';
				}
				$(".cmtList-box").html(output);
			}
		})//ajax
		
	}

	</script>
	
<script>
	function cmtDeleteOpen(num){
		var msg = confirm("本当に削除しますか");
		if(msg == true){
			deleteCmt(num);
		}
	}

	function deleteCmt(num){
		var qbno = "${view.qbno}";
		 $.ajax({
             
             url: "answerdelete.do",
             type: "POST",
             data:{num : num,
            	 qbno : qbno},
             
             success:function(result){
                 alert("削除しました");
                 $("#Cmtcount span").html(result);
                 getCmtList();
             },
             error:function(error){
                 console.log(error);
             }
             
         });
	}
</script>
<script>
	function qnaModifyOpen(num){
		
		$("#"+num).contents().unwrap().wrap( '<textarea class="cmtContent" id="'+num+'"></textarea>' );
		
		$("#"+num).siblings(".Cmtdelete").removeAttr("onclick");
		$("#"+num).siblings(".Cmtdelete").attr("onclick", "cmtModify("+num+")");
		$("#"+num).siblings(".Cmtdelete").html("これに直す");
		
		$("#"+num).siblings(".Cmtmodify").removeAttr("onclick");
		$("#"+num).siblings(".Cmtmodify").attr("onclick", "getCmtList()");
		$("#"+num).siblings(".Cmtmodify").html("取り消し");
		
		}
	
</script>
<script>
	
	function cmtModify(num){
		if($("#"+num).val() == ""){
			alert("コメントを入力してください");
			return false;
		}
		
		var answertext = $("#"+num).val();
		
		var mdData ={num : num,
				     answertext : answertext
					}

		$.ajax({
			type:'post',
			url:'answermodify.do',
			data:mdData,
			success: function(result){
				
				getCmtList();
				
			},error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		       }

		});
		
	}
</script>
	

<%@ include file="../footer.jsp" %>