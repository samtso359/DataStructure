package linear;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

public class parenMatch {
	
	public static boolean parenMatch(String expression){
		Stack<Character> stack = new Stack<Character> (); 	// create a stack
		for (int i = 0; i < expression.length(); i++){
			char c = expression.charAt(i);
			if(c == '('){
				stack.push(c);
			}
			else if(c == ')'){
				try{		//doing try and catch, to prevent the case where the expression only has ), ex )4+5)*3 
				stack.pop();
				} catch(NoSuchElementException e){
					System.out.println(e.getMessage());
					return false;
				}
			}
		}
		return stack.isEmpty(); //true if is empty, which also means the stack doesn't have any parenthesis in it
		//which also means parenthesis matched
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br = 
				new BufferedReader(new InputStreamReader(System.in)); //System.in reads from keyboard
		System.out.print("Enter the expression: ");
		try {
			String expr = br.readLine();
			if(parenMatch(expr)){
				System.out.println("Matched");
			}else{
				System.out.println("Not matched");
			}
		} catch (IOException e){       // in case something wrong with input
			e.printStackTrace();
		}
		}
		
	

}
