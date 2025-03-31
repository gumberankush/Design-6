// Space Complexity: O(n)
// All operations are O(1) time complexity

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class PhoneDirectory {

    Set<Integer> set;
    Queue<Integer> queue;

    public PhoneDirectory(int maxNumbers) {
        this.set = new HashSet<>();
        this.queue = new LinkedList<>();

        for(int i = 0; i < maxNumbers; i++){
            set.add(i);
            queue.offer(i);
        }
    }

    // Time Complexity: O(1)
    public int get() {
        if(queue.isEmpty()){
            return -1;
        }
        int pop = queue.poll();
        set.remove(pop);
        return pop;
    }

    // Time Complexity: O(1)
    public boolean check(int number) {
        if(set.contains(number)){
            return true;
        }
        return false;
    }

    // Time Complexity: O(1)
    public void release(int number) {
        if(set.contains(number)){
            return;
        }
        set.add(number);
        queue.offer(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */