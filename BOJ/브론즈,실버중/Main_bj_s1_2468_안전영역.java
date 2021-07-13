import java.io.*;
import java.util.*;

public class Main {

    static boolean[][] visited;
    static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static int N, map[][], res, nr, nc;

    static boolean rangeCheck(int r, int c) {
        return (r < 0 || r >= N || c < 0 || c >= N) ? true : false;
    }

    static void dfs(int r, int c) {
        visited[r][c] = true;

        for (int d = 0; d < 4; d++) {
            nr = r + dir[d][0];
            nc = c + dir[d][1];

            if (rangeCheck(nr, nc) || visited[nr][nc])
                continue;

            dfs(nr, nc);
        }

        return;
    }

    static int init(int h) {
        int cnt = 0;
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j] = (map[i][j] < h) ? true : false;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j])
                    continue;

                dfs(i, j);
                cnt++;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        int val = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                val = Integer.parseInt(st.nextToken());
                map[i][j] = val;
            }
        }

        for (int i = 1; i <= 100; i++) {
            val = init(i);
            if (val == 0)
                break;
            res = Math.max(res, val);
        }

        System.out.println(res);
        br.close();
    }
}