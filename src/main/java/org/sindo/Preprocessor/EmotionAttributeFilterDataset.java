/*
 * 	Program based in:
 *  How to use WEKA API in Java
 *  Copyright (C) 2014
 *  @author Dr Noureddin M. Sadawi (noureddin.sadawi@gmail.com)
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it as you wish ...
 *  I ask you only, as a professional courtesy, to cite my name, org.sindo.web page
 *  and my YouTube Channel!
 *
 */
package org.sindo.Preprocessor;

//import required classes
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.TweetToEmbeddingsFeatureVector;
import weka.filters.unsupervised.attribute.TweetToLexiconFeatureVector;

import java.io.File;
import java.io.IOException;

public class EmotionAttributeFilterDataset{
    private Instances dataset;
    private String emotion;
    private String id;

    public EmotionAttributeFilterDataset(String file, String emotion, String id) throws Exception {
        this.emotion = emotion;
        this.id = id;
        //load dataset
        DataSource source = new DataSource(file);
        this.dataset = source.getDataSet();

        //use a simple filter to remove a certain attribute
        //set up options to remove 1st attribute
        String[] opts = new String[]{ "-R", "1,3"};
        //create a Remove object (this is the filter class)
        Remove remove = new Remove();
        //set the filter options
        remove.setOptions(opts);
        //pass the dataset to the filter
        remove.setInputFormat(this.dataset);
        //apply the filter
        this.dataset = Filter.useFilter(this.dataset, remove);
    }
    
    public EmotionAttributeFilterDataset(Instances dataset) {
    	this.dataset = dataset;
    }
    
    public Instances getDataset() {
    	return dataset;
    }

    public void filterTweetToEmbeddings() throws Exception {
        //set up options to filter to feature vector
    	String[] opts = weka.core.Utils.splitOptions("weka.filters.unsupervised.attribute.TweetToEmbeddingsFeatureVector -S AVERAGE_ACTION -embeddingHandler \"affective.core.CSVEmbeddingHandler -K /Users/gustavo/Desktop/wekafiles/packages/AffectiveTweets/resources/w2v.twitter.edinburgh.100d.csv.gz -sep TAB -I last\" -K 15 -stemmer weka.core.stemmers.NullStemmer -stopwords-handler \"weka.core.stopwords.Null \" -I 1 -U -tokenizer \"weka.core.tokenizers.TweetNLPTokenizer \"");
    	
        //create a filter object (this is the filter class)
        TweetToEmbeddingsFeatureVector filter = new TweetToEmbeddingsFeatureVector();
        //set the filter options
        filter.setOptions(opts);
        //pass the dataset to the filter
        filter.setInputFormat(this.dataset);
        //apply the filter
        this.dataset = Filter.useFilter(this.dataset, filter);
    }

    public void filterTweetToLexicons() throws Exception {
        //set up options to remove 1st attribute
        String[] opts = weka.core.Utils.splitOptions("weka.filters.unsupervised.attribute.TweetToLexiconFeatureVector -F -D -R -A -T -L -N -P -J -H -Q -stemmer weka.core.stemmers.NullStemmer -stopwords-handler \"weka.core.stopwords.Null \" -I 1 -U -tokenizer \"weka.core.tokenizers.TweetNLPTokenizer \"");

        //create a filter object (this is the filter class)
        TweetToLexiconFeatureVector filter = new TweetToLexiconFeatureVector();
        //set the filter options
        filter.setOptions(opts);
        //pass the dataset to the filter
        filter.setInputFormat(this.dataset);
        //apply the filter
        this.dataset = Filter.useFilter(this.dataset, filter);
    }

    public void saveDatasetFile() throws IOException {
        //now save the dataset to a new file
        ArffSaver saver = new ArffSaver();
        saver.setInstances(this.dataset);
        saver.setFile(new File("wekafiles/filtered/" + emotion + "_" + id + ".arff"));
        saver.writeBatch();
    }
}
