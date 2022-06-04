package com.olimpiadas.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.olimpiadas.api.dto.ApiResponse;
import com.olimpiadas.api.entity.Calificacion;
import com.olimpiadas.api.entity.Competidor;
import com.olimpiadas.api.entity.Juez;
import com.olimpiadas.api.repository.RepoCalificacion;
import com.olimpiadas.api.repository.RepoCompetidor;
import com.olimpiadas.api.repository.RepoJuez;
import com.olimpiadas.exception.ApiException;

@Service
public class SvcCalificacionImp implements SvcCalificacion {
	
	@Autowired
	RepoCalificacion repo;
	
	@Autowired
	RepoJuez repoJuez;
	
	@Autowired
	RepoCompetidor repoCompetidor;

	@Override
	public List<Calificacion> getCalificaciones(String rfc_competidor) {
		return repo.findByRfcCompetidor(rfc_competidor, 1);
	}

	@Override
	public Calificacion getCalificacion(String rfc_juez, String rfc_competidor) {
		Calificacion cal = repo.get(rfc_juez, rfc_competidor, 0);
		if(cal != null)
			return cal;
		throw new ApiException(HttpStatus.NOT_FOUND, "calificacion doesn't exist");
	}

	@Override
	public ApiResponse createCalificacion(Calificacion in) {
		if(!validateJuez(in.getRfc_juez()))
			throw new ApiException(HttpStatus.BAD_REQUEST, "juez doesn't exist");
		if(!validateCompetidor(in.getRfc_competidor()))
			throw new ApiException(HttpStatus.BAD_REQUEST, "competidor doesn't exist");
		Calificacion cal = repo.get(in.getRfc_juez(), in.getRfc_competidor(), 0);
		if(cal != null)
			if(repo.updateCalificacion(in.getCalificacion_id(), in.getCalification(), in.getComentario()) > 0)
				return new ApiResponse("calificacion updated");
		else
			try {
				in.setStatus(1);
				repo.save(in);
			}catch(Exception e) {
				throw new ApiException(HttpStatus.BAD_REQUEST, "calificacion err");
			}
		return new ApiResponse("calificacion created");
	}

	@Override
	public ApiResponse removeCalificacion(Integer calificacion_id) {
		if(repo.removeCalificacion(calificacion_id) > 0)
			return new ApiResponse("calificacion removed");
		else
			throw new ApiException(HttpStatus.BAD_REQUEST, "calificacion cannot be removed");
	}

	@Override
	public ApiResponse clearCalificacion(String rfc_competidor) {
		if(repo.clearCompetidor(rfc_competidor) > 0)
			return new ApiResponse("clear calificacion");
		else
			throw new ApiException(HttpStatus.BAD_REQUEST, "calificacion cannot be removed");
	}

	private boolean validateCompetidor(String rfc) {
		try {
			Competidor competidor = repoCompetidor.findByRfcAndStatus(rfc, 1);
			return competidor != null;
		}catch(Exception e) {
			throw new ApiException(HttpStatus.OK, "unable to retrieve competidor information");
		}
	}
	
	private boolean validateJuez(String rfc) {
		try {
			Juez juez = repoJuez.findByRfcAndStatus(rfc, 1);
			return juez != null;
		}catch(Exception e) {
			throw new ApiException(HttpStatus.OK, "unable to retrieve juez information");
		}
	}
}
