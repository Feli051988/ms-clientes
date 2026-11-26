package com.mx.app.exeption;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExeptionHandler {

	// error 404 Recurso no encontrado
	@ExceptionHandler(RecursoNoEncotradoExeption.class)
	public ResponseEntity<Map<String, Object>> recursoNoEncotradoExeption(RecursoNoEncotradoExeption ex,
			HttpServletRequest request) {

		Map<String, Object> error = new LinkedHashMap<>();

		error.put("Fecha y Hora error", LocalDateTime.now());
		error.put("Status: ", HttpStatus.NOT_FOUND.value());
		error.put("Error: ", HttpStatus.NOT_FOUND.getReasonPhrase());
		error.put("Message: ", ex.getMessage());
		error.put("Path: ", request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	// error 409 Correo duplicado
	@ExceptionHandler(InformacionDuplicadaExeption.class)
	public ResponseEntity<Map<String, Object>> informacionDuplicadaExeption(InformacionDuplicadaExeption ex,
			HttpServletRequest request) {

		Map<String, Object> error = new LinkedHashMap<>();

		error.put("Fecha y Hora error", LocalDateTime.now());
		error.put("Status: ", HttpStatus.CONFLICT.value());
		error.put("Error: ", HttpStatus.CONFLICT.getReasonPhrase());
		error.put("Message: ", ex.getMessage());
		error.put("Path: ", request.getRequestURI());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

	// error 400 Datos incorrectos
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> methodArgumentNotValidException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {

		Map<String, Object> error = new LinkedHashMap<>();

		List<String> errores = ex.getBindingResult().getFieldErrors().stream()
				.map(err -> err.getField() + ":" + err.getDefaultMessage()).toList();

		error.put("Fecha y Hora error", LocalDateTime.now());
		error.put("Status: ", HttpStatus.BAD_REQUEST.value());
		error.put("Error: ", HttpStatus.BAD_REQUEST.getReasonPhrase());
		error.put("Message: ", errores);
		error.put("Path: ", request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	private static final Logger logger = LoggerFactory.getLogger(GlobalExeptionHandler.class);

	// error 500 Datos incorrectos
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> exeptionGeneral(Exception ex, HttpServletRequest request) {

		logger.error("Error interno del endPoint: {}",request.getRequestURI(), ex);

		Map<String, Object> error = new LinkedHashMap<>();

		error.put("Fecha y Hora error", LocalDateTime.now());
		error.put("Status: ", HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.put("Error: ", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		error.put("Message: ", "Ocurrio un error interno del sevidor");
		error.put("Path: ", request.getRequestURI());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

}
