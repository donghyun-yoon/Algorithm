import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_bj_g4_2239_스도쿠 {
	static final int MAX = 10;
	static StringBuilder sb = new StringBuilder();
	static ArrayList<int[]> zero;
	static int sdoku[][], squareNum[][];
	static boolean rowCheck[][], colCheck[][], squareCheck[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		sdoku = new int[MAX][MAX];
		squareNum = new int[MAX][MAX];
		rowCheck = new boolean[MAX][MAX];
		colCheck = new boolean[MAX][MAX];
		squareCheck = new boolean[MAX][MAX];
		zero = new ArrayList<>();

		int idx = 1;
		for(int i=1; i<MAX; i++) {
			for(int j=1; j<MAX; j++) {
				squareNum[i][j] = idx;
				if(j%3 == 0)idx++;
			}
			if(i%3 != 0) idx -= 3;
		}

		for(int i=1; i<MAX; i++) {
			char[] ch = br.readLine().toCharArray();
			for(int j=1; j<MAX; j++) {
				sdoku[i][j] = ch[j-1] - '0';
				if(sdoku[i][j] == 0) zero.add(new int[] {i,j});
				else {
					rowCheck[i][sdoku[i][j]] = true;
					colCheck[j][sdoku[i][j]] = true;
					squareCheck[squareNum[i][j]][sdoku[i][j]] = true;
				}
			}
		}
		dfs(0);
		System.out.println(sb);
        br.close();
	}

	static void dfs(int cnt) {
		if(sb.length() > 0) return;

		if(cnt == zero.size()) {
			if(sb.length() == 0) {
				for(int i=1; i<MAX; i++) {
					for(int j=1; j<MAX; j++) {
						sb.append(sdoku[i][j]);
					}
					if(i != MAX-1) sb.append("\n");
				}
			}
			return;
		}

		int i = zero.get(cnt)[0];
		int j = zero.get(cnt)[1];
		for(int k=1; k<MAX; k++) {
			if(!rowCheck[i][k] && !colCheck[j][k] && !squareCheck[squareNum[i][j]][k]) {
				sdoku[i][j] = k;
				rowCheck[i][k] = colCheck[j][k] = squareCheck[squareNum[i][j]][k] = true;
				dfs(cnt+1);
				sdoku[i][j] = 0;
				rowCheck[i][k] = colCheck[j][k] = squareCheck[squareNum[i][j]][k] = false;
			}
		}
	}
}