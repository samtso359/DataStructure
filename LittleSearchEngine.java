package search;

import java.io.*;
import java.util.*;

/**
 * This class encapsulates an occurrence of a keyword in a document. It stores the
 * document name, and the frequency of occurrence in that document. Occurrences are
 * associated with keywords in an index hash table.
 * 
 * @author Sesh Venugopal
 * 
 */
class Occurrence {
	/**
	 * Document in which a keyword occurs.
	 */
	String document;
	
	/**
	 * The frequency (number of times) the keyword occurs in the above document.
	 */
	int frequency;
	
	/**
	 * Initializes this occurrence with the given document,frequency pair.
	 * 
	 * @param doc Document name
	 * @param freq Frequency
	 */
	public Occurrence(String doc, int freq) {
		document = doc;
		frequency = freq;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "(" + document + "," + frequency + ")";
	}
}

/**
 * This class builds an index of keywords. Each keyword maps to a set of documents in
 * which it occurs, with frequency of occurrence in each document. Once the index is built,
 * the documents can searched on for keywords.
 *
 */
public class LittleSearchEngine {
	
	/**
	 * This is a hash table of all keywords. The key is the actual keyword, and the associated value is
	 * an array list of all occurrences of the keyword in documents. The array list is maintained in descending
	 * order of occurrence frequencies.
	 */
	HashMap<String,ArrayList<Occurrence>> keywordsIndex;
	
	/**
	 * The hash table of all noise words - mapping is from word to itself.
	 */
	HashMap<String,String> noiseWords;
	
	/**
	 * Creates the keyWordsIndex and noiseWords hash tables.
	 */
	public LittleSearchEngine() {
		keywordsIndex = new HashMap<String,ArrayList<Occurrence>>(1000,2.0f);
		noiseWords = new HashMap<String,String>(100,2.0f);
	}
	
	/**
	 * This method indexes all keywords found in all the input documents. When this
	 * method is done, the keywordsIndex hash table will be filled with all keywords,
	 * each of which is associated with an array list of Occurrence objects, arranged
	 * in decreasing frequencies of occurrence.
	 * 
	 * @param docsFile Name of file that has a list of all the document file names, one name per line
	 * @param noiseWordsFile Name of file that has a list of noise words, one noise word per line
	 * @throws FileNotFoundException If there is a problem locating any of the input files on disk
	 */
	public void makeIndex(String docsFile, String noiseWordsFile) 
	throws FileNotFoundException {
		// load noise words to hash table
		Scanner sc = new Scanner(new File(noiseWordsFile));
		while (sc.hasNext()) {
			String word = sc.next();
			noiseWords.put(word,word);
		}
		
		// index all keywords
		sc = new Scanner(new File(docsFile));
		while (sc.hasNext()) {
			String docFile = sc.next();
			HashMap<String,Occurrence> kws = loadKeyWords(docFile);
			mergeKeyWords(kws);
		}
		
	}

	/**
	 * Scans a document, and loads all keywords found into a hash table of keyword occurrences
	 * in the document. Uses the getKeyWord method to separate keywords from other words.
	 * 
	 * @param docFile Name of the document file to be scanned and loaded
	 * @return Hash table of keywords in the given document, each associated with an Occurrence object
	 * @throws FileNotFoundException If the document file is not found on disk
	 */
	public HashMap<String,Occurrence> loadKeyWords(String docFile) 
	throws FileNotFoundException {
		HashMap<String,Occurrence> kws = new HashMap<String,Occurrence>();
		Scanner sc = new Scanner(new File(docFile));
		while (sc.hasNext()) {
			String word = sc.next();
			if(getKeyWord(word)!=null&&!kws.containsKey(getKeyWord(word))){
				Occurrence temp = new Occurrence(docFile, 1);
				kws.put(getKeyWord(word), temp);
			}
			else if(getKeyWord(word)!=null&&kws.containsKey(getKeyWord(word))){
				kws.get(getKeyWord(word)).frequency++;
			}
		}
//		System.out.println("successfully loaded key words");
//		System.out.println("The kws set is: "+kws.entrySet());
		return kws;
	}
	
