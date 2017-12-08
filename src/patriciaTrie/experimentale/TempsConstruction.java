package patriciaTrie.experimentale;

import java.util.ArrayList;
import java.util.List;

import patriciaTrie.structure.PatriciaTrie;
import trie.file.TrieLoadFile;
import trie.file.TriePrintFile;

public class TempsConstruction {
	public static void main(String[] args) {
		List<String> shakespeare = TrieLoadFile.loadWordsShakespeare();
		List<String> time = new ArrayList<>();
		int pas = 1000;
		int max = shakespeare.size();
		int cpt = 0;
		PatriciaTrie root = null;
		Long debut;
		Long fin;
		Long elapsed;
		
		while(max >= cpt+pas){
			root = new PatriciaTrie();
			debut = System.currentTimeMillis();
			for(int i = 0; i < cpt; i++){
				PatriciaTrie.ajouterMot(root, shakespeare.get(i));
			}
			fin = System.currentTimeMillis();
			elapsed = fin - debut;
			time.add(cpt+" "+elapsed);
			cpt += pas;
		}
		
		TriePrintFile.printFile("comparaison/patricia_trie_temps_construction.txt", time);
	}
}
