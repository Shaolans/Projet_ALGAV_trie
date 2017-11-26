package trie.conveter.test;

import patriciaTrie.structure.PatriciaTrie;
import trieHybride.interfaces.ITrieHybride;

public class TrieConverterTest {
	public static void main(String[] args) {
		ITrieHybride root = trie.file.TrieLoadFile.loadFileTrieHybride("files/exemple_de_base");
		PatriciaTrie rootpat = trie.converter.TrieConverter.convertIntoPatriciaTrie(root);
		System.out.println("Test");
	}
}