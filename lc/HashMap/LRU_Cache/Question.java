package LRU_Cache;

import java.util.HashMap;


public class Question {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		LRUCache ca = new LRUCache(1);
		ca.set(2,1);
		System.out.println(ca.get(2));
	}


}
//最新访问（添加）的node放在尾巴；最旧的从头删除。
//http://blog.csdn.net/linhuanmars/article/details/21310633
class LRUCache {
	//Node class(doubly LinkedList)
	public class Node{
		int key;
		int val;
		Node next;
		Node pre;
		Node(int k, int v){
			this.key = k;
			this.val = v;
		}
	}
	
	//private variables
	private HashMap<Integer, Node> map; //<key, node>即key以及key对应的node(其中node中包涵了所有信息包括key, val, pre, next)
	private int capacity;
	private int num;
	private Node first;
	private Node last;
	
	//constructor
	public LRUCache(int capacity) {
		this.capacity = capacity;
		num = 0;
		first = null;
		last = null;
		map = new HashMap<Integer, Node>();
	}
  
  public int get(int key) {
		Node node = map.get(key);
		if (node == null) {
			return -1;
		} else if (node != last) {// because if node == last, we don't need to move node to last
			// to connect node's pre to node's next;
			if (node == first) {
				first = first.next;
			} else {
				node.pre.next = node.next;
			}
			// to connect node's next to node's pre
			node.next.pre = node.pre;
			// make node the new last
			last.next = node;
			node.pre = last;
			node.next = null;
			last = node;
		}
		return node.val;
	}
  
	public void set(int key, int value) {
		Node node = map.get(key);
		// if node already exists, no need to worry about exceed cache capacity
		if (node != null) {
			// update node's value;
			node.val = value;
			// update node's position similar as getter()
			if (node != last) {// because if node == last, we don't need to move
								// node to last
				// to connect node's pre to node's next;
				if (node == first) {
					first = first.next;
				} else {
					node.pre.next = node.next;
				}
				// to connect node's next to node's pre
				node.next.pre = node.pre;
				// make node the new last
				last.next = node;
				node.pre = last;
				node.next = null;
				last = node;
			}
		} else {
			// need to make a new node and need to concern capacity issue
			Node newNode = new Node(key, value);
			// reach capacity, then remove first and put newNode to last
			if (num >= capacity) {
				// wrong! should remove key!
				// map.remove(first);
				map.remove(first.key);
				first = first.next;
				if (first != null) {
					first.pre = null;
				} else {
					last = null;// an empty cache(capacity is 1 for this scenario)
				}
				num--;// until now, we should have space for newNode again
			}
			// empty cache
			if (first == null || last == null) {
				first = newNode;
			} else {// put newNode at the end
				last.next = newNode;
			}
			// connect newNode to original last;
			newNode.pre = last;
			last = newNode;
			map.put(key, newNode);// add new key-node couple to map
			num++; // num increase;
		}
	}
}