package com.urielsda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LyricsPanel extends Thread {
    JFrame jf = new JFrame();
    JPanel jp = new JPanel();
    JLabel lb = new JLabel();
    String recievedHymnalValue = "1-Praise to the Lord";
    SDAHymnal mainDialog = new SDAHymnal();

    public void run() {
        jf.setSize(300, 200);

        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setTitle("Lyrics");
        jp.setLayout(null);
        lb.setText(recievedHymnalValue);
        lb.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lb.setBounds(90, 50, 300, 60);
        jp.add(lb);
        jf.add(jp);
       }




        String RetunsSting () {
        final String dir = System.getProperty("user.dir");
        String fullpath = dir + "\\ChurchHymnal\\sources\\SongLyrics\\1-Praise to the Lord.csv"; // this is to be modified on netbeans
        System.out.println("current dir = " + dir);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fullpath));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SDAHymnal.class.getName()).log(Level.SEVERE, null, ex);
        }

        String st;
        int index = 0;
        try {
            while ((st = br.readLine()) != null) {
                System.out.println(st);
            }
        } catch (Exception e) {}
        br.close();


    }

