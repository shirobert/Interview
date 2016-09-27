package com.interview.structure;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

        public void insert(String word) {
            root.insert(word.toCharArray(), 0);
        }

        public boolean search(String word) {
            return root.search(word.toCharArray(), 0);
        }

        public boolean startsWith(String prefix) {
            return root.startsWith(prefix.toCharArray(), 0);
        }
}