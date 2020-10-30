package hangman;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import linked_data_structures.SinglyLinkedList;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.DropMode;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

public class HangmanFrame extends JFrame {

	private JPanel contentPane;
	private JPanel welcomePanel;
	private JPanel newGamePanel;
	private JPanel newPlayerPanel;
	private JLayeredPane layeredPane;

	static JLabel lblNewLabel;
	static JButton btnNewGame;
	static JButton btnResumeGame;
	static JButton btnExitGame;
	static JLabel lblhangman;

	private static DictionaryWordsRepo words;
	private static DictionaryWord word;
	private static HangmanGame game;
	private static Scoreboard scoreboard;
	private static SinglyLinkedList<Object> gameData;
	private static SinglyLinkedList<Object> savedData;

	private int currentImg;

	private JMenu helpMenu;
	private JMenuItem menuItemRules;
	private JMenuItem menuItemSaveGame;
	private JMenuItem menuItemReturnToMainMenu;
	private JMenuItem menuItemExitGame;
	private JMenuItem menuItemScoreboard;
	private JFormattedTextField txtFieldUserName;
	private JLabel lblOr;
	private JFormattedTextField enterLetterTxtField;
	private JButton btnNewButton;
	private JButton btnEnter;
	private JPanel lettersSpacePanel;
	private JTextArea lettersSpace;

	public static void main(String[] args) {

		// Start Game
		game = new HangmanGame();
		HangmanFrame frame = new HangmanFrame();
		frame.setVisible(true);

		// if user clicks on save
//		gameData = new SinglyLinkedList<Object>();
//		words = new DictionaryWordsRepo();
//
//		word = new DictionaryWord(words.sendRandomWord());
//		word.printLists();
//		System.out.println("\n\n0 - " + words + "\n1 - " + word);
//		gameData.add(words);
//		gameData.add(word);
//
//		System.out.println("\n\n0 - " + gameData.getElementAt(0) + "\n1 - " + gameData.getElementAt(1));
//
//		game.saveGame(gameData);
//		savedData = game.resumeGame();
//		DictionaryWord savedWord = (DictionaryWord) savedData.getElementAt(0);
//
//		savedWord.printLists();

	}

	public HangmanFrame() {
		init();
	}

