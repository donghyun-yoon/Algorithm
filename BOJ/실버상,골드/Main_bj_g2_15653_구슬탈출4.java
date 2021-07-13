import java.io.*;
import java.util.*;

public class Main {

    static char[][] map;
    static int dir[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static int N, M, res;

    static int bfs(int rx, int ry, int bx, int by) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[N][M][N][M];

        queue.offer(new int[] { 1, rx, ry, bx, by });
        visited[rx][ry][bx][by] = true;

        int rnx, rny, bnx, bny, rdist, bdist, cur[];
        rnx = rny = bnx = bny = rdist = bdist = 0;

        while (!queue.isEmpty()) {
            cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                rnx = cur[1];
                rny = cur[2];
                bnx = cur[3];
                bny = cur[4];

                // 파란공 움직이기
                while (true) {
                    bnx += dir[d][0];
                    bny += dir[d][1];

                    if (map[bnx][bny] == 'O') {
                        break;
                    }
                    if (map[bnx][bny] == '#') {
                        bnx -= dir[d][0];
                        bny -= dir[d][1];
                        break;
                    }
                }

                if (map[bnx][bny] == 'O') {
                    continue;
                }

                // 빨간공 움직이기
                while (true) {
                    rnx += dir[d][0];
                    rny += dir[d][1];

                    if (map[rnx][rny] == 'O') {
                        break;
                    }
                    if (map[rnx][rny] == '#') {
                        rnx -= dir[d][0];
                        rny -= dir[d][1];
                        break;
                    }
                }

                if (map[rnx][rny] == 'O') {
                    return cur[0];
                }

                if (rnx == bnx && rny == bny) {
                    rdist = Math.abs(cur[1] - rnx) + Math.abs(cur[2] - rny);
                    bdist = Math.abs(cur[3] - bnx) + Math.abs(cur[4] - bny);

                    if (rdist > bdist) {
                        rnx -= dir[d][0];
                        rny -= dir[d][1];
                    } else {
                        bnx -= dir[d][0];
                        bny -= dir[d][1];
                    }
                }

                if (!visited[rnx][rny][bnx][bny]) {
                    queue.offer(new int[] { cur[0] + 1, rnx, rny, bnx, bny });
                    visited[rnx][rny][bnx][bny] = true;
                }

            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로

        map = new char[N][M];

        int rx, ry, bx, by;
        rx = ry = bx = by = 0;

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'R') {
                    map[i][j] = '.';
                    rx = i;
                    ry = j;
                } else if (map[i][j] == 'B') {
                    map[i][j] = '.';
                    bx = i;
                    by = j;
                }
            }
        }

        System.out.println(bfs(rx, ry, bx, by));

        br.close();
    }
}