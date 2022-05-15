package com;

import com.Payments;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PaymentsAPI")
public class PaymentsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Payments projectObj = new Payments();
	
    public PaymentsAPI() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	//	doGet(request, response);
		
		String output = projectObj.insertProject(
				request.getParameter("b_id"), 
				request.getParameter("account_number"),
				request.getParameter("c_id"),
				request.getParameter("c_name"),
				request.getParameter("amount"),
				request.getParameter("card_number"),
				request.getParameter("bank_name"),
				request.getParameter("card_exp_date"),
				request.getParameter("cvv"),
				request.getParameter("date"));

		response.getWriter().write(output);
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Map paras = getParasMap(request);

		String output = projectObj.updateProject(paras.get("hidProjectIDSave").toString(),
				paras.get("b_id").toString(), 
				paras.get("account_number").toString(),
				paras.get("c_id").toString(),
				paras.get("c_name").toString(), 
				paras.get("amount").toString(),
				paras.get("card_number").toString(),
				paras.get("bank_name").toString(), 
				paras.get("card_exp_date").toString(),
				paras.get("cvv").toString(),
				paras.get("date").toString()
				

		);

		response.getWriter().write(output);
		
	}

	

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);

		String output = projectObj.deleteProject(paras.get("id").toString());

		response.getWriter().write(output);
		
	}
	
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();

			String[] params = queryString.split("&");
			for (String param : params) {

				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {

		}
		return map;

	}

}
