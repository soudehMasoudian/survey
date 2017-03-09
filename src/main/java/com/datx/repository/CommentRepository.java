package com.datx.repository;

import com.datx.mapper.Comment;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Soudeh Masoudian on 12/1/2016.
 */
@Repository
public class CommentRepository<T, PK extends Serializable> extends AbstractDao<Comment, Long> {
    public CommentRepository() {
        super(Comment.class);
    }
    public List<Comment> findAllBySurveyId(long surveyId) throws Exception {
        getSession();
        return session.
                createCriteria(Comment.class).
                add(Restrictions.eq("fkSurvey.id", surveyId)).
                list();
    }
}
