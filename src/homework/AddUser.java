package homework;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/Store/AddUser"})
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		out.println("<!doctype html><html lang=\"en-us\"><head><title>Regitration Form</title></head><body>");
		out.println("<form action='AddUser' method='post'>");
		
		// If the 'fullName' parameter exists, let's use it as the default value
		// Otherwise, use the empty string
		String fullName = request.getParameter("fullName") == null || request.getAttribute("nameError") != null 
						? "" 
						: request.getParameter("fullName");
		
		out.println("<input type='text' name='fullName' value='" + fullName + "' placeholder='Enter your name' /> <br />");
		
		// Do we display an error message?
		if (request.getAttribute("nameError") != null){
			out.println("<p style='color:#f00;'>Please enter your full name</p>");
		}
		
		
		
		// If the 'fullName' parameter exists, let's use it as the default value
		// Otherwise, use the empty string
		String email = request.getParameter("email") == null || request.getAttribute("email") != null 
						? "" 
						: request.getParameter("email");
		out.println("<input type='text' name='email' value='" + email + "' placeholder='Enter your email' /> <br />");
		// Do we display an error message?
		if (request.getAttribute("emailError") != null)
			out.println("<p style='color:#f00;'>Please enter a valid Email Address</p>");
		
		
		out.println("<input type='password' name='password1' placeholder='Enter a password' /> <br />");
		out.println("<input type='password' name='password2' placeholder='Enter the password again' /> <br />");
		out.println("<input type='submit' value='Add User' />");
		out.println("</form>");
		out.println("</body></html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean hasError = false; // Assume that we start with no errors
		
		// Validate the name
		String fullName = request.getParameter("fullName");
		String firstName = null;
		String lastName = null;
		
		if (fullName != null){
			
			// Tokenize the fullName
			String[] tokens = fullName.trim().split(" ");
			
			// Did the User submit at least two names?
			if (tokens.length < 2){
				hasError = true;
				request.setAttribute("nameError", true);				
			}		
			else{
				firstName = tokens[0];
				lastName = tokens[1];
			}
		} 
		else{
			hasError = true;
			request.setAttribute("nameError", true);
		}
		
		// Validate the e-mail
		String email = request.getParameter("email");
		
		if (email == null || email.trim().length() == 0){
			hasError = true;
			request.setAttribute("emailError", true);			
		}
		
		// Validate password
		String pw1 = request.getParameter("password1");
		String pw2 = request.getParameter("password2");
			
		if (pw1 == null || pw2 == null || pw1.trim().length() == 0 || !pw1.equals(pw2)){
			hasError = true;
			request.setAttribute("passwordError", true);
		}
				
		// Redisplay the form if we have errors
		if (hasError){
			doGet(request, response);
			return;
		}
		else{
			// Cool, let's add a new user
			List<User> users = (List<User>) getServletContext().getAttribute("users");
			users.add(new User(firstName, lastName, email, pw1));
			response.sendRedirect("AllUsers");
		}
		
	}
	
	
	
	

}