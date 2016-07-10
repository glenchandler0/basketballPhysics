package PhysicsStuff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;





public class Menu extends JPanel
{

	public static JFrame frame = new JFrame("Main Menu");
	private static ImageIcon labelIcon = new ImageIcon( "basketBallPicture.jpg" );
	private static JLabel centerImg = new JLabel( labelIcon );
	private static JButton playerVsPlayer = new JButton("Begin Game");
	private static JPanel southPanel = new JPanel();
	private static JLabel titleLabel = new JLabel("SUPER BASKETBALL MASH 2014");
	private static JLabel prevScore = new JLabel();
	
	private static int selection  = 0;
	
	public Menu() throws InterruptedException 
	{
		String prevScoreString = ("Last score: " + Integer.toString(PEngine.getPointNum())); //Calls static variable from PEngine to find last score
		prevScore.setText(prevScoreString); //Set text to label prevScore
		
		JMenuBar menuBar = new JMenuBar(); //MenuBar
		frame.setJMenuBar(menuBar); //Sets the menu
		menuBar.add(createHighscoreItem()); //Adds the items
		menuBar.add(createHelpItem()); //Adds items 
		
		JPanel northPanel = new JPanel(); //Panel that will hold title
		northPanel.add(titleLabel, BorderLayout.CENTER); //Title added to panel
		
		mainGame(); //Will add playerVsPlayer to the nested butHandler class (within this function)
		southPanel.add(playerVsPlayer, BorderLayout.SOUTH);
		southPanel.add(prevScore, BorderLayout.NORTH);
		
		frame.pack();
		frame.add(northPanel, BorderLayout.NORTH);
		frame.add(southPanel, BorderLayout.SOUTH);
		centerImg.setSize(700, 800);
		frame.add(centerImg, BorderLayout.CENTER);
		
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setBackground(Color.black);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		boolean b = true;
		while(b == true)
		{
			switch(selection)
			{
			case 0:
				System.out.print("");
				continue;
			case 1:
				//System.out.println("Opening main");
				selection = 0;
				GameRun game = new GameRun();
				b = false;
				break;
			default:
				System.out.println("Button selection error");
				b = false;
				break;
			}
		}
	}	
	
	
	private static void mainGame() throws InterruptedException
	{
		class butHandler implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == playerVsPlayer)
				{
					selection = 1;
				}
			}
			
		}
		butHandler handle = new butHandler();
		playerVsPlayer.addActionListener(handle);
	}
	
	
	//Menu items-------------------------
	private JMenu createHelpItem()
	{
		JMenu menu = new JMenu("Help");
		menu.add(createHelpFile());
		
		return menu;
	}
	
	private JMenuItem createHelpFile() //Similar to adding the listener to the button, but for the menu selection
	{
		JMenuItem item = new JMenuItem("Help");
		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				TextArea text = new TextArea(10,10);
				Scanner in = null;
				File selectedFile = new File("help.txt");
				try
				{
				in = new Scanner(selectedFile);
				int lineNumber = 1;
				
				while ( in.hasNextLine()) //puts whole text file on textArea
				{
					String line = in.nextLine();
					text.append(line + "\n");
					lineNumber++;
				}
				in.close();
				}
				catch (FileNotFoundException e) //In case help.txt is not found
				{
					System.out.println("File help not found");
				}
				JFrame helpFrame = new JFrame("Help/Instructions");
				helpFrame.add(text);
				
				helpFrame.setSize(300,610);
				helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				helpFrame.setVisible(true);
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener); //adds the item to above listener, and returns it for menu.add()
		
		return item;
	}
	
	//Drop down title
	private JMenu createHighscoreItem()
	{
		JMenu menu = new JMenu("Highscores");
		menu.add(seeHighScores());
		menu.add(saveHighScore());
		
		return menu;
	}
	
	//Same as for help, but a different file
	private JMenuItem seeHighScores() 
	{
		JMenuItem item = new JMenuItem("See HighScores");
		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				TextArea text = new TextArea(10,10);
				Scanner in = null;
				File selectedFile = new File("highScores.txt");
				try
				{
				in = new Scanner(selectedFile);
				int lineNumber = 1;
				
				while ( in.hasNextLine())
				{
					String line = in.nextLine();
					text.append(line + "\n");
					lineNumber++;
				}
				in.close();
				}
				catch (FileNotFoundException e)
				{
					System.out.println("File highScores not found");
				}
				JFrame helpFrame = new JFrame("-High Scores-");
				helpFrame.add(text);
				
				helpFrame.setSize(225,400);
				helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				helpFrame.setVisible(true);
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		
		return item;
	}
	
	private JMenuItem saveHighScore() 
	{
		JMenuItem item = new JMenuItem("Save Current Score");
		class MenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm"); //formats date for being printed
				final Date date = new Date(); //Creates current date
				
				final JFrame inputFrame = new JFrame("Enter Highscore"); //Window for entering name for new score
				
				//Window design
				JLabel labelName = new JLabel("Name:"); 
				final JTextField nameField = new JTextField();
				JButton enterButton = new JButton("Save Score");
				
				//Adds components to window / frame
				inputFrame.add(labelName, BorderLayout.NORTH);
				inputFrame.add(nameField, BorderLayout.CENTER);
				inputFrame.add(enterButton, BorderLayout.SOUTH);
				
				//Nested class with listener
				class ButtonPress implements ActionListener
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						try //No system.in, just out
						{
						String outputFileName = "highScores.txt";
						String name = nameField.getText(); //Gets text from textfield to be put into file
						PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName, true))); //Allows for parsing to file rather than overriding
						out.printf("\n-%s- %s: %s\n",dateFormat.format(date), name, Integer.toString(PEngine.getPointNum())); //Parses new line to the selected file
						
						out.close();
						}
						catch (FileNotFoundException err)
						{
							System.out.println("File highScores not found");
						}
						catch(IOException i)
						{
							System.out.println("saveHighScore IOException");
						}
						
						//Closes frame
						inputFrame.getContentPane().removeAll();
						inputFrame.setVisible(false);
						inputFrame.dispose();					
					}
					
				}
				ButtonPress listener = new ButtonPress(); //Adds listener
				enterButton.addActionListener(listener); //Adds button to listener 
				inputFrame.setSize(200,150);
				inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Allows you to close frame without stopping program
				inputFrame.setVisible(true);
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener); //Adds to listener
		
		return item; //returns item so it can be .add()-ed
	}
}
