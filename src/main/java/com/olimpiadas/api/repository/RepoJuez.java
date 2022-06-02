package com.olimpiadas.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olimpiadas.api.entity.Juez;

@Repository
public interface RepoJuez extends JpaRepository<Juez, Integer>{

	Juez findByRfcAndStatus(String rfc, Integer status);
	
	@Query(value = "SELECT * FROM juez WHERE rfc = :rfc AND mail = :mail", nativeQuery = true)
	Juez findByRfcAndMail(@Param("rfc") String rfc, @Param("mail") String mail);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE juez " 
			+ "SET name = :name, " 
				+ "surname = :surname, "
				+ "rfc = :rfc, "
				+ "mail = :mail, "
				+ "password = :password "
			+ "WHERE juez_id = :juez_id AND status = 1", nativeQuery = true)
	Integer updateJuez(
			@Param("juez_id") Integer juez_id,
			@Param("name") String name,
			@Param("surname") String surname,
			@Param("rfc") String rfc,
			@Param("mail") String mail,
			@Param("password") String password);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE juez SET status = 0 WHERE juez_id = :juez_id AND status = 1", nativeQuery = true)
	Integer deleteJuez(@Param("juez_id") Integer juez_id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE juez SET status = 1 WHERE juez_id = :juez_id", nativeQuery = true)
	Integer activateJuez(@Param("juez_id") Integer juez_id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE juez SET disciplina_id = :disciplina_id WHERE juez_id = :juez_id AND status = 1", nativeQuery = true)
	Integer updateJuezDisciplina(@Param("juez_id") Integer juez_id, @Param("disciplina_id") Integer disciplina_id);
}