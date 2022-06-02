package com.olimpiadas.api.service;

import java.util.List;

import com.olimpiadas.api.dto.ApiResponse;
import com.olimpiadas.api.dto.DtoJuezList;
import com.olimpiadas.api.entity.Disciplina;
import com.olimpiadas.api.entity.Juez;

public interface SvcJuez {

	public List<DtoJuezList> getJueces();
	public Juez getJuez(String rfc);
	public ApiResponse createJuez(Juez in);
	public ApiResponse updateJuez(Juez in, Integer id);
	public ApiResponse deleteJuez(Integer id);
	
	public ApiResponse updateJuezDisciplina(Integer id, Disciplina disciplina);
}