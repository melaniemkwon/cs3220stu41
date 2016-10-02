package cs3220servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")	// URL relative to the app name
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// code for HTTP GET request
	// HTTPServletRequest: contains anything that comes FROM the browser
	// HTTPServletResponse: uses to send stuff TO browser. Most common is getWriter for PrintWriter that points at browser.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// tell the browser that you're sending it HTML
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		// modify println statements to build legal web page
		// check HTML with formal syntax validator
		
		// Check your HTML with a formal syntax validator
		//  – http://validator.w3.org/
		//	– http://www.htmlhelp.com/tools/validator/
		
//		out.println
//        ("<!DOCTYPE html>\n" +
//         "<html>\n" +
//         "<head><title>A Test Servlet</title></head>\n" +
//         "<body bgcolor=\"#fdf5e6\">\n" +
//         "<h1>Test</h1>\n" +
//         "<p>Simple servlet for testing.</p>\n" +
//         "</body></html>");
		
//		out.println("<!DOCTYPE html>");
//		out.println("<html>");
//		out.println("<head>");
//		out.println("    <title></title>");
//		out.println("</head>");
//		out.println("<body bgcolor=\"#FDF5E6\">");
//		out.println("    <form action=\"SomeProgram\">");
//		out.println("        <center>");
//		out.println("        First name:");
//		out.println("        <input type=\"text\" name=\"firstName\" value=\"J. Random\"><br>");
//		out.println("        Last name:");
//		out.println("        <input type=\"text\" name=\"lastName\" value=\"Hacker\"><p>");
//		out.println("        <input type=\"submit\">");
//		out.println("        </center>");
//		out.println("    </form>");
//		out.println("</body>");
//		out.println("</html>");
		
//		out.println("<!DOCTYPE html>");
//		out.println("<html>");
//		out.println("<head>");
//		out.println("    <title></title>");
//		out.println("</head>");
//		out.println("<body bgcolor=\"#FDF5E6\">");
//		out.println("    <form action=\"three-params\">");		//uncomment to link to three-params
//		out.println("    <form action=\"show-parameters\">");	//uncomment to link to show-parameters
//		out.println("        First Paramter: <input type=\"text\" name=\"param1\"><br>");
//		out.println("        Second Parameter: <input type=\"text\" name=\"param2\"><br>");
//		out.println("        Third Parameter: <input type=\"text\" name=\"param3\"><br>");
//		out.println("        Fourth Paramter: <input type=\"text\" name=\"paramName\"><br>");
//		out.println("        Fifth Parameter: <input type=\"text\" name=\"paramName\"><br>");
//		out.println("        Sixth Parameter: <input type=\"text\" name=\"paramName\"><br>");
//		out.println("        <center><input type=\"submit\"></center>");
//		out.println("    </form>");
//		out.println("</body>");
//		out.println("</html>");
		
//		//Form aesthetics in HTML
//		out.println("<!DOCTYPE html>");
//		out.println("<html>");
//		out.println("<head>");
//		out.println("    <title>Tags for Form Aesthetics</title>");
//		out.println("    <style>");
//		out.println("        legend {");
//		out.println("            font-weight: bold;");
//		out.println("            color: black;");
//		out.println("            background-color: white;");
//		out.println("            border: 1px solid #cccccc;");
//		out.println("            padding: 4px 2px;");
//		out.println("        }");
//		out.println("    </style>");
//		out.println("</head>");
//		out.println("<body bgcolor=\"#fdf5e6\">");
//		out.println("<fieldset>");
//		out.println("    <legend>ajax:updateField</legend>");
//		out.println("    <form>");
//		out.println("        <label for=\"f\">Enter temperature in Fahrenheit:</label>");
//		out.println("        <input type=\"text\" id=\"f\">");
//		out.println("        <input type=\"button\" id=\"convertButton\" value=\"Convert\">");
//		out.println("        <hr width=\"500\" align=\"left\"/>");
//		out.println("        <label for=\"c\">Temperature in Celcius:</label>");
//		out.println("        <input type=\"text\" id=\"c\">");
//		out.println("        <label for=\"k\">Temperature in Kelvin:</label>");
//		out.println("        <input type=\"text\" id=\"k\">");
//		out.println("    </form>");
//		out.println("</fieldset>");
//		out.println("</body>");
//		out.println("</html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
