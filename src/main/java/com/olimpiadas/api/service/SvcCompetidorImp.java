package com.olimpiadas.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.olimpiadas.api.dto.ApiResponse;
import com.olimpiadas.api.dto.DtoCompetidorList;
import com.olimpiadas.api.entity.Competidor;
import com.olimpiadas.api.entity.Disciplina;
import com.olimpiadas.api.entity.Entrenador;
import com.olimpiadas.api.repository.RepoCompetidor;
import com.olimpiadas.api.repository.RepoCompetidorList;
import com.olimpiadas.exception.ApiException;

@Service
public class SvcCompetidorImp implements SvcCompetidor {
	
	@Autowired
	RepoCompetidor repo;
	
	@Autowired
	RepoCompetidorList repoCompList;

	@Override
	public List<DtoCompetidorList> getCompetidores() {
		return repoCompList.findByStatus(1);
	}

	@Override
	public List<DtoCompetidorList> getCompetidores(Integer disciplina_id) {
		return repoCompList.findByDisciplina(disciplina_id, 1);
	}

	@Override
	public List<DtoCompetidorList> getCompetidoresEntrenador(Integer entrenador_id) {
		return repoCompList.findByEntrenador(entrenador_id, 1);
	}

	@Override
	public Competidor getCompetidor(String rfc) {
		Competidor competidor = repo.findByRfcAndStatus(rfc, 1);
		if(competidor != null)
			return competidor;
		else
			throw new ApiException(HttpStatus.NOT_FOUND, "competidor doesn't exist");
	}

	@Override
	public ApiResponse createCompetidor(Competidor in) {
		Competidor competidorSaved = (Competidor)repo.findByRfcAndMail(in.getRfc(), in.getMail());
		if(competidorSaved != null) {
			if(competidorSaved.getStatus() == 0) {
				repo.activateCompetidor(competidorSaved.getCompetidor_id());
				return new ApiResponse("juez has been activated");
			}else
				throw new ApiException(HttpStatus.BAD_REQUEST, "competidor already exist");
		}
		in.setStatus(1);
		try {
			repo.save(in);
		}catch(DataIntegrityViolationException e) {
			if(e.getLocalizedMessage().contains("rfc"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "competidor rfc already exist");
			if(e.getLocalizedMessage().contains("mail"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "competidor mail already exist");			
		}
		return new ApiResponse("competidor created");
	}

	@Override
	public ApiResponse updateCompetidor(Competidor in, Integer id) {
		try {
			repo.updateCompetidor(id, in.getName(), in.getSurname(), in.getRfc(), in.getMail(), in.getPassword());
		}catch(DataIntegrityViolationException e) {
			if(e.getLocalizedMessage().contains("rfc"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "competidor rfc already exist");
			if(e.getLocalizedMessage().contains("mail"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "competidor mail already exist");			
		}
		return new ApiResponse("competidor updated");
	}

	@Override
	public ApiResponse deleteCompetidor(Integer id) {
		if(repo.deleteCompetidor(id) > 0)
			return new ApiResponse("competidor removed");
		else
			throw new ApiException(HttpStatus.BAD_REQUEST, "competidor cannot be deleted");
	}

	@Override
	public ApiResponse updateCompetidorDisciplina(Integer id, Disciplina disciplina) {
		try {
			if(repo.updateCompetidorDisciplina(id,disciplina.getDisciplina_id()) > 0)
				return new ApiResponse("competidor disciplina updated");
			else
				throw new ApiException(HttpStatus.BAD_REQUEST, "competidor disciplina cannot be updated");
		}catch(DataIntegrityViolationException e) {
			throw new ApiException(HttpStatus.NOT_FOUND, "disciplina not found");
		}
	}

	@Override
	public ApiResponse updateCompetidorEntrenador(Integer id, Entrenador entrenador) {
		try {
			if(repo.updateCompetidorEntrenador(id, entrenador.getEntrenador_id()) > 0)
				return new ApiResponse("competidor entrenador updated");
			else
				throw new ApiException(HttpStatus.BAD_REQUEST, "competidor entrenador cannot be updated");
		}catch(DataIntegrityViolationException e) {
			throw new ApiException(HttpStatus.NOT_FOUND, "entrenador not found");
		}
	}
}