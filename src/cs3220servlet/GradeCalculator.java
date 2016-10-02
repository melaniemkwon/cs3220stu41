package cs3220servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Labs/GradeCalculator")
public class GradeCalculator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
		response.setContentType("text/html");		
		PrintWriter out = response.getWriter();
		
		// Read fields if they exist
		String possible_attendance = request.getParameter("possible_attendance") == null ? "100" : request.getParameter("possible_attendance");
		String possible_hwlabs = request.getParameter("possible_hwlabs") == null ? "200" : request.getParameter("possible_hwlabs");
		String possible_quizes = request.getParameter("possible_quizes") == null ? "50" : request.getParameter("possible_quizes");
		String possible_midterm = request.getParameter("possible_midterm") == null ? "100" : request.getParameter("possible_midterm");
		String possible_final = request.getParameter("possible_final") == null ? "100" : request.getParameter("possible_final");
		
		String my_attendance = request.getParameter("my_attendance") == null ? "" : request.getParameter("my_attendance");
		String my_hwlabs = request.getParameter("my_hwlabs") == null ? "" : request.getParameter("my_hwlabs");
		String my_quizes = request.getParameter("my_quizes") == null ? "" : request.getParameter("my_quizes");
		String my_midterm = request.getParameter("my_midterm") == null ? "" : request.getParameter("my_midterm");
		String my_final = request.getParameter("my_final") == null ? "" : request.getParameter("my_final");
	                				
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("   <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");
		out.println("    <title>Grade Calculator</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("    <table class=\"table table-bordered\" style=width:50%>");
		out.println("    <form action=\"GradeCalculator\" method=\"post\">");
		out.println("        <tr>");
		out.println("            <th></th>");
		out.println("            <th>Possible</th>");
		out.println("            <th>Actual</th>  ");
		out.println("            <th>Grade</th>  ");
		out.println("        </tr>");		
		
		/*		
		 * 		Row for Attendance
		 */
		out.println("        <tr>");
		out.println("            <td>Attendance: </td>");
//		System.out.println("DEBUG: possible_attendance doGet: " + my_attendance);
		out.println("            <td><input type=\"number\" name=\"possible_attendance\" value=\"" + possible_attendance + "\" /></td>");
		out.println("            <td><input type=\"number\" name=\"my_attendance\" value=\"" + my_attendance + "\" /></td>");
		if (request.getAttribute("percent_attendance") == null)
			out.println("		 <td> - </td>");
		else
			out.println("        <td>" + String.format("%.2f", request.getAttribute("percent_attendance")) + "  / 5%</td>");
		out.println("        </tr>");
		
		/*		
		 * 		Row for HW & Labs
		 */		
		out.println("        <tr>");
		out.println("            <td>HW & Labs: </td>");
		out.println("            <td><input type=\"number\" name=\"possible_hwlabs\" value=\"" + possible_hwlabs + "\" /></td>");
		out.println("            <td><input type=\"number\" name=\"my_hwlabs\" value=\"" + my_hwlabs + "\" /></td>");
		if (request.getAttribute("percent_hwlabs") == null)
			out.println("		 <td> - </td>");
		else
			out.println("        <td>" + String.format("%.2f", request.getAttribute("percent_hwlabs")) + " / 20%</td>");
		out.println("        </tr>");
		
		/*		
		 * 		Row for Quizzes
		 */	
		out.println("        <tr>");
		out.println("            <td>Quizzes: </td>");
		out.println("            <td><input type=\"number\" name=\"possible_quizes\" value=\"" + possible_quizes + "\" /></td>");
		out.println("            <td><input type=\"number\" name=\"my_quizes\" value=\"" + my_quizes + "\" /></td>");
		if (request.getAttribute("percent_quizes") == null)
			out.println("		 <td> - </td>");
		else
			out.println("        <td>" + String.format("%.2f", request.getAttribute("percent_quizes")) + " / 25%</td>");
		out.println("        </tr>");
		
		/*		
		 * 		Row for Midterm
		 */	
		out.println("        <tr>");
		out.println("            <td>Midterm: </td>");
		out.println("            <td><input type=\"number\" name=\"possible_midterm\" value=\"" + possible_midterm + "\"/></td>");
		out.println("            <td><input type=\"number\" name=\"my_midterm\" value=\"" + my_midterm + "\" /></td>");
		if (request.getAttribute("percent_midterm") == null)
			out.println("		 <td> - </td>");
		else
			out.println("        <td>" + String.format("%.2f", request.getAttribute("percent_midterm")) + " / 25%</td>");
		out.println("        </tr>");
		
		/*		
		 * 		Row for Final
		 */	
		out.println("        <tr>");
		out.println("            <td>Final: </td>");
		out.println("        <td><input type=\"number\" name=\"possible_final\" value=\"" + possible_final + "\" /></td>");
		out.println("            <td><input type=\"number\" name=\"my_final\" value=\"" + my_final + "\" /></td>");
		if (request.getAttribute("percent_final") == null)
			out.println("		 <td> - </td>");
		else
			out.println("        <td>" + String.format("%.2f", request.getAttribute("percent_final")) + " / 25%</td>");
		out.println("        </tr>");
		
		/*		
		 * 		Calculate
		 */	
		out.println("        <tr>");
		out.println("            <td><input type=\"submit\" value=\"Calculate\" /></td>");
		out.println("        	 <td></td>");
		out.println("            <td></td>");
		System.out.println("my_grade doGet: " + request.getParameter("my_grade")); //debug
		if (request.getAttribute("my_grade") != null)
			out.println("            <td>" + String.format("%.2f", (double)request.getAttribute("my_grade")) + "/100%" + "</td>");			
		else
			out.println("            <td> - </td>");
		out.println("        </tr>");
		out.println("    </form>");
		out.println("    </table>");
		out.println("</body>");
		out.println("</html>");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Double possible_attendance = Double.parseDouble(request.getParameter("possible_attendance"));
		Double possible_hwlabs = Double.parseDouble(request.getParameter("possible_hwlabs"));
		Double possible_quizes = Double.parseDouble(request.getParameter("possible_quizes"));
		Double possible_midterm = Double.parseDouble(request.getParameter("possible_midterm"));
		Double possible_final = Double.parseDouble(request.getParameter("possible_final"));
		
		Double my_attendance = Double.parseDouble(request.getParameter("my_attendance"));
		Double my_hwlabs = Double.parseDouble(request.getParameter("my_hwlabs"));
		Double my_quizes = Double.parseDouble(request.getParameter("my_quizes"));
		Double my_midterm = Double.parseDouble(request.getParameter("my_midterm"));
		Double my_final = Double.parseDouble(request.getParameter("my_final"));
		
		request.setAttribute("percent_attendance", (my_attendance / possible_attendance) * 5);
		request.setAttribute("percent_hwlabs", (my_hwlabs / possible_hwlabs) * 20);
		request.setAttribute("percent_quizes", (my_quizes / possible_quizes) * 25);
		request.setAttribute("percent_midterm", (my_midterm / possible_midterm) * 25);
		request.setAttribute("percent_final", (my_final / possible_final) * 25);
		
		double my_grade = ((double)request.getAttribute("percent_attendance") 
			+ (double)request.getAttribute("percent_hwlabs") 
			+ (double)request.getAttribute("percent_quizes") 
			+ (double)request.getAttribute("percent_midterm") 
			+ (double)request.getAttribute("percent_final"));
		request.setAttribute("my_grade", my_grade);
		
		doGet(request,response);
		
	}

}
