import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class BOJ_2589_보물섬 {
	static class Node {
		int x;
		int y;
		int distance;
		Node(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
	static char[][] island;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int w,d;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		w = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		island = new char[w][d];
		for(int i=0; i<w; i++) {
			island[i] = br.readLine().toCharArray();
		}
		int max = Integer.MIN_VALUE;
		for(int i=0; i<w; i++) {
			for(int j=0; j<d; j++) {
				if(island[i][j] == 'W') continue;
				max = Math.max(max, bfs(i,j));
			}
		}
		System.out.println(max);
		br.close();
	}

	static int bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visted = new boolean[w][d];

		q.offer(new Node(x,y,0));
		visted[x][y] = true;

		int dis = Integer.MIN_VALUE;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			for(int dir=0; dir<4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];

				if(nx<0 || nx>=w || ny<0 || ny>=d) continue;
				if(visted[nx][ny] || island[nx][ny] == 'W') continue;

				visted[nx][ny] = true;
				q.offer(new Node(nx, ny, cur.distance+1));
				dis = Math.max(dis, cur.distance+1);
			}
		}
		return dis;
	}
}