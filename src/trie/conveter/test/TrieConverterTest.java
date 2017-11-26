package trie.conveter.test;

import patriciaTrie.structure.PatriciaTrie;
import trie.visualizer.TrieVisualizer;
import trieHybride.implementation.TrieHybride;
import trieHybride.implementation.TrieHybridePrimitive;
import trieHybride.interfaces.ITrieHybride;

public class TrieConverterTest {
	public static void main(String[] args) {
		ITrieHybride root = new TrieHybride();
		TrieHybridePrimitive.ajoutMot("bonjour", root, 0);
		TrieHybridePrimitive.ajoutMot("bonsoir", root, 1);
		TrieHybridePrimitive.ajoutMot("bonneannee", root, 2);
		TrieHybridePrimitive.ajoutMot("marcher", root, 3);
		PatriciaTrie rootpat = trie.converter.TrieConverter.convertIntoPatriciaTrie(root);
		TrieVisualizer.visualizePatriciaTrie(rootpat);
	}
}