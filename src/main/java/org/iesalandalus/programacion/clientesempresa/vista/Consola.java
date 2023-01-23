package org.iesalandalus.programacion.clientesempresa.vista;

import org.iesalandalus.programacion.utilidades.Entrada;
import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Consola {

	private Consola() {
	}

	public static void mostrarMenu() {

		System.out.println("--------------------------------");
		System.out.println("Trabajo con clientes");
		System.out.println("Las opciones disponibles son:");
		System.out.println("1 - Insertar un cliente.");
		System.out.println("2 - Buscar un cliente.");
		System.out.println("3 - Borrar un cliente.");
		System.out.println("4 - Mostrar todos los clientes.");
		System.out.println("5 - Mostrar todos los clientes que hayan nacido en una fecha concreta.");
		System.out.println("6 - Salir.");

	}

	public static Opcion elegirOpcion() {

		int seleccion;

		do {

			System.out.println("Por favor, elija una de las opciones del menú: ");
			seleccion = Entrada.entero();
		} while (seleccion < 1 || seleccion > 6);

		if (seleccion == 1) {
			System.out.println("Ha elegido insertar un cliente.");
			return Opcion.INSERTAR_CLIENTE;
		}
		if (seleccion == 2) {
			System.out.println("Ha elegido buscar un cliente.");
			return Opcion.BUSCAR_CLIENTE;
		}
		if (seleccion == 3) {
			System.out.println("Ha elegido borrar un cliente.");
			return Opcion.BORRAR_CLIENTE;
		}
		if (seleccion == 4) {
			System.out.println("Ha elegido mostrar todos los clientes.");
			return Opcion.MOSTRAR_CLIENTES;
		}
		if (seleccion == 5) {
			System.out.println("Ha elegido mostrar los clientes con una fecha de nacimiento concreta.");
			return Opcion.MOSTRAR_CLIENTES_FECHA;
		}
		if (seleccion == 6) {
			System.out.println("Ha elegido cerrar el programa.");
			return Opcion.SALIR;
		}
		return Opcion.SALIR;
	}

	public static Cliente leerCliente() {
		DateTimeFormatter formatoLargo = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Introduzca el nombre del cliente.");
		String nombre = Entrada.cadena();
		String dni;
		do {

			System.out.println("Por favor, introduzca el dni del cliente: ");
			dni = Entrada.cadena();
		} while (dni.length() != 9);

		String correo;
		System.out.println("Introduzca el correo del cliente.");
		correo = Entrada.cadena();

		String telefono;
		do {

			System.out.println("Por favor, introduzca el telefono del cliente: ");
			telefono = Entrada.cadena();
		} while (telefono.length() != 9);

		String fechaNacimiento;
		do {
			System.out.println("Introduzca la fecha de nacimiento del cliente (en formato dd/mm/yyyy).");
			fechaNacimiento = Entrada.cadena();
		} while (fechaNacimiento.length() != 10);

		LocalDate fechaCliente = LocalDate.parse(fechaNacimiento, formatoLargo);
		

		Cliente clienteLeer = new Cliente(nombre, dni, correo, telefono, fechaCliente);
		return clienteLeer;
	}

	public static Cliente leerClienteDni() {
		
		String dni;
		do {

			System.out.println("Por favor, introduzca el dni del cliente: ");
			dni = Entrada.cadena();
		} while (dni.length() != 9);
		LocalDate fechaDummy = LocalDate.now();
		Cliente clienteDumy = new Cliente("Dummy", dni, "dummy@gmail.com", "111222333", fechaDummy);
		return clienteDumy;
		
	}
		
		public static LocalDate leerFechaNacimiento() {
			DateTimeFormatter formatoLargo = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String fechaNacimiento;
			do {
				System.out.println("Introduzca la fecha de nacimiento del cliente (en formato dd/mm/yyyy).");
				fechaNacimiento = Entrada.cadena();
			} while (fechaNacimiento.length() != 10);

			LocalDate fechaCliente = LocalDate.parse(fechaNacimiento, formatoLargo);
			return fechaCliente;
			
		}
	}
