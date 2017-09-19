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
 * Servlet implementation class EnrollServelet
 */
@WebServlet("/enroll")
public class EnrollServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String mID = request.getParameter("userId");
		String mPWD = request.getParameter("userPwd");
		String mNAME = request.getParameter("userName");
		String mBIRTH = request.getParameter("birth");
		String mEMAIL = request.getParameter("userEmail1") + "@" + request.getParameter("userEmail2");
		String mPHONE1 = request.getParameter("hp1");
		String mPHONE2 = request.getParameter("hp2");
		String mPHONE3 = request.getParameter("hp3");
		String mADDRESS1 = request.getParameter("post1");
		String mADDRESS2 = request.getParameter("post2");
		String mADDRESS3 = request.getParameter("address");
		String mADDRESS4 = request.getParameter("address2");
		String gRADE = "normal";
		long mTOTALMILEAGE = 2000;
		long mTOTALPURCHASE = 0;
		char mGENDER = request.getParameter("gender").charAt(0);

		Member me = new Member(mID, mPWD, mNAME, mBIRTH, mEMAIL, mPHONE1, mPHONE2, mPHONE3, mADDRESS1, mADDRESS2,
				mADDRESS3, mADDRESS4, gRADE, mTOTALMILEAGE, mTOTALPURCHASE, mGENDER, null);

		int result = new MemberService().insertMember(me);

		if (result > 0) {
			response.sendRedirect("/semi/view/member/loginForm.jsp");
		}

		else { response.sendRedirect("/semi/view/member/enrollForm.jsp"); System.out.println("실패"); }
	}

}
