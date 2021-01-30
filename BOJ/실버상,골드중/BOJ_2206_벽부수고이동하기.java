import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class BOJ_2206_벽부수고이동하기 {
	static class Node {
		int x, y, distance, crash;

		Node(int x, int y, int distance, int crash) {
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.crash = crash;
		}
	}

	static int[][] map;
	static boolean[][][] visited;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());

		N = Integer.parseInt(str.nextToken());
		M = Integer.parseInt(str.nextToken());
		map = new int[N][M];
		visited = new boolean[2][N][M];

						for (int i = 0; i < N; i++) {
			String[] s = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}

		System.out.println(bfs(0, 0));

	}// end main

	static int bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();

		q.offer(new Node(0, 0, 1, 0));
		visited[0][x][y] = true;
		visited[1][x][y] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();
			if(cur.x == N-1 && cur.y == M-1) {
				return cur.distance;
			}
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

				if(map[nx][ny] == 1) { //벽을 만났을때
					if(cur.crash == 0 && !visited[1][nx][ny]) { //벽을 부순적이 없고 다른사람이 벽을부수고 방문한적 없을때
						q.offer(new Node(nx, ny, cur.distance+1, 1));
						visited[1][nx][ny] = true;
					}
				} else { //벽이 아닐때
					if(!visited[cur.crash][nx][ny]) {
					//현재 노드가 그전에 벽을 부쉈는지에 대한 정보를 바탕으로 visited배열에 저장 한번 부순적이 있다면 [1][nx][ny], 부순적 없으면 [0][nx][ny]
						q.offer(new Node(nx,ny,cur.distance+1,cur.crash));
						visited[cur.crash][nx][ny] = true;
					}
				}
			}
		}
		return -1;
	}
}// end class