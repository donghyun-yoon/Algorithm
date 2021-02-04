import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BOJ_16395_파스칼의삼각형{
	static int[][] pascal = new int[30][30];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		pascal[0][0] = 1;
		for(int i=1; i<30; i++) {
			pascal[i][0] = 1;
			pascal[i][i] = 1;
		}
		for(int i=2; i<30; i++) {
			for(int j=1; j<i; j++) {
				pascal[i][j] += pascal[i-1][j-1] + pascal[i-1][j];
			}
		}
		System.out.println(pascal[N-1][K-1]);
	}
}