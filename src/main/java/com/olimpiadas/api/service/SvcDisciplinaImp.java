package com.olimpiadas.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.olimpiadas.api.dto.ApiResponse;
import com.olimpiadas.api.entity.Disciplina;
import com.olimpiadas.api.repository.RepoDisciplina;
import com.olimpiadas.exception.ApiException;

@Service
public class SvcDisciplinaImp implements SvcDisciplina {
	
	@Autowired
	RepoDisciplina repo;

	@Override
	public List<Disciplina> getDisciplinas() {
		return repo.findByStatus(1);
	}

	@Override
	public Disciplina getDisciplina(Integer disciplina_id) {
		Disciplina disciplina = repo.findByDisciplinaId(disciplina_id);
		if(disciplina == null)
			throw new ApiException(HttpStatus.NOT_FOUND, "disciplina doesn't exist");
		else
			return disciplina;
	}

	@Override
	public ApiResponse createDisciplina(Disciplina disciplina) {
		Disciplina disciplinaSaved = (Disciplina) repo.findByDisciplina(disciplina.getDisciplina());
		if(disciplinaSaved != null) {
			if(disciplinaSaved.getStatus() == 0) {
				repo.activateDisciplina(disciplinaSaved.getDisciplina_id());
				return new ApiResponse("disciplina has been activated");
			}else
				throw new ApiException(HttpStatus.BAD_REQUEST, "disciplina already exist");
		}
		repo.createDisciplina(disciplina.getDisciplina(), disciplina.getDescripcion());
		return new ApiResponse("disciplina created");
	}

	@Override
	public ApiResponse updateDisciplina(Integer disciplina_id, Disciplina disciplina) {
		Disciplina disciplinaSaved = (Disciplina)repo.findByDisciplinaId(disciplina_id);
		if(disciplinaSaved == null)
			throw new ApiException(HttpStatus.NOT_FOUND, "disciplina doesn't exist");
		else {
			if(disciplinaSaved.getStatus() == 0)
				throw new ApiException(HttpStatus.BAD_REQUEST, "disciplina isn't active");
			else {
				try {
					repo.updateDisciplina(disciplina_id, disciplina.getDisciplina(), disciplina.getDescripcion());
				}catch(DataIntegrityViolationException e) {
					if(e.getLocalizedMessage().contains("disciplina"))
						throw new ApiException(HttpStatus.BAD_REQUEST, "disciplina already exist");
				}
				return new ApiResponse("disciplina updated");
			}
		}
	}

	@Override
	public ApiResponse deleteDisciplina(Integer disciplina_id) {
		Disciplina disciplinaSaved = (Disciplina) repo.findByDisciplinaId(disciplina_id);
		if(disciplinaSaved == null)
			throw new ApiException(HttpStatus.NOT_FOUND, "disciplina doesn't exist");
		else {
			repo.deleteById(disciplina_id);
			return new ApiResponse("disciplina removed");
		}
	}
}