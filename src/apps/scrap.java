package apps;

public class scrap {
	public static void main(String argp[]){
		String x = "a";
		String y = "ab";
		System.out.println(y.contains(x));
		
		for(int g = 0; g<y.length();g++){
			if(y.charAt(g)==x.charAt(0)){
				System.out.println("true");
			}
		}
}
}