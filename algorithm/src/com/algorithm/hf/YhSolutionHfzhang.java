package com.algorithm.hf;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hfzhang
 * @date 2020/12/6
 */
public class YhSolutionHfzhang {

    public List<List<Integer>> generate(int numRows){
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0;i<numRows;++i){
            List<Integer> row = new ArrayList<>();
            for(int j=0;j<=i;++j){
                if(j==0 || j==i){
                    //每一行首尾都为1
                    row.add(1);
                }else{
                    //除了首尾，每一行中间部分，都是与该位置相邻的上一行（list.get(i-1)）的左右两数字（(j-1)和(j)）之和
                    row.add(list.get(i-1).get(j-1)+list.get(i-1).get(j));
                }
            }
            list.add(row);
        }
        return list;
    }

    public static void main(String[] args) {
        YhSolutionHfzhang yhSolutionHfzhang = new YhSolutionHfzhang();
        System.out.println(yhSolutionHfzhang.generate(5));
    }
}
