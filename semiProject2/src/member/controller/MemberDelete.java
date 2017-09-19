package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberDelete
 */
@WebServlet("/dropOutMember")
public class MemberDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		int deleteResult = new MemberService().deleteMember(userId, userPwd);
		 
		if (deleteResult >0 ) {
			request.getSession().invalidate();
			response.sendRedirect("/semi/view/member/loginForm.jsp");
		} else {
			request.setAttribute("message", "회원삭제에 실패하였습니다.");
			response.sendRedirect("/semi/view/myPage/myPage.jsp");
		}
	}

}
