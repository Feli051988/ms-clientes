package com.mx.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.app.dto.ClienteRequestDTO;
import com.mx.app.dto.ClienteResponseDTO;
import com.mx.app.entity.Cliente;
import com.mx.app.exeption.InformacionDuplicadaExeption;
import com.mx.app.exeption.RecursoNoEncotradoExeption;
import com.mx.app.mapper.ClienteMapper;
import com.mx.app.repository.ClienteRepository;

@Service
@Transactional
public class ClienteServiceImp implements ClienteService {

	private final ClienteRepository repo;
	private final ClienteMapper mapper;

	public ClienteServiceImp(ClienteRepository repo, ClienteMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	public int edadActual(LocalDate fechaNacimiento) {
		int edadActual = Period.between(fechaNacimiento, LocalDate.now()).getYears();

		return edadActual;
	}

	@Override
	public ClienteResponseDTO guardar(ClienteRequestDTO request) {
		// throw new RuntimeException("Error interno de prueba");

		Cliente cliente = mapper.toEntity(request);

		if (repo.existsByCorreo(cliente.getCorreo())) {
			throw new InformacionDuplicadaExeption("El correo ya esta registrado " + cliente.getCorreo());
		}

		cliente.setEdad(edadActual(cliente.getFechaNacimiento()));
		cliente.setFechaHoraRegistro(LocalDateTime.now());
		cliente.setFechaHoraActualizacion(null);
		Cliente clienteGuardar = repo.save(cliente);
		return mapper.toResponse(clienteGuardar);
	}

	@Override
	public ClienteResponseDTO actualizar(Long id, ClienteRequestDTO request) {
		Cliente cliente = repo.findById(id)
				.orElseThrow(() -> new RecursoNoEncotradoExeption("Cliente no encontrado con id: " + id));

		cliente.setNombre(request.getNombre());
		cliente.setApp(request.getApp());
		cliente.setFechaNacimiento(request.getFechaNacimiento());
		cliente.setEdad(edadActual(cliente.getFechaNacimiento()));
		cliente.setCorreo(request.getCorreo());
		// cliente.setFechaHoraRegistro(LocalDateTime.now());
		cliente.setFechaHoraActualizacion(LocalDateTime.now());

		Cliente clienteActualizar = repo.save(cliente);

		return mapper.toResponse(clienteActualizar);
	}

	@Override
	public void eliminar(Long id) {

		if (!repo.existsById(id)) {
			throw new RecursoNoEncotradoExeption("El cliente con id: " + id);
		}
		repo.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public ClienteResponseDTO buscarID(Long id) {

		Cliente cliente = repo.findById(id)
				.orElseThrow(() -> new RecursoNoEncotradoExeption("El cliente no exite con id: " + id));

		return mapper.toResponse(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public ClienteResponseDTO buscarNombre(String nombre) {
		Cliente cliente = repo.buscarNombre(nombre);

		if (cliente == null) {
			throw new RecursoNoEncotradoExeption("No exite cliente con nombre: " + nombre);
		}
		return mapper.toResponse(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ClienteResponseDTO> listar() {

		return repo.findAll().stream().map(mapper::toResponse).toList();
	}

}
