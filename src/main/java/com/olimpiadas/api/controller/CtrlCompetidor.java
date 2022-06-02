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
import com.olimpiadas.api.entity.Competidor;
import com.olimpiadas.api.entity.Disciplina;
import com.olimpiadas.api.entity.Entrenador;
import com.olimpiadas.api.service.SvcCompetidor;
import com.olimpiadas.exception.ApiException;

@RestController
@RequestMapping("/competidor")
public class CtrlCompetidor {

	@Autowired
	SvcCompetidor svc;
	
	@GetMapping
	public ResponseEntity<List<DtoCompetidorList>> getCompetidores(){
		return new ResponseEntity<>(svc.getCompetidores(), HttpStatus.OK);
	}
	
	@GetMapping("/{rfc}")
	public ResponseEntity<Competidor> getCompetidor(@PathVariable("rfc") String rfc){
		return new ResponseEntity<>(svc.getCompetidor(rfc), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> createCompetidor(@Valid @RequestBody Competidor in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.createCompetidor(in), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateCompetidor(@PathVariable("id") Integer id, @Valid @RequestBody Competidor in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.updateCompetidor(in, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCompetidor(@PathVariable("id") Integer id){
		return new ResponseEntity<>(svc.deleteCompetidor(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}/disciplina")
	public ResponseEntity<ApiResponse> updateCompetidorDisciplina(@PathVariable("id") Integer id, @RequestBody Disciplina in){
		return new ResponseEntity<>(svc.updateCompetidorDisciplina(id, in), HttpStatus.OK);
	}
	
	@PutMapping("/{id}/entrenador")
	public ResponseEntity<ApiResponse> updateCompetidorEntrenador(@PathVariable("id") Integer id, @RequestBody Entrenador in){
		return new ResponseEntity<>(svc.updateCompetidorEntrenador(id, in), HttpStatus.OK);
	}
}