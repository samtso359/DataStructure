package apps;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import javax.management.ValueExp;

import structures.Stack;

public class Expression {

	/**
	 * Expression to be evaluated
	 */
	String expr;                
    
	/**
	 * Scalar symbols in the expression 
	 */
	ArrayList<ScalarSymbol> scalars;   
	
	/**
	 * Array symbols in the expression
	 */
	ArrayList<ArraySymbol> arrays;
    
    /**
     * String containing all delimiters (characters other than variables and constants), 
     * to be used with StringTokenizer
     */
    public static final String delims = " \t*+-/()[]";
    
    /**
     * Initializes this Expression object with an input expression. Sets all other
     * fields to null.
     * 
     * @param expr Expression
     */
    public Expression(String expr) {
        this.expr = expr;
    }

    /**
     * Populates the scalars and arrays lists with symbols for scalar and array
     * variables in the expression. For every variable, a SINGLE symbol is created and stored,
     * even if it appears more than once in the expression.
     * At this time, values for all variables are set to
     * zero - they will be loaded from a file in the loadSymbolValues method.
     */
    public void buildSymbols() {
        arrays = new ArrayList<ArraySymbol>();
        scalars = new ArrayList<ScalarSymbol>();

        String temp = "";
        for (int i = 0; i < expr.length(); i++)
        {
            temp = temp + expr.charAt(i);
            if (expr.charAt(i) == '[')
            {
                temp = temp + "~";
            }
        }
        StringTokenizer str = new StringTokenizer(temp, " \t*+-/()]~");

        while (str.hasMoreElements())
        {
            String x = str.nextToken();
            if (x.charAt(x.length()-1) == '[')
            {
                arrays.add(new ArraySymbol(x.substring(0, x.length()-1)));
            }
            else
            {
                if (!Character.isLetter(x.charAt(0)))
                    continue;
                else
                    scalars.add(new ScalarSymbol(x));
            }

        }


    }
    
