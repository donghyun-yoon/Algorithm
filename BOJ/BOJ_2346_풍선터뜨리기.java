package Algorithm.BOJ;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class BOJ_2346_풍선터뜨리기 {
    static class Point {
        int index;
        int move;
        Point(int index,int move) {
            this.index = index;
            this.move = move;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        LinkedList<Point> deque = new LinkedList<Point>();
        for(int i=1; i<=N; i++) {
            int move = sc.nextInt();
            deque.add(new Point(i, move));
        }
        int now = 0;
        int val = 0;
        for(int i=0; i<N; i++) {
            if(val > 0) {
                for(int j=0; j<val-1; j++) {
                    ++now;
                    if(now >= deque.size()) now = 0;
                }
            } else if(val < 0) {
                val *= -1;
                for(int j=0; j<val; j++) {
                    --now;
                    if(now < 0) now = deque.size()-1;
                }
            }
            Point p = deque.get(now);
            val = p.move;
            System.out.print(p.index + " ");
            deque.remove(now);
            if(now == deque.size()) now=0;
        }
        /*
        while(!deque.isEmpty()) {
            if(move > 0) {
                while(move > deque.size()) {
                    move -= deque.size();
                }
            } else if(move < 0) {
                move *= 1;
                while(move > deque.size()) {
                    move -= deque.size();
                }
            }
            Point p = deque.get(move);
            deque.remove(move);
            move = p.move;
            System.out.print(p.index + " ");
            if(move == deque.size()) move = 0;
        }
        */

        sc.close();
    }
}
