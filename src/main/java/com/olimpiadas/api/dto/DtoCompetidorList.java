package com.olimpiadas.api.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.olimpiadas.api.entity.Disciplina;
import com.olimpiadas.api.entity.Entrenador;

@Entity
@Table(name = "competidor")
public class DtoCompetidorList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("competidor_id")
	@Column(name = "competidor_id")
	private Integer competidor_id;
	
	@JsonProperty("name")
	@Column(name = "name")
	@NotNull(message = "name is required")
	private String name;
	
	@JsonProperty("surname")
	@Column(name = "surname")
	@NotNull(message = "surname is required")
	private String surname;
	
	@JsonProperty("rfc")
	@Column(name = "rfc")
	@NotNull(message = "rfc is required")
	private String rfc;
	
	@JsonProperty("mail")
	@Column(name = "mail")
	@NotNull(message = "mail is required")
	private String mail;
	
	@JsonProperty("password")
	@Column(name = "password")
	@NotNull(message = "password is required")
	private String password;
	
	@Column(name = "status")
	@Min(value = 0, message = "status must be 0 or 1")
	@Max(value = 1, message = "status must be 0 or 1")
	private Integer status;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "disciplina_id", referencedColumnName = "disciplina_id")
	private Disciplina disciplina;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "entrenador_id", referencedColumnName = "entrenador_id")
	private Entrenador entrenador;

	public Integer getCompetidor_id() {
		return competidor_id;
	}

	public void setCompetidor_id(Integer competidor_id) {
		this.competidor_id = competidor_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}
}