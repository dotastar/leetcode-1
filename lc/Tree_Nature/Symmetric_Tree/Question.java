package Symmetric_Tree;

import java.util.LinkedList;

public class Question {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//http://blog.csdn.net/linhuanmars/article/details/23072829
	//recursive
    public boolean isSymmetric(TreeNode root) {
    	if(root == null){
    		return true;
    	}
        return helper(root.left, root.right);
    }
    private boolean helper(TreeNode l, TreeNode r) {
    	if (l == null && r == null){
    		return true;
    	}
    	if (l ==null || r == null){
    		return false;
    	}
    	if (l.val != r.val){
    		return false;
    	}
        return helper(l.left, r.right) && helper(l.right, r.left);
    }
    
    //iterative way
    public boolean isSymmetricBSF(TreeNode root) {
    	if(root == null){
    		return true;
    	}
    	if (root.left == null && root.right == null){
    		return true;
    	}
    	if (root.left == null || root.right == null){
    		return false;
    	}
    	LinkedList<TreeNode> q1 = new LinkedList<TreeNode>();
    	LinkedList<TreeNode> q2 = new LinkedList<TreeNode>();
    	q1.offer(root.left);
    	q2.offer(root.right);
    	while(!q1.isEmpty() && !q2.isEmpty()){
    		TreeNode n1 = q1.poll();
    		TreeNode n2 = q2.poll();
    		if(n1.val != n2.val){
    		    return false;
    		}
    		if(n1.left != null && n2.right == null || n1.left == null && n2.right != null){
    			return false;
    		}
    		if(n1.right != null && n2.left == null || n1.right == null && n2.left != null){
    			return false;
    		}
    		if(n1.left != null && n2.right != null){
    			q1.offer(n1.left);
    			q2.offer(n2.right);
    		}
    		if(n1.right != null && n2.left != null){
    			q1.offer(n1.right);
    			q2.offer(n2.left);
    		}
    	}
    	return true;
    }
    
    

}
 class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	  }