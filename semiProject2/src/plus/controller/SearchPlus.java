package plus.controller;

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

import plus.model.service.PlusService;
import plus.model.vo.Plus;
import stock.model.service.StockService;
import stock.model.vo.Stock;

/**
 * Servlet implementation class SearchPlus
 */
@WebServlet("/plusSearch")
public class SearchPlus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPlus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchtype = request.getParameter("plusSearchType");
		String searchvalue = request.getParameter("plusSearchvalue");	
		String searchDate1 = request.getParameter("plusSearchBeforeDate");	
		String searchDate2 = request.getParameter("plusSearchAfterDate");	
		
	
		
		ArrayList<Plus> list = new PlusService().searchSelectPlus(searchtype,searchvalue,searchDate1,searchDate2);
		
	 
		
		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		for(Plus p : list){
		
			JSONObject j = new JSONObject();
			j.put("pNum", p.getPlusNumber());
			j.put("pId", p.getpId());
			j.put("pName", p.getpName());
			j.put("pSize", p.getpSize());
			j.put("pColor", p.getpColor());
			j.put("pMaterial", p.getpMaterial());
			j.put("pPrice", p.getpPrice());
			j.put("pSeason", p.getpSeason());
			j.put("pProvider", p.getProvidercode());
			j.put("pPlusPrice", p.getPlusPrice());
			j.put("pPlusQuantity", p.getPlusQuantity());
			j.put("pPlusTotal", p.getPlusTotal());
			j.put("pPlusDate", p.getPlusDate());
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
