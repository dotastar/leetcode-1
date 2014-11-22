package Binary_Tree_Level_Order_Traversal_II;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


public class Question {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//using BFS template
	//http://blog.csdn.net/linhuanmars/article/details/23414711
	//类似：https://oj.leetcode.com/problems/binary-tree-level-order-traversal/
	public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (root == null){
			return result;
		}
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while(!queue.isEmpty()){
			ArrayList<Integer> item = new ArrayList<Integer>();
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				item.add(node.val);
				if (node.left != null){
					queue.offer(node.left);
				}
				if (node.right != null){
					queue.offer(node.right);
				}
			}
			result.add(item);
		}
		Collections.reverse(result);
		return result;
	}
}

 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }