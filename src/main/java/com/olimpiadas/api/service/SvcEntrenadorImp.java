package com.olimpiadas.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.olimpiadas.api.dto.ApiResponse;
import com.olimpiadas.api.dto.DtoCompetidorList;
import com.olimpiadas.api.dto.DtoEntrenadorList;
import com.olimpiadas.api.entity.Disciplina;
import com.olimpiadas.api.entity.Entrenador;
import com.olimpiadas.api.repository.RepoEntrenador;
import com.olimpiadas.api.repository.RepoEntrenadorList;
import com.olimpiadas.exception.ApiException;

@Service
public class SvcEntrenadorImp implements SvcEntrenador {
	
	@Autowired
	RepoEntrenador repo;
	
	@Autowired
	RepoEntrenadorList repoEntrenadorList;

	@Override
	public List<DtoEntrenadorList> getEntrenadores() {
		return repoEntrenadorList.findByStatus(1);
	}

	@Override
	public List<DtoCompetidorList> getCompetidores(Integer entrenador_id) {
		return repoEntrenadorList.getCompetidores(entrenador_id, 1);
	}

	@Override
	public Entrenador getEntrenador(String rfc) {
		Entrenador entrenador = repo.findByRfcAndStatus(rfc, 1);
		if(entrenador != null)
			return entrenador;
		else
			throw new ApiException(HttpStatus.NOT_FOUND, "entrenador doesn't exist");
	}

	@Override
	public ApiResponse createEntrenador(Entrenador in) {
		Entrenador entrenadorSaved = (Entrenador)repo.findByRfcAndMail(in.getRfc(), in.getMail());
		if(entrenadorSaved != null) {
			if(entrenadorSaved.getStatus() == 0) {
				repo.activateEntrenador(entrenadorSaved.getEntrenador_id());
				return new ApiResponse("entrenador has been activated");
			}else
				throw new ApiException(HttpStatus.BAD_REQUEST, "entrenador already exist");
		}
		in.setStatus(1);
		try {
			repo.save(in);
		}catch(DataIntegrityViolationException e) {
			if(e.getLocalizedMessage().contains("rfc"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "entrenador rfc already exist");
			if(e.getLocalizedMessage().contains("mail"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "entrenador mail already exist");			
		}
		return new ApiResponse("entrenador created");
	}

	@Override
	public ApiResponse updateEntrenador(Entrenador in, Integer id) {
		try {
			repo.updateEntrenador(id, in.getName(), in.getSurname(), in.getRfc(), in.getMail(), in.getPassword());
		}catch(DataIntegrityViolationException e) {
			if(e.getLocalizedMessage().contains("rfc"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "entrenador rfc already exist");
			if(e.getLocalizedMessage().contains("mail"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "entrenador mail already exist");
		}
		return new ApiResponse("entrenador updated");
	}

	@Override
	public ApiResponse deleteEntrenador(Integer id) {
		if(repo.deleteEntrenador(id) > 0)
			return new ApiResponse("entrenador removed");
		else
			throw new ApiException(HttpStatus.BAD_REQUEST, "entrenador cannot be deleted");
	}

	@Override
	public ApiResponse updateEntrenadorDisciplina(Integer id, Disciplina disciplina) {
		try {
			if(repo.updateEntrenadorDisciplina(id, disciplina.getDisciplina_id()) > 0)
				return new ApiResponse("entrenador disciplina updated");
			else
				throw new ApiException(HttpStatus.BAD_REQUEST, "entrenador disciplina cannot be updated");
		}catch(DataIntegrityViolationException e) {
			throw new ApiException(HttpStatus.NOT_FOUND, "disciplina not found");
		}
	}
}