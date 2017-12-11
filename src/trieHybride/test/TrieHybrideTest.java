package trieHybride.test;

import java.util.ArrayList;
import java.util.Arrays;

import trie.file.TrieLoadFile;
import trie.file.TriePrintFile;
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
		
		/*TrieHybridePrimitive.ajoutMot("ling", root);
		TrieHybridePrimitive.ajoutMot("lune", root);
		TrieHybridePrimitive.ajoutMot("li", root);
		TrieHybridePrimitive.ajoutMot("la", root);*/
		/*System.out.println(TrieHybridePrimitive.comptageMots(root));
		System.out.println(TrieHybridePrimitive.comptageNil(root));
		System.out.println(TrieHybridePrimitive.hauteur(root));*/
		//System.out.println(TrieHybridePrimitive.listeMots(root));
		/*System.out.println(TrieHybridePrimitive.prefixe(root, "li"));
		System.out.println(TrieHybridePrimitive.profondeurMoyenne(root));
		TrieVisualizer.visualizeTrieHybride(root);
		TrieHybridePrimitive.supression(root, "ling");
		TrieHybridePrimitive.supression(root, "li");
		TrieHybridePrimitive.supression(root, "la");
		TrieHybridePrimitive.supression(root, "lune");
		TrieVisualizer.visualizeTrieHybride(root);*/
		
		
		/*TrieHybridePrimitive.ajoutMot("th", root);
		TrieHybridePrimitive.ajoutMot("bar", root);
		TrieHybridePrimitive.ajoutMot("ar", root);
		TrieHybridePrimitive.ajoutMot("ze", root);
		TrieHybridePrimitive.ajoutMot("ur", root);
		TrieHybridePrimitive.ajoutMot("ve", root);
		TrieHybridePrimitive.ajoutMot("wa", root);
		TrieHybridePrimitive.ajoutMot("xe", root);
		
		TrieHybridePrimitive.ajoutMot("doef", root);
		TrieHybridePrimitive.ajoutMot("dob", root);
		TrieHybridePrimitive.ajoutMot("doc", root);
		TrieHybridePrimitive.ajoutMot("dof", root);
		TrieHybridePrimitive.ajoutMot("doh", root);
		TrieHybridePrimitive.ajoutMot("dox", root);
		TrieHybridePrimitive.ajoutMot("doz", root);*/
				
		//TrieHybridePrimitive.balanceTrieHybride(root);
		
		/*System.out.println();
		System.out.println(root.getfg());
		System.out.println(root.getfd());
		System.out.println(root.getfd().getfd());
		System.out.println(root.getfd().getfg());*/
		
		
		/*
		TrieHybridePrimitive.supression(root,"t");
		TrieHybridePrimitive.supression(root,"th");
		TrieHybridePrimitive.supression(root,"the");
		TrieHybridePrimitive.supression(root,"them");
		TrieHybridePrimitive.supression(root,"themeselves");
		System.out.println(TrieHybridePrimitive.recherche(root, "theme"));
		TrieHybridePrimitive.supression(root,"theme");
		System.out.println(TrieHybridePrimitive.recherche(root, "theme"));
		*/
		//TrieVisualizer.visualizeTrieHybride(root);
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