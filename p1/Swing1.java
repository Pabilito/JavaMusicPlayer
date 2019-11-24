package p1;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.sound.sampled.AudioInputStream;	
import javax.sound.sampled.AudioSystem; 		
import javax.sound.sampled.Clip;
// music
import javax.swing.*;

class Swing1 implements ActionListener{
	Clip currentClip;
	boolean isPlaying = false;
	String m1 = "Warriors (ft. Imagine Dragons)  Worlds 2014 - League of Legends.wav";
	String m2 = "SABATON - 401 (Official Music Video).wav";
	String m3 = "Dynoro & Gigi D’Agostino - In My Mind.wav";
	String currentMusic = m1;
	String musicPlaying;
	JLabel jlab;
	JLabel jlab2;
	JLabel jlab3;
	JFrame jfrm;
	JCheckBox c1;
	ButtonGroup bg1 = new ButtonGroup();
	JRadioButton rb1 = new JRadioButton("Sea and glass", true);
	JRadioButton rb2 = new JRadioButton("Metalic");
	static long timePosition = 0;
	JButton jbtn1 = new JButton("Play");
	JButton jbtn2 = new JButton("Next");
	JButton jbtn3 = new JButton("Previous");
	BufferedImage image = null;
	boolean switchLookEnabled = true;
			
	Swing1(){
		jfrm = new JFrame("Music Player. 3 songs available!");
		JLayeredPane layeredPane = new JLayeredPane();
		jfrm.setResizable(false);
		Image galaxy = Toolkit.getDefaultToolkit().getImage("galaxy810.png");
		//Image img1 = Toolkit.getDefaultToolkit().getImage("galaxy1.png");
		//Image img2 = Toolkit.getDefaultToolkit().getImage("galaxy2.png");
		//Image img3 = Toolkit.getDefaultToolkit().getImage("galaxy3.png");
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
	    JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel(){
	         /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
	        	 super.paintComponent(g);
	        	 g.drawImage(galaxy, 0, 0, null);
			}
		};
		      
		panel1.setBounds(0,0,450,50);
		panel1.setBackground(Color.gray);
		jfrm.setIconImage(new ImageIcon("dragon.png").getImage());    
        //panel1.setForeground(Color.white);
		panel2.setBounds(0,50,450,50);  
		panel2.setBackground(Color.red);
		panel3.setBounds(0,100,450,50);    
        panel3.setBackground(Color.blue);
		panel4.setBounds(0,150,450,50);
		jfrm.setSize(450,225);
		layeredPane.setPreferredSize(new Dimension(810, 420));
		BoxLayout boxLayout = new BoxLayout(jfrm.getContentPane(), BoxLayout.Y_AXIS); // top to bottom
		jfrm.setLayout(boxLayout);
		//jbtn1.setForeground(Color.red);
		//jbtn2.setForeground(Color.blue);
		//jbtn3.setForeground(Color.blue); 
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jlab = new JLabel(currentMusic);
		jlab2 = new JLabel("Please choose a song!");
		jlab3 = new JLabel("Song to be played: ");
		jlab.setForeground(Color.green);
		jlab2.setForeground(Color.green);
		jlab3.setForeground(Color.cyan);
		jfrm.setLocationRelativeTo(null); //Putting window in the middle of the screen
		jfrm.setVisible(true);
		jbtn1.addActionListener(this);
		jbtn2.addActionListener(this);
		jbtn3.addActionListener(this);
		c1 = new JCheckBox("Enable switching 'Look and Feel'");
		c1.setSelected(true);
		rb1.addActionListener(this);
		rb2.addActionListener(this);
		c1.addActionListener(this);
		
