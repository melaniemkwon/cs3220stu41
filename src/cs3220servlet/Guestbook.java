package cs3220servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220model.GuestBookEntry;


@WebServlet("/requests/GuestBook")
public class Guestbook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// Create a local list of guest book entries
		List<GuestBookEntry> entries = new ArrayList<GuestBookEntry>();
		
		// Pre-populate the guestbook with a few entries
		entries.add(new GuestBookEntry("John Doe", "Hello"));
		entries.add(new GuestBookEntry("Mary", "Hi"));
		entries.add(new GuestBookEntry("Joe", "Howdy"));
		
		// Add the list to the global scope for use later
		getServletContext().setAttribute("entries", entries);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get a reference to the application scope
		ServletContext context = this.getServletContext();
		
		// Get a reference to the guestbook
		List<GuestBookEntry> entries = (List<GuestBookEntry>) context.getAttribute("entries");
		
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("	<!-- Latest compiled and minified CSS -->");
		out.println("	<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");			
		out.println("	<meta charset=\"UTF-8\">");
		out.println("	<title>Guest Book</title>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<div class=\"container\">");
		
		out.println("<div class=\"page-header\">");
		out.println("  <h1>Guest Book <small>CS3220</small></h1>");
		out.println("</div>");
		
		out.println("<table class=\"table table-striped table-bordered table-hover\">");
		out.println("  <tr>");
		out.println("    <th>Name</th>");
		out.println("    <th>Message</th>");
		out.println("    <th>Date</th>");		
		out.println("    <th>Actions</th>");
		out.println("  </tr>");
		
		// Print one table row <tr> for every entry in our guest book.
		
		for ( GuestBookEntry entry : entries){
			out.println("<tr>");
			out.println("  <td>" + entry.getName() + "</td>");
			out.println("  <td>" + entry.getMessage() + "</td>");
			out.println("  <td>" + entry.getCreated() + "</td>");
			out.println("  <td>");
			out.println("    <a href=\"EditEntry?id=" + entry.getId() + "\">Edit</a> | ");
			out.println("    <a href=\"DeleteEntry?id=" + entry.getId() + "\">Delete</a>");
			out.println(" </td>");
			out.println("</tr>");
			
		}

		out.println("</table>");
		
		out.println("<a class=\"btn btn-primary\" href=\"AddEntry\">Add Entry</a>");
		out.println("<a class=\"btn btn-primary\" href=\"AddEntryWithCookie\">Add Entry (with Cookie)</a>");
		out.println("<a class=\"btn btn-primary\" href=\"AddEntryWithSessions\">Add Entry (with Sessions)</a>");
		
	
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}