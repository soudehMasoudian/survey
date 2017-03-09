package com.datx.mapper;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Soudeh Masoudian on 11/28/2016.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "chosen_option")
public class ChosenOption implements Serializable{

    private static final long serialVersionUID = -3307482378009209205L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "form_serial_no")
    private int formSerialNo;

    @Column(name = "fk_option", nullable = false)
    private long fkOption;

    @Column
    private Long questionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFormSerialNo() {
        return formSerialNo;
    }

    public void setFormSerialNo(int formSerialNo) {
        this.formSerialNo = formSerialNo;
    }

    public Long getFkOption() {
        return fkOption;
    }

    public void setFkOption(long fkOption) {
        this.fkOption = fkOption;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
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
        if (!(object instanceof ChosenOption)) {
            return false;
        }
        ChosenOption other = (ChosenOption) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ChosenOption[ id=" + id + " ]";
    }
}
