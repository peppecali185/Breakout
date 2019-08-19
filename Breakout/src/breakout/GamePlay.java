/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author peppe
 * pannello all'interno del frame principale nel quale avverrà il gioco vero e proprio
 */
public class GamePlay extends JPanel implements KeyListener,ActionListener{

    private boolean play=false;
    private int score=0;
    private int totalBricks=21;
    private Timer timer;  //setta il timer della palla (quanto veloce deve muoversi)
    private int delay=8;
    private int playerX=310;  //posizione iniziale giocatore
    private int ballposX=120; //posizione iniziale asse X palla
    private int ballposY=250; //posizione iniziale asse Y palla
    private int ballXdir=-1;
    private int ballYdir=-2;
    
    public GamePlay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start(); //parte il timer
    }
    
    public void paint (Graphics g){ //grafica del pannello
        g.setColor(Color.black);    //colore nero di background pannello
        g.fillRect(1, 1, 692, 592); //rimpie il pannello di nero
        
        //bordo
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        
        //pannello che si muove
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);  //(playerX è la posizione iniziale del giocatore)
        
        //palla
        g.setColor(Color.yellow);
        g.fillRect(ballposX, ballposY, 20, 20);  //inserisce la palla nelle posizioni iniziali x e y prestabilite
        
    }
            
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override  //METODO CHE ENTRA IN FUNZIONE QUANDO UN TASTO VIENE PREMUTO
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){   //quando premo il tasto freccia di destra
            if(playerX>=600){         //controllo che il pannello non esce dai bordi (per il 600 controlla nel main nella posizione e grandezza del frame)
                
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){   //quando premo il tasto freccia di destra
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
