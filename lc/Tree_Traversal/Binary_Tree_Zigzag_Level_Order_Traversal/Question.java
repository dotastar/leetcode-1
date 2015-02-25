package Binary_Tree_Zigzag_Level_Order_Traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Question {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		System.out.println(result.size());

	}

	/**
	 * https://oj.leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
	 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

		For example:
		Given binary tree {3,9,20,#,#,15,7},
		    3
		   / \
		  9  20
		    /  \
		   15   7
		return its zigzag level order traversal as:
		[
		  [3],
		  [20,9],
		  [15,7]
		]

	 */
	
	//类似https://oj.leetcode.com/problems/binary-tree-level-order-traversal/ 不同之处在于由于每层遍历的方向不同，所以不能简单用queue来存node了。
	//http://www.ninechapter.com/solutions/binary-tree-zigzag-level-order-traversal/ 
	//此题需要两个stack(curStack和nextStack)分别存当前层node以及下一层的node。其中nextStack存的即是curStack所有node的孩子们
	//而nextStack是按照何种方式入栈，取决于当前层的奇偶性： 偶数层自左向右（正序），而奇数层自右向左（逆序）。(根为第0层)
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null) {
			return result;
		}
		LinkedList<TreeNode> curStack = new LinkedList<TreeNode>();
		LinkedList<TreeNode> nextStack = new LinkedList<TreeNode>();
		LinkedList<TreeNode> temp;
		curStack.push(root);
		boolean normalOrder = true;// 表示当前在第偶数层，即正序(自左向右)
		while (!curStack.isEmpty()) {
			List<Integer> item = new ArrayList<Integer>(); // 存本层内容
			while (!curStack.isEmpty()) {
				TreeNode node = curStack.pop();
				item.add(node.val);// 当前node值加入item
				// 当前为偶数层，把当前node的孩子按正序压入下一层的栈(nextStack)。(这样这一层处理完之后，处理下一层时，奇数层就能按逆序弹出栈，依次加入item中)
				if (normalOrder) {
					if (node.left != null) {
						nextStack.push(node.left);
					}
					if (node.right != null) {
						nextStack.push(node.right);
					}
				} else {// 奇数层，和偶数层正好相反
					if (node.right != null) {
						nextStack.push(node.right);
					}
					if (node.left != null) {
						nextStack.push(node.left);
					}
				}
			}
			// swap stack。意图是：最开始时候curStack里面有node，而nextStack里没有。随着处理，curStack里的node依次被弹出并存入item，而nextStack依次被压入这些node的孩子们。
			// 直到curStack为空，即curStack里面的node都处理完了。而nextStack里现在压入了原来curStack的所有孩子们。
			// 接下来该处理nextStack了，就把curStack和nextStack的指针交换一下，重新让curStack装着node，而nextStack为空。
			temp = curStack;
			curStack = nextStack;
			nextStack = temp;
			
			normalOrder = !normalOrder;// 勿忘翻转normalOrder的boolean值
			result.add(item);
		}
		return result;
	}
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}