package com.interview.structure;

public class TrieNode {
    private TrieNode[] nodes = new TrieNode[26];
    boolean valueNode = false;

    public void insert(char[] chars, int index) {
        if (index >= chars.length) {
            return;
        }
        int i = chars[index] - 'a';
        if (nodes[i] == null) {
            nodes[i] = new TrieNode();
        }
        nodes[i].insert(chars, index + 1);
        if (index == chars.length - 1) {
            nodes[i].valueNode = true;
        }
    }

    public boolean search(char[] chars, int index) {
        if (index >= chars.length) {
            if (valueNode) {
                valueNode = false;  //One time use baby!!!
                return true;
            }
            return false;
        }
        int i = chars[index] - 'a';
        if (nodes[i] == null) {
            return false;
        }
        return nodes[i].search(chars, index + 1);
    }

    public boolean startsWith(char[] chars, int index) {
        if (index >= chars.length) {
            return true;
        }

        int i = chars[index] - 'a';
        if (nodes[i] == null) {
            return false;
        }
        return nodes[i].startsWith(chars, index + 1);
    }
}