import java.util.ArrayList;
import java.util.List;

class TrieNode {
    public static final int R = 'z' - 'a' + 1;
    
    public String word;
    public TrieNode[] nodes;
    // Initialize your data structure here.
    public TrieNode() {
        word = null;
        nodes = new TrieNode[R];
    }
    
    @Override
    public String toString() {
    	List<String> chars = new ArrayList<String>(R);
    	for (int i = 0; i < R; i++)
    		chars.add((nodes[i] == null)? "" : ((char) (i + 'a')) + "");
    	return chars.toString();
    }
}

class Trie {
    public TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    private void insertAt(TrieNode r, String word, int c) {
        int n = word.charAt(c) - 'a';
        if (r.nodes[n] == null)
            r.nodes[n] = new TrieNode();
        
        if (c + 1 == word.length())
            r.nodes[n].word = word;
        else
            insertAt(r.nodes[n], word, c + 1);
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if (word == null || word.isEmpty())
            return;
        insertAt(root, word, 0);
    }
}

public class Solution {
    private Trie trie;
    private char[][] board;
    private byte[][] visited;
    private List<String> foundWords;
    
    private void dfs(TrieNode prefix, int i, int j) {
        visited[i][j] = 1;
        int n = board[i][j] - 'a';
        if (prefix.nodes[n] == null) {
        	visited[i][j] = 0;
            return;
        }
        if (prefix.nodes[n].word != null) {
            foundWords.add(prefix.nodes[n].word);
            prefix.nodes[n].word = null;
        }
            
        if (i - 1 > -1 && visited[i - 1][j] == 0) dfs(prefix.nodes[n], i - 1, j);
        if (j - 1 > -1 && visited[i][j - 1] == 0) dfs(prefix.nodes[n], i, j - 1);
        if (i + 1 < board.length && visited[i + 1][j] == 0) dfs(prefix.nodes[n], i + 1, j);
        if (j + 1 < board[0].length && visited[i][j + 1] == 0) dfs(prefix.nodes[n],i, j + 1);
        visited[i][j] = 0;
    }
    
    private void buildTrie(String[] words) {
        this.trie = new Trie();
        for (String w : words)
            this.trie.insert(w);
        return;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        this.foundWords = new ArrayList<String>(words.length);
        if (board == null || board.length == 0 || board[0].length == 0 || words.length == 0)
            return foundWords;
        this.board = board;
        this.buildTrie(words);
        this.visited = new byte[board.length][board[0].length];
        boolean foundAll = false;
        for (int i = 0; i < board.length && !foundAll; i++) {
            for (int j = 0; j < board[0].length && !foundAll; j++) {
                dfs(this.trie.root, i, j);
                foundAll = this.foundWords.size() == words.length;
            }
        }
        return foundWords;
    }
    
    public static void main(String[] args) {
    	String[] boardRows = new String[] {"oaan","etae","ihkr","iflv"};
    	char[][] board = new char[boardRows.length][];
    	for (int r = 0; r < boardRows.length; r++)
    			board[r] = boardRows[r].toCharArray();
    	String[] words = new String[] {"oath","pea","eat","rain"};
    	System.out.println(new Solution().findWords(board, words));
	}
}