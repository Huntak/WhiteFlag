package product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import product.model.service.ProductService;
import product.model.vo.*;

/**
 * Servlet implementation class NewProductServlet
 */
@WebServlet("/pnew")
public class NewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 새상품 등록 처리용 컨트롤러
		request.setCharacterEncoding("utf-8");
		String image = request.getSession().getServletContext().getRealPath("image");
		MultipartRequest multi = new MultipartRequest(request,image,10*1024*1024,"utf-8",new DefaultFileRenamePolicy());
				
		//response.setContentType("text/html; charset=utf-8"); //여기서 오타 나면 페이지로 넘어가지않고 다운로드가 실행된다.
		int count = 0;
		
		String pName = multi.getParameter("pName");
		int pPrice = Integer.parseInt(multi.getParameter("pPrice"));
		String[] pSize = multi.getParameterValues("pSize");
		String[] pColor = multi.getParameterValues("pColor");
		String pMaterial = multi.getParameter("pMaterial");
		String cId = multi.getParameter("category");
		String pSeason = multi.getParameter("season");
		String providerCode = multi.getParameter("providerCode");
		
		String mainImage = multi.getOriginalFileName("mainimage");
		String image1 = multi.getOriginalFileName("image1");
		String image2 = multi.getOriginalFileName("image2");
		String image3 = multi.getOriginalFileName("image3");
		String image4 = multi.getOriginalFileName("image4");
		String image5 = multi.getOriginalFileName("image5");
		String image6 = multi.getOriginalFileName("image6");
		String image7 = multi.getOriginalFileName("image7");
		String image8 = multi.getOriginalFileName("image8");
		String image9 = multi.getOriginalFileName("image9");
		String image10 = multi.getOriginalFileName("image10");
		
		/*for(int i = 0; i<pColor.length;i++)
			System.out.println(pColor.length);*/
		
		ProductImage productImage = new ProductImage(mainImage,image1,image2,image3,image4,image5,image6,image7,image8,image9,image10); 
		
		int imageid = new ProductService().insertProductImage(productImage);
		
		for(int i = 0; i<pSize.length;i++){
			for(int j = 0; j<pColor.length;j++){
				Product product = new Product(pName, pPrice, pSize[i], pColor[j], pMaterial, pSeason, cId, providerCode, imageid);
				
				count += new ProductService().insertProduct(product);	
			}
		}

		response.sendRedirect("/semi/index.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
