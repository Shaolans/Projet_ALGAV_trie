package trieHybride.test;

import trie.file.TrieLoadFile;
import trie.visualizer.TrieVisualizer;
import trieHybride.implementation.TrieHybridePrimitive;
import trieHybride.interfaces.ITrieHybride;

public class TrieHybrideTest {

	public static void main(String[] args) {
		ITrieHybride root = TrieHybridePrimitive.initTrieHybride();
		/*
		TrieHybridePrimitive.ajoutMot("n", root, 0);
		TrieHybridePrimitive.ajoutMot("en", root, 1);
		TrieHybridePrimitive.ajoutMot("or", root, 2);
		TrieHybridePrimitive.ajoutMot("a", root, 3);
		TrieHybridePrimitive.ajoutMot("genial", root, 4);
		TrieHybridePrimitive.ajoutMot("mot", root, 5);
		TrieHybridePrimitive.ajoutMot("de", root, 6);
		TrieHybridePrimitive.ajoutMot("du", root, 7);
		TrieHybridePrimitive.ajoutMot("der", root, 8);*/
		//System.out.println(root.getfc());
		//System.out.println(root.getfc().getfd());
		//TrieHybridePrimitive.parcours2(root);
		//System.out.println(TrieHybridePrimitive.recherche(root, "motar"));
		//System.out.println(TrieHybridePrimitive.recherche(root, "quel"));
		//System.out.println(TrieHybridePrimitive.recherche(root, "motorise"));
		//root = TrieLoadFile.loadFileTrieHybride("files/exemple_de_base");
		//root = TrieLoadFile.loadFileShakespeareTrieHybride(null);
		
		TrieHybridePrimitive.ajoutMot("ling", root, 0);
		TrieHybridePrimitive.ajoutMot("lune", root, 1);
		TrieHybridePrimitive.ajoutMot("li", root, 2);
		TrieHybridePrimitive.ajoutMot("la", root, 1);
		System.out.println(TrieHybridePrimitive.comptageMots(root));
		System.out.println(TrieHybridePrimitive.comptageNil(root));
		System.out.println(TrieHybridePrimitive.hauteur(root));
		//System.out.println(TrieHybridePrimitive.listeMots(root));
		System.out.println(TrieHybridePrimitive.prefixe(root, "d"));
		System.out.println(TrieHybridePrimitive.profondeurMoyenne(root));
		
		
		TrieHybridePrimitive.supression(root, "ling");
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
