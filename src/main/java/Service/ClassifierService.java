package Service;

import Dataset.EmotionDataset;
import org.json.simple.JSONObject;
import Model.ModelClassifier;
import Preprocessor.EmotionAttributeFilterDataset;
import weka.core.Instances;

public class ClassifierService {
	
	public static JSONObject classify(String comment) throws Exception {
		//Create dataset
		EmotionDataset emoDataset = new EmotionDataset("anger");
		Instances dataset = emoDataset.addInstance(comment);
		
		//Filter
		EmotionAttributeFilterDataset filter = new EmotionAttributeFilterDataset(dataset);
		filter.filterTweetToEmbeddings();
		filter.filterTweetToLexicons();
		dataset = filter.getDataset();
		
		// Classify anger
		ModelClassifier classifier = new ModelClassifier(dataset);
		double scoreAnger = classifier.classifyAnger();
		return createResponse(scoreAnger);
	}
	
	private static JSONObject createResponse(double anger) {
		JSONObject obj = new JSONObject();
		obj.put("name", "anger");
		obj.put("emotionIntensity", anger);
	    return obj;  
	}

}
