package Juego;
import javax.swing.*;

public class Main implements Runnable {

    Tablero tablero = new Tablero();

    public static void main(String[] args) {
        new Thread(new Main()).start();
    }
    @Override
    public void run() {
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null,"1.Facil\n 2.Medio \n 3.Dificil", "Menu",JOptionPane.PLAIN_MESSAGE));
        if (opcion == 1){
            tablero.alt = 8;
            tablero.anch = 8;
            tablero.min = 5;
        }
        else if (opcion == 2){
            tablero.alt = 10;
            tablero.anch = 10;
            tablero.min = 40;
        }
        else if (opcion == 3){
            tablero.alt = 16;
            tablero.anch = 16;
            tablero.min = 99;
        }
        while (true) {
            tablero.repaint();
            tablero.revisarVictoria();
            if (tablero.win == true) {
                JOptionPane.showMessageDialog(null, "¡Ganaste!");
                System.exit(1);
            }
            if (tablero.lose == true) {
                JOptionPane.showMessageDialog(null, "Perdiste");
                System.exit(1);
            }

        }
    }
}
