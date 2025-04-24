import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GUI {

	private JFrame frame = new JFrame("Teams");
	private JFrame frameInstruct = new JFrame("Instructions");
	private JFrame frameTournament = new JFrame("Tournament");
	private int teamCount = 0;
	
	//runs application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//
	public GUI() {
		initialize();
	}
	
	//
	public void initialize() {
		ArrayList<String> nameList = new ArrayList<String>();
		Random random = new Random();
		File file = new File("C:/Users/sbsmi/Documents/eclipse-workspace/Random/src/names");
		
		
		//Setting frame parameters
		frame.setSize(500,800);
		frame.getContentPane().setLayout(null);
		frame.setForeground(Color.WHITE);
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frameInstruct.setVisible(false);
		frameInstruct.setSize(1000,600);
		frameInstruct.getContentPane().setLayout(null);
		frameInstruct.setForeground(Color.WHITE);
		frameInstruct.getContentPane().setForeground(Color.BLACK);
		frameInstruct.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frameTournament.setVisible(false);
		frameTournament.setSize(1000,600);
		frameTournament.getContentPane().setLayout(null);
		frameTournament.setForeground(Color.WHITE);
		frameTournament.getContentPane().setForeground(Color.BLACK);
		frameTournament.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Setting Name textbox parameters
		JTextField tfName = new JTextField();
		tfName.setFont(new Font("Ariel", Font.BOLD, 16));
		tfName.setBounds(50,140, 400, 63);
		tfName.setHorizontalAlignment(JTextField.CENTER);
		tfName.setVisible(true);
		frame.getContentPane().add(tfName);
		tfName.setColumns(10);
		
		//Setting Count textbox parameters
		JTextField tfCount = new JTextField();
		
		tfCount.setFont(new Font("Ariel", Font.BOLD, 16));
		tfCount.setBounds(430, 10, 50, 63);
		tfCount.setHorizontalAlignment(JTextField.CENTER);
		tfCount.setVisible(true);
		frame.getContentPane().add(tfCount);
		
		JLabel lbSize = new JLabel("Size of Teams:");
		lbSize.setFont(new Font("Ariel", Font.BOLD, 16));
		lbSize.setBounds(300,10,150, 63);
		lbSize.setVisible(true);
		frame.getContentPane().add(lbSize);
		
		JLabel lbName = new JLabel("Name:");
		lbName.setFont(new Font("Ariel", Font.BOLD, 16));
		lbName.setBounds(200,90, 100, 50);
		lbName.setHorizontalAlignment(JLabel.CENTER);
		lbName.setVisible(true);
		frame.getContentPane().add(lbName);
		
		
		//Initialize textarea to display teams
		JTextArea teams = new JTextArea();
		
		//Button to inserts name from textbox into arrayLists
		JButton btnName = new JButton("Enter Name");
		btnName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nameList.add(tfName.getText());

				//MAKE A FUCKING BUTTON TO CLEAR DUMBASS

				if(tfName.getText().toUpperCase().equals("CLEAR")) {
					nameList.clear();
				}
				teams.append("'" +tfName.getText() + "' has been added\n");
				tfName.setText("");
			}
		});
		btnName.setBounds(10,220, 230,50);
		frame.getContentPane().add(btnName);
		
		JButton btnRandom = new JButton("Random");
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfCount.getText().equals("")) {
					teams.append("PLEASE ENTER SIZE OF TEAMS\n");
				} else {	
					teams.setText(null);
					int teamSize = Integer.parseInt(tfCount.getText());
					int counter = 0;
					for(int i = -1;i <= nameList.size(); i++) {
						counter++;
						String strCounter = String.format("%o:\n", counter);
						teams.append("Team " + strCounter);
						for(int j = 0; j < teamSize; j++) {
							int randomItem=random.nextInt(nameList.size());
							String randomElement=nameList.get(randomItem);
							teams.append(randomElement + "\n");
							nameList.remove(randomElement);
						}
						teamCount=counter;
					}
					System.out.println(teamCount);
				}
			}
		});
		btnRandom.setBounds(245,220, 230, 50);
		frame.getContentPane().add(btnRandom);
		
		
		JButton btnCreateList = new JButton("Create Preset List");
		btnCreateList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(nameList.size() == 0) {
					teams.append("Current List is empty \nPlease add to list to write to file");
				} else {
					try {
						FileWriter fileWriter = new FileWriter("C:/Users/sbsmi/Documents/eclipse-workspace/Random/src/names");
						for(String str : nameList) {
							fileWriter.write(str + System.lineSeparator());
						}
						fileWriter.close();
						System.out.println("File Successfully Written");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnCreateList.setBounds(10, 280, 230, 50);
		frame.getContentPane().add(btnCreateList);
		
		JButton btnLoadList = new JButton("Load Preset List");
		btnLoadList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Scanner scanner = new Scanner(file);
					while(scanner.hasNextLine()) {
						nameList.add(scanner.nextLine());
					}
					teams.append("List has been loaded \n");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		btnLoadList.setBounds(245, 280, 230, 50);
		frame.getContentPane().add(btnLoadList);
		
		JButton btnDisplayList = new JButton("Display List");
		btnDisplayList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(nameList.size() != 0) {
					teams.setText(null);
					for(String str : nameList) {
						teams.append(str + "\n");
					}
				} else {
					teams.append("Current List is empty");
				}
			}
		});
		btnDisplayList.setBounds(245, 340, 230, 50);
		frame.getContentPane().add(btnDisplayList);
		
		teams.setFont(new Font("Ariel", Font.PLAIN, 16));
		teams.setBounds(10, 400, 470, 290);
		frame.getContentPane().add(teams);
		
		JLabel instructions = new JLabel("<html><i>Instructions:<br>Type the number of people on each team in the first box<br>" +
				"Type the name of a participant and click the button titled 'Enter Name' to enter the name into the system<br>" +
				"</i><html>");
		instructions.setFont(new Font("Ariel", Font.PLAIN, 12));
		instructions.setBounds(0,0,800,300);
		frameInstruct.getContentPane().add(instructions);
		
		JButton btnInstruct = new JButton("Instructions");
		btnInstruct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameInstruct.setVisible(true);
			}
		});
		btnInstruct.setFont(new Font("Ariel", Font.BOLD, 16));
		btnInstruct.setBounds(10, 340, 230, 50);
		frame.getContentPane().add(btnInstruct);
		
		JTextArea tournament = new JTextArea();
		tournament.setFont(new Font("Ariel", Font.PLAIN, 16));
		tournament.setBounds(10,100, 400, 400);
		tournament.setAlignmentX(200);
		frameTournament.getContentPane().add(tournament);
		
		
		
		JButton btnTournament = new JButton("Tournament");
		btnTournament.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameTournament.setVisible(true);
				frame.setVisible(false);
				tournament.append(Integer.toString(teamCount));
				ArrayList<Integer> ALTeams = new ArrayList<Integer>();
				for(int i=1; i<=teamCount;i++) {
					ALTeams.add(i);
				}
				System.out.println(ALTeams);
			}
		});
		btnTournament.setBounds(10, 700, 470, 50);
		frame.getContentPane().add(btnTournament);

		JButton btnBack = new JButton("<-");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameTournament.setVisible(false);
				frame.setVisible(true);
			}
		});
		btnBack.setBounds(50,50,75,75);
		frameTournament.getContentPane().add(btnBack);

		
	}	
		
	
}
