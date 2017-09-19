package category.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import review.model.service.ReviewService;
import review.model.vo.Review;
import stock.model.vo.Stock;

/**
 * Servlet implementation class detailReviewList
 */
@WebServlet("/dReviewList")
public class DetailReviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailReviewList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지글 전체 조회용 컨트롤러
		response.setContentType("text/html; charset=utf-8");
		
		int imageId = 0;
		if(request.getParameter("imageId") != null)
			imageId = Integer.parseInt(request.getParameter("imageId"));
		
		int currentPageR = 1;
		int limitR = 5;		// 한 페이지에 10개씩 출력
		
		if(request.getParameter("pageR") != null)
			currentPageR = Integer.parseInt(request.getParameter("pageR"));
		int listCountR = new ReviewService().getListCountImageId(imageId);
		
		//ArrayList<Notice> list = nservice.selectList();
		ArrayList<Review> listR = new ReviewService().selectListImageId(currentPageR, limitR, imageId);
		// 총 페이지 수 계산 : 목록이 최소 1개일 때 1page로 처리
		// 0.9를 더함
		int maxPageR = (int)((double)listCountR / limitR + 0.8);
		// 현재 페이지에 보여질 시작 페이지 값 (1, 11, 21, 31, ...)
		int startPageR = (((int)((double)currentPageR / limitR + 0.8)) - 1) * limitR + 1;
		// 현재 페이지에 보여질 마지막 페이지 값 (10, 20, 30, ...)
		int endPageR =  startPageR + limitR - 1;
		
		if(maxPageR < endPageR)
			endPageR = maxPageR;
		
		JSONObject json = new JSONObject();
		
		json.put("listCountR", new Integer(listCountR));
		json.put("currentPageR",currentPageR);
		json.put("startPageR", startPageR);
		json.put("endPageR", endPageR);
		json.put("maxPageR", maxPageR);
		
		JSONArray jarr = new JSONArray();
		for(Review r : listR){
			//System.out.println(r);
			JSONObject j = new JSONObject();
			j.put("rNumber", r.getrNumber());
			j.put("rTitle", r.getrTitle());
			j.put("rContent", r.getrContent());
			j.put("rDate", r.getrDate());
			j.put("rImage", r.getrImage());
			j.put("rReadcount", r.getrReadcount());
			j.put("rAnswer_YN", r.getrAnswer_YN());
			j.put("mId", r.getmId());
			j.put("pId", r.getpId());
			jarr.put(j);
		}
		json.put("listR", jarr);
		
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
