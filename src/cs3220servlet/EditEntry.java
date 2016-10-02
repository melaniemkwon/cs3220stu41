package cs3220servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220model.GuestBookEntry;

@WebServlet("/requests/EditEntry")
public class EditEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get the id of the entry we want to edit
		int id = Integer.parseInt( request.getParameter("id") );
		
		// Get a reference to the entry
		List<GuestBookEntry> entries = (List<GuestBookEntry>) getServletContext().getAttribute("entries");
		
		GuestBookEntry entry = null; // Defined here for scope
		
		for (GuestBookEntry e : entries){
			if (e.getId() == id){
				entry = e;
				break;
			}
		}
		
		if (entry == null){
			response.sendRedirect("GuestBook");
			return;
		}
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("	<!-- Latest compiled and minified CSS -->");
		out.println("	<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");			
		out.println("	<meta charset=\"UTF-8\">");
		out.println("	<title>Insert title here</title>");
		out.println("</head>");
		out.println("<body>");
		
		
		out.println("<form action=\"EditEntry\" method=\"post\">");
		out.println("  Name: <input type=\"text\" value=\"" + entry.getName() + "\" name=\"name\" /> <br />");
		out.println("  Message: <input type=\"text\" value=\"" + entry.getMessage() + "\" name=\"message\" /> <br />");
		out.println("  <input type=\"hidden\" name=\"id\" value=\"" + entry.getId() + "\" />");
		out.println("  <input type=\"submit\" value=\"Save\" />");
		out.println("</form>");
		
		out.println("</body>");
		out.println("</html>");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String message = request.getParameter("message");
				
		// Get a reference to the entry
		List<GuestBookEntry> entries = (List<GuestBookEntry>) getServletContext().getAttribute("entries");
		
		for (GuestBookEntry entry : entries){
			if (entry.getId() == id){
				// Update the entry
				entry.setName(name);
				entry.setMessage(message);
				break;
			}
		}
		
		response.sendRedirect("GuestBook");
	}

}

