// Time Complexity: O(1)
class AutocompleteSystem {
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
            if(!curr.li.contains(sentence)){
                curr.li.add(sentence);
            }
            List<String> top3 = curr.li;

            Collections.sort(top3, (a, b) ->{
                int cntA = map.get(a);
                int cntB = map.get(b);

                if(cntA == cntB){
                    return a.compareTo(b);
                }
                return cntB-cntA;
            });

            if(top3.size() > 3){
                top3.remove(top3.size()-1);
            }
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
                return a.compareTo(b);
            }
            return cntA-cntB;
        });

        for(int i = 0; i < sentences.length; i++){
            int count = times[i];

            map.put(sentences[i], map.getOrDefault(sentences[i], 0)+count);
            insert(sentences[i]);
        }
    }

    public List<String> input(char c) {
        if(c == '#'){
            // update the cache
            map.put(inp, map.getOrDefault(inp, 0)+1);
            insert(inp);
            this.inp = "";
            return new ArrayList<>();
        }

        inp = inp + c;

        List<String> search = search(inp);
        return search;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */