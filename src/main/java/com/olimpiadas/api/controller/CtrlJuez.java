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
import com.olimpiadas.api.dto.DtoJuezList;
import com.olimpiadas.api.entity.Disciplina;
import com.olimpiadas.api.entity.Juez;
import com.olimpiadas.api.service.SvcJuez;
import com.olimpiadas.exception.ApiException;

@RestController
@RequestMapping("/juez")
public class CtrlJuez {
	
	@Autowired
	SvcJuez svc;
	
	@GetMapping
	public ResponseEntity<List<DtoJuezList>> getJueces(){
		return new ResponseEntity<>(svc.getJueces(), HttpStatus.OK);
	}
	
	@GetMapping("/{rfc}")
	public ResponseEntity<Juez> getJuez(@PathVariable("rfc") String rfc){
		return new ResponseEntity<>(svc.getJuez(rfc), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> createJuez(@Valid @RequestBody Juez in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.createJuez(in), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateJuez(@PathVariable("id") Integer id, @Valid @RequestBody Juez in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.updateJuez(in, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteJuez(@PathVariable("id") Integer id){
		return new ResponseEntity<>(svc.deleteJuez(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}/disciplina")
	public ResponseEntity<ApiResponse> updateJuezDisciplina(@PathVariable("id") Integer id, @RequestBody Disciplina in){
		return new ResponseEntity<>(svc.updateJuezDisciplina(id, in), HttpStatus.OK);
	}
}