		bg1.add(rb1);
		bg1.add(rb2);
		jfrm.add(layeredPane);
		panel1.add(jlab3);
		panel1.add(jlab);	
		panel2.add(jbtn3);
		panel2.add(jbtn1);
		panel2.add(jbtn2);
		panel3.add(jlab2);
		panel4.add(c1);
		panel4.add(rb1);
		panel4.add(rb2);
		//layeredPane.setOpaque(true);
		layeredPane.add(panel1, new Integer(1));
		layeredPane.add(panel2, new Integer(1));
		layeredPane.add(panel3, new Integer(1));
		layeredPane.add(panel4, new Integer(1));
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("Play")) {
			if(isPlaying) {
			timePosition = stopMusic(currentClip);
			}
			isPlaying = true;
			timePosition = 0;
			currentClip = playMusic(currentMusic);
			jbtn1.setText("Stop");
			musicPlaying = currentMusic;
			jlab2.setText("Currently playing: " + currentMusic);
		}else if(ae.getActionCommand().equals("Previous")) {
			if(currentMusic == m1) {
				currentMusic = m3;
			}else if(currentMusic == m2) {
				currentMusic = m1;
			}else if(currentMusic == m3) {
				currentMusic = m2;
			}
			jlab.setText(currentMusic);
			if(currentMusic == musicPlaying && isPlaying) {
				jbtn1.setText("Stop");
			}else if(currentMusic == musicPlaying) {
				jbtn1.setText("Resume");
			}else {
				jbtn1.setText("Play");
			}
		}else if(ae.getActionCommand().equals("Next")) {
			if(currentMusic == m1) {
				currentMusic = m2;
			}else if(currentMusic == m2) {
				currentMusic = m3;
			}else if(currentMusic == m3) {
				currentMusic = m1;
			}
			jlab.setText(currentMusic);
			if(currentMusic == musicPlaying && isPlaying) {
				jbtn1.setText("Stop");
			}else if(currentMusic == musicPlaying) {
				jbtn1.setText("Resume");
			}else {
				jbtn1.setText("Play");
			}
		}else if(ae.getActionCommand().equals("Stop")) {
			isPlaying = false;
			jbtn1.setText("Resume");
			timePosition = stopMusic(currentClip);
			jlab2.setText("Currently paused: " + currentMusic);
		}else if(ae.getActionCommand().equals("Resume")) {
			isPlaying = true;
			currentClip = playMusic(currentMusic);
			jbtn1.setText("Stop");
			musicPlaying = currentMusic;
			jlab2.setText("Currently playing: " + currentMusic);
		}else if(ae.getActionCommand().equals("Sea and glass")) {
			if(switchLookEnabled == true) {
				try {
					UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SwingUtilities.updateComponentTreeUI(jfrm);
				jfrm.setMinimzable(false);
			}
		}else if(ae.getActionCommand().equals("Metalic")){
			if(switchLookEnabled == true) {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				}catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			SwingUtilities.updateComponentTreeUI(jfrm);
			}
		}else if(ae.getActionCommand().equals("Enable switching look and feel")){
			if(switchLookEnabled == true) {
				rb1.setEnabled(false);
				rb2.setEnabled(false);
			}else {
				rb1.setEnabled(true);
				rb2.setEnabled(true);
			}
			switchLookEnabled = !switchLookEnabled;
		}
	}
	
	public static Clip playMusic(String nameOfSong) {
			try {
				File musicpath = new File(nameOfSong);
				
				if(musicpath.exists()) {
					AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicpath);
					Clip myClip = AudioSystem.getClip();
					myClip.open(audioInput);
					myClip.setMicrosecondPosition(timePosition); //3s fix
					myClip.start();
					myClip.loop(Clip.LOOP_CONTINUOUSLY);
					
					/*
					JOptionPane.showMessageDialog(null, "Click OK to pause");
					timePosition = myClip.getMicrosecondPosition();
					myClip.stop();
					
					JOptionPane.showMessageDialog(null, "Click OK to resume");
					myClip.setMicrosecondPosition(timePosition);
					myClip.start();
					*/
					return myClip;
				}else {
					JOptionPane.showMessageDialog(null, "Wrong path");
					System.exit(0);
				}
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Somehow cannot play music");
				System.exit(0);
			}
			return null; //XD
		}
	
	public static long stopMusic(Clip playedClip) {
		playedClip.stop();
		return playedClip.getMicrosecondPosition();
	}

	public static void main(String args[]) {
		//string name = UIManager.getLookAndFeel();
		try {
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Swing1();
			}
		});
	}
}