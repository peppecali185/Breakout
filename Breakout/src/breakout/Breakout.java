/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakout;

import javax.swing.JFrame;

/**
 *
 * @author peppe
 */
public class Breakout {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame obj= new JFrame();        //nuovo frame   
        GamePlay gamePlay =new GamePlay();//oggetto di tipo gameplay
        
        obj.setBounds(10, 10, 700,600);  //grandezza del frame e posizione
        obj.setTitle("Breakout");        //titolo del frame
        obj.setResizable(false);         //non può essere ingrandito o diminuito dall'utente
        obj.setVisible(true);            //rendo il frame visibile
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//quando premo la X si chiude l'intera applicazione
        obj.add(gamePlay);               //aggiungo al frame principale il pannello del gameplay
        
        
        
    }
    
}
