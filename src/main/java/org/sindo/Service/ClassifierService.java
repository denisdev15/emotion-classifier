package org.sindo.Service;

import org.sindo.Dataset.EmotionDataset;
import org.json.simple.JSONObject;
import org.sindo.Model.ModelClassifier;
import org.sindo.Preprocessor.EmotionAttributeFilterDataset;
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
		double scoreFear = classifier.classifyFear();
		double scoreJoy = classifier.classifyJoy();		
		double scoreSadness = classifier.classifySadness();
		return createResponse(scoreAnger, scoreFear, scoreJoy, scoreSadness);
	}
	
	@SuppressWarnings("unchecked")
	private static JSONObject createResponse(double anger, double fear, double joy, double sadness) {
		JSONObject obj = new JSONObject();		
		obj.put("anger", anger);
		obj.put("fear", fear);
		obj.put("joy", joy);
		obj.put("sadness", sadness);
	    return obj;
	}

}
