package com.algorithm.leet;

import java.util.ArrayList;
import java.util.List;

/**
 * 用递归实现杨辉三角
 * @author hfzhang
 * @date 2020/12/6
 */
public class YhSolution {

    /**
     * 获赞最多的网友题解
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows){
        //存储要返回的杨辉三角
        List<List<Integer>> dg = new ArrayList<>();
        //若0行，则返回空
        if(numRows == 0){
            return dg;
        }

        //第一步，递归出口
        if(numRows == 1){
            dg.add(new ArrayList<>());
            dg.get(0).add(1);
            return dg;
        }

        //第二步，递归注意返回值
        dg = generate(numRows - 1);
        //一级递归要做啥，我们可以看第二行和第三行需要做啥
        //首先是要申请一个list来存储第三行，然后通过第二行得到第三行
        //第三行的首尾为1是确定了的，然后就是中间的数如何做到
        //通过观察很容易拿到for循环里的式子
        //最后别忘了返回值
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for(int j=1;j<numRows-1;j++){
            row.add(dg.get(numRows-2).get(j-1)+dg.get(numRows-2).get(j));
        }
        row.add(1);
        dg.add(row);
        return dg;
    }

    /**
     * 官方题解
     * @param numRows
     * @return
     */
    public List<List<Integer>> generateOfiical(int numRows){
        List<List<Integer>> ret = new ArrayList<>();
        for(int i=0;i<numRows; ++i){
            List<Integer> row = new ArrayList<>();
            for(int j=0;j<=i; ++j){
                if(j==0 || j==i){
                    row.add(1);
                }else{
                    row.add(ret.get(i-1).get(j-1)+ret.get(i-1).get(j));
                }
            }
            ret.add(row);
        }
        return ret;
    }

    public static void main(String[] args) {
        YhSolution solution = new YhSolution();
        System.out.println(solution.generateOfiical(6));
    }
}
