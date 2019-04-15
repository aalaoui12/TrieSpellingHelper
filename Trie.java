package triespellinghelper;

import java.util.ArrayList;

public class Trie<Value> {
    private static int radix = 256;
    private Node root;
    
    private static class Node {
        private Object val;
        private Node[] next = new Node[radix];
    }
    
    public ArrayList<String> getRecs(String key) { //get the word recs and put them in an arraylist
        Node node = get(root, key, 0); //start off on the node with the string we already have
        int numWordsLeft = numWordsLeft(node);
        if(numWordsLeft > 5) {
            return null;
        }
        else {
            return wordsLeft(key, node);
        }
    }
    
    private int numWordsLeft(Node node) {
        int numWordsLeft = 0;
        if(node.val != null)
            numWordsLeft++;
        
        for(char i = 'a'; i < node.next.length; i++) {
            if(node.next[i] != null)
                numWordsLeft += numWordsLeft(node.next[i]);
        }
        
        return numWordsLeft;
    }
    
    private ArrayList<String> wordsLeft(String key, Node node) {
        ArrayList<String> strings = new ArrayList<>();
        if(node.val != null) { //if val is not null, then we've stumbled across a word in the dictionary!
            strings.add(key);
        }
        
        for(char i = 'a'; i < node.next.length; i++) { //iterate through the characters
            if(node.next[i] != null) {
                String newKey = key.concat(String.valueOf(i));
                strings.addAll(wordsLeft(newKey, node.next[i]));
            }
        }
        
        return strings;
    }
    
    private Node get(Node node, String key, int d) {
        if (node == null)
            return null;
        if (d == key.length())
            return node;
        
        char c = key.charAt(d);
        return get(node.next[c], key, d+1);
    }
    
    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }
    
    private Node put(Node node, String key, Value val, int d) {
        if (node == null)
            node = new Node();
        if (d == key.length()) {
            node.val = val;
            return node;
        }
        
        char c = key.charAt(d);
        node.next[c] = put(node.next[c], key, val, d+1);
        return node;
    }
}
