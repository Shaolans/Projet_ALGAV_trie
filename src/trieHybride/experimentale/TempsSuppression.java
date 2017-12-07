package trieHybride.experimentale;

import java.util.ArrayList;
import java.util.List;

import trie.file.TrieLoadFile;
import trie.file.TriePrintFile;
import trieHybride.implementation.TrieHybride;
import trieHybride.implementation.TrieHybridePrimitive;
import trieHybride.interfaces.ITrieHybride;

public class TempsSuppression {

	public static void main(String[] args) {
		List<String> time = new ArrayList<>();
		ITrieHybride root = new TrieHybride();
		int pas = 1000;
		int max = 0;
		int cpt = 0;
		Long debut;
		Long fin;
		Long elapsed;
		TrieLoadFile.loadFileShakespeareTrieHybride(root);
		List<String> supp = TrieHybridePrimitive.listeMots(root);
		max = supp.size();
		
		while(max >= cpt+pas){
			root = new TrieHybride();
			TrieLoadFile.loadFileShakespeareTrieHybride(root);
			debut = System.currentTimeMillis();
			for(int i = 0; i < cpt; i++){
				TrieHybridePrimitive.supression(root, supp.get(i));
			}
			System.out.println(cpt);
			fin = System.currentTimeMillis();
			elapsed = fin - debut;
			time.add(cpt+" "+elapsed);
			cpt += pas;
		}
		
		TriePrintFile.printFile("comparaison/trie_hybride_temps_suppression.txt", time);
	}

}
