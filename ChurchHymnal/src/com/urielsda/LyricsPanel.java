package com.urielsda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import jaco.mp3.player.MP3Player;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
            


public class LyricsPanel extends Thread{
    
    JFrame mainFrame = new JFrame();
    JPanel mainPanel = new JPanel();
    JPanel buttonPanel = new JPanel(new GridBagLayout());
    JTextArea lyricsTextArea = new JTextArea(29,20);
    JButton playButton = new JButton("Play");
    JButton pauseButton = new JButton("Pause");
    JButton stopButton = new JButton("Stop");
    //JScrollPane scrollPane = new JScrollPane(lyricsTextArea);
    
    String recievedHymnalValue;
    
    public void run() {
        
        mainFrame.setSize(600, 500);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setTitle("Hymn #: "+ recievedHymnalValue);
        //mainPanel.setBorder ( new TitledBorder ( new EtchedBorder (), recievedHymnalValue) );
        
        MP3Player player = new MP3Player(new File(getAudioPathValue()));
        
        
        try {
            lyricsTextArea.append(getFileContent());
        } catch (IOException ex) {
            Logger.getLogger(LyricsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        mainPanel.setBackground(Color.BLACK);   
        lyricsTextArea.setEditable(false);  
        lyricsTextArea.setFont(new java.awt.Font("Tahoma", 3, 25));
        lyricsTextArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(lyricsTextArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //scrollPane.isWheelScrollingEnabled();
        //scrollPane.setToolTipText("Scroll up / down to view the lyrics");
        
        //JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(stopButton);
        buttonPanel.setBackground(Color.BLACK);  
            

   
        mainPanel.add(scrollPane);        
        mainFrame.add(mainPanel);
        mainFrame.add(buttonPanel,BorderLayout.SOUTH); 
        
        mainFrame.pack();
        
        
        playButton.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { 
            player.play();
            } 
            } );
        pauseButton.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { 
            player.pause();
            } 
            } );
       stopButton.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { 
            player.stop();
            } 
            } );
       }
    
    private String getAudioPathValue()
    {
        final String audiodir = System.getProperty("user.dir"); 
        String audioPath = audiodir + "/sources/Audio/"+ recievedHymnalValue + ".mp3";
        return audioPath;
    }
    
    private String getFileContent() throws FileNotFoundException, IOException {
            final String dir = System.getProperty("user.dir");
            String pathdir = dir + "/sources/lyrics/SongLyrics/"+recievedHymnalValue + ".txt";
            System.out.println(pathdir);
            InputStream is = new FileInputStream(pathdir); 
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
             String line = buf.readLine(); 
             StringBuilder sb = new StringBuilder(); 
             while(line != null){ sb.append(line).append("\n"); 
             line = buf.readLine();
             } 
             String fileAsString = sb.toString(); 
             buf.close();
             return fileAsString;
    }
}
    
    