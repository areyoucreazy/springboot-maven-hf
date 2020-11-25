package com.algorithm.leet;

/**
 * @author hfzhang
 * @date 2020/11/25
 */
public class ReverseLinkedList {

    /**
     * 题目：
     *
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     *
     * 说明:
     * 1 ≤ m ≤ n ≤ 链表长度。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     *
     * 题目地址：https://leetcode-cn.com/problems/reverse-linked-list-ii/
     */


    /**
     * 解题思路
     * 迭代,反转法
     * 因为要反转中间的链表,所以我把链表分成三条(前部分链表,反转链表和后部分链表)
     * 找到前部分链表的尾节点(第m-1个节点)就可以开始反转,最后把三个链表拼接
     * 前部分链表(1~m-1)
     * 反转链表(m~n)
     * 后部分链表(n~最后)
     */

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.5 MB, 在所有 Java 提交中击败了100.00% 的用户
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        int count = 1;
        //虚拟头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        //找到前部分链表的尾节点
        while (count != m) {
            node = node.next;
            count++;
        }
        //拼接操作: 前部分链表->反转链表 (此时的node是前部分链表的尾节点)
        node.next = reverse(node.next, n - m + 1);
        return dummy.next;
    }

    /**
     * 反转链表
     * @param head
     * @param num 循环次数
     * @return
     */
    private static ListNode reverse(ListNode head, int num) {
        ListNode next;
        ListNode newNode = null;
        //反转链表后的最后一个节点就是一开始的头节点
        ListNode reverseTail = head;
        for (int i = 0; i < num; i++) {
            next = head.next;
            head.next = newNode;
            newNode = head;
            head = next;
        }
        //拼接操作: 反转链表->后部分链表 (此时的head=next=后部分链表头节点)
        reverseTail.next = head;
        return newNode;
    }

    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        reverseBetween(new ListNode(5), 2, 4);
    }
}
