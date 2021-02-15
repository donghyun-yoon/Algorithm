import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

class BOJ_2146_다리만들기 {

	static class Node {
		int x;
		int y;
		int d;
		Node(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	static ArrayList<Node> al;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int N, cnt, min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		al = new ArrayList<>();
		visited = new boolean[N][N];
		cnt=1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visited[i][j] || map[i][j] == 0) continue;
				dfs(i, j);
				cnt++;
			}
		}
		for(Node a:al) {
			bfs(a.x,a.y);
		}
		System.out.println(min);
		br.close();
	}

	static void dfs(int x, int y) {
		if(visited[x][y]) return;

		visited[x][y] = true;
		map[x][y] = cnt;
		
		for(int dir=0; dir<4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny]) continue;
			if(map[nx][ny] == 0) {
				boolean isCheck = false;
				for(Node n:al) {
					if(n.x == x && n.y == y) isCheck = true;
				}
				if(!isCheck) al.add(new Node(x, y, 0));
				continue;
			}
			dfs(nx, ny);
		}
	}

	static void bfs(int x, int y) {
		ArrayDeque<Node> q = new ArrayDeque<>();
		visited = new boolean[N][N];
		q.offer(new Node(x, y, 0));

		int check = map[x][y];
		Node cur;
		int nx,ny;
		while(!q.isEmpty()) {
			cur = q.poll();
			for(int dir=0; dir<4; dir++) {
				nx = cur.x + dx[dir];
				ny = cur.y + dy[dir];

				if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny]) continue;
				if(map[nx][ny] != 0 && map[nx][ny] != check) {
					min = Math.min(min, cur.d);
					break;
				} else if(map[nx][ny] != 0) continue;

				visited[nx][ny] = true;
				q.offer(new Node(nx,ny,cur.d+1));
			}
		}
	}
}