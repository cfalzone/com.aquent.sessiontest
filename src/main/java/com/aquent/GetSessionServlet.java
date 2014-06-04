package com.aquent;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetSessionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 5928803305632119583L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		Date d = (Date) session.getAttribute("testDate");
		
		resp.setContentType("text/html");
        ServletOutputStream out = resp.getOutputStream();
        out.println("<p>The session date is "+d+"</p>");
	}

}