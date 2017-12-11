package trie.file;

import patriciaTrie.structure.PatriciaTrie;
import trieHybride.implementation.TrieHybridePrimitive;
import trieHybride.interfaces.ITrieHybride;

public class GraphvizMain {

	public static void main(String[] args) {
		ITrieHybride root = TrieHybridePrimitive.initTrieHybride();
		root = TrieLoadFile.loadFileTrieHybride("files/exemple_de_base");
		GraphvizPrint.printTrieHybrideGraph("graphviz/th_exemple_de_base.dot", root);
		
		PatriciaTrie pt = new PatriciaTrie();
		pt = TrieLoadFile.loadFilePatriciaTrie("files/exemple_de_base");
		GraphvizPrint.printPatriciaTrieGraph("graphviz/pt_exemple_de_base.dot", pt);
		
	}

}
