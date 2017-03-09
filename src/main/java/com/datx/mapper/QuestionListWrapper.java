package com.datx.mapper;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Soudeh Masoudian on 11/30/2016.
 */
public class QuestionListWrapper implements Serializable {

    private static final long serialVersionUID = -8873905021592595515L;
    private Map<String, StringWriter> questionList;

    public Map<String, StringWriter> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(Map<String, StringWriter> questionList) {
        this.questionList = questionList;
    }
}
