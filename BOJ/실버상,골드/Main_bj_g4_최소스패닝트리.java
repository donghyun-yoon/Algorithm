import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_bj_g4_최소스패닝트리 {

	static class Node implements Comparable<Node> {
		int from;
		int to;
		long weight;
		Node(int from, int to, long weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Main.Node o) {
			return Long.compare(this.weight, o.weight);
		}
	}

	static Node[] edge;
	static int[] parents;
	static int V,E;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		edge = new Node[E];

		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long weight = Long.parseLong(st.nextToken());
			edge[i] = new Node(from, to, weight);
		}

		Arrays.sort(edge);

		parents = new int[V+1];
		for(int i=0; i<=V; i++) {
			parents[i] = i;
		}

		long res = 0;
		for(int i=0; i<E; i++) {
			if(union(edge[i].from, edge[i].to)) res += edge[i].weight;
		}
		System.out.println(res);
		
		br.close();
	}

	static int find(int x) {
		if(parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if(aRoot == bRoot) return false;

		parents[bRoot] = aRoot;
		return true;
	}

}