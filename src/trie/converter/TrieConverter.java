package trie.converter;

import patriciaTrie.structure.PatriciaTrie;
import trieHybride.interfaces.ITrieHybride;

public class TrieConverter {

	
	public static PatriciaTrie convertIntoPatriciaTrie(ITrieHybride th) {
		return TrieConverter.convertIntoPatriciaTrieAux(th, "");
	}
	
	/*private static PatriciaTrie convertIntoPatriciaTrieAux(ITrieHybride th, String word) {
		if(th == null) return null;
		
		if(th.isLeaf()) {
			if(th.isWord()) {
				return new PatriciaTrie(word.length()+1, word+th.getChar(), true);
			}
			return null;
		}
		

		String current = word;
		ITrieHybride tmp = th;
		ITrieHybride tmpchild;
		PatriciaTrie newPat;
		PatriciaTrie pattable[];
		int charvalue;
		
		while(!tmp.existfg() && !tmp.existfd() && tmp.existfc() && !tmp.isWord()) {
			current += tmp.getChar();
			tmp = tmp.getfc();
		}
		
		
		current += tmp.getChar();
		if(tmp.isWord()) {
			if(tmp.isLeaf()) {
				return new PatriciaTrie(current.length(), current, true);
			}
			newPat = new PatriciaTrie(current.length(), current, false);
			pattable = newPat.getPatTries();
			
			tmpchild = tmp.getfg();
			charvalue = tmpchild.getChar();
			pattable[charvalue-97] = TrieConverter.convertIntoPatriciaTrieAux(tmpchild, word);
			
			tmpchild = tmp.getfc();
			charvalue = tmpchild.getChar();
			pattable[charvalue-97] = TrieConverter.convertIntoPatriciaTrieAux(tmpchild, word);
			
			tmpchild = tmp.getfd();
			charvalue = tmpchild.getChar();
			pattable[charvalue-97] = TrieConverter.convertIntoPatriciaTrieAux(tmpchild, word);
			
			return newPat;
		}
		
		newPat = new PatriciaTrie(current.length(), current, false);
		
		
		return null;
	}*/
	
	
	
	private static PatriciaTrie convertIntoPatriciaTrieAux(ITrieHybride th, String word) {
		if(th == null) return null;
		
		PatriciaTrie newPat = new PatriciaTrie(word.length(), word, false);
		PatriciaTrie pattable[] = newPat.getPatTries();
		
		if(th.isWord()) {
			pattable[26] = new PatriciaTrie(word.length(), word, true);
		}
		
		String current = word;
		ITrieHybride tmp = th;
		ITrieHybride tmpchild;
		int charvalue;
		System.out.println(th.getChar());
		while(!tmp.existfg() && !tmp.existfd() && tmp.existfc() && !tmp.isWord()) {
			current += tmp.getChar();
			tmp = tmp.getfc();
		}
		System.out.println(current+tmp.getChar());
		
		tmpchild = tmp.getfg();
		if(tmpchild != null) {
			charvalue = tmpchild.getChar();
			pattable[charvalue-97] = TrieConverter.convertIntoPatriciaTrieAux(tmpchild, current);
		}
		
		
		
		tmpchild = tmp.getfc();
		if(tmpchild != null) {
			charvalue = tmpchild.getChar();
			pattable[charvalue-97] = TrieConverter.convertIntoPatriciaTrieAux(tmpchild, current+tmpchild.getChar());
		}
		
		tmpchild = tmp.getfd();
		if(tmpchild != null) {
			charvalue = tmpchild.getChar();
			pattable[charvalue-97] = TrieConverter.convertIntoPatriciaTrieAux(tmpchild, current);
		}
		
		return newPat;
	}
}
