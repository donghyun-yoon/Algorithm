import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_d4_1238_Contact {

    static ArrayList<Integer>[] adjList;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        for(int tc=1; tc<=10; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[101];
            
            for(int i=0; i<101; i++) {
                adjList[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<L/2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adjList[from].add(to);
            }

            sb.append("#").append(tc).append(" ").append(bfs(V)).append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    static int bfs(int V) {
        ArrayDeque<Integer> qDeque = new ArrayDeque<>();
        int[] visited = new int[101];

        qDeque.offer(V);
        visited[V] = 1;
        int depth = 1;

        while(!qDeque.isEmpty()) {
            int cur = qDeque.poll();
            for(int i=0; i<adjList[cur].size(); i++) {
                if(visited[adjList[cur].get(i)]>0) continue;
                visited[adjList[cur].get(i)] = visited[cur]+1;
                depth = Math.max(depth, visited[adjList[cur].get(i)]);
                qDeque.offer(adjList[cur].get(i));
            }
        }
        
        int max = Integer.MIN_VALUE;
        for(int i=1; i<101; i++) {
            if(visited[i] == depth) max = Math.max(max, i);
        }
        return max;
    }

}