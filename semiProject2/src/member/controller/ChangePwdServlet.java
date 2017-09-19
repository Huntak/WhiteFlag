package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class ChangePwdServlet
 */
@WebServlet("/changePw")
public class ChangePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String mID = request.getParameter("afteruserId");
		String mPWD = request.getParameter("updatePwd");
			
		int result = new MemberService().changePwd(mID,mPWD);

		System.out.println(mID +"  "+mPWD);
		
		if (result > 0) {
			response.sendRedirect("/semi/view/member/loginForm.jsp");
		}

		else { response.sendRedirect("/semi/view/member/enrollForm.jsp"); System.out.println("실패"); }
	}

}
