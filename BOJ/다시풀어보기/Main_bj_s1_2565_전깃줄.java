import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_bj_s1_2565_전깃줄 {

	static class Line {
		int left;
		int right;
		Line(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}

	static Line[] line;
	static int[] dp;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());

		line = new Line[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			line[i] = new Line(left, right);
		}

		Arrays.sort(line, new Comparator<Line>(){
			@Override
			public int compare(Main.Line o1, Main.Line o2) {
				return Integer.compare(o1.left, o2.left);
			}
		});

		dp = new int[N];
		int max = 0;
		for(int i=0; i<N; i++) {
			dp[i] = 1;
			for(int j=0; j<i; j++) {
				if(line[i].right > line[j].right && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
					max = Math.max(max, dp[i]);
				}
			}
		}
		System.out.println(N-max);
		br.close();
	}

}