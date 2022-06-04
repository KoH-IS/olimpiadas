package com.olimpiadas.api.entity;

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
@Table(name = "disciplina")
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "disciplina_id")
	private Integer disciplina_id;
	
	@JsonProperty("disciplina")
	@NotNull(message = "disciplina is required")
	@Column(name = "disciplina")
	private String disciplina;
	
	@JsonProperty("descripcion")
	@NotNull(message = "descripcion is required")
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "status")
	@Min(value = 0, message="status must be 0 or 1")
	@Max(value = 1, message="status must be 0 or 1")
	@JsonIgnore
	private Integer status;

	public Integer getDisciplina_id() {
		return disciplina_id;
	}

	public void setDisciplina_id(Integer disciplina_id) {
		this.disciplina_id = disciplina_id;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Disciplina [disciplina_id=" + disciplina_id + ", disciplina=" + disciplina + ", descripcion="
				+ descripcion + ", status=" + status + "]";
	}
}