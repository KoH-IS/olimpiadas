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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olimpiadas.api.dto.ApiResponse;
import com.olimpiadas.api.entity.Calificacion;
import com.olimpiadas.api.service.SvcCalificacion;
import com.olimpiadas.exception.ApiException;

@RestController
@RequestMapping("/calificacion")
public class CtrlCalificacion {
	
	@Autowired
	SvcCalificacion svc;
	
	@GetMapping("/{rfc_competidor}")
	public ResponseEntity<List<Calificacion>> getCalificacion(@PathVariable("rfc_competidor") String rfc_competidor){
		return new ResponseEntity<>(svc.getCalificaciones(rfc_competidor), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> createCalificacion(@Valid @RequestBody Calificacion in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.createCalificacion(in), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{calificacion_id}")
	public ResponseEntity<ApiResponse> removeCalificacion(@PathVariable("calificacion_id") Integer calificacion_id){
		return new ResponseEntity<>(svc.removeCalificacion(calificacion_id), HttpStatus.OK);
	}
	
	@DeleteMapping("/clear/{rfc_competidor}")
	public ResponseEntity<ApiResponse> clearCalificacion(@PathVariable("rfc_competidor") String rfc_competidor){
		return new ResponseEntity<>(svc.clearCalificacion(rfc_competidor), HttpStatus.OK);
	}
}