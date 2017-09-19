package orderAndPay.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class ApplyMileageAll
 */
@WebServlet("/applyAll")
public class ApplyMileageAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyMileageAll() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서블릿");
	
		String mid = request.getParameter("mid");
		String[] allprice = request.getParameterValues("allprice");
		String[] allquantity = request.getParameterValues("allquantity");
		int mileage = Integer.parseInt(request.getParameter("mmileage"));
		
		float mileageRate = (float) new MemberService().selectMileage(mid);
		Member member = new MemberService().selectOneMember(mid);
	
		int allPrice[] = new int[allprice.length];
		int allQuantity[] = new int[allquantity.length];
		float apllyMileage = 0;
		for(int i = 0; i<allquantity.length; i++){
			allPrice[i] = Integer.parseInt(allprice[i]);
			allQuantity[i] = Integer.parseInt(allquantity[i]);
			apllyMileage += (allPrice[i] * allQuantity[i]) * mileageRate;
		}
		
		int minuMileage = (int)member.getMTOTALMILEAGE() - mileage;
		
		int finalMileage = 0;
		
		finalMileage = Math.round(minuMileage + apllyMileage);
		
		int result = new MemberService().updateMemberMileage(mid, finalMileage);
		System.out.println(result + " : 마일리지 결과" + finalMileage);	
	}

}
