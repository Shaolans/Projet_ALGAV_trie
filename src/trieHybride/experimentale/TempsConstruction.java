package trieHybride.experimentale;

import java.util.ArrayList;
import java.util.List;

import trie.file.TrieLoadFile;
import trie.file.TriePrintFile;
import trieHybride.implementation.TrieHybride;
import trieHybride.implementation.TrieHybridePrimitive;
import trieHybride.interfaces.ITrieHybride;

public class TempsConstruction {

	public static void main(String[] args) {
		List<String> shakespeare = TrieLoadFile.loadWordsShakespeare();
		List<String> time = new ArrayList<>();
		int pas = 1000;
		int max = shakespeare.size();
		int cpt = 0;
		ITrieHybride root = null;
		Long debut;
		Long fin;
		Long elapsed;
		
		while(max >= cpt+pas){
			root = new TrieHybride();
			debut = System.currentTimeMillis();
			for(int i = 0; i < cpt; i++){
				TrieHybridePrimitive.ajoutMot(shakespeare.get(i), root);
			}
			fin = System.currentTimeMillis();
			elapsed = fin - debut;
			time.add(cpt+" "+elapsed);
			cpt += pas;
		}
		
		TriePrintFile.printFile("comparaison/trie_hybride_temps_construction.txt", time);
	}

}
