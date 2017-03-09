package com.datx.repository;

import com.datx.mapper.ChosenOption;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Soudeh Masoudian on 12/2/2016.
 */
@Repository
public class ChosenOptionRepository<T, PK extends Serializable> extends AbstractDao<ChosenOption, Long>{
    public ChosenOptionRepository() {
        super(ChosenOption.class);
    }
}
