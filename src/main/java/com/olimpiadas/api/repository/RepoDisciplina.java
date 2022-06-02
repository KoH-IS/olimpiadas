package com.olimpiadas.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olimpiadas.api.entity.Disciplina;

@Repository
public interface RepoDisciplina extends JpaRepository<Disciplina, Integer>{
	
	@Query(value = "SELECT * FROM disciplina WHERE status = :status", nativeQuery = true)
	List<Disciplina> findByStatus(@Param("status") Integer status);

	@Query(value = "SELECT * FROM disciplina WHERE disciplina_id = :disciplina_id AND status = 1", nativeQuery = true)
	Disciplina findByDisciplinaId(@Param("disciplina_id") Integer disciplina_id);

	@Query(value = "SELECT * FROM disciplina WHERE disciplina = :disciplina", nativeQuery = true)
	Disciplina findByDisciplina(@Param("disciplina") String disciplina);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO disciplina (disciplina,descripcion,status) VALUES(:disciplina,:descripcion,1)", nativeQuery = true)
	void createDisciplina(@Param("disciplina") String disciplina, @Param("descripcion") String descripcion);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE disciplina SET disciplina = :disciplina, descripcion = :descripcion WHERE disciplina_id = :disciplina_id", nativeQuery = true)
	Integer updateDisciplina(@Param("disciplina_id") Integer disciplina_id, @Param("disciplina") String disciplina, @Param("descripcion") String descripcion);

	@Modifying
	@Transactional
	@Query(value = "UPDATE disciplina SET status = 1 WHERE disciplina_id = :disciplina_id", nativeQuery = true)
	Integer activateDisciplina(@Param("disciplina_id") Integer disciplina_id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE disciplina SET status = 0 WHERE disciplina_id = :disciplina_id", nativeQuery = true)
	void deleteById(@Param("disciplina_id") Integer disciplina_id);
}