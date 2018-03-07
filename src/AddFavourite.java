import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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



@WebServlet("/favorite")
public class AddFavourite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//array of json objects
    public AddFavourite() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//defining an object,array and a parser
		JSONObject jsonObj = new JSONObject();
		JSONArray jarray = new JSONArray();
		JSONParser parser = new JSONParser();
		//setting response type
		response.setContentType("application/json");
		String city = request.getParameter("city");
		PrintWriter out = response.getWriter();
		//shows the city added
		String country = request.getParameter("country");
		String temperature = request.getParameter("temperature");
		String conditions = request.getParameter("conditions");
		String description = request.getParameter("description");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		//storing key value pairs getting from GET
		jsonObj.put("city", city);
		jsonObj.put("country", country);
		jsonObj.put("temperature", temperature);
		jsonObj.put("conditions", conditions);
		jsonObj.put("description", description);
		jsonObj.put("longitude", longitude);
		jsonObj.put("latitude", latitude);
		String PATH = "/home/sapient/Documents/favorites.json";
		
		File f = new File(PATH);
		if(f.exists()) {
			//wont happen ever because we checked the conditions already above
			try {
				//if array exists take the values else make a new file
				jarray = (JSONArray)parser.parse(new FileReader(PATH));
			} catch (ParseException e) {
				//wont reach here ever
			}
		}
		
		//if length exceeds 10, warning message is issued
		if(jarray.size() < 10) {
			out.print("<hr><hr><center><p id = \"message\">"+city+" has been added to your favorite locations!!</p></center><hr><hr>");
			jarray.add(jsonObj);
			FileWriter jsonFile=null;
			try {
				//over writing the previous file
				jsonFile =  new FileWriter(PATH);
				jsonFile.write(jarray.toString());
				System.out.println(jsonObj.toString());
			}catch(Exception e){
				System.out.println("Please enter a valid path where you want to store your json");
			}finally {
				jsonFile.flush();
			}
		}
		
		else {
			out.print("<hr><hr><center><p id = \"redMessage\"> Your Bucket List is full!!</p></center><hr><hr>");
			System.out.println("You have exceeded your limit");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
