import java.io.BufferedReader;
import java.io.InputStreamReader;

class BOJ_9663_NQueen {
	static int[] arr;
	static int n,cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		cnt = 0;
		nQueen(0);
		System.out.println(cnt);
		br.close();
	}

	static void nQueen(int depth) {
		if(depth == n) {
			cnt++;
			return;
		}

		for(int i=0; i<n; i++) {
			arr[depth] = i;
			if(Possibility(depth)) {
				nQueen(depth+1);
			}
		}
	}

	static boolean Possibility(int col) {
		for(int i=0; i<col; i++) {
			//행 비교
			if(arr[col] == arr[i]) return false;
			//대각선 비교
			else if(Math.abs(col-i) == Math.abs(arr[col]-arr[i])) return false;
		}
		return true;
	}
}


/* 시간초과
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main {
	static int[][] arr;
	static boolean[][] visited;
    static int[] dx = { -1, 0, 1, 1, 1, 0, -1, -1 };// 좌상부터 시계방향
	static int[] dy = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int N, count;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N][N];
		count=0;
		

		for(int i=0; i<N; i++) {
			arr[0][i] = 1;
			dfs(1);
			arr[0][i] = 0;
		}
		
		System.out.println(count);

		br.close();
	}
	
	static void dfs(int x) {
		if(checkCount()) {
			count++;
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[x][i] && !moveCheck(x, i)) {
				arr[x][i] = 1;
				visited[x][i] = true;
				dfs(x+1);
				arr[x][i] = 0;
				visited[x][i] = false;
			}
		}
	}

	static boolean moveCheck(int x, int y) {
		for(int k=0; k<8; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			while(nx>=0 && nx<N && ny>=0 && ny<N) {
				if(arr[nx][ny] == 1) return true;
				nx = nx + dx[k];
				ny = ny + dy[k];
			}
		}
		return false;
	}

	static boolean checkCount() {
		int cnt=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j] == 1) cnt++;
			}
		}
		return (cnt == N)?true:false;
	}
}*/