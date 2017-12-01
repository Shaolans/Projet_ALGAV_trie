package trieHybride.test;

import trie.file.TrieLoadFile;
import trie.visualizer.TrieVisualizer;
import trieHybride.implementation.TrieHybridePrimitive;
import trieHybride.interfaces.ITrieHybride;

public class TrieHybrideTest {

	public static void main(String[] args) {
		ITrieHybride root = TrieHybridePrimitive.initTrieHybride();
		
		/*TrieHybridePrimitive.ajoutMot("n", root);
		TrieHybridePrimitive.ajoutMot("en", root);
		TrieHybridePrimitive.ajoutMot("or", root);
		TrieHybridePrimitive.ajoutMot("a", root);
		TrieHybridePrimitive.ajoutMot("genial", root);
		TrieHybridePrimitive.ajoutMot("mot", root);
		TrieHybridePrimitive.ajoutMot("de", root);
		TrieHybridePrimitive.ajoutMot("du", root);
		TrieHybridePrimitive.ajoutMot("der", root);*/
		//System.out.println(root.getfc());
		//System.out.println(root.getfc().getfd());
		//TrieHybridePrimitive.parcours2(root);
		//System.out.println(TrieHybridePrimitive.recherche(root, "motar"));
		//System.out.println(TrieHybridePrimitive.recherche(root, "quel"));
		//System.out.println(TrieHybridePrimitive.recherche(root, "motorise"));
		//root = TrieLoadFile.loadFileTrieHybride("files/exemple_de_base");
		//root = TrieLoadFile.loadFileShakespeareTrieHybride(null);
		
		TrieHybridePrimitive.ajoutMot("ling", root);
		TrieHybridePrimitive.ajoutMot("lune", root);
		TrieHybridePrimitive.ajoutMot("li", root);
		TrieHybridePrimitive.ajoutMot("la", root);
		/*System.out.println(TrieHybridePrimitive.comptageMots(root));
		System.out.println(TrieHybridePrimitive.comptageNil(root));
		System.out.println(TrieHybridePrimitive.hauteur(root));*/
		//System.out.println(TrieHybridePrimitive.listeMots(root));
		System.out.println(TrieHybridePrimitive.prefixe(root, "li"));
		System.out.println(TrieHybridePrimitive.profondeurMoyenne(root));
		
		//TrieHybridePrimitive.supression(root, "ling");
		TrieVisualizer.visualizeTrieHybride(root);
		
		//TrieHybridePrimitive.supression(root, "genial");
		//System.out.println(TrieHybridePrimitive.listeMots(root));
		//System.out.println(root.getChar());
		/*for(String word: TrieLoadFile.loadWordsShakespeare()){
			if(!TrieHybridePrimitive.recherche(root, word)){
				System.out.println("PROBLEME");
			}
		}*/
		
		/*ITrieHybride tmp = root;
		tmp = tmp.getfc();
		tmp = tmp.getfc();
		tmp = tmp.getfc();
		System.out.println(tmp.getChar());
		System.out.println(tmp.getfg().getChar());*/
	}

}
