package com.mx.app.exeption;

public class InformacionDuplicadaExeption extends RuntimeException {

	public InformacionDuplicadaExeption(String mensaje) {
		super(mensaje);
	}
}
