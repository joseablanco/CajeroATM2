package cajeroatm;

import javax.swing.SwingUtilities;

public class CajeroATM {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}
