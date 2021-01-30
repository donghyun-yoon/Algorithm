import java.util.Scanner;

class SWEA_34499_퍼펙트셔플 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();

            //StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String[] card = new String[N];
            StringBuilder deck = new StringBuilder();

            for(int i=0; i<N; i++) card[i] = sc.next();

            int half = N/2;
            int remain = N%2; //짝수 0, 홀수 1

            for(int i=0; i<half; i++) deck.append(card[i] + " " + card[i+half+remain] + " ");

            if(remain == 1) deck.append(card[half]);

            System.out.println("#"+test_case+" " + deck);
        }
    }
}
/*
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            StringBuilder half_deck1 = new StringBuilder();
            StringBuilder half_deck2 = new StringBuilder();
            StringBuilder deck = new StringBuilder();

            int cnt=0;
            while(st.hasMoreTokens()) {
                if(N%2 ==0) {
                    if(cnt < (N/2)) {
                        half_deck1.append(st.nextToken()+" ");
                    } else {
                        half_deck2.append(st.nextToken()+" ");
                    }
                } else {
                    if(cnt < (N/2)+1) {
                        half_deck1.append(st.nextToken()+" ");
                    } else {
                        half_deck2.append(st.nextToken()+" ");
                    }
                }
                cnt++;
            }

            StringTokenizer st_deck1 = new StringTokenizer(half_deck1.toString(), " ");
            StringTokenizer st_deck2 = new StringTokenizer(half_deck2.toString(), " ");

            while(st_deck1.hasMoreTokens() || st_deck2.hasMoreTokens()) {
                if(st_deck1.hasMoreTokens()) deck.append(st_deck1.nextToken()+" ");
                if(st_deck2.hasMoreTokens()) deck.append(st_deck2.nextToken()+" ");
            }

            System.out.println("#"+test_case+" " + deck);
        }
*/