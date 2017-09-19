package product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import product.model.service.ProductService;
import product.model.vo.Product;

/**
 * Servlet implementation class InsertRowOneProductServlet
 */
@WebServlet("/insertrowone")
public class InsertRowOneProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertRowOneProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String pname = request.getParameter("pname");
		int pprice = Integer.parseInt(request.getParameter("pprice"));
		String pmaterial = request.getParameter("pmaterial");
		String pseason = request.getParameter("pseason");
		String providercode = request.getParameter("providercode");
		String cid = request.getParameter("cid");
		int imageid = Integer.parseInt(request.getParameter("imageid"));
		String psize = request.getParameter("psize");
		String pcolor = request.getParameter("pcolor");
		
		Product p = new Product();
		p.setpName(pname);
		p.setpSeason(pseason);
		p.setpColor(pcolor);
		p.setpSize(psize);
		p.setpMaterial(pmaterial);
		p.setProviderCode(providercode);
		p.setcId(cid);
		p.setImageId(imageid);
		p.setpPrice(pprice);
		System.out.println(p);
		int result = new ProductService().insertProduct(p);
		
		JSONObject json = new JSONObject();
		
		json.put("result", result);
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
