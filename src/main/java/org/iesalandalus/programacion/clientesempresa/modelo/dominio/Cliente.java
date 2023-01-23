package org.iesalandalus.programacion.clientesempresa.modelo.dominio;

import java.util.regex.Pattern; 
import java.util.Objects;
import java.util.regex.Matcher;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cliente {

	private final String ER_CORREO = "([\\w.]+[@][\\w]+[.][\\w]+)";
	private final String ER_DNI = "([0-9]{8})([A-Za-z])";
	private final String ER_TELEFONO = "([0-9]{9})";
	private String dni, correo, telefono, nombre;
	public String FORMATO_FECHA;
	private LocalDate fechaNacimiento;
	DateTimeFormatter formatoLargo = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Cliente(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {

		this.setCorreo(correo);
		this.setDni(dni);
		this.setNombre(nombre);
		this.setTelefono(telefono);
		this.setFechaNacimiento(fechaNacimiento);

	}

	public Cliente(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		} else {
		correo = cliente.getCorreo();
		dni = cliente.getDni();
		nombre = cliente.getNombre();
		telefono = cliente.getTelefono();
		fechaNacimiento = cliente.getFechaNacimiento();
		}

	}

	private String formateaNombre(String nombre) {
		String mayus, minus, nombreFinal = "";
		String[] nombreString = nombre.trim().toLowerCase().split("\s+");
		for (int i = 0; i < nombreString.length; i++) {
			mayus = nombreString[i].substring(0, 1).toUpperCase();
			minus = nombreString[i].substring(1);
			nombreString[i] = mayus + minus;
			if (i == 0) {
				nombreFinal = nombreString[i];
			} else {
				nombreFinal = nombreFinal + " " + nombreString[i];
			}
		}
		return new String(nombreFinal);
	}

	private Boolean comprobarLetraDni(String dni) {
		Pattern patron = Pattern.compile(ER_DNI);
		Matcher comparador = patron.matcher(dni);
		if (comparador.matches()) {
			final char[] LETRADNI = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S',
					'Q', 'V', 'H', 'L', 'C', 'K', 'E' };
			int numDni = Integer.parseInt(comparador.group(1));
			char letraDniTemp = LETRADNI[numDni % 23];
			String letraDniTempString = String.valueOf(letraDniTemp);
			if (letraDniTempString.matches(comparador.group(2)))
				return true;
			else
				return false;
		} else
			return false;
	}

	private String getIniciales() {

		String mayus, minus, nombreFinal = "";
		String[] nombreString = nombre.trim().toLowerCase().split("\s+");
		for (int i = 0; i < nombreString.length; i++) {
			mayus = nombreString[i].substring(0, 1).toUpperCase();
			minus = nombreString[i].substring(1);
			nombreString[i] = mayus;
			if (i == 0) {
				nombreFinal = nombreString[i];
			} else {
				nombreFinal = nombreFinal + nombreString[i];
			}
		}
		return new String(nombreFinal);
	}

	public String getDni() {
		if (dni == null)
			throw new NullPointerException("ERROR: El dni de un cliente no puede ser nulo.");
		else
			return new String(dni);
	}

	private void setDni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El dni de un cliente no puede ser nulo.");
		}
		Pattern patron = Pattern.compile(ER_DNI);
		Matcher comparador = patron.matcher(dni);
		if (comparador.matches())
			if (comprobarLetraDni(dni)) {
				this.dni = dni;
			} else
				throw new IllegalArgumentException("ERROR: La letra del dni del cliente no es correcta.");
		else {
			throw new IllegalArgumentException("ERROR: El dni del cliente no tiene un formato válido.");

		}
	}

	public String getCorreo() {
		if (correo == null)
			throw new NullPointerException("ERROR: El correo de un cliente no puede ser nulo.");
		else
			return new String(correo);
	}

	public void setCorreo(String correo) {
		if (correo == null)
			throw new NullPointerException("ERROR: El correo de un cliente no puede ser nulo.");
		else if ((correo.matches(ER_CORREO)))
			this.correo = correo;
		else
			throw new IllegalArgumentException("ERROR: El correo del cliente no tiene un formato válido.");
	}

	public String getTelefono() {
		if (telefono == null)
			throw new NullPointerException("ERROR: El teléfono de un cliente no puede ser nulo.");
		else
			return new String(telefono);
	}

	public void setTelefono(String telefono) {
		if (telefono == null)
			throw new NullPointerException("ERROR: El teléfono de un cliente no puede ser nulo.");
		else if ((telefono.matches(ER_TELEFONO)))
			this.telefono = telefono;
		else
			throw new IllegalArgumentException("ERROR: El teléfono del cliente no tiene un formato válido.");

	}

	public String getNombre() {
		if (nombre == null)
			throw new NullPointerException("ERROR: El nombre de un cliente no puede ser nulo.");
		else
			return new String(nombre);
	}

	public void setNombre(String nombre) {
		if (nombre == null)
			throw new NullPointerException("ERROR: El nombre de un cliente no puede ser nulo.");
		else if ((nombre.trim().isEmpty()))
			throw new IllegalArgumentException("ERROR: El nombre de un cliente no puede estar vacío.");
		else
			this.nombre = formateaNombre(nombre);
	}

	public LocalDate getFechaNacimiento() {
		if (fechaNacimiento == null)
			throw new NullPointerException("ERROR: La fecha de nacimiento de un cliente no puede ser nula.");
		else
			return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		if (fechaNacimiento == null)
			throw new NullPointerException("ERROR: La fecha de nacimiento de un cliente no puede ser nula.");
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return "nombre=" + formateaNombre(nombre) + " (" + getIniciales() + "), DNI=" + dni + ", correo=" + correo
				+ ", teléfono=" + telefono + ", fecha nacimiento=" + fechaNacimiento.format(formatoLargo);
	}
}
