import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_d4_1868_파핑파핑지뢰찾기 {

    static int[][] d = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
    static int N,res;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            char[][] map = new char[N][N];

            for(int i=0; i<N; i++) {
                map[i] = br.readLine().toCharArray();
            }

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j] == '*') {
                        int nx = 0, ny = 0;
                        for(int dir=0; dir<8; dir++) {
                            nx = i + d[dir][0];
                            ny = j + d[dir][1];

                            if(rangeCheck(nx, ny) || map[nx][ny] == '*' || map[nx][ny] == ',') continue;
                            map[nx][ny] = ',';
                        }
                    }
                }
            }

            res = 0;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j] != '.') continue;
                    bfs(i, j, map);
                    res++;
                }
            }

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j] == ',') res++;
                }
            }
            
            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static int countMine(int x, int y, char[][] map) {
        int cnt = 0;

        int nx = 0, ny = 0;
        for(int dir=0; dir<8; dir++) {
            nx = x + d[dir][0];
            ny = y + d[dir][1];

            if(rangeCheck(nx, ny)) continue;
            if(map[nx][ny] == '*') cnt++;
        }

        return cnt;
    }

    static boolean rangeCheck(int x, int y) {
        return (x < 0 || x >= N || y < 0 || y >= N)? true : false;
    }

    static void bfs(int x, int y, char[][] map) {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[] {x,y});
        map[x][y] = '0';

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            int count = countMine(cur[0], cur[1], map);
            if(count != 0) {
                map[cur[0]][cur[1]] = (char)(count+'0');
                continue;
            }

            for(int dir=0; dir<8; dir++) {
                int nx = cur[0] + d[dir][0];
                int ny = cur[1] + d[dir][1];
    
                if(rangeCheck(nx, ny) || map[nx][ny] == '0') continue;
                
                map[nx][ny] = '0';
                queue.offer(new int[] {nx,ny});
            }
        }
    }
}