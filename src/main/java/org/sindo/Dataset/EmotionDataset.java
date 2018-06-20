package org.sindo.Dataset;

import java.util.ArrayList;
import java.util.List;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class EmotionDataset {
	private Attribute tweet;
	private Attribute score;
	private ArrayList<Attribute> attributes;
	private Instances dataRaw;
	
	public EmotionDataset(String emotion) {
		tweet = new Attribute("tweet", (List<String>) null);
	    score = new Attribute("score");
	    attributes = new ArrayList<Attribute>();
	    attributes.add(tweet);
	    attributes.add(score);
	    dataRaw = new Instances(emotion + "_Instances", attributes, 0);
	    dataRaw.setClassIndex(dataRaw.numAttributes() - 1);
	}
	
	
    public Instances addInstance(String comment) {
		dataRaw.clear();
		Instance inst = new DenseInstance(2);
		inst.setValue(tweet, comment);
        dataRaw.add(inst);
        return dataRaw;
	}

}
