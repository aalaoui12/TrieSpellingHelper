/*
run:
Start typing word, character by character.
Enter next character.
y
Too many recommendations...
Enter next character.
e
Too many recommendations...
Enter next character.
s
Too many recommendations...
Enter next character.
s
yessed
yesses
yessing
Enter next character.
e
yessed
yesses
Enter next character.
d
Last possibility: yessed
BUILD SUCCESSFUL (total time: 37 seconds)
*/

package triespellinghelper;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TrieSpellingHelper {

    public static Trie<Integer> trie;
    
    public static void main(String[] args) throws IOException {
        FileInputStream in = null;
       
        trie = new Trie<>();
       
        try{
            in = new FileInputStream("words.txt"); //read from file
        }   finally {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String word;
            int i = 0;
            while((word = br.readLine()) != null) {
               trie.put(word, i);
               i++;
            } 
            in.close();
        }
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Start typing word, character by character.");
        
        String fullWord = "";
        boolean wordIsFinished = false;
        while(!wordIsFinished) {
            System.out.println("Enter next character.");
            String str = scanner.nextLine();
            if(str.length() != 1) {
                System.out.println("You have not followed the instructions. Enter one character at a time.");
                System.exit(0);
            }
            
            fullWord += str;
            ArrayList<String> recs = trie.getRecs(fullWord); //get word recs
            if(recs == null) {
                System.out.println("Too many recommendations...");
            }
            else if(recs.size() == 1) { //if only one word rec, then that's the last one.
                System.out.println("Last possibility: " + recs.get(0));
                wordIsFinished = true;
            }
            else {
                for(int i = 0; i < recs.size(); i++) {
                    System.out.println(recs.get(i));
                }
            }
        }
    }
    
}
