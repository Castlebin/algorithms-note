package ds.ch05.exe;

import java.util.*;

public class HuffmanCodes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int charCount = Integer.parseInt(sc.nextLine());
        String[] charFreqs = sc.nextLine().split("\\s+");
        int count = Integer.parseInt(sc.nextLine());
        List<Map<Character, String>> codeMapList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Map<Character, String> codeMap = new HashMap<>();
            for (int j = 0; j < charCount; j++) {
                String[] code = sc.nextLine().split("\\s+");
                codeMap.put(code[0].charAt(0), code[1]);
            }
            codeMapList.add(codeMap);
        }

        List<CharFreqNode> charFreqNodeList = buildCharFreqList(charFreqs);
        CharFreqNode huffmanTree = buildHuffmanTree(charFreqNodeList);
        // 遍历，得到Huffman编码
        Map<Character, String> huffmanCodeMap = reverseForHuffmanCode(huffmanTree);
        int encodeSize = getEncodeSize(huffmanCodeMap, charFreqNodeList);

        for (Map<Character, String> codeMap : codeMapList) {
            checkHuffmanCode(codeMap, charFreqNodeList, encodeSize);
        }
    }

    private static boolean checkHuffmanCode(Map<Character, String> codeMap , List<CharFreqNode> charFreqNodeList, int encodeSize) {
        Set<String> codeSet = new HashSet<>(codeMap.values());
        if (codeSet.size() != charFreqNodeList.size()) {
            return false;
        }
        int eSize = getEncodeSize(codeMap, charFreqNodeList);
        if (eSize != encodeSize) {
            return false;
        }


        // todo gaoyuxin
        return true;
    }

    private static int getEncodeSize(Map<Character, String> huffmanCodeMap, List<CharFreqNode> charFreqNodeList) {
        int encodeSize = 0;
        for (CharFreqNode node : charFreqNodeList) {
            encodeSize += huffmanCodeMap.get(node.data).length() * node.freq;
        }
        return encodeSize;
    }

    private static CharFreqNode buildHuffmanTree(List<CharFreqNode> charFreqNodeList) {
        PriorityQueue<CharFreqNode> heap = new PriorityQueue<>((o1, o2) -> o1.freq - o2.freq);
        heap.addAll(charFreqNodeList);
        while (heap.size() > 1) {
            CharFreqNode left = heap.remove();
            CharFreqNode right = heap.remove();
            CharFreqNode newNode = new CharFreqNode(null, left.freq + right.freq);
            newNode.left = left;
            newNode.right = right;
            heap.add(newNode);
        }
        return heap.remove();
    }

    private static List<CharFreqNode> buildCharFreqList(String[] charFreqs) {
        List<CharFreqNode> charFreqList = new ArrayList<>();
        for (int i = 0; i < charFreqs.length; i += 2) {
            charFreqList.add(new CharFreqNode(charFreqs[i].charAt(0), Integer.parseInt(charFreqs[i+1])));
        }
        return charFreqList;
    }

    private static Map<Character, String> reverseForHuffmanCode(CharFreqNode root) {
        Map<Character, String> codeMap = new HashMap<>();
        Stack<CharFreqNode> stack = new Stack<>();
        Stack<String> codeStack = new Stack<>();
        CharFreqNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
                if (cur != null) {
                    codeStack.push("0");
                }
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                if (cur.left == null) {
                    codeMap.put(cur.data, getCode(codeStack));
                    codeStack.pop();
                }
                if (cur == root) {
                    codeStack.clear();
                }
                cur = cur.right;
                if (cur != null) {
                    codeStack.push("1");
                }
            }
        }
        return codeMap;
    }

    public static String getCode(Stack<String> codeStack) {
        StringBuilder sb = new StringBuilder();
        for (String s : codeStack) {
            sb.append(s);
        }
        return sb.toString().substring(0, sb.length());
    }

    public static class CharFreqNode {
        Character data;
        int freq;

        CharFreqNode left;
        CharFreqNode right;

        CharFreqNode(Character data, int freq) {
            this.data = data;
            this.freq = freq;
        }
    }

}
