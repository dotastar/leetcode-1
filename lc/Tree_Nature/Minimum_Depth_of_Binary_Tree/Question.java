package Minimum_Depth_of_Binary_Tree;

import java.util.LinkedList;


public class Question {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//http://blog.csdn.net/linhuanmars/article/details/19660209
	//http://blog.csdn.net/linhuanmars/article/details/39024195
	/*
	 * 要注意因为是取左右子树小的深度，但是有一种情况是不计入深度的，
	 * 就是比如左子树彻底为空时，这种情况我们不会认为深度就是0，因为左边并没有叶子，按照定义我们是要找叶子结点的最小深度。
	 * 所以需要对于左右是否为空做一个额外的判断。
	 */
	//recursive way
    public int minDepth(TreeNode root) {
		if (root == null){
    		return 0;
    	}
    	if(root.left == null){
    		return minDepth(root.right)+1;
    	}
    	if(root.right == null){
    		return minDepth(root.left)+1;
    	}
    	else{
    		return Math.min(minDepth(root.left), minDepth(root.right))+1; 
    	}
    }
    
    //iterative way
    //以下三个【注】，主要是要考虑边界情况，比如输入是{0},
    public int minDepthBSF(TreeNode root) {
    	if (root == null){
    		return 0;
    	}
    	int lastNum = 1;
    	int curNum = 0;
    	int level = 1;//【注】关于level的初始值(如果只有一个root， level是1)
    	LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.offer(root);
    	while(!queue.isEmpty()){
    		TreeNode node = queue.poll();
    		//【注意】当前node有任何一个孩子是叶子节点（左右孩子都是null），就可以返回当前level了
    		if (node.left == null && node.right == null){
    			return level;
    		}
    		lastNum--;//【注】关于lastNum--放置的位置
    		if (node.left != null){
    			queue.offer(node.left);
    			curNum++;
    		}
    		if (node.right != null){
    			queue.offer(node.right);
    			curNum++;
    		}
    		if (lastNum == 0){
    			lastNum = curNum;
    			curNum = 0;
    			level++;
    		}
    	}
    	return level;//【注】关于return的value
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}