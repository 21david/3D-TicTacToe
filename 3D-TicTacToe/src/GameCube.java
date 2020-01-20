public class GameCube
{
    int[][][] cube;
    
    public GameCube() {
        this.cube = new int[3][3][3];
    }
    
    public GameCube(final int[][][] c) {
        this.cube = c;
    }
    
    public int checkWin() {
        for (int i1 = 0; i1 < 3; ++i1) {
            for (int i2 = 0; i2 < 3; ++i2) {
                int p = this.cube[0][i1][i2];
                if (p != 0 && this.cube[1][i1][i2] == p && this.cube[2][i1][i2] == p) {
                    return p;
                }
                p = this.cube[i1][0][i2];
                if (p != 0 && this.cube[i1][1][i2] == p && this.cube[i1][2][i2] == p) {
                    return p;
                }
                p = this.cube[i1][i2][0];
                if (p != 0 && this.cube[i1][i2][1] == p && this.cube[i1][i2][2] == p) {
                    return p;
                }
            }
        }
        for (int j = 0; j < 3; ++j) {
            int p = this.cube[0][0][j];
            if (p != 0 && this.cube[1][1][j] == p && this.cube[2][2][j] == p) {
                return p;
            }
            p = this.cube[0][2][j];
            if (p != 0 && this.cube[1][1][j] == p && this.cube[2][0][j] == p) {
                return p;
            }
            p = this.cube[0][j][0];
            if (p != 0 && this.cube[1][j][1] == p && this.cube[2][j][2] == p) {
                return p;
            }
            p = this.cube[0][j][2];
            if (p != 0 && this.cube[1][j][1] == p && this.cube[2][j][0] == p) {
                return p;
            }
            p = this.cube[j][0][0];
            if (p != 0 && this.cube[j][1][1] == p && this.cube[j][2][2] == p) {
                return p;
            }
            p = this.cube[j][0][2];
            if (p != 0 && this.cube[j][1][1] == p && this.cube[j][2][0] == p) {
                return p;
            }
        }
        int p = this.cube[0][0][0];
        if (p != 0 && this.cube[1][1][1] == p && this.cube[2][2][2] == p) {
            return p;
        }
        p = this.cube[0][0][2];
        if (p != 0 && this.cube[1][1][1] == p && this.cube[2][2][0] == p) {
            return p;
        }
        p = this.cube[0][2][0];
        if (p != 0 && this.cube[1][1][1] == p && this.cube[2][0][2] == p) {
            return p;
        }
        p = this.cube[2][0][0];
        if (p != 0 && this.cube[1][1][1] == p && this.cube[0][2][2] == p) {
            return p;
        }
        return 0;
    }
    
    public int[][][] getArrayCopy() {
        final int[][][] copy = new int[3][3][3];
        for (int z = 0; z < 3; ++z) {
            for (int y = 0; y < 3; ++y) {
                for (int x = 0; x < 3; ++x) {
                    copy[z][y][x] = this.cube[z][y][x];
                }
            }
        }
        return copy;
    }
}