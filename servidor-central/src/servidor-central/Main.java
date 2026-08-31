
import javax.swing.UIManager;


public class Main {
    public static void main(String[] args) {
    UIManager.put("swing.boldMetal", Boolean.FALSE);
    VentanaPrincipal ventana =new VentanaPrincipal();
    ventana.setVisible(true);
    }
}