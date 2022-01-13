<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../header.jsp" %>

  <div class="member-contents">
  	<div class="member-group">
  		<div class="member">
  			<h2 class="border-bottom-3px" style="padding-bottom:10px;">JOIN/会員登録</h2>
  			<div class="formtable">
  				<input type="checkbox" class="agreeAll" name="chkCtrl" id="chkCtrl" value="t" > Sketchbook利用約款にすべて同意します。
  				<p id="agreemsg" style="padding-bottom:30px;"></p>
  				<div class="list_con">
  					<textarea>第1条 本規約の範囲および変更
1. 本規約は、当社が会員に提供する本サービスの利用条件を定めるものであり、会員（第3条で定義します。）登録を希望する者（以下「会員登録希望者」といいます。）が会員登録を申請する場合および会員登録を受けた会員が本サービスを利用する場合を含む本サービスの一切の利用について適用され、会員および会員登録希望者を含む本サービスの一切の利用 者（以下「利用者」といいます。）に適用されるものとします。

2. 当社は、本サービスの追加・変更等を行う場合、関連する法令の改正等があった場合等の本規約を変更する必要がある場合には民法第548条の４に基づき本規約を変更することがあり、本規約を変更しようとする場合には、変更の 7 日以上前に本規約に定めるほかウェブサイト上での掲載または電子メール等の当社が適当と判断する方法で利用者に利用規約を変更すること及び変更の内容、変更の効力発生日について通知するものとします。本規約を変更した場合、効力発生日以降の本サービスの利用に関する一切の事項は、変更前の利 用者も含めて変更後の利用規約によるものとします。

3. 会員が未成年者である場合には、親権者等の法定代理人の同意を得て、本サービスを利用するものとします。

4. 当社は、本サービスの利用に関して、本規約とは別に個別規約（使用上の注意等）を定めることができます。これらの個別規約は、その名目の如何に関わらず、本規約の一部を構成するものとします。
                    </textarea>
                    <input type="checkbox" name="agree" id="agree1" value="t" onclick="check();">利用約款 同意
                    <p id="agreemsg1" style="padding-bottom:30px;"></p>
                </div>
                <div class="list_con">
                   <textarea>第4条 会員登録
1. 会員登録希望者は、本サービスの会員登録ページから当社の指定する方法に従い会員登録申請を行うものとします。

2. クレジットカードの登録を行う場合は、ご本人名義のクレジットカードでなければならないものとします。

3. 当社は第１項の申請に対し、承認する場合には、登録確認メールを送信することにより申請を承認するものとします。

4. 会員は、当社から不定期に配信される電子メール、カタログ等（メールマガジン、告知メール、お知らせメール、アプリプッシュ通知、電話、チラシ、カタログ、その他冊子等郵送によるご案 内の送付も含みますがこれらに限られません。）のご案内を受信または受領することを承諾 するものとします。

5. 当社は、以下の各号のいずれかに該当する場合、当該登録申請を承認しない場合があります。

(1) 会員登録希望者本人以外の者による会員登録申請の場合
(2) 会員登録希望者が過去に本規約、または当社もしくは当社グループ各社による商品売買もしくはサービスの提供に関する契約もしくは規約に違反したこと等により、会員登録の抹消、利用資格の取消し、その他解除されたことなどの処分をうけていることが判明した場合
(3) 会員登録希望者の申請内容に、虚偽または不正確な事項が含まれている場合
(4) 会員登録希望者が、過去に当社または当社グループ各社による商品売買またはサービスの提供に関して、料金等の支払債務の履行遅延、長期間にわたる商品の受取り不能、不適切な返品・交換の要求またはその他の債務不履行があったことが判明した場合
(5) その他登録申請を承認することが不適当であると当社が判断する場合
                    </textarea>
                    <input type="checkbox" name="agree" id="agree2" value="t" onclick="check();">個人情報の収集及び利用に同意
                    <p id="agreemsg2" style="padding-bottom:30px;"></p>
                </div>
                <div class="list_con">
                    <textarea>
                    1. 会員は、住所、氏名、電話番号、クレジットカード番号、その他当社に届出ている事項に変更 が生じた場合には、当社が別途指示する方法により、すみやかに当社に届出るものとします。

2. 当社は、会員が適宜、変更登録を行わなかったこと（以下「本変更登録の遅滞」といいます。）により当該会員に何らかの損害が生じたとしても、当社の故意または重大な過失による場合 を除き、一切責任を負わないものとします。また、当社は、本規約等に別途定める場合を除き、本変更登録の遅滞によって当該会員に生じた損害について、当社の責に基づく場合（当社の故意または重大な過失による場合を除きます。）、当該損害のうち、当該会員が被った通常かつ直接の範囲の損害（予見可能性の有無を問わず、特別損害および逸失利益を含まないものとします。）に限り責任を負うものとします。
                    </textarea>
                    <input type="checkbox" name="agree" id="agree3" value="t" onclick="check();">位置情報利用約款に同意
                    <p id="agreemsg3" style="padding-bottom:30px;"></p>
                </div>
  				
	  			<div class="pagination">
	  				<input type="button" class="btn submit btn_ok" id ="btn_ok" value="確認" >
	  				<input type="button" class="btn reset" value="取り消し" onclick="javascript:history.go(-1);">
				</div>
  			</div>
  		</div>
  		
  	</div>	
  </div>
  
<script>
	$(function(){
		
		var chkList =  $("input[name=agree]");
		
		$("#chkCtrl").click(function(){
			if($(this).is(":checked")){//전체동의 체크 상태이면
				chkList.prop("checked", true);//체크상태 확인 함수
			}else{
				chkList.prop("checked", false);
			}
		})
		
		$("#agree1, #agree2, #agree3").on("click", function(){
				if($("#agree1").is(":checked") == true && $("#agree2").is(":checked") == true && $("#agree3").is(":checked") == true){
					$("#chkCtrl").prop("checked", true);
				}else{
					$("#chkCtrl").prop("checked", false);
				}
			})
			 
		//btn_ok
		$("#btn_ok").on("click", function(){
			var chk = true;
			
			for(var i=0; i<chkList.length; i++){
				if(!chkList[i].checked){
					chk = false;
				}
			}
			
			if(!chk){
				alert("모든 약관에 동의해 주세요");
				return false;
			}else{
				location.href='member.do';
			}
		})
		
	});
</script>

<%@ include file = "../footer.jsp" %>