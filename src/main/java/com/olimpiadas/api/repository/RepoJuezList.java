package com.olimpiadas.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olimpiadas.api.dto.DtoJuezList;

@Repository
public interface RepoJuezList extends JpaRepository<DtoJuezList, Integer>{

	List<DtoJuezList> findByStatus(Integer status);
}