package patriciaTrie.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import patriciaTrie.structure.PatriciaTrie;

public class PrimitiveTest {
	
	public static void main(String[] args ){
		
		PatriciaTrie pt = new PatriciaTrie();
		File f = new File("files/exemple_de_base");
		String word;
		Scanner scan = null;
		try {
			scan = new Scanner(f);
			
			while( scan.hasNext()){
				word = scan.next();
				word=word.toLowerCase();
				PatriciaTrie.addWord(pt, word);
			}

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			scan.close();
		}
		
		System.out.println(pt);
		
	}
	
}
