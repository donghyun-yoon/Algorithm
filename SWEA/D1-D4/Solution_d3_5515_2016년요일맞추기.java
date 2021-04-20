import java.io.*;
import java.util.StringTokenizer;

public class Solution_d3_5515_2016년요일맞추기 {

    //계산하기 쉽게 1~12월까지의 누적 일수를 구한다. (2월은 윤달이기 때문에 + 29일)
    static int[] months = {0,31,60,91,121,152,182,213,244,274,305,335};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            // 7의 배수 : 요일(기준요일 + 경과 일)로 사용이 가능
            // 월요일이면 0, 화요일이면 1, 수요일이면 2, 목요일이면 3, 금요일이면 4, 토요일이면 5, 일요일이면 6
            // 문제에서 1월1일이 금요일 -> %7의 결과가 4이므로 기준요일은 3이 된다.
            //(기준일 + 경과일) % 7 -> 요일을 알수있다.
            int days = 3; // 기준일
            days += months[m-1]; //해당 월 전까지의 누적 일수
            days += d; // 해당 월에 지난 일수 
            days %= 7; // 모듈러 연산을 통해 요일의 정보를 알 수 있다.

            sb.append("#").append(tc).append(" ").append(days).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
}