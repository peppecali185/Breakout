/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
    private int delay=5;
    private int playerX=310;  //posizione iniziale giocatore
    private int ballposX=120; //posizione iniziale asse X palla
    private int ballposY=250; //posizione iniziale asse Y palla
    private int ballXdir=-1;
    private int ballYdir=-2;
    private MapGenerator map;
    
    public GamePlay(){
        map=new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start(); //parte il timer
    }
    
    public void paint (Graphics g){ //grafica del pannello
        g.setColor(Color.black);    //colore nero di background pannello
        g.fillRect(1, 1, 692, 592); //rimpie il pannello di nero
        
        //disegna i blocchi da distruggere
        map.draw((Graphics2D) g); //ho castato graphics in graphics2d
        
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
        g.fillOval(ballposX, ballposY, 20, 20);  //inserisce la palla nelle posizioni iniziali x e y prestabilite
       
        g.dispose();
    }
            
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override  //METODO CHE ENTRA IN FUNZIONE QUANDO UN TASTO VIENE PREMUTO
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){   //quando premo il tasto freccia di destra
            if(playerX>=600){         //controllo che il pannello non esce dai bordi (per il 600 controlla nel main nella posizione e grandezza del frame)
                playerX=600;          //lo faccio restare al limite del bordo
            }
            else{
                moveRight();         //altrimenti faccio muovere il pannello a destra
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){   //quando premo il tasto freccia di sinistra
            if(playerX<10){
                playerX=0;
            }
            else{
                moveLeft();
            }
        }
    }
    public void moveRight(){
        play=true;
        playerX+=20;
    }
    public void moveLeft(){
        play=true;
        playerX-=20;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {   //CAPTA azioni che succedono all'interno del gioco
        timer.start();
        if(play){ //se abbiamo premuto tatsto destro o sinistro
            if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))){  //creo un rettangolo attorno alla pallina e un rettangolo attorno al pannello.
                ballYdir=-ballYdir;                                                                   //se i due rettangoli entrano in collisione (.intersects) fai cambiare la direzione della pallina
            }
            for(int i=0;i<map.map.length;i++){
                for(int j=0;j<map.map[0].length;j++){
                    if(map.map[i][j]>0){                  //se il blocconon è ancora stato distrutto...
                        int brickX=j*map.brickWidth+80;   //posizione asse X del blocco
                        int brickY=i*map.brickHeight+50;
                        int brickWidth=map.brickWidth;
                        int brickHeight=map.brickHeight;
                        
                        Rectangle rect=new Rectangle(brickX,brickY,brickWidth,brickHeight);  //crea un nuovo rettangolo immaginario per ciascun blocchetto
                        Rectangle ballRect =new Rectangle(ballposX,ballposY,20,20);
                        Rectangle brickRect=rect;
                        if(ballRect.intersects(brickRect)){  //se il rettangolo della palla e quello del blocco entrano in collisionee
                            map.setBrickValue(0, i, j);      //il valore del blocco diventa 0
                            totalBricks--;                   //i mattoncini totali decrementano
                            score+=5;                        //aumento il punteggio di 5
                            
                            if(ballposX+19<=brickRect.x||ballposX+1>=brickRect.x+brickRect.width){
                                ballXdir=-ballXdir;          //cambio direzione alla palla
                            }else{
                                ballYdir=-ballYdir;
                            }
                        }
                    }
                }
            }
            ballposX+=ballXdir;
            ballposY+=ballYdir;
            if(ballposX<0){         //se la pallina tocca la parete sinistra
                ballXdir=-ballXdir;
            }
            if(ballposY<0){         //se la pallina tocca il soffitto
                ballYdir=-ballYdir;
            }
            if(ballposX>670){
                ballXdir=-ballXdir;
            }
        }
        repaint(); //richiama il metodo paint più volte altrimenti verrebe richimato una sola volta
    }
    
    
    
}
