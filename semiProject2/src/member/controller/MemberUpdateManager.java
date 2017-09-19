package member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateManager
 */
@WebServlet("/mmupdate")
public class MemberUpdateManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자 회원정보 수정 페이지
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String mID = request.getParameter("mid");
		String mPWD = request.getParameter("mpwd");
		String mNAME = request.getParameter("mname");
		String mBIRTH = request.getParameter("mbirth");
		String mEMAIL = request.getParameter("memail");
		String mPHONE1 = request.getParameter("mphone1");
		String mPHONE2 = request.getParameter("mphone2");
		String mPHONE3 = request.getParameter("mphone3");
		String mADDRESS1 = request.getParameter("maddress1");
		String mADDRESS2 = request.getParameter("maddress2");
		String mADDRESS3 = request.getParameter("maddress3");
		String mADDRESS4 = request.getParameter("maddress4");
		String gRADE = request.getParameter("grade");
		long mTOTALMILEAGE = Long.parseLong(request.getParameter("mtotalmileage"));
		long mTOTALPURCHASE = Long.parseLong(request.getParameter("mtotalpurchase"));
		char mGENDER = request.getParameter("mgender").charAt(0);
		Date mEnrollDate = Date.valueOf(request.getParameter("menrolldate"));
		
		Member m = new Member(mID, mPWD, mNAME, mBIRTH, mEMAIL, mPHONE1, mPHONE2, mPHONE3, mADDRESS1, mADDRESS2,
				mADDRESS3, mADDRESS4, gRADE, mTOTALMILEAGE, mTOTALPURCHASE, mGENDER, mEnrollDate);
		
		int result = new MemberService().updateMemberManager(m);
		
		RequestDispatcher view = request.getRequestDispatcher("mselectone?mid="+mID);
		request.setAttribute("m", m);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
