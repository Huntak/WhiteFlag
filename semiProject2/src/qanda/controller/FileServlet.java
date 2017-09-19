package qanda.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileServlet
 */
@WebServlet("/qdown")
public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 다운로드 처리용 컨트롤러
		request.setCharacterEncoding("utf-8");
		
		String originalFilepath = request.getParameter("ofile");
		String renameFilepath = request.getParameter("rfile");
		
		//파일이 저장된 위치 지정
		String savePath = request.getSession().getServletContext().getRealPath("/qnaUploadFiles/");
		
		//서버의 해당 위치의 파일을 읽어서 클라이언트로 파일을 내보냄
		//파일읽기용 스트림과 파일출력용 스트림 생성
		BufferedInputStream bin = null;
		ServletOutputStream sout = null;
		File downFile = new File(savePath + "\\" + renameFilepath);
		
		response.setContentType("text/plane"); //파일일때는 text/plane
		response.setCharacterEncoding("utf-8");
		response.addHeader("Content-Disposition", "attachment; filename=" + originalFilepath);
		response.setContentLength((int)downFile.length());
		
		sout = response.getOutputStream();
		bin = new BufferedInputStream(new FileInputStream(downFile));
		
		int readData = 0;
		while((readData = bin.read()) != -1){
			sout.write(readData);
			}
		bin.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
