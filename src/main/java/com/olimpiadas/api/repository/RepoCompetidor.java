package com.olimpiadas.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.olimpiadas.api.entity.Competidor;

public interface RepoCompetidor extends JpaRepository<Competidor, Integer> {

	Competidor findByRfcAndStatus(String rfc, Integer status);
	
	@Query(value = "SELECT * FROM competidor WHERE rfc = :rfc AND mail = :mail", nativeQuery = true)
	Competidor findByRfcAndMail(@Param("rfc") String rfc, @Param("mail") String mail);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE competidor " 
			+ "SET name = :name, " 
				+ "surname = :surname, "
				+ "rfc = :rfc, "
				+ "mail = :mail, "
				+ "password = :password "
			+ "WHERE competidor_id = :competidor_id AND status = 1", nativeQuery = true)
	Integer updateCompetidor(
			@Param("competidor_id") Integer competidor_id,
			@Param("name") String name,
			@Param("surname") String surname,
			@Param("rfc") String rfc,
			@Param("mail") String mail,
			@Param("password") String password);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE competidor SET status = 0 WHERE competidor_id = :competidor_id AND status = 1", nativeQuery = true)
	Integer deleteCompetidor(@Param("competidor_id") Integer competidor_id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE competidor SET status = 1 WHERE competidor_id = :competidor_id", nativeQuery = true)
	Integer activateCompetidor(@Param("competidor_id") Integer competidor_id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE competidor SET disciplina_id = :disciplina_id WHERE competidor_id = :competidor_id AND status = 1", nativeQuery = true)
	Integer updateCompetidorDisciplina(@Param("competidor_id") Integer competidor_id,  @Param("disciplina_id") Integer disciplina_id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE competidor SET entrenador_id = :entrenador_id WHERE competidor_id = :competidor_id AND status = 1", nativeQuery = true)
	Integer updateCompetidorEntrenador(@Param("competidor_id") Integer competidor_id, @Param("entrenador_id") Integer entrenador_id);
}