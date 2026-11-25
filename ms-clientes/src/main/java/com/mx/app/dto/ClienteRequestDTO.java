package com.mx.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClienteRequestDTO {

	 @NotBlank(message = "El nombre es obligatorio")
	    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
	    private String nombre;

	    @NotBlank(message = "El apellido paterno es obligatorio")
	    @Size(max = 50, message = "El apellido paterno no puede superar los 50 caracteres")
	    private String app;

	    @NotNull(message = "La fecha de nacimiento es obligatoria")
	    @PastOrPresent(message = "La fecha de nacimiento no puede ser futura")
	    private LocalDate fechaNacimiento;

	    @NotBlank(message = "El correo electrónico es obligatorio")
	    @Email(message = "El formato del correo electrónico no es válido")
	    @Size(max = 100, message = "El correo no puede superar los 100 caracteres")
	    private String correo;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	

}
