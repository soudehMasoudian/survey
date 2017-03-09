package com.datx.mapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Soudeh Masoudian on 11/28/2016.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "`option`")
public class Option implements Serializable {

    private static final long serialVersionUID = 281506150522395646L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String description;

    @Column
    private int counter;

    @Column
    private int precedence;

    @JoinColumn(name = "fk_question", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Question fkQuestion;

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

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getPrecedence() {
        return precedence;
    }

    public void setPrecedence(int precedence) {
        this.precedence = precedence;
    }

    public Question getFkQuestion() {
        return fkQuestion;
    }

    public void setFkQuestion(Question fkQuestion) {
        this.fkQuestion = fkQuestion;
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
        if (!(object instanceof Option)) {
            return false;
        }
        Option other = (Option) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Option[ id=" + id + " ]";
    }
}
