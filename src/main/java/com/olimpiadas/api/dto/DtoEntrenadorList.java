package com.olimpiadas.api.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "entrenador")
public class DtoEntrenadorList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("entrenador_id")
	@Column(name = "entrenador_id")
	private Integer entrenador_id;
	
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
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value = 0, message = "status must be 0 or 1")
	@Max(value = 1, message = "status must be 0 or 1")
	private Integer status;

	public Integer getEntrenador_id() {
		return entrenador_id;
	}

	public void setEntrenador_id(Integer entrenador_id) {
		this.entrenador_id = entrenador_id;
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

}
