import java.io.*;
import java.util.*;

public class Main {

    final static int MAX = 10;
    static int[][] map = new int[MAX][MAX];
    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        for (int i = 0; i < MAX; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < MAX; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] paperCnt = new int[MAX / 2];
        for (int i = 0; i < 5; i++)
            paperCnt[i] = MAX / 2;

        go(0, paperCnt, 0);
        System.out.println((res == Integer.MAX_VALUE) ? -1 : res);
        br.close();
    }

    static void go(int idx, int[] paperCnt, int cnt) {

        if (idx == MAX * MAX) {
            res = Math.min(res, cnt);
            return;
        }
        if (cnt >= res)
            return;

        int r = idx / MAX;
        int c = idx % MAX;
        if (map[r][c] == 0) {
            go(idx + 1, paperCnt, cnt);
        } else {
            for (int i = 5; i > 0; i--) {
                if (checkAttach(r, c, i) || paperCnt[i - 1] == 0)
                    continue;

                attachPaper(r, c, i, 0);
                paperCnt[i - 1]--;

                go(idx + 1, paperCnt, cnt + 1);

                attachPaper(r, c, i, 1);
                paperCnt[i - 1]++;
            }
        }

    }

    static void attachPaper(int r, int c, int length, int v) {
        for (int i = r; i < r + length; i++) {
            for (int j = c; j < c + length; j++) {
                map[i][j] = v;
            }
        }
    }

    static boolean rangeCheck(int r, int c) {
        return (r < 0 || c < 0 || r >= MAX || c >= MAX) ? true : false;
    }

    static boolean checkAttach(int r, int c, int length) {
        for (int i = r; i < r + length; i++) {
            for (int j = c; j < c + length; j++) {
                if (rangeCheck(i, j) || map[i][j] == 0)
                    return true;
            }
        }
        return false;
    }
}