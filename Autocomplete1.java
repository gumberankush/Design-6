import java.util.*;

class AutocompleteSystem1 {
    class TrieNode{
        TrieNode[] children;
        List<String> li;

        public TrieNode(){
            this.li = new ArrayList<>();
            this.children = new TrieNode[100];
        }
    }

    private void insert(String sentence){
        TrieNode curr = root;

        for(int i = 0; i < sentence.length(); i++){
            char c = sentence.charAt(i);
            if(curr.children[c-' '] == null){
                curr.children[c-' '] = new TrieNode();
            }
            curr = curr.children[c-' '];
            curr.li.add(sentence);
        }
    }

    private List<String> search(String input){
        TrieNode curr = root;

        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if(curr.children[c-' '] == null){
                return new ArrayList<>();
            }
            curr = curr.children[c-' '];
        }
        return curr.li;
    }

    Map<String, Integer> map;
    PriorityQueue<String> pq;
    String inp;
    TrieNode root;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
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

            if(!map.containsKey(sentences[i])){
                insert(sentences[i]);
            }

            map.put(sentences[i], map.getOrDefault(sentences[i], 0)+count);
        }
    }

    public List<String> input(char c) {
        if(c == '#'){
            if(!map.containsKey(inp)){
                insert(inp);
            }
            // update the cache
            map.put(inp, map.getOrDefault(inp, 0)+1);
            this.inp = "";
            return new ArrayList<>();
        }

        inp = inp + c;

        List<String> search = search(inp);
        for(String word: search){
            pq.add(word);

            if(pq.size() > 3){
                pq.poll();
            }
        }
        List<String> res = new ArrayList<>();

        while(!pq.isEmpty()){
            res.add(0, pq.poll());
        }

        return res;
    }
}