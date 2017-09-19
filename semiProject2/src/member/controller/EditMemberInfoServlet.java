package member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class EditMemberInfoServlet
 */
@WebServlet("/editMemberInfo")
public class EditMemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public EditMemberInfoServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String mID = request.getParameter("userId");
		String mPWD = request.getParameter("userPwd");
		String mEMAIL = request.getParameter("userEmail1") + "@" + request.getParameter("userEmail2");
		String mPHONE1 = request.getParameter("hp1");
		String mPHONE2 = request.getParameter("hp2");
		String mPHONE3 = request.getParameter("hp3");
		String mADDRESS1 = request.getParameter("post1");
		String mADDRESS2 = request.getParameter("post2");
		String mADDRESS3 = request.getParameter("address");
		String mADDRESS4 = request.getParameter("address2");
		 
		
		
	//	System.out.println(mID+"  "+ mPWD+"  "+ mNAME+"  "+ mBIRTH+"  "+ mEMAIL+"  "+ mPHONE1+"  "+ mPHONE2+"  "+ mPHONE3+"  "+ mADDRESS1+"  "+ 
	//			mADDRESS2+"  "+mADDRESS3+"  "+ mADDRESS4+"  "+ gRADE+"  "+ mTOTALMILEAGE+"  "+ mTOTALPURCHASE+"  "+ mGENDER+"  "+ mENROLLDATE);
		
		Member me = new Member(mID, mPWD, mEMAIL, mPHONE1, mPHONE2, mPHONE3, mADDRESS1, mADDRESS2,
				mADDRESS3, mADDRESS4);
		
		int result = new MemberService().updateMember(me);

		if (result > 0) {
			
			Member loginUser = new MemberService().selectOneMember(mID);
			
			HttpSession session = request.getSession(); 

			session.setAttribute("loginUser", loginUser);
			
			response.sendRedirect("/semi/view/myPage/myPage.jsp");
		}

		else { response.sendRedirect("/semi/view/member/editMemberInfo.jsp"); System.out.println("실패"); }
	

	}

}
