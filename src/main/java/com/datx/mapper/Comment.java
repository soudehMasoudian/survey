package com.datx.mapper;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Soudeh Masoudian on 12/1/2016.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "comment")
public class Comment implements Serializable{

    private static final long serialVersionUID = -3703588574693097741L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String comment;

    @Column(name = "form_serial_no")
    private int formSerialNo;

    @JoinColumn(name = "fk_survey", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Survey fkSurvey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Survey getFkSurvey() {
        return fkSurvey;
    }

    public void setFkSurvey(Survey fkSurvey) {
        this.fkSurvey = fkSurvey;
    }

    public int getFormSerialNo() {
        return formSerialNo;
    }

    public void setFormSerialNo(int formSerialNo) {
        this.formSerialNo = formSerialNo;
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
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comment[ id=" + id + " ]";
    }
}
