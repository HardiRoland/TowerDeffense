package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainWindow extends JFrame {

    private javax.swing.JButton newGameButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel menuTitle;

    /**
     * Létrehozza a kezdőképernyőt. Amin a cím és két gomb szerepel. 
     * El lehet indítani egy új játékot vagy kilépni a játékból.
     */
    public MainWindow() {
        setTitle("Tower Defense Játék");
        ImageIcon img = new ImageIcon(getClass().getResource("/img/gameIcon.png"));
        this.setIconImage(img.getImage());
        setResizable(false);
        String fontFamily = "Arial";
        
        menuTitle = new javax.swing.JLabel();
        newGameButton = new javax.swing.JButton(); 
        exitButton = new javax.swing.JButton();

        menuTitle.setFont(new java.awt.Font(fontFamily, 1, 24)); 
        menuTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuTitle.setText("Főmenü");

        newGameButton.setFont(new java.awt.Font(fontFamily, 0, 14)); 
        newGameButton.setText("Új játék");
        newGameButton.addActionListener(goToNewGameWindow());

        exitButton.setFont(new java.awt.Font(fontFamily, 0, 14));
        exitButton.setText("Kilépés");
        exitButton.addActionListener(closeGame());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(200, 200, 200))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(menuTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addGap(66, 66, 66)
                .addComponent(newGameButton)
                .addGap(25, 25, 25)
                .addComponent(exitButton)
                .addGap(113, 113, 113))
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
     * Átirányít az új játék kezdése ablakra és bezárja a főmenüt.
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
     * Ennek lefuttatásával indul el a program.
     * @param args Paraméterek listája.
     */
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
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

