package medicina;

public abstract class Personas {
	//Atributos 
	protected String nombre;
	protected String apellido1;
	protected String apellido2;
	protected String domicilio;
	protected String dni; 
	protected String genero;

	protected int x;
	protected int y;
	protected int edad;
	//NO hacen falta la altura y el peso
	//	protected int altura;
	//	protected int peso;

	
	
	public abstract String obtenerRol();
	
	
	public Personas(String nombre, String apellido1, String apellido2, int x, int y,String domicilio,String dni,int edad, String genero) {
		setNombre(nombre);
		setApellido1(apellido1);
		setApellido2(apellido2);
		setX(x);
		setY(y);
		setDomicilio(domicilio);
		setDni(dni);
		setEdad(edad);
		setGenero(genero);
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = FuncionesAux.ayudaSetter(genero, "genero");
	}

	public String getNombre() {
		return nombre;
	}
	//Evitar nombre vacío
	public void setNombre(String nombre) {
		this.nombre = FuncionesAux.ayudaSetter(nombre, "nombre");
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = FuncionesAux.ayudaSetter(apellido1, "apellido1");
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = FuncionesAux.ayudaSetter(apellido2, "apellido2");
	}



	public int getX() {
		return x;
	}
	//Las coordenadas pueden ser negativas
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = FuncionesAux.ayudaSetter(domicilio, "domicilio");
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		if (dni != null && dni.length() == 9 && FuncionesAux.comprobacionDNI(dni)) {
			this.dni = dni;
		}
		else {
			System.err.println("Fallo con el DNI del usuario");
			this.dni = "Desconocido";
		}
	}


	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		if (edad <0) {
			System.err.println("Fallo con la edad de la persona");
			this.edad = 0;
		}else {
			this.edad = edad;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || !(obj instanceof Personas)) return false;
		Personas otraPersona = (Personas) obj;
		return ( getDni().equals(otraPersona.getDni()));
	}

	@Override
	public String toString() {
		return "Nombre: "+ getNombre()+ "\n"+
				"Rol: " + obtenerRol() +"\n"+
				"Apellidos: "+getApellido1()+" "+ getApellido2()+ "\n"+
				"Genero: " + getGenero() + "\n"+
				"Casa: "+ getDomicilio()+ "\n"+
				"Sitio: "+ "("+x+","+y+")"+"\n"+
				"Edad: "+ getEdad()+ "\n"+
				"DNI: "+getDni()+"\n";
	}




}
