package com.mx.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.app.dto.ClienteRequestDTO;
import com.mx.app.dto.ClienteResponseDTO;
import com.mx.app.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/cliente")
@CrossOrigin("*")
@Tag(name = "MS-CLIENTE", description = "Operaciones relacionados con clientes")
public class ClienteController {

	private final ClienteService service;

	public ClienteController(ClienteService service) {
		this.service = service;
	}

	// http://localhost:9000/api/cliente
	@GetMapping
	@Operation(summary = "Lista de clientes", description = "Optiene todos los clientes de la base de datos")
	public ResponseEntity<List<ClienteResponseDTO>> listar() {
		return ResponseEntity.ok(service.listar());
	}

	// http://localhost:9000/api/cliente/
	@GetMapping("/{id}")
	@Operation(summary = "Busca un cliente por ID", description = "Optiene un cliente de la base de datos")
	public ResponseEntity<ClienteResponseDTO> buscarID(@PathVariable() Long id) {
		return ResponseEntity.ok(service.buscarID(id));
	}

	// http://localhost:9000/api/cliente/buscarNombre/
	@GetMapping("buscarNombre/{nombre}")
	@Operation(summary = "Busca un cliente por Nombre", description = "Optiene un cliente de la base de datos")
	public ResponseEntity<ClienteResponseDTO> buscarNombre(@PathVariable() String nombre) {
		return ResponseEntity.ok(service.buscarNombre(nombre));
	}

	// http://localhost:9000/api/cliente
	@PostMapping
	@Operation(summary = "Guardar un cliente", description = "Permite guardar un nuevo cliente en la base de datos H2 DataBase")
	public ResponseEntity<ClienteResponseDTO> guardar(@Valid @RequestBody ClienteRequestDTO request) {
		ClienteResponseDTO cliente = service.guardar(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
	}

	// http://localhost:9000/api/cliente
	@PutMapping("/{id}")
	@Operation(summary = "Actualiza un cliente", description = "Permite actualziar un cliente en la base de datos H2 DataBase")
	public ResponseEntity<ClienteResponseDTO> actualizar(@PathVariable() Long id,
			@Valid @RequestBody ClienteRequestDTO request) {
		ClienteResponseDTO cliente = service.actualizar(id, request);
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}

	// http://localhost:9000/api/cliente/
	@DeleteMapping("/{id}")
	@Operation(summary = "Elimina un cliente", description = "Permite eliminar un cliente en la base de datos H2 DataBase")
	public ResponseEntity<Void> eliminar(@PathVariable() Long id) {
		service.eliminar(id);
		return ResponseEntity.noContent().build();
	}

	// http://localhost:9000/api/cliente/eliminarNombre/
	@DeleteMapping("eliminarNombre/{nombre}")
	@Operation(summary = "Elimina un cliente por nombre", description = "Elimina un cliente únicamente cuando existe una coincidencia")
	public ResponseEntity<Void> eliminarNombre(@PathVariable() String nombre) {
		service.eliminarNombre(nombre);
		return ResponseEntity.noContent().build();
	}

}
