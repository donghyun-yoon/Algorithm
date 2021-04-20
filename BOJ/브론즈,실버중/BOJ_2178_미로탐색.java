import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_2178_미로탐색 {

	static class Node {
		int x;
		int y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[][] map;
	static int[][] visited;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int N,M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		map = new int[N][M];
		visited = new int[N][M];
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split("");
			for(int j=0; j<M; j++) {
				map[i][j] = stoi(str[j]);
			}
		}

		System.out.println(bfs(0,0));
		br.close();
	}

	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static int bfs(int x, int y) {
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.offer(new Node(x,y));
		visited[x][y] = 1;

		while(!q.isEmpty()) {
			Node cur = q.poll();

			for(int dir=0; dir<4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];

				if(nx<0 || ny<0 || nx>=N || ny>=M || visited[nx][ny] != 0 || map[nx][ny] == 0) continue;

				visited[nx][ny] = visited[cur.x][cur.y] + 1;
				q.offer(new Node(nx, ny));
			}
		}

		return visited[N-1][M-1];
	}
}