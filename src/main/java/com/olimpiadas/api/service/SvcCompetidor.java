package com.olimpiadas.api.service;

import java.util.List;

import com.olimpiadas.api.dto.ApiResponse;
import com.olimpiadas.api.dto.DtoCompetidorList;
import com.olimpiadas.api.entity.Competidor;
import com.olimpiadas.api.entity.Disciplina;
import com.olimpiadas.api.entity.Entrenador;

public interface SvcCompetidor {
	
	public List<DtoCompetidorList> getCompetidores();
	public Competidor getCompetidor(String rfc);
	public ApiResponse createCompetidor(Competidor in);
	public ApiResponse updateCompetidor(Competidor in, Integer id);
	public ApiResponse deleteCompetidor(Integer id);
	
	public ApiResponse updateCompetidorDisciplina(Integer id, Disciplina disciplina);
	public ApiResponse updateCompetidorEntrenador(Integer id, Entrenador entrenador);
}