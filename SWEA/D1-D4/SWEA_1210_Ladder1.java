import java.util.Scanner;
import java.io.FileInputStream;

class SWEA_1210_Ladder1 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int[][] Ladder = new int[100][100];

        for (int test_case = 1; test_case <= 10; test_case++) {
            int T = sc.nextInt();
            for (int i = 0; i < Ladder.length; i++) {
                for (int j = 0; j < Ladder.length; j++) {
                    Ladder[i][j] = sc.nextInt();
                }
            }
            int col=0;
						int row=98;
            for (int i = 0; i < Ladder.length; i++) {
                if (Ladder[99][i] == 2) {
                    col = i; break;
                }
            }
            while (row != 0) {
                if (col + 1 < Ladder.length && Ladder[row][col+1] == 1) {
                    while (col + 1 < Ladder.length && Ladder[row][col+1] != 0) {
                        col++;
                    }
                } else if (col - 1 >= 0 && Ladder[row][col-1] == 1) {
                    while (col - 1 >= 0 && Ladder[row][col-1] != 0) {
                        col--;
                    }
                }
                row--;
            }
            System.out.println("#" + test_case + " " + col);
        }
    }
}