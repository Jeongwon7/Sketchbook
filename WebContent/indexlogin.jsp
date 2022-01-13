<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<h1 class="logo"><a href="index.jsp"><img src="images/logo.png" alt="sketchbook" class="logoimg"></a></h1>
			<h1 class="logotxt"><a href="index.jsp">&nbsp;Sketchbook</a></h1>
			<nav class="navi">
				<ul>
					<li><a href="myblog/myblogwrite.jsp">POSTING</a></li>
					<li><a href="myblog/myblog.jsp">MYBLOG</a></li>
					<li><a href="">LOGOUT</a></li>
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

	<div class="slider">
    	<div class="images">
    		<img src="images/slide03.JPG">
    		<div class="text">
    			<h1>Sketch Your Daily Life!</h1>
    		</div>
    	</div>
    	<div class="images">
    		<img src="images/slide02.JPG">
    		<div class="text">
    			<h1>Sketch Your Daily Life!</h1>
    		</div>
    	</div>
    	<div class="images">
    		<img src="images/slide01.JPG">
    		<div class="text">
    			<h1>Sketch Your Daily Life!</h1>
    		</div>
    	</div>
 	</div>
 	

<div class="contents">
  		<div class="album-list">
  			<h2>Popular Posts</h2>
  				<div class="album">
  				<div class="your-class">
  				<div class="list">
  					<img src="images/banner01.JPG">
  						<div class="list-txt">
  						<a href="myblog/myblogview.jsp">
  							<h3>幸せな一日</h3>
	  						<p>海抜150mの青空の下に広がる済州畑の塀、済州市涯月邑手形1里のコンセミ畑…</p>
	  						<span>by スヨン</span>
	  					</a>
	  					</div>
	  				
  				</div>
  				<div class="list">
  					<img src="images/banner02.JPG">
  						<div class="list-txt">
  							<a href="myblog/myblogview.jsp">
  							<h3>猫のえさは健康まで考えて</h3>
	  						<p>猫を飼っている方々なら小さいことまで気を使わなければならないようです...</p>
	  						<span>by にゃにゃん券 </span>
	  					</a>
  						</div>
  					</div>
  				<div class="list">
  					<img src="images/banner03.JPG">
  						<div class="list-txt">
  							<a href="myblog/myblogview.jsp">
  							<h3>紫アイスクリーム</h3>
	  						<p>再び訪れたブランチグルメ店、そして手作りアイスクリーム、この前知り合いと一緒に...</p>
	  						<span>by アイス星人</span>
	  					</a>
  						</div>
  				</div>
  				<div class="list">
  					<img src="images/iruka.jpg">
  					<div class="list-txt">
  							<a href="myblog/myblogview.jsp">
  							<h3>美ら海のおきちゃん</h3>
	  						<p>この前沖縄美ら海水族館でお伺いした後記を残してみようと思います…</p>
	  						<span>by freshB</span>
	  					</a>
  						</div>
  				</div>
  				<div class="list">
  					<img src="images/kujira.jpg">
  					<div class="list-txt">
  							<a href="myblog/myblogview.jsp">
  							<h3>ジンベエザメツアー</h3>
	  						<p>楽しかったジンベエザメツアー、日本で前もって予約をしてから去りました…</p>
	  						<span>by ZHB </span>
	  					</a>
  						</div>
  				</div>
  				<div class="list">
  					<img src="images/cafe.jpg">
  					<div class="list-txt">
  							<a href="myblog/myblogview.jsp">
  							<h3>2021-10-11 日常</h3>
	  						<p>家のWi-Fiの調子がおかしくなったせいで、今日は仕方なくカフェで勉強…</p>
	  						<span>by JW</span>
	  					</a>
  						</div>
  				</div>
  			</div>
  		</div>
  	</div>
  </div>
  <div class="contents">
  		<div class="latestpost">
  			<div class="latest"><!-- 1240 -->
  				<h2>Latest Posts</h2>
  					<div class="post">
  						<div class="post-left">
  							<img src="images/banner01.JPG">
  						</div>
  						<div class="post-right">
  							<a href="">
  							<h3>沖縄南部の喫茶店、浜辺の茶屋</h3>
								<p>沖縄旅行、沖縄南部のカフェ浜部の茶屋
									沖縄戦争の中で最も被害を受けた沖縄南部、 
									半世紀が過ぎ、戦争の跡は波に流され、美しい風景だけが残っています。
									まだ開発されていないため閑静さと素朴さがいっぱいで余裕のある沖縄でも
									 一番余裕のあるここで時間を過ごしました…</p>
								<span>2021-10-05</span>&nbsp;&nbsp;&nbsp;
								<span><i class="fa fa-heart"></i>230</span>&nbsp;&nbsp;&nbsp;
								<span><i class="fa fa-comment"></i>20</span>	
  							</a>
  						</div>
  					</div>
  					<div class="post">
  						<div class="post-left">
  							<img src="images/banner02.JPG">
  						</div>
  						<div class="post-right">
  							<a href="">
  							<h3>ノルウェイの森の猫</h3>
								<p>ノルウェーの森猫は自然発生種から始まり、品種改良過程を経て今日に至った。 
								ノルウェーの森猫は厳然と品種改良を経た品種苗なので、ノルウェーにいる一般猫たちとは違う。
								 ノルウェー原産の猫で寒さによく適応した品種。 長い毛が、体全体を覆っていて···。</p>
								<span>2021-10-05</span>&nbsp;&nbsp;&nbsp;
								<span><i class="fa fa-heart"></i>230</span>&nbsp;&nbsp;&nbsp;
								<span><i class="fa fa-comment"></i>20</span>	
  							</a>
  						</div>
  					</div>
  					<div class="post">
  						<div class="post-left">
  							<img src="images/banner03.JPG">
  						</div>
  						<div class="post-right">
  							<a href="">
  							<h3>アイスクリーム</h3>
								<p>アイスクリーム(英語:ice cream)は冷たいデザートで、 
								普通クリームに香辛料と泡を立てた白身を入れて凍らせたものである。 
								チョコレート味、イチゴ、味付け、バニラ味、緑茶味などいろいろな味がある。 
								ちなみに、ice creamはiced creamまたはcream iceから派生した単語だ。</p>
								<span>2021-10-05</span>&nbsp;&nbsp;&nbsp;
								<span><i class="fa fa-heart"></i>230</span>&nbsp;&nbsp;&nbsp;
								<span><i class="fa fa-comment"></i>20</span>	
  							</a>
  						</div>
  					</div>
  					<div class="post">
  						<div class="post-left">
  							<img src="images/slide01.JPG">
  						</div>
  						<div class="post-right">
  							<a href="">
  								<h3>カクテル(cocktail)</h3>
								<p>カクテルは、お酒と数種類の飲み物、添加物などを混ぜて作った混合酒のこと。 
								但し、ノンアルコールカクテルもあり、これらは「ネックテール」と呼ぶ。 
								人の好みや好みに合わせて独特な味や色を出すことができます。
								人の好みや好みに合わせて独特な味や色を出すことができます。</p>
								<span>2021-10-05</span>&nbsp;&nbsp;&nbsp;
								<span><i class="fa fa-heart"></i>230</span>&nbsp;&nbsp;&nbsp;
								<span><i class="fa fa-comment"></i>20</span>	
  							</a>
  						</div>
  					</div>
  			</div>
  		</div>
  	</div>
  		
  	
  	
  	<script type="text/javascript">
    $(document).ready(function(){
      $('.your-class').slick({
    	  infinite: true,
    	  slidesToShow: 3,
    	  slidesToScroll: 1,
    	  
      });
    })
  </script>
  <div class="footer">
  	<p>Copyright © Sketch Your Daily Life. All rights reserved.</p>
  </div>
  
  

<script>
// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();
</script>
<div id="id01" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'"
class="close" title="Close Modal">&times;</span>

  <!-- Modal Content -->
  <form class="modal-content animate" action="/action_page.php">
    <div class="imgcontainer">
      <h3>LOGIN</h3>
    </div>

    <div class="container">
      <label for="uname"><b>Username</b></label>
      <input type="text" placeholder="Enter Username" name="uname" required>

      <label for="psw"><b>Password</b></label>
      <input type="password" placeholder="Enter Password" name="psw" required>
        
      <button type="submit">Login</button>
      <label>
        <input type="checkbox" checked="checked" name="remember"> Remember me
      </label>
    </div>

  </form>
</div>

<script>
// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>
