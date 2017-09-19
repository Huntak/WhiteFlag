package plus.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import plus.model.service.PlusService;
import plus.model.vo.Plus;

/**
 * Servlet implementation class DeleteStock
 */
@WebServlet("/deletestock")
public class DeleteStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStock() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//PID, PLUSPRICE, PLUSQUANTITY, PLUSTOTAL 필요.	 
		 String plusEditArray = request.getParameter("plusEditArray");
		 	System.out.println(plusEditArray);
		 String[] plusArrayForEdit = plusEditArray.split("],");  
		 ArrayList<Plus> plusArrayForDelete = new ArrayList<Plus>(plusArrayForEdit.length);
		 int countPlusObject = 0;
		 String[] eachPlusArray = null ;
		// System.out.println(plusArray[1]);
		// System.out.println(plusArray[1].substring(1));
		 for(int i=0; i<plusArrayForEdit.length; i++){  
			 
			 if(plusArrayForEdit.length==1 && i==0) {plusArrayForEdit[i] =plusArrayForEdit[i].substring(2,plusArrayForEdit[i].length()-2) ;}
			 else {
			 // 넘어온 값들을 객체별(pid)로  값과 , 만 남게 수정한다.   10, 20000, 5, 100000 
			 if(i==0) { plusArrayForEdit[i] = plusArrayForEdit[i].substring(2);  }
			 else if(i==plusArrayForEdit.length-1){  plusArrayForEdit[i] =plusArrayForEdit[i].substring(1,plusArrayForEdit[i].length()-2) ;		}
			 else {plusArrayForEdit[i] = plusArrayForEdit[i].substring(1); }
			 }
			 System.out.println("plusArrayForEdit.length : "+plusArrayForEdit.length);
			 // 객체별로 각각의 값을 ,를 제외한 하나의 배열로 만들어 담는다.
			 eachPlusArray = plusArrayForEdit[i].split(",");
			 
			 
				 Plus pl = new Plus(eachPlusArray[0],eachPlusArray[1],eachPlusArray[2],eachPlusArray[3] );
				 
				 
				 plusArrayForDelete.add(pl);
				 //countPlusObject ++; 
			
		 }
		 
		 		      
	      int result = new PlusService().deletePlus(plusArrayForDelete);
	      
	      JSONObject job = new JSONObject();
	      job.put("result", String.valueOf(result));
	      PrintWriter out = response.getWriter();
	      out.print(job.toJSONString());
	      out.flush();
	      out.close();
	}

}
