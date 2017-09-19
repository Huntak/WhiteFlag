package orderAndPay.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.model.dao.CartDao;
import cart.model.service.CartService;
import orderAndPay.model.service.OrderListService;
import orderAndPay.model.vo.OrderList;
import stock.model.service.StockService;
import stock.model.vo.Stock;


/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 결제 서블릿
		String mid = request.getParameter("mid");
		String pid = request.getParameter("pid");
		String cartid = request.getParameter("cartid");
		String osnumber = request.getParameter("osnumber");
		String[] varray = request.getParameterValues("varray");
		int omiliage = Integer.parseInt(request.getParameter("omiliage"));
		int oquantity = Integer.parseInt(request.getParameter("oquantity"));
		int total = Integer.parseInt(request.getParameter("total2"));
		
		//재고 마이너스 처리
		Stock stock = new StockService().selectOneStock(pid);
		int minus = stock.getsQuantity() - oquantity;
		int minusStock = new StockService().updateStockQuantity(pid, minus);
				
		System.out.println(minusStock + "재고 마이너스 완료");
		
		String totalid = new OrderListService().selectTotalId();
		
		OrderList ol = new OrderList();
		ol.setMID(mid);
		ol.setPID(pid);
		ol.setOSNUMBER(osnumber);
		ol.setOMILEAGE(omiliage);
		ol.setOQUANTITY(oquantity);
		ol.setNAME(varray[0]);
		ol.setPOST1(varray[1]);
		ol.setPOST2(varray[2]);
		ol.setADDRESS1(varray[3]);
		ol.setADDRESS2(varray[4]);
		ol.setPHONE1(varray[5]);
		ol.setPHONE2(varray[6]);
		ol.setPHONE3(varray[7]);
		ol.setEMAIL(varray[8]);
		ol.setTOTALPRICE(total);
		ol.setTOTALID(totalid);
		
		int result = new OrderListService().insertOrderList(ol);
		System.out.println(result + "오더리스트 완료");
		
		if(result > 0){
			int delete = new CartService().deleteCart(cartid);
			System.out.println(delete+"카트 하나 삭제 완료");
		}
		
		
	}

}
