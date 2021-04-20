import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_S2_1260_dfs와bfs {
	static StringBuilder sb = new StringBuilder();
	static boolean[][] adjMatrix;
	static boolean[] visited;
	static int N,M,V;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		V = stoi(st.nextToken());

		adjMatrix = new boolean[N+1][N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			adjMatrix[from][to] = adjMatrix[to][from] = true;
		}

		visited = new boolean[N+1];
		dfs(V);

		sb.append("\n");

		bfs();

		System.out.print(sb);
		br.close();
	}

	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static void bfs() {
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];

		deque.offer(V);
		visited[V] = true;
		sb.append(V).append(" ");

		while(!deque.isEmpty()) {
			int cur = deque.poll();

			for(int i=0; i<=N; i++) {
				if(visited[i] || !adjMatrix[cur][i]) continue;
				sb.append(i).append(" ");
				visited[i] = true;
				deque.offer(i);
			}
		}
	}

	static void dfs(int v) {
		visited[v] = true;
		sb.append(v).append(" ");

		for(int i=0; i<=N; i++) {
			if(visited[i] || !adjMatrix[v][i]) continue;
			dfs(i);
		}
	}
}