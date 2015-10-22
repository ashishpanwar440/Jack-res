package csc4380.cle25;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class Project1 extends javax.swing.JFrame {
    final Project1 thisObject = this;
    final int NUMBER_OF_PIECES = 7;
    final int[] COLOR_1 = {0, 189, 255};
    final int[] COLOR_2 = {0, 255, 120};
    final int[] PIECE_COLOR = {255, 255, 255};
    final int DIRECTION_LEFT = 0;
    final int DIRECTION_DOWN = 1;
    final int DIRECTION_RIGHT = 2;
    final int DIRECTION_ROTATE = 3;
    final int LINE_PIECE = 0;
    final int SQUARE_PIECE = 1;
    final int L_PIECE = 2;
    final int T_PIECE = 3;
    final int REVERSED_L_PIECE = 4;
    final int S_PIECE = 5;
    final int Z_PIECE = 6;
    final int DEFAULT_INTERVAL = 800;
    final int MIN_INTERVAL = 30;
    final int DIMENSION_WIDTH = 10;
    final int DIMENSION_HEIGHT = 22;
    final Color GHOST_COLOR = new Color(150, 150, 150);
    final String TITLE = "Project 1, cle25 - TERRY THE TETRIS - ";
    AudioPlayer theme = new AudioPlayer("tetris.wav", true);
    AudioPlayer meep = new AudioPlayer("meep.wav", false);
    AudioPlayer boop = new AudioPlayer("boop.wav", false);
    AudioPlayer pugh = new AudioPlayer("pugh.wav", false);
    AudioPlayer ploop = new AudioPlayer("ploop.wav", false);
    AudioPlayer aww = new AudioPlayer("aww.wav", false);
    AudioPlayer shout = new AudioPlayer("shout.wav", false);
    AudioPlayer woop = new AudioPlayer("woop.wav", false);
    int combo = 0;
    int score = 0;
    int interval = DEFAULT_INTERVAL;
    long previousTime = 0;
    DoubleBufferRenderer backgroundBuffer;
    DoubleBufferRenderer mainPanelBuffer;
    DoubleBufferRenderer nextPieceBuffer;
    DoubleBufferRenderer nextListBuffer;
    boolean playingState = false;
    byte currentPiece = 0;
    int fun = 0;
    byte[][] playingCells = new byte[DIMENSION_HEIGHT][DIMENSION_WIDTH];
    byte[] pieceTracker = new byte[2];
    LinkedList<Byte> nextPiecesList = new LinkedList<>();
    
    // <editor-fold defaultstate="collapsed" desc="Pieces design">
    final int [][] PIECES = {
        //Line piece
        {
            0, 1,
            1, 1,
            2, 1,
            3, 1,
            26, 150, 65 //Green
        },
        //Square piece
        {
            1, 1,
            1, 2,
            2, 1,
            2, 2,
            150, 170, 0 //Yellow
        },
        //L piece
        {
            0, 1,
            1, 1,
            2, 1,
            2, 2,
            69, 177, 180 //Sky blue
        },
        //T piece
        {
            0, 1,
            1, 1,
            1, 2,
            2, 1,
            60, 60, 136 //Blue
        },
        //Reversed L piece
        {
            0, 1,
            0, 2,
            1, 1,
            2, 1,
            215, 48, 39 //Red
        },
        //S piece
        {
            0, 1,
            1, 1,
            1, 2,
            2, 2,
            160, 60, 160, //Purple
        },
        //Z piece
        {
            0, 2,
            1, 2,
            1, 1,
            2, 1,
            70, 70, 70, //Gray
        },
    }; //</editor-fold>
    
    private void populateNextList() {
        byte lastPiece = -1;
        if (!nextPiecesList.isEmpty()) lastPiece = nextPiecesList.getLast();
        byte pieceToAdd;
        while(nextPiecesList.size() < 5) {
            if ((pieceToAdd = (byte) (Math.random() * (NUMBER_OF_PIECES - .01)))
                    != lastPiece) {
                lastPiece = pieceToAdd;
                nextPiecesList.add(pieceToAdd);
            }
        }
    }
    private void spawn() {
        currentPiece = nextPiecesList.getFirst();
        nextPiecesList.removeFirst();
        populateNextList();
        pieceTracker[0] = 0;
        pieceTracker[1] = 3;
        if (!putPieceOnArray(playingCells, currentPiece, (byte) 1, 3, 0)){
            playingState = false;
            if (musicCheckBox.isSelected()) theme.stop();
            if (sfxCheckBox.isSelected()) aww.playFromBeginning();
            JOptionPane.showMessageDialog(this, "OH NOSE YOU LOST D:",
                    "TERRY THE TETRIS",
                    JOptionPane.OK_OPTION);
            newGame();
            playingState = true;
            if (musicCheckBox.isSelected()) theme.playFromBeginning();
        } else {
            int randomNumber = (int) (Math.random() * 5);
            for (int i = 0; i < randomNumber; i++)
                move(DIRECTION_ROTATE);
        }
    }
    ActionListener timerAction = (ActionEvent ae) -> {
        if (playingState) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - previousTime > interval) {
                previousTime = currentTime;
                boolean pleb = move(DIRECTION_DOWN);
                if (!pleb) {
                    solidify();
                    spawn();
                }
                paintPanel();
                paintNextPanel();
                paintNextList();
            }
        }
    };
    private void solidify() {
        for (int i = 0; i < playingCells.length; i++) {
            for (int j = 0; j < playingCells[0].length; j++) {
                if (playingCells[i][j] == 1) {
                    playingCells[i][j] = (byte) (2 + currentPiece);
                }
            }
        }
        if (sfxCheckBox.isSelected()) pugh.playFromBeginning();
        int lineScored = checkScore();
        if (sfxCheckBox.isSelected() && lineScored >= 4){
            shout.playFromBeginning();
        }
        score += lineScored * 100 + combo * 10;
        interval = Math.max(MIN_INTERVAL, interval - lineScored);
        if (lineScored > 0) {
            combo += lineScored;
        } else {
            combo = 0;
        }
        this.setTitle(TITLE + "SCORE: " + score + " - COMBO!! " + combo);
    }
    public Project1() {
        initComponents();
        resizeEverything();
        populateNextList();
        spawn();
        playingState = true;
        Timer time = new Timer(1, timerAction);
        time.start();
        theme.play();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        nextPiece = new javax.swing.JPanel();
        nextList = new javax.swing.JPanel();
        theMenuBar = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuNewGame = new javax.swing.JMenuItem();
        mnuPause = new javax.swing.JMenuItem();
        mnuExit = new javax.swing.JMenuItem();
        mnuSetting = new javax.swing.JMenu();
        musicCheckBox = new javax.swing.JCheckBoxMenuItem();
        sfxCheckBox = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Project 1, cle25 - TERRY THE TETRIS - SCORE: 0 - COMBO!! 0");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanel.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 213, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
        );

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 26, -1, -1));

        nextPiece.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout nextPieceLayout = new javax.swing.GroupLayout(nextPiece);
        nextPiece.setLayout(nextPieceLayout);
        nextPieceLayout.setHorizontalGroup(
            nextPieceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        nextPieceLayout.setVerticalGroup(
            nextPieceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(nextPiece, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, -1, -1));

        nextList.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout nextListLayout = new javax.swing.GroupLayout(nextList);
        nextList.setLayout(nextListLayout);
        nextListLayout.setHorizontalGroup(
            nextListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        nextListLayout.setVerticalGroup(
            nextListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );

        getContentPane().add(nextList, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, -1, 220));

        mnuFile.setText("File");

        mnuNewGame.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mnuNewGame.setText("New game");
        mnuNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNewGameActionPerformed(evt);
            }
        });
        mnuFile.add(mnuNewGame);

        mnuPause.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        mnuPause.setText("Pause");
        mnuPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPauseActionPerformed(evt);
            }
        });
        mnuFile.add(mnuPause);

        mnuExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        mnuExit.setText("Exit");
        mnuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExitActionPerformed(evt);
            }
        });
        mnuFile.add(mnuExit);

        theMenuBar.add(mnuFile);

        mnuSetting.setText("Settings");

        musicCheckBox.setSelected(true);
        musicCheckBox.setText("Music");
        musicCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                musicCheckBoxActionPerformed(evt);
            }
        });
        mnuSetting.add(musicCheckBox);

        sfxCheckBox.setSelected(true);
        sfxCheckBox.setText("SFX");
        mnuSetting.add(sfxCheckBox);

        theMenuBar.add(mnuSetting);

        setJMenuBar(theMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void paintAPanel(byte[][] theArray, DoubleBufferRenderer theBuffer,
            int width, int height, int offsetX, int offsetY,
            int locOffsetX, int locOffsetY, boolean[][] ghost) {
        int size = height / (theArray.length - offsetY);
        int locX, locY;
        int theColor[] = new int[3];
        boolean drawOrNah = false;
        theBuffer.bufferedGraphics.setColor(Color.BLACK);
        theBuffer.bufferedGraphics.fillRect(0, 0,
                width, height);
        for (int i = 0; i < theArray.length; i++) {
            for (int j = 0; j < theArray[0].length; j++) {
                if (theArray[i][j] == 0) {
                    drawOrNah = false;
                } else if (theArray[i][j] == 1) {
                    drawOrNah = true;
                    for (int m = 0; m < theColor.length; m++) {
                        theColor[m] = PIECES[currentPiece][8 + m];
                    }
                } else if (theArray[i][j] > 1) {
                    drawOrNah = true;
                    for (int m = 0; m < theColor.length; m++) {
                        theColor[m] = PIECES[theArray[i][j] - 2][8 + m];
                    }
                }
                locX = (j - offsetX) * width / (theArray[0].length - offsetX)
                        + locOffsetX;
                locY = (i - offsetY) * height / (theArray.length - offsetY)
                        + locOffsetY;
                if (drawOrNah) {
                    for (int k = 0; k < size / 2; k++){
                        theBuffer.bufferedGraphics.setColor(gradient(k, size / 2, .8,
                                intArrayToColor(theColor), Color.WHITE));
                        theBuffer.bufferedGraphics.fillRect(locX + k, locY + k,
                                size - k * 2, size - k * 2);
                    }
                }
                if (ghost != null) {
                    if (ghost[i][j] && theArray[i][j] != 1) {
                        theBuffer.bufferedGraphics.drawRect(locX, locY,
                                size, size);
                        theBuffer.bufferedGraphics.drawRect(locX + 1, locY + 1,
                                size - 2, size - 2);
                    }
                }
            }
        }
        theBuffer.render();
    }
    private void paintPanel() {
        boolean[][] ghost = new boolean[DIMENSION_HEIGHT][DIMENSION_WIDTH];
        for (int i = 0; i < ghost.length; i++) {
            for (int j = 0; j < ghost[0].length; j++) {
                if (playingCells[i][j] == 1) {
                    ghost[i][j] = true;
                }
            }
        }
        boolean colide = false;
        while (!colide) {
            for (int i = 0; i < ghost.length; i++) {
                for (int j = 0; j < ghost[0].length; j++) {
                    if (ghost[i][j] &&
                            (i + 1 >= ghost.length ||
                            playingCells[i + 1][j] > 1)) {
                        colide = true;
                        break;
                    }
                }
                if (colide) break;
            }
            if (!colide) {
                for (int i = ghost.length - 1; i > 0; i--) {
                    for (int j = 0; j < ghost[0].length; j++) {
                        ghost[i][j] = ghost[i - 1][j];
                    }
                }
                for (int j = 0; j < ghost[0].length; j++) {
                    ghost[0][j] = false;
                }
            }
        }
        paintAPanel(playingCells, mainPanelBuffer,
                mainPanel.getWidth(), mainPanel.getHeight(),
                0, 2, 0, 0, ghost);
    }
    private void paintNextPanel() {
        byte[][] nextPieceArray = new byte[4][4];
        byte theNextPiece = nextPiecesList.getFirst();
        int offsetX = 0;
        int offsetY = 0;
        if (theNextPiece != LINE_PIECE && theNextPiece != SQUARE_PIECE) {
            offsetY = nextPiece.getHeight() / 8;
        } else if (theNextPiece == LINE_PIECE) {
            offsetX = nextPiece.getWidth() / 8;
        }
        putPieceOnArray(nextPieceArray, theNextPiece,
                (byte) (theNextPiece + 2), 0, 0);
        paintAPanel(nextPieceArray, nextPieceBuffer,
                nextPiece.getWidth(), nextPiece.getHeight(),
                0, 0, offsetX, offsetY, null);
    }
    private void paintNextList() {
        byte[][] theArray = new byte[24][7];
        for (int i = 1; i < nextPiecesList.size(); i++) {
            byte thePiece = nextPiecesList.get(i);
            putPieceOnArray(theArray, thePiece,
                    (byte) (thePiece + 2), 1, (i - 1) * 5 + 2);
        }
        paintAPanel(theArray, nextListBuffer,
                nextList.getWidth(), nextList.getHeight(),
                0, 0, nextList.getWidth() / 13, nextList.getHeight() / 48, null);
    }
    private boolean putPieceOnArray(byte[][] array, byte piece, byte pieceValue,
            int offsetX, int offsetY) {
        boolean notOverlap = true;
        int locX, locY;
        for (int i = 0; i < 7; i += 2) {
            locX = offsetX + PIECES[piece][i + 1];
            locY = offsetY + PIECES[piece][i];
            if (array[locY][locX] != 0) notOverlap = false;
            array[locY][locX] = (byte) (pieceValue);
        }
        return notOverlap;
    }
    private int checkScore() {
        int result = 0;
        boolean scoreThatLine;
        for (int i = playingCells.length - 1; i >= 0; i--) {
            scoreThatLine = true;
            for (int j = 0; j < playingCells[0].length; j++) {
                if (playingCells[i][j] < 2) {
                    scoreThatLine = false;
                    break;
                }
            }
            if (scoreThatLine) {
                for (int k = i; k > 0; k--) {
                    for (int j = 0; j < playingCells[0].length; j++) {
                        if (playingCells[k-1][j] != 1) {
                            playingCells[k][j] = playingCells[k-1][j];
                        }
                    }
                }
                for (int j = 0; j < playingCells[0].length; j++) {
                    playingCells[0][j] = 0;
                }
                paintAPanel(playingCells, mainPanelBuffer,
                        mainPanel.getWidth(), mainPanel.getHeight(),
                        0, 2, 0, 0, null);
                if (sfxCheckBox.isSelected()) ploop.playFromBeginning();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Project1.class.getName()).log(Level.SEVERE, null, ex);
                }
                result++;
                i++;
            }
        }
        return result;
    }
    private boolean move(int direction) {
        switch (direction) {
            case DIRECTION_DOWN:
                for (int i = 0; i < playingCells.length; i++) {
                    for (int j = 0; j < playingCells[0].length; j++) {
                        if (playingCells[i][j] == 1 &
                                (i + 1 >= playingCells.length
                                || playingCells[i + 1][j] > 1))
                            return false;
                    }
                }
                pieceTracker[0] ++;
                for (int i = playingCells.length - 1; i > 0; i--) {
                    for (int j = 0; j < playingCells[0].length; j++) {
                        if (playingCells[i - 1][j] == 1) {
                            playingCells[i - 1][j] = 0;
                            playingCells[i][j] = 1;
                        }
                    }
                }
                return true;
                
            case DIRECTION_RIGHT:
                for (int i = 0; i < playingCells.length; i++) {
                    for (int j = 0; j < playingCells[0].length; j++) {
                        if (playingCells[i][j] == 1 &
                                (j + 1 >= playingCells[0].length
                                || playingCells[i][j + 1] > 1))
                            return false;
                    }
                }
                pieceTracker[1] ++;
                for (int j = playingCells[0].length - 1; j > 0; j--) {
                    for (int i = 0; i < playingCells.length; i++) {
                        if (playingCells[i][j - 1] == 1) {
                            playingCells[i][j - 1] = 0;
                            playingCells[i][j] = 1;
                        }
                    }
                }
                return true;
                
            case DIRECTION_LEFT:
                for (int i = 0; i < playingCells.length; i++) {
                    for (int j = 0; j < playingCells[0].length; j++) {
                        if (playingCells[i][j] == 1 &
                                (j - 1 < 0
                                || playingCells[i][j - 1] > 1))
                            return false;
                    }
                }
                pieceTracker[1] --;
                for (int j = 0; j < playingCells[0].length - 1; j++) {
                    for (int i = 0; i < playingCells.length; i++) {
                        if (playingCells[i][j + 1] == 1) {
                            playingCells[i][j + 1] = 0;
                            playingCells[i][j] = 1;
                        }
                    }
                }
                return true;
                
            case DIRECTION_ROTATE:
                if (currentPiece != SQUARE_PIECE) {
                    boolean[][] bufferArray;
                    if (currentPiece == LINE_PIECE){
                        bufferArray = new boolean[4][4];
                    } else {
                        bufferArray = new boolean[3][3];
                    }
                    for (int i = 0; i < bufferArray.length; i++) {
                        for (int j = 0; j < bufferArray[0].length; j++) {
                            int locX = pieceTracker[1] + j;
                            int locY = pieceTracker[0] + i;
                            if (locY < playingCells.length &&
                                    locX < playingCells[0].length &&
                                    locX >= 0 &&
                                    locY >= 0 &&
                                    playingCells[locY][locX] == 1) {
                                bufferArray[i][j] = true;
                            }
                        }
                    }
                    boolean[][] temp = new boolean
                            [bufferArray.length]
                            [bufferArray[0].length];
                    
                    for (int i = 0; i < temp.length; i++) {
                        for (int j = 0; j < temp[0].length; j++) {
                            temp[j][temp.length - i - 1] =
                                    bufferArray[i][j];
                        }
                    }
                    for (int i = 0; i < temp.length; i++) {
                        for (int j = 0; j < temp[0].length; j++) {
                            if (temp[i][j]) {
                                int locX = pieceTracker[1] + j;
                                int locY = pieceTracker[0] + i;
                                if (locX >= playingCells[0].length ||
                                        locX < 0 ||
                                        locY >= playingCells.length ||
                                        locY < 0 ||
                                        playingCells[locY][locX] > 1
                                        ){
                                    return false;
                                }
                            }
                        }
                    }
                    for (int i = 0; i < temp.length; i++) {
                        for (int j = 0; j < temp[0].length; j++) {
                            int locX = pieceTracker[1] + j;
                            int locY = pieceTracker[0] + i;
                            if (locX >= 0 && locX < playingCells[0].length
                                    && locY >= 0 && locY < playingCells.length) {
                                if (playingCells[locY][locX] < 2) {
                                    playingCells[locY][locX] =
                                            temp[i][j] ? (byte) 1 : (byte) 0;
                                }
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }
    private Color intArrayToColor(int[] color) {
        return new Color(
                color[0],
                color[1],
                color[2]
        );
    }
    private Color gradient(int current, int total, double exp,
            Color color1, Color color2) {
        int[] color1Int = new int[3];
        int[] color2Int = new int[3];
        int color1Value = color1.getRGB();
        int color2Value = color2.getRGB();
        for (int i = 0; i < color1Int.length; i++) {
            color1Int[color1Int.length - i - 1] = color1Value >>> (i * 8) & 0xFF;
            color2Int[color2Int.length - i - 1] = color2Value >>> (i * 8) & 0xFF;
        }
        return gradient(current, total, exp, color1Int, color2Int);
    }
    private Color gradient(int current, int total, double exp,
            int[] color1, int[] color2) {
        int[] theColor = new int[3];
        int newY = (int)(Math.pow((double) current / (double) total, exp)
                * total);
        for (int i = 0; i < theColor.length; i++) {
            theColor[i] = color1[i] +
                    (color2[i] - color1[i])
                    * newY / total;
        }
        return intArrayToColor(theColor);
    }
    private void mnuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnuExitActionPerformed

    private void mnuNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNewGameActionPerformed
        newGame();
    }//GEN-LAST:event_mnuNewGameActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                previousTime = System.currentTimeMillis();
                if (move(DIRECTION_DOWN)){
                    if (sfxCheckBox.isSelected())
                        meep.playFromBeginning();
                } else {
                    if (sfxCheckBox.isSelected())
                        boop.playFromBeginning();
                }
                break;
            case KeyEvent.VK_LEFT:
                if (move(DIRECTION_LEFT)) {
                    if (sfxCheckBox.isSelected())
                        meep.playFromBeginning();
                } else {
                    if (sfxCheckBox.isSelected())
                        boop.playFromBeginning();
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (move(DIRECTION_RIGHT)) {
                    if (sfxCheckBox.isSelected())
                        meep.playFromBeginning();
                } else {
                    if (sfxCheckBox.isSelected())
                        boop.playFromBeginning();
                }
                break;
            case KeyEvent.VK_UP:
                move(DIRECTION_ROTATE);
                if (sfxCheckBox.isSelected()) {
                    meep.playFromBeginning();
                }
                break;
            case KeyEvent.VK_SPACE:
                if (sfxCheckBox.isSelected()) {
                    woop.playFromBeginning();
                }
                previousTime = System.currentTimeMillis();
                boolean tryMove;
                while(true){
                    tryMove = move(DIRECTION_DOWN);
                    if (!tryMove){
                        solidify();
                        spawn();
                        break;
                    }
                    paintPanel();
                    try {
                        Thread.sleep(4);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Project1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
        }
        paintPanel();
        paintNextPanel();
        paintNextList();
    }//GEN-LAST:event_formKeyPressed

    private void musicCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_musicCheckBoxActionPerformed
        if (musicCheckBox.isSelected()) {
            theme.play();
        } else {
            theme.stop();
        }
    }//GEN-LAST:event_musicCheckBoxActionPerformed

    private void mnuPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPauseActionPerformed
        playingState = !playingState;
        if (musicCheckBox.isSelected()) {
            if (playingState) theme.play();
            else theme.stop();
        }
    }//GEN-LAST:event_mnuPauseActionPerformed
    private void newGame() {
        playingCells = new byte[DIMENSION_HEIGHT][DIMENSION_WIDTH];
        while (!nextPiecesList.isEmpty()) nextPiecesList.removeFirst();
        score = 0;
        combo = 0;
        interval = DEFAULT_INTERVAL;
        populateNextList();
        spawn();
        paintPanel();
        paintNextPanel();
        paintNextList();
        this.setTitle(TITLE + "SCORE: " + score + " - COMBO!! " + combo);
        playingState = true;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawBackground();
        paintPanel();
        paintNextPanel();
        paintNextList();
    }
    @Override
    public void validate() {
        super.validate();
        resizeEverything();
    }
    private void resizeEverything() {
        int height = this.getContentPane().getHeight() - 26 * 2;
        int width = height / 2;
        int left = this.getContentPane().getWidth() / 2
                - width / 2;
        mainPanel.setSize(width, height);
        mainPanel.setLocation(left, 26);
        nextPiece.setSize(width / 5 * 2, width / 5 * 2);
        nextPiece.setLocation(mainPanel.getX() + mainPanel.getWidth() + 30, 40);
        nextList.setSize(width / 5 * 2, width / 5 * 6);
        nextList.setLocation(nextPiece.getX(),
                nextPiece.getY() + nextPiece.getHeight() + 30);
        backgroundBuffer = new DoubleBufferRenderer(this.getContentPane().getGraphics()
                , this.getContentPane().getWidth()
                , this.getContentPane().getHeight(), this);
        mainPanelBuffer = new DoubleBufferRenderer(mainPanel.getGraphics()
                , mainPanel.getWidth(), mainPanel.getHeight(), this);
        nextPieceBuffer = new DoubleBufferRenderer(nextPiece.getGraphics()
                , nextPiece.getWidth(), nextPiece.getHeight(), this);
        nextListBuffer = new DoubleBufferRenderer(nextList.getGraphics()
                , nextList.getWidth(), nextList.getHeight(), this);
    }
    private void drawBackground() {
        if (backgroundBuffer.bufferedGraphics != null) {
            for (int i = 0; i <= this.getContentPane().getHeight(); i++) {
                backgroundBuffer.bufferedGraphics.setColor(gradient(i,
                        this.getContentPane().getHeight(), 1,
                        intArrayToColor(COLOR_1),
                        intArrayToColor(COLOR_2)));
                backgroundBuffer.bufferedGraphics.drawLine(0, i, this.getContentPane().getWidth(), i);
            }
            backgroundBuffer.render();
        }
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
        */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Project1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Project1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Project1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Project1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
//</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Project1 tetris = new Project1();
                tetris.setLocationRelativeTo(null);
                tetris.show();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuItem mnuExit;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenuItem mnuNewGame;
    private javax.swing.JMenuItem mnuPause;
    private javax.swing.JMenu mnuSetting;
    private javax.swing.JCheckBoxMenuItem musicCheckBox;
    private javax.swing.JPanel nextList;
    private javax.swing.JPanel nextPiece;
    private javax.swing.JCheckBoxMenuItem sfxCheckBox;
    private javax.swing.JMenuBar theMenuBar;
    // End of variables declaration//GEN-END:variables
}
