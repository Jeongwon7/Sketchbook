<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sketch Your Daily Life</title>
<link href="images/faviconsu.ico" rel="shortcut icon">
<script src="js/jquery-3.6.0.min.js"></script>

	
<script>
	//$(document).ready(function(){
	$(function(){
/*		
		$(".navi > ul > li").hover(function(){//네비 밑에 유엘 밑에 엘아이에 마우스가 올라갔을 때 function 실행(선택자+함수명)
			$(".navi > ul > li > .sub-2depth").stop().slideDown(200);//0.2초 서브2딥스에서 애니메이션이 실행되고있으면 정지하고 슬라이드다운
			},function(){//,:그렇지 않으면
				$(".navi > ul > li > .sub-2depth").stop().slideUp(300);
			
		})
		*/
		$(".navi > ul > li").hover(function(){
			
			$(".navi > ul > li").removeClass("active");
			$(this).addClass("active");
			$(this).children(".sub-2depth").stop().slideDown(200);
			},function(){//,:그렇지 않으면
				$(".navi > ul > li").removeClass("active");
				$(this).children(".sub-2depth").slideUp(300);
			
		})
		
	})//제이쿼리 시작하는 문장 $(document):선택자(html문서)가 로딩이 되어 레디되면 함수 실행{}안에!-->
</script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
	<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
	
<script>
    $(document).ready(function(){
      $('.slider').bxSlider({
      	mode:'fade',
      	auto:true,
      	pause:3000
 		});
    });
</script>	

<script>
    $(document).ready(function(){
      $('.list').bxSlider({
      
    });
</script>	


	<link href="css/font-awesome.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet"><!-- bx슬라이더 css 다음에 실행되도록 뒤로 보내기 -->
	<link href="css/main.css" rel="stylesheet">
	<link href="css/sub.css" rel="stylesheet">
	
	<!-- 슬릭 slick css,js 연결-->
	<link rel="stylesheet" type="text/css" href="css/slick.css"/>
	<script type="text/javascript" src="js/slick.min.js"></script>
</head>
<body>
	<header class="header">
		<div class="gnb">
			<h1 class="logo"><a href="main.do"><img src="images/logo.png" alt="sketchbook" class="logoimg"></a></h1>
			<h1 class="logotxt"><a href="main.do">&nbsp;Sketchbook</a></h1>
			<nav class="navi">
				<ul>
					<li><a href="myblogwrite.do">POSTING</a></li>
					<li><a href="guestbook.do">GUEST BOOK</a></li>
					<li><a href="myblog.do">MYBLOG</a></li>
					<li><a href="main.do">LOGOUT</a></li>
					<li style="width:84px;">
					<a href="https://www.facebook.com/moonbyun1"><i class="fa fa-facebook" ></i></a>
					</li>
					<li style="width:84px;">
					<a href="https://www.instagram.com/dlwlrma/?hl=ko"><i class="fa fa-instagram" ></i></a>	
					</li>
				</ul>
			</nav>
		</div>	
	</header>
    