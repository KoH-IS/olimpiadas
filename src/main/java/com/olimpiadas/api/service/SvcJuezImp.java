package com.olimpiadas.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.olimpiadas.api.dto.ApiResponse;
import com.olimpiadas.api.dto.DtoJuezList;
import com.olimpiadas.api.entity.Disciplina;
import com.olimpiadas.api.entity.Juez;
import com.olimpiadas.api.repository.RepoJuez;
import com.olimpiadas.api.repository.RepoJuezList;
import com.olimpiadas.exception.ApiException;

@Service
public class SvcJuezImp implements SvcJuez {
	
	@Autowired
	RepoJuez repo;
	
	@Autowired
	RepoJuezList repoJuezList;

	@Override
	public List<DtoJuezList> getJueces() {
		return repoJuezList.findByStatus(1);
	}

	@Override
	public Juez getJuez(String rfc) {
		Juez juez = repo.findByRfcAndStatus(rfc, 1);
		if(juez != null)
			return juez;
		else
			throw new ApiException(HttpStatus.NOT_FOUND, "juez doesn't exist");
	}

	@Override
	public ApiResponse createJuez(Juez in) {
		Juez juezSaved = (Juez)repo.findByRfcAndMail(in.getRfc(), in.getMail());
		if(juezSaved != null) {
			if(juezSaved.getStatus() == 0) {
				repo.activateJuez(juezSaved.getJuez_id());
				return new ApiResponse("juez has been activated");
			}else
				throw new ApiException(HttpStatus.BAD_REQUEST, "juez already exist");
		}
		in.setStatus(1);
		try {
			repo.save(in);
		}catch(DataIntegrityViolationException e) {
			if(e.getLocalizedMessage().contains("rfc"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "juez rfc already exist");
			if(e.getLocalizedMessage().contains("mail"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "juez mail already exist");			
		}
		return new ApiResponse("juez created");
	}

	@Override
	public ApiResponse updateJuez(Juez in, Integer id) {
		try {
			repo.updateJuez(id, in.getName(), in.getSurname(), in.getRfc(), in.getMail(), in.getPassword());
		}catch(DataIntegrityViolationException e) {
			if(e.getLocalizedMessage().contains("rfc"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "juez rfc already exist");
			if(e.getLocalizedMessage().contains("mail"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "juez mail already exist");
		}
		return new ApiResponse("juez updated");
	}

	@Override
	public ApiResponse deleteJuez(Integer id) {
		if(repo.deleteJuez(id) > 0)
			return new ApiResponse("juez removed");
		else
			throw new ApiException(HttpStatus.BAD_REQUEST, "juez cannot be deleted");
	}

	@Override
	public ApiResponse updateJuezDisciplina(Integer id, Disciplina disciplina) {
		try {
			if(repo.updateJuezDisciplina(id, disciplina.getDisciplina_id()) > 0)
				return new ApiResponse("juez disciplina updated");
			else
				throw new ApiException(HttpStatus.BAD_REQUEST, "juez disciplina cannot be updated");
		}catch(DataIntegrityViolationException e) {
			throw new ApiException(HttpStatus.NOT_FOUND, "disciplina not found");
		}
	}
}