    /**
     * Loads values for symbols in the expression
     * 
     * @param sc Scanner for values input
     * @throws IOException If there is a problem with the input 
     */
    public void loadSymbolValues(Scanner sc) 
    throws IOException {
        while (sc.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine().trim());
            int numTokens = st.countTokens();
            String sym = st.nextToken();
            ScalarSymbol ssymbol = new ScalarSymbol(sym);
            ArraySymbol asymbol = new ArraySymbol(sym);
            int ssi = scalars.indexOf(ssymbol);
            int asi = arrays.indexOf(asymbol);
            if (ssi == -1 && asi == -1) {
            	continue;
            }
            int num = Integer.parseInt(st.nextToken());
            if (numTokens == 2) { // scalar symbol
                scalars.get(ssi).value = num;
            } else { // array symbol
            	asymbol = arrays.get(asi);
            	asymbol.values = new int[num];
                // following are (index,val) pairs
                while (st.hasMoreTokens()) {
                    String tok = st.nextToken();
                    StringTokenizer stt = new StringTokenizer(tok," (,)");
                    int index = Integer.parseInt(stt.nextToken());
                    int val = Integer.parseInt(stt.nextToken());
                    asymbol.values[index] = val;              
                }
            }
        }
    }
    
    
    /**
     * Evaluates the expression, using RECURSION to evaluate subexpressions and to evaluate array 
     * subscript expressions.
     * 
     * @return Result of evaluation
     */
   /* private String[] tokenizer(){
    	 String regx = "";
  	   	 String expr2 = expr;
		    char[] ca = regx.toCharArray();
		    for (char c : ca) {
		        expr2 = expr2.replace(""+c, " ");
		    }
		 //   System.out.println(expr2);
		 //   System.out.println(expr);
		 //   System.out.println("The string is:" + expr);
		    StringBuilder temper = new StringBuilder(expr2);
		    int length = temper.length();
		    for (int i = 0; i < length; i ++){
		       if (temper.charAt(i) == '['){
		          temper.insert(i+1," ");
		       }
		    }
		    String result = temper.toString();
		   // System.out.println("the expression is:"+result);
		    
		    
	String[] temp = result.split(" ");
		return temp;
    	} */
   
  
    
  /*  private float operation(int a,int b, String c){
    	float result = a;
    	if(c.equals("+")){
    		return result+=b;
    	}
    	else if(c.equals("-")){
    		return result-=b;
    	}
    	else if(c.equals("*")){
    		return result*=b;
    	}
    	else if(c.equals("/")){
    		return result/=b;
    	}
		return result;
    	
    }*/
    
   
    private  static boolean isNumeric(String str)
    {
    	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    	}
    private static boolean containsVariable(String s){  
        if (s != null && !s.isEmpty())
        {
            for (char c : s.toCharArray())
            {
                if (Character.isLetter(c))
                {
                    return true;
                }
            }
        }

        return false;
    }
    private static boolean containsparathesis(String s){
    	boolean fin = false;
		
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i)=='('||s.charAt(i)==')'){
		fin = true;
		
	}
			}
	


    	return fin;
    }
    private static boolean containsbrackets(String s){
    	boolean fin = false;
    	
    	for(int i = 0; i<s.length(); i++){
    		if(s.charAt(i)=='['||s.charAt(i)==']'){
    			fin = true;
    		}
    	}
    	return fin;
    }
    
   private String scalarsTranslate(String x){
    	System.out.println("THe string being translated is: "+ x);
    	String scalarsname = "";
		String expr2 = "";
		String[] splitter = x.split("(?<=[)(-+*\\[/])|(?=[)-\\[+\\]*/])");
		for(int g = 0; g<scalars.size(); g++){
			scalarsname+= scalars.get(g).name;
			
		}
		//System.out.println(scalarsname);
		for(int i = 0; i<splitter.length; i++){
			if(scalarsname.contains(splitter[i])){
				//System.out.println("yes");
				for(int z = 0; z <scalars.size(); z++){
					if(scalars.get(z).name.equals(splitter[i])){
						//System.out.println("the scalars value for "+scalars.get(z).name+" is:"+scalars.get(z).value);
						//System.out.println("expr2 has: "+expr2);
						expr2+=scalars.get(z).value;
					//	System.out.println("expr2 after adding has: "+expr2);
					}}}
			
		
			
			if("+/*-".contains(splitter[i])){
				//System.out.println("expr2 before adding operators has: "+expr2);
				expr2+=splitter[i];
				//System.out.println("expr2 after adding operators has: "+expr2);
			}
			if("1234567890".contains(splitter[i])){
				expr2+=splitter[i];
			}
		}
		System.out.println("after translate: "+expr2);
    	return expr2	;	
    }
    private static boolean parenMatch(String expression){
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
    public float evaluate() {
    		if(!containsVariable(expr)&&!containsparathesis(expr)){
    			if (!expr.contains("+") && !expr.contains("-") && !expr.contains("*") && !expr.contains("/")) {
    	    	return Float.parseFloat(expr);
    	    }
    	    int i;

    	    for (i = expr.length() - 1; i >= 0; i--) {
    	        if (expr.charAt(i) == '+' || expr.charAt(i) == '-') {
    	            break;
    	        }
    	    }
    	    if (i < 0) {
    	      
    	        for (i = expr.length() - 1; i >= 0; i--) {
    	            if (expr.charAt(i) == '*' || expr.charAt(i) == '/') {
    	                break;
    	            }
    	        }
    	    }

    	    Expression r1 = new Expression(expr.substring(0, i));
    	    Expression r2 = new Expression(expr.substring(i + 1, expr.length()));

    	    float result = 0;

    	    switch (expr.charAt(i)) {
    	        case '+':
    	            result = r1.evaluate() + r2.evaluate();
    	            break;
    	        case '-':
    	            result = r1.evaluate() - r2.evaluate();
    	            break;
    	        case '*':
    	            result = r1.evaluate() * r2.evaluate();
    	            break;
    	        case '/':
    	            float right = r2.evaluate();
    	            if (right == 0) 
    	            {
    	                System.out.println("CANNOT DIVIDE BY ZERO");
    	                System.exit(1);
    	            } else {
    	                result = r1.evaluate() / right;
    	            }
    	            break;
    	    }
    	    return result;
    		}
    	    
    		else if(containsparathesis(expr)){
    		//WHEN IT CONTAINS PARATHESIS---------------------------------------------	
    		
    			int lastoccurofopen = expr.lastIndexOf('(');
    			int closeindex = 0;
    			String inpara = "";
    			String temp = "";
    			for (int i = lastoccurofopen; i < expr.length(); i++){
    				if(expr.charAt(i)==(')')){
    					closeindex = i;
    					break;
    				}
    				if(expr.charAt(i)!=(')')&&i>lastoccurofopen){
    					inpara+=expr.charAt(i);
    				}
    			}
    			Expression inthepara = new Expression(inpara);
    			inthepara.scalars = this.scalars;
    			inthepara.arrays = this.arrays;
    			System.out.println("successfully decleared");
    			System.out.println(inthepara.evaluate());
    			System.out.println("right now expr is: "+expr);
    			temp = expr.substring(0, lastoccurofopen)+(int)inthepara.evaluate()+expr.substring(closeindex+1);
    			System.out.println("got pass assign temp: "+temp);
    			this.expr = temp;
    			return evaluate();
    			/*while(containsparathesis(expr)){
    			//arrayA[(arrayA[varx/(2+0)]+vary)*2-(varx-vary)]
    			String para = "";
    			int index = expr.indexOf('(');
    			int index2 = expr.indexOf(')');
    			String end = expr.substring(0, index)+expr.substring(index2+1);
    			for(int i = 0; i < expr.length(); i++){
    				
    				if(expr.charAt(i)=='('){
    					para = expr.substring(index+1, index2);
    					
    				}
    			}
    			
    			System.out.println("The expression in () is: "+para);
    			
    		
    			
    			int temperary = (calculate(scalarsTranslate(para)));
    		//	System.out.println("The values in the paranthesis are: "+t.evaluate());
    			this.expr = end.substring(0,index)+temperary+end.substring(index);
    			System.out.println("The expr now is: "+ this.expr);
    			return evaluate();
    		
    		}*/
    		}
    		
    		//WHEN IT CONTAINS BRACKETS!!!!!!!!____________________________________________
    		else if(containsbrackets(expr)){
    			System.out.println("it contains brackets!!!!!!");
    			System.out.println("the expr is: "+expr);
    			int lastoccurofopen = expr.lastIndexOf('[');
    			System.out.println("The [ we are working on is at index: "+lastoccurofopen);
    			int closeindex = 0;
    			String inpara = "";
    			String temp = "";
    			System.out.println("before product inprar is: "+inpara);
    			for (int i = lastoccurofopen; i < expr.length(); i++){
    				if(expr.charAt(i)==(']')){
    					closeindex = i;
    					System.out.println("The close ] index is at: "+closeindex);
    					break;
    					
    				}
    				
    				
    			/*	if(expr.charAt(i)!=(']')&&i>lastoccurofopen&&i<=closeindex){
    				
    					inpara+=expr.charAt(i);
    				}*/
    			}
    			inpara=expr.substring(lastoccurofopen+1, closeindex);
    			System.out.println("product inpara here is: "+inpara);
    			Expression xx = new Expression(inpara);
    			xx.scalars = this.scalars;
    			xx.arrays = this.arrays;
    			int valueofinpara = (int) xx.evaluate();
    			System.out.println("the value of inpara here is: "+valueofinpara);
    			// I GOT THE VALUE BETWEEN []s 
    			
    			//trying to reconstruct the expression
    			System.out.println("trying to reconstruct the expression");
    			String newexpr = expr;
    			System.out.println("at this point the expr is: "+expr);
    	        
    	        System.out.println("The new copy of expr looks like: "+newexpr);
    	        StringTokenizer str = new StringTokenizer(newexpr, " \t*+-/()]~0123456789");
    	        String varname = "";
    	        String finexpr = "";
    	        int arrayvalue =0;
    	        
    	        /////WORKING ON HERE_________________________________________________________
    	        while (str.hasMoreElements())
    	        {
    	            String x = str.nextToken();
    	            System.out.println("The string toenizer looks like: "+x);
    	            if(x.indexOf('[')>=0){			
    	            	varname = x.substring(0, x.indexOf('[')); 
    	            	
    	            }
    	           

    	        }
    	        System.out.println("Before fix the varname is: "+varname);
    	        String[] fix = varname.split("\\[");
    	        for(int ji = 0; ji<fix.length;ji++){
    	        	System.out.println("What is in the splitted varname: "+fix[ji]);
    	        	varname = fix[ji];
    	        	
    	        }
    	        System.out.println("Obtained varname: "+varname);
    	        int varnameindex = expr.lastIndexOf(varname);
    	        System.out.println("the last index of ocurr the varname is: "+varnameindex);
    	        System.out.println("the expr here is: "+expr);
    	        System.out.println("the arrays size here is: "+arrays.size());
    	        for(int i = 0; i<arrays.size(); i++){
    	        	System.out.println("right now looping at: "+i+"the name is: "+ arrays.get(i).name);
    	        	if(arrays.get(i).name.equals(varname)){
    	        		System.out.println("the varname matches the string name of arrays, which is: "+ arrays.get(i).name);
    	        		System.out.println("the value of inpara here is: "+ valueofinpara);
    	        		System.out.println("and its value is: "+arrays.get(i).values[valueofinpara]);
    	        		arrayvalue = arrays.get(i).values[valueofinpara];
    	        		break;
    	        	}
    	        	
    	        }
    	        System.out.println("after translating, here in the array's final value: "+arrayvalue);
    	        System.out.println("before chaning expr: "+expr);
    	       
    	        int newlastocc = expr.lastIndexOf('[');
    	        System.out.println("the index of [ is :"+newlastocc+"! 	and close index is: "+closeindex);
    	        finexpr = expr.substring(0, expr.lastIndexOf(varname))+arrayvalue+expr.substring(closeindex+1);
    	        System.out.println("the product expr is: "+finexpr);
    	        this.expr = finexpr;
    			//remove the Arrays[]
    			
    			return evaluate();
    		}
    		
    		
    		
    		
    		//Only scalars left--------------------------------------------------------
    		else{
    			
    			System.out.println("THe string being translated is: "+ expr);
    			
    	    	String scalarsname = "";
    			String expr2 = "";
    			
    			//String[] splitter = expr.split("(?<=[)(-+*\\[/])|(?=[)-\\[+-\\]*/])");
  
    			String[] splitter = expr.split("(?<=[-+*/])|(?=[-+*/])");
    			for(int j =0; j<splitter.length;j++){
    				System.out.println("What is is the splitter: "+ splitter[j]);
    			}
    				expr="";
    			System.out.println("got pass expr = ''");
    			
    			
    			for(int g = 0; g<scalars.size(); g++){
    				scalarsname+= scalars.get(g).name;
    				scalarsname+=" ";
    			}
    			for(int h =0;h< splitter.length;h++){
    				System.out.println("What is in the splitter: "+splitter[h]);
    			}
    			System.out.println("The scalar valariables are: "+scalarsname);
    			// I HAVE TO REMOVE DUPS FROM scalarsname
    			scalarsname=scalarsname.replaceAll("(\\b\\w+\\b)-(?=.*\\b\\1\\b)", "");
    			System.out.println("The scalar valariables are after replce all: "+scalarsname);
    			/*char[] chars = scalarsname.toCharArray();
    			Set<Character> charSet = new LinkedHashSet<Character>();
    			for (char c : chars) {
    			    charSet.add(c);
    			}

    			StringBuilder sb = new StringBuilder();
    			for (Character character : charSet) {
    			    sb.append(character);
    			}
    			String maybe = sb.toString();
    			System.out.println("After removing dubs for scalarsname: "+maybe);*/
    			
    			
    			
    			for(int i = 0; i<splitter.length; i++){
    				
    				//if(scalarsname.contains(splitter[i])){
    				System.out.println("what is splitter[i] right now: "+splitter[i]);
    				System.out.println("what is maybe?= "+scalarsname);
    				System.out.println("Is maybe contains splitter[i], maybe.contains(splitter[i])? "+scalarsname.contains(splitter[i]));
    				String a = scalarsname;
    				String b = splitter[i];
    				if(a.contains(b)){
    				System.out.println("i am in");
    					int split = scalarsname.split(splitter[i],-1).length-1;
    					System.out.println("the number of occurence of: "+splitter[i]+" is "+split);
    					System.out.println("yes");
    					System.out.println("the lengeth of scalarsname: "+scalarsname.length());
    					System.out.println("the szie of the scalars: "+scalars.size());
    					for(int z = 0; z <scalars.size(); z++){
    						System.out.println("the scalars string is "+ scalars.get(z).name);
    						
    						System.out.println("scalars.get(z).name.equals(splitter[i]) "+scalars.get(z).name.equals(splitter[i]));
    						if(scalars.get(z).name.equals(splitter[i])&&split>0){
    						
    							//System.out.println("the scalars value for "+scalars.get(z).name+" is:"+scalars.get(z).value);
    							//System.out.println("expr2 has: "+expr2);
    							expr+=scalars.get(z).value;
    							split--;
    						
    							System.out.println("added scalar value: "+scalars.get(z).value);
    							break;
    						//	System.out.println("expr2 after adding has: "+expr2);
    						}}}
    			
    				
    				if("+/*-".contains(splitter[i])){
    					//System.out.println("expr2 before adding operators has: "+expr2);
    					expr+=splitter[i];
    					System.out.println(splitter[i]+" operator has been added");
    					//System.out.println("expr2 after adding operators has: "+expr2);
    				}
    				 if(isNumeric(splitter[i])){
    					System.out.println(splitter[i]+" integer has been added");
    					expr+=splitter[i];
    				}
    				
    				
    				
    			}
    			System.out.println("Successfully translated: " +expr);
    			return evaluate();
    		}

    }
    

    /**
     * Utility method, prints the symbols in the scalars list
     */
  

    public void printScalars() {
        for (ScalarSymbol ss: scalars) {
            System.out.println(ss);
        }
    }
    
    /**
     * Utility method, prints the symbols in the arrays list
     */
    public void printArrays() {
    		for (ArraySymbol as: arrays) {
    			System.out.println(as);
    		}
    }

}
