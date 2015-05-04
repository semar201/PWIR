package Firma_przewozowa;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javafx.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class start
{
	public static void main(String[] args) 
	{

		
		Punkt_baza Pbaza=new Punkt_baza();
		Pbaza.generuj();
		Trasa_baza Tbaza=new Trasa_baza(Pbaza);
		Firma wypozyczalnia=new Firma(5,10);
		
        
	    Panel mainFrame = new Panel(Pbaza,wypozyczalnia);
		mainFrame.setVisible(true);
		
		Pbaza.fabryki_start(wypozyczalnia,Tbaza);
		while(true)
		{
			mainFrame.odswiez(wypozyczalnia,Pbaza);
            try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}


	
}
