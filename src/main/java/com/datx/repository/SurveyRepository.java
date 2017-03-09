package com.datx.repository;

import com.datx.mapper.Survey;
import org.hibernate.LockMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Soudeh Masoudian on 11/30/2016.
 */
@Repository
public class SurveyRepository<T, PK extends Serializable> extends AbstractDao<Survey, Long> {
    /**
     * refers to the extended
     */
    public SurveyRepository() {
        super(Survey.class);
    }

    public List<Survey> findAll() throws Exception {
        getSession();
        return session.
                createCriteria(type.getName()).
                list();
    }

    public List<Survey> findAllBySurveyId(long surveyId) throws Exception {
        getSession();
        return session.
                createCriteria(type.getName()).
                add(Restrictions.eq("id", surveyId)).
                list();
    }

    public List<Survey> findMainSurveys() throws Exception {
        getSession();
        return session.
                createCriteria(Survey.class).
                add(Restrictions.eq("isMain", true)).
                list();
    }

    public Survey findMainSurvey() throws Exception {
        getSession();
        return (Survey) session.
                createCriteria(Survey.class).
                add(Restrictions.eq("isMain", true)).
                uniqueResult();
    }

    public Survey selectSurveyForUpdate(long id) throws Exception {
        getSession();
        return (Survey) session.get(Survey.class, id, LockMode.PESSIMISTIC_WRITE);
    }

}
