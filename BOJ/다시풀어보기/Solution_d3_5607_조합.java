import java.io.*;
import java.util.StringTokenizer;

public class Solution_d3_5607_조합 {
    static final int MOD = 1234567891;
    static long[] factorial;
    //static long[][] dp;
    static int N,R;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        factorial = new long[1000001];
        factorial[0] = 1;
        for(int i=1; i<1000001; i++) {
            factorial[i] = (factorial[i-1]*i)%MOD;
        }

        // nCr -> n-1Ck + n-1Ck-1
        // OutOfMemoryError: Java heap space
        // dp = new long[1000001][1000001];
        // for(int i=0; i<1000001; i++) {
        //     for(int j=0; j<1000001; j++) {
        //         if(i==0 || j==i) dp[i][j] = 1;
        //         else dp[i][j] = (dp[i-1][j-1] % MOD + dp[i-1][j] % MOD) % MOD;
        //     }
        // }

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            // nCr -> n! / r!(n-r)! 에 mod X
            // n! mod X
            // 1 / r!(n-r)! mod X
            // 페르마 정리에 의해 a^X-2 -> 1/a modX와 같다.
            // 따라서, n! * (r!)^X-2 * (n-r)!^X-2 (mod X)

            long res = ( factorial[n] * pow((factorial[r]*factorial[n-r]) % MOD, MOD-2) ) % MOD;

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    //분할 정복을 통해서 제곱근을 구해나간다.
    //base ^ power
    static long pow(long base, long power) {
        // N^0은 어떤 수든 1이므로
        if(power == 0) return 1;

        // 분할정복으로 power를 계속해서 /2해준다.
        long divide = pow(base, power/2);
        if(power%2 == 0) return (divide * divide) % MOD;
        else return ((divide * divide) % MOD * base) % MOD;
        //자바는 정수를 곲/나누기는 가우스 값이기 때문에 홀수일때는 2^3일때 2^2 * 2를 해주는 것이 2^3을 구하는것이다.
    }

    // 시간초과
    // static long combination(int n, int r) {
    //     if(n == r || r == 0) return 1;
    //     return combination(n-1, r-1) % MOD + combination(n-1, r) % MOD;
    // }
}