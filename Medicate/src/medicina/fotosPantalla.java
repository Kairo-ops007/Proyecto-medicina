package medicina;

public class fotosPantalla {
	
	private String enrutado;
	private String descripcion;
	private String respuestaPaciente;
	
	public fotosPantalla(String enrutado, String descripcion, String respuestaPaciente) {
	
		setEnrutado(enrutado);
		setDescripcion(descripcion);
		setRespuestaPaciente(respuestaPaciente);
	
	}

	
	
	public String getEnrutado() {
		return enrutado;
	}
//TODO pasar el enrutado como predefinido seria mas interesante
	public void setEnrutado(String enrutado) {
		FuncionesAux.ayudaSetter(enrutado, "../");
	}


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		FuncionesAux.ayudaSetter(descripcion, "descripcion");
	}


	public String getRespuestaPaciente() {
		return respuestaPaciente;
	}

	public void setRespuestaPaciente(String respuestaPaciente) {
		FuncionesAux.ayudaSetter(respuestaPaciente, "ERROR");
	}

	


	@Override
	public boolean equals(Object obj) {
		if (obj == null||!(obj instanceof fotosPantalla)) {
			return false;
		}
		if (obj == this) 
		{return true;}
		fotosPantalla ft = (fotosPantalla) obj;
		return ft.getEnrutado().equals(this.getEnrutado()) && ft.getDescripcion().equals(this.getDescripcion());

	}


	@Override
	public String toString() {
		return "fotosPantalla [enrutado=" + enrutado + ", descripcion=" + descripcion + ", respuestaPaciente="
				+ respuestaPaciente + "]";
	}
	
	
	
}