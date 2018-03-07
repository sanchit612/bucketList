import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@WebServlet("/viewSaved")
public class ViewSaved extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewSaved() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
    	JSONParser parser = new JSONParser(); 
    	JSONObject obj = new JSONObject();
    	JSONArray arr = new JSONArray();
    	String PATH = "/home/sapient/Documents/favorites.json";
    	try {
    		//reading values from the file
				arr = (JSONArray)parser.parse(new FileReader(PATH));
				
				if(arr.size() == 0)
					out.print("<center><p id = \"redMessage\"> You have not added any favourites!!</p></center><hr><hr>");
				else {
					String cityName,countryName,temp,conditions,description,latitude,longitude;
					out.println("<table class = \"table\">");
					for(int i=0;i<arr.size();i++) {
						obj=(JSONObject) arr.get(i);
						cityName = (String)obj.get("city");
						countryName = (String)obj.get("country");
						temp = (String)obj.get("temperature");
						conditions = (String)obj.get("conditions");
						description = (String)obj.get("description");
						latitude = (String)obj.get("latitude");
						longitude = (String)obj.get("longitude");
						// sending the index of the object to be deleted	
						out.println(
								"<tr><td class = \"tableData\">"+
								(i+1)+"</td><td class = \"tableData\">"+
								
					"<div id = \"card\" class=\"card\">"
					+ "<div class=\"card-body\">"
					+ "<h4 class=\"card-title\">"
					+ cityName
					+ ", "
					+ countryName
					+ "</h5> <hr>"
					+ "<center><table class = \"tablei\"><tr><td class = \"tableDatai\"><b>Temperature</b></td><td class = \"tableDatai\">"
					+ temp
					+ "</td></tr><tr><td class = \"tableDatai\"><b>Conditions</b></td><td class = \"tableDatai\">"
					+ conditions
					+ "</td></tr><tr><td class = \"tableDatai\"><b>Description</b></td><td class = \"tableDatai\">"
					+ description
					+ "</td></tr><tr><td class = \"tableDatai\"><b>Longitude</b></td><td class = \"tableDatai\">"
					+ longitude
					+ "</td></tr><tr><td class = \"tableDatai\"><b>Latitude</b></td><td class = \"tableDatai\">"
					+ latitude
					+ "</td></tr></table></center><hr>"
					+ "</div>" + "</div>"											
					+"</td><td class = \"tableData\"><button type = \"button\" onclick = \"removeFav("+i+")\" id = \"remove_button\" class = \"btn btn-outline-danger\">Remove</button></td></tr>"
								);
		    	}
					out.println("</table>");
				}
			} catch (ParseException e) {
					System.out.println("File Not Found");
			}
    	
    

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
