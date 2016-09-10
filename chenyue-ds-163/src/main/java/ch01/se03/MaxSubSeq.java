package ch01.se03;

public class MaxSubSeq {

    public Long onlineMaxSubSeq(Integer[] arr) {
        long thisSum = 0, maxSubSeqSum = 0;

        for (int i = 0; i < arr.length; i++) {
            thisSum += arr[i];
            if (thisSum > maxSubSeqSum) {
                maxSubSeqSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
        }
        return maxSubSeqSum;
    }

}
