package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class BOJ_5014_스타트링크 {
    static int[] dx = {1,-1};
    static int F,S,G,U,D;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine());

        //F(건물 층), S(현재위치), G(회사위치), Up, Down
        F = Integer.parseInt(str.nextToken());
        S = Integer.parseInt(str.nextToken());
        G = Integer.parseInt(str.nextToken());
        U = Integer.parseInt(str.nextToken());
        D = Integer.parseInt(str.nextToken());
        if(S==G) {
            System.out.println(0);
            return;
        }
        int min = bfs();
        System.out.println((min !=0)?min:"use the stairs");
        br.close();
    }//end main

    public static int bfs() {
        int[] visited = new int[F];
        Queue<Integer> q = new LinkedList();

        q.add(S-1);
        visited[S-1] = 0;
        while(!q.isEmpty()) {
            int cur = q.remove();

            for(int i=0; i<2; i++) {
                int nx = cur + dx[i] * ((i==0)?U:D);

                if(nx<0 || nx>=F || nx == cur) continue;
                if(visited[nx] != 0) continue;
                if(nx == G-1) {
                    return visited[cur]+1;
                }

                q.add(nx);
                visited[nx] = visited[cur]+1;
            }
        }
        return 0;
    }
}//end class