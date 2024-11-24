package org.testing;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class UtilityMethods {


}

public class App {
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here
        int n = V;
        int m = adj.size();

        ArrayList<Integer> bfs = new ArrayList<>();

        Queue<Integer> q = new LinkedList<>();
        int[] visit = new int[n];

        q.offer(0);
        visit[0] = 1;

        while(!q.isEmpty()){
            Integer ele = q.poll();
            bfs.add(ele);

            for(Integer it : adj.get(ele)){
                if(visit[it]==0){
                    q.offer(it);
                    visit[it] = 1;
                }
            }
        }

        return bfs;

    }

    public static void main(String[] args) {
    }
}