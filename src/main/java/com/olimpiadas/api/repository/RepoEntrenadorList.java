package com.olimpiadas.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olimpiadas.api.dto.DtoCompetidorList;
import com.olimpiadas.api.dto.DtoEntrenadorList;

@Repository
public interface RepoEntrenadorList extends JpaRepository<DtoEntrenadorList, Integer> {

	List<DtoEntrenadorList> findByStatus(Integer status);
	
	@Query(value = "SELECT * FROM competidor WHERE entrenador_id = :entrenador_id AND status = :status", nativeQuery = true)
	List<DtoCompetidorList> getCompetidores(@Param("entrenador_id") Integer entrenador_id, @Param("status") Integer status);
}