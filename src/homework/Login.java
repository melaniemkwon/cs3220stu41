package homework;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns={"/Store/Login"}, loadOnStartup=1)
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// Create the collection in the servlet context to be used by all other servlets
		List<User> users = new ArrayList<User>();
		
		// Pre-Populate your users database with the following accounts
		users.add(new User("John", "Doe", "john@doe.com", "aa"));
		users.add(new User("Mary", "Jane", "mary@jane.com", "bb"));
		users.add(new User("Joe", "Boxer", "joe@boxer.com", "cc"));
		
		// Add the users list to the global scope
		getServletContext().setAttribute("users", users);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		// If User has visited page, pre-populate the e-mail field if the username cookie exists
		String email = getCookie(request, "username") == null ? "" : getCookie(request, "username");
		System.out.println("DEBUG1: cookie = " + getCookie(request, "username"));
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("    <!-- Latest compiled and minified CSS -->");
		out.println("    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");
		out.println("    <title>Login</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");

		out.println("    <div class=\"page-header\">");
		out.println("      <h1>Login</h1>");
		out.println("    </div>");

		if (request.getAttribute("loginError") != null)
			out.println("<p style=\"color:red\">Invalid Username and/or Password.</p>");
		
		out.println("    <form action=\"Login\" method=\"POST\">");
		out.println("        <input type=\"text\" name=\"email\" placeholder=\"Enter email\" value=\"" + email + "\">");
		out.println("        <input type=\"password\" name=\"password\" placeholder=\"Enter password\"><br>");
		out.println("        <input type=\"checkbox\" name=\"rememberMe\"> Remember me <br><br>");
		out.println("        <input type=\"submit\">");
		out.println("    </form>");
		out.println("</div> <!-- div container -->");
		out.println("</body>");
		out.println("</html>");		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Reference to the users list
		List<User> users = (List<User>)getServletContext().getAttribute("users");
		
		boolean isError = false;
		
		// Validate credentials by finding the User object in the users 
		// collection stored in the Servlet Context that matches the submitted fields
		for (User user : users) {
			if (user.getEmail().equals(request.getParameter("email")) 
				&& user.getPassword().equals(request.getParameter("password"))) {		
				
				// TODO: If the rememberMe is checked, store email in cookie			
				if (request.getParameter("rememberMe") != null ) {									
					// Read the username/email from the cookie (if it exists)
					String username = getCookie(request, "username"); 	
					
					// If it doesn't exist, create and add cookie
					if (username == null) { 					
						username = request.getParameter("email");																	
						response.addCookie(new Cookie("username", username));
						System.out.println("DEBUG2: cookie = " + getCookie(request, "username"));
					}
				}
				else {
					expireCookie(request, response, "username");
					System.out.println("DEBUG3: Cookie username should be expired.");
				}
			
				// TODO: Create a Session attribute named CurrentUser, and store the User object 
				// that corresponds to the credentials that were submitted.
//				HttpSession session = request.getSession();
//				
//				User user = (String) session.getAttribute("");
//				
//				// If attribute doesn't exist, read from the request object and add to the session
//				if (name == null){
//
//					name = request.getParameter("name");
//					
//					session.setAttribute("CurrentUser", name);
//				}
				
				response.sendRedirect("Inventory");
			}
			else {
				isError = true;
				request.setAttribute("loginError", true);
				doGet(request, response);
				return;
			}
		}
	}
	
	private String getCookie(HttpServletRequest request, String name){		
		Cookie[] cookies = request.getCookies();
		
		if ( cookies != null ) {
			for (Cookie cookie : cookies) {
				if ( cookie.getName().equals(name) )
					return cookie.getValue();	
			}
		}
		
		return null;
	}
	
	private void expireCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ( cookie.getName().equals(name) ) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
					
	}

}
