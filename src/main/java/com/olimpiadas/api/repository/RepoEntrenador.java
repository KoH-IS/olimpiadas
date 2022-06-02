package com.olimpiadas.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.olimpiadas.api.entity.Entrenador;

public interface RepoEntrenador extends JpaRepository<Entrenador, Integer> {

	Entrenador findByRfcAndStatus(String rfc, Integer status);
	
	@Query(value = "SELECT * FROM entrenador WHERE rfc = :rfc AND mail = :mail", nativeQuery = true)
	Entrenador findByRfcAndMail(@Param("rfc") String rfc, @Param("mail") String mail);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE entrenador " 
			+ "SET name = :name, " 
				+ "surname = :surname, "
				+ "rfc = :rfc, "
				+ "mail = :mail, "
				+ "password = :password "
			+ "WHERE entrenador_id = :entrenador_id AND status = 1", nativeQuery = true)
	Integer updateEntrenador(
			@Param("entrenador_id") Integer entrenador_id,
			@Param("name") String name,
			@Param("surname") String surname,
			@Param("rfc") String rfc,
			@Param("mail") String mail,
			@Param("password") String password);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE entrenador SET status = 0 WHERE entrenador_id = :entrenador_id AND status = 1", nativeQuery = true)
	Integer deleteEntrenador(@Param("entrenador_id") Integer entrenador_id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE entrenador SET status = 1 WHERE entrenador_id = :entrenador_id", nativeQuery = true)
	Integer activateEntrenador(@Param("entrenador_id") Integer entrenador_id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE entrenador SET disciplina_id = :disciplina_id WHERE entrenador_id = :entrenador_id AND status = 1", nativeQuery = true)
	Integer updateEntrenadorDisciplina(@Param("entrenador_id") Integer entrenador_id, @Param("disciplina_id") Integer disciplina_id);
}