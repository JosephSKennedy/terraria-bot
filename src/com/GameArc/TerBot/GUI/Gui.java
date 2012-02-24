package com.GameArc.TerBot.GUI;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Gui extends JFrame{
	private static final long serialVersionUID = 8656625305547397842L;

	public Gui(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		setTitle("Terraria JBot");
		setBounds(100, 100, 250, 250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}