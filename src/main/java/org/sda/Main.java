package org.sda;

import org.sda.consoleApp.WeatherDataGui;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                WeatherDataGui app = new WeatherDataGui();
                app.display();
            }
        });
    }
}
