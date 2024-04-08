//EQUIPO D: Ana Castella / Areli Maza



import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyTextField extends JTextField implements KeyListener {

    public MyTextField() {
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        // Permitir solo números del 1 al 9, '(' y ')' y espacio
        if (!((c >= '0' && c <= '9') || c == '(' || c == ')' || c == ' ') ||
                // Permitir solo un par de paréntesis
                (c == '(' && this.getText().contains("(")) ||
                (c == ')' && this.getText().contains(")")) ||
                // Limitar la longitud del texto a 13 caracteres
                (this.getText().length() >= 13)) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Verificar si el texto coincide con el formato requerido
        if (this.getText().isEmpty() || this.getText().matches("\\(\\d{3}\\) \\d{7}")) {
            // Si es válido, establecer el color de fondo a blanco y el color del texto a rojo
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        } else {
            // Si no es válido, establecer el color de fondo a rojo y el color del texto a blanco
            this.setBackground(Color.RED);
            this.setForeground(Color.WHITE);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}