package trie.conveter.test;

import patriciaTrie.structure.PatriciaTrie;
import trie.converter.PatToHybrid;
import trie.visualizer.TrieVisualizer;
import trieHybride.implementation.TrieHybride;
import trieHybride.implementation.TrieHybridePrimitive;

public class PatToHybridTest {
	
	public static void main(String[] args){
		
		PatriciaTrie p = new PatriciaTrie();
		TrieHybride th = new TrieHybride();
		TrieHybride bonTh = new TrieHybride();
		
		PatriciaTrie.ajouterMot(p, "amelo");
		PatriciaTrie.ajouterMot(p, "ami");
		PatriciaTrie.ajouterMot(p, "ameliorer");
		PatriciaTrie.ajouterMot(p, "amel");
		/*PatriciaTrie.ajouterMot(p, "salut");
		PatriciaTrie.ajouterMot(p, "salee");
		PatriciaTrie.ajouterMot(p, "sayonara");*/
		
		System.out.println("Patricia trie entré");
		
		System.out.println(p);
		
		th = (TrieHybride) PatToHybrid.patToHybridTrie(p, th);
		
		System.out.println("Trie hybride résultant"); 
		
		TrieVisualizer.visualizeTrieHybride(th);
		
		
		TrieHybridePrimitive.ajoutMot("amel", bonTh);
		TrieHybridePrimitive.ajoutMot("ameliorer", bonTh);
		TrieHybridePrimitive.ajoutMot("amelo", bonTh);
		TrieHybridePrimitive.ajoutMot("ami", bonTh);
		TrieHybridePrimitive.ajoutMot("salee", bonTh);
		TrieHybridePrimitive.ajoutMot("salut", bonTh);
		TrieHybridePrimitive.ajoutMot("sayonara", bonTh);

		
		System.out.println("\nBon trie hybride :");
		TrieVisualizer.visualizeTrieHybride(bonTh);
		
	}
	
}
