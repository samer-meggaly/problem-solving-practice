package leetcode.utils;

class TrieNode {
    
    public static final int R = 'z' - 'a' + 1;
    
    public boolean wordEnd;
    public TrieNode[] nodes;
    // Initialize your data structure here.
    public TrieNode() {
        wordEnd = false;
        nodes = new TrieNode[R];
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    private void insertAt(TrieNode r, String word, int c) {
        int n = word.charAt(c) - 'a';
        if (r.nodes[n] == null)
            r.nodes[n] = new TrieNode();
        
        if (c + 1 == word.length())
            r.nodes[n].wordEnd = true;
        else
            insertAt(r.nodes[n], word, c + 1);
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if (word == null || word.isEmpty())
            return;
        insertAt(root, word, 0);
    }
    
    private boolean searchAt(TrieNode r, String word, int c, boolean prefix) {
        int n = word.charAt(c) - 'a';
        if (r.nodes[n] == null)
            return false;
        else if (c + 1 == word.length())
            return r.nodes[n].wordEnd || prefix;
        else
            return searchAt(r.nodes[n], word, c + 1, prefix);
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if (word == null || word.isEmpty())
            return false;
        return searchAt(root, word, 0, false);
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if (prefix == null)
            return false;
        else if (prefix.isEmpty())
            return true;
        return searchAt(root, prefix, 0, true);
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");