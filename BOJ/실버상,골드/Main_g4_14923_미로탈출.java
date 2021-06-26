import java.io.*;
import java.util.*;

public class Main {

    static int[][] map;
    static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int Hx = Integer.parseInt(st.nextToken()) - 1;
        int Hy = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        int Ex = Integer.parseInt(st.nextToken()) - 1;
        int Ey = Integer.parseInt(st.nextToken()) - 1;

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(String.valueOf(bfs(Hx, Hy, Ex, Ey)));
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean rangeCheck(int r, int c) {
        return (r < 0 || c < 0 || r >= N || c >= M) ? true : false;
    }

    static int bfs(int Hx, int Hy, int Ex, int Ey) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[2][N][M];

        queue.offer(new int[] { Hx, Hy, 0, 0 }); // 시작위치, 거리, 지팡이사용여부
        visited[0][Hx][Hy] = true;

        int nx = 0, ny = 0, nd = 0, nc = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == Ex && cur[1] == Ey) {
                return cur[2];
            }

            for (int d = 0; d < 4; d++) {
                nx = cur[0] + dir[d][0];
                ny = cur[1] + dir[d][1];
                nd = cur[2] + 1;
                nc = cur[3];

                if (rangeCheck(nx, ny))
                    continue;
                if (map[nx][ny] == 1) {
                    if (nc == 1)
                        continue;
                    ++nc;
                }
                if (visited[nc][nx][ny])
                    continue;

                visited[nc][nx][ny] = true;
                queue.offer(new int[] { nx, ny, nd, nc });
            }
        }

        return -1;
    }

}