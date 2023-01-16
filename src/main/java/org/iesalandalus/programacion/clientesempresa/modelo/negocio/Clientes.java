package org.iesalandalus.programacion.clientesempresa.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;

public class Clientes {

	private Cliente[] coleccionClientes;
	private int capacidad = coleccionClientes.length;
	private int tamano;

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

		int indice = tamano + 1;
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
			throw new NullPointerException("ERROR: Musiquita de anuncio.");

		if (tamano == capacidad)
			throw new OperationNotSupportedException("ERROR: Maxima capacidad alcanzada.");

		Cliente clienteCopia = new Cliente(cliente);
		boolean esNulo = false;
		for (int i = 0; i < capacidad && !esNulo; i++) {
			if (coleccionClientes[i] == null) {
				tamano = i;
				esNulo = true;
				coleccionClientes[i] = clienteCopia;
			}
		}

	}

	public Cliente buscar(Cliente cliente) {
		if (cliente == null)
			throw new NullPointerException("ERROR: Musiquita de anuncio.");

		boolean estaCliente = false;
		Cliente buscaCliente = null;

		for (int i = 0; i < coleccionClientes.length && !estaCliente; i++) {
			if (coleccionClientes[i].equals(cliente)) {
				buscaCliente = new Cliente(coleccionClientes[i]);
				estaCliente = true;
			}
		}

		return buscaCliente;
	}
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		
		
		
		for (int i = indice++; i <= coleccionClientes.length && coleccionClientes[i] != null; i++) 
        {
			coleccionClientes[i-1] = coleccionClientes[i];
			coleccionClientes[i] = null;
        }
		
	}
	
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		
		int indiceCliente = buscarIndice(cliente);
		
		if (indiceCliente==tamano+1)
		 throw new OperationNotSupportedException("ERROR: Me quiero morir por favor.");
		
		else {
			
			coleccionClientes[indiceCliente]=null;
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
		for (int i=0; i < capacidad; i++) {
			if (coleccionClientes[i] !=null) {
				coleccionTemporal[i] = new Cliente(coleccionClientes[i]);
			}
			
		}
		
		return coleccionTemporal;
	}

}
