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
@Table(name = "calificacion")
public class Calificacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("calificacion_id")
	@Column(name = "calificacion_id")
	private Integer calificacion_id;
	
	@JsonProperty("rfc_juez")
	@Column(name = "rfc_juez")
	@NotNull(message = "rfc juez is required")
	private String rfc_juez;
	
	@JsonProperty("rfc_competidor")
	@Column(name = "rfc_competidor")
	@NotNull(message = "rfc competidor is required")
	private String rfc_competidor;
	
	@JsonProperty("calification")
	@Column(name = "calification")
	@NotNull(message = "calification is required")
	@Min(value = 0, message = "calification must be positive")
	private Double calification;
	
	@JsonProperty("comentario")
	@Column(name = "comentario")
	private String comentario;
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value = 0, message = "status must be 0 or 1")
	@Max(value = 1, message = "status must be 0 or 1")
	private Integer status;

	public Integer getCalificacion_id() {
		return calificacion_id;
	}

	public void setCalificacion_id(Integer calificacion_id) {
		this.calificacion_id = calificacion_id;
	}

	public String getRfc_juez() {
		return rfc_juez;
	}

	public void setRfc_juez(String rfc_juez) {
		this.rfc_juez = rfc_juez;
	}

	public String getRfc_competidor() {
		return rfc_competidor;
	}

	public void setRfc_competidor(String rfc_competidor) {
		this.rfc_competidor = rfc_competidor;
	}

	public Double getCalification() {
		return calification;
	}

	public void setCalification(Double calification) {
		this.calification = calification;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}