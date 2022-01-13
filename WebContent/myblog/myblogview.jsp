<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../header.jsp" %>

	<div class="group">
	<div class="sub-contents">
		<h2><a href="myblog.do?writer=${view.writer}">${view.nickname}'s Blog</a></h2>
		<div class="contents-lefts" style="width:1000px; margin:0 auto;">
			<div class="col-md-9">
				<div class="writer-info">
					<h2 style="padding-bottom:30px;" >${view.title }</h2>
					<div class="col-md-12">
						<span>${fn:substring(view.regdate,0,10)}&nbsp;&nbsp;&nbsp;|</span>&nbsp;&nbsp;
						<span><i class="fa fa-eye"></i>&nbsp;${view.viewcount} </span>&nbsp;&nbsp;|&nbsp;&nbsp;
						<span>by.${view.nickname}</span>
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
							${view.content}
							</p>
						</div>
					</div>
			<div class="prev_next">
			<c:if test="${prev != null}"><!-- 이전글이 있으면 -->
				<a href="myblogview.do?bno=${prev.bno}&writer=${prev.writer}" class="btn_prev">
					<i class="fa fa-angle-left"></i>
					<span class="prev_wrap">
						<strong>previous</strong>
						<br>
						<span>${fn:substring(prev.title,0,12)}...</span>
					</span>
				</a>
			</c:if>
			<c:choose>
				<c:when test="${view.writer eq userid}">
					<div class="btn_3wrap">
						<a href="myblogmodify.do?bno=${view.bno}" class="btn btn-primary" >修正</a>
						<a href="myblogdelete.do?bno=${view.bno}&writer=${view.writer}"class="btn btn-success" onclick="return confirm('本当に削除しますか?')">削除</a>
						<a href="myblog.do?writer=${view.writer}" class="btn btn-info" >リスト</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="btn_3wrap">
						<a href="myblog.do?writer=${view.writer}" class="btn btn-info" >リスト</a>
					</div>
				</c:otherwise>
			</c:choose>
				
			<c:if test="${next != null }">
				<a href="myblogview.do?bno=${next.bno}&writer=${next.writer}" class="btn_next">
					<span class="next_wrap">
						<strong>next</strong>
						<br>
						<span>${fn:substring(next.title,0,12)}...</span>
					</span>
					<i class="fa fa-angle-right"></i>
				</a>
			</c:if>
			</div>
					
			</div>
			<div class="containaer">
			      <div class="cmt-container">
			         <div class="cmtCount" id="Cmtcount">Comments: <span>${count}</span></div>
			         <div class="cmt-box">
			            <textarea class="cmt-area" id="placeholder" rows="4" cols="" placeholder="コメントを入力してください"></textarea>
			         </div>
			         <div class="cmt-buttons">
			         	<c:if test="${empty userid }">
				       		<button type="button" id="btnCmt" class="info cmt-insert" onclick="cmtWriteOpen();">コメント登録</button>
				        </c:if>
				        <c:if test="${not empty userid }">
				            <button type="button" id="btnCmt" class="info cmt-insert" onclick="cmtWrite();">コメント登録</button>
			         	</c:if>
			         </div>
			         <div  class="cmtList-box">
			            <ul id="addcmt">
			            <c:forEach var = "list" items = "${cmtList }">
			               <li class="reply">
			                  <a href="myblog.do?writer=${list.id}"><span><img src="${pageContext.request.contextPath}/upload/${list.profileimg}" alt="" class="cmt-icon thumb_profile"></span></a>
				                  <span>
				                     <a href="myblog.do?writer=${list.id}"><span class="cmtWriter" id="CmtId">${list.nickname }</span></a>
				                     <span class="cmtDate">${list.wdate }</span>
				                     <span class="cmtContent" id="${list.num}">${list.replytext}</span>
				                       <c:if test="${list.id eq userid }">
			        				     <button class="Cmtdelete Cmtbtn" onclick="cmtDeleteOpen(${list.num})">削除 </button>
			       						 <button class="Cmtmodify Cmtbtn" onclick="cmtModifyOpen(${list.num})">修正</button>
			        			 	   </c:if>
				                  </span>
			               </li>
			            </c:forEach>
			            </ul>
			         </div>
			        </div><!--cmt-container end-->
			   </div>
		  </div>
	  </div>
