import java.util.ArrayList;

/**
 * 添加字符串   判断是否以什么为开头  判断是否存在字符串
 */
public class Trie {

        /** Initialize your data structure here. */
        public Trie() {
            strs = new ArrayList<>();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            strs.add(word);
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            if (strs.contains(word)){
                return true;
            }
            return false;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            for (int i=0 ; i<strs.size() ; i++){
                if (strs.get(i).contains(prefix)){
                    return true;
                }
            }
            return false;
        }

        public static ArrayList<String> strs;
}
