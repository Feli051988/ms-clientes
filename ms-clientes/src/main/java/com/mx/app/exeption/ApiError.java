package com.mx.app.exeption;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ApiError {

	private LocalDateTime fechaHoraError;
	private int status;
	private String error;
	private String message;
	private String path;

	public LocalDateTime getFechaHoraError() {
		return fechaHoraError;
	}

	public void setFechaHoraError(LocalDateTime fechaHoraError) {
		this.fechaHoraError = fechaHoraError;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
