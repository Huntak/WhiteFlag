package provider.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import provider.model.service.ProviderService;
import provider.model.vo.Provider;

/**
 * Servlet implementation class ProviderUpdateServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/updateprovider" })
public class ProviderUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProviderUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String providercode = request.getParameter("providercode");
		String providercompany = request.getParameter("providercompany");
		String providername = request.getParameter("providername");
		String providertell = request.getParameter("providertell");
		String providerphone = request.getParameter("providerphone");
		String provideraddress1 = request.getParameter("post1");
		String provideraddress2 = request.getParameter("post2");
		String provideraddress3 = request.getParameter("address");
		String provideraddress4 = request.getParameter("address2");
		String provideretc = request.getParameter("provideretc");
		
		Provider p = new Provider(providercode, providercompany, providername, providertell, providerphone, provideraddress1, provideraddress2, provideraddress3, provideraddress4, provideretc);
		
		int result = new ProviderService().updateProvider(p);
		
		RequestDispatcher view = request.getRequestDispatcher("view/manager/providerView.jsp");
		request.setAttribute("p", p);
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
