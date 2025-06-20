import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GameBoard extends Canvas implements Runnable, MouseMotionListener, MouseListener, KeyListener {
   int mouseX;
   int mouseY;
   AI a;
   GameCube c;
   int currPlayer;
   int width;
   int height;
   int scale;
   int xShift;
   int yShift;
   int mouseButton;
   boolean mousePressed;
   int cX;
   int cY;
   int uX;
   int uY;
   int aiWins;
   int playerWins;

   public GameBoard() {
      this.setBackground(Color.white);
      this.addMouseMotionListener(this);
      this.mouseX = this.mouseY = 0;
      (new Thread(this)).start();
      this.addKeyListener(this);
      this.addMouseListener(this);
      this.c = new GameCube();
      this.currPlayer = 0;
      this.a = new AI();
      this.width = this.height = 240;
      this.scale = 120;
      this.xShift = 130;
      this.yShift = 320;
      this.mouseButton = 0;
      this.aiWins = this.playerWins = 0;
      this.uX = this.uY = 75;
   }

   public void mousePressed(MouseEvent e) {
      this.mouseButton = e.getButton();
      this.mousePressed = true;
      int x = e.getX();
      int y = e.getY();
      if (e.getButton() != 2) {
         if (x < 200 && x > 50 && y < 200 && y > 50) {
            x -= 50;
            y -= 50;
            if (this.c.cube[0][y / 50][x / 50] == 0) {
               this.c.cube[0][y / 50][x / 50] = 1;
               if (this.c.checkWin() == 0) {
                  this.c = new GameCube(this.a.place(this.c));
               }

               this.repaint();
            }
         } else if (x < 400 && x > 250 && y < 200 && y > 50) {
            x -= 250;
            y -= 50;
            if (this.c.cube[1][y / 50][x / 50] == 0) {
               this.c.cube[1][y / 50][x / 50] = 1;
               if (this.c.checkWin() == 0) {
                  this.c = new GameCube(this.a.place(this.c));
               }

               this.repaint();
            }
         } else if (x < 600 && x > 450 && y < 200 && y > 50) {
            x -= 450;
            y -= 50;
            if (this.c.cube[2][y / 50][x / 50] == 0) {
               this.c.cube[2][y / 50][x / 50] = 1;
               if (this.c.checkWin() == 0) {
                  this.c = new GameCube(this.a.place(this.c));
               }

               this.repaint();
            }
         } else {
            this.cX = e.getX();
            this.cY = e.getY();
         }
      }
   }

   public void update(Graphics g) {
      System.out.println("update() has been called");
      Image offscreen = null;
      Dimension d = this.size();
      offscreen = this.createImage(d.width, d.height);
      Graphics offgc = offscreen.getGraphics();
      offgc.setColor(this.getBackground());
      offgc.fillRect(0, 0, d.width, d.height);
      offgc.setColor(this.getForeground());
      this.paint(offgc);
      g.drawImage(offscreen, 0, 0, this);
      g.setFont(new Font("TAHOMA", 1, 55));
      g.setColor(Color.black);
      if (this.c.checkWin() == 1) {
         ++this.playerWins;
         g.drawString("Player wins!", 650, 400);
         g.setFont(new Font("TAHOMA", 0, 25));
         g.drawString("Click anywhere to play again.", 650, 430);
         this.currPlayer = 0;
         this.c = new GameCube();
      } else if (this.c.checkWin() == 2) {
         ++this.aiWins;
         g.drawString("Computer wins!", 650, 400);
         g.setFont(new Font("TAHOMA", 0, 25));
         g.drawString("Click anywhere to play again.", 650, 430);
         this.currPlayer = 0;
         this.c = new GameCube();
      }

   }

   public void paint(Graphics g) {
      g.setFont(new Font("ARIAL", 0, 17));
      g.drawString("PLAYER", 700, 70);
      g.drawString("COMPUTER", 895, 70);
      g.drawLine(650, 90, 1040, 90);
      g.setColor(Color.blue);
      g.fillRect(650, 80, 390, 30);
      g.setColor(Color.red);
      if (this.playerWins + this.aiWins != 0) {
         g.fillRect(650, 80, (int)((double)this.playerWins / ((double)this.aiWins + (double)this.playerWins) * 390.0D), 30);
      } else {
         g.fillRect(650, 80, 195, 30);
      }

      g.setColor(Color.black);

      int xs;
      for(xs = 0; xs < this.playerWins; ++xs) {
         if (xs % 5 == 4) {
            g.drawLine(705, 120 + xs / 5 * 40, 770, 150 + xs / 5 * 40);
            g.drawLine(706, 120 + xs / 5 * 40, 771, 150 + xs / 5 * 40);
         } else {
            g.drawLine(715 + xs % 4 * 15, 120 + xs / 5 * 40, 715 + xs % 4 * 15, 150 + xs / 5 * 40);
            g.drawLine(715 + xs % 4 * 15 + 1, 120 + xs / 5 * 40, 715 + xs % 4 * 15 + 1, 150 + xs / 5 * 40);
         }
      }

      for(xs = 0; xs < this.aiWins; ++xs) {
         if (xs % 5 == 4) {
            g.drawLine(910, 120 + xs / 5 * 40, 975, 150 + xs / 5 * 40);
            g.drawLine(911, 120 + xs / 5 * 40, 976, 150 + xs / 5 * 40);
         } else {
            g.drawLine(920 + xs % 5 * 15, 120 + xs / 5 * 40, 920 + xs % 5 * 15, 150 + xs / 5 * 40);
            g.drawLine(920 + xs % 5 * 15 + 1, 120 + xs / 5 * 40, 920 + xs % 5 * 15 + 1, 150 + xs / 5 * 40);
         }
      }

      g.setFont(new Font("ARIAL", 0, 11));
      g.drawString("Back panel", 95, 45);
      g.drawRect(50, 50, 150, 150);
      g.drawRect(100, 50, 50, 150);
      g.drawRect(50, 100, 150, 50);
      g.drawString("Middle panel", 295, 45);
      g.drawRect(250, 50, 150, 150);
      g.drawRect(300, 50, 50, 150);
      g.drawRect(250, 100, 150, 50);
      g.drawString("Front panel", 495, 45);
      g.drawRect(450, 50, 150, 150);
      g.drawRect(500, 50, 50, 150);
      g.drawRect(450, 100, 150, 50);

      int ys;
      int y;
      for(xs = 0; xs < 3; ++xs) {
         for(ys = 0; ys < 3; ++ys) {
            for(y = 0; y < 3; ++y) {
               if (this.c.cube[xs][ys][y] != 0) {
                  g.setColor(this.c.cube[xs][ys][y] == 1 ? Color.red : Color.blue);
               }

               if (this.c.cube[xs][ys][y] == 1 || this.c.cube[xs][ys][y] == 2) {
                  g.fillOval(xs * 200 + 50 + y * 50, ys * 50 + 50, 50, 50);
               }
            }
         }
      }

      xs = this.uX - this.cX;
      if (xs > 75) {
         xs = 75;
      }

      if (xs < -75) {
         xs = -75;
      }

      ys = this.uY - this.cY;
      if (ys > 75) {
         ys = 75;
      }

      if (ys < -75) {
         ys = -75;
      }

      for(y = 0; y < 3; ++y) {
         g.setColor(Color.blue);
         g.drawRect(0 * (xs / 2) + this.xShift + y, 0 * (ys / 2) + this.yShift + y, this.scale * 3, this.scale * 3);
         g.drawRect(0 * (xs / 2) + this.xShift + this.scale + y, 0 * (ys / 2) + this.yShift + y, this.scale, this.scale * 3);
         g.drawRect(0 * (xs / 2) + this.xShift + y, 0 * (ys / 2) + this.yShift + this.scale + y, this.scale * 3, this.scale);
      }

      int x;
      for(y = 0; y < 3; ++y) {
         for(x = 0; x < 3; ++x) {
            if (this.c.cube[0][y][x] == 1) {
               this.drawO(g, this.scale, this.xShift + x * this.scale + 0 * xs / 2 + xs / 4, this.yShift + y * this.scale + 0 * ys / 2 + ys / 4);
            } else if (this.c.cube[0][y][x] == 2) {
               this.drawOBlue(g, this.scale, this.xShift + x * this.scale + 0 * xs / 2 + xs / 4, this.yShift + y * this.scale + 0 * ys / 2 + ys / 4);
            }
         }
      }

      for(y = 0; y < 3; ++y) {
         g.setColor(Color.blue);
         g.drawRect(1 * (xs / 2) + this.xShift + y, 1 * (ys / 2) + this.yShift + y, this.scale * 3, this.scale * 3);
         g.drawRect(1 * (xs / 2) + this.xShift + this.scale + y, 1 * (ys / 2) + this.yShift + y, this.scale, this.scale * 3);
         g.drawRect(1 * (xs / 2) + this.xShift + y, 1 * (ys / 2) + this.yShift + this.scale + y, this.scale * 3, this.scale);
      }

      for(y = 0; y < 3; ++y) {
         for(x = 0; x < 3; ++x) {
            if (this.c.cube[1][y][x] == 1) {
               this.drawO(g, this.scale, this.xShift + x * this.scale + 1 * xs / 2 + xs / 4, this.yShift + y * this.scale + 1 * ys / 2 + ys / 4);
            } else if (this.c.cube[1][y][x] == 2) {
               this.drawOBlue(g, this.scale, this.xShift + x * this.scale + 1 * xs / 2 + xs / 4, this.yShift + y * this.scale + 1 * ys / 2 + ys / 4);
            }
         }
      }

      for(y = 0; y < 3; ++y) {
         g.setColor(Color.blue);
         g.drawRect(2 * (xs / 2) + this.xShift + y, 2 * (ys / 2) + this.yShift + y, this.scale * 3, this.scale * 3);
         g.drawRect(2 * (xs / 2) + this.xShift + this.scale + y, 2 * (ys / 2) + this.yShift + y, this.scale, this.scale * 3);
         g.drawRect(2 * (xs / 2) + this.xShift + y, 2 * (ys / 2) + this.yShift + this.scale + y, this.scale * 3, this.scale);
      }

      for(y = 0; y < 3; ++y) {
         for(x = 0; x < 3; ++x) {
            if (this.c.cube[2][y][x] == 1) {
               this.drawO(g, this.scale, this.xShift + x * this.scale + 2 * xs / 2 + xs / 4, this.yShift + y * this.scale + 2 * ys / 2 + ys / 4);
            } else if (this.c.cube[2][y][x] == 2) {
               this.drawOBlue(g, this.scale, this.xShift + x * this.scale + 2 * xs / 2 + xs / 4, this.yShift + y * this.scale + 2 * ys / 2 + ys / 4);
            }
         }
      }

      for(y = 0; y < 3; ++y) {
         g.setColor(Color.blue);
         g.drawRect(3 * (xs / 2) + this.xShift + y, 3 * (ys / 2) + this.yShift + y, this.scale * 3, this.scale * 3);
         g.drawRect(3 * (xs / 2) + this.xShift + this.scale + y, 3 * (ys / 2) + this.yShift + y, this.scale, this.scale * 3);
         g.drawRect(3 * (xs / 2) + this.xShift + y, 3 * (ys / 2) + this.yShift + this.scale + y, this.scale * 3, this.scale);
      }

      for(y = 0; y < 16; ++y) {
         x = this.xShift + this.scale * (y % 4);
         y = this.yShift + this.scale * (y / 4);

         for(int t = 0; t < 3; ++t) {
            g.drawLine(x, y + t, x + 3 * xs / 2, y + 3 * ys / 2 + t);
         }
      }

   }

   public void drawO(Graphics g, int scale, int x, int y) {
      for(int i = 0; i <= 255; i += 15) {
         g.setColor(new Color(i, i / 20, 0));
         g.fillOval(x + i / 20, y + i / 20, scale - i / 20 - i / 5, scale - i / 20 - i / 5);
      }

   }

   public void drawOBlue(Graphics g, int scale, int x, int y) {
      for(int i = 0; i <= 255; i += 15) {
         g.setColor(new Color(0, i / 20, i));
         g.fillOval(x + i / 20, y + i / 20, scale - i / 20 - i / 5, scale - i / 20 - i / 5);
      }

   }

   public void drawX(Graphics g, int x, int y) {
      for(int i = 0; i < 15; ++i) {
         g.setColor(new Color(i, i * 12, i * 12));
         g.drawLine(x + 10 * this.scale / 80, y + 5 * this.scale / 80 + i, x + 70 * this.scale / 80, y + 65 * this.scale / 80 + i);
         g.drawLine(x + 70 * this.scale / 80, y + 5 * this.scale / 80 + i, x + 10 * this.scale / 80, y + 65 * this.scale / 80 + i);
      }

      int scale = 20;

      int i;
      for(i = 0; i <= 155; ++i) {
         g.setColor(new Color(i / 20, 0, i + 100));
         g.fillOval(x + i / 20, y + i / 20, scale * this.scale / 80 - i / 20 - i / 5, scale * this.scale / 80 - i / 20 - i / 5);
      }

      for(i = 0; i <= 155; ++i) {
         g.setColor(new Color(i / 20, 0, i + 100));
         g.fillOval(x + i / 20, y + this.scale - scale + i / 20 - scale, scale * this.scale / 80 - i / 20 - i / 5, scale * this.scale / 80 - i / 20 - i / 5);
      }

      for(i = 0; i <= 155; ++i) {
         g.setColor(new Color(i / 20, 0, i + 100));
         g.fillOval(x + this.scale - scale + i / 20 - scale, y + i / 20, scale * this.scale / 80 - i / 20 - i / 5, scale * this.scale / 80 - i / 20 - i / 5);
      }

      for(i = 0; i <= 155; ++i) {
         g.setColor(new Color(i / 20, 0, i + 100));
         g.fillOval(x + this.scale - scale + i / 20 - scale, y + this.scale - scale + i / 20 - scale, scale * this.scale / 80 - i / 20 - i / 5, scale * this.scale / 80 - i / 20 - i / 5);
      }

   }

   public void mouseDragged(MouseEvent e) {
      System.out.println("mouse dragged" + this.mouseButton + " " + this.cX + " " + this.cY + " " + this.uX + " " + this.uY);
      int x = e.getX();
      int y = e.getY();
      if (x <= 50 || x >= 600 || y >= 200 || y <= 50) {
         switch(this.mouseButton) {
         case 1:
            this.uX = e.getX();
            this.uY = e.getY();
            break;
         case 2:
            this.xShift = e.getX();
            this.yShift = e.getY();
         case 3:
         }

         this.repaint();
      }
   }

   public void mouseMoved(MouseEvent e) {
   }

   public void keyReleased(KeyEvent e) {
   }

   public void keyTyped(KeyEvent e) {
   }

   public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == 81) {
         if (this.c.cube[0][0][0] != 0) {
            return;
         }

         this.c.cube[0][0][0] = 1;
      } else if (e.getKeyCode() == 87) {
         if (this.c.cube[0][0][1] != 0) {
            return;
         }

         this.c.cube[0][0][1] = 1;
      } else if (e.getKeyCode() == 69) {
         if (this.c.cube[0][0][2] != 0) {
            return;
         }

         this.c.cube[0][0][2] = 1;
      } else if (e.getKeyCode() == 65) {
         if (this.c.cube[0][1][0] != 0) {
            return;
         }

         this.c.cube[0][1][0] = 1;
      } else if (e.getKeyCode() == 83) {
         if (this.c.cube[0][1][1] != 0) {
            return;
         }

         this.c.cube[0][1][1] = 1;
      } else if (e.getKeyCode() == 68) {
         if (this.c.cube[0][1][2] != 0) {
            return;
         }

         this.c.cube[0][1][2] = 1;
      } else if (e.getKeyCode() == 90) {
         if (this.c.cube[0][2][0] != 0) {
            return;
         }

         this.c.cube[0][2][0] = 1;
      } else if (e.getKeyCode() == 88) {
         if (this.c.cube[0][2][1] != 0) {
            return;
         }

         this.c.cube[0][2][1] = 1;
      } else if (e.getKeyCode() == 67) {
         if (this.c.cube[0][2][2] != 0) {
            return;
         }

         this.c.cube[0][2][2] = 1;
      } else if (e.getKeyCode() == 82) {
         if (this.c.cube[1][0][0] != 0) {
            return;
         }

         this.c.cube[1][0][0] = 1;
      } else if (e.getKeyCode() == 84) {
         if (this.c.cube[1][0][1] != 0) {
            return;
         }

         this.c.cube[1][0][1] = 1;
      } else if (e.getKeyCode() == 89) {
         if (this.c.cube[1][0][2] != 0) {
            return;
         }

         this.c.cube[1][0][2] = 1;
      } else if (e.getKeyCode() == 70) {
         if (this.c.cube[1][1][0] != 0) {
            return;
         }

         this.c.cube[1][1][0] = 1;
      } else if (e.getKeyCode() == 71) {
         if (this.c.cube[1][1][1] != 0) {
            return;
         }

         this.c.cube[1][1][1] = 1;
      } else if (e.getKeyCode() == 72) {
         if (this.c.cube[1][1][2] != 0) {
            return;
         }

         this.c.cube[1][1][2] = 1;
      } else if (e.getKeyCode() == 86) {
         if (this.c.cube[1][2][0] != 0) {
            return;
         }

         this.c.cube[1][2][0] = 1;
      } else if (e.getKeyCode() == 66) {
         if (this.c.cube[1][2][1] != 0) {
            return;
         }

         this.c.cube[1][2][1] = 1;
      } else if (e.getKeyCode() == 78) {
         if (this.c.cube[1][2][2] != 0) {
            return;
         }

         this.c.cube[1][2][2] = 1;
      } else if (e.getKeyCode() == 85) {
         if (this.c.cube[2][0][0] != 0) {
            return;
         }

         this.c.cube[2][0][0] = 1;
      } else if (e.getKeyCode() == 73) {
         if (this.c.cube[2][0][1] != 0) {
            return;
         }

         this.c.cube[2][0][1] = 1;
      } else if (e.getKeyCode() == 79) {
         if (this.c.cube[2][0][2] != 0) {
            return;
         }

         this.c.cube[2][0][2] = 1;
      } else if (e.getKeyCode() == 74) {
         if (this.c.cube[2][1][0] != 0) {
            return;
         }

         this.c.cube[2][1][0] = 1;
      } else if (e.getKeyCode() == 75) {
         if (this.c.cube[2][1][1] != 0) {
            return;
         }

         this.c.cube[2][1][1] = 1;
      } else if (e.getKeyCode() == 76) {
         if (this.c.cube[2][1][2] != 0) {
            return;
         }

         this.c.cube[2][1][2] = 1;
      } else if (e.getKeyCode() == 77) {
         if (this.c.cube[2][2][0] != 0) {
            return;
         }

         this.c.cube[2][2][0] = 1;
      } else if (e.getKeyCode() == 44) {
         if (this.c.cube[2][2][1] != 0) {
            return;
         }

         this.c.cube[2][2][1] = 1;
      } else if (e.getKeyCode() == 46) {
         if (this.c.cube[2][2][2] != 0) {
            return;
         }

         this.c.cube[2][2][2] = 1;
      }

      this.repaint();
      if (this.c.checkWin() == 0) {
         this.c = new GameCube(this.a.place(this.c));
      }

      this.repaint();
   }

   public void run() {
   }

   public void mouseReleased(MouseEvent e) {
      this.mouseButton = 0;
      this.mousePressed = false;
   }

   public void mouseClicked(MouseEvent e) {
   }

   public void mouseExited(MouseEvent e) {
   }

   public void mouseEntered(MouseEvent e) {
   }
}
