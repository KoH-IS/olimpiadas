package com.olimpiadas.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olimpiadas.api.entity.Calificacion;

@Repository
public interface RepoCalificacion extends JpaRepository<Calificacion, Integer>{

	@Query(value = "SELECT * FROM calificacion WHERE rfc_juez = :rfc_juez AND rfc_competidor = :rfc_competidor AND status = :status", nativeQuery = true)
	Calificacion get(@Param("rfc_juez") String rfc_juez, @Param("rfc_competidor") String rfc_competidor, @Param("status") Integer status);
	
	@Query(value = "SELECT FROM calificacion WHERE rfc_competidor = :rfc_competidor AND status = :status", nativeQuery = true)
	List<Calificacion> findByRfcCompetidor(@Param("rfc_competidor") String rfc_competidor, @Param("status") Integer status);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE calificacion SET calification = :calification, comentario = :comentario "
			+ "WHERE calificacion_id = :calificacion_id AND status = 1", nativeQuery = true)
	Integer updateCalificacion(
			@Param("calificacion_id") Integer calificacion_id,
			@Param("calification") Double calification,
			@Param("comentario") String comentario);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE calificacion SET status = 0 WHERE calificacion_id = :calificacion_id AND status = 1", nativeQuery = true)
	Integer removeCalificacion(@Param("calificacion_id") Integer calificacion_id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE calificacion SET status = 0 WHERE rfc_competidor = :rfc_competidor AND status = 1", nativeQuery = true)
	Integer clearCompetidor(@Param("rfc_competidor") String rfc_competidor);
}