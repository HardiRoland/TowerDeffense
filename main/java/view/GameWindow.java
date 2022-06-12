package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.*;
import java.awt.Point;

public class GameWindow extends JFrame {

    private javax.swing.JButton nextRoundButton;
    private javax.swing.JButton tankUnitButton;
    private javax.swing.JButton arrowTowerButton;
    private javax.swing.JButton stoneTowerButton;
    private javax.swing.JButton cannonTowerButton;
    private javax.swing.JButton mineButton;
    private javax.swing.JButton castleUpgradeButton;
    private javax.swing.JButton fireBallSpellButton;
    private javax.swing.JButton arrowSpellButton;
    private javax.swing.JButton healingSpellButton;
    private javax.swing.JButton cavalryUnitButton;
    private javax.swing.JButton warriorUnitButton;
    private javax.swing.JLabel cavalryUnitPrice;
    private javax.swing.JLabel player1UnitLabel;
    private javax.swing.JLabel warriorUnitPrice;
    private javax.swing.JLabel playerHealthPointInfo;
    private javax.swing.JLabel playerCastleLevelInfo;
    private javax.swing.JLabel playerTowerInfo;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel playerMoneyInfo;
    private javax.swing.JLabel actualPlayerInfo;
    private javax.swing.JLabel player2UnitLabel;
    private javax.swing.JLabel tankUnitPrice;
    private javax.swing.JLabel stoneTowerPirce;
    private javax.swing.JLabel arrowTowerPrice;
    private javax.swing.JLabel cannonTowerPrice;
    private javax.swing.JLabel playerMineInfo;
    private javax.swing.JLabel minePrice;
    private javax.swing.JLabel castleUpgradePrice;
    private javax.swing.JLabel castleUpgradeLabel;
    private javax.swing.JLabel arrowSpellPrice;
    private javax.swing.JLabel fireBallSpellPrice;
    private javax.swing.JLabel healingSpellPrice;
    private javax.swing.JLabel spellsLabel;
    private javax.swing.JLabel mineLabel;
    private javax.swing.JLabel unitsLabel;
    private javax.swing.JLabel towersLabel;
    private javax.swing.JList<String> player1UnitList;
    private javax.swing.JList<String> player2UnitList;
    private javax.swing.JScrollPane player1UnitScrollPane;
    private javax.swing.JScrollPane player2UnitScrollPane;
    private GameBoard gameBoard;
    private static final int MAPSIZE = 500;
    private int tileSize;
    private static ArrayList<Integer> player1UnitsOnClickedTile;
    private static ArrayList<Integer> player2UnitsOnClickedTile;
    private static ArrayList<JButton> everyButton;
    private String baseText;
    private String buyingText;

