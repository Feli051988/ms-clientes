package com.mx.app.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.Collate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CLIENTE")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Cliente {

	@Id
	@SequenceGenerator(name = "sql_sequence", sequenceName = "SQL_SEQUENCE_UNO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sql_sequence")
	private Long id;
	private String nombre;
	private String app;
	@Column(name = "FECHA_NACIMIENTO")
	private LocalDate fechaNacimiento;
	private Integer edad;
	private String correo;
	@Column(name = "FECHA_HORA_REGISTRO")
	private LocalDateTime fechaHoraRegistro;
	@Column(name = "FECHA_HORA_ACTUALIZACION")
	private LocalDateTime fechaHoraActualizacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public LocalDateTime getFechaHoraRegistro() {
		return fechaHoraRegistro;
	}

	public void setFechaHoraRegistro(LocalDateTime fechaHoraRegistro) {
		this.fechaHoraRegistro = fechaHoraRegistro;
	}

	public LocalDateTime getFechaHoraActualizacion() {
		return fechaHoraActualizacion;
	}

	public void setFechaHoraActualizacion(LocalDateTime fechaHoraActualizacion) {
		this.fechaHoraActualizacion = fechaHoraActualizacion;
	}

}
