package com.datx.repository;

import com.datx.mapper.Question;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Soudeh Masoudian on 12/1/2016.
 */
@Repository
public class QuestionRepository<T, PK extends Serializable> extends AbstractDao<Question, Long> {

    public QuestionRepository() {
        super(Question.class);
    }

}
