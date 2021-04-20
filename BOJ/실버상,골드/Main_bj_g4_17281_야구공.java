import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_bj_g4_17281_야구공 {

	static int[][] inning;
	static int[] perm = new int[10];
	static boolean[] visited = new boolean[10];
	static int N,res = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		inning = new int[N][9];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				inning[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		perm[3] = 0;
		visited[0] = true;
		permutation(0);
		System.out.println(res);
		br.close();
	}

	static void permutation(int cnt) {
		if(cnt == 3) {
			permutation(cnt+1);
			return;
		}

		if(cnt == 9) {
			playGame();
			return;
		}

		for(int i=0; i<9; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			perm[cnt] = i;
			permutation(cnt+1);
			visited[i] = false;
		}
	}

	public static void playGame() {
        int score = 0;
        int out;
        boolean[] roo;
        int hitter = 0;
        
        for (int i = 0; i < N; i++) {
            out = 0;
            roo = new boolean[4];
            while(true) {
				int now = inning[i][perm[hitter]];
                if(hitter==8) hitter = 0;
                else hitter++;
                if (now == 1) { // 안타
                    if(roo[3]) {
                        score++;
                        roo[3]=false;
                    }
                    for(int r=2;r>=1;r--) {
                        if(roo[r]) {
                            roo[r]=false;
                            roo[r+1]=true;
                        }
                    }
                    roo[1]=true;
                } else if (now == 2) { // 2루타
                    if(roo[3]) {
                        score++;
                        roo[3]=false;
                    }
                    if(roo[2]) {
                        score++;
                        roo[2]=false;
                    }
                    if(roo[1]) {
                        roo[1]=false;
                        roo[3]=true;
                    }
                    roo[2]=true;

                } else if (now == 3) { // 3루타
                    for(int r=1;r<=3;r++) {
                        if(roo[r]) {
                            score++;
                            roo[r]=false;
                        }
                    }
                    roo[3] = true;
                } else if (now == 4) { // 홈런
                    for(int r=1;r<=3;r++) {
                        if(roo[r]) {
                            score++;
                            roo[r]=false;
                        }
                    }
                    score++; //타자도 홈으로.
                } else if (now == 0) { // 아웃
                    out++;
                    if (out == 3) {
                        break;
                    }
                }
            }
        }
        if(res < score) {
			res = score;
		}
    } //end of playGame
}