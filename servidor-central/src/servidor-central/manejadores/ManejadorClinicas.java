package manejadores;

public class ManejadorClinicas {
	private static ManejadorClinicas instancia;
	
	private ManejadorClinicas() {
		
	};
	
	public static ManejadorClinicas getInstance() {
		if (instancia != null) instancia = new ManejadorClinicas();
		return instancia;
	}

}
