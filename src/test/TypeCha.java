package test;
public class TypeCha{
	public static void main(String[] args){
		Object str = new String("12546546");
		if(str instanceof Integer)
			System.out.println("erro");
		System.out.println("true");
	}
}
