import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BOJ_15652_N과M4{
	static int[] num;
	static int n,m;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		num = new int[m];
		sequence(0,1);
	}

	static void sequence(int cnt, int start) {
		if(cnt == m) {
			for(int v: num) System.out.print(v+" ");
			System.out.println();
			return;
		}
		for(int i=start; i<=n; i++) {
			num[cnt] = i;
			sequence(cnt+1, i);
		}
	}
}