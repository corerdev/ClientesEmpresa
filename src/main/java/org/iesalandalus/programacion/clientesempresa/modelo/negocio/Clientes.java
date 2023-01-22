package org.iesalandalus.programacion.clientesempresa.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;

public class Clientes {

	private Cliente[] coleccionClientes;
	private int capacidad;
	private int tamano;

	public Clientes(int capacidad) {
		if (capacidad<=0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.coleccionClientes = new Cliente[capacidad];
		this.capacidad = capacidad;
		this.tamano = 0;
	}

	private boolean capacidadSuperada(int indice) {

		if (indice <= capacidad)
			return false;
		else
			return true;
	}

	private boolean tamanoSuperado(int indice) {

		if (indice <= tamano)
			return false;
		else
			return true;
	}

	private int buscarIndice(Cliente cliente) {

		int indice = this.tamano + 1;
		boolean estaCliente = false;

		for (int i = 0; i < coleccionClientes.length && !estaCliente; i++) {
			if (coleccionClientes[i] != null && coleccionClientes[i].equals(cliente)) {
				indice = i;
				estaCliente = true;
			}
		}

		return indice;

	}

	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null)
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		
		if (buscarIndice(cliente)!=this.tamano+1) {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese dni.");
		}
		
		if (this.capacidadSuperada(buscarIndice(cliente)))
			throw new OperationNotSupportedException("ERROR: No se aceptan más clientes.");
		
		//if (this.tamano == this.capacidad)
		//	throw new OperationNotSupportedException("ERROR: Maxima capacidad alcanzada.");

		Cliente clienteCopia = new Cliente(cliente);
		boolean esNulo = false;
		for (int i = 0; i < capacidad && !esNulo; i++) {
			if (coleccionClientes[i] == null) {
				this.tamano = i+1;
				esNulo = true;
				coleccionClientes[i] = clienteCopia;
			}
		}

	}

	public Cliente buscar(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		}

		boolean estaCliente = false;
		Cliente buscaCliente = null;

		for (int i = 0; i < coleccionClientes.length && !estaCliente; i++) {
			if (coleccionClientes[i] != null && coleccionClientes[i].equals(cliente)) {
				buscaCliente = new Cliente(coleccionClientes[i]);
				estaCliente = true;
			}
		}

		return buscaCliente;
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		
		this.tamano = indice;

		for (int i = indice+1; i <= this.coleccionClientes.length && this.coleccionClientes[i] != null; i++) {
			this.coleccionClientes[i - 1] = this.coleccionClientes[i];
			this.coleccionClientes[i] = null;
			this.tamano = i;
		
			
		}

	}

	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}
		int indiceCliente = buscarIndice(cliente);

		if (indiceCliente == this.tamano + 1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente como el indicado.");
		}

		else {

			coleccionClientes[indiceCliente] = null;
			desplazarUnaPosicionHaciaIzquierda(indiceCliente);

		}

	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	public Cliente[] get() {
		Cliente[] coleccionTemporal = new Cliente[capacidad];
		for (int i = 0; i < capacidad; i++) {
			if (coleccionClientes[i] != null) {
				coleccionTemporal[i] = new Cliente(coleccionClientes[i]);
			}

		}

		return coleccionTemporal;
	}

}
