import java.util.*;

class Solution {
    public ArrayList<Integer> solution(int[] array, int[][] commands) {
        ArrayList<Integer> answer = new ArrayList<>();
        PriorityQueue<Integer> temp = new PriorityQueue<>();
        
        for(int i=0; i<commands.length; i++) {
            for(int j = commands[i][0] - 1; j<commands[i][1]; j++) {
                temp.offer(array[j]);
            }
            while(--commands[i][2] > 0) {
                temp.poll();
            }
            answer.add(temp.poll());
            temp.clear();
        }
        
        return answer;
    }
}
