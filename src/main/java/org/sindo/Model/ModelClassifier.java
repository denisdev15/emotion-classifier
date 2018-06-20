package org.sindo.Model;

import java.io.File;

import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instance;
import weka.core.Instances;

/**
 * This is a classifier for Emotions
 */
public class ModelClassifier {
	Instance comment;
	
	private FilteredClassifier angerModel;
	private FilteredClassifier fearModel;
	private FilteredClassifier joyModel;
	private FilteredClassifier sadnessModel;
	
	public ModelClassifier(Instances dataset) throws Exception {
		this.comment = dataset.instance(0);
		this.angerModel = (FilteredClassifier) weka.core.SerializationHelper.read(new File("wekafiles/models/anger-model.model").getAbsolutePath());
		this.fearModel = (FilteredClassifier) weka.core.SerializationHelper.read(new File("wekafiles/models/fear-model.model").getAbsolutePath());
		this.joyModel = (FilteredClassifier) weka.core.SerializationHelper.read(new File("wekafiles/models/joy-model.model").getAbsolutePath());
		this.sadnessModel = (FilteredClassifier) weka.core.SerializationHelper.read(new File("wekafiles/models/sadness-model.model").getAbsolutePath());
 			
	}
	
	public double classifyAnger() throws Exception {
		double pred = angerModel.classifyInstance(comment);
		return pred;
	}
	
	public double classifyFear() throws Exception {
		double pred = fearModel.classifyInstance(comment);
		return pred;
	}
	
	public double classifyJoy() throws Exception {
		double pred = joyModel.classifyInstance(comment);
		return pred;
	}
	
	public double classifySadness() throws Exception {
		double pred = sadnessModel.classifyInstance(comment);
		return pred;
	}
        

}