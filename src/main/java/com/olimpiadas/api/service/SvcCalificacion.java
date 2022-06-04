package com.olimpiadas.api.service;

import java.util.List;

import com.olimpiadas.api.dto.ApiResponse;
import com.olimpiadas.api.entity.Calificacion;

public interface SvcCalificacion {

	List<Calificacion> getCalificaciones(String rfc_competidor);
	Calificacion getCalificacion(String rfc_juez, String rfc_competidor);
	public ApiResponse createCalificacion(Calificacion in);
	public ApiResponse removeCalificacion(Integer calificacion_id);
	public ApiResponse clearCalificacion(String rfc_competidor);
}