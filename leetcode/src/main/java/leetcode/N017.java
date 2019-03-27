package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. Letter Combinations of a Phone Number
 *
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 *
 * Example:
 *
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class N017 {

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        result.add("");
        for (int i = 0; i < digits.length(); i++) {
            List<String> tmp = new ArrayList<>();
            for (String combination : result) {
                tmp.addAll(backtrack(combination, digits.charAt(i)));
            }
            result = tmp;
        }

        return result;
    }

    private List<String> backtrack(String combination, char nextDigit) {
        List<String> re = new ArrayList<>();
        for (char c : numMap.get(nextDigit)) {
            re.add(combination + c);
        }
        return re;
    }

    private Map<Character, char[]> numMap = new HashMap<Character, char[]>() {{
        put('2', "abc".toCharArray());
        put('3', "def".toCharArray());
        put('4', "ghi".toCharArray());
        put('5', "jkl".toCharArray());
        put('6', "mno".toCharArray());
        put('7', "pqrs".toCharArray());
        put('8', "tuv".toCharArray());
        put('9', "wxyz".toCharArray());
    }};

}
