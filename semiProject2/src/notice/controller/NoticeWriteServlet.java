package notice.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeWriteServlet
 */
@WebServlet("/nwrite")
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일업로드 기능이 추가된 공지글쓰기 처리용 컨트롤러
				//cos.jar 라이브러리를 이용한 소스코드
				//전송자료에 한글이 있으므로 인코딩 처리함
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
				//전송파일에 대한 용량 제한을 설정하고 싶으면 정함 : 10메가바이트로
				int limitSize = 1024 * 1024 * 10;
				
				//전송방식에 multipart 설정을 했는지 검사함
				/*boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				if(!isMultipart){
					RequestDispatcher view = 
					request.getRequestDispatcher("view/notice/noticeError.jsp");
					request.setAttribute("message", "multipart 설정을 하지 않았습니다");
					view.forward(request, response);
				}*/
				
				
				//업로드된 파일의 저장폴더 지정
				//1. 임의로 저장 폴더 만들고 위치로 지정할 경우
				//String savePath = "E:\\수업자료\\saveFiles";
				//2.웹컨테이너 내에 저장 위치를 지정할 경우
				String savePath = request.getSession().getServletContext().getRealPath("/noticeUploadFiles/");
				System.out.println(savePath);
				
				//전송받을 파일명 저장용 변수 원본
				//실제 저장기록될 변경된 파일명 저장용 변수 백업
				//String renameFileName = null;
				
				
				//업로드된 날짜와 시간을 파일명으로 사용할 경우
				//시스템으로 부터 현재 날짜와 시간정보를 얻어 옴
				long currentTime = System.currentTimeMillis();
				//파일명으로 사용할 날짜/시간에 대한 포맷 설정함
				SimpleDateFormat formatFileName = 
							new SimpleDateFormat("yyyyMMddHHmmss");
				
				//cos.jar가 제공하는 전송객체를 이용하여 전송정보 추출
				MultipartRequest mrequest = new MultipartRequest(request, savePath, limitSize, "utf-8", new DefaultFileRenamePolicy());
				
				//업로드 파일명 읽기
				String nImage = mrequest.getFilesystemName("nimage");
				
				if(nImage != null){
					//바꿀 파일명 만들기
					/*renameFileName = formatFileName.format(new java.sql.Date(currentTime)) + "," 
											+ originalFileName.substring(originalFileName.lastIndexOf(".")+ 1);*/
					
					//기록 저장할 파일명 바꾸기 : 저장된 파일을 File 객체로 만듬
					//File saveFile = new File(savePath + "\\" + nImage);
					
					//이름바꾸기 하고 실패했을 경우 강제로 이름바꾸기함
					//바꿀 이름에 대한 파일을 만들고, 원본 파일을 복사하고나서,
					//현재 저장된 원본 파일은 삭제함
					//File newFile = new File(savePath + "\\" + renameFileName);
					
					/*if(!saveFile.renameTo(newFile)){
						//파일의 데이터 읽어서 저장할 변수
						int readData = 0;
						//한번에 읽을 크기와 값 담을 배열 지정
						byte[] buf = new byte[1024];
						
						//파일입력용 스트림과 파일기록용 스트림 생성
						FileInputStream fin = new FileInputStream(saveFile);
						FileOutputStream fout = new FileOutputStream(newFile);
						
						while((readData = fin.read(buf, 0, buf.length)) != -1){
							fout.write(buf);
						}
						
						fin.close();
						fout.close();
						saveFile.delete();
					}*/
				}
				//파일속성을 가져옴 input type name속성을 꼭 똑같이 써야함
				
				//String nNumber = mrequest.getParameter("nnumber"); 시퀀스가 대신 해결함
				String nTitle = mrequest.getParameter("ntitle");
				String nContent	 = mrequest.getParameter("ncontent"); //예외적으로 textarea name 가져옴
			//	String nImage= mrequest.getParameter("nimage");
				
				Notice notice = new Notice(nTitle, nContent, nImage);
				System.out.println(nTitle);
				System.out.println(nContent);
				System.out.println(nImage);
				int result = new NoticeService().insertNotice(notice);
				
					response.sendRedirect("/semi/nlist");
				 
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
