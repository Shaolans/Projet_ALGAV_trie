package patriciaTrie.test;

import patriciaTrie.structure.PatriciaTrie;

public class PrimitiveTest {
	
	public static void main(String[] args ){
		
		PatriciaTrie pt = new PatriciaTrie();
		
		pt.addWord("amel");
		pt.addWord("ami");
		pt.addWord("amical");
		pt.addWord("bob");
		pt.addWord("amicalement");
		
		System.out.println(pt);
		
		//System.out.println(pt.findWord("amel"));
		
	}
	
}
