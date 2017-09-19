package orderAndPay.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.dao.MemberDao;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class ApplyMiliage
 */
@WebServlet("/mileage")
public class ApplyMiliage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyMiliage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 마일리지 적용
		
		String mid = request.getParameter("mmid");
		int price  = Integer.parseInt(request.getParameter("mprice"));
		int mileage = Integer.parseInt(request.getParameter("mmileage"));
		
		float mileageRate = (float) new MemberService().selectMileage(mid);
		Member member = new MemberService().selectOneMember(mid);
		
		int minuMileage = (int)member.getMTOTALMILEAGE() - mileage;
		float apllyMileage = price * mileageRate;
		
		int finalMileage = 0;
		
		finalMileage = Math.round(minuMileage + apllyMileage);
		
		int result = new MemberService().updateMemberMileage(mid, finalMileage);
		System.out.println(result + " : 마일리지 결과");	
	}

}
