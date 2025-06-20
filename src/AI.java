public class AI {
   int[][][] playerFreq = new int[3][3][3];

   public int[][][] place(GameCube cu) {
      int[][][] cubeArray = cu.getArrayCopy();
      new GameCube(cubeArray);

      int z;
      int count;
      //int z;
      for(z = 0; z < 3; ++z) {
         for(count = 0; count < 3; ++count) {
            for(z = 0; z < 3; ++z) {
               if (cubeArray[z][count][z] == 1) {
                  int var10002 = this.playerFreq[z][count][z]++;
               }
            }
         }
      }

      for(z = 0; z < 3; ++z) {
         for(count = 0; count < 3; ++count) {
            for(z = 0; z < 3; ++z) {
               System.out.print(this.playerFreq[z][count][z]);
            }

            System.out.println();
         }

         System.out.println("\n");
      }

      GameCube cube;
      for(z = 0; z < 3; ++z) {
         for(count = 0; count < 3; ++count) {
            for(z = 0; z < 3; ++z) {
               if (cubeArray[z][count][z] == 0) {
                  cubeArray[z][count][z] = 2;
                  cube = new GameCube(cubeArray);
                  if (cube.checkWin() == 2) {
                     return cubeArray;
                  }

                  cubeArray[z][count][z] = 0;
               }
            }
         }
      }

      for(z = 0; z < 3; ++z) {
         for(count = 0; count < 3; ++count) {
            for(z = 0; z < 3; ++z) {
               if (cubeArray[z][count][z] == 0) {
                  cubeArray[z][count][z] = 1;
                  cube = new GameCube(cubeArray);
                  if (cube.checkWin() == 1) {
                     cubeArray[z][count][z] = 2;
                     return cubeArray;
                  }

                  cubeArray[z][count][z] = 0;
               }
            }
         }
      }

      boolean placed = false;
      count = 0;

      int r;
      int c;
      while(!placed) {
         z = 0;
         r = -1;
         c = -1;
         int maxC = -1;

         for(int z1 = 0; z1 < 3; ++z1) {
            for(int r1 = 0; r1 < 3; ++r1) {
               for(int c1 = 0; c1 < 3; ++c1) {
                  if (this.playerFreq[z1][r1][c1] > z1) {
                     z1 = this.playerFreq[z1][r1][c1];
                     r1 = z1;
                     c1 = r1;
                     maxC = c1;
                  }
               }
            }
         }

         if (r >= 0 && cubeArray[r][c][maxC] == 0) {
            System.out.println("choose: " + r + " " + c + " " + maxC);
            cubeArray[r][c][maxC] = 2;
            return cubeArray;
         }

         if (r >= 0) {
            this.playerFreq[r][c][maxC] = 0;
         }

         ++count;
         if (count == 27) {
            placed = true;
            count = 0;
         }
      }

      if (cubeArray[1][1][1] == 0) {
         cubeArray[1][1][1] = 2;
         return cubeArray;
      } else if ((cubeArray[0][0][0] == 1 || cubeArray[0][0][2] == 1 || cubeArray[0][2][0] == 1 || cubeArray[0][2][2] == 1) && cubeArray[0][1][1] == 0) {
         cubeArray[0][1][1] = 2;
         return cubeArray;
      } else if ((cubeArray[1][0][0] == 1 || cubeArray[1][0][2] == 1 || cubeArray[1][2][0] == 1 || cubeArray[1][2][2] == 1) && cubeArray[1][1][1] == 0) {
         cubeArray[1][1][1] = 2;
         return cubeArray;
      } else if ((cubeArray[2][0][0] == 1 || cubeArray[2][0][2] == 1 || cubeArray[2][2][0] == 1 || cubeArray[2][2][2] == 1) && cubeArray[2][1][1] == 0) {
         cubeArray[2][1][1] = 2;
         return cubeArray;
      } else {
         for(z = 0; z < 3; ++z) {
            for(r = 0; r < 3; r += 2) {
               for(c = 0; c < 3; c += 2) {
                  if (cubeArray[z][r][c] == 0) {
                     cubeArray[z][r][c] = 2;
                     return cubeArray;
                  }
               }
            }
         }

         byte z1;
         byte r1;
         byte c1;
         for(z = 0; z < 3; ++z) {
            for(r = 0; r < 3; ++r) {
               for(c = 0; c < 3; ++c) {
                  if (cubeArray[z][r][c] == 0) {
                     for(z1 = 0; z < 3; ++z) {
                        for(r1 = 0; r < 3; ++r) {
                           for(c1 = 0; c < 3; ++c) {
                              if (cubeArray[z1][r1][c1] == 0) {
                                 cubeArray[z1][r1][c1] = 1;
                                 cubeArray[z][r][c] = 1;
                                 cube = new GameCube(cubeArray);
                                 if (cube.checkWin() == 1) {
                                    cubeArray[z1][r1][c1] = 2;
                                    cubeArray[z][r][c] = 0;
                                    return cubeArray;
                                 }

                                 cubeArray[z][r][c] = 0;
                                 cubeArray[z1][r1][c1] = 0;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (cubeArray[1][1][1] == 0) {
            cubeArray[1][1][1] = 2;
            return cubeArray;
         } else {
            for(z = 0; z < 3; ++z) {
               for(r = 0; r < 3; ++r) {
                  for(c = 0; c < 3; ++c) {
                     if (cubeArray[z][r][c] == 0) {
                        for(z1 = 0; z < 3; ++z) {
                           for(r1 = 0; r < 3; ++r) {
                              for(c1 = 0; c < 3; ++c) {
                                 if (cubeArray[z1][r1][c1] == 0) {
                                    cubeArray[z1][r1][c1] = 2;
                                    cubeArray[z][r][c] = 2;
                                    cube = new GameCube(cubeArray);
                                    if (cube.checkWin() == 2) {
                                       cubeArray[z][r][c] = 0;
                                       return cubeArray;
                                    }

                                    cubeArray[z][r][c] = 0;
                                    cubeArray[z1][r1][c1] = 0;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }

            placed = false;

            while(!placed) {
               z = (int)(Math.random() * 3.0D);
               r = (int)(Math.random() * 3.0D);
               c = (int)(Math.random() * 3.0D);
               if (cubeArray[z][r][c] == 0) {
                  cubeArray[z][r][c] = 2;
                  return cubeArray;
               }
            }

            return new int[3][3][3];
         }
      }
   }
}