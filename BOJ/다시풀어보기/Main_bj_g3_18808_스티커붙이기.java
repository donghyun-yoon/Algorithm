import java.io.*;
import java.util.*;

public class Main_bj_g3_18808_스티커붙이기 {

    static int[][] notebook;
    static int N, M, K, res;

    // rotation 90
    static int[][] rotation(int[][] arr) {
        int r = arr.length;
        int c = arr[0].length;
        int[][] newArr = new int[c][r];

        int nc = r - 1;
        int nr = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                newArr[nr++][nc] = arr[i][j];
            }
            nr = 0;
            nc--;
        }

        return newArr;
    }

    static void attachSticker(int[][] sticker, int r, int c) {
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[0].length; j++) {
                if (sticker[i][j] == 1) {
                    notebook[r + i][c + j] = sticker[i][j];
                    res++;
                }
            }
        }
    }

    static boolean checkAttach(int[][] sticker, int r, int c) {
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[0].length; j++) {
                if (sticker[i][j] == 1 && notebook[r + i][c + j] == 1)
                    return false;
            }
        }
        return true;
    }

    static boolean attach(int[][] sticker) {
        for (int i = 0; i < N - sticker.length + 1; i++) {
            for (int j = 0; j < M - sticker[0].length + 1; j++) {
                if (checkAttach(sticker, i, j)) {
                    attachSticker(sticker, i, j);
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        K = Integer.parseInt(st.nextToken()); // 스티커의 개수

        notebook = new int[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[row][col];

            for (int r = 0; r < row; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < col; c++) {
                    sticker[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            for (int k = 0; k < 4; k++) {
                if (attach(sticker))
                    break;
                sticker = rotation(sticker);
            }
        }

        System.out.println(res);
        br.close();
    }
}