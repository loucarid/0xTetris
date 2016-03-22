package fr.tetris;

import fr.tetris.piece.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


public class Tetris extends JFrame {

    /**
     * JPanel_board
     */
    private Board board;

    /**
     * Jpanel_SideBar
     */
    private SideBar sideBar;

    /**
     * Current Piece
     */
    private Piece currentPiece;

    /**
     * Piece suivante
     */
    private Piece nextPiece;

    /**
     * GameSpeed
     */
    private int speed;

    /**
     * Fin du jeu
     */
    private boolean gameOver;

    /**
     * Pause
     */
    private boolean pause;

    /**
     * Score
     */
    private int score;

    /**
     *
     * Tetris_constructor
     *
     */
    public Tetris() {

        /**
         * Set_Tetris_Window
         */
        this.setTitle("Tetris");
        this.getContentPane().setBackground( Color.BLACK);
        this.setLayout(new BorderLayout());

        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        /**
         * Board : Jpanel to Jframe
         */
        this.board = new Board();
        this.add(this.board, BorderLayout.CENTER);

        /**
         * Sidebar : Jpanel to Jframe
         */
        this.sideBar = new SideBar(this);
        this.add(this.sideBar, BorderLayout.EAST);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    /**
                     * Rotate piece
                     */
                    case KeyEvent.VK_Z:
                    case KeyEvent.VK_UP:
                        if (!isPause()) {
                            currentPiece.rotate();
                            if (board.verifCollision(currentPiece)) {
                                currentPiece.rotate();
                                currentPiece.rotate();
                                currentPiece.rotate();
                            } else {
                                updatePrint();
                            }
                        }
                        break;

                    /**
                     * Move Left
                     */
                    case KeyEvent.VK_Q:
                    case KeyEvent.VK_LEFT:
                        if (!isPause()) {
                            currentPiece.moveLeft();
                            if (board.verifCollision(currentPiece)) {
                                currentPiece.moveRight();
                            } else {
                                updatePrint();
                            }
                        }
                        break;

                    /**
                     * Move Right
                     */
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        if (!isPause()) {
                            currentPiece.moveRight();
                            if (board.verifCollision(currentPiece)) {
                                currentPiece.moveLeft();
                            } else {
                                updatePrint();
                            }
                        }
                        break;

                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        run();
                        break;

                    case KeyEvent.VK_ENTER:
                        if (isGameOver()) {
                            initGame();
                        }
                        break;

                    case KeyEvent.VK_P:
                        setPause(!isPause());
                        break;

                }
            }
        });


        /**
         * Print_Tetris
         */
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        /**
         * Init_Game
         */

        this.initGame();
        this.loop();
    }

    /**
     * Launch_Game
     */
    private void run() {
        if (!isPause()) {
            this.currentPiece.moveDown();

            if (this.board.verifCollision(this.currentPiece)) {
                this.currentPiece.cancelMoveDown();
                this.changePiece();
            }

        }

        this.updatePrint();
    }

    /**
     * Random_Piece
     *
     * @return une pièce
     */
    private Piece randomPiece() {
        Random random = new Random();

        switch (random.nextInt(7)) {
            case 0:
                return new PieceI();
            case 1:
                return new PieceJ();
            case 2:
                return new PieceL();
            case 3:
                return new PieceO();
            case 4:
                return new PieceS();
            case 5:
                return new PieceT();
            case 6:
                return new PieceZ();
        }

        return null;
    }

    /**
     * Update_screen
     */
    private void updatePrint() {
        if (isGameOver() || isPause()) {
            this.board.printPause(isGameOver());
        } else {
            this.board.print(this.currentPiece);
        }
        this.board.repaint();
        this.sideBar.repaint();
    }

    /**
     * Game_loop
     */
    private void loop() {

        while (true) {
            run();
            if (!isPause()) {
                this.speed--;
            }
            try {
                Thread.sleep(this.speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Init_Game
     */
    private void initGame() {
        this.board.reset();
        this.speed = 800;
        this.currentPiece = randomPiece();
        this.nextPiece = randomPiece();
        this.setGameOver(false);
        this.updatePrint();
        this.score = 0;
    }

    /**
     * Change_piece
     */
    private void changePiece() {
        this.board.addPiece(this.currentPiece);
        switch (this.board.fullLines()) {
            case 1:
                this.score += 50;
                break;
            case 2:
                this.score += 100;
                break;
            case 3:
                this.score += 200;
                break;
            case 4:
                this.score += 400;
                break;
        }
        this.currentPiece = this.nextPiece;
        if (this.board.verifCollision(this.currentPiece)) {
            setGameOver(true);
        } else {
            this.nextPiece = this.randomPiece();
        }
    }

	/**
	 * End_Game
	 * @return
	 */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * End_Game
     * @param gameOver
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Pause ?
     */
    public boolean isPause() {
        return pause;
    }

    /** met en pause
     * 
     * @param pause
     */
    public void setPause(boolean pause) {
        this.pause = pause;
    }

	/**
	 * Print_score
	 * @return
	 */
    public int getScore() {
        return score;
    }

   /**
    * Next_piece
    * @return
    */
    public Piece getNextPiece() {
        return nextPiece;
    }
}
