package structures;

import java.util.ArrayList;

/**
 * This class implements a compressed trie. Each node of the tree is a CompressedTrieNode, with fields for
 * indexes, first child and sibling.
 * 
 * @author Sesh Venugopal
 *
 */
public class Trie {
	
	/**
	 * Words indexed by this trie.
	 */
	ArrayList<String> words;
	
	/**
	 * Root node of this trie.
	 */
	TrieNode root;
	
	/**
	 * Initializes a compressed trie with words to be indexed, and root node set to
	 * null fields.
	 * 
	 * @param words
	 */
	public Trie() {
		root = new TrieNode(null, null, null);
		words = new ArrayList<String>();
	}
	
	/**
	 * Inserts a word into this trie. Converts to lower case before adding.
	 * The word is first added to the words array list, then inserted into the trie.
	 * 
	 * @param word Word to be inserted.
	 */
	private static String prefix(String x, String y){
		
		if(x == null || y == null){
			return "";
		}
		
		int minstringlen = 0;
		
		if(x.length() < y.length()){
			minstringlen = x.length();
		}
		else{
			minstringlen = y.length();
		}
		
		for(int i = 0; i<minstringlen;i++){
			if(x.charAt(i) == y.charAt(i)){
				continue;
			}
			else{
				return x.substring(0, i);
			}
		}
		return x.substring(0, minstringlen);
	}
	
	private TrieNode lastelement(TrieNode x) {
	    if (x == null) {
	        return null;
	    }
	    if (x.sibling == null) {
	        return x;
	    }
	    return lastelement(x.sibling);
	}
	private TrieNode lastelementfc(TrieNode x) {
	    if (x == null) {
	        return null;
	    }
	    if (x.firstChild == null) {
	        return x;
	    }
	    return lastelement(x.firstChild);
	}
	private String hascommonprefix(ArrayList<String> words, String word){
	
		for(int i=0; i<words.size()-1;i++){;
			if(prefix(words.get(i), word).length()>0){
				return prefix(words.get(i), word);
			}
			
		}
		return "";
	}
	
	private String hascommonprefix2(ArrayList<String> words, String word){
		int max=0;
		String temp ="";
		for(int i=0; i<words.size()-1;i++){
			if(prefix(words.get(i), word).length()>0){
				if(prefix(words.get(i), word).length()>max){
				max =prefix(words.get(i), word).length();
				temp = prefix(words.get(i), word);
			}
			
		}
		
		
	}
		return temp;
	}
	
	private TrieNode nodehascommon(TrieNode x, String word){
		if(x==null){
			return null;
		}
		if(words.get(x.substr.wordIndex).contains(word)){
			return x; 
		}
		
		TrieNode result = null;
		if(x.sibling!=null){							//if it has siblings
			result = nodehascommon(x.sibling, word);
			if(result != null){
				return result;
			}
		}
		if(x.firstChild !=null){
			result = nodehascommon(x.firstChild, word);
			if(result!=null){
				return result;
			}
		}
		
		return null;
	}
	

	private TrieNode nodeequalsword(TrieNode x, String word){
		if(x==null){
			return null;
		}
		if(words.get(x.substr.wordIndex)==(word)){
			return x; 
		}
		
		TrieNode result = null;
		if(x.sibling!=null){							//if it has siblings
			result = nodeequalsword(x.sibling, word);
			if(result != null){
				return result;
			}
		}
		if(x.firstChild !=null){
			result = nodeequalsword(x.firstChild, word);
			if(result!=null){
				return result;
			}
		}
		
		return null;
	}
	
		//if(words.get(x.substr.wordIndex)==word&&(x.substr.endIndex+1)==word.length()){
	
	private TrieNode findnodehasprefix(TrieNode x, String prefix){
		TrieNode temp = null;
		for(int i=0; i<words.size();i++){
			if(words.get(i).contains(prefix)){
				System.out.println("I am here in findnodehasprefix"+words.get(i));
				temp = nodeequalsword(x ,words.get(i));
				break;
			}
		}
		return temp;
	}

	
	private TrieNode searchparent(TrieNode x, TrieNode target){
		
		if(x==target){
			return x;
		}
		
		
		TrieNode result = null;
		if(x.sibling!=null){							//if it has siblings
			result = searchparent(x.sibling, target);
			if(result != null){
				return x;
			}
		}
		if(x.firstChild !=null){
			result = searchparent(x.firstChild, target);
			if(result!=null){
				return x;
			}
		}
		
		return null;
	}
	private TrieNode search2(TrieNode x, String target){
		if(x==null){
			return null;
		}
		if(x.substr.wordIndex==words.indexOf(target)&&(x.substr.endIndex+1==target.length())){
			return x; 
		}
		
		TrieNode result = null;
		if(x.sibling!=null){							//if it has siblings
			result = search2(x.sibling, target);
			if(result != null){
				return result;
			}
		}
		if(x.firstChild !=null){
			result = search2(x.firstChild, target);
			if(result!=null){
				return result;
			}
		}
		
		return null;
	}
	private TrieNode search3(TrieNode x, String target){
		if(x==null){
			return null;
		}
		if(x.substr.wordIndex==words.indexOf(target)){
			return x; 
		}
		
		TrieNode result = null;
		if(x.sibling!=null){							//if it has siblings
			result = search3(x.sibling, target);
			if(result != null){
				return result;
			}
		}
		if(x.firstChild !=null){
			result = search3(x.firstChild, target);
			if(result!=null){
				return result;
			}
		}
		
		return null;
	}
	private ArrayList<String> search4(ArrayList<String> result, TrieNode x, String prefix){
		if(words.get(x.substr.wordIndex).contains(prefix)){
			result.add(words.get(x.substr.wordIndex));
		}
		if(x == null){
			return result;
		}
		if(x.firstChild!=null){
			return search4(result, x.firstChild, prefix);
		}
		if(x.sibling!=null){
			return search4(result, x.sibling, prefix);
		}
		return result;
	}
	
	private TrieNode search(TrieNode x, int target){
		if(x==null){
			return null;
		}
		if(x.substr.wordIndex==target){
			return x; 
		}
		
		TrieNode result = null;
		if(x.sibling!=null){							//if it has siblings
			result = search(x.sibling, target);
			if(result != null){
				return result;
			}
		}
		if(x.firstChild !=null){
			result = search(x.firstChild, target);
			if(result!=null){
				return result;
			}
		}
		
		return null;
	}
	
	private TrieNode searchbyindexes(TrieNode x, Indexes target){
		if(x==null){
			return null;
		}
		if(x.substr==target){
			return x; 
		}
		
		TrieNode result = null;
		if(x.sibling!=null){							//if it has siblings
			result = searchbyindexes(x.sibling, target);
			if(result != null){
				return result;
			}
		}
		if(x.firstChild !=null){
			result = searchbyindexes(x.firstChild, target);
			if(result!=null){
				return result;
			}
		}
		
		return null;
	}
	private String wordhascommon(String prefix){
		for(int i=0; i<words.size();i++){
			if(words.get(i).contains(prefix)){
				return words.get(i);
			}
		}
		return "";
	}
	private int searchparent2(TrieNode root, TrieNode x){
		if(root.firstChild==x){
			return root.substr.endIndex+1;
		}
	
		
		
		if(root.firstChild !=null){
			return(searchparent2(root.firstChild, x));
			
		}
		
		
		
		return 0;
	}
	private int searchparent3(TrieNode root, TrieNode x){
		if(root.firstChild==x){
			return root.substr.endIndex+1;
		}
		if(alreadyexist2(root, x)==true){
			return 0;
		}
		if(root.firstChild!=null){
			if(root.firstChild.sibling!=null){
				if(alreadyexist2(root.firstChild, x)==true){
					return root.substr.endIndex+1;
				}
			}
			else{
			return searchparent3(root.firstChild, x);
			}
		}
		
		if(root.sibling!=null){
			return searchparent3(root.sibling, x);
		}
//		if(root.firstChild==null&&root.sibling==null){
//			return null;
//		}
		
			
				
		
		return 0;
	}
	
	
	//need to fix searchparent, find the left most and it's parent
	private boolean alreadyexist(TrieNode root, Indexes x){ ///find if that node.substr is already in the trie!
		if(root.substr.equals(x)){
			return true;
		}
		if(root.firstChild!=null){
			return alreadyexist(root.firstChild, x);
		}
		
		if(root.sibling!=null){
			return alreadyexist(root.sibling, x);
		}
		return false;
	}
	private boolean alreadyexist2(TrieNode root, TrieNode x){ ///find if that node.substr is already in the trie!
		if(root.equals(x)){
			return true;
		}
	
		
		if(root.sibling!=null){
			return alreadyexist2(root.sibling, x);
		}
		return false;
	}
	
