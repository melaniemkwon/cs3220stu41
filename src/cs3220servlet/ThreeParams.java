package cs3220servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/three-params")
public class ThreeParams extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("    <title>Three Params</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<ul>");
		out.println("    <li><b>param1</b>: " + request.getParameter("param1") + "</li>");
		out.println("    <li><b>param2</b>: " + request.getParameter("param2") + "</li>");
		out.println("    <li><b>param3</b>: " + request.getParameter("param3") + "</li>");
		out.println("</ul>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
