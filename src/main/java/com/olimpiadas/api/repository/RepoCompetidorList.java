package com.olimpiadas.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olimpiadas.api.dto.DtoCompetidorList;

@Repository
public interface RepoCompetidorList extends JpaRepository<DtoCompetidorList, Integer> {

	List<DtoCompetidorList> findByStatus(Integer status);
}