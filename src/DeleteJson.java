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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@WebServlet("/deletejson")
public class DeleteJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteJson() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String value = request.getParameter("value");
		
		//index to be deleted
		int index = Integer.parseInt(value);
		JSONParser parser = new JSONParser(); 
  	JSONArray array = new JSONArray();
  	//reading the json file
  	FileWriter jsonFile = null;
		try {
			array = (JSONArray)parser.parse(new FileReader("/home/sapient/Documents/favorites.json"));
			array.remove(index);
			out.print("Successfully removed");
			//writing the array to the same file
			jsonFile =  new FileWriter("/home/sapient/Documents/favorites.json");
			jsonFile.write(array.toString());
			
		} catch (ParseException e) {
			System.out.println("Your file could not be found");
		}finally {
			jsonFile.flush();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
