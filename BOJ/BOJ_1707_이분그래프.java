package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class BOJ_1707_이분그래프 {
	static ArrayList<Integer> adList[];
	static int V, E;
	static boolean isCheck = false;

	static void bfs(int start, int color, int[] visit) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		visit[start] = color;

		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int next : adList[cur]) {
				if (visit[next] == 0) {
					q.add(next);
					visit[next] = visit[cur] * -1;
				} else if (visit[next] + visit[cur] != 0) {
					isCheck = true;
					return;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int K = Integer.parseInt(br.readLine());
		int[] visit;

		while (K-- > 0) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			V = Integer.parseInt(str.nextToken());
			E = Integer.parseInt(str.nextToken());

			adList = new ArrayList[V + 1];
			for (int i = 0; i <= V; i++) {
				adList[i] = new ArrayList<Integer>();
			}

			for (int i = 1; i <= E; i++) {
				str = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(str.nextToken());
				int to = Integer.parseInt(str.nextToken());

				adList[from].add(to);
				adList[to].add(from);
			}

			visit = new int[V + 1];

			for (int i = 1; i <= V; i++) {
				if (isCheck) break;
				if (visit[i] == 0) bfs(i, 1, visit);
			}
			System.out.println((isCheck) ? "NO" : "YES");
			isCheck = false;
		}
		br.close();
	}// end main
}// end class