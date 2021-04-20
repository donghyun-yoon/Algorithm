import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_bj_g3_14890_경사로 {

    static int[][] map,tmap;
	static int N,L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		tmap = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = tmap[j][i] = Integer.parseInt(st.nextToken());
			}
		}

		int res = 0;
		for(int i=0; i<N; i++) {
			res += ramp(map[i]);
			res += ramp(tmap[i]);
		}
		System.out.println(res);
        br.close();
	}

	static int ramp(int[] road) {
		int beforeHeight = road[0];// 이전 높이
		int cnt = 0; //이전 높이가 몇개 있는지를 위한 변수
		int j = 0;

		while(j < N) {
			if(beforeHeight == road[j]) { // 높이가 같을때
				++cnt; ++j;
			}else if(beforeHeight+1 == road[j]) { //높이가 1 높을때
				if(cnt < L) return 0;
				beforeHeight++;
				cnt = 1;
				++j;
			}else if(beforeHeight-1 == road[j]) {
				int count = 0;
				for(int k=j; k<N; k++) {
					if(road[k] != beforeHeight-1) break;
					if(++count == L) break;
				}
				if(count < L) return 0;
				beforeHeight--;
				cnt = 0;
				j += L;
			} else return 0;
		}
		return 1;
	}

}