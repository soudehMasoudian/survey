package com.datx.mapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Soudeh Masoudian on 11/28/2016.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "survey")
public class Survey implements Serializable{
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private Boolean isMain;

    @Column(columnDefinition = "BIGINT default '0'")
    private int counter = 0;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSurvey", fetch = FetchType.LAZY)
    private List<Question> questions;

    @Transient
    private String[] question;

    @Transient
    private List<String> options;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsMain() {
        return isMain;
    }

    public void setIsMain(Boolean isMain) {
        this.isMain = isMain;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String[] getQuestion() {
        return question;
    }

    public void setQuestion(String[] question) {
        this.question = question;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Survey)) {
            return false;
        }
        Survey other = (Survey) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Survey[ id=" + id + " ]";
    }
}
