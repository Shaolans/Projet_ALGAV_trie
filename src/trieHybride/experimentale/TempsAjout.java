package trieHybride.experimentale;

import java.util.ArrayList;
import java.util.List;

import trie.file.TrieLoadFile;
import trie.file.TriePrintFile;
import trieHybride.implementation.TrieHybride;
import trieHybride.implementation.TrieHybridePrimitive;
import trieHybride.interfaces.ITrieHybride;

public class TempsAjout {
	
	public static void main(String[] args) {
		List<String> time = new ArrayList<>();
		List<String> addword = TrieLoadFile.loadWords("files/listemotfr_par_taille.txt");
		ITrieHybride root = new TrieHybride();
		Long debut;
		Long fin;
		Long elapsed;
		
		TrieLoadFile.loadFileShakespeareTrieHybride(root);
		
		for(int i = 0; i < addword.size(); i++){
			debut = System.currentTimeMillis();
			TrieHybridePrimitive.ajoutMot(addword.get(i), root);
			fin = System.currentTimeMillis();
			elapsed = fin - debut;
			time.add(addword.get(i).length()+" "+elapsed);
			
		}

		
		TriePrintFile.printFile("comparaison/trie_hybride_temps_ajout_mot.txt", time);
	}
}
