package controller.member;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

@WebServlet("/emailsend.do")
public class EmailSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmailSendServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		String host = "smtp.naver.com";
		String user = "baozi3785@naver.com";
		String password = "";
		
		String to_email = email;
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.ssl.enable", "true");
	    props.put("mail.smtp.ssl.protocols", "TLSv1.2");
	    
	    StringBuffer temp =new StringBuffer();//StringBuffer: 문자형 객체만 저장할 수 있는 기억장소 객체 생성
        Random rnd = new Random();
        for(int i=0;i<10;i++)
        {
            int rIndex = rnd.nextInt(3);// 0 1 2
            switch (rIndex) {
            case 0:
                // a-z
                temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                break;
            case 1:
                // A-Z
                temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                break;
            case 2:
                // 0-9
                temp.append((rnd.nextInt(10)));
                break;
            }
        }
        String AuthenticationKey = temp.toString();//temp 값을 문자열로 바꿔서 저장
        System.out.println(AuthenticationKey);//인증번호 콘솔 출력
        
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user,password);
            }
        });
        
        //email 전송
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(user, "SKETCHBOOK"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));
            
            //메일 제목
            msg.setSubject("SKETCHBOOK認証番号のお知らせメールです");
            //메일 내용
            msg.setText("認証番号 :"+temp);
            
            Transport.send(msg);//msg 실제로 전송
            System.out.println("이메일 전송");
            
        }catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }
        HttpSession saveKey = request.getSession();//세션
        saveKey.setAttribute("AuthenticationKey", AuthenticationKey);
        //인증번호를 AuthenticationKey 세션 속성에 저장한다(다른 페이지에서도 사용할 수 있게)
        
        JSONObject obj = new JSONObject();//제이슨 객체 생성, 프린트라이터 x(제이슨 형식으로 보내야되니까)
        
        obj.put("msg", "メール転送完了しました" );
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json, charset=utf-8");
        response.getWriter().print(obj);//메일 전송 후 ajax success 메서드 실행
	}

}
