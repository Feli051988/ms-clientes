package com.mx.app.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.mx.app.dto.ClienteRequestDTO;
import com.mx.app.dto.ClienteResponseDTO;
import com.mx.app.entity.Cliente;

@Component
public class ClienteMapper {

	public Cliente toEntity(ClienteRequestDTO request) {

		Cliente cliente = new Cliente();

		cliente.setNombre(request.getNombre());
		cliente.setApp(request.getApp());
		cliente.setFechaNacimiento(request.getFechaNacimiento());
		cliente.setCorreo(request.getCorreo());

		return cliente;

	}

	public ClienteResponseDTO toResponse(Cliente cliente) {

		ClienteResponseDTO response = new ClienteResponseDTO();

		response.setId(cliente.getId());
		response.setNombre(cliente.getNombre());
		response.setApp(cliente.getApp());
		response.setFechaNacimiento(cliente.getFechaNacimiento());
		response.setEdad(cliente.getEdad());
		response.setCorreo(cliente.getCorreo());
		response.setFechaHoraRegistro(cliente.getFechaHoraRegistro());
		response.setFechaHoraActualizacion(cliente.getFechaHoraActualizacion());

		return response;

	}

}
