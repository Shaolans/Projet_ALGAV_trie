package patriciaTrie.experimentale;

import java.util.ArrayList;
import java.util.List;

import patriciaTrie.structure.PatriciaTrie;
import trie.file.TrieLoadFile;
import trie.file.TriePrintFile;

public class TrieHauteur {
	public static void main(String[] args) {
		List<String> shakespeare = TrieLoadFile.loadWordsShakespeare();
		List<String> depth = new ArrayList<>();
		int pas = 1000;
		int max = shakespeare.size();
		PatriciaTrie root = new PatriciaTrie();
		
		for(int i = 0; i < max; i++){
			PatriciaTrie.ajouterMot(root, shakespeare.get(i));
			if(i%pas == 0){
				depth.add(i+" "+PatriciaTrie.hauteur(root));
			}
		}
		
		
		TriePrintFile.printFile("comparaison/patricia_trie_hauteur.txt", depth);

	}
}
