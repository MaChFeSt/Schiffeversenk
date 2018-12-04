package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Network.Client;
import Network.Host;


public class Gui extends Thread {
	
	
	public JPanel boardpanel = new JPanel();
	
	public JPanel	boardtop = new JPanel(),
					boardbottom = new JPanel(),
					boardleft = new JPanel(),
					boardright = new JPanel();
	 
	 
	
	public Gui(){
	
	//*****************JFRAME******************
	 JFrame frame = new JFrame();	 
	 frame.setTitle("Schiffe versenken");
	 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 frame.setSize(1280,720);
	 frame.setVisible(true);
	 frame.setLocationRelativeTo(null);
	 frame.setResizable(false);
	
	 

	 
	 //****************JPANEL LOGO********************
	 JPanel panellogo = new JPanel();
	 boardtop.add(panellogo);

	 panellogo.setOpaque(false);
	 
	 ImageIcon iconlogo = new ImageIcon(Gui.class.getResource("logo.png"));
	 Image iconlogoi = iconlogo.getImage().getScaledInstance(580,260, Image.SCALE_SMOOTH);
	 JLabel logo = new JLabel(iconlogo);
	 logo.setIcon(new ImageIcon(iconlogoi));
	 
	 Color customColor = new Color(85,100,191);
	 boardtop.setBackground(customColor);
	 boardtop.setOpaque(true);
	 panellogo.add(logo);
	 



	 
	 //****************JPANEL BUTTONS********************
	 JPanel panel = new JPanel(new GridLayout(5, 2));
	 panel.setOpaque(false);
	 panel.setPreferredSize(new Dimension(300, 400));
	 
	 boardpanel.setLayout(new BorderLayout());
	 boardpanel.add(panel, BorderLayout.CENTER);
	 
	 

	  	//einzelne Borderpanels in das Spielfeld panel
		boardpanel.add(boardtop, BorderLayout.NORTH);
		boardtop.setOpaque(true);
		boardtop.setPreferredSize(new Dimension (1280,270));
	 
		boardpanel.add(boardleft, BorderLayout.WEST);
		boardleft.setOpaque(false);
		boardleft.setPreferredSize(new Dimension (490,400));
		
		boardpanel.add(boardright, BorderLayout.EAST);
		boardright.setOpaque(false);
		boardright.setPreferredSize(new Dimension (490,400));
//		add(boardpanel);
		
		//****************JPANEL NAME********************
		 boardpanel.add(boardbottom, BorderLayout.SOUTH);
		 boardbottom.setOpaque(true);
		 boardbottom.setPreferredSize(new Dimension(1280,50));
		 JPanel panelname = new JPanel();
		 boardbottom.add(panelname);	 
		 JLabel name = new JLabel("Created by: Stefanie, Feyzanur, Christian, Marcus");
		 panelname.add(name);
		
	 	 
	//****************VERSUS KI********************
	 ImageIcon iconversusai = new ImageIcon(Gui.class.getResource("Versus AI.png"));
	 JButton versusai = new JButton(iconversusai);
	 versusai.setIcon(iconversusai);
	 versusai.setContentAreaFilled(false);
	 versusai.setBorderPainted(false);
	 versusai.setOpaque(true);
	 panel.add(versusai);
	 
	 //****************NETWORK GAME********************
	 ImageIcon iconnetwork = new ImageIcon(Gui.class.getResource("network.png"));
	 JButton network = new JButton(iconnetwork);
	 network.setIcon(iconnetwork);
	 network.setContentAreaFilled(false);
	 network.setBorderPainted(false);
	 network.setOpaque(true);
	 panel.add(network);

	//****************SETTINGS********************
	 ImageIcon iconsettings = new ImageIcon(Gui.class.getResource("settings.png"));
	 JButton settings = new JButton(iconsettings);
	 settings.setIcon(iconsettings);
	 settings.setContentAreaFilled(false);
	 settings.setBorderPainted(false);
	 settings.setOpaque(true);
	 panel.add(settings);
	
	//****************HELP********************
	 ImageIcon iconhelp = new ImageIcon(Gui.class.getResource("help.png"));
	 JButton help = new JButton(iconhelp);
	 help.setIcon(iconhelp);
	 help.setContentAreaFilled(false);
	 help.setBorderPainted(false);
	 help.setOpaque(true);
	 panel.add(help);
		
	//****************QUIT********************
	 ImageIcon iconquit = new ImageIcon(Gui.class.getResource("quit.png"));
	 JButton quit = new JButton(iconquit);
	 quit.setIcon(iconquit);
	 quit.setContentAreaFilled(false);
	 quit.setBorderPainted(false);
	 quit.setOpaque(true);
	 panel.add(quit);
	
	 quit.addActionListener(new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
			 System.exit(0);
		 }
	 });
	 
	//****************ACTION LISTENER VERSUS KI********************
	 versusai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	panel.setVisible(false);
            	new Screen();
            	frame.setVisible(false);
            	
	 
            }
        });
	 
	//****************ACTION LISTENER NETWORK********************
	 network.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	panel.setVisible(false);
            	JPanel panelnw = new JPanel();
        		panelnw.setVisible(true);
        		boardpanel.add(panelnw);
        		 
        		 
        		 ImageIcon iconjoin = new ImageIcon(Gui.class.getResource("join.png"));
        		 JButton join = new JButton(iconjoin);
        		 join.setIcon(iconjoin);
        		 join.setContentAreaFilled(false);
        		 join.setBorderPainted(false);
        		 join.setOpaque(true);
        		 panelnw.add(join);
        		
        		 
        		 join.addActionListener(new ActionListener() {
	     	            public void actionPerformed(ActionEvent e)
	     	            {
	     	            	panelnw.setVisible(false);
	     	            	JPanel paneljoin = new JPanel();
		   	        		paneljoin.setVisible(true);
		   	        		 
		   	        		JLabel labeljoin = new JLabel("Enter IP adress:");
			        		paneljoin.add(labeljoin);
			        		 
			        		JTextField textjoin = new JTextField(20);
			        		paneljoin.add(textjoin);
			        		textjoin.addActionListener(new java.awt.event.ActionListener(){
								public void actionPerformed(ActionEvent e){
									String eingabe = textjoin.getText();

									Client c = new Client(eingabe, 4444);
									c.start();
								}
							});

			        		
			        		boardpanel.add(paneljoin);
			        		 
			        		
			        		
			        		textjoin.addActionListener(new java.awt.event.ActionListener() {
			        			public void actionPerformed(ActionEvent e) {
			        				//Spiel Joinen
			        			}
			        			
			        		});
			        		
	     	            }
	        		 });
        		 
        		 
        		 
        		 ImageIcon iconhost = new ImageIcon(Gui.class.getResource("host.png"));
        		 JButton host = new JButton(iconhost);
	        		 host.setIcon(iconhost);
	        		 host.setContentAreaFilled(false);
	        		 host.setBorderPainted(false);
	        		 host.setOpaque(true);
        		 panelnw.add(host);
        		 
        		 host.addActionListener(new ActionListener() {
	     	            public void actionPerformed(ActionEvent e)
	     	            {
	     	            	panelnw.setVisible(false);
	     	            	JPanel panelhost = new JPanel();
	     	            	panelhost.setVisible(true);
	     	            	
	     	            	Host host1 = new Host(4444);
							String ip = host1.getIP();
	   	        		 
	     	            	JLabel labelhost = new JLabel("Your IP adress: " + ip);
	     	            	panelhost.add(labelhost);
	     	            	
	     	            	host1.start();
	     	            	
	     	            	
	   	        		 
			        		boardpanel.add(panelhost);
	     	            }
	        		 });
        		 
            }
        });
	 
	//****************ACTION LISTENER SETTINGS********************
	 settings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	panel.setVisible(false);
            	JPanel panelsettings = new JPanel();
        		panelsettings.setVisible(true);

        		boardpanel.add(panelsettings);
            	
        		ImageIcon iconmenu = new ImageIcon(Gui.class.getResource("menu.png"));
	        	JButton menu = new JButton(iconmenu);
       		 		menu.setIcon(iconmenu);
       		 		menu.setContentAreaFilled(false);
       		 		menu.setBorderPainted(false);
       		 		menu.setOpaque(true);
	        	panelsettings.add(menu);
	        		 
        		menu.addActionListener(new ActionListener() {
     	            public void actionPerformed(ActionEvent e)
     	            {
     	            	panelsettings.setVisible(false);
     	            	panel.setVisible(true);
     	            }
        		 });
            }
       });
	 
	//****************ACTION LISTENER HELP********************
		 help.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e)
	            {
	            	panel.setVisible(false);
	            	JPanel panelhelp = new JPanel();
	        		panelhelp.setVisible(true);

	        		boardpanel.add(panelhelp);
	            	
	        		ImageIcon iconmenu = new ImageIcon(Gui.class.getResource("menu.png"));
		        	JButton menu = new JButton(iconmenu);
	       		 		menu.setIcon(iconmenu);
	       		 		menu.setContentAreaFilled(false);
	       		 		menu.setBorderPainted(false);
	       		 		menu.setOpaque(true);
		        	panelhelp.add(menu);
		        		 
	        		menu.addActionListener(new ActionListener() {
	     	            public void actionPerformed(ActionEvent e)
	     	            {
	     	            	panelhelp.setVisible(false);
	     	            	panel.setVisible(true);
	     	            }
	        		 });
	            }
	       });
	 
	 frame.add(boardpanel);
	 frame.setPreferredSize(new Dimension(1280,720));
	 frame.pack();
}

public void run() {
	
}

	
//************************MAIN METHODE*******************
public static void main(String[] args) {
	
	new Gui();

}
}
