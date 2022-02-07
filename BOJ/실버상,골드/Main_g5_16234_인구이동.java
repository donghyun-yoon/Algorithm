import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_g5_16234_인구이동 {

    static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static int N, L, R, sum;
    static Queue<int[]> move;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = 0;
        move = new ArrayDeque<>();
        for (; res <= 2000; res++) {

            boolean[][] visited = new boolean[N][N];
            boolean isCheck = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j])
                        continue;
                    sum = 0;
                    dfs(i, j, map, visited);

                    if (!move.isEmpty()) {
                        isCheck = true;

                        int pop = sum / (move.size() + 1);
                        map[i][j] = pop;
                        visited[i][j] = true;

                        int[] cur = null;
                        while (!move.isEmpty()) {
                            cur = move.poll();
                            map[cur[0]][cur[1]] = pop;
                        }
                    }
                }
            }

            if (!isCheck)
                break;

        }
        System.out.println(res);
    }

    static boolean rangeCheck(int r, int c) {
        return (r < 0 || r >= N || c < 0 || c >= N) ? true : false;
    }

    static void dfs(int r, int c, int[][] map, boolean[][] visited) {
        visited[r][c] = true;
        sum += map[r][c];

        for (int d = 0; d < 4; d++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];

            if (rangeCheck(nr, nc) || visited[nr][nc] || Math.abs(map[nr][nc] - map[r][c]) < L
                    || Math.abs(map[nr][nc] - map[r][c]) > R)
                continue;

            dfs(nr, nc, map, visited);
            move.add(new int[] { nr, nc });
        }
        return;
    }
}
