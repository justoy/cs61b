public class Max{
	public static int max(int[] numbers){
		int m=numbers[0];
		for(int i=1; i< numbers.length; ++i){
			if(m<numbers[i]){
				m=numbers[i];
			}
		}
		return m;
	}

	public static void main(String[] args){
		int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6}; 
		System.out.println(max(numbers));
	}
}