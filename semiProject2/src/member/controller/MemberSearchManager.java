package member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberSearchManager
 */
@WebServlet("/mmsearch")
public class MemberSearchManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSearchManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원관리 검색 기능
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String keyword = request.getParameter("keyword");
		String checkedItem = request.getParameter("memberSearch");
		
		ArrayList<Member> list = null;
		
		if(keyword == null){
			list = new MemberService().selectMemberList();
		}
		
		if(checkedItem.equals("mid")){
			list = new MemberService().searchMidManager(keyword);
		}
		if(checkedItem.equals("mname")){
			list = new MemberService().searchMnameManager(keyword);
		}
		if(checkedItem.equals("mphone")){
			list = new MemberService().searchMphoneManager(keyword);
		}
		if(checkedItem.equals("maddress")){
			list = new MemberService().searchMaddressManager(keyword);
		}
		if(checkedItem.equals("menrolldate")){
			String beforeDate = request.getParameter("beforeDate");
			String afterDate = request.getParameter("afterDate");
			if(beforeDate != afterDate)
			list = new MemberService().searchMenrolldateManager(beforeDate,afterDate);
			else{
				list = new MemberService().searchMenrolldateManager(beforeDate);
			}
		}
		
		RequestDispatcher view = null;
		view = request.getRequestDispatcher("view/manager/memberManager.jsp");
		request.setAttribute("list", list);
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
