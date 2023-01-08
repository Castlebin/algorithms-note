package leetcode.N901_N1000;

import org.junit.Assert;
import org.junit.Test;

/**
 * 944. Delete Columns to Make Sorted
 */
public class N944 {

    public int minDeletionSize(String[] strs) {
        int count = 0;
        for (int col = 0; col < strs[0].length(); col++) {
            char lastChar = strs[0].charAt(col);
            for (int index = 1; index < strs.length; index++) {
                char curChar = strs[index].charAt(col);
                if (curChar < lastChar) {
                    count++;
                    break;
                }
                lastChar = curChar;
            }
        }
        return count;
    }

    @Test
    public void testMinDeletionSize() {
        Assert.assertEquals(1, minDeletionSize(new String[] {"cba", "daf", "ghi"}));
        Assert.assertEquals(0, minDeletionSize(new String[] {"a", "b"}));
        Assert.assertEquals(3, minDeletionSize(new String[] {"zyx", "wvu", "tsr"}));
    }

}
