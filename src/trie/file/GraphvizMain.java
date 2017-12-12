package trie.file;

import patriciaTrie.structure.PatriciaTrie;
import trieHybride.implementation.TrieHybridePrimitive;
import trieHybride.interfaces.ITrieHybride;

public class GraphvizMain {

	public static void main(String[] args) {
		GraphvizMain.graphExempleDeBase();
		GraphvizMain.graphTest1();
		
	}
	
	
	public static void graphExempleDeBase() {
		ITrieHybride root = TrieHybridePrimitive.initTrieHybride();
		root = TrieLoadFile.loadFileTrieHybride("files/exemple_de_base");
		GraphvizPrint.printTrieHybrideGraph("graphviz/th_exemple_de_base.dot", root);
		
		PatriciaTrie pt = new PatriciaTrie();
		pt = TrieLoadFile.loadFilePatriciaTrie("files/exemple_de_base");
		GraphvizPrint.printPatriciaTrieGraph("graphviz/pt_exemple_de_base.dot", pt);
	}
	
	public static void graphTest1() {
		ITrieHybride root = TrieHybridePrimitive.initTrieHybride();
		TrieHybridePrimitive.ajoutMot("ceci", root);
		TrieHybridePrimitive.ajoutMot("est", root);
		TrieHybridePrimitive.ajoutMot("la", root);
		TrieHybridePrimitive.ajoutMot("representation", root);
		TrieHybridePrimitive.ajoutMot("du", root);
		TrieHybridePrimitive.ajoutMot("trie", root);
		TrieHybridePrimitive.ajoutMot("structure", root);
		TrieHybridePrimitive.ajoutMot("reprise", root);
		GraphvizPrint.printTrieHybrideGraph("graphviz/th_exemple_1.dot", root);
		
		PatriciaTrie pt = new PatriciaTrie();
		PatriciaTrie.ajouterMot(pt, "ceci");
		PatriciaTrie.ajouterMot(pt, "est");
		PatriciaTrie.ajouterMot(pt, "la");
		PatriciaTrie.ajouterMot(pt, "representation");
		PatriciaTrie.ajouterMot(pt, "du");
		PatriciaTrie.ajouterMot(pt, "trie");
		PatriciaTrie.ajouterMot(pt, "structure");
		PatriciaTrie.ajouterMot(pt, "reprise");
		GraphvizPrint.printPatriciaTrieGraph("graphviz/pt_exemple_1.dot", pt);
		
		
	}

}
