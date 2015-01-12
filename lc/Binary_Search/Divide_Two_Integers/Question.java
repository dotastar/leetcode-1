package Divide_Two_Integers;

public class Question {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question q = new Question();
		System.out.println(q.divide(-2147483648, -1));
		//System.out.println(Integer.MIN_VALUE);
		//System.out.println(Math.abs((long)Integer.MIN_VALUE));
		//System.out.println((long)Math.abs(Integer.MIN_VALUE));

		
	}
	
	/**
	 * https://oj.leetcode.com/problems/divide-two-integers/ 
	 * Divide two integers  without using multiplication, division and mod operator.
	 * If it is overflow, return MAX_INT.
	 */
	
	//http://www.cnblogs.com/yuzhangcmu/p/4049170.html
	public int divide(int dividend, int divisor) {
		long a = Math.abs((long)dividend); // 【注】在这里必须先取long再abs，否则int的最小值abs后也是原值
		long b = Math.abs((long)divisor);
		long result = 0; // result不能用int 否则当输入为(-2147483648, -1)时，result += count累加到最后一次时会溢出。（result初值为0，因为不确定被除数是否比除数绝对值大）
		while (a >= b) { // 这里必须是= 因为相等时也可以减
			for (long deduct = b, count = 1; a >= deduct; deduct <<= 1, count <<= 1) { // 【注】count初值为1，若为0则左移无意义
				a -= deduct;
				result += count;
			}
		} 
		result = (((dividend ^ divisor) >> 31) & 1) == 1 ? -result : result; // 通过判断dividend和divisor符号位是否相同，来判断商的正负
		if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
			return Integer.MAX_VALUE;
		}
		return (int)result;
	}
	
	//O(logn)
	//http://blog.csdn.net/linhuanmars/article/details/20024907
/*	public int divide(int dividend, int divisor) {
		//边缘情况
    	if (dividend == 0){ //被除数为0
    		return 0;
    	}
    	if (divisor == 0){ //除数为0
    		return Integer.MAX_VALUE;
    	}
		int result = 0;
		
		 * 对于int类型最小的整数比最大的整数绝对值大1， 所以如果要取绝对值进行统一处理， 
		 * 那么就要单独处理最小整数的情况， 下面代码的做法是把它加一个除数的绝对值，让被除数以后可以取绝对值。
		 
		if (dividend == Integer.MIN_VALUE){//process min int value to avoid exceed boundary when "Math.abs()" it 
			dividend += Math.abs(divisor);
			result = 1;
		}
		if (divisor ==  Integer.MIN_VALUE){//如果dividend == Integer.MIN_VALUE，则返回1，否则其他情况返回0
			return result;
		}
		//简便方法判断dividend和divisor是否同号。
		boolean isNeg = (dividend^divisor)>>>31 == 1; // any difference between >> and >>> here? Yes! see below:
		//http://docs.oracle.com/javase/tutorial/java/nutsandbolts/op3.html
		//"The unsigned right shift operator ">>>" shifts a zero into the leftmost position, while the leftmost position after ">>" depends on sign extension."
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);
		int digit = 0;
		//计算商中含有最大的2的指数次幂的指数值为digit
		while(divisor <= dividend>>1){
			divisor <<= 1;
			digit++;
		}
		//从被除数中每次减去除数
		while(digit>=0){
			//只要当前被除数中含有1个除数，就做如下操作
			if(dividend-divisor>=0){
				dividend -= divisor; //被除数中减去当前除数
				result += 1<<digit; //result累加当前2的digit次幂
			}
			digit--; //2的幂指数减1
			divisor >>= 1; //除数减半
		}
		return isNeg ? -result : result; //恢复符号
	}*/

}
