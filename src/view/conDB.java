package view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static config.Config.logo;

public class conDB extends Frame {

    public conDB(Frame main){
        setIconImage(logo.getImage());
        setLayout(null);
        setBounds(0,0,600,800);
        setResizable(false);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                main.setEnabled(true);
                conDB.this.dispose();
            }
        });
    }
}
