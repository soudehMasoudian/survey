package com.datx.mapper;

import javax.persistence.*;

import java.time.Instant;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@SuppressWarnings("serial")
@Entity
@Table(name = "account")
public class Account implements java.io.Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String username;
	
	@JsonIgnore
	private String password;

	private String role = "ROLE_USER";

	private Date created;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinTable(
			name = "ACCOUNT_SURVEY",
			joinColumns = { @JoinColumn(name = "fk_account", nullable = false) },
			inverseJoinColumns = @JoinColumn(name = "fk_survey", nullable = false)
	)
	private Set<Survey> surveys;

    public Account() {

	}
	
	public Account(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.created = new Date();
	}

	public Long getId() {
		return id;
	}

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public Set<Survey> getSurveys() {
		return surveys;
	}

	public void setSurveys(Set<Survey> surveys) {
		this.surveys = surveys;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreated() {
		return created;
	}
}
