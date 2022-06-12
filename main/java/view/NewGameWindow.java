package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameWindow extends JFrame {

    private javax.swing.ButtonGroup difficultyButtonGroup;
    private javax.swing.ButtonGroup tileInRowButtonGroup;
    private javax.swing.JButton newGameButton;
    private javax.swing.JLabel newGameTitle;
    private javax.swing.JLabel difficultyLabel;
    private javax.swing.JLabel tileInRowLabel;
    private javax.swing.JRadioButton easyDifficultyButton;
    private javax.swing.JRadioButton mediumDifficultyButton;
    private javax.swing.JRadioButton hardDifficultyButton;
    private javax.swing.JRadioButton mediumtileInRowButton;
    private javax.swing.JRadioButton largetileInRowButton;
    private javax.swing.JRadioButton smalltileInRowButton;

    /**
     * Létrehozza az új játék elindításának ablakát. Amin a cím, két gombcsoport és egy gomb szerepel. 
     * Az első gombcsoportból ki lehet választani a játék nehézségét.
     * A második gombcsoportból ki lehet választani a pálya méretét.
     * Az alsó gombbal pedig el lehet indítani a játékot a fent megadott beállítások alapján.
     */
    public NewGameWindow() {
        setTitle("Tower Defense Játék");
        ImageIcon img = new ImageIcon(getClass().getResource("/img/gameIcon.png"));
        this.setIconImage(img.getImage());
        setResizable(false);
        String fontType = "Arial";
        
        difficultyButtonGroup = new javax.swing.ButtonGroup();
        tileInRowButtonGroup = new javax.swing.ButtonGroup();
        newGameTitle = new javax.swing.JLabel();
        easyDifficultyButton = new javax.swing.JRadioButton();
        difficultyLabel = new javax.swing.JLabel();
        mediumDifficultyButton = new javax.swing.JRadioButton();
        hardDifficultyButton = new javax.swing.JRadioButton();
        tileInRowLabel = new javax.swing.JLabel();
        mediumtileInRowButton = new javax.swing.JRadioButton();
        largetileInRowButton = new javax.swing.JRadioButton();
        smalltileInRowButton = new javax.swing.JRadioButton();
        newGameButton = new javax.swing.JButton();

        newGameTitle.setFont(new java.awt.Font(fontType, 1, 24)); 
        newGameTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newGameTitle.setText("Új játék indítása");

        difficultyLabel.setFont(new java.awt.Font(fontType, 0, 14));
        difficultyLabel.setText("Nehézség");
        difficultyLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        difficultyButtonGroup.add(easyDifficultyButton);
        easyDifficultyButton.setFont(new java.awt.Font(fontType, 0, 11));
        easyDifficultyButton.setSelected(true);
        easyDifficultyButton.setText("Könnyű");
        easyDifficultyButton.setActionCommand("easy");

        difficultyButtonGroup.add(mediumDifficultyButton);
        mediumDifficultyButton.setFont(new java.awt.Font(fontType, 0, 11)); 
        mediumDifficultyButton.setText("Közepes");
        mediumDifficultyButton.setActionCommand("medium");

        difficultyButtonGroup.add(hardDifficultyButton);
        hardDifficultyButton.setFont(new java.awt.Font(fontType, 0, 11));
        hardDifficultyButton.setText("Nehéz");
        hardDifficultyButton.setActionCommand("hard");

        tileInRowLabel.setFont(new java.awt.Font(fontType, 0, 14)); 
        tileInRowLabel.setText("Pálya méret");
        tileInRowLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tileInRowButtonGroup.add(smalltileInRowButton);
        smalltileInRowButton.setFont(new java.awt.Font(fontType, 0, 11)); 
        smalltileInRowButton.setText("Kicsi");
        smalltileInRowButton.setActionCommand("small");

        tileInRowButtonGroup.add(mediumtileInRowButton);
        mediumtileInRowButton.setFont(new java.awt.Font(fontType, 0, 11)); 
        mediumtileInRowButton.setSelected(true);
        mediumtileInRowButton.setText("Közepes");
        mediumtileInRowButton.setActionCommand("medium");

        tileInRowButtonGroup.add(largetileInRowButton);
        largetileInRowButton.setFont(new java.awt.Font(fontType, 0, 11)); 
        largetileInRowButton.setText("Nagy");
        largetileInRowButton.setActionCommand("large");

        newGameButton.setFont(new java.awt.Font(fontType, 0, 14)); 
        newGameButton.setText("Új játék");
        newGameButton.addActionListener(startGame());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(newGameTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(150, 150, 150))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tileInRowLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(smalltileInRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mediumtileInRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(largetileInRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(difficultyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(easyDifficultyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mediumDifficultyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hardDifficultyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(newGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(200, 200, 200))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(newGameTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addComponent(easyDifficultyButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mediumDifficultyButton)
                    .addComponent(difficultyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hardDifficultyButton)
                .addGap(18, 18, 18)
                .addComponent(smalltileInRowButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mediumtileInRowButton)
                    .addComponent(tileInRowLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(largetileInRowButton)
                .addGap(28, 28, 28)
                .addComponent(newGameButton)
                .addGap(61, 61, 61))
        );

        pack();
        setLocationRelativeTo(null);

        /**
         * Egyszerű kilépés helyett kilépés előtt feldob egy ablakot, megkérdezi biztos ki akar-e lépni.
         */
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showExitConfirmation();
            }
        });
    }         
    
    /**
     * A fent bejelölt gombok alapján eldönti milyen nehézségű legyen a játék
     * és hogy egy sorba/oszlopba hány mező legyen. Ezekkel az értékekkel létrehozza
     * a játékmezőt és megjeleníti annak az ablakát és bezárja az új játék ablakot.
     */
    private ActionListener startGame() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int difficulty = 0;
                    switch (difficultyButtonGroup.getSelection().getActionCommand()) {
                        case "easy":
                            difficulty = 0;
                        break;
                        case "medium":
                            difficulty = 1;
                        break;
                        case "hard":
                            difficulty = 2;
                        break;
                        default:
                            break;
                    }
                    int tileInRow = 0;
                    switch (tileInRowButtonGroup.getSelection().getActionCommand()) {
                        case "small":
                            tileInRow = 8;
                        break;
                        case "medium":
                            tileInRow = 10;
                        break;
                        case "large":
                            tileInRow = 12;
                        break;
                        default:
                            break;
                    }
                    GameWindow gameWindow = new GameWindow(difficulty, tileInRow);
                    gameWindow.setVisible(true);
                    dispose();
            }
        };
    }

    /**
     * Kilépés megerősítése. Megkérdezi a felhasználót, hogy biztos ki akar-e
     * lépni. Ez egy külön panelen jelenik meg igen és nem opciókkal.
     */
    private void showExitConfirmation() {
        int n = JOptionPane.showConfirmDialog(this, "Valóban ki akar lépni?",
                "Megerősítés", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            doUponExit();
        }
    }

    /**
     * Kilépés a programból. Bezárja magát a program.
     */
    protected void doUponExit() {
        dispose();
    }
}
