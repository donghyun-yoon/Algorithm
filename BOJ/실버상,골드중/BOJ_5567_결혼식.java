import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class BOJ_5567_결혼식 {
    static ArrayList<Integer>[] adjust;
    static int n,m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        //상근이는 0
        adjust = new ArrayList[n];
        for(int i=0; i<n; i++) {
            adjust[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++) {
            StringTokenizer str = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(str.nextToken())-1;
            int to = Integer.parseInt(str.nextToken())-1;
            adjust[from].add(to);
            adjust[to].add(from);
        }
        System.out.println(bfs(0));
    }

    static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[n];

        q.add(start);
        visited[start] = 1;
        int cnt = 0;
        while(!q.isEmpty()) {
            int cur = q.remove();
            for(int i=0; i<adjust[cur].size(); i++) {
                int move = adjust[cur].get(i);

                if(visited[move] != 0) continue;

                q.add(move);
                visited[move] = visited[cur] + 1;
                if(visited[move] <= 3) {
                    cnt++;
                } 
            }
        }
        return cnt;
    }
}