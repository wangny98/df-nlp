package com.ineuron.domain.nlp.annotator.annotation;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.util.ErasureUtils;

import java.util.List;

/**
 * Annotation for the statements of a sentence.
 */
public class QuantityAnnotation implements CoreAnnotation<List<String>> {
    public Class<List<String>> getType() {
        return ErasureUtils.<Class<List<String>>>uncheckedCast(String.class);
    }
}