</div>

<script>
	function cmtModifyOpen(num){
		
		$("#"+num).contents().unwrap().wrap( '<textarea class="cmtContent" id="'+num+'"></textarea>' );
		
		$("#"+num).siblings(".Cmtdelete").removeAttr("onclick");
		$("#"+num).siblings(".Cmtdelete").attr("onclick", "cmtModify("+num+")");
		$("#"+num).siblings(".Cmtdelete").html("これに直す");
		
		$("#"+num).siblings(".Cmtmodify").removeAttr("onclick");
		$("#"+num).siblings(".Cmtmodify").attr("onclick", "getCmtList()");
		$("#"+num).siblings(".Cmtmodify").html("取り消し");
		
		
		}
	
	function cmtModify(num){
		if($("#"+num).val() == ""){
			alert("コメントを入力してください");
			return false;
		}
		
		var replytext = $("#"+num).val();
		
		var mdData ={num : num,
					replytext : replytext
					}

		$.ajax({
			type:'post',
			url:'myblogcmtmodify.do',
			data:mdData,
			success: function(result){
				
				getCmtList();
				
			},error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		       }

		});
		
	}
</script>

<script>
	function cmtWriteOpen(){
		alert("ログインが必要な機能です");
		return false;
	}
	
	function cmtWrite(){
		
		var bno = "${view.bno}";
		var cmtContent =$(".cmt-area").val();
		var writer = "${sessionScope.userid}";
		
		if(cmtContent == ""){
			alert("コメントを入力してください");
			return false;
		}
		
		if(writer == ""){
			alert("ログインしてください");
			return false;
		}
		
		var cdData = {//제이슨 형식 데이터 ☆★
				bno:bno,
				writer:writer,
				content:cmtContent
		}
		
		$.ajax({
			type:'post',
			url:'myblogCmt.do',
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
		var logId = "${sessionScope.userid}";
		var bno = "${view.bno}";
		var cdData= {
				bno:bno,
		}
		
		$.ajax({
			type:'get',
			url:'myblogCmt.do',
			data:cdData,//보내는 데이터 타입
			dataType:"json",//받는 데이터 타입
			success:function(result){
				for(var i in result){//향상된 for문
					 output += '<ul id="addcmt">';
		             output += '<li class="reply">';
		             output += '<a href="myblog.do?writer='+result[i].id+'"><span><img src=" ${pageContext.request.contextPath}/upload/'+result[i].profileimg+'" alt="" class="cmt-icon thumb_profile"></span></a>';
		             output += '<span>';
		             output += '<a href="myblog.do?writer='+result[i].id+'"><span class="cmtWriter">'+result[i].nickname+'</span></a>';
		             output += '<span class="cmtDate">'+result[i].wdate+'</span>';
		             output += '<span class="cmtContent" id="'+result[i].num+'">'+result[i].replytext+'</span>';
		             if(result[i].id == logId){
			             output += ' <button class="Cmtdelete Cmtbtn" onclick="cmtDeleteOpen('+result[i].num+')">削除</button>';
		            	 output += ' <button class="Cmtmodify Cmtbtn" onclick="cmtModifyOpen('+result[i].num+')">修正</button>';
		            	// output += '<button style="float:right;" onclick="deleteCmt('+result[i].bno+')"></button>';
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
		console.log(num);
		var msg = confirm("本当に削除しますか");
		if(msg == true){
			deleteCmt(num);
		}
	}

	function deleteCmt(num){
		var bno = "${view.bno}";
		 $.ajax({
             
             url: "myblogCmtdelete.do",
             type: "POST",
             data:{num : num,
            	 bno : bno},
             
             success:function(result){
            	 $("#Cmtcount span").html(result);
                 alert("削除しました");
                 getCmtList();
             },
             error:function(error){
                 console.log(error);
             }
             
         });
	}
</script>





  
  <%@ include file="../footer.jsp" %>
