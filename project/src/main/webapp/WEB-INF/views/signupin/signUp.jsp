<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	.signUpForm{
		margin-top:100px;
	}
</style>
<script>
	function characterCheck(obj){
	var regExp = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi; 
	
	if( regExp.test(obj.value) ){
		alert("특수문자는 입력불가");
		obj.value = obj.value.substring( 0 , obj.value.length - 1 );
		}
	}

	function spaceCheck(obj){
		var regExp = /\s/gi; 
		
		if( regExp.test(obj.value) ){
			alert("공백 입력불가");
			obj.value = obj.value.substring( 0 , obj.value.length - 1 );
			}
		}
	
	function mailCheck(obj){
		var regExp = /[ \{\}\[\]\/?,;:|\)*~`!^\-_+┼<>\#$%&\'\"\\\(\=]/gi; 
		
		if( regExp.test(obj.value) ){
			alert("특수문자는 입력불가(@ . 만 허용)");
			obj.value = obj.value.substring( 0 , obj.value.length - 1 );
			}
		}

</script>
<body>
	
    <div id="conWrap">
    	<div class="main_signup">
			<form name="frmSignUp" method="post" action="/project/signUp.do" encType="UTF-8">
			
			<section class="agreement_wrap">
				<div class="logo">
				</div>
				<br><br>
			
                    <div class="agreement">
                        <textarea name="message" rows="10" cols="60" disabled>여러분을 환영합니다.
Null study 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 서비스의 이용과 관련하여 이를 이용하는 Null study 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
서비스를 이용하시거나 서비스 회원으로 가입하실 경우 여러분은 본 약관 및 관련 운영 정책을 확인하거나 동의하게 되므로, 잠시 시간을 내시어 주의 깊게 살펴봐 주시기 바랍니다.
회원 가입 시 이름, 생년월일, 휴대전화번호 등의 정보를 허위로 기재해서는 안 됩니다. 회원 계정에 등록된 정보는 항상 정확한 최신 정보가 유지될 수 있도록 관리해 주세요. 자신의 계정을 다른 사람에게 판매, 양도, 대여 또는 담보로 제공하거나 다른 사람에게 그 사용을 허락해서는 안 됩니다. 아울러 자신의 계정이 아닌 타인의 계정을 무단으로 사용해서는 안 됩니다. 이에 관한 상세한 내용은 계정 운영정책을 참고해 주시기 바랍니다.
타인에 대해 직접적이고 명백한 신체적 위협을 가하는 내용의 게시물, 타인의 자해 행위 또는 자살을 부추기거나 권장하는 내용의 게시물, 타인의 신상정보, 사생활 등 비공개 개인정보를 드러내는 내용의 게시물, 타인을 지속적으로 따돌리거나 괴롭히는 내용의 게시물, 성매매를 제안, 알선, 유인 또는 강요하는 내용의 게시물, 공공 안전에 대해 직접적이고 심각한 위협을 가하는 내용의 게시물은 제한될 수 있습니다.
관련 법령상 금지되거나 형사처벌의 대상이 되는 행위를 수행하거나 이를 교사 또는 방조하는 등의 범죄 관련 직접적인 위험이 확인된 게시물, 관련 법령에서 홍보, 광고, 판매 등을 금지하고 있는 물건 또는 서비스를 홍보, 광고, 판매하는 내용의 게시물, 타인의 지식재산권 등을 침해하거나 모욕, 사생활 침해 또는 명예훼손 등 타인의 권리를 침해하는 내용이 확인된 게시물은 제한될 수 있습니다.
자극적이고 노골적인 성행위를 묘사하는 등 타인에게 성적 수치심을 유발시키거나 왜곡된 성 의식 등을 야기할 수 있는 내용의 게시물, 타인에게 잔혹감 또는 혐오감을 일으킬 수 있는 폭력적이고 자극적인 내용의 게시물, 본인 이외의 자를 사칭하거나 허위사실을 주장하는 등 타인을 기만하는 내용의 게시물, 과도한 욕설, 비속어 등을 계속하여 반복적으로 사용하여 심한 혐오감 또는 불쾌감을 일으키는 내용의 게시물은 제한될 수 있습니다.
자동화된 수단을 활용하는 등 서비스의 기능을 비정상적으로 이용하여 게재된 게시물, 각 개별 서비스의 제공 취지와 부합하지 않는 내용의 게시물은 다른 이용자들의 정상적인 서비스 이용에 불편을 초래하고 더 나아가  원활한 서비스 제공을 방해하므로 역시 제한될 수 있습니다. 기타 제한되는 게시물에 관한 상세한 내용은 게시물 운영정책 및 각 개별 서비스에서의 약관, 운영정책 등을 참고해 주시기 바랍니다.</textarea><br>
                        <ul>
                            <li><input type="checkbox" name="chk1" required></li>
                            <li>스터디 이용약관동의</li>
                        </ul>
                    </div>
            </section>
	
            <section class="sign_wrap">
	            <div>
	            <p class="title">아이디</p>
	            <input type="text" class="s_inputs" name="id" onkeyup="characterCheck(this)" onkeydown="characterCheck(this)" required></input><br>
	            </div>
	            
	            <div>
	            <p class="title">비밀번호</p>
	            <input type="password" class="s_inputs" name="pw" onkeyup="spaceCheck(this)" onkeydown="spaceCheck(this)" required></input>
	            </div>
	            
	            <div>
	            <p class="title">비밀번호 재확인</p>
	            <input type="password" class="s_inputs" name="re_pw" onkeyup="spaceCheck(this)" onkeydown="spaceCheck(this)" required></input>
	            </div>
	            
	            <div>
	            <p class="title">이름</p>
	            <input type="text" class="s_inputs" name="name" onkeyup="characterCheck(this)" onkeydown="characterCheck(this)" required></input>
	            </div>
	            
	            <div>
	            <p class="title">닉네임</p>
	            <input type="text" class="s_inputs" name="nickname" onkeyup="characterCheck(this)" onkeydown="characterCheck(this)"></input>
	            </div>
	            
	            <div>
	            <p class="title">성별</p>
	            <select name="sex" class="s_inputs" required>
	    		<option value="none">선택</option>
	    		<option value="man">남자</option>
	    		<option value="woman">여자</option>
	    		</select>
	            </div>
	            
	            <div>
	            <p class="title">본인 확인 이메일</p>
	            <input type="text" name="email" placeholder="" class="s_inputs" onkeyup="mailCheck(this)" onkeydown="mailCheck(this)" required></input>
	            </div>
	            
	            <div class="btn_s_wrap">
	            <input type="submit" value="가입하기" class="sign_btn"></input>
	            </div>
	            
            </section>
            </form>
	</div>
</body>
</html>

