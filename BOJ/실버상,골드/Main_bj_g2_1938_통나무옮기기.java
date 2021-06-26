import java.io.*;
import java.util.*;

public class Main {

    static Queue<int[]> queue = new ArrayDeque<>();
    static char[][] map;
    static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
    static int[] EEE = new int[3];
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        boolean bbb = true;
        boolean eee = true;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] <= '1')
                    continue;

                if (map[i][j] == 'B' && bbb) {
                    if (j + 1 < N && map[i][j + 1] == 'B') { // 가로 방향
                        queue.offer(new int[] { i, j + 1, 0, 0 }); // 행, 열, 가로방향
                    } else if (i + 1 < N && map[i + 1][j] == 'B') { // 세로방향
                        queue.offer(new int[] { i + 1, j, 1, 0 }); // 행, 열, 세로방향
                    }
                    bbb = false;
                }

                if (map[i][j] == 'E' && eee) {
                    if (j + 1 < N && map[i][j + 1] == 'E') { // 가로 방향
                        EEE[0] = i;
                        EEE[1] = j + 1;
                        EEE[2] = 0;
                    } else if (i + 1 < N && map[i + 1][j] == 'E') { // 세로방향
                        EEE[0] = i + 1;
                        EEE[1] = j;
                        EEE[2] = 1;
                    }
                    eee = false;
                }
            }
        }

        System.out.println(bfs());

        br.close();
    }

    static boolean rangeCheck(int t, int r, int c) {
        if (t == 0) { // 가로
            return (r < 0 || r >= N || c - 1 < 0 || c + 1 >= N) ? true : false;
        }
        return (r - 1 < 0 || r + 1 >= N || c < 0 || c >= N) ? true : false;
    }

    static boolean treeCheck(int t, int r, int c) {
        if (t == 0) { // 가로
            return (map[r][c] == '1' || map[r][c - 1] == '1' || map[r][c + 1] == '1') ? true : false;
        }
        return (map[r][c] == '1' || map[r - 1][c] == '1' || map[r + 1][c] == '1') ? true : false;
    }

    static boolean rotaionCheck(int r, int c) {
        int nr = 0, nc = 0;

        for (int d = 0; d < 8; d++) {
            nr = r + dir[d][0];
            nc = c + dir[d][1];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == '1')
                return true;
        }

        return false;
    }

    static int bfs() {
        boolean[][][] visited = new boolean[2][N][N];
        visited[queue.peek()[2]][queue.peek()[0]][queue.peek()[1]] = true;

        int cur[] = null, nr = 0, nc = 0, nt = 0;
        while (!queue.isEmpty()) {
            cur = queue.poll();

            if (cur[0] == EEE[0] && cur[1] == EEE[1] && cur[2] == EEE[2]) {
                return cur[3];
            }

            for (int d = 0; d < 4; d++) {
                nr = cur[0] + dir[d][0];
                nc = cur[1] + dir[d][1];
                nt = cur[2];

                if (rangeCheck(nt, nr, nc) || treeCheck(nt, nr, nc) || visited[nt][nr][nc])
                    continue;

                visited[nt][nr][nc] = true;
                queue.offer(new int[] { nr, nc, nt, cur[3] + 1 });
            }

            // 회전하는 경우
            nr = cur[0];
            nc = cur[1];
            nt = (cur[2] == 0) ? 1 : 0;

            if (rotaionCheck(nr, nc) || visited[nt][nr][nc])
                continue;

            visited[nt][nr][nc] = true;
            queue.offer(new int[] { nr, nc, nt, cur[3] + 1 });
        }

        return 0;
    }

}
