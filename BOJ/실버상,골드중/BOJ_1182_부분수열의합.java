import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BOJ_1182_부분수열의합{
	static int N,S, count=0;;
	static int[] input;
	static int[] numbers;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		input = new int[N];
		numbers = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		subsequence(0, 0);
		System.out.println((S==0)?--count:count);
	}

	static void subsequence(int cnt, int start) {
		if(cnt == N) {
			int sum = 0;
			
			if(start == S) {
				count++;
			}
			return;
		}

		subsequence(cnt+1, start+numbers[cnt]);
		subsequence(cnt+1, start);
	}
}