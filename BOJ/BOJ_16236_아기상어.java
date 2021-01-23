package Algorithm.BOJ;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236_아기상어 {
    static class Point {
        int x;
        int y;
        int dis;
        Point(int x, int y) {
            this(x, y, 0);
        }
        Point(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }
    static Point shark;
    static ArrayList<Point> fish = new ArrayList<Point>();
    static int[][] map = new int[20][20];
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static int N, cnt, size=2;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        //값 초기화 및 상어위치와 물고기 위치 저장
        for(int i=0; i<N; i++) {
            StringTokenizer str = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int n = Integer.parseInt(str.nextToken());
                map[i][j] = n;
                if(n>0 && n<7) {
                    fish.add(new Point(i, j));
                } else if(n == 9) {
                    shark = new Point(i, j);
                }
            }
        }

        while(!fish.isEmpty()) {
            Queue<Point> q = new LinkedList<>();
            q.add(shark);
            while(!q.isEmpty()) {
                Point cur = q.poll();
                
            }
        }
        System.out.println(cnt);
    }
}
