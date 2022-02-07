import java.io.*;
import java.util.*;

public class Main_bj_g4_1939_중량제한 {

    static class Node implements Comparable<Node> {
        int from;
        int to;
        int weight;

        Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return -Integer.compare(this.weight, node.weight);
        }
    }

    static int find(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b)
            parent[b] = a;
    }

    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 섬의 개수
        int M = Integer.parseInt(st.nextToken()); // 다리 정보의 개수

        PriorityQueue<Node> pq = new PriorityQueue<>();

        int from = 0, to = 0, w = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken()) - 1;
            to = Integer.parseInt(st.nextToken()) - 1;
            w = Integer.parseInt(st.nextToken());

            pq.add(new Node(from, to, w));
        }

        parent = new int[N];
        for (int i = 0; i < N; i++)
            parent[i] = i;

        st = new StringTokenizer(br.readLine());
        from = Integer.parseInt(st.nextToken()) - 1;
        to = Integer.parseInt(st.nextToken()) - 1;

        Node cur = null;
        while (!pq.isEmpty()) {
            cur = pq.poll();

            union(cur.from, cur.to);

            if (find(from) == find(to)) {
                System.out.println(cur.weight);
                break;
            }
        }

        br.close();
    }
}