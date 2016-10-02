package cs3220servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/show-request-headers")
public class ShowRequestHeaders extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("	<!-- Latest compiled and minified CSS -->");
		out.println("	<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");			
		out.println("	<meta charset=\"UTF-8\">");
		out.println("    <title>Table of all request headers</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("    <p><b>Request Method: </b> " + request.getMethod() + "</p>\n");
		out.println("    <p><b>Request RequestURI: </b> " + request.getRequestURI() + "</p>\n");
		out.println("    <p><b>Request Protocol: </b> " + request.getProtocol() + "</p>\n");
		out.println("");
		out.println("    <table class = \"table\">");
		out.println("        <tr>");
		out.println("            <th>Header Name</th>");
		out.println("            <th>Header Value</th>");
		out.println("        </tr>");
		
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			//print all headers
			out.println("        <tr>");
			out.println("            <td>" + headerName + "</td>");
			out.println("            <td>" + request.getHeader(headerName) + "</td>");
			out.println("        </tr>");
		}
	}

	//Since this servlet is for debugging, handle GET and POST identically
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
