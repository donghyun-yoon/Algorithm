import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1949_등산로조성 {

    static class Node {
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static ArrayList<Node> top;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int N,K,C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            visited = new boolean[N][N];
            top = new ArrayList<>();

            int max = Integer.MIN_VALUE;
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] > max) max = map[i][j];
                }
            }

            C = Integer.MIN_VALUE;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j] != max) continue;
                    visited[i][j] = true;
                    dfs(1,i,j,false);
                    visited[i][j] = false;
                }
            }
            sb.append("#").append(tc).append(" ").append(C).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
/*
1
5 1
9 3 2 3 2 
6 3 1 7 5
3 4 8 9 9
2 3 7 7 7
7 6 5 5 8
*/

    static void dfs(int cnt, int x, int y, Boolean flag) {
        
        C = Math.max(C, cnt);
        
        for(int dir=0; dir<4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny]) continue;
            
            if(map[nx][ny] < map[x][y]) {
                visited[nx][ny] = true;
                dfs(cnt+1, nx, ny, flag);
                visited[nx][ny] = false;
            } else {
                if(!flag) {
                    for(int k=1; k<=K; k++) {
                        map[nx][ny] -= k;
                        if(map[nx][ny] < map[x][y]) {
                            visited[nx][ny] = true;
                            dfs(cnt+1, nx, ny, true);
                            visited[nx][ny] = false;
                        }
                        map[nx][ny] += k;
                    }
                }
            }
        }
    }
}