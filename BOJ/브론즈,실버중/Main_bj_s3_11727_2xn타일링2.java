import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_bj_s3_11727_2xn타일링2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());

		//1<= n <= 1000이므로
		int[] dp = new int[1001];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 3;
		for(int i=3; i<=n; i++) {
			// i-1에는 | 칸밖에 안들어감
			// i-2에는 2만큼의 크기가 있으므로 ㅁ 과, =가 들어갈수 있으므로 *2를 해줘야 한다.
			// dp[i]는 i-1에 |칸 만큼 들어간 것과 i-2칸에 ㅁ, i-2칸에 =이 들어간것을 알 수 있다.
			// 점화식으로 f(n) = f(n-1) + 2 * f(n-2) 가 되는 것을 알 수 있다.
			dp[i] = (dp[i-1] + dp[i-2]*2) % 10007;
		}

		System.out.println(dp[n]);
        br.close();
	}

}