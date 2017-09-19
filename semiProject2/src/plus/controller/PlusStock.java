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

import member.model.service.MemberService;
import member.model.vo.Member;
import plus.model.service.PlusService;
import plus.model.vo.Plus;

/**
 * Servlet implementation class PlusStock
 */
@WebServlet("/plusstock")
public class PlusStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlusStock() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//PID, PLUSPRICE, PLUSQUANTITY, PLUSTOTAL 필요.	 
		 String plus = request.getParameter("plusArray");
		 	
		 String[] plusArray = plus.split("],");  
		 ArrayList<Plus> plusArrayForPlus = new ArrayList<Plus>(plusArray.length);
		 int countPlusObject = 0;
		 String[] eachPlusArray = null ;
		// System.out.println(plusArray[1]);
		// System.out.println(plusArray[1].substring(1));
		 for(int i=0; i<plusArray.length; i++){  
			 
			 // 넘어온 값들을 객체별(pid)로  값과 , 만 남게 수정한다.   10, 20000, 5, 100000 
			 if(i==0) { plusArray[i] = plusArray[i].substring(2);  }
			 else if(i==plusArray.length-1){  plusArray[i] =plusArray[i].substring(1,plusArray[i].length()-2) ;		}
			 else {plusArray[i] = plusArray[i].substring(1); }
			 			 
			 // 객체별로 각각의 값을 ,를 제외한 하나의 배열로 만들어 담는다.
			 eachPlusArray = plusArray[i].split(",");
			 
			 // 입고량이 0인 항목을 제외한다.
			 if(!eachPlusArray[2].equals("0")){ 
				 Plus pl = new Plus(eachPlusArray[0],eachPlusArray[1],eachPlusArray[2],eachPlusArray[3] );
				 plusArrayForPlus.add(pl);
				 //countPlusObject ++; 
			}
		 }
		 
		 // 입고갯수가 0인 항목은 List에서 삭제.
		 for(int j=0; j<plusArrayForPlus.size(); j++ ){
			 if(plusArrayForPlus.get(j).getPlusQuantity()==0     ){
				 plusArrayForPlus.remove(j);
			 }
		 }
		 		      
	      int result = new PlusService().insertPlus(plusArrayForPlus);
	      
	      JSONObject job = new JSONObject();
	      job.put("result", String.valueOf(result));
	      PrintWriter out = response.getWriter();
	      out.print(job.toJSONString());
	      out.flush();
	      out.close();
	    
	
	}

}
