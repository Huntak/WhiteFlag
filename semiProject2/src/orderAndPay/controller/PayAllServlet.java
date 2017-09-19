package orderAndPay.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.model.service.CartService;
import orderAndPay.model.service.OrderListService;
import orderAndPay.model.vo.OrderList;
import stock.model.service.StockService;
import stock.model.vo.Stock;

/**
 * Servlet implementation class PayAllServlet
 */
@WebServlet("/payall")
public class PayAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		String osnumber = request.getParameter("osnumber");
		int minus = Integer.parseInt(request.getParameter("minus"));
		
		String[] omileage = request.getParameterValues("omileage");
		String[] varray = request.getParameterValues("varray");
		String[] pid = request.getParameterValues("allpid");
		String[] allcartid = request.getParameterValues("allcartid");
		String[] allprice = request.getParameterValues("allprice");
		String[] allquantity = request.getParameterValues("allquantity");
		
		//시퀀스 가져오기
		String totalid = new OrderListService().selectTotalId();
		
		//배송비, 적립금 총금액에 계산
		int[] total = new int[allcartid.length];
		for(int i = 0; i < allcartid.length; i++){
			total[i] = Integer.parseInt(allprice[i]) *  Integer.parseInt(allquantity[i]);
	
		}
		total[0] =  total[0] + 2500 - minus;
	
		
		int[] quantity = new int[allquantity.length];
		for(int i = 0; i<allquantity.length; i++){
			quantity[i] = Integer.parseInt(allquantity[i]);
		}
		
		OrderList ol = null;
		ArrayList<Stock> stock = new ArrayList<Stock>();
		int[] stockminus = new int[allquantity.length];
		
		int[] omileage2 = new int[omileage.length];		
		for(int i = 0; i<allcartid.length; i++){
			
		//재고 마이너스 처리
		stock.add(new StockService().selectOneStock(pid[i]));
		stockminus[i] = stock.get(i).getsQuantity() - quantity[i];
		int minusStock = new StockService().updateStockQuantity(pid[i], stockminus[i]);
					
		omileage2[i] = Integer.parseInt(omileage[i].trim());
		
		ol = new OrderList(totalid, mid, pid[i], osnumber, omileage2[i], quantity[i]
				, varray[0], varray[1], varray[2], varray[3], varray[4], varray[5], varray[6], varray[7], varray[8], total[i]);
		System.out.println(ol);
		
		int result = new OrderListService().insertOrderList(ol);
		System.out.println(result + "오더리스트 완료");
		
			if(result > 0){
				int delete = new CartService().deleteCart(allcartid[i]);
				System.out.println(delete+"전체주문 카트삭제 완료");
			
		}
		
		}
		
		
	}

}
