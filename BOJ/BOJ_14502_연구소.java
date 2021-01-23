package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_연구소 {
    static class Point {
        int x;
        int y;
        Point(int x,int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static ArrayList<Point> empty;
    static int N,M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][]map = new int[N][M];

        empty = new ArrayList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) empty.add(new Point(i, j));
            }
        }

        int max = Integer.MIN_VALUE;
        int[][] maxArr = new int[N][M];
        for(int i=0; i<empty.size()-2; i++) {
            for(int j=i+1; j<empty.size()-1; j++) {
                for(int k=j+1; k<empty.size(); k++) {
                    int[][] tmp = new int[N][M];
                    for(int x=0; x<N; x++) {
                        for(int y=0; y<M; y++) {
                            tmp[x][y] = map[x][y];
                        }
                    }
                    Point wall = empty.get(i);
                    Point wall2 = empty.get(j);
                    Point wall3 = empty.get(k);
                    tmp[wall.x][wall.y] = tmp[wall2.x][wall2.y] = tmp[wall3.x][wall3.y] =1;
                    max = Math.max(max, bfs(tmp));
                }
            }
        }
        System.out.println(max);
    }//end main

    static int bfs(int[][] map) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] != 2 || visited[i][j]) continue;

                q.add(new Point(i, j));
                visited[i][j] = true;
                while(!q.isEmpty()) {
                    Point cur = q.poll();
                    for(int k=0; k<4; k++) {
                        int nx = cur.x + dx[k];
                        int ny = cur.y + dy[k];

                        if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
                        if(map[nx][ny] != 0 || visited[nx][ny]) continue;

                        q.add(new Point(nx, ny));
                        visited[nx][ny] = true;
                        map[nx][ny] = 2;
                    }
                }
            }
        }

        int cnt = 0;
        for(int[] x:map) {
            for(int y:x) {
                if(y==0) cnt++;
            }
        }
        return cnt;
    }
}//end class
