package cs3220servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/Labs/AboutMe"})
public class AboutMe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AboutMe() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mycin = 303229971;
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if (request.getParameter("cin") == null || Integer.parseInt(request.getParameter("cin")) != mycin) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("    <meta charset=\"UTF-8\">");
			out.println("    <title>About Me</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"container\">");
			out.println("  <div class=\"page-header\">");
			out.println("    <h1>About Me <small>CS3220 - Lab</small></h1>");
			out.println("  </div>");
			out.println("  <form action=\"AboutMe\" method=\"post\">");
			out.println("    <input type=\"text\" name=\"cin\" placeholder=\"Enter CIN\" />");
			out.println("    <input type=\"submit\" value=\"Show\" />");
			out.println("  </form>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");			
		}
		else if (request.getParameter("cin") != null && Integer.parseInt(request.getParameter("cin")) == mycin){
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("    <title>About Me</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h2><div id=\"name\">Melanie Kwon</div></h2>");
			out.println("    <p class=\"description\">Most of my experience has been in Java, C++, Python, R, VBA for Excel applications. Also know basics of mobile/resposive design.</p>");
			out.println("    <p class=\"description\">Would like a better understanding of server-side web development, learn .net and apply it to Sharepoint development.</p>");
			out.println("    <p class=\"description\">Undergrad is in poli sci from UCLA, and I'm currently a project analyst at Kaiser. I write SSRS reports and VBA applications in Excel for finance folks.</p>");
			out.println("</body>");
			out.println("</html>");
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
