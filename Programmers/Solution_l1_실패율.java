import java.io.*;
import java.util.PriorityQueue;

public class Solution_l1_실패율 {

    static class failureRate implements Comparable<failureRate> {
        int index;
        double rate;

        failureRate(int index, double rate) {
            this.index = index;
            this.rate = rate;
        }

        @Override
        public int compareTo(failureRate o) {
            if (this.rate == o.rate)
                return Integer.compare(this.index, o.index);
            return -Double.compare(this.rate, o.rate);
        }
    }

    static int N = 5;
    static int[] stages = { 2, 1, 2, 6, 2, 4, 3, 3 };

    public static void main(String[] args) throws Exception {
        PriorityQueue<failureRate> pq = new PriorityQueue<>();

        int people = stages.length;
        int[] count = new int[N + 2];
        for (int i = 0; i < people; i++) {
            count[stages[i]]++;
        }

        for (int i = 1; i <= N; i++) {
            double rate = count[i] != 0 ? (double) count[i] / people : 0;
            pq.offer(new failureRate(i, rate));
            people -= count[i];
        }

        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = pq.poll().index;
            System.out.print(answer[i] + " ");
        }
    }

}