	public void init() {

		setTitle("Welcome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1048, 692);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1048, 670);
		contentPane.add(layeredPane);

//		String[] previousNames = game.getPreviousNames();
		String[] previousNames = { "", "Arsen", "Diana" };// TODO Use getPreviousNames here;

		newGamePanel = new JPanel();
		layeredPane.setLayer(newGamePanel, 0);
		newGamePanel.setBounds(0, 0, 1048, 670);
		layeredPane.add(newGamePanel);
		newGamePanel.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1048, 22);
		menuBar.setVisible(false);
		newGamePanel.add(menuBar);

		JMenu gameMenu = new JMenu("Game");
		menuBar.add(gameMenu);

		JMenuItem menuItemNewGame = new JMenuItem("New Game");
		gameMenu.add(menuItemNewGame);

		menuItemSaveGame = new JMenuItem("Save Game");
		gameMenu.add(menuItemSaveGame);

		menuItemScoreboard = new JMenuItem("Scoreboard");
		gameMenu.add(menuItemScoreboard);

		menuItemReturnToMainMenu = new JMenuItem("Return to Main Menu");
		gameMenu.add(menuItemReturnToMainMenu);

		menuItemExitGame = new JMenuItem("Exit Game");
		gameMenu.add(menuItemExitGame);

		helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

		menuItemRules = new JMenuItem("Rules");
		helpMenu.add(menuItemRules);

		JPanel padPanel = new JPanel();
		padPanel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		padPanel.setBackground(Color.ORANGE);
		padPanel.setBounds(398, 463, 327, 181);
		newGamePanel.add(padPanel);
		padPanel.setLayout(null);

		enterLetterTxtField = new JFormattedTextField();
		enterLetterTxtField.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (enterLetterTxtField.getText().length() >= 1 ) // limit character input to 1 character
		            e.consume(); 
		    }  
		});
		enterLetterTxtField.setBounds(52, 26, 157, 58);
		padPanel.add(enterLetterTxtField);
		enterLetterTxtField.setHorizontalAlignment(SwingConstants.CENTER);
		enterLetterTxtField.setFont(new Font("Arial", Font.PLAIN, 20));

		btnEnter = new JButton("Enter Letter");
		btnEnter.setForeground(Color.WHITE);
		btnEnter.setBackground(Color.BLACK);
		btnEnter.setBounds(52, 108, 157, 47);
		padPanel.add(btnEnter);
		btnEnter.setFont(new Font("Arial", Font.PLAIN, 20));

		btnNewButton = new JButton("Hint");
		btnNewButton.setBounds(237, 33, 72, 47);
		padPanel.add(btnNewButton);
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));

		JPanel wrongGuessesPanel = new JPanel();
		wrongGuessesPanel.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
		wrongGuessesPanel.setBackground(Color.ORANGE);
		wrongGuessesPanel.setBounds(773, 434, 207, 218);
		newGamePanel.add(wrongGuessesPanel);
		wrongGuessesPanel.setLayout(null);

		JTextArea txtWrongGuesses = new JTextArea();
		txtWrongGuesses.setBounds(17, 16, 172, 170);
		wrongGuessesPanel.add(txtWrongGuesses);
		txtWrongGuesses.setForeground(Color.BLACK);
		txtWrongGuesses.setLineWrap(true);
		txtWrongGuesses.setDropMode(DropMode.INSERT);
		txtWrongGuesses.setBackground(Color.ORANGE);
		txtWrongGuesses.setText("Guessed Letters:\n\n");
		txtWrongGuesses.setTabSize(0);
		txtWrongGuesses.setFont(new Font("Arial", Font.PLAIN, 20));
		txtWrongGuesses.setEditable(false);
		
		lettersSpacePanel = new JPanel();
		lettersSpacePanel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lettersSpacePanel.setBounds(398, 175, 510, 186);
		newGamePanel.add(lettersSpacePanel);
		lettersSpacePanel.setLayout(null);
		
		lettersSpace = new JTextArea();
		lettersSpace.setEditable(false);
		lettersSpace.setBounds(22, 55, 470, 67);
		lettersSpacePanel.add(lettersSpace);

		welcomePanel = new JPanel();
		layeredPane.setLayer(welcomePanel, 1);
		welcomePanel.setBounds(0, 0, 1048, 670);
		welcomePanel.setLayout(null);
		layeredPane.add(welcomePanel);

		lblNewLabel = new JLabel("Welcome To Hangman!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(357, 76, 375, 36);
		welcomePanel.add(lblNewLabel);

		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(newPlayerPanel);
				layeredPane.repaint();
				layeredPane.revalidate();
				setTitle("New Game");

			}
		});
		btnNewGame.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewGame.setBounds(436, 309, 212, 57);
		welcomePanel.add(btnNewGame);

		btnResumeGame = new JButton("Resume Game");
		btnResumeGame.setFont(new Font("Arial", Font.PLAIN, 20));
		btnResumeGame.setBounds(436, 240, 212, 57);
		btnResumeGame.setEnabled(game.canResume());
		welcomePanel.add(btnResumeGame);

		btnExitGame = new JButton("Exit Game");
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(welcomePanel, "Would you like to save your game?");
				switch (answer) {
				case 0:
					game.saveGame(gameData);
					System.exit(1);
					break;
				case 1:
					System.exit(2);
					break;
				case 2:
					break;
				}
			}
		});
		btnExitGame.setFont(new Font("Arial", Font.PLAIN, 20));
		btnExitGame.setBounds(436, 382, 212, 57);
		welcomePanel.add(btnExitGame);

		newPlayerPanel = new JPanel();
		layeredPane.setLayer(newPlayerPanel, 0);
		newPlayerPanel.setBounds(0, 0, 1048, 670);
		newPlayerPanel.setLayout(null);
		layeredPane.add(newPlayerPanel);

		JLabel lblUserInput = new JLabel("Please enter your name:");
		lblUserInput.setFont(new Font("Arial", Font.PLAIN, 19));
		lblUserInput.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserInput.setBounds(388, 252, 294, 31);
		newPlayerPanel.add(lblUserInput);

		JLabel lblNewGame = new JLabel("New Game");
		lblNewGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewGame.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewGame.setBounds(375, 136, 307, 51);
		newPlayerPanel.add(lblNewGame);

		txtFieldUserName = new JFormattedTextField();
		txtFieldUserName.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldUserName.setBounds(409, 280, 256, 31);
		newPlayerPanel.add(txtFieldUserName);

		lblOr = new JLabel("OR");
		lblOr.setHorizontalAlignment(SwingConstants.CENTER);
		lblOr.setFont(new Font("Arial", Font.PLAIN, 19));
		lblOr.setBounds(388, 339, 294, 16);
		newPlayerPanel.add(lblOr);
		JComboBox comboBox = new JComboBox(previousNames);
		comboBox.setFont(new Font("Arial", Font.PLAIN, 13));
		comboBox.setMaximumRowCount(100);
		comboBox.setBounds(409, 418, 256, 27);
		newPlayerPanel.add(comboBox);

		JLabel lblPreviousNames = new JLabel("Choose from previous names:");
		lblPreviousNames.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreviousNames.setFont(new Font("Arial", Font.PLAIN, 19));
		lblPreviousNames.setBounds(388, 384, 294, 31);
		newPlayerPanel.add(lblPreviousNames);

		JButton btnPlay = new JButton("PLAY");
		btnPlay.setFont(new Font("Arial", Font.PLAIN, 20));
		btnPlay.setBounds(489, 496, 101, 38);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.repaint();
				layeredPane.revalidate();
				layeredPane.add(newGamePanel);
				menuBar.setVisible(true);
				setTitle("New Game");
				displayHangmanImage();
			}
		});
		newPlayerPanel.add(btnPlay);
	}

	public void displayHangmanImage() {
		currentImg = 0;
		lblhangman = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/" + currentImg + ".png")).getImage();
		lblhangman.setIcon(new ImageIcon(img));
		lblhangman.setBounds(22, 72, 400, 400);
		newGamePanel.add(lblhangman);
	}

	public void changeHangmanImage() {
		currentImg++;
		Image img = new ImageIcon(this.getClass().getResource("/" + currentImg + ".png")).getImage();
		lblhangman.setIcon(new ImageIcon(img));
		lblhangman.setBounds(22, 72, 400, 400);
		newGamePanel.add(lblhangman);
	}

	public void displayGuessedLetters() {

	}

	public void updateGuessedLetters() {

	}

	public void updateWrongGuesses() {

	}

	public void displayHint() {

	}
}