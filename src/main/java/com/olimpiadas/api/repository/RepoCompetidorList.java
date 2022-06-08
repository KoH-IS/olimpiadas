package com.olimpiadas.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olimpiadas.api.dto.DtoCompetidorList;

@Repository
public interface RepoCompetidorList extends JpaRepository<DtoCompetidorList, Integer> {

	List<DtoCompetidorList> findByStatus(Integer status);
	
	@Query(value = "SELECT * FROM competidor WHERE disciplina_id = :disciplina_id AND status = :status", nativeQuery = true)
	List<DtoCompetidorList> findByDisciplina(@Param("disciplina_id") Integer disciplina_id, @Param("status") Integer status);
	
	@Query(value = "SELECT * FROM competidor WHERE entrenador_id = :entrenador_id AND status = :status", nativeQuery = true)
	List<DtoCompetidorList> findByEntrenador(@Param("entrenador_id") Integer entrenador_id, @Param("status") Integer status);
}