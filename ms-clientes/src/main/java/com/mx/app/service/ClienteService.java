package com.mx.app.service;

import java.util.List;

import com.mx.app.dto.ClienteRequestDTO;
import com.mx.app.dto.ClienteResponseDTO;

public interface ClienteService {
	
	ClienteResponseDTO guardar(ClienteRequestDTO request);
	ClienteResponseDTO actualizar(Long id,ClienteRequestDTO request);
	void eliminar(Long id);
	
	ClienteResponseDTO buscarID(Long id);
	ClienteResponseDTO buscarNombre(String nombre);
	List<ClienteResponseDTO> listar();

}
