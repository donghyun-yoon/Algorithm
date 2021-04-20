import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5656_벽돌깨기 {

    static Queue<int[]> queue;
    static boolean[][] visited;
    static int[][] map, tmap;
    static int[][] d = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static int[] point;
    static int N, W, H, res;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new int[H][W];
            point = new int[N];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            res = Integer.MAX_VALUE;
            dfs(0);

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    static void dfs(int cnt) {
        if (cnt == N) {
            tmap = new int[H][W];
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++)
                    tmap[i][j] = map[i][j];
            }

            for(int i=0; i<N; i++) {
                crash(tmap, point[i]);
                moveDown(tmap);
            }

            int count = 0;
            for(int[] tt:tmap) {
                for(int t:tt) if(t > 0) count++;
            }
            res = Math.min(res, count);

            return;
        }

        for (int w = 0; w < W; w++) {
            point[cnt] = w;
            dfs(cnt + 1);
        }
    }

    static void crash(int[][] map, int w) {
        queue = new ArrayDeque<>();
        visited = new boolean[H][W];

        for(int h=0; h<H; h++) {
            if(map[h][w] > 0) {
                visited[h][w] = true;
                queue.offer(new int[] {h, w, map[h][w] - 1 });
                map[h][w] = 0;
                break;
            }
        }

        int[] cur = null;
        int nx = 0, ny = 0;
        while (!queue.isEmpty()) {
            cur = queue.poll();

            if (cur[2] == 0)
                continue;

            for (int dir = 0; dir < 4; dir++) {
                for (int k = 1; k <= cur[2]; k++) {
                    nx = cur[0] + d[dir][0] * k;
                    ny = cur[1] + d[dir][1] * k;

                    if (nx < 0 || nx >= H || ny < 0 || ny >= W) break;
                    if (visited[nx][ny] || map[nx][ny] == 0) continue;

                    visited[nx][ny] = true;
                    queue.offer(new int[] {nx, ny, map[nx][ny] - 1});
                    map[nx][ny] = 0;
                }
            }
        }
    }

    static void moveDown(int[][] map) {
        for (int j = 0; j < W; j++) {
            for (int i = H - 1; i >= 0; i--) {
                if (map[i][j] == 0) continue;
                int nx = i;
                int ny = j;
                while (true) {
                    if (nx == H - 1)
                        break;
                    if (map[nx + 1][ny] != 0)
                        break;
                    nx++;
                }
                map[nx][ny] = map[i][j];
                if(nx != i) map[i][j] = 0;
            }
        }
    }
}