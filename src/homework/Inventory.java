package homework;

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

@WebServlet(urlPatterns={"/Store/Inventory"}, loadOnStartup = 2)
public class Inventory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        ArrayList<Item> inventory = new ArrayList<Item>();
        
        // Pre-populate table with items
        inventory.add(new Item("Fallout 4", "Post-apocalyptic shooter for PS4", 1, 59.99));
        inventory.add(new Item("EGF Serum", "Aloe Leaf extract for skin care", 3, 39.95));
        inventory.add(new Item("Introduction to Statistics", "An introduction to descriptive statistics using R", 1, 45.00));
        
        // Add inventory list to global scope
        getServletContext().setAttribute("inventory", inventory);
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Reference to the application scope
		ServletContext context = this.getServletContext();
		
		// Reference to the inventory
		List<Item> inventory = (List<Item>)context.getAttribute("inventory");
		
		// If item id was passed to Inventory servlet, delete the item from inventory list
		if (request.getParameter("id") != null)
			deleteItem(request.getParameter("id"), inventory);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("	<!-- Latest compiled and minified CSS -->");
		out.println("	<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");			
		out.println("	<meta charset=\"UTF-8\">");
		out.println("	<title>Poor Man's Amazon</title>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<div class=\"container\">");
		
		out.println("<div class=\"page-header\">");
		out.println("  <h1>Inventory</h1>");
		out.println("</div>");
		
		// Search form
		out.println("<form action=\"Inventory\" method=\"GET\">");
		out.println("    <input type=\"text\" name=\"query\" placeholder=\"Search\">");
		out.println("    <input type=\"submit\" value=\"search\">");
		out.println("</form>");
		
			out.println("<table class=\"table table-striped table-bordered table-hover\">");
			out.println("  <tr>");
			out.println("    <th>Name</th>");
			out.println("    <th>Description</th>");
			out.println("    <th>Quantity</th>");		
			out.println("    <th>Price</th>");
			out.println("    <th>Actions</th>");
			out.println("  </tr>");
			
			if (inventory.isEmpty())
				out.println("<h3>There are no items in your inventory.</h3>");
			
			for (Item i : inventory){
				
				// If there's a search query, check whether the name or description contains the query
				String query = request.getParameter("query");				
				if (query == null || 
						i.getName().toLowerCase().contains(query.trim().toLowerCase()) ||  
						i.getDescription().toLowerCase().contains(query.trim().toLowerCase())) {
					out.println("<tr>");
					out.println("  <td>" + i.getName() + "</td>");
					out.println("  <td>" + i.getDescription() + "</td>");
					out.println("  <td>" + i.getQuantity() + "</td>");
					out.println("  <td>$" + String.format("%.2f", i.getPrice()) + "</td>");
					out.println("  <td>");
					out.println("    <a href=\"Inventory?id=" + i.getId() + "\">Delete</a>"); //pass Id to deleteItem method
					out.println(" </td>");
					out.println("</tr>");
				}
			}		
			out.println("</table>");

		out.println("<a class=\"btn btn-primary\" href=\"AddItem\">Add Item</a>");		
	
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}

	// ##### Delete method #####
	private void deleteItem(String item_id, List<Item> inventory) {
		int id = Integer.parseInt(item_id);
		
		for (Item i : inventory ) {
			if (i.getId() == id) {
				inventory.remove(i);
				break;
			}
		}	
	}
}
