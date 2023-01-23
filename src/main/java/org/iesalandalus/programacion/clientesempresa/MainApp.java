package org.iesalandalus.programacion.clientesempresa;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.clientesempresa.modelo.negocio.Clientes;
import org.iesalandalus.programacion.clientesempresa.vista.Consola;
import org.iesalandalus.programacion.clientesempresa.vista.Opcion;
import java.time.LocalDate;

public class MainApp {
	private static Clientes clientes;
	private static final int NUM_MAX_CLIENTES = 5;

	public static void main(String[] args) {

		clientes = new Clientes(NUM_MAX_CLIENTES);
		int bucleInfinito = 0;
		do {

			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		} while (bucleInfinito == 0);

	}

	private static void insertarCliente() {

		try {
			clientes.insertar(Consola.leerCliente());
		} catch (OperationNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void buscarCliente() {

		Cliente clienteABuscar = Consola.leerClienteDni();
		Cliente clienteEncontrado = clientes.buscar(clienteABuscar);
		if (clienteEncontrado == null) {

			System.out.println("ERROR: El cliente no existe.");

		} else {

			System.out.println(clienteEncontrado);

		}

	}

	private static void borrarCliente() {

		try {
			clientes.borrar(Consola.leerClienteDni());
		} catch (OperationNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void mostrarClientes() {
		
			Cliente[] clientesTemporal = clientes.get();
			
			for (Cliente cliente : clientesTemporal) {
				if (cliente!=null) {
				System.out.println(cliente);
				}
				
			}

		

	}

	private static void mostrarClientesFecha() {

		LocalDate fechaBuscada = Consola.leerFechaNacimiento();
		Cliente[] clientesTemporal = clientes.get();
		boolean vigia = false;
		
		for (Cliente cliente : clientesTemporal) {
			if (cliente!=null && cliente.getFechaNacimiento().equals(fechaBuscada)) {
			System.out.println(cliente);
			vigia = true;
			}
		}
		if (vigia==false) {
			System.out.println("No se ha encontrado ningun cliente con esa fecha de nacimiento.");
			
		}
	}

	private static void ejecutarOpcion(Opcion opcion) {

		switch (opcion) {

		case BUSCAR_CLIENTE:
			buscarCliente();
			break;

		case INSERTAR_CLIENTE:
			insertarCliente();
			break;
		case BORRAR_CLIENTE:
			borrarCliente();
			break;
		case MOSTRAR_CLIENTES:
			mostrarClientes();
			break;
		case MOSTRAR_CLIENTES_FECHA:
			mostrarClientesFecha();
			break;
		default:
			System.exit(0);

		}

	}
}