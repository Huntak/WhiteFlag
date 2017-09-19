package provider.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import provider.model.service.ProviderService;
import provider.model.vo.Provider;

/**
 * Servlet implementation class ProvideerViewajax
 */
@WebServlet("/vprovider")
public class ProvideerViewajax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProvideerViewajax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String providercode = request.getParameter("providerCode");
		Provider p = new ProviderService().selectOneProvider(providercode);
		JSONObject json = new JSONObject();
		if(p !=null){
		json.put("code", p.getProviderCode());
		json.put("company", p.getProviderCompany());
		json.put("name", p.getProviderName());
		json.put("tell", p.getProviderTell());
		json.put("phone", p.getProviderPhone());
		json.put("address1", p.getProviderAddress1());
		json.put("address2", p.getProviderAddress2());
		json.put("address3", p.getProviderAddress3());
		json.put("address4", p.getProviderAddress4());
		json.put("etc", p.getProviderETC());
		}else{
			json = null;
		}
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
