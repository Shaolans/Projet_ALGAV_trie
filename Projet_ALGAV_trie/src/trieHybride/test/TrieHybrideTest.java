package trieHybride.test;

import trieHybride.file.TrieHybrideLoadFile;
import trieHybride.implementation.TrieHybridePrimitive;
import trieHybride.interfaces.ITrieHybride;

public class TrieHybrideTest {

	public static void main(String[] args) {
		ITrieHybride root = TrieHybridePrimitive.initTrieHybride();
		/*TrieHybridePrimitive.ajoutMot("n", root, 0);
		TrieHybridePrimitive.ajoutMot("en", root, 0);
		TrieHybridePrimitive.ajoutMot("appel", root, 0);
		TrieHybridePrimitive.ajoutMot("a", root, 1);
		TrieHybridePrimitive.ajoutMot("genial", root, 2);
		TrieHybridePrimitive.ajoutMot("mot", root, 2);
		TrieHybridePrimitive.ajoutMot("de", root, 2);
		TrieHybridePrimitive.ajoutMot("du", root, 2);
		TrieHybridePrimitive.ajoutMot("der", root, 2);*/
		//System.out.println(root.getfc());
		//System.out.println(root.getfc().getfd());
		//TrieHybridePrimitive.parcours2(root);
		//System.out.println(TrieHybridePrimitive.recherche(root, "motar"));
		//System.out.println(TrieHybridePrimitive.recherche(root, "quel"));
		//System.out.println(TrieHybridePrimitive.recherche(root, "motorise"));
		root = TrieHybrideLoadFile.loadFile("files/exemple_de_base", null);
		
		//System.out.println(TrieHybridePrimitive.comptageMots(root));
		//System.out.println(TrieHybridePrimitive.comptageNil(root));
		//System.out.println(TrieHybridePrimitive.hauteur(root));
		//System.out.println(TrieHybridePrimitive.listeMots(root));
		//System.out.println(TrieHybridePrimitive.prefixe(root, "d"));
		//System.out.println(TrieHybridePrimitive.profondeurMoyenne(root));
		
		//TrieHybridePrimitive.supression(root, "A");
		System.out.println(TrieHybridePrimitive.listeMots(root));
		//System.out.println(root.getChar());
		
		
		/*ITrieHybride tmp = root;
		tmp = tmp.getfc();
		tmp = tmp.getfc();
		tmp = tmp.getfc();
		System.out.println(tmp.getChar());
		System.out.println(tmp.getfg().getChar());*/
	}

}
