package utility;

import java.security.MessageDigest;

public class SecurityPassword {
	
	//패스워드 문자열을 전달받아 암호화하여 반환하는 메서드, static 이니까 객체생성 없이 사용가능
	public static String encoding(String pw) {
		String newPassWord = "";
		try {
			//자바시큐리티에 메세지다이제스트 임포트
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			//암호화하고자 하는 패스워드를 update() 메서드를 이용하여 전달
			//String 객체가 아닌 byte 배열 형태로 전달
			md.update(pw.getBytes());
			//패스워드 문자열을 digest() 메서드를 이용하여 암호화하여 byte배열로 반환하여 저장
			byte[] encodeData = md.digest();
			//암호화된 byte 배열을 String 객체로 변환하여 저장
			for(int i=0; i<encodeData.length; i++) {
				//byte 데이터를 16진수 문자열로 변환하여 반환, 문자열에 추가(한번 더 암호화)
				newPassWord += Integer.toHexString(encodeData[i]&0xFF);
			}//Integer.toHexString: 문자열로 바꿈
		}catch(Exception e) {
			e.printStackTrace();
		}
		return newPassWord;
	}
}
