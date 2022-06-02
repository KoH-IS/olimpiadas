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
import com.olimpiadas.api.entity.Disciplina;
import com.olimpiadas.api.service.SvcDisciplina;
import com.olimpiadas.exception.ApiException;

@RestController
@RequestMapping("/disciplina")
public class CtrlDisciplina {

	@Autowired
	SvcDisciplina svc;
	
	@GetMapping
	public ResponseEntity<List<Disciplina>> getDisciplinas() throws Exception{
		return new ResponseEntity<>(svc.getDisciplinas(), HttpStatus.OK);
	}
	
	@GetMapping("/{disciplina_id}")
	public ResponseEntity<Disciplina> getDisciplina(@PathVariable Integer disciplina_id) {
		return new ResponseEntity<>(svc.getDisciplina(disciplina_id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> createDisciplina(@Valid @RequestBody Disciplina disciplina, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.createDisciplina(disciplina), HttpStatus.OK);
	}
	
	@PutMapping("/{disciplina_id}")
	public ResponseEntity<ApiResponse> updateDisciplina(@PathVariable Integer disciplina_id, @Valid @RequestBody Disciplina disciplina, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.updateDisciplina(disciplina_id, disciplina), HttpStatus.OK);
	}
	
	@DeleteMapping("/{disciplina_id}")
	public ResponseEntity<ApiResponse> deleteDisciplina(@PathVariable Integer disciplina_id){
		return new ResponseEntity<>(svc.deleteDisciplina(disciplina_id), HttpStatus.OK);
	}
}