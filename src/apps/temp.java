package apps;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import javax.management.ValueExp;

import structures.Stack;

public class temp {
	private static  boolean isNumeric(String str)
    {
    	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<Integer> values = new Stack();
		ArrayList<ScalarSymbol> scalars;
		scalars= new ArrayList();
		ScalarSymbol a = new ScalarSymbol("a");
		a.value = 2;
		scalars.add(a);
		ScalarSymbol b = new ScalarSymbol("b");
		b.value =3;
		scalars.add(b);
		String myString= "a+b+a";
		String[] result = myString.split("(?<=[)(-+*\\[/])|(?=[)-\\[+\\]*/])");
		/*for(int i = 0; i<result.length; i++){
			System.out.println(result[i] );
		}
		if(isNumeric(result[result.length-1]))
			values.push(Integer.parseInt(result[result.length-1]));*/
		if (myString.matches("[0-9]+") && myString.length() > 2){
		System.out.println("true");
		}
		
		String scalarsname = "";
		String expr2 = "";
		String[] splitter = myString.split("(?<=[)(-+*\\[/])|(?=[)-\\[+\\]*/])");
		for(int g = 0; g<scalars.size(); g++){
			scalarsname+= scalars.get(g).name;
			
		}
		System.out.println(scalarsname);
		for(int i = 0; i<splitter.length; i++){
			if(scalarsname.contains(splitter[i])){
				System.out.println("yes");
				for(int z = 0; z <scalars.size(); z++){
					if(scalars.get(z).name.equals(splitter[i])){
						expr2+=scalars.get(z).value;
					}
			}
		}
			
			if("+/*-".contains(splitter[i])){
				expr2+=splitter[i];
			}
		}
		System.out.println(expr2);
		
	//	System.out.println(scalars.indexOf());
			//if(splitter[i].contains("+-*/")){
				
		
		
		/*
		for(int i = 0; i < scalars.size(); i++){
		if(scalars.get(i).name.equals("b")){
			System.out.println("yes");
		}
		}
		String delims = " \t*+-/()[]";
		System.out.println(result[result.length-2]);
		if(result[result.length-2].equals("+")||result[result.length-2].equals("-")||result[result.length-2].equals("*")||result[result.length-2].equals("/")){
			System.out.println("is has delims");
		}
		
		System.out.println(isNumeric("123"));*/
		
		
		
	}

	}
