package com.olimpiadas.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olimpiadas.api.dto.DtoJuezList;

@Repository
public interface RepoJuezList extends JpaRepository<DtoJuezList, Integer>{

	List<DtoJuezList> findByStatus(Integer status);
	
	@Query(value = "SELECT * FROM juez WHERE disciplina_id = :disciplina_id AND status = :status", nativeQuery = true)
	List<DtoJuezList> findByDisciplina(@Param("disciplina_id") Integer disciplina_id, @Param("status") Integer status);
}