package interview.alibaba.guoyun;

/**
 * 5.比较一个源字符串和一个目标字符串，如果在源字符串中包含目标字符串全部字符，输出所包含的第一个最小子串；如果不存在，输出空。
 * 样例:  source = "BPDAUNZHGAHSIWBADNC"，target = "BDN" 满足要求的解  "BADN"
 * 要求：时间复杂度为O(n^2)
 * @desc 最小窗口函数
 * @author Lgy
 * @date 2020-12-21
 */
public class MinWindow {
    /**
     * 这道题的思路是：
     *  1) begin开始指向0， end一直后移，直到begin - end区间包含T中所有字符。
     * 记录窗口长度d
     * 2) 然后begin开始后移移除元素，直到移除的字符是T中的字符则停止，此时T中有一个字符没被
     * 包含在窗口，
     * 3) 继续后移end，直到T中的所有字符被包含在窗口，重新记录最小的窗口d。
     * 4) 如此循环知道end到S中的最后一个字符。
     * 时间复杂度为O(n)
     * @param source string字符串
     * @param target string字符串
     * @return string字符串
     */
    public String minWindow (String source, String target) {
        // write code here
        if(source ==  null || target == null || source.length() == 0 || target.length() == 0){
            return "";
        }
        //init map, 记录T中每个元素出现的次数
        int[] map = new int[128];
        for(int i=0;i<target.length();i++){
            map[target.charAt(i)]++;
        }
        // begin end两个指针指向窗口的首位，d记录窗口的长度， counter记录T中还有几个字符没被窗口包含
        int begin = 0,end=0,d=Integer.MAX_VALUE,counter = target.length(),head=0;
        while(end <source.length()){
             // map[] > 0 说明该字符在T中出现，counter-- 表示对应的字符被包含在了窗口，counter--,
            // 如果s中的字符没有在T中出现，则map[]中对应的字符-1后变为负值
            if(map[source.charAt(end++)]-- > 0){
                counter--;
            }
            // 当counter==0时，说明窗口已经包含了T中的所有字符
            while(counter == 0){
                if(end - begin < d){
                    d = end - (head = begin);
                }
                // begin开始后移，继续向后寻找。如果begin后移后指向的字符在map中==0，表示是在T中出现的，如果没有出现，map[]中的值会是负值。
                // 在T中的某个字符从窗口中移除，所以counter++。
                if(map[source.charAt(begin++)]++ == 0){
                    counter++;
                }
            }
        }
        return d == Integer.MAX_VALUE?"":source.substring(head,head+d);
    }

    public static void main(String[] args) {
        MinWindow minWindow = new MinWindow();
        System.out.println(minWindow.minWindow("BPDAUNZHGAHSIWBADNC","BDN"));
    }
}