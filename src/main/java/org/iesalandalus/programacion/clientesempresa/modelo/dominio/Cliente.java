package org.iesalandalus.programacion.clientesempresa.modelo.dominio;

import java.util.regex.Pattern;
import java.util.Objects;
import java.util.regex.Matcher;
import java.time.LocalDate;

public class Cliente {

	private final String ER_CORREO = "([\\w.]+[@][\\w]+[.][\\w]+)";
	private final String ER_DNI = "([0-9]{8})([A-Za-z])";
	private final String ER_TELEFONO = "([0-9]{9})";
	private String dni, correo, telefono, nombre;
	public String FORMATO_FECHA;
	private LocalDate fechaNacimiento;

	public Cliente(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
		if (nombre == null)
			throw new NullPointerException("ERROR: El nombre de un cliente no puede ser nulo.");
		else if ( dni == null)
			throw new NullPointerException("ERROR: El dni de un cliente no puede ser nulo.");
		else if (correo == null) 
			throw new NullPointerException("ERROR: El correo de un cliente no puede ser nulo.");
		else if (telefono == null)
				throw new NullPointerException("ERROR: El teléfono de un cliente no puede ser nulo.");
		else if (fechaNacimiento == null)
			throw new NullPointerException("ERROR: La fecha de nacimiento de un cliente no puede ser nula.");
		 else {
			this.setCorreo(correo);
		this.setDni(dni);
		this.setNombre(nombre);
		this.setTelefono(telefono);
		this.setFechaNacimiento(fechaNacimiento);
		 }
	}

	public Cliente(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		} else
			cliente = new Cliente(cliente);

	}

	private String formateaNombre(String nombre) {
		String nombreTrimmed = nombre.trim();
		String nombreLower = nombreTrimmed.toLowerCase();
		char[] nombreTemp = nombreLower.toCharArray();
		nombreTemp[0] = Character.toUpperCase(nombreTemp[0]);
		for (int i = 0; i < nombreLower.length() - 1; i++)
			if (nombreTemp[i] == ' ' || nombreTemp[i] == '.' || nombreTemp[i] == ',')
				nombreTemp[i + 1] = Character.toUpperCase(nombreTemp[i + 1]);
		return new String(nombreTemp);
	}

	private Boolean comprobarLetraDni(String dni) {
		Pattern patron = Pattern.compile(ER_DNI);
		Matcher comparador = patron.matcher(dni);
		final char[] LETRADNI = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
				'V', 'H', 'L', 'C', 'K', 'E' };
		int numDni = Integer.parseInt(comparador.group(1));
		char letraDniTemp = LETRADNI[numDni % 23];
		String letraDniTempString = String.valueOf(letraDniTemp);
		if (letraDniTempString.matches(comparador.group(2)))
			return true;
		else
			return false;

	}

	private String getIniciales() {

		String stringTemporal = this.formateaNombre(nombre);
		char primeraLetra, segundaLetra = 0, terceraLetra = 0;
		char[] charTemporal = stringTemporal.toCharArray();
		primeraLetra = charTemporal[0];
		int i;
		for (i = 0; i < stringTemporal.length() - 1; i++) {
			if (charTemporal[i] == ' ' || charTemporal[i] == '.' || charTemporal[i] == ',') {
				segundaLetra = Character.toUpperCase(charTemporal[i + 1]);
				break;
			}
		}
		for (i++; i < stringTemporal.length(); i++) {
			if (charTemporal[i] == ' ' || charTemporal[i] == '.' || charTemporal[i] == ',') {
				terceraLetra = Character.toUpperCase(charTemporal[i + 1]);
				break;
			}
		}
		return new String(primeraLetra + "." + segundaLetra + "." + terceraLetra + ".");
	}

	public String getDni() {
		return new String(dni);
	}

	private void setDni(String dni) {
		if (dni == null)
			throw new NullPointerException("Error: Brbrbrbrbrbrbrb.");
		else {
			Pattern patronDni = Pattern.compile(ER_DNI);
			Matcher comparadorDni = patronDni.matcher(dni);
			int numDni = Integer.parseInt(comparadorDni.group(1));
			String letraDniTempString = String.valueOf(numDni);
			if (letraDniTempString.matches(comparadorDni.group(1))) {
				if (comprobarLetraDni(dni)) {
					this.dni = dni;
				} else
					throw new IllegalArgumentException("ERROR: SHVUJNSFBNSBSJBSNBFdni2.");
			} else
				throw new IllegalArgumentException("ERROR: SHVUJNSFBNSBSJBSNBFdni.");
		}
	}

	public String getCorreo() {
		return new String(correo);
	}

	public void setCorreo(String correo) {
		if (correo == null)
			throw new NullPointerException("Error: Brbrbrbrbrbrbrb.");
		else {
			Pattern patronCorreo = Pattern.compile(ER_CORREO);
			Matcher comparadorCorreo = patronCorreo.matcher(correo);
			if (correo.matches(comparadorCorreo.group(1)))
				this.correo = correo;
			else
				throw new IllegalArgumentException("ERROR: HAHGAHGHAGHAGA.");
		}
	}

	public String getTelefono() {
		return new String(telefono);
	}

	public void setTelefono(String telefono) {
		if (telefono == null)
			throw new NullPointerException("Error: Brbrbrbrbrbrbrb.");
		else {
			Pattern patronTelefono = Pattern.compile(ER_TELEFONO);
			Matcher comparadorTelefono = patronTelefono.matcher(correo);
			if (telefono.matches(comparadorTelefono.group(1)))
				this.telefono = telefono;
			else
				throw new IllegalArgumentException("ERROR: HAHGAHGHAGHAGAtelefono.");
		}
	}

	public String getNombre() {
		return new String(nombre);
	}

	public void setNombre(String nombre) {
		if (nombre == null)
			throw new NullPointerException("Error: Brbrbrbrbrbrbrb.");
		else if (nombre == " ")
			throw new IllegalArgumentException("ERROR: Su puta madre.");
		else
		this.nombre = nombre;
	}

	public LocalDate getFechaNacimiento() {
		return new LocalDate(fechaNacimiento);
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		if (fechaNacimiento == null)
			throw new NullPointerException("Error: Brbrbrbrbrbrbrb.");
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
		return "Cliente [dni=" + dni + ", correo=" + correo + ", telefono=" + telefono + ", nombre=" + getIniciales()
				+ formateaNombre(nombre) + ", FORMATO_FECHA=" + FORMATO_FECHA + "]";
	}
}
