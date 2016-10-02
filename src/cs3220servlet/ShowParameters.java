package cs3220servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/show-parameters")
public class ShowParameters extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("    <title>Reading all request parameters</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table>");
		out.println("    <th>Parameter Name</th>");
		out.println("    <th>Parameter Value(s)</th>");
		
		//String arraylist of request's parameter names
		Enumeration<String> paramNames = request.getParameterNames();
		
		//loop through all parameter names
		while(paramNames.hasMoreElements()) {
			//get next parameter NAME in arraylist
			String paramName = (String)paramNames.nextElement();
			out.println("<tr><td>" + paramName + "<td>");
			
			//String array of parameter's VALUES
			String[] paramValues = request.getParameterValues("paramName");
			
			//if only one value in String array, print no value or the value
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				
				if (paramValue.length() == 0)
					out.println("<i>No Value</i>");
				else
					out.println(paramValue);
			}
			//if more than one value in String array, print each in a list
			else {
				out.println("<ul>");
				for (int i=0; i<paramValues.length; i++) {
					out.println("<li>" + paramValues[i]);
				}
				out.println("</ul>");
			}
		} //end while paramNames
		
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
