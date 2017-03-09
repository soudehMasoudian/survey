package com.datx.mapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Soudeh Masoudian on 11/28/2016.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "question")
public class Question implements Serializable {

    private static final long serialVersionUID = -2905135122060335203L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String description;

    @JoinColumn(name = "fk_survey", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Survey fkSurvey;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkQuestion")
    private List<Option> options;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Survey getFkSurvey() {
        return fkSurvey;
    }

    public void setFkSurvey(Survey fkSurvey) {
        this.fkSurvey = fkSurvey;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
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
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Question[ id=" + id + " ]";
    }
}
