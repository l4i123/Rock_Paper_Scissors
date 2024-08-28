package main;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // must be implemented actionListener.actionPerformed(actionEvent)

// Custom Button class (опционално)
class Button extends JButton  {
    public Button(String text, int x, int y, int width, int height) {
        super(text); // Вика конструктора на JButton и задава текста на бутона
        this.setBounds(x, y, width, height); // Задава позиция и размер на бутона
    }

    
    
}