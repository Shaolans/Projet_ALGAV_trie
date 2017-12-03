package trie.conveter.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import patriciaTrie.structure.PatriciaTrie;
import trie.converter.TrieConverter;
import trie.visualizer.TrieVisualizer;
import trieHybride.implementation.TrieHybride;
import trieHybride.implementation.TrieHybridePrimitive;
import trieHybride.interfaces.ITrieHybride;

public class TrieConverterTest  {
	
	@Test
	public void hybrideToPatTest(){
		ITrieHybride th = new TrieHybride("salut");
		TrieHybridePrimitive.ajoutMot("amel", th);
		TrieHybridePrimitive.ajoutMot("ameliorer", th);
		TrieHybridePrimitive.ajoutMot("ami", th);
		TrieHybridePrimitive.ajoutMot("salee", th);
		TrieHybridePrimitive.ajoutMot("sayonara", th);
		
		
		PatriciaTrie p = TrieConverter.hybrideTrieToPatricia(th);
		
		PatriciaTrie bonPat = new PatriciaTrie();
		PatriciaTrie.ajouterMot(bonPat, "amel");
		PatriciaTrie.ajouterMot(bonPat, "ami");
		PatriciaTrie.ajouterMot(bonPat, "salut");
		PatriciaTrie.ajouterMot(bonPat, "salee");
		PatriciaTrie.ajouterMot(bonPat, "sayonara");
		PatriciaTrie.ajouterMot(bonPat, "ameliorer");
		
		assertEquals(bonPat.toString(), p.toString());
		
	}
	
	@Test
	public void patToHybrideTest() {
		
		PatriciaTrie p = new PatriciaTrie();
		TrieHybride th ;
		
		TrieHybride.resetCpt();
		PatriciaTrie.ajouterMot(p, "amelo");
		PatriciaTrie.ajouterMot(p, "ami");
		PatriciaTrie.ajouterMot(p, "ameliorer");
		PatriciaTrie.ajouterMot(p, "amel");
		PatriciaTrie.ajouterMot(p, "salut");
		PatriciaTrie.ajouterMot(p, "salee");
		PatriciaTrie.ajouterMot(p, "sayonara");
		
		
		th = (TrieHybride) TrieConverter.patToHybridTrie(p);
		
		TrieHybride.resetCpt();
		
		TrieHybride bonTh = new TrieHybride();
		TrieHybridePrimitive.ajoutMot("amel", bonTh);
		TrieHybridePrimitive.ajoutMot("ameliorer", bonTh);
		TrieHybridePrimitive.ajoutMot("amelo", bonTh);
		TrieHybridePrimitive.ajoutMot("ami", bonTh);
		TrieHybridePrimitive.ajoutMot("salee", bonTh);
		TrieHybridePrimitive.ajoutMot("salut", bonTh);
		TrieHybridePrimitive.ajoutMot("sayonara", bonTh);

		
		PrintStream standardOutput = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		
		System.setOut(new PrintStream(outContent));
		TrieVisualizer.visualizeTrieHybride(th);
		String resObtenu = outContent.toString();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		TrieVisualizer.visualizeTrieHybride(bonTh);
		String resAttendu = outContent.toString();
		System.setOut(standardOutput);

		assertEquals(resAttendu, resObtenu);

	}
}