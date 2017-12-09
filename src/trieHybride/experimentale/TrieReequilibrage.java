package trieHybride.experimentale;

import java.util.ArrayList;
import java.util.List;

import trie.file.TrieLoadFile;
import trie.file.TriePrintFile;
import trieHybride.implementation.TrieHybride;
import trieHybride.implementation.TrieHybridePrimitive;
import trieHybride.interfaces.ITrieHybride;

public class TrieReequilibrage {

	public static void main(String[] args) {
		List<String> shakespeare = TrieLoadFile.loadWordsShakespeare();
		List<String> depth = new ArrayList<>();
		int pas = 1000;
		int max = shakespeare.size();
		ITrieHybride root = new TrieHybride();
		
		for(int i = 0; i < max; i++){
			TrieHybridePrimitive.ajoutMot(shakespeare.get(i), root);
			if(i%pas == 0){
				TrieHybridePrimitive.balanceTrieHybride(root);
				depth.add(i+" "+TrieHybridePrimitive.hauteur(root));
			}
		}
		
		
		TriePrintFile.printFile("comparaison/trie_hybride_reequilibrage_hauteur.txt", depth);

	}

}
