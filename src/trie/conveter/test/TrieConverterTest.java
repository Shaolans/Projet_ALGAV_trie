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
		TrieHybridePrimitive.ajoutMot("mar", root, 4);
		TrieHybridePrimitive.ajoutMot("bonsoiree", root, 5);
		//TrieHybridePrimitive.ajoutMot("bonsoirea", root, 6);
		TrieHybridePrimitive.ajoutMot("bonsoireee", root, 7);
		
		PatriciaTrie rootpat = trie.converter.TrieConverter.convertIntoPatriciaTrie(root);
		//TrieVisualizer.visualizeTrieHybride(root);
		TrieVisualizer.visualizePatriciaTrie(rootpat);
	}
}