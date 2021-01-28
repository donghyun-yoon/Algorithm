package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class BOJ_2234_성곽 {
    static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0,-1,0,1};
    static int[] dy = {-1,0,1,0};
    static int n,m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine());
        n = Integer.parseInt(str.nextToken());
        m = Integer.parseInt(str.nextToken());

        map = new int[m][n];
        visited = new boolean[m][n];
        for(int i=0; i<m; i++) {
            str = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        int roomCnt = 0;
        int roomSize = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(visited[i][j]) continue;
                roomSize = Math.max(roomSize, bfs(i,j));
                roomCnt++;
            }
        }
        int maxRoomCnt = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                for(int k=0; k<4; k++) {
                    if((map[i][j] & (1<<k)) != 0) {
                        visited = new boolean[m][n];
                        map[i][j] -= (1<<k);
                        maxRoomCnt = Math.max(maxRoomCnt, bfs(i,j));
                        map[i][j] += (1<<k);
                    }
                }
            }
        }

        System.out.println(roomCnt + "\n" + roomSize + "\n" + maxRoomCnt);
        br.close();
    }//end main

    static int bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x,y));
        visited[x][y] = true;
        int maxRoom = 1;
        while(!q.isEmpty()) {
            Point cur = q.remove();
            for(int dir=0; dir<4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                
                if(nx<0 || nx>=m || ny<0 || ny>=n) continue;
                if((map[cur.x][cur.y] & (1<<dir)) != 0 || visited[nx][ny]) continue;

                q.add(new Point(nx, ny));
                visited[nx][ny] = true;
                maxRoom++;
            }
        }
        return maxRoom;
    }
}