package 자료형;

public class CharEx {
	public static void main(String[] args) {
		char c = 'A'; // 65 와 같다
		System.out.println("c의 값 = " + c);
		c = 66;
		System.out.println("c의 값 = " + c);
		c = '\u0043'; // 내부적으로 아스키코드로 바꿔준다 => 67
		System.out.println("c의 값 = " + c);
	}
}