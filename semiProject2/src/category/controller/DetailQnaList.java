package category.controller;

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

import qanda.model.service.QnaService;
import qanda.model.vo.Qna;

/**
 * Servlet implementation class DetailQnaList
 */
@WebServlet("/dQnaList")
public class DetailQnaList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailQnaList() {
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
				
				int currentPageQ = 1;
				int limitQ = 5;		// 한 페이지에 10개씩 출력
				
				if(request.getParameter("pageQ") != null)
					currentPageQ = Integer.parseInt(request.getParameter("pageQ"));
				int listCountQ = new QnaService().getListCountImageId(imageId);
				
				//ArrayList<Notice> list = nservice.selectList();
				ArrayList<Qna> listQ = new QnaService().selectListImageId(currentPageQ, limitQ, imageId);
				// 총 페이지 수 계산 : 목록이 최소 1개일 때 1page로 처리
				// 0.9를 더함
				int maxPageQ = (int)((double)listCountQ / limitQ + 0.8);
				// 현재 페이지에 보여질 시작 페이지 값 (1, 11, 21, 31, ...)
				int startPageQ = (((int)((double)currentPageQ / limitQ + 0.8)) - 1) * limitQ + 1;
				// 현재 페이지에 보여질 마지막 페이지 값 (10, 20, 30, ...)
				int endPageQ =  startPageQ + limitQ - 1;
				
				if(maxPageQ < endPageQ)
					endPageQ = maxPageQ;
				
				JSONObject json = new JSONObject();
				
				json.put("listCountQ", new Integer(listCountQ));
				json.put("currentPageQ",currentPageQ);
				json.put("startPageQ", startPageQ);
				json.put("endPageQ", endPageQ);
				json.put("maxPageQ", maxPageQ);
				
				JSONArray jarr = new JSONArray();
				for(Qna q : listQ){
					//System.out.println(r);
					JSONObject j = new JSONObject();
					j.put("qNumber", q.getqNumber());
					j.put("qTitle", q.getqTitle());
					j.put("qContent", q.getqContent());
					j.put("qDate", q.getqDate());
					j.put("qImage", q.getqImage());
					j.put("qReadcount", q.getqReadcount());
					j.put("qAnswer_YN", q.getqAnswerYN());
					j.put("mId", q.getmId());
					j.put("pId", q.getPid());
					jarr.put(j);
				}
				json.put("listQ", jarr);
				
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
