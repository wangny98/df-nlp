package com.ineuron.domain.nlp.annotator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ineuron.domain.nlp.annotator.annotation.QuantityAnnotation;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.util.CoreMap;

public class NERQuantityAnnotator implements Annotator{
	
	Pattern pattern = Pattern.compile("\\d+L");
	
	public NERQuantityAnnotator(String name, Properties properties) {
        String prefix = (name != null && !name.isEmpty())? name + ".":"";
        //String path = properties.getProperty(prefix + "model");
        System.out.println("prefix = " + prefix);
    }

	public void annotate(Annotation annotation) {
		Set<String> quantities = new HashSet<String>();
		CoreMap sentence = annotation.get(CoreAnnotations.SentencesAnnotation.class).get(0);
		for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {  
            // this is the text of the token  
            String word = token.get(CoreAnnotations.TextAnnotation.class);
            // this is the POS tag of the token  
            String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            
            if(pos.equals("CD") ){
            	Matcher matcher = pattern.matcher(word);
            	if(matcher.matches()){
            		String[] result = splitStr(word);
            		quantities.add(result[0]);
            		quantities.add(result[1]);  
            	}
            	         	
            }
            System.out.println(word+"\t"+pos);  
            
        }  
		sentence.set(QuantityAnnotation.class, quantities);      
		
	}

	public Set<Class<? extends CoreAnnotation>> requirementsSatisfied() {
		return Collections.emptySet();
	}

	public Set<Class<? extends CoreAnnotation>> requires() {
		return Collections.emptySet();
	}

	private static String[] splitStr(String str){
		StringBuilder number = new StringBuilder();
		StringBuilder unit = new StringBuilder();
		for (int i = str.length();--i>=0;){
			if (Character.isDigit(str.charAt(i))){
				number.append(str.charAt(i));
			}else{
				unit.append(str.charAt(i));
			}
		}
		String[] result = {number.toString(), unit.toString()};
		return result;
		
	}
	 

}
