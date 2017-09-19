package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginServelet
 */
@WebServlet("/login")
public class LoginServelet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // 로그인 서비스 처리용 컨트롤러
      //System.out.println("LoginServlet 클래스 구동 확인...");
      // 1. 전송값에 한글이 포함되어 있을 경우 인코딩 처리함.
      request.setCharacterEncoding("utf-8");
      // 2. 처리결과 응답페이지 종류 지정
      response.setContentType("text/html; charset=utf-8");

      // 3. 전송값 꺼내서 변수에 담기 ( 파라미터 명은 html해당 태그에 준 name 명)
      String userId = request.getParameter("userId");
      String userPwd = request.getParameter("userPwd");

      //System.out.println(userId+", "+ userPwd);
      // 4. 비즈니스로직 처리용 클래스로 전달하고 결과받기
      Member loginUser = new MemberService().loginCheck(userId, userPwd);
      System.out.println(loginUser);
      // Member loginUser = null;
      // 5. 받은 결과를 가지고 뷰를 선택해서 클라이언트에게 내보냄(응답)
      if (loginUser != null) {
         // 로그인 상태 관리를 위한 세션객체 생성함

         HttpSession session = request.getSession(); // 톰캣서버에 세션이 만들어지고 고유
                                          // 아이디가 발급

         // session.setMaxInactiveInterval(60*30); // ()안의 숫자는 초단위. 하지만
         // 소스단위에서 이렇게 작성하기는 번거로우니까 보통 web.xml에서 작성.
         // <session-config><session-timeout>30</session-timeout></session-config>

         // System.out.println("생성된 세션객체의 id : "+ session.getId());
         session.setAttribute("loginUser", loginUser);
      
            response.sendRedirect("/semi/index.jsp");

      } else {
         RequestDispatcher view = null;
         
         view = request.getRequestDispatcher("view/member/loginForm.jsp");
         request.setAttribute("message", "loginError");
         view.forward(request, response);
       
          
      }
   }

}