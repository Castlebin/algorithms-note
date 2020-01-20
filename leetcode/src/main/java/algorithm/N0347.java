package algorithm;

import java.util.*;

/**
 * 347. Top K Frequent Elements
 */
public class N0347 {

    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numFrequentMap = buildNumFrequentMap(nums);
        Queue<Map.Entry<Integer, Integer>> frequentHeap = buildFrequentHeap(numFrequentMap);

        return getTopK(frequentHeap, k);
    }

    private static List<Integer> getTopK(Queue<Map.Entry<Integer, Integer>> heap, int k) {
        List<Integer> result = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            result.add(heap.poll().getKey());
        }
        return result;
    }

    private static Queue<Map.Entry<Integer, Integer>> buildFrequentHeap(Map<Integer, Integer> numFrequentMap) {
        Queue<Map.Entry<Integer, Integer>> frequentHeap = new PriorityQueue<>((a, b) -> b.getValue().compareTo(a.getValue()));
        frequentHeap.addAll(numFrequentMap.entrySet());
        return frequentHeap;
    }

    private static Map<Integer, Integer> buildNumFrequentMap(int[] nums) {
        Map<Integer, Integer> numFrequentMap = new HashMap<>();
        if (nums == null || nums.length == 0) {
            return numFrequentMap;
        }
        for (int num : nums) {
            numFrequentMap.merge(num, 1, Integer::sum);
        }

        return numFrequentMap;
    }

}