    /**
     * Létrehozza a játék képernyőjét. Felül látszanak a játékhoz tartozó üzenetek.
     * Emellett található a kör átadása gomb. Alatta pedig maga a játékmező és a játékosok
     * adatai körtől függően. Legallul pedig a bolt ahol sereget, tornyokat, bányát
     * és varázsigéket lehet venni.
     * 
     * @param difficulty A játék nehézségét adja meg.
     * @param tileInRow  A mezők számát adja meg egy sorban.
     */
    public GameWindow(int difficulty, int tileInRow) {
        setTitle("Tower Defense Játék");
        ImageIcon img = new ImageIcon(getClass().getResource("/img/gameIcon.png"));
        this.setIconImage(img.getImage());
        setResizable(false);
        String fontFamily = "Arial";

        tileSize = MAPSIZE / tileInRow;

        gameBoard = new GameBoard(difficulty, tileInRow, MAPSIZE);
        nextRoundButton = new javax.swing.JButton();
        messageLabel = new javax.swing.JLabel();
        cavalryUnitButton = new javax.swing.JButton();
        warriorUnitButton = new javax.swing.JButton();
        tankUnitButton = new javax.swing.JButton();
        unitsLabel = new javax.swing.JLabel();
        arrowTowerButton = new javax.swing.JButton();
        stoneTowerButton = new javax.swing.JButton();
        cannonTowerButton = new javax.swing.JButton();
        towersLabel = new javax.swing.JLabel();
        playerMineInfo = new javax.swing.JLabel();
        cavalryUnitPrice = new javax.swing.JLabel();
        player1UnitScrollPane = new javax.swing.JScrollPane();
        player1UnitList = new javax.swing.JList<>();
        player1UnitLabel = new javax.swing.JLabel();
        playerHealthPointInfo = new javax.swing.JLabel();
        playerCastleLevelInfo = new javax.swing.JLabel();
        playerTowerInfo = new javax.swing.JLabel();
        playerMoneyInfo = new javax.swing.JLabel();
        actualPlayerInfo = new javax.swing.JLabel();
        player2UnitLabel = new javax.swing.JLabel();
        player2UnitScrollPane = new javax.swing.JScrollPane();
        player2UnitList = new javax.swing.JList<>();
        warriorUnitPrice = new javax.swing.JLabel();
        tankUnitPrice = new javax.swing.JLabel();
        stoneTowerPirce = new javax.swing.JLabel();
        arrowTowerPrice = new javax.swing.JLabel();
        cannonTowerPrice = new javax.swing.JLabel();
        mineButton = new javax.swing.JButton();
        minePrice = new javax.swing.JLabel();
        castleUpgradeButton = new javax.swing.JButton();
        castleUpgradePrice = new javax.swing.JLabel();
        castleUpgradeLabel = new javax.swing.JLabel();
        arrowSpellPrice = new javax.swing.JLabel();
        fireBallSpellButton = new javax.swing.JButton();
        fireBallSpellPrice = new javax.swing.JLabel();
        arrowSpellButton = new javax.swing.JButton();
        healingSpellPrice = new javax.swing.JLabel();
        healingSpellButton = new javax.swing.JButton();
        spellsLabel = new javax.swing.JLabel();
        mineLabel = new javax.swing.JLabel();
        player1UnitsOnClickedTile = new ArrayList<Integer>();
        player2UnitsOnClickedTile = new ArrayList<Integer>();
        everyButton = new ArrayList<JButton>(Arrays.asList(nextRoundButton, warriorUnitButton, cavalryUnitButton,
                tankUnitButton, stoneTowerButton, arrowTowerButton, cannonTowerButton, mineButton, castleUpgradeButton,
                arrowSpellButton, fireBallSpellButton, healingSpellButton));

        // GAMEBOARD---------------------------------------------------

        gameBoard.setPreferredSize(new java.awt.Dimension(MAPSIZE, MAPSIZE));
        gameBoard.addMouseListener(getInfoMessageFromTile());

        // PLAYER INFO PANEL-------------------------------------------------

        baseText = "Vásároljon a boltból vagy adja át a kört!";
        setLabelTextStyle(messageLabel, fontFamily, 1, 16);
        messageLabel.setText(baseText);

        nextRoundButton.setFont(new java.awt.Font(fontFamily, 0, 14));
        nextRoundButton.setText("Kör átadása");

        setLabelTextStyle(actualPlayerInfo, fontFamily, 1, 14);

        setLabelTextStyle(playerHealthPointInfo, fontFamily, 0, 14);

        setLabelTextStyle(playerCastleLevelInfo, fontFamily, 0, 14);

        setLabelTextStyle(playerMoneyInfo, fontFamily, 0, 14);

        setLabelTextStyle(playerTowerInfo, fontFamily, 0, 14);

        setLabelTextStyle(playerMineInfo, fontFamily, 0, 14);

        setLabelTextStyle(player1UnitLabel, fontFamily, 1, 14);
        player1UnitLabel.setText("Player1 serege");

        setLabelTextStyle(player2UnitLabel, fontFamily, 1, 14);
        player2UnitLabel.setText("Player2 serege");

        player1UnitList.setFont(new java.awt.Font(fontFamily, 0, 14));
        player1UnitList.setCellRenderer(new Palyer1CellRenderer());

        player2UnitList.setFont(new java.awt.Font(fontFamily, 0, 14));
        player2UnitList.setCellRenderer(new Palyer2CellRenderer());
        UpdatePlayerInfos();

        // PRICES------------------------------------------------------------------

        setLabelTextStyle(warriorUnitPrice, fontFamily, 0, 12);
        warriorUnitPrice.setText(String.valueOf(WarriorUnit.getPrice()));

        setLabelTextStyle(cavalryUnitPrice, fontFamily, 0, 12);
        cavalryUnitPrice.setText(String.valueOf(CavalryUnit.getPrice()));

        setLabelTextStyle(tankUnitPrice, fontFamily, 0, 12);
        tankUnitPrice.setText(String.valueOf(TankUnit.getPrice()));

        setLabelTextStyle(stoneTowerPirce, fontFamily, 0, 12);
        stoneTowerPirce.setText(String.valueOf(StoneTower.getPrice()));

        setLabelTextStyle(arrowTowerPrice, fontFamily, 0, 12);
        arrowTowerPrice.setText(String.valueOf(ArrowTower.getPrice()));

        setLabelTextStyle(cannonTowerPrice, fontFamily, 0, 12);
        cannonTowerPrice.setText(String.valueOf(CannonTower.getPrice()));

        setLabelTextStyle(minePrice, fontFamily, 0, 12);
        minePrice.setText(String.valueOf(MineMO.getPrice()));

        setLabelTextStyle(castleUpgradePrice, fontFamily, 0, 12);
        castleUpgradePrice
                .setText(String.valueOf(gameBoard.getGameModel().getActualPlayer().getCastle().getUpgradePrice()));

        setLabelTextStyle(arrowSpellPrice, fontFamily, 0, 12);
        arrowSpellPrice.setText(String.valueOf(ArrowSpell.getPrice()));

        setLabelTextStyle(fireBallSpellPrice, fontFamily, 0, 12);
        fireBallSpellPrice.setText(String.valueOf(FireballSpell.getPrice()));

        setLabelTextStyle(healingSpellPrice, fontFamily, 0, 12);
        healingSpellPrice.setText(String.valueOf(HealingSpell.getPrice()));

        // SHOP LABELS---------------------------------------------------------------

        unitsLabel.setFont(new java.awt.Font(fontFamily, 0, 14));
        unitsLabel.setText("Egységek:");

        towersLabel.setFont(new java.awt.Font(fontFamily, 0, 14));
        towersLabel.setText("Tornyok:");

        castleUpgradeLabel.setFont(new java.awt.Font(fontFamily, 0, 14));
        castleUpgradeLabel.setText("Kastély fejlesztés:");

        mineLabel.setFont(new java.awt.Font(fontFamily, 0, 14));
        mineLabel.setText("Bánya:");

        spellsLabel.setFont(new java.awt.Font(fontFamily, 0, 14));
        spellsLabel.setText("Varázsigék:");

        // SHOP BUTTON ICONS-----------------------------------------------------

        UpdateShop();

        // SHOP BUTTON ACTIONS------------------------------------------------------------

        setShopButtonAsDefault();

        // NEXTROUND ACTION------------------------------------

        nextRoundButton.addActionListener(nextRound());

        // LAYOUT BUILDER--------------------------------------------------------------

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(gameBoard, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                MAPSIZE, Short.MAX_VALUE)
                                                        .addComponent(messageLabel,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(nextRoundButton,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 136,
                                                                Short.MAX_VALUE)
                                                        .addComponent(playerMoneyInfo,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 135,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(actualPlayerInfo,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 135,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(playerHealthPointInfo,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 135,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(playerCastleLevelInfo,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 135,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(playerTowerInfo,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 135,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(player1UnitLabel,
                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(playerMineInfo,
                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 135,
                                                                Short.MAX_VALUE)
                                                        .addComponent(player2UnitLabel,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 132,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(player1UnitScrollPane,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 132,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(player2UnitScrollPane,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 132,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                                        false)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(warriorUnitButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                50,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(39, 39, 39)
                                                                        .addComponent(cavalryUnitButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                50,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(warriorUnitPrice,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                50,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(cavalryUnitPrice,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                50,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(unitsLabel,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 80,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(towersLabel,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 62,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(stoneTowerButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                50,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(stoneTowerPirce,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                50,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(39, 39, 39)
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(arrowTowerPrice,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                50,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(arrowTowerButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                50,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(38, 38, 38)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                .addComponent(tankUnitButton,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(tankUnitPrice,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(cannonTowerButton,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 0,
                                                                        Short.MAX_VALUE))
                                                        .addComponent(cannonTowerPrice,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99,
                                                        Short.MAX_VALUE)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(
                                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(layout
                                                                                                        .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(
                                                                                                                arrowSpellButton,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                50,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(
                                                                                                                arrowSpellPrice,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                50,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(39, 39, 39)
                                                                                                .addGroup(layout
                                                                                                        .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(
                                                                                                                fireBallSpellPrice,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                50,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(
                                                                                                                fireBallSpellButton,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                50,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                        .addComponent(spellsLabel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                87,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(38, 38, 38)
                                                                                .addGroup(layout.createParallelGroup(
                                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(
                                                                                                healingSpellButton,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                50,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(healingSpellPrice,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                50,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                        .addComponent(minePrice,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                50,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(mineLabel,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        62,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        Short.MAX_VALUE)
                                                                                .addComponent(castleUpgradeLabel)))
                                                                .addGap(84, 84, 84))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(castleUpgradePrice,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                50,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(mineButton,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        50,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(96, 96, 96)
                                                                                .addComponent(castleUpgradeButton,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        50,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(115, 115, 115)))))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nextRoundButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(gameBoard, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(actualPlayerInfo, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(playerHealthPointInfo,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(playerCastleLevelInfo)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(playerMoneyInfo, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(playerTowerInfo, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(playerMineInfo, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(player1UnitLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(player1UnitScrollPane,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 131,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(player2UnitLabel)
                                                .addGap(18, 18, 18)
                                                .addComponent(player2UnitScrollPane,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(unitsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(cavalryUnitButton,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(warriorUnitButton,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(tankUnitButton,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(cavalryUnitPrice)
                                                                .addComponent(warriorUnitPrice))
                                                        .addComponent(tankUnitPrice))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(towersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(stoneTowerButton,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(arrowTowerButton,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(cannonTowerButton,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(stoneTowerPirce)
                                                        .addComponent(arrowTowerPrice)
                                                        .addComponent(cannonTowerPrice)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(castleUpgradeLabel,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 21,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(mineLabel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(mineButton,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(castleUpgradeButton,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(minePrice)
                                                        .addComponent(castleUpgradePrice,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spellsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(arrowSpellButton,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(fireBallSpellButton,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(healingSpellButton,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(arrowSpellPrice)
                                                        .addComponent(fireBallSpellPrice)
                                                        .addComponent(healingSpellPrice))))
                                .addGap(50, 50, 50)));
        pack();
        setLocationRelativeTo(null);

        /**
         * Egyszerű kilépés helyett kilépés előtt feldob egy ablakot, megkérdezi biztos
         * ki akar-e lépni.
         */
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener((WindowListener) new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showExitConfirmation();
            }
        });
    }

    /**
     * A kör átadásához tartozó akciófigyelő.
     */
    private ActionListener nextRound() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameBoard.getGameModel().switchCycle();
                if (gameBoard.getGameModel().isGameOver()) {
                    WinnerWindow winnerWindow = new WinnerWindow(gameBoard.getGameModel().findWinner().getName());
                    winnerWindow.setVisible(true);
                    dispose();
                } else {
                    gameBoard.repaint();
                    removeAllMouseListener(gameBoard);
                    setShopButtonAsDefault();
                    UpdateShop();
                    UpdatePlayerInfos();
                }
            }
        };
    }

    /**
     * A kőtorony lerakásához tartozó akciófigyelő.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener buyStoneTower(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Rakja le a tornyot egy üres mezőre a térfelén akadályozás nélkül!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(placeTowerOnMap("stone"));
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * Az íjásztorony lerakásához tartozó akciófigyelő.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener buyArrowTower(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Rakja le a tornyot egy üres mezőre a térfelén akadályozás nélkül!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(placeTowerOnMap("arrow"));
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * Az ágyústorony lerakásához tartozó akciófigyelő.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener buyCannonTower(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Rakja le a tornyot egy üres mezőre a térfelén akadályozás nélkül!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(placeTowerOnMap("cannon"));
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * A harcos lerakásához tartozó akciófigyelő.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener buyWarriorUnit(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Kattintson az egyik barakkjára a harcos elindításához!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(placeUnitOnMap("warrior"));
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * A lovas lerakásához tartozó akciófigyelő.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener buyCavalryUnit(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Kattintson az egyik barakkjára a lovas elindításához!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(placeUnitOnMap("cavalry"));
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * A tank lerakásához tartozó akciófigyelő.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener buyTankUnit(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Kattintson az egyik barakkjára a tank elindításához!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(placeUnitOnMap("tank"));
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * A bánya lerakásához tartozó akciófigyelő.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener buyMine(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Rakja le a bányát egy üres mezőre a térfelén akadályozás nélkül!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(placeMineOnMap());
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * A kastély fejlesztéséhez tartozó akciófigyelő.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener upgradeCastleLevel() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateShop();
                gameBoard.getGameModel().upgradeCastle();
                UpdatePlayerInfos();
                UpdateShop();
            }
        };
    }

    /**
     * A nyílzápor varázsige elhasználásához tartozó akciófigyelő.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener BuyArrowSpell(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Kattintson egy mezőre a nyílzápor varázsige elhasználásához!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(castSpellOnMap("arrow"));
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * A tűzgolyó varázsige elhasználásához tartozó akciófigyelő.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener BuyFireBallSpell(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Kattintson egy mezőre a tűzgolyó varázsige elhasználásához!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(castSpellOnMap("fireBall"));
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * A gyógyítás varázsige elhasználásához tartozó akciófigyelő.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener BuyHealingSpell(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Kattintson egy mezőre a gyógyítás varázsige elhasználásához!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(castSpellOnMap("healing"));
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * A tornyok lehelyezéséhez tartozó egérfigyelő.
     * 
     * @param towerType Torony típusa.
     */
    private MouseListener placeTowerOnMap(String towerType) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (gameBoard.getGameModel().placeTower(new Point(event.getY() / tileSize, event.getX() / tileSize),
                        towerType)) {
                    messageLabel.setText(baseText);
                    enableAllButton();
                    gameBoard.repaint();
                    gameBoard.removeMouseListener(this);
                    setShopButtonAsDefault();
                    UpdatePlayerInfos();
                    UpdateShop();
                }
            };
        };
    }

    /**
     * Az egység lehelyezéséhez tartozó egérfigyelő.
     * 
     * @param unitType Egység típusa.
     */
    private MouseListener placeUnitOnMap(String unitType) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (gameBoard.getGameModel().createUnits(new Point(event.getY() / tileSize, event.getX() / tileSize),
                        unitType)) {
                    messageLabel.setText(baseText);
                    enableAllButton();
                    gameBoard.repaint();
                    gameBoard.removeMouseListener(this);
                    setShopButtonAsDefault();
                    UpdatePlayerInfos();
                    UpdateShop();
                }
            }
        };
    }

    /**
     * A bánya lehelyezéséhez tartozó egérfigyelő.
     */
    private MouseListener placeMineOnMap() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (gameBoard.getGameModel().placeMine(new Point(event.getY() / tileSize, event.getX() / tileSize))) {
                    messageLabel.setText(baseText);
                    enableAllButton();
                    gameBoard.repaint();
                    gameBoard.removeMouseListener(this);
                    setShopButtonAsDefault();
                    UpdatePlayerInfos();
                    UpdateShop();
                }
            }
        };
    }

    /**
     * A varázsigék lehelyezéséhez tartozó egérfigyelő.
     * 
     * @param spellType Varázsige típusa.
     */
    private MouseListener castSpellOnMap(String spellType) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                boolean spellCasted = false;
                switch (spellType) {
                    case "arrow":
                        if (gameBoard.getGameModel()
                                .placeArrowSpell(new Point(event.getY() / tileSize, event.getX() / tileSize))) {
                            gameBoard.castArrowSpell(new Point(event.getY() / tileSize, event.getX() / tileSize));
                            spellCasted = true;
                        }

                        break;
                    case "fireBall":
                        if (gameBoard.getGameModel()
                                .placeFireballSpell(new Point(event.getY() / tileSize, event.getX() / tileSize))) {
                            gameBoard.castFireBallSpell(new Point(event.getY() / tileSize, event.getX() / tileSize));
                            spellCasted = true;
                        }

                        break;
                    case "healing":
                        if (gameBoard.getGameModel()
                                .placeHealingSpell(new Point(event.getY() / tileSize, event.getX() / tileSize))) {
                            gameBoard.castHealingSpell(new Point(event.getY() / tileSize, event.getX() / tileSize));
                            spellCasted = true;
                        }
                        break;
                    default:
                        break;
                }

                if (spellCasted) {
                    messageLabel.setText(baseText);
                    enableAllButton();
                    gameBoard.removeMouseListener(this);
                    setShopButtonAsDefault();
                    UpdatePlayerInfos();
                    UpdateShop();
                }
            };
        };
    }

    /**
     * Jobb egér kattintásra felül kiírja mi található a mezőn.
     */
    private MouseListener getInfoMessageFromTile() {
        return new MouseAdapter() {
            private String previousMessage;

            @Override
            public void mousePressed(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON3) {
                    previousMessage = messageLabel.getText();
                    messageLabel.setText(gameBoard.getGameModel()
                            .listClickedPosition(new Point(event.getY() / tileSize, event.getX() / tileSize)));
                    ArrayList<Units> player1Units = gameBoard.getGameModel().getPlayer1().getUnits();
                    for (int i = 0; i < player1Units.size(); i++) {
                        if (gameBoard.getGameModel().getPlayer1().getUnits().get(i).getPosition().y == event.getX()
                                / tileSize
                                && gameBoard.getGameModel().getPlayer1().getUnits().get(i).getPosition().x == event
                                        .getY() / tileSize) {
                            player1UnitsOnClickedTile.add(i);
                        }
                    }
                    ;
                    player1UnitList.repaint();

                    ArrayList<Units> player2Units = gameBoard.getGameModel().getPlayer2().getUnits();
                    for (int i = 0; i < player2Units.size(); i++) {
                        if (gameBoard.getGameModel().getPlayer2().getUnits().get(i).getPosition().y == event.getX()
                                / tileSize
                                && gameBoard.getGameModel().getPlayer2().getUnits().get(i).getPosition().x == event
                                        .getY() / tileSize) {
                            player2UnitsOnClickedTile.add(i);
                        }
                    }
                    ;
                    player2UnitList.repaint();
                }
            };

            @Override
            public void mouseReleased(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON3) {
                    messageLabel.setText(previousMessage);
                    player1UnitsOnClickedTile.removeAll(player1UnitsOnClickedTile);
                    player1UnitList.repaint();
                    player2UnitsOnClickedTile.removeAll(player2UnitsOnClickedTile);
                    player2UnitList.repaint();
                }
            };
        };
    }

    /**
     * Felül jelzi üzenetben ha adott dolgot nem tudjuk megvenni.
     * 
     * @param shopItem Amit meg akarunk venni.
     * @param money Mennyibe kerül.
     */
    private MouseListener notEnoughtMoneyForShopItem(String shopItem, int money) {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                messageLabel.setText("Nincs elég pénze a" + shopItem + ". Az ára: " + money + " arany!");
            }

            @Override
            public void mouseExited(MouseEvent event) {
                messageLabel.setText(baseText);
            }
        };

    }

    /**
     * Felül jelzi üzenetben ha kevés a kastély szintje ahhoz amit meg akarunk venni.
     * 
     * @param requiredCastleLevel Milyen szintűnek kell lennie a kastélynak.
     */
    private MouseListener castleLevelLow(int requiredCastleLevel) {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                messageLabel.setText(requiredCastleLevel + ". szintű kastély kell a torony lehelyezéséhez!");
            }

            @Override
            public void mouseExited(MouseEvent event) {
                messageLabel.setText(baseText);
            }
        };
    }

    /**
     * Felül jelzi üzenetben ha a kastély elérte a maximális szintet.
     */
    private MouseListener castleLevelMax() {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                messageLabel.setText("A kastély elérte a maximális szintet!");
            }

            @Override
            public void mouseExited(MouseEvent event) {
                messageLabel.setText(baseText);
            }
        };
    }

    /**
     * Felül jelzi üzenetben ha épp másik dolgot vásárolunk.
     */
    private MouseListener otherBuyingInProgress() {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                messageLabel.setText("Először fejezze be a jelenlegi vásárlást!");
            }

            @Override
            public void mouseExited(MouseEvent event) {
                messageLabel.setText(buyingText);
            }
        };
    }

    /**
     * Felül jelzi üzenetben ha a mostani vásárlást vissza akarjuk vonni.
     */
    private MouseListener thisBuyingInProgress() {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                messageLabel.setText("Vásárlás visszavonása!");
            }

            @Override
            public void mouseExited(MouseEvent event) {
                messageLabel.setText(buyingText);
            }
        };
    }
    
    /**
     * A megvásárolni kívánt dolog helyére a visszavonás lehetőséget teszi be.
     * 
     * @param button Amire vonatkozik.
     */
    private void switchToCancelBuying(JButton button) {
        removeAllActionListener(button);
        insertShopNaturalItemPicture(button, "/img/cancel.png");
        button.addActionListener(cancelBuying(button));
    }
    
    /**
     * Visszavonja a vásárlást.
     * 
     * @param button Amire vonatkozik.
     */
    private ActionListener cancelBuying(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAllActionListener(button);
                removeAllMouseListener(gameBoard);
                messageLabel.setText(baseText);
                enableAllButton();
                setShopButtonAsDefault();
                UpdateShop();
            }
        };
    }
    
    /**
     * A megadott gombról törli az összes akciófigyelőt
     * 
     * @param button Amire vonatkozik.
     */
    private void removeAllActionListener(JButton button) {
        for (ActionListener actionListener : button.getActionListeners()) {
            button.removeActionListener(actionListener);
        }
    }
    
    /**
     * A játékmezőről törli az összes egérfigyelőt
     * 
     * @param panel A játékmező.
     */
    private void removeAllMouseListener(JPanel panel) {
        for (MouseListener mouseListener : panel.getMouseListeners()) {
            gameBoard.removeMouseListener(mouseListener);
        }
        gameBoard.addMouseListener(getInfoMessageFromTile());
    }
    
    /**
     * Letilt minden gombot és hozzájuk adja hogy jelezzék üzenetben 
     * hogy épp nem ennek a vásárlása folyik.
     * 
     * @param selectedButton Amire vonatkozik.
     */
    private void disableAllOtherButton(JButton selectedButton) {
        for (JButton button : everyButton) {
            button.setEnabled(false);
            button.addMouseListener(otherBuyingInProgress());
        }
        selectedButton.setEnabled(true);
        selectedButton
                .removeMouseListener(selectedButton.getMouseListeners()[selectedButton.getMouseListeners().length - 1]);
        selectedButton.addMouseListener(thisBuyingInProgress());
    }
    
    /**
     * Minden gombot újra használhatóvá tesz.
     */
    private void enableAllButton() {
        for (JButton button : everyButton) {
            button.setEnabled(true);
            button.removeMouseListener(button.getMouseListeners()[button.getMouseListeners().length - 1]);
        }
    }    

    /**
     * Beállítja az adott szöveg stílusát.
     * 
     * @param label Amire vonatkozik.
     * @param fontfamily Szövegcsalád.
     * @param bold Félkövér legyen-e.
     * @param fontsize Szöveg nagyság.
     */
    private void setLabelTextStyle(JLabel label, String fontfamily, int bold, int fontsize) {
        label.setFont(new java.awt.Font(fontfamily, bold, fontsize));
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    }

    /**
     * Beállítja a bolt összes gombját alapértelmezettre.
     */
    private void setShopButtonAsDefault() {
        removeAllActionListener(stoneTowerButton);
        stoneTowerButton.addActionListener(buyStoneTower(stoneTowerButton));

        removeAllActionListener(arrowTowerButton);
        arrowTowerButton.addActionListener(buyArrowTower(arrowTowerButton));

        removeAllActionListener(cannonTowerButton);
        cannonTowerButton.addActionListener(buyCannonTower(cannonTowerButton));

        removeAllActionListener(warriorUnitButton);
        warriorUnitButton.addActionListener(buyWarriorUnit(warriorUnitButton));

        removeAllActionListener(cavalryUnitButton);
        cavalryUnitButton.addActionListener(buyCavalryUnit(cavalryUnitButton));

        removeAllActionListener(tankUnitButton);
        tankUnitButton.addActionListener(buyTankUnit(tankUnitButton));

        removeAllActionListener(mineButton);
        mineButton.addActionListener(buyMine(mineButton));

        removeAllActionListener(castleUpgradeButton);
        castleUpgradeButton.addActionListener(upgradeCastleLevel());

        removeAllActionListener(arrowSpellButton);
        arrowSpellButton.addActionListener(BuyArrowSpell(arrowSpellButton));

        removeAllActionListener(fireBallSpellButton);
        fireBallSpellButton.addActionListener(BuyFireBallSpell(fireBallSpellButton));

        removeAllActionListener(healingSpellButton);
        healingSpellButton.addActionListener(BuyHealingSpell(healingSpellButton));
    }

    /**
     * Beállítja az adott játékos információit.
     */
    private void UpdatePlayerInfos() {

        // UPDATE PLAYER INFOS IN SIDEBAR

        if (gameBoard.getGameModel().getActualPlayer().getName() == "player1") {
            actualPlayerInfo.setText("Player1 köre");
        } else {
            actualPlayerInfo.setText("Player2 köre");
        }
        playerHealthPointInfo.setText("Kastély életerő: "
                + String.valueOf(gameBoard.getGameModel().getActualPlayer().getCastle().getHealth()));
        playerCastleLevelInfo.setText("Kastély szint: "
                + String.valueOf(gameBoard.getGameModel().getActualPlayer().getCastle().getLevel() + 1));
        playerMoneyInfo.setText("Arany: " + String.valueOf(gameBoard.getGameModel().getActualPlayer().getMoney()));
        playerTowerInfo.setText(
                "Tornyok: " + String.valueOf(gameBoard.getGameModel().getActualPlayer().getAllTowers().size()));
        playerMineInfo
                .setText("Bányák: " + String.valueOf(gameBoard.getGameModel().getActualPlayer().getAllMines().size()));

        player1UnitList.setModel(new javax.swing.AbstractListModel<String>() {
            ArrayList<String> list = fillList();

            private ArrayList<String> fillList() {
                ArrayList<String> l = new ArrayList<String>();
                ArrayList<Units> units = gameBoard.getGameModel().getPlayer1().getUnits();

                int w = 0;
                int c = 0;
                int t = 0;
                String unitName = null;
                for (int i = 0; i < units.size(); i++) {
                    if (units.get(i) instanceof WarriorUnit) {
                        unitName = "Harcos#" + ++w;
                    } else if (units.get(i) instanceof CavalryUnit) {
                        unitName = "Lovas#" + ++c;
                    } else if (units.get(i) instanceof TankUnit) {
                        unitName = "Tank#" + ++t;
                    }
                    l.add(unitName + ": " + units.get(i).getHealth() + "élet");
                }
                ;
                return l;
            };

            public int getSize() {
                return list.size();
            }

            public String getElementAt(int i) {
                return list.get(i);
            }
        });
        player1UnitScrollPane.setViewportView(player1UnitList);

        player2UnitList.setModel(new javax.swing.AbstractListModel<String>() {
            ArrayList<String> list = fillList();

            private ArrayList<String> fillList() {
                ArrayList<String> l = new ArrayList<String>();
                ArrayList<Units> units = gameBoard.getGameModel().getPlayer2().getUnits();

                int w = 0;
                int c = 0;
                int t = 0;
                String unitName = null;
                for (int i = 0; i < units.size(); i++) {
                    if (units.get(i) instanceof WarriorUnit) {
                        unitName = "Harcos#" + ++w;
                    } else if (units.get(i) instanceof CavalryUnit) {
                        unitName = "Lovas#" + ++c;
                    } else if (units.get(i) instanceof TankUnit) {
                        unitName = "Tank#" + ++t;
                    }
                    l.add(unitName + ": " + units.get(i).getHealth() + "élet");
                }
                ;
                return l;
            };

            public int getSize() {
                return list.size();
            }

            public String getElementAt(int i) {
                return list.get(i);
            }
        });
        player2UnitScrollPane.setViewportView(player2UnitList);
    }

    /**
     * Frissíti a boltot és annak kinézetét.
     */
    private void UpdateShop() {

        // IMAGES

        // UNITS
        insertShopTeamSpecialPicture(warriorUnitButton, "/img/p1_warriorUnit_shop.png", "/img/p2_warriorUnit_shop.png");
        insertShopTeamSpecialPicture(cavalryUnitButton, "/img/p1_cavalryUnit_shop.png", "/img/p2_cavalryUnit_shop.png");
        insertShopTeamSpecialPicture(tankUnitButton, "/img/p1_tankUnit_shop.png", "/img/p2_tankUnit_shop.png");

        // TOWERS
        insertShopTeamSpecialPicture(stoneTowerButton, "/img/p1_stoneTower_shop.png", "/img/p2_stoneTower_shop.png");
        insertShopTeamSpecialPicture(arrowTowerButton, "/img/p1_arrowTower_shop.png", "/img/p2_arrowTower_shop.png");
        insertShopTeamSpecialPicture(cannonTowerButton, "/img/p1_cannonTower_shop.png", "/img/p2_cannonTower_shop.png");

        // MINE
        insertShopTeamSpecialPicture(mineButton, "/img/p1_mine_shop.png", "/img/p2_mine_shop.png");

        // CASTLE_UPGRADE
        insertShopNaturalItemPicture(castleUpgradeButton, "/img/castleUpgrade.png");

        // SPELLS
        insertShopNaturalItemPicture(arrowSpellButton, "/img/arrowSpell.png");
        insertShopNaturalItemPicture(fireBallSpellButton, "/img/fireBallSpell.png");
        insertShopNaturalItemPicture(healingSpellButton, "/img/healingSpell.png");

        // AFFORDABLE ITEMS

        // UNITS
        checkMoneyToBuy(warriorUnitButton, WarriorUnit.getPrice(), " harcos egységre");
        checkMoneyToBuy(cavalryUnitButton, CavalryUnit.getPrice(), " lovas egységre");
        checkMoneyToBuy(tankUnitButton, TankUnit.getPrice(), " tank egységre");

        // TOWERS
        checkMoneyToBuy(stoneTowerButton, StoneTower.getPrice(), " kőtoronyra");
        checkCastleLevelAndMoneyToBuy(arrowTowerButton, ArrowTower.getPrice(), " íjásztoronyra", ArrowTower.getLevel());
        checkCastleLevelAndMoneyToBuy(cannonTowerButton, CannonTower.getPrice(), "z ágyústoronyra",
                CannonTower.getLevel());

        // MINE
        checkMoneyToBuy(mineButton, MineMO.getPrice(), " bányára");

        // CASTLE_UPGRADE

        if (gameBoard.getGameModel().getActualPlayer().getCastle().getLevel() == 2) {
            castleUpgradePrice.setText("Max");
            castleUpgradeButton.setEnabled(false);
            castleUpgradeButton.addMouseListener(castleLevelMax());
        } else {
            if (castleUpgradeButton.getMouseListeners().length > 1) {
                castleUpgradeButton.removeMouseListener(
                        castleUpgradeButton.getMouseListeners()[castleUpgradeButton.getMouseListeners().length - 1]);
            }
            checkMoneyToBuy(castleUpgradeButton,
                    gameBoard.getGameModel().getActualPlayer().getCastle().getUpgradePrice(), " kastély fejlesztésre");
            castleUpgradePrice
                    .setText(String.valueOf(gameBoard.getGameModel().getActualPlayer().getCastle().getUpgradePrice()));
        }

        // SPELLS
        checkMoneyToBuy(arrowSpellButton, ArrowSpell.getPrice(), " nyílzápor varázsigére");
        checkMoneyToBuy(fireBallSpellButton, FireballSpell.getPrice(), " tűzgolyó varázsigére");
        checkMoneyToBuy(healingSpellButton, HealingSpell.getPrice(), " gyógyító varázsigére");
    }

    /**
     * Ellenőrzi hogy a kastély elég magas szintű-e és van-e elég pénze a játékosnak.
     */
    private void checkCastleLevelAndMoneyToBuy(JButton button, int price, String shopItem, int requiredCastleLevel) {
        if (gameBoard.getGameModel().getActualPlayer().getCastle().getLevel() < requiredCastleLevel) {
            button.setEnabled(false);
            if (button.getMouseListeners().length == 1) {
                button.addMouseListener(castleLevelLow(requiredCastleLevel + 1));
            }
        } else {
            if (button.getMouseListeners().length > 1) {
                button.removeMouseListener(button.getMouseListeners()[button.getMouseListeners().length - 1]);
            }
            checkMoneyToBuy(button, price, shopItem);
        }
    }

    /**
     * Ellenőrzi hogy van-e elég pénze a játékosnak.
     */
    private void checkMoneyToBuy(JButton button, int price, String shopItem) {
        MouseListener setText = notEnoughtMoneyForShopItem(shopItem, price);
        if (gameBoard.getGameModel().getActualPlayer().getMoney() < price) {
            button.setEnabled(false);
            if (button.getMouseListeners().length == 1) {
                button.addMouseListener(setText);
            }
        } else {
            button.setEnabled(true);
            if (button.getMouseListeners().length > 1) {
                button.removeMouseListener(button.getMouseListeners()[button.getMouseListeners().length - 1]);
            }
        }
    }

    /**
     * Két kép közül eldönti melyiket kell használni és azt betölti.
     * 
     * @param button Amire vonatkozi.
     * @param player1Image Player1-nél lévő kép.
     * @param player2Image Player2-nél lévő kép.
     */
    private void insertShopTeamSpecialPicture(JButton button, String player1Image, String player2Image) {
        Image img;
        try {
            if (gameBoard.getGameModel().getActualPlayer().getName() == "player1") {
                img = ImageIO.read(getClass().getResource(player1Image)).getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            } else {
                img = ImageIO.read(getClass().getResource(player2Image)).getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            }
            button.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Betölti a képet megadott gombra.
     * @param button Amire vonatkozi.
     * @param image Betöltendő kép.
     */
    private void insertShopNaturalItemPicture(JButton button, String image) {
        Image img;
        try {
            img = ImageIO.read(getClass().getResource(image)).getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            button.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Kilépés megerősítése. Megkérdezi a felhasználót, hogy biztos ki akar-e
     * lépni. Ez egy külön panelen jelenik meg igen és nem opciókkal.
     */
    private void showExitConfirmation() {
        int n = JOptionPane.showConfirmDialog(this, "Valoban ki akar lepni?",
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

    /**
     * Visszaadja Player1 egységeinek listáját.
     */
    public static ArrayList<Integer> getPlayer1UnitsOnClickedTile() {
        return player1UnitsOnClickedTile;
    }

    /**
     * Visszaadja Player2 egységeinek listáját.
     */
    public static ArrayList<Integer> getPlayer2UnitsOnClickedTile() {
        return player2UnitsOnClickedTile;
    }

}
