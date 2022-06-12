package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WinnerWindow extends JFrame {

    private javax.swing.JButton newGameButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel winnerTitle;

    /**
     * Akkor jön létre ha végetér a játék. A győztesnek szóló gratuláció látszik rajta
     * valamint két gomb. Amikkel el lehet indítani egy új játékot vagy ki lehet lépni a játékból.
     * @param winner A győztes játékos neve.
     */
    public WinnerWindow(String winner) {
        setTitle("Tower Defense Játék");
        ImageIcon img = new ImageIcon(getClass().getResource("/img/gameIcon.png"));
        this.setIconImage(img.getImage());
        setSize(700, 500);
        setResizable(false);
        String fontFamily = "Arial";
        
        winnerTitle = new javax.swing.JLabel();
        newGameButton = new javax.swing.JButton(); 
        exitButton = new javax.swing.JButton();

        winnerTitle.setFont(new java.awt.Font(fontFamily, 1, 24)); 
        winnerTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        winnerTitle.setText("Gratulálok " + winner + " a győzelemhez!");

        newGameButton.setFont(new java.awt.Font(fontFamily, 0, 14)); 
        newGameButton.setText("Új játék");
        winnerTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newGameButton.addActionListener(goToNewGameWindow());

        exitButton.setFont(new java.awt.Font(fontFamily, 0, 14));
        exitButton.setText("Kilépés");
        winnerTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitButton.addActionListener(closeGame());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(winnerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(winnerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(newGameButton)
                .addGap(18, 18, 18)
                .addComponent(exitButton)
                .addContainerGap(130, Short.MAX_VALUE))
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
     * Átirányít az új játék kezdése ablakra és bezárja ezt az ablakot.
     */
    private ActionListener goToNewGameWindow() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewGameWindow newgameWindow = new NewGameWindow();
                newgameWindow.setVisible(true);
                dispose();
            }
        };
    }

    /**
     * Akció hatására bezárja a programot.
     */
    private ActionListener closeGame() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showExitConfirmation();
            }
        };
    }

    /**
     * Kilépés megerősítése. Megkérdezi a felhasználót, hogy biztos ki akar-e
     * lépni. Ez egy külön panelen jelenik meg igen és nem opciókkal.
     * 
     */
    private void showExitConfirmation() {
        int n = JOptionPane.showConfirmDialog(this, "Valóban ki akar lépni?",
                "Megerősítés", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {      
            doUponExit();
        }
    }

    /**
     * Kilépés a programból. Bezárja magát a program és leállítja az időzítőt.
     */
    protected void doUponExit() {
        GameBoard.stopTimer();
        dispose();
    }
}

