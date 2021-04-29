package Juego;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;
import java.util.Random;

public class Tablero extends JFrame {

    int spacing = 2;
    int alt = 16;
    int anch = 16;
    int min = 10;
    int mx = 0;
    int my = 0;
    int vecino = 0;
    boolean lose = false;
    boolean win = false;

    Random rand = new Random();
    int[][] minas = new int[alt][anch];
    int[][] vecinos = new int[alt][anch];
    boolean[][] visible = new boolean[alt][anch];


    public int boxY(){
        for (int i = 0; i < alt; i++) {
            for (int j = 0; j < anch; j++) {
                if (mx >= spacing + i * 1280 / alt && mx < i * 1280 / alt + 1280 / alt - spacing && my >= spacing + j * 800 / anch + 29 && my < j * 800 / anch + 800 / anch + spacing + 29) {
                return i;}
            }
        }

        return -1;
    }

    public int boxX (){
        for (int i = 0; i < alt; i++) {
            for (int j = 0; j < anch; j++) {
                if (mx >= spacing + i * 1280 / alt && mx < i * 1280 / alt + 1280 / alt - spacing && my >= spacing + j * 800 / anch + 29 && my < j * 800 / anch + 800 / anch + spacing + 29) {
                    return j;}
            }
        }
        return -1;

    }

    public void revisarVictoria(){

        for (int i = 0; i < alt; i++) {
            for (int j = 0; j < anch; j++) {
                if (visible[i][j] == true && minas[i][j] == 1) {
                    lose = true;
                }

            }
        }
        if (casillasReveladas() >= anch*alt - totalMinas()) {
            win = true;
        }
    }

    public int totalMinas() {
        int total = 0;
        for (int i = 0; i < alt; i++) {
            for (int j = 0; j < anch; j++) {
                if (minas[i][j] == 1) {
                    total++;
                }
            }
        }
        return total;
    }
    public int casillasReveladas(){
        int total = 0;
        for (int i = 0; i < alt; i++) {
            for (int j = 0; j < anch; j++) {
                if (visible[i][j] == true) {
                    total++;
                }
            }
        }
        return total;
    }

    public Tablero() {
        this.setTitle("Buscaminas");
        this.setSize(1295, 829);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        while (min > 0) {
            for (int i = 0; i < alt; i++) {
                for (int j = 0; j < anch; j++) {
                    if (rand.nextInt(100) < 10 && min > 0) {
                        minas[i][j] = 1;
                        min = min - 1;
                    } else if (minas[i][j]==1) {
                        minas[i][j] = 1;
                    } else if (minas[i][j]==0){
                        minas[i][j] = 0;
                    }

                }
            }
        }

        for (int i = 0; i < alt; i++) {
            for (int j = 0; j < anch; j++) {
                vecino = 0;
                for (int m =0; m < alt; m++ ){
                    for (int n=0; n < anch; n++){
                        if(!(m==i && n == j )) {
                            if (isN(j, i, n, m) == true)
                                vecino++;
                        }
                    }
                }
                vecinos[i][j] = vecino;
            }
        }

        for (int i = 0; i < alt; i++) {
            for (int j = 0; j < anch; j++) {
                visible[i][j] = false;
            }
        }

        Grid grid = new Grid();
        this.setContentPane(grid);

        Menu menu = new Menu();

        Mover mover = new Mover();
        this.addMouseMotionListener(mover);

        Click click = new Click();
        this.addMouseListener(click);

    }

    public class Grid extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.black);
            g.fillRect(0, 0, 1280, 800);
            for (int i = 0; i < alt; i++) {
                for (int j = 0; j < anch; j++) {
                    g.setColor(Color.DARK_GRAY);
                    /*if (minas[i][j]==1){
                        g.setColor(Color.red);
                    }*/
                    if (vecinos[i][j] == 0 && minas[i][j] != 1) {
                        visible[i][j] = true;
                    }
                    if (visible[i][j] == true){
                        g.setColor(new Color (0,102,0));
                        if (minas[i][j] == 1){
                            g.setColor(Color.RED);
                            lose = true;
                        }
                    }
                    if (mx >= spacing + i * 1280/alt && mx < i*1280/alt +1280/alt - spacing  && my >= spacing + j * 800/anch + 29  && my < j*800/anch + 800/anch + spacing + 29){
                        g.setColor(new Color(0,153,0));
                    }
                    g.fillRect(spacing + i * 1280/alt , spacing + j * 800/anch , 1280/alt -  2*spacing, 800/anch - 2*spacing);
                    if(visible[i][j] == true){
                        if (minas[i][j] == 0){
                            g.setColor(Color.white);
                            g.setFont(new Font("Tahoma", Font.BOLD,(anch*alt)/5));
                            if(vecinos[j][i] != 0) {
                                g.drawString(Integer.toString(vecinos[j][i]), i * 1280 / alt + 5*spacing , j * 800 / anch + 800 / anch - 5*spacing);
                                }
                            } else {
                            g.setColor(Color.BLACK);
                            g.fillOval(spacing + i * 1280/alt+4*anch,spacing + j * 800/anch, 800/anch - anch, 800/anch - anch);
                        }
                    }
                }
            }
        }

    }

    public class Mover implements MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent e) {


        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mx = e.getX();
            my = e.getY();
        }
    }

    public class Click implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            if(boxY() != -1 && boxX() != -1){
                System.out.println("El mouse esta en X:" + boxY()+" Y:"+ boxX() +"," + " Minas al rededor: " + vecinos[boxX()][boxY()]);
            }
            if(boxY() != -1 && boxX() != -1){
                visible[boxY()][boxX()] = true;
            }




        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    public boolean isN(int mX, int mY, int cX, int cY ){
        if(mX - cX < 2 && mX - cX > -2 && mY - cY < 2 && mY - cY > -2 && minas[cX][cY] == 1){
            return true;
        }
        return false;
    }
    public void transformartripleta(int sparseMatrix[][]) {
        int size = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (sparseMatrix[i][j] != 0) {
                    size++;
                }
            }
        }

        // number of columns in compactMatrix (size) must be
        // equal to number of non - zero elements in
        // sparseMatrix
        int compactMatrix[][] = new int[3][size];

        // Making of new matrix
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (sparseMatrix[i][j] != 0) {
                    compactMatrix[0][k] = i;
                    compactMatrix[1][k] = j;
                    compactMatrix[2][k] = sparseMatrix[i][j];
                    k++;
                }
            }
        }
    }

}