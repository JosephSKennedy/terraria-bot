package com.GameArc.TerBot.GUI;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Gui extends JFrame{
	private static final long serialVersionUID = 8656625305547397842L;

	public Gui(){
		super("Terraria JBot");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		getContentPane().setPreferredSize(new Dimension(250, 250));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}