package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class BOJ_6087_레이저통신 {
    static class Node {
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static char[][] map;
    static int[][] visited;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int W,H;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine());
        W = Integer.parseInt(str.nextToken());
        H = Integer.parseInt(str.nextToken());

        map = new char[H][W];
        visited = new int[H][W];

        for(int i=0; i<H; i++) {
            String[] st = br.readLine().split("");
            for(int j=0; j<W; j++) {
                map[i][j] = st[j].charAt(0);
            }
        }

        String[] s = new String[2];
        int index=0;
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                if(map[i][j] == 'C') {
                    s[index++] = i+" "+j;
                }
            }
        }
        
        str = new StringTokenizer(s[0]);
        int startX = Integer.parseInt(str.nextToken());
        int startY = Integer.parseInt(str.nextToken());

        bfs(startX,startY);
        
        str = new StringTokenizer(s[1]);
        int endX = Integer.parseInt(str.nextToken());
        int endY = Integer.parseInt(str.nextToken());
        System.out.println(visited[endX][endY]-1);

        br.close();
    }//end main

    static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[x][y] = 0;

        //거울의 수가 최소이기때문에 한방향으로 갈 수 있는 최대로 간다
        //만약 방문한 좌표라도 지금 나의 distance가 작다면 바꿔준다.
        while(!q.isEmpty()) {
            Node cur = q.remove();
            for(int k=0; k<4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];
                while(nx>=0 && nx<H && ny>=0 && ny<W) {
                    if(map[nx][ny] == '*') break;
                    if(visited[nx][ny] != 0) {
                        if(visited[nx][ny] <= visited[cur.x][cur.y]) break;
                    }
                    q.add(new Node(nx,ny));
                    visited[nx][ny] = visited[cur.x][cur.y] + 1;
                    nx = nx + dx[k];
                    ny = ny + dy[k];
                }
            }
        }
    }
}//end class