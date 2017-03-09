package com.datx.repository;

import com.datx.mapper.Option;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Soudeh Masoudian on 12/2/2016.
 */
@Repository
public class OptionRepository<T, PK extends Serializable> extends AbstractDao<Option, Long> {
    public OptionRepository() {
        super(Option.class);
    }

    public List<Option> findAllBySurveyId(List optionIds) throws Exception {
        getSession();
        return session.
                createCriteria(type.getName()).
                add(Restrictions.in("id", optionIds)).
                list();
    }
}
