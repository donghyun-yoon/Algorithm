import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class BOJ_17406_배열돌리기4 {

	static class Node {
		int r;
		int c;
		int s;
		Node(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}

	static ArrayList<Node> al = new ArrayList<>();
	static int[][] arr;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int[] perm;
	static boolean[] visited;
	static int N,M,K,min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int r,c,s;
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			al.add(new Node(r,c,s));
		}

		visited = new boolean[K];
		perm = new int[K];
		permutation(0);
		
		System.out.println(min);
		br.close();
	}

	static void permutation(int cnt) {
		if(cnt == K) {
			int[][] temp = new int[N+1][M+1];
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					temp[i][j] = arr[i][j];
				}
			}
			for(int i=0; i<K; i++) {
				temp = rotation(al.get(perm[i]).r, al.get(perm[i]).c, al.get(perm[i]).s, temp);
			}
			for(int[] aa: temp) {
				int sum = 0;
				for(int a : aa) sum += a;
				if(sum != 0) min = Math.min(min, sum);
			}
			return;
		}
		for(int i=0; i<K; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			perm[cnt] = i;
			permutation(cnt+1);
			perm[cnt] = 0;
			visited[i] = false;
		}
	}

	static int[][] rotation(int r, int c, int s, int[][] tarr) {
		int[][] temp = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				temp[i][j] = tarr[i][j];
			}
		}
		int xrangeMin, xrangeMax, yrangeMin, yrangeMax;
		xrangeMin = r - s;
		xrangeMax = r + s;
		yrangeMin = c - s;
		yrangeMax = c + s;

		int x = xrangeMin;
		int y = yrangeMin;
		for(int k=0; k<c-1; k++) {
			for(int dir=0; dir<4; dir++) {
				while(true) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					if((nx<xrangeMin) || (nx>xrangeMax) || (ny<yrangeMin) || (ny>yrangeMax)) break;
					temp[nx][ny] = tarr[x][y];
					x = nx;
					y = ny;
				}
			}
			x++;y++;
			xrangeMin++;xrangeMax--;
			yrangeMin++;yrangeMax--;
		}
		return temp;
	}
}