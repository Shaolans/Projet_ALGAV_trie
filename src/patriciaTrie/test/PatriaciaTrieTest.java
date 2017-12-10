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
		PatriciaTrie.ajouterMot(p, "yohu");
		PatriciaTrie.ajouterMot(p, "yan");
		PatriciaTrie.ajouterMot(p, "y");
		System.out.println(PatriciaTrie.prefixe(p, "yo"));
		
		PatriciaTrie p2 = new PatriciaTrie();
		PatriciaTrie.ajouterMot(p2, "amelo");
		PatriciaTrie p3 = new PatriciaTrie();
		PatriciaTrie.ajouterMot(p3, "ameliorer");
		PatriciaTrie.ajouterMot(p3, "ameli");
		
		TrieVisualizer.visualizePatriciaTrie(p3);
		TrieVisualizer.visualizePatriciaTrie(PatriciaTrie.fusion(p3, p2));
		
		
		
		
	}
}
