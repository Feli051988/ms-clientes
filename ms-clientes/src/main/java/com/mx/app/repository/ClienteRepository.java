package com.mx.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mx.app.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM CLIENTE WHERE NOMBRE=:NOMBRE")
	public Cliente buscarNombre(@Param("NOMBRE") String nombre);

	boolean existsByCorreo(String correo);
	
	void deleteByNombre(String nombre);
	
	// en caso de tener mas de un cliente con el mismo nombre
	List<Cliente> findAllByNombreIgnoreCase(String nombre);
}
