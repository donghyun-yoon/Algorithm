import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class BOJ_1194_달이차오른다 {
	static class Node {
		int x; // row
		int y; // col
		int d; // distance
		int k; // key value
		Node(int x, int y, int d, int k) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.k = k;
		}
	}
	static char[][] map;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int N,M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		int x=0, y=0;
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				if(map[i][j] == '0') {
					x = i; y = j;
				}
			}
		}
		System.out.println(bfs(x,y));
		br.close();
	}

	static int bfs(int x, int y) {
		ArrayDeque<Node> arrayDeque = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N][M][64]; //2 ^ 6 = 64	각각의 키값을 가지고 bfs를 돌렸을떄 방문했는지를 알기 위해서
		arrayDeque.offer(new Node(x,y,0,0));
		visited[x][y][0] = true; // 처음 상태에는 아무 키도 들고있지않기때문에 000000 -> 0

		while(!arrayDeque.isEmpty()) {
			Node cur = arrayDeque.poll();

			if(map[cur.x][cur.y] == '1') return cur.d;

			for(int dir=0; dir<4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				int k = cur.k;

				//범위 체크
				if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
				//벽이거나 해당 키값을 가지고 방문한적 있는지 체크
				if(map[nx][ny] == '#' || visited[nx][ny][k]) continue;

				if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
					if((k & (1<<map[nx][ny]-'A')) == 0) continue;
				}
				if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
					//비트 마스킹을 이용하여 확인하고 다음값은 비트마스킹한 key값을 넣어준다!!!
					k = k | 1<<(map[nx][ny]-'a');
				}

				arrayDeque.add(new Node(nx, ny, cur.d+1, k));
				visited[nx][ny][k] = true;
			}
		}

		return -1;
	}
}