	private boolean isinarraylist(TrieNode x){
		return false;
	}
	public void insertWord(String word) {
		word = word.toLowerCase();
		words.add(word);
		
			if(root.firstChild == null){
				root.firstChild = new TrieNode(new Indexes(words.indexOf(word), (short)word.indexOf(word), (short)(word.length()-1)), null, null);
				}
				else if(root.firstChild!=null){
//					System.out.println("The word now is: "+word);
					//If the word has no common prefix________________________________________________________________________
					
					
					if(hascommonprefix(words, word).length()==0){
//						System.out.println("the prefix is empty, which means there is no common prefix");
					
						
						lastelement(root.firstChild).sibling = new TrieNode(new Indexes(words.indexOf(word), (short)word.indexOf(word), (short)(word.length()-1)), null, null);
						
					}
					else if(hascommonprefix(words, word).length()>0){
						String commonprefix = hascommonprefix2(words, word);
//						System.out.println("commonprefix is: "+commonprefix);
//						System.out.println("node has common prefix2: "+(nodehascommon(root.firstChild, commonprefix)));
//						System.out.println("word has common prefix: "+(wordhascommon(commonprefix)));
//						System.out.println("search2 of word has common prefix: "+search2(root.firstChild,wordhascommon(commonprefix)));
//						System.out.println("search2 of nodehascommon: "+search2(root.firstChild, words.get(nodehascommon(root.firstChild, commonprefix).substr.wordIndex)).substr);
//						System.out.println("search3 of nodehascommon: "+search3(root.firstChild, words.get(nodehascommon(root.firstChild, commonprefix).substr.wordIndex)).substr);
						
						TrieNode temp = search2(root.firstChild, words.get(nodehascommon(root.firstChild, commonprefix).substr.wordIndex));
						Indexes indtemp = new Indexes(nodehascommon(root.firstChild, commonprefix).substr.wordIndex,(short) searchparent2(root.firstChild, temp),(short)(((words.get(nodehascommon(root.firstChild, commonprefix).substr.wordIndex)).lastIndexOf(commonprefix))+commonprefix.length()-1) );
//						System.out.println("indtemp is: "+indtemp);
//						System.out.println(indtemp+" already exist? "+alreadyexist(root.firstChild, indtemp));
//						System.out.println("searchparent2 returns: "+searchparent2(root.firstChild, temp));
//						System.out.println("alreadyexist2 returns: "+alreadyexist2(root.firstChild, temp));
//						System.out.println("searchparent3 returns: "+searchparent3(root.firstChild, temp));
						
						
						
						if(alreadyexist(root.firstChild, indtemp)==false){
						temp.substr.wordIndex= nodehascommon(root.firstChild, commonprefix).substr.wordIndex;
						
						temp.substr.startIndex = (short) (searchparent3(root.firstChild, temp));
						temp.substr.endIndex = (short) (((words.get(nodehascommon(root.firstChild, commonprefix).substr.wordIndex)).lastIndexOf(commonprefix))+commonprefix.length()-1);
//						search2(root.firstChild, words.get(nodehascommon(root.firstChild, commonprefix).substr.wordIndex)).substr=new Indexes(nodehascommon(root.firstChild, commonprefix).substr.wordIndex, (short)((words.get(nodehascommon(root.firstChild, commonprefix).substr.wordIndex)).indexOf(commonprefix)) , (short)(((words.get(nodehascommon(root.firstChild, commonprefix).substr.wordIndex)).lastIndexOf(commonprefix))+commonprefix.length()-1));
						temp.firstChild = new TrieNode(new Indexes(nodehascommon(root.firstChild, commonprefix).substr.wordIndex, (short)(((words.get(nodehascommon(root.firstChild, commonprefix).substr.wordIndex)).lastIndexOf(commonprefix))+commonprefix.length()), (short)(words.get(nodehascommon(root.firstChild, commonprefix).substr.wordIndex).length()-1)), null, null);
						temp.firstChild.sibling =new TrieNode(new Indexes(words.indexOf(word), (short)search2(root.firstChild,wordhascommon(commonprefix)).substr.startIndex, (short)(word.length()-1)), null, null);;
						//lastelementfc(search3(root.firstChild, words.get(nodehascommon(root.firstChild, commonprefix).substr.wordIndex))).firstChild = new TrieNode(new Indexes(nodehascommon(root.firstChild, commonprefix).substr.wordIndex, (short)(((words.get(nodehascommon(root.firstChild, commonprefix).substr.wordIndex)).lastIndexOf(commonprefix))+commonprefix.length()), (short)(words.get(nodehascommon(root.firstChild, commonprefix).substr.wordIndex).length()-1)), null, null);

						}
						else if(alreadyexist(root.firstChild, indtemp)==true){
//							System.out.println(nodehascommon(root.firstChild, commonprefix));
							lastelement(nodehascommon(root.firstChild, commonprefix).firstChild).sibling =new TrieNode(new Indexes(words.indexOf(word), (short)search2(root.firstChild,wordhascommon(commonprefix)).substr.startIndex, (short)(word.length()-1)), null, null);
						}
						
						
						//search2(root.firstChild,wordhascommon(commonprefix)).sibling = new TrieNode(new Indexes(words.indexOf(word), (short)search2(root.firstChild,wordhascommon(commonprefix)).substr.startIndex, (short)(word.length()-1)), null, null);
						
						//System.out.println(search2(root.firstChild, words.get(nodehascommon(root.firstChild, commonprefix).substr.wordIndex)).substr);
						//search2(root.firstChild, words.get(nodehascommon(root.firstChild, commonprefix).substr.wordIndex)).sibling = new TrieNode(new Indexes(words.indexOf(word), (short)(word.length()-commonprefix.length()), (short)(word.length()-1)), null, null );
						

					}
					
					
					}
					}
					

					
					//If the word has common prefix___________________________________________________________________________
//					else{
//						String commonprefix = prefix(words.get(root.firstChild.substr.wordIndex), word);
//						//System.out.println("the triblets so far is: "+root.firstChild.substr);
//						//System.out.println("the index of the previous word is: "+words.indexOf(words.get(root.firstChild.substr.wordIndex)));
//						//System.out.println("the commonprefix begins at index: "+words.get(root.firstChild.substr.wordIndex).indexOf(commonprefix));
//						int endcommon = (words.get(root.firstChild.substr.wordIndex).indexOf(commonprefix)+commonprefix.length()-1);
//						//System.out.println("the end of commonprefix is at the index: "+endcommon);
//						root.firstChild.substr = new Indexes(words.indexOf(words.get(root.firstChild.substr.wordIndex)), (short)words.get(root.firstChild.substr.wordIndex).indexOf(commonprefix), (short)endcommon);			
//						root.firstChild.firstChild= new TrieNode(new Indexes(words.indexOf(words.get(root.firstChild.substr.wordIndex)), (short)(endcommon+1), (short)((words.get(root.firstChild.substr.wordIndex).length())-endcommon)), null, null);
//						root.firstChild.firstChild.sibling = new TrieNode(new Indexes(words.indexOf(word), (short)(endcommon+1), (short)((words.get(root.firstChild.substr.wordIndex).length())-endcommon)), null, null);
//						
//						
//						
//					}
				
				
		
		
	
	
	/**
	 * Given a string prefix, returns its "completion list", i.e. all the words in the trie
	 * that start with this prefix. For instance, if the tree had the words bear, bull, stock, and bell,
	 * the completion list for prefix "b" would be bear, bull, and bell; for prefix "be" would be
	 * bear and bell; and for prefix "bell" would be bell. (The last example shows that a prefix can be
	 * an entire word.) The order of returned words DOES NOT MATTER. So, if the list contains bear and
	 * bell, the returned list can be either [bear,bell] or [bell,bear]
	 * 
	 * @param prefix Prefix to be completed with words in trie
	 * @return List of all words in tree that start with the prefix, order of words in list does not matter.
	 *         If there is no word in the tree that has this prefix, null is returned.
	 */
	public ArrayList<String> completionList(String prefix) {
		/** COMPLETE THIS METHOD **/
		ArrayList<String> result = new ArrayList<String>();
		result = search4(result, root.firstChild, prefix);
		
		for(int i = 0; i<result.size(); i++){
			for(int g=i+1; g<result.size(); g++){
				if(result.get(i).equals(result.get(g))){
					result.remove(g);
					g--;
				}
			}
		}
		
		return result;
				
		
		
	}
	
	
	public void print() {
		print(root, 1, words);
	}
	
	private static void print(TrieNode root, int indent, ArrayList<String> words) {
		if (root == null) {
			return;
		}
		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}
		
		if (root.substr != null) {
			System.out.println("      " + words.get(root.substr.wordIndex));
		}
		
		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}
		System.out.print(" ---");
		System.out.println("(" + root.substr + ")");
		
		for (TrieNode ptr=root.firstChild; ptr != null; ptr=ptr.sibling) {
			for (int i=0; i < indent-1; i++) {
				System.out.print("    ");
			}
			System.out.println("     |");
			print(ptr, indent+1, words);
		}
	}
 }
