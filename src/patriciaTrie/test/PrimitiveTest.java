package patriciaTrie.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import patriciaTrie.structure.PatriciaTrie;
import trie.file.TrieLoadFile;
import trieHybride.implementation.TrieHybridePrimitive;

public class PrimitiveTest {
	
	public static void main(String[] args ){
		
		PatriciaTrie pt = new PatriciaTrie();
		/*File f = new File("files/exemple_de_base");
		List<String> words = new ArrayList<String>();
		String word;
		Scanner scan = null;
		try {
			scan = new Scanner(f);
			
			while( scan.hasNext()){
				word = scan.next();
				word=word.toLowerCase();
				words.add(word);
			}

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			scan.close();
		}
		
		for(String w : words)
			PatriciaTrie.ajouterMot(pt, w);
		
		System.out.println(pt);
		
		System.out.println("Nombre de mots du dictionnaire : "+PatriciaTrie.comptageMots(pt));
		System.out.println(PatriciaTrie.listeMots(pt));
		System.out.println("Nombre de compteurs NIL :" + PatriciaTrie.comptageNil(pt));
		System.out.println("Hauteur : "+ PatriciaTrie.hauteur(pt));
		System.out.println("Profondeur moyenne :"+ PatriciaTrie.profondeurMoyenne(pt));
		System.out.println("Nombre de mot de préfixe dactylo "+ PatriciaTrie.prefixe(pt, "dactylo"));*/
		
		PatriciaTrie pt2 = new PatriciaTrie();
		
		PatriciaTrie.ajouterMot(pt, "amel");
		PatriciaTrie.ajouterMot(pt2, "amel");
		PatriciaTrie.ajouterMot(pt2, "ameliorer");
		PatriciaTrie.ajouterMot(pt, "coo");
		PatriciaTrie.ajouterMot(pt2, "coo");
		PatriciaTrie.ajouterMot(pt, "cookie");
		PatriciaTrie.ajouterMot(pt2, "cook");
		PatriciaTrie.ajouterMot(pt2, "gateau");
		//trie.visualizer.TrieVisualizer.visualizePatriciaTrie(pt);
		PatriciaTrie pt3 = PatriciaTrie.fusion(pt, pt2);
		
		//System.out.println(pt3);
		
	
		PatriciaTrie pt4 = new PatriciaTrie();
		PatriciaTrie.ajouterMot(pt4,"bonjour");
		PatriciaTrie.ajouterMot(pt4,"bonsoir");
		PatriciaTrie.ajouterMot(pt4,"bonneannee");
		PatriciaTrie.ajouterMot(pt4,"marcher");
		PatriciaTrie.ajouterMot(pt4,"mar");
		trie.visualizer.TrieVisualizer.visualizePatriciaTrie(pt4);
		
		PatriciaTrie pt6 = TrieLoadFile.loadFileShakespearePatriciaTrie();
		
	}
	
}
