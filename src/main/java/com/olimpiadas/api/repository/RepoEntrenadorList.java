package com.olimpiadas.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olimpiadas.api.dto.DtoEntrenadorList;

@Repository
public interface RepoEntrenadorList extends JpaRepository<DtoEntrenadorList, Integer> {

	List<DtoEntrenadorList> findByStatus(Integer status);
}