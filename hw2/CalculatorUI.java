public class CalculatorUI{
	public static void main(String[] args) {
    		while (true) {
    			String first= StdIn.readString();
    			if(first.equals("quit")){
    				return;
    			}
    			String second= StdIn.readString();
    			String third= StdIn.readString();
    			int x=Integer.parseInt(first);
    			int y=Integer.parseInt(third);
    			Calculator result =new Calculator();
    			if(second.equals("+")){
    				System.out.println(result.add(x,y));
    			}
    			else if(second.equals("*")){
    				System.out.println(result.multiply(x,y));
    			}
    		}
	}
}