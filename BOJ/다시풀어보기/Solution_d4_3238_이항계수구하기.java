import java.io.*;
import java.util.StringTokenizer;

//3238 이항계수 구하기 풀어보기!!
public class Solution_d4_3238_이항계수구하기 {
    static long[] factorial;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken());
            long r = Long.parseLong(st.nextToken());
            long p = Long.parseLong(st.nextToken());

            factorial = new long[(int)p];
            factorial[0] = 1;
            for(int i=1; i<p; i++) {
                factorial[i] = (factorial[i-1] * i) % p;
            }

            long res = 1;
            while(n > 0 || r > 0) {
                if((int)(n%p) < (int)(r%p)) {
                    res = 0;
                    break;
                }

                long numerator = factorial[(int)(n%p)];
                long denominator = (factorial[(int)(r%p)] * factorial[(int)((n-r)%p)]) % p;
                
                long fermatNum = pow(denominator, p-2, p);
                res = res * ( numerator * fermatNum ) % p;

                n /= p;
                r /= p;
            }

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    static long pow(long base, long power, long p) {
        if(power == 0) return 1;

        long divide = pow(base, power/2, p);
        if(power%2 == 0) return (divide * divide) % p;
        else return ((divide * divide) % p * base) % p;
    }

}