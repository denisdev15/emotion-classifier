package Service;

import java.io.StringWriter;
import org.json.simple.JSONObject;

public class TestRun {

	public static void main(String[] args) throws Exception {
		String comment = "";
		
		JSONObject response = ClassifierService.classify(comment);
		
		StringWriter out = new StringWriter();
		response.writeJSONString(out);
  
		String jsonText = out.toString();
		System.out.print(jsonText);
	}

}
