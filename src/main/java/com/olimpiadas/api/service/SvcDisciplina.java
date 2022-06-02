package com.olimpiadas.api.service;

import java.util.List;

import com.olimpiadas.api.dto.ApiResponse;
import com.olimpiadas.api.entity.Disciplina;

public interface SvcDisciplina {
	
	List<Disciplina> getDisciplinas() throws Exception;
	Disciplina getDisciplina(Integer disciplina_id);
	ApiResponse createDisciplina(Disciplina disciplina);
	ApiResponse updateDisciplina(Integer disciplina_id, Disciplina disciplina);
	ApiResponse deleteDisciplina(Integer disciplina_id);
}