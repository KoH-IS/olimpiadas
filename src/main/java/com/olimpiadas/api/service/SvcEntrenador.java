package com.olimpiadas.api.service;

import java.util.List;

import com.olimpiadas.api.dto.ApiResponse;
import com.olimpiadas.api.dto.DtoCompetidorList;
import com.olimpiadas.api.dto.DtoEntrenadorList;
import com.olimpiadas.api.entity.Disciplina;
import com.olimpiadas.api.entity.Entrenador;

public interface SvcEntrenador {
	
	public List<DtoEntrenadorList> getEntrenadores();
	public List<DtoCompetidorList> getCompetidores(Integer entrenador_id);
	public Entrenador getEntrenador(String rfc);
	public ApiResponse createEntrenador(Entrenador in);
	public ApiResponse updateEntrenador(Entrenador in, Integer id);
	public ApiResponse deleteEntrenador(Integer id);
	
	public ApiResponse updateEntrenadorDisciplina(Integer id, Disciplina disciplina);
}