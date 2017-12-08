package patriciaTrie.experimentale;

import java.util.ArrayList;
import java.util.List;

import patriciaTrie.structure.PatriciaTrie;
import trie.file.TrieLoadFile;
import trie.file.TriePrintFile;

public class TempsAjout {
	public static void main(String[] args) {
		List<String> time = new ArrayList<>();
		List<String> addword = TrieLoadFile.loadWords("files/listemotfr_par_taille.txt");
		PatriciaTrie root = new PatriciaTrie();
		Long debut;
		Long fin;
		Long elapsed;
		
		root = TrieLoadFile.loadFileShakespearePatriciaTrie();
		
		for(int i = 0; i < addword.size(); i++){
			debut = System.currentTimeMillis();
			PatriciaTrie.ajouterMot(root, addword.get(i));
			fin = System.currentTimeMillis();
			elapsed = fin - debut;
			time.add(addword.get(i).length()+" "+elapsed);
			
		}

		
		TriePrintFile.printFile("comparaison/patricia_trie_temps_ajout_mot.txt", time);
	}
}
