// Time Complexity: O(N + mlogm) where N is the number of sentences and m is the number of sentences that start with the input string.
// Space Complexity: O(N)

import java.util.*;

class AutocompleteSystem {

    Map<String, Integer> map;
    PriorityQueue<String> pq;
    String inp;

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.inp = new String();
        this.pq = new PriorityQueue<>((a, b) ->{
            int cntA = map.get(a);
            int cntB = map.get(b);

            if(cntA == cntB){
                return b.compareTo(a);
            }
            return cntA-cntB;
        });

        for(int i = 0; i < sentences.length; i++){
            int count = times[i];

            map.put(sentences[i], map.getOrDefault(sentences[i], 0)+count);
        }
    }

    public List<String> input(char c) {
        if(c == '#'){
            // update the cache
            map.put(inp, map.getOrDefault(inp, 0)+1);
            this.inp = "";
            return new ArrayList<>();
        }

        inp = inp + c;

        for(String key: map.keySet()){
            if(key.startsWith(inp)){
                pq.add(key);

                if(pq.size() > 3){
                    pq.poll();
                }
            }
        }
        List<String> res = new ArrayList<>();

        while(!pq.isEmpty()){
            res.add(0, pq.poll());
        }

        return res;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
