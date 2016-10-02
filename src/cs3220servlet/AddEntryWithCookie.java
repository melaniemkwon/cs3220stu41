package cs3220servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220model.GuestBookEntry;

/**
 * Servlet implementation class AddEntryWithCookie
 */
@WebServlet("/requests/AddEntryWithCookie")
public class AddEntryWithCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String getUsersName( HttpServletRequest request ){
		
		Cookie[] cookies = request.getCookies();
		
		// Are there any cookies?
		if ( cookies != null )
			for (Cookie cookie : cookies)
				if ( cookie.getName().equals("name") )
					return cookie.getValue();
			
		
		return null;
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		out.println("<h1>Add Entry with Cookie</h1>");
		
		out.println("<form action=\"AddEntryWithCookie\" method=\"post\">");
		
		String name = getUsersName(request);
		
		if (name == null)
			out.println("  Name: <input type=\"text\" name=\"name\" /> <br />");
		else
			out.println("  Name: <strong>" + name +"</strong> <br />");
		
		out.println("  Message: <input type=\"text\" name=\"message\" /> <br />");
		out.println("  <input type=\"submit\" />");
		out.println("</form>");
		
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Read the name from the cookie (if it exists)
		String name = getUsersName(request);
		
		// Did the cookie exist?
		if (name == null){
			
			// Read the name from the request object
			name = request.getParameter("name");
			
			// Create the 'usersname' cookie
			Cookie cookie = new Cookie("name", name);
			
			// Send the cookie back to the browser
			response.addCookie( cookie );
		}
		
		
		
		String message = request.getParameter("message");
		
		if (name != null && name.trim().length() > 0 &&
			message != null && message.trim().length() > 0){
		
			// Get a reference to the guest book
			List<GuestBookEntry> entries = (List<GuestBookEntry>) getServletContext().getAttribute("entries");
			
			// Add a new entry
			entries.add(new GuestBookEntry(name, message));
		}
		
		// Send the User back to the Guest Book page
		response.sendRedirect("GuestBook");
	}

}