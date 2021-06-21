/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_2;

/**
 *
 * @author amnwaqar
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public final class GameController extends JPanel implements ActionListener {

    private static Connect scoreDB;
    private static final String databaseURL = "jdbc:derby:database;create=true";;
    private DrawingPanel drawPanel;
    private JButton submit, fiftyfiftyButton, skipButton, begin, playAgain[], exit[], highscore[];
    private ButtonGroup group = new ButtonGroup();
    private JRadioButton a, b, c, d;
    private JPanel questions, options, buttons;
    private JLabel question, pattern, whitespace[], status, userScore;
    private Questionaire questionaire;
    private ProgressView progressBar;
    private Skip skip;
    private FiftyFifty fiftyfifty;
    private int q, skipIndex = -1;
    private String answer;
    private static OpeningView open;
    private static GameController myPanel;
    private static JFrame frame;
    private WinningView won;
    private ExitView quit;
    private LosingView lost;
    private HighscoreView highscores;
    private HighscoreMessage highscoreGUI;
    private JTextField usernameField;
    private User user;

    public GameController() throws SQLException {
        super(new BorderLayout());
        initialiseQuestionaireVariables();
        setUpUserFunctionality();
        intialiseButtons();
        open = new OpeningView(begin, usernameField);
        quit = new ExitView();
        setUpRadioButtons();
        setUpPanelButtons();
        setUpOptions();
        setUpQuestions();
        setUpDrawingPanel();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        scoreDB = new Connect(databaseURL, "APP", "APP");
        myPanel = new GameController();
        frame = new JFrame("Who Wants To Be A Millionaire?");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(open);
        frame.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(new Point((d.width / 2) - (frame.getWidth() / 2), (d.height / 2) - (frame.getHeight() / 2)));
        frame.setVisible(true);
        frame.getContentPane().validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        answer = e.getActionCommand();
    }

    public void correct() throws SQLException {
        boolean correct;

        if (skipIndex != -1) {
            correct = questionaire.isCorrect(answer, skipIndex);
            skipIndex = -1;
        } else {
            correct = questionaire.isCorrect(answer, q);
        }

        if (correct) {

            JOptionPane.showMessageDialog(this, "Correct!!\nYou earned " + (++q) + " points!");

            user.updateScore(q);

            if (q == 10) {
                highscoreGUI.updateHighscore(user);
                boolean newHScore = scoreDB.updateHScore(user);
                highscores = new HighscoreView(playAgain[2], exit[3], scoreDB.getAllScores());
                userScore.setText("U S E R:  " + user.getUsername() + "             S C O R E:  " + user.getScore() + "             H I G H S C O R E:  " + scoreDB.getHScore(user.getUsername()));
                won = new WinningView(highscore[0], playAgain[0], exit[0], userScore);
                changePanels(won);
                if (newHScore) {
                    JOptionPane.showMessageDialog(this, "Congrats!! You have a new high score of " + user.getScore() + " point(s)!");
                }
            } else {
                status.setText("U S E R:  " + user.getUsername() + "             S C O R E:  " + user.getScore() + "                   C O R R E C T !           H I G H S C O R E:  " + scoreDB.getHScore(user.getUsername()));
                updateQuestion(q);
                progressBar.update(q + 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect! Better luck next time.");
            highscoreGUI.updateHighscore(user);
            boolean newHScore = scoreDB.updateHScore(user);
            highscores = new HighscoreView(playAgain[2], exit[3], scoreDB.getAllScores());
            userScore.setText("U S E R:  " + user.getUsername() + "             S C O R E:  " + user.getScore() + "             H I G H S C O R E:  " + scoreDB.getHScore(user.getUsername()));
            lost = new LosingView(highscore[1], playAgain[1], exit[1], userScore);
            changePanels(lost);
            if (newHScore) {
                JOptionPane.showMessageDialog(this, "Congrats!! You have a new high score of " + user.getScore() + " point(s)!");
            }
        }
    }

    public void Lifeline50_50() throws SQLException {
        try {
            int keep50_50 = fiftyfifty.use();
            int ansIndex, keepIndex, index;

            JOptionPane.showMessageDialog(this, "50-50 Used!!\nYou lost 5 points!");

            if (skipIndex == -1) {
                index = q;
            } else {
                index = skipIndex;
            }

            ansIndex = questionaire.getAnswerIndex(index);
            keepIndex = questionaire.getKeepIndex(index, keep50_50);

            for (int k = 0; k < 4; k++) {
                if (k != ansIndex && k != keepIndex) {
                    switch (k) {
                        case 0: {
                            a.setText("");
                        }
                        break;
                        case 1: {
                            b.setText("");
                        }
                        break;
                        case 2: {
                            c.setText("");
                        }
                        break;
                        case 3: {
                            d.setText("");
                        }
                        break;
                    }
                }
            }

            progressBar.update50_50();
            progressBar.update(q + 1);
            user.updateScore(-5);
            status.setText("U S E R:  " + user.getUsername() + "             S C O R E:  " + user.getScore() + "             H I G H S C O R E:  " + scoreDB.getHScore(user.getUsername()));
        } catch (LifelineUsedException e) {
            JOptionPane.showMessageDialog(this, "50-50 Already Used!!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void LifelineSkip() throws SQLException {
        try {
            skipIndex = skip.use();
            JOptionPane.showMessageDialog(this, "Skip Used!!\nYou lost 5 points!");
            updateQuestion(skipIndex);
            progressBar.updateSkip();
            user.updateScore(-5);
            progressBar.update(q + 1);
            status.setText("U S E R:  " + user.getUsername() + "             S C O R E:  " + user.getScore() + "             H I G H S C O R E:  " + scoreDB.getHScore(user.getUsername()));
        } catch (LifelineUsedException e) {
            JOptionPane.showMessageDialog(this, "Skip Already Used!!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateQuestion(int k) {
        question.setText(questionaire.getQuestion(k));
        a.setText(questionaire.getOptions(k, 0));
        b.setText(questionaire.getOptions(k, 1));
        c.setText(questionaire.getOptions(k, 2));
        d.setText(questionaire.getOptions(k, 3));
    }

    public void setUpOptions() {

        whitespace = new JLabel[10];

        for (int k = 0; k < 10; k++) {
            whitespace[k] = new JLabel(" ", JLabel.CENTER);
        }

        options = new JPanel();
        options.setLayout(new BoxLayout(options, BoxLayout.PAGE_AXIS));

        options.add(whitespace[0]);
        options.add(whitespace[1]);
        options.add(whitespace[5]);
        options.add(whitespace[6]);
        options.add(a);
        options.add(whitespace[2]);
        options.add(b);
        options.add(whitespace[3]);
        options.add(c);
        options.add(whitespace[4]);
        options.add(d);
        options.setBackground(new Color(255, 238, 204));
    }

    public void setUpRadioButtons() {
        a = new JRadioButton(questionaire.getOptions(q, 0));
        a.setActionCommand("a");
        a.addActionListener(this);
        a.setAlignmentX(CENTER_ALIGNMENT);
        group.add(a);

        b = new JRadioButton(questionaire.getOptions(q, 1));
        b.setActionCommand("b");
        b.addActionListener(this);
        b.setAlignmentX(CENTER_ALIGNMENT);
        group.add(b);

        c = new JRadioButton(questionaire.getOptions(q, 2));
        c.setActionCommand("c");
        c.addActionListener(this);
        c.setAlignmentX(CENTER_ALIGNMENT);
        group.add(c);

        d = new JRadioButton(questionaire.getOptions(q, 3));
        d.setActionCommand("d");
        d.addActionListener(this);
        d.setAlignmentX(CENTER_ALIGNMENT);
        group.add(d);
    }

    public void setUpPanelButtons() {
        status = new JLabel("", JLabel.CENTER);
        submit = new JButton("Submit");
        submit.addActionListener(action -> {
            try {
                if (answer != null)
                {
                    correct();
                }
            } catch (SQLException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        skipButton = new JButton("Skip");
        skipButton.addActionListener(action -> {
            try {
                LifelineSkip();
            } catch (SQLException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        fiftyfiftyButton = new JButton("50-50");
        fiftyfiftyButton.addActionListener(action -> {
            try {
                Lifeline50_50();
            } catch (SQLException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        buttons = new JPanel(new BorderLayout());
        buttons.add(submit, BorderLayout.CENTER);
        buttons.add(skipButton, BorderLayout.EAST);
        buttons.add(fiftyfiftyButton, BorderLayout.WEST);
        buttons.add(status, BorderLayout.NORTH);
    }

    public void setUpQuestions() {
        questions = new JPanel(new BorderLayout());
        question = new JLabel(questionaire.getQuestion(q), JLabel.CENTER);

        questions.add(question, BorderLayout.NORTH);
        questions.add(options, BorderLayout.CENTER);
        questions.add(buttons, BorderLayout.SOUTH);
        questions.setPreferredSize(new Dimension(825, 500));
    }

    public void changePanels(JPanel component_to_add) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(component_to_add);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

    public void beginNow() throws SQLException {
        String username = usernameField.getText().toLowerCase();
        boolean moveOn = false;
        
        if (!username.isEmpty() && !username.contentEquals("enter username here")) {
            
            if (username.length() <= 7)
            {
                boolean userExists = scoreDB.checkUser(username);
                user.setUsername(usernameField.getText().toLowerCase());

                if (!userExists) {
                    int answer = JOptionPane.showConfirmDialog(this, "Entered username does not exist. Press Yes to SIGN UP with entered credentials or No to EXIT", "Sign Up", JOptionPane.YES_NO_OPTION);

                    if (answer == 0) {
                        String inputStr = JOptionPane.showInputDialog("Enter a password for your new account: ");

                        if (inputStr != null)
                        {
                            scoreDB.addUser(username);
                            scoreDB.addPassword(username, inputStr);
                            moveOn = true;
                        }
                    }
                } else if (userExists) {
                    boolean correctPass = false;
                    String enteredPass = "";
                    while (!correctPass && enteredPass != null) {
                        enteredPass = JOptionPane.showInputDialog("Enter the password for your account: ");

                        if (enteredPass != null)
                        {
                            correctPass = scoreDB.checkPassword(user.getUsername(), enteredPass);

                            if (!correctPass) {
                                JOptionPane.showMessageDialog(this, "Invalid Password. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                            else
                            {
                                moveOn = true;
                                JOptionPane.showMessageDialog(this, "Welcome back " + user.getUsername() + ". Good luck!");
                            }
                        }
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please limit the username to 7 characters. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Username field cannot be empty. You must choose a username.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        if (moveOn)
        {
            changePanels(myPanel);
            status.setText("U S E R:  " + user.getUsername() + "             S C O R E:  " + user.getScore() + "             H I G H S C O R E:  " + scoreDB.getHScore(user.getUsername()));
        }
     }

    public void playAgainNow() {
        resetGame();
        changePanels(open);
    }

    public void exitNow() {
        changePanels(quit);
    }

    public void viewHighscores() {
        changePanels(highscores);
    }

    public void resetGame() {
        q = 0;
        fiftyfifty.reset();
        skip.reset();
        status.setText("");
        progressBar.reset();
        progressBar.update(q + 1);
        questionaire.setQuestionOrder();
        updateQuestion(0);
        user.reset();
    }

    public void intialiseButtons() {
        begin = new JButton("Begin");
        begin.addActionListener(action -> {
            try {
                beginNow();
            } catch (SQLException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        playAgain = new JButton[3];
        highscore = new JButton[2];
        exit = new JButton[4];

        for (int k = 0; k < 4; k++) {
            if (k < 3) {
                playAgain[k] = new JButton("Play Again");
                playAgain[k].addActionListener(action -> playAgainNow());

                if (k < 2) {
                    highscore[k] = new JButton("View Highscores");
                    //Previously reading highscore from a text file
                    highscore[k].addActionListener(action -> {
                        viewHighscores();
                    }
                    );
                }
            }

            exit[k] = new JButton("Exit");
            exit[k].addActionListener(action -> exitNow());
        }
    }

    public void setUpDrawingPanel() {
        drawPanel = new DrawingPanel();
        drawPanel.add(questions, BorderLayout.WEST);
        drawPanel.add(progressBar, BorderLayout.EAST);
        drawPanel.add(exit[2], BorderLayout.SOUTH);
        add(drawPanel, BorderLayout.CENTER);
    }

    public void setUpUserFunctionality() throws SQLException {
        highscoreGUI = new HighscoreMessage(scoreDB.getAllScores());
        user = new User();
        userScore = new JLabel("", JLabel.CENTER);
        usernameField = new JTextField(30);
        usernameField.setText("enter username here");
        usernameField.setPreferredSize(new Dimension(50, 30));
        usernameField.setHorizontalAlignment(JTextField.CENTER);
    }

    public void initialiseQuestionaireVariables() {
        q = 0;
        questionaire = new Questionaire();
        skip = new Skip();
        fiftyfifty = new FiftyFifty();
        progressBar = new ProgressView();
    }
}