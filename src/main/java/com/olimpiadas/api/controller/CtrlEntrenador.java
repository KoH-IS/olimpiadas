package com.olimpiadas.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olimpiadas.api.dto.ApiResponse;
import com.olimpiadas.api.dto.DtoCompetidorList;
import com.olimpiadas.api.dto.DtoEntrenadorList;
import com.olimpiadas.api.entity.Calificacion;
import com.olimpiadas.api.entity.Disciplina;
import com.olimpiadas.api.entity.Entrenador;
import com.olimpiadas.api.service.SvcCalificacion;
import com.olimpiadas.api.service.SvcEntrenador;
import com.olimpiadas.exception.ApiException;

@RestController
@RequestMapping("/entrenador")
public class CtrlEntrenador {

	@Autowired
	SvcEntrenador svc;
	
	@Autowired
	SvcCalificacion svcCal;
	
	@GetMapping
	public ResponseEntity<List<DtoEntrenadorList>> getEntrenadores(){
		return new ResponseEntity<>(svc.getEntrenadores(), HttpStatus.OK);
	}
	
	@GetMapping("/{entrenador_id}/competidor")
	public ResponseEntity<List<DtoCompetidorList>> getCompetidores(@PathVariable("entrenador_id") Integer entrenador_id){
		return new ResponseEntity<>(svc.getCompetidores(entrenador_id), HttpStatus.OK);
	}
	
	@GetMapping("/{rfc_juez}/{rfc_competidor}/calificacion")
	public ResponseEntity<Calificacion> getCalificacion(@PathVariable("rfc_juez") String rfc_juez, @PathVariable("rfc_competidor") String rfc_competidor){
		return new ResponseEntity<>(svcCal.getCalificacion(rfc_juez, rfc_competidor), HttpStatus.OK);
	}
	
	@GetMapping("/{rfc}")
	public ResponseEntity<Entrenador> getEntrenador(@PathVariable("rfc") String rfc){
		return new ResponseEntity<>(svc.getEntrenador(rfc), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> createEntrenador(@Valid @RequestBody Entrenador in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.createEntrenador(in), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateEntrenador(@PathVariable("id") Integer id, @Valid @RequestBody Entrenador in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.updateEntrenador(in, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteEntrenador(@PathVariable("id") Integer id){
		return new ResponseEntity<>(svc.deleteEntrenador(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}/disciplina")
	public ResponseEntity<ApiResponse> updateEntrenadorDisciplina(@PathVariable("id") Integer id, @RequestBody Disciplina in){
		return new ResponseEntity<>(svc.updateEntrenadorDisciplina(id, in), HttpStatus.OK);
	}
}