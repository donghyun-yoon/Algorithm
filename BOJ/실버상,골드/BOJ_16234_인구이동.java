import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

class BOJ_16234_인구이동 {
	static class Node {
		int x;
		int y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static ArrayList<ArrayList<Node>> union;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int N, L, R, index=0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt=0;
		while(true) {
			index = 0;
			union = new ArrayList<ArrayList<Node>>();
			visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j]) continue;
					bfs(i, j);
				}
			}
			for(int i=0; i<union.size(); i++) {
				int sum = 0;
				for(int j=0; j<union.get(i).size(); j++) {
					sum += map[union.get(i).get(j).x][union.get(i).get(j).y];
				}
				int pMove = sum/union.get(i).size();
				for(int j=0; j<union.get(i).size(); j++) {
					map[union.get(i).get(j).x][union.get(i).get(j).y] = pMove;
				}
			}
			if(union.size() == 0) break;
			cnt++;
		}
		System.out.println(cnt);
		br.close();
	}
	
	static void bfs(int x, int y) {
		ArrayDeque<Node> aDeque = new ArrayDeque<>();
		aDeque.offer(new Node(x, y));
		visited[x][y] = true;
		ArrayList<Node> u = new ArrayList<>();
		u.add(new Node(x,y));

		while(!aDeque.isEmpty()) {
			Node cur = aDeque.poll();
			for(int dir=0; dir<4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;

				int pDiff = Math.abs(map[cur.x][cur.y] - map[nx][ny]);
				if(visited[nx][ny] || pDiff<L || pDiff>R) continue;

				u.add(new Node(nx, ny));
				aDeque.offer(new Node(nx, ny));
				visited[nx][ny] = true;
			}
		}
		if(u.size() > 1) {
			index++;
			union.add(u);
		}
	}
}