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

@WebServlet("/deleteValue")
public class RemoveFavourite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveFavourite() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String value = request.getParameter("value");
		
		//index to be deleted
		int index = Integer.parseInt(value);
		JSONParser parser = new JSONParser(); 
  	JSONArray array = new JSONArray();
  	String PATH = "/home/sapient/Documents/favorites.json";
  	//reading the json file
  	FileWriter jsonFile = null;
		try {
			array = (JSONArray)parser.parse(new FileReader(PATH));
			array.remove(index);
			out.print("<center><p id = \"message\"> Successfully removed the city!!</p></center><hr><hr>");
			//writing the array to the same file
			jsonFile =  new FileWriter(PATH);
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
