package homework;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/Store/AllUsers"}, loadOnStartup = 1)
public class AllUsers extends HttpServlet {
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		out.println("<!doctype html><html lang=\"en-us\"><head><title>List of Users</title></head><body>");
		
		out.println("<form action='AllUsers' method='get'><input type='text' name='query' /><input type='submit' value='search' /></form>");
		
		out.println("<table border=1><tr><th>Id</th><th>Name</th><th>E-mail</th><th>Password</th></tr>");
		
		// Get a reference to our users
		List<User> users = (List<User>) getServletContext().getAttribute("users");
		
		for(User user : users){
			
			String query = request.getParameter("query");
			
			if (query == null || user.getEmail().contains( query.trim() ) ){
				
				out.println("<tr>");
				out.println("  <td>" + user.getId() + "</td>");
				out.println("  <td>" + user.getFirstName() + " " + user.getLastName() + "</td>");
				out.println("  <td>" + user.getEmail() + "</td>");
				out.println("  <td>" + user.getPassword() + "</td>");
				out.println("</tr>");
			}
		}
		
		out.println("</table>");
		out.println("<a href='AddUser'>Add a User</a>");
		out.println("</body></html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}