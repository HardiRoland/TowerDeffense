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
     * L??trehozza a j??t??k k??perny??j??t. Fel??l l??tszanak a j??t??khoz tartoz?? ??zenetek.
     * Emellett tal??lhat?? a k??r ??tad??sa gomb. Alatta pedig maga a j??t??kmez?? ??s a j??t??kosok
     * adatai k??rt??l f??gg??en. Legallul pedig a bolt ahol sereget, tornyokat, b??ny??t
     * ??s var??zsig??ket lehet venni.
     * 
     * @param difficulty A j??t??k neh??zs??g??t adja meg.
     * @param tileInRow  A mez??k sz??m??t adja meg egy sorban.
     */
    public GameWindow(int difficulty, int tileInRow) {
        setTitle("Tower Defense J??t??k");
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

        baseText = "V??s??roljon a boltb??l vagy adja ??t a k??rt!";
        setLabelTextStyle(messageLabel, fontFamily, 1, 16);
        messageLabel.setText(baseText);

        nextRoundButton.setFont(new java.awt.Font(fontFamily, 0, 14));
        nextRoundButton.setText("K??r ??tad??sa");

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
        unitsLabel.setText("Egys??gek:");

        towersLabel.setFont(new java.awt.Font(fontFamily, 0, 14));
        towersLabel.setText("Tornyok:");

        castleUpgradeLabel.setFont(new java.awt.Font(fontFamily, 0, 14));
        castleUpgradeLabel.setText("Kast??ly fejleszt??s:");

        mineLabel.setFont(new java.awt.Font(fontFamily, 0, 14));
        mineLabel.setText("B??nya:");

        spellsLabel.setFont(new java.awt.Font(fontFamily, 0, 14));
        spellsLabel.setText("Var??zsig??k:");

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
         * Egyszer?? kil??p??s helyett kil??p??s el??tt feldob egy ablakot, megk??rdezi biztos
         * ki akar-e l??pni.
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
     * A k??r ??tad??s??hoz tartoz?? akci??figyel??.
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
     * A k??torony lerak??s??hoz tartoz?? akci??figyel??.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener buyStoneTower(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Rakja le a tornyot egy ??res mez??re a t??rfel??n akad??lyoz??s n??lk??l!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(placeTowerOnMap("stone"));
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * Az ??j??sztorony lerak??s??hoz tartoz?? akci??figyel??.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener buyArrowTower(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Rakja le a tornyot egy ??res mez??re a t??rfel??n akad??lyoz??s n??lk??l!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(placeTowerOnMap("arrow"));
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * Az ??gy??storony lerak??s??hoz tartoz?? akci??figyel??.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener buyCannonTower(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Rakja le a tornyot egy ??res mez??re a t??rfel??n akad??lyoz??s n??lk??l!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(placeTowerOnMap("cannon"));
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * A harcos lerak??s??hoz tartoz?? akci??figyel??.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener buyWarriorUnit(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Kattintson az egyik barakkj??ra a harcos elind??t??s??hoz!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(placeUnitOnMap("warrior"));
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * A lovas lerak??s??hoz tartoz?? akci??figyel??.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener buyCavalryUnit(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Kattintson az egyik barakkj??ra a lovas elind??t??s??hoz!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(placeUnitOnMap("cavalry"));
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * A tank lerak??s??hoz tartoz?? akci??figyel??.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener buyTankUnit(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Kattintson az egyik barakkj??ra a tank elind??t??s??hoz!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(placeUnitOnMap("tank"));
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * A b??nya lerak??s??hoz tartoz?? akci??figyel??.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener buyMine(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Rakja le a b??ny??t egy ??res mez??re a t??rfel??n akad??lyoz??s n??lk??l!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(placeMineOnMap());
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * A kast??ly fejleszt??s??hez tartoz?? akci??figyel??.
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
     * A ny??lz??por var??zsige elhaszn??l??s??hoz tartoz?? akci??figyel??.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener BuyArrowSpell(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Kattintson egy mez??re a ny??lz??por var??zsige elhaszn??l??s??hoz!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(castSpellOnMap("arrow"));
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * A t??zgoly?? var??zsige elhaszn??l??s??hoz tartoz?? akci??figyel??.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener BuyFireBallSpell(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Kattintson egy mez??re a t??zgoly?? var??zsige elhaszn??l??s??hoz!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(castSpellOnMap("fireBall"));
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * A gy??gy??t??s var??zsige elhaszn??l??s??hoz tartoz?? akci??figyel??.
     * 
     * @param button Melyik gombhoz van rendelve.
     */
    private ActionListener BuyHealingSpell(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyingText = "Kattintson egy mez??re a gy??gy??t??s var??zsige elhaszn??l??s??hoz!";
                messageLabel.setText(buyingText);
                UpdateShop();
                disableAllOtherButton(button);
                gameBoard.addMouseListener(castSpellOnMap("healing"));
                switchToCancelBuying(button);
            }
        };
    }

    /**
     * A tornyok lehelyez??s??hez tartoz?? eg??rfigyel??.
     * 
     * @param towerType Torony t??pusa.
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
     * Az egys??g lehelyez??s??hez tartoz?? eg??rfigyel??.
     * 
     * @param unitType Egys??g t??pusa.
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
     * A b??nya lehelyez??s??hez tartoz?? eg??rfigyel??.
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
     * A var??zsig??k lehelyez??s??hez tartoz?? eg??rfigyel??.
     * 
     * @param spellType Var??zsige t??pusa.
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
     * Jobb eg??r kattint??sra fel??l ki??rja mi tal??lhat?? a mez??n.
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
     * Fel??l jelzi ??zenetben ha adott dolgot nem tudjuk megvenni.
     * 
     * @param shopItem Amit meg akarunk venni.
     * @param money Mennyibe ker??l.
     */
    private MouseListener notEnoughtMoneyForShopItem(String shopItem, int money) {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                messageLabel.setText("Nincs el??g p??nze a" + shopItem + ". Az ??ra: " + money + " arany!");
            }

            @Override
            public void mouseExited(MouseEvent event) {
                messageLabel.setText(baseText);
            }
        };

    }

    /**
     * Fel??l jelzi ??zenetben ha kev??s a kast??ly szintje ahhoz amit meg akarunk venni.
     * 
     * @param requiredCastleLevel Milyen szint??nek kell lennie a kast??lynak.
     */
    private MouseListener castleLevelLow(int requiredCastleLevel) {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                messageLabel.setText(requiredCastleLevel + ". szint?? kast??ly kell a torony lehelyez??s??hez!");
            }

            @Override
            public void mouseExited(MouseEvent event) {
                messageLabel.setText(baseText);
            }
        };
    }

    /**
     * Fel??l jelzi ??zenetben ha a kast??ly el??rte a maxim??lis szintet.
     */
    private MouseListener castleLevelMax() {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                messageLabel.setText("A kast??ly el??rte a maxim??lis szintet!");
            }

            @Override
            public void mouseExited(MouseEvent event) {
                messageLabel.setText(baseText);
            }
        };
    }

    /**
     * Fel??l jelzi ??zenetben ha ??pp m??sik dolgot v??s??rolunk.
     */
    private MouseListener otherBuyingInProgress() {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                messageLabel.setText("El??sz??r fejezze be a jelenlegi v??s??rl??st!");
            }

            @Override
            public void mouseExited(MouseEvent event) {
                messageLabel.setText(buyingText);
            }
        };
    }

    /**
     * Fel??l jelzi ??zenetben ha a mostani v??s??rl??st vissza akarjuk vonni.
     */
    private MouseListener thisBuyingInProgress() {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                messageLabel.setText("V??s??rl??s visszavon??sa!");
            }

            @Override
            public void mouseExited(MouseEvent event) {
                messageLabel.setText(buyingText);
            }
        };
    }
    
    /**
     * A megv??s??rolni k??v??nt dolog hely??re a visszavon??s lehet??s??get teszi be.
     * 
     * @param button Amire vonatkozik.
     */
    private void switchToCancelBuying(JButton button) {
        removeAllActionListener(button);
        insertShopNaturalItemPicture(button, "/img/cancel.png");
        button.addActionListener(cancelBuying(button));
    }
    
    /**
     * Visszavonja a v??s??rl??st.
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
     * A megadott gombr??l t??rli az ??sszes akci??figyel??t
     * 
     * @param button Amire vonatkozik.
     */
    private void removeAllActionListener(JButton button) {
        for (ActionListener actionListener : button.getActionListeners()) {
            button.removeActionListener(actionListener);
        }
    }
    
    /**
     * A j??t??kmez??r??l t??rli az ??sszes eg??rfigyel??t
     * 
     * @param panel A j??t??kmez??.
     */
    private void removeAllMouseListener(JPanel panel) {
        for (MouseListener mouseListener : panel.getMouseListeners()) {
            gameBoard.removeMouseListener(mouseListener);
        }
        gameBoard.addMouseListener(getInfoMessageFromTile());
    }
    
    /**
     * Letilt minden gombot ??s hozz??juk adja hogy jelezz??k ??zenetben 
     * hogy ??pp nem ennek a v??s??rl??sa folyik.
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
     * Minden gombot ??jra haszn??lhat??v?? tesz.
     */
    private void enableAllButton() {
        for (JButton button : everyButton) {
            button.setEnabled(true);
            button.removeMouseListener(button.getMouseListeners()[button.getMouseListeners().length - 1]);
        }
    }    

    /**
     * Be??ll??tja az adott sz??veg st??lus??t.
     * 
     * @param label Amire vonatkozik.
     * @param fontfamily Sz??vegcsal??d.
     * @param bold F??lk??v??r legyen-e.
     * @param fontsize Sz??veg nagys??g.
     */
    private void setLabelTextStyle(JLabel label, String fontfamily, int bold, int fontsize) {
        label.setFont(new java.awt.Font(fontfamily, bold, fontsize));
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    }

    /**
     * Be??ll??tja a bolt ??sszes gombj??t alap??rtelmezettre.
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
     * Be??ll??tja az adott j??t??kos inform??ci??it.
     */
    private void UpdatePlayerInfos() {

        // UPDATE PLAYER INFOS IN SIDEBAR

        if (gameBoard.getGameModel().getActualPlayer().getName() == "player1") {
            actualPlayerInfo.setText("Player1 k??re");
        } else {
            actualPlayerInfo.setText("Player2 k??re");
        }
        playerHealthPointInfo.setText("Kast??ly ??leter??: "
                + String.valueOf(gameBoard.getGameModel().getActualPlayer().getCastle().getHealth()));
        playerCastleLevelInfo.setText("Kast??ly szint: "
                + String.valueOf(gameBoard.getGameModel().getActualPlayer().getCastle().getLevel() + 1));
        playerMoneyInfo.setText("Arany: " + String.valueOf(gameBoard.getGameModel().getActualPlayer().getMoney()));
        playerTowerInfo.setText(
                "Tornyok: " + String.valueOf(gameBoard.getGameModel().getActualPlayer().getAllTowers().size()));
        playerMineInfo
                .setText("B??ny??k: " + String.valueOf(gameBoard.getGameModel().getActualPlayer().getAllMines().size()));

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
                    l.add(unitName + ": " + units.get(i).getHealth() + "??let");
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
                    l.add(unitName + ": " + units.get(i).getHealth() + "??let");
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
     * Friss??ti a boltot ??s annak kin??zet??t.
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
        checkMoneyToBuy(warriorUnitButton, WarriorUnit.getPrice(), " harcos egys??gre");
        checkMoneyToBuy(cavalryUnitButton, CavalryUnit.getPrice(), " lovas egys??gre");
        checkMoneyToBuy(tankUnitButton, TankUnit.getPrice(), " tank egys??gre");

        // TOWERS
        checkMoneyToBuy(stoneTowerButton, StoneTower.getPrice(), " k??toronyra");
        checkCastleLevelAndMoneyToBuy(arrowTowerButton, ArrowTower.getPrice(), " ??j??sztoronyra", ArrowTower.getLevel());
        checkCastleLevelAndMoneyToBuy(cannonTowerButton, CannonTower.getPrice(), "z ??gy??storonyra",
                CannonTower.getLevel());

        // MINE
        checkMoneyToBuy(mineButton, MineMO.getPrice(), " b??ny??ra");

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
                    gameBoard.getGameModel().getActualPlayer().getCastle().getUpgradePrice(), " kast??ly fejleszt??sre");
            castleUpgradePrice
                    .setText(String.valueOf(gameBoard.getGameModel().getActualPlayer().getCastle().getUpgradePrice()));
        }

        // SPELLS
        checkMoneyToBuy(arrowSpellButton, ArrowSpell.getPrice(), " ny??lz??por var??zsig??re");
        checkMoneyToBuy(fireBallSpellButton, FireballSpell.getPrice(), " t??zgoly?? var??zsig??re");
        checkMoneyToBuy(healingSpellButton, HealingSpell.getPrice(), " gy??gy??t?? var??zsig??re");
    }

    /**
     * Ellen??rzi hogy a kast??ly el??g magas szint??-e ??s van-e el??g p??nze a j??t??kosnak.
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
     * Ellen??rzi hogy van-e el??g p??nze a j??t??kosnak.
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
     * K??t k??p k??z??l eld??nti melyiket kell haszn??lni ??s azt bet??lti.
     * 
     * @param button Amire vonatkozi.
     * @param player1Image Player1-n??l l??v?? k??p.
     * @param player2Image Player2-n??l l??v?? k??p.
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
     * Bet??lti a k??pet megadott gombra.
     * @param button Amire vonatkozi.
     * @param image Bet??ltend?? k??p.
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
     * Kil??p??s meger??s??t??se. Megk??rdezi a felhaszn??l??t, hogy biztos ki akar-e
     * l??pni. Ez egy k??l??n panelen jelenik meg igen ??s nem opci??kkal.
     */
    private void showExitConfirmation() {
        int n = JOptionPane.showConfirmDialog(this, "Valoban ki akar lepni?",
                "Meger??s??t??s", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            doUponExit();
        }
    }

    /**
     * Kil??p??s a programb??l. Bez??rja mag??t a program ??s le??ll??tja az id??z??t??t.
     */
    protected void doUponExit() {
        GameBoard.stopTimer();
        dispose();
    }

    /**
     * Visszaadja Player1 egys??geinek list??j??t.
     */
    public static ArrayList<Integer> getPlayer1UnitsOnClickedTile() {
        return player1UnitsOnClickedTile;
    }

    /**
     * Visszaadja Player2 egys??geinek list??j??t.
     */
    public static ArrayList<Integer> getPlayer2UnitsOnClickedTile() {
        return player2UnitsOnClickedTile;
    }

}
