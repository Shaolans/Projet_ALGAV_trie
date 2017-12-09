package patriciaTrie.test;

import org.junit.Test;

import patriciaTrie.structure.PatriciaTrie;
import trie.file.TrieLoadFile;
import trie.visualizer.TrieVisualizer;

public class PatriaciaTrieTest {
	
	@Test
	public void testAjout2(){
		PatriciaTrie p = new PatriciaTrie();
		PatriciaTrie.ajouterMot(p, "youro");
		PatriciaTrie.ajouterMot(p, "you");
		PatriciaTrie.ajouterMot(p, "youri");
		//PatriciaTrie.ajouterMot(p, "yohu");
		PatriciaTrie.ajouterMot(p, "yan");
		PatriciaTrie.ajouterMot(p, "y");
		System.out.println(p);
		
		PatriciaTrie.supprimerMot(p, "yan");
		PatriciaTrie.supprimerMot(p, "y");

		PatriciaTrie.supprimerMot(p, "you");
		
		
		TrieVisualizer.visualizePatriciaTrie(p);
		
	}
}
