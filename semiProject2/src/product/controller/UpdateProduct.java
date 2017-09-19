package product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.ProductService;
import product.model.vo.Product;

/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet("/updatepd")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		
		String[] pid = request.getParameterValues("pid");
		String[] psize = request.getParameterValues("psize");
		String[] pcolor = request.getParameterValues("pcolor");
		String[] pname= request.getParameterValues("pname");
		String[] pmaterial= request.getParameterValues("pmaterial");
		String[] pprice= request.getParameterValues("pprice");
		String[] pseason= request.getParameterValues("pseason");
		String[] imageid = request.getParameterValues("imageid");
		String[] cid = request.getParameterValues("cid");
		String[] providercode = request.getParameterValues("providercode");
		int result = 0;
		Product p = null;
		//String pId, String pName, int pPrice, String pSize, String pColor, String pMaterial,
		//String pSeason, String cId, String providerCode, int imageId
		for(int i = 0; i<pid.length;i++){
			p= new Product(pid[i], pname[i], Integer.parseInt(pprice[i]),psize[i],pcolor[i],pmaterial[i],pseason[i],cid[i],providercode[i],Integer.parseInt(imageid[i]));
			
			result = new ProductService().updateProduct(p);
		}
		
		response.sendRedirect("/semi/pselectall");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