	/**
	 * Merges the keywords for a single document into the master keywordsIndex
	 * hash table. For each keyword, its Occurrence in the current document
	 * must be inserted in the correct place (according to descending order of
	 * frequency) in the same keyword's Occurrence list in the master hash table. 
	 * This is done by calling the insertLastOccurrence method.
	 * 
	 * @param kws Keywords hash table for a document
	 */
	//private Occurrence compare(Occurrence x, Occurrence y){
		//if(x.frequency>=y.frequency){
		//return x;
		//}
	//return y;
	//}
	public void mergeKeyWords(HashMap<String,Occurrence> kws) {
		Set<String> keys = kws.keySet();  // Set is of type String since keys are Strings
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			if(keywordsIndex.get(key)== null){
				ArrayList<Occurrence> temp = new ArrayList<Occurrence>();
				temp.add(kws.get(key));
				keywordsIndex.put(key, temp);
			}
			else{
				keywordsIndex.get(key).add(kws.get(key));
				insertLastOccurrence(keywordsIndex.get(key));
				
			}
			
		}
		
	}
	
	/**
	 * Given a word, returns it as a keyword if it passes the keyword test,
	 * otherwise returns null. A keyword is any word that, after being stripped of any
	 * TRAILING punctuation, consists only of alphabetic letters, and is not
	 * a noise word. All words are treated in a case-INsensitive manner.
	 * 
	 * Punctuation characters are the following: '.', ',', '?', ':', ';' and '!'
	 * 
	 * @param word Candidate word
	 * @return Keyword (word without trailing punctuation, LOWER CASE)
	 */
	public String getKeyWord(String word) {
		int wordlength = word.length();
		String punc = ".,?:;!";
		while(wordlength>0){
			if(punc.contains(word.charAt(word.length()-1)+"")){
				word=word.substring(0, word.length()-1);
			}
			wordlength--;}
		for(int i = 0; i<word.length(); i++){
		
			if(punc.contains(word.charAt(i)+"")){
				//System.out.println("Checking if it contains punc...");
				//System.out.println("the index is: "+i +" is a puncuation");
				return null;
			}
			}
		if(noiseWords.containsKey(word.toLowerCase())){
			//System.out.println("the word is in noiseWords!");
			return null;
		}
		//System.out.println("the word now is: "+word);
		//System.out.println("It is good");
		return word.toLowerCase();
	}
	
	/**
	 * Inserts the last occurrence in the parameter list in the correct position in the
	 * same list, based on ordering occurrences on descending frequencies. The elements
	 * 0..n-2 in the list are already in the correct order. Insertion of the last element
	 * (the one at index n-1) is done by first finding the correct spot using binary search, 
	 * then inserting at that spot.
	 * 
	 * @param occs List of Occurrences
	 * @return Sequence of mid point indexes in the input list checked by the binary search process,
	 *         null if the size of the input list is 1. This returned array list is only used to test
	 *         your code - it is not used elsewhere in the program.
	 */
	public ArrayList<Integer> insertLastOccurrence(ArrayList<Occurrence> occs) {
		int key = occs.size()-1;
		
		if(occs.size() == 1){
		return null;
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		int lo = 0;
		int hi = occs.size()-2;
		while(lo <= hi){
			int mid = (lo + hi)/2;
			result.add(mid);
			if(occs.get(mid).frequency> occs.get(key).frequency){
				lo = mid + 1;
			}
			if(occs.get(mid).frequency< occs.get(key).frequency){
				hi = mid -1;
			}
			
			if(occs.get(mid).frequency == occs.get(key).frequency){
				break;
			}
		}
	
		
	
		Collections.sort(occs, new Comparator<Occurrence>(){
			public int compare(Occurrence x, Occurrence y){
				return ((Integer)y.frequency).compareTo((Integer)x.frequency);
			}
		});
		
		
		return result;
	}
	
	/**
	 * Search result for "kw1 or kw2". A document is in the result set if kw1 or kw2 occurs in that
	 * document. Result set is arranged in descending order of occurrence frequencies. (Note that a
	 * matching document will only appear once in the result.) Ties in frequency values are broken
	 * in favor of the first keyword. (That is, if kw1 is in doc1 with frequency f1, and kw2 is in doc2
	 * also with the same frequency f1, then doc1 will appear before doc2 in the result. 
	 * The result set is limited to 5 entries. If there are no matching documents, the result is null.
	 * 
	 * @param kw1 First keyword
	 * @param kw1 Second keyword
	 * @return List of NAMES of documents in which either kw1 or kw2 occurs, arranged in descending order of
	 *         frequencies. The result size is limited to 5 documents. If there are no matching documents,
	 *         the result is null.
	 */
	public ArrayList<String> top5search(String kw1, String kw2) {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<Occurrence> list1 = keywordsIndex.get(kw1);
		ArrayList<Occurrence> list2 = keywordsIndex.get(kw2);
		ArrayList<Occurrence> temp = keywordsIndex.get(kw1);
		temp.addAll(keywordsIndex.get(kw2));
		Collections.sort(temp, new Comparator<Occurrence>(){
			public int compare(Occurrence x, Occurrence y){
				return ((Integer)y.frequency).compareTo((Integer)x.frequency);
			}
		});
		
		//System.out.println("before remove dups: "+temp);
		for(int i = 0; i<temp.size(); i++){
			for(int g=i+1; g<temp.size(); g++){
				if(temp.get(i).document.equals(temp.get(g).document)){
					temp.remove(g);
					g--;
				}
			}
		}
		//System.out.println("after remove dups: "+temp);
		if(temp.size()>5){
			for(int g=0; g<5; g++){
				result.add(temp.get(g).document);
			}
		}
		if(temp.size() <=5){
		for(int i=0; i<temp.size(); i++){
			result.add(temp.get(i).document);
		}
		}
		//System.out.println("the finale result list is: "+ result);
		
		
		
		
		return result;
	}
}
