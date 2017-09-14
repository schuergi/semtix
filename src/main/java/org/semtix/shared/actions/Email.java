package org.semtix.shared.actions;

//import org.semtix.shared.daten.ArrayHelper;
//import org.semtix.shared.daten.StringHelper;

import javax.swing.*;
//import java.awt.*;
import java.io.IOException;
//import java.net.URI;

/**
 * Sends a mail
 * <p>
 * Created by Mi on 02.10.15.
 */

/* java.desktop class is not more supported under linux - java.desktop has been written for now outdated gnome2
 * TODO: undo the encoding of spaces and newlines in OptionPanelAntrag.java
 * TODO: instead of sloppy and dirty delegating it to the shell, use a ProcessBuilder
 * see
 * -fx
 */

public class Email {
    public static void send(String emailAdress, String strURI) {
        if (emailAdress == null || emailAdress.isEmpty() || emailAdress.equals("")) {
            String message = "Es ist keine Mailadresse eingetragen. Oder E-Mails werden nicht unterstützt.";
            JOptionPane.showMessageDialog(null, message, "Fehler", JOptionPane.ERROR_MESSAGE);
        } else {
            strURI = strURI.replaceAll("%20", " ");
            strURI = strURI.replaceAll("%0A", "\n");
            String EmailCommandString = new String("xdg-email ").concat(strURI + " " + emailAdress);
            System.out.println(EmailCommandString);
            try {
                Runtime.getRuntime().exec(new String[]{"bash", "-c", EmailCommandString});
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "[FEHLER] Konnte Mailprogramm nicht starten. \r\n" + e.getLocalizedMessage());
            }

           /* Desktop desktop = Desktop.getDesktop();
            try {
                desktop.mail(new URI(strURI));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "[FEHLER] Konnte Mailprogramm nicht starten. \r\n" + e.getLocalizedMessage());

            }
            */
        }

    }
}
