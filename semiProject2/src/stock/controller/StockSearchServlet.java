package stock.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import stock.model.service.StockService;
import stock.model.vo.Stock;

/**
 * Servlet implementation class StockSearchServlet
 */
@WebServlet("/stocksearch")
public class StockSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchtype = request.getParameter("stocksearchType");
		String searchvalue = request.getParameter("stocksearchvalue");		
		ArrayList<Stock> list = new StockService().searchSelectStock(searchtype,searchvalue);
		
		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		for(Stock s : list){
			System.out.println(s);
			JSONObject j = new JSONObject();
			j.put("pid", s.getpId());
			j.put("pname", s.getpName());
			j.put("psize", s.getpSize());
			j.put("pcolor", s.getpColor());
			j.put("pmaterial", s.getpMaterial());
			j.put("pprice", s.getpPrice());
			j.put("pseason", s.getpSeason());
			j.put("providercode", s.getProvidercode());
			j.put("squantity", s.getsQuantity());
			jarr.put(j);
		}
		
		json.put("list", jarr);
		response.setContentType("application/json; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
