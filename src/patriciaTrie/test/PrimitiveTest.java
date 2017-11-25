package patriciaTrie.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import patriciaTrie.structure.PatriciaTrie;

public class PrimitiveTest {
	
	public static void main(String[] args ){
		
		PatriciaTrie pt = new PatriciaTrie();
		File f = new File("files/exemple_de_base");
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
		System.out.println("Nombre de mot de préfixe dactylo "+ PatriciaTrie.prefixe(pt, "dactylo"));
		
	}
	
}
