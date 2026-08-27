package clases;

public class Medico{
	private int registro;
	private int mesLicencia;
	private int porcentajeCostoConsulta;
	private String sitioWeb;
	
	public Medico(
			int reg,
			int mesLic,
			int pcc,
			String sitioW
			) {
		this.registro = reg;
		this.mesLicencia = mesLic;
		this.porcentajeCostoConsulta = pcc;
		this.sitioWeb = sitioW;
	}
}