/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author yandiio
 */
public class Koneksi {

    public static java.sql.Connection getKoneksi()  {
        String[] dataKoneksi = Koneksi.readSettings();
        String username = dataKoneksi[0];
        String port = dataKoneksi[1];
        String host = dataKoneksi[2];
        String db = dataKoneksi[3];
        String password = dataKoneksi[4];

        java.sql.Connection Koneksi = null;
        String konString = "jdbc:mysql://" + host + ":" + port + "/" + db;

        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Koneksi = DriverManager.getConnection(konString, username, password);
            System.out.println("Koneksi Berhasil");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Koneksi Gagal");
            Koneksi = null;
        }

        return Koneksi;
    }

    private static String[] readSettings() {
        String[] data = new String[5];
        BufferedReader bf = null;
        FileReader fr = null;

        try {
            bf = new BufferedReader(fr);
            fr = new FileReader("data//Settings.myf");
            String curLine;

            int i = 1;
            while ((curLine = bf.readLine()) != null) {
                data[i] = curLine;
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
                if (bf != null) {
                    bf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return data;
    }

}
