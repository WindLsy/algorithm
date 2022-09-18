package cn.linshiyou.algorithm.swordRefersToOffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LJ
 * @Description: 剑指 Offer II 087. 复原 IP
 *
 * 中等：回溯
 */
public class zero87 {

    public static void main(String[] args) {

        Solution solution = new Solution();
        System.out.println(solution.restoreIpAddresses("25525511135"));

    }

    static class Solution {

        static final int SEG_COUNT = 4;
        List<String> ans;
        int[] segments;

        public List<String> restoreIpAddresses(String s) {

            ans = new ArrayList<>(); //最后的结果集合
            segments = new int[SEG_COUNT]; //每个部分

            dfs(s, 0, 0);
            return ans;

        }

        private void dfs(String s, int segId, int segStart) {

            //如果找到了4段IP并且遍历完了字符串，那么就是一种答案
            if (segId == SEG_COUNT){
                if (segStart == s.length()){
                    StringBuffer ipAddr = new StringBuffer();
                    for (int i=0; i<SEG_COUNT; i++){
                        ipAddr.append(segments[i]);
                        if (i!=SEG_COUNT-1) ipAddr.append(".");
                    }
                    ans.add(ipAddr.toString());
                }
                return;
            }

            //如果还没有找到4段ip地址就已经遍历完了字符串，那么提前回溯
            if (segStart == s.length()) return;

            // 由于不能有前导0， 如果当前数字为0，那么这一段IP地址只能为0
            if (s.charAt(segStart)=='0'){
                segments[segId] = 0;
                dfs(s, segId+1, segStart+1);
            }

            //一般情况，枚举每一种可能性并递归
            int addr = 0;
            for (int segEnd = segStart; segEnd<s.length(); segEnd++){
                addr = addr*10 + (s.charAt(segEnd)-'0');
                if (addr>0 && addr<=255){
                    segments[segId] = addr;
                    dfs(s, segId+1, segEnd+1);
                }else {
                    break;
                }
            }
        }

    }

}
