package patriciaTrie.experimentale;

import java.util.ArrayList;
import java.util.List;

import patriciaTrie.structure.PatriciaTrie;
import trie.file.TrieLoadFile;
import trie.file.TriePrintFile;

public class TempsSuppression {
	public static void main(String[] args) {
		List<String> time = new ArrayList<>();
		PatriciaTrie root = null;
		int pas = 1000;
		int max = 0;
		int cpt = 0;
		Long debut;
		Long fin;
		Long elapsed;
		List<String> supp = TrieLoadFile.loadWordsShakespeare();
		max = supp.size();
		
		while(max >= cpt+pas){
			root = new PatriciaTrie();
			root = TrieLoadFile.loadFileShakespearePatriciaTrie();
			debut = System.currentTimeMillis();
			for(int i = 0; i < cpt; i++){
				PatriciaTrie.supprimerMot(root, supp.get(i));
			}
			System.out.println(cpt);
			fin = System.currentTimeMillis();
			elapsed = fin - debut;
			time.add(cpt+" "+elapsed);
			cpt += pas;
		}
		
		TriePrintFile.printFile("comparaison/patricia_trie_temps_suppression.txt", time);
	}
}
