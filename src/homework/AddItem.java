package homework;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220model.GuestBookEntry;

@WebServlet(urlPatterns={"/Store/AddItem"}, loadOnStartup = 3)
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		// Sticky form
		String name = request.getParameter("name") == null ? "" : request.getParameter("name");
		String description = request.getParameter("description") == null ? "" : request.getParameter("description");
		String quantity = request.getParameter("quantity") == null ? "" : request.getParameter("quantity");
		String price = request.getParameter("price") == null ? "" : request.getParameter("price");
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("	<!-- Latest compiled and minified CSS -->");
		out.println("	<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");			
		out.println("	<meta charset=\"UTF-8\">");
		out.println("    <title>Add Item</title>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<div class=\"container\">");
		
		out.println("<div class=\"page-header\">");
		out.println("  <h2>Add Item</h2>");
		out.println("</div>");
		
		out.println("<form action=\"AddItem\" method=\"POST\">");
		out.println("    <table class=\"table\" style=width:40%>");
		out.println("    <tr>");
//		System.out.println("DEBUG: getParameter for name = " + request.getParameter("name"));
//		System.out.println("DEBUG: getAttribute for name = " + request.getAttribute("name"));
		out.println("        <td><label for=\"name\"><b><div style=\"text-align:right\">Name*</div></b></label></td>");
		out.println("        <td><input type=\"text\" placeholder=\"Enter item name\" name=\"name\" id=\"name\" required value=\"" + name + "\"></td>");
		if (request.getAttribute("error_name") != null)
			out.println("<td><p style=\"color:red; padding-left:5px\"> Missing name.</p></td>");
		out.println("    </tr>");
		out.println("    <tr>");
		out.println("        <td><label for=\"description\"><b>Description*</b></label></td>");
		out.println("        <td><textarea rows=\"3\" cols=\"25\" placeholder=\"Enter description\" name=\"description\" id=\"description\" required>" + description + "</textarea></td>");
		if (request.getAttribute("error_description") != null)
			out.println("<td><p style=\"color:red; padding-left:5px\"> Missing description.</p></td>");
		out.println("    </tr>");
		out.println("    <tr>");
		out.println("        <td><label for=\"quantity\"><b>Quantity</b></label></td>");
		out.println("        <td><input type=\"text\" placeholder=\"Enter quantity\" name=\"quantity\" id=\"quantity\" value=\"" + quantity + "\"></td>");
		if (request.getAttribute("error_quantity") != null)
			out.println("<td><p style=\"color:red; padding-left:5px\"> Invalid quantity.</p></td>");
		out.println("    </tr>");
		out.println("    <tr>    ");
		out.println("        <td><label for=\"price\"><b>Price</b></label></td>");
		out.println("        <td><input type=\"text\" placeholder=\"Enter price\" name=\"price\" id=\"price\" value=\"" + price + "\"></td>");
		if (request.getAttribute("error_price") != null)
			out.println("<td><p style=\"color:red; padding-left:5px\"> Invalid price.</p></td>");
		out.println("    </tr>");
		out.println("    <tr>    ");
		out.println("        <td></td>");
		out.println("    <td><button type=\"submit\" class=\"btn btn-primary\">Add to Inventory</button></td>");
		out.println("    </tr>");
		out.println("    </table>");
		out.println("</form>");
		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int quantity = -999;	//initialize to impossible value b/c primitive values cannot be null
		double price = -999;	//initialize to impossible value b/c primitive values cannot be null
		
		boolean isError = false;
		
		/* ##### Form Validation #####
		 * Name and description fields cannot be empty.
		 * Quantity must be an integer.
		 * Price must be a double.
		 */
		if (name == null || name.trim().length() == 0) {
			request.setAttribute("error_name", true);
			isError = true;
		}
		if (description == null || description.trim().length() == 0) {
			request.setAttribute("error_description", true);
			isError = true;
		}
		
		try {
			quantity = Integer.parseInt(request.getParameter("quantity"));
		}
		catch (NumberFormatException e) {
			request.setAttribute("error_quantity", true);
			isError = true;
		}
		
		try {
			price = Double.parseDouble(request.getParameter("price"));
		}
		catch (NumberFormatException e) {
			request.setAttribute("error_price", true);
			isError = true;
		}
		
		// If there's an error, redisplay the form
		if (isError) {
			doGet(request, response);
			return;
		}
		else {		
			// Reference to the application scope
			ServletContext context = this.getServletContext();
			
			// Get a reference to the inventory
			List<Item> inventory = (List<Item>)context.getAttribute("inventory");
			
			// Add the new item to the inventory
			inventory.add(new Item(name, description, quantity, price));
			
			response.sendRedirect("Inventory");
		}
		
		
	}
}
