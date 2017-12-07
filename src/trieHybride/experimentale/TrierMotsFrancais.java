package trieHybride.experimentale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import trie.file.TrieLoadFile;
import trie.file.TriePrintFile;

public class TrierMotsFrancais {

	public static void main(String[] args) {
		List<String> l = TrieLoadFile.loadWords("files/listemotfr_entier.txt");
		List<String> l2 = new ArrayList<>();
		boolean trouve = false;
		
		Collections.shuffle(l);
		
		for(String m: l){
			for(String m2: l2){
				if(m2.length() == m.length()){
					trouve = true;
				}
			}
			if(!trouve){
				l2.add(m);
			}
			trouve=false;
		}
		
		Collections.sort(l2, (a,b)->(a.length()<b.length()?-1:1));
		
		TriePrintFile.printFile("files/listemotfr_par_taille.txt", l2);
	}

}
