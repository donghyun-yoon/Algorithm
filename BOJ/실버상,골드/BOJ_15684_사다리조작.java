import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BOJ_15684_사다리조작 {
    static int[][] map;
	static boolean isCheck;
	static int N,M,H,res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][N];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
		}

		isCheck = false;
		for(int i=0; i<4; i++) {
            res = i;
			perm(0, 0);
			if(isCheck) {
				break;
			}
		}
		System.out.println((isCheck)?res:-1);
		br.close();
	}
    
	static boolean LadderResult() {
		for(int i=0; i<N; i++) {
			int nx = 0;
			int ny = i;
			while(nx<H && ny>=0 && ny<N) {
				if(map[nx][ny] == 1) ny += 1;
				else if(ny-1>=0 && map[nx][ny-1] == 1) ny -= 1;
				nx += 1;
			}
			if(ny != i) return false;
		}
		return true;
	}

	static void perm(int cnt, int row) {

		if(isCheck) return;

		if(cnt == res) {
			if(LadderResult()) isCheck = true;
			return;
		}

		for(int i=row; i<H; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==1) continue;
				if(j-1>=0 && map[i][j-1]==1) continue;
				if(j+1<N && map[i][j+1]==1) continue;
				map[i][j] = 1;
				perm(cnt+1, i);
				map[i][j] = 0;
			}
		}
	}
}//end class