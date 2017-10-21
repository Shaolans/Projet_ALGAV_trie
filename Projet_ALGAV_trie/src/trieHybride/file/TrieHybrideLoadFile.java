package trieHybride.file;


import java.io.File;
import java.util.Scanner;

import trieHybride.implementation.TrieHybridePrimitive;
import trieHybride.interfaces.ITrieHybride;


public class TrieHybrideLoadFile {

	
	/**
	 * Construit un TrieHybride a partir d'un fichier texte
	 * @param file le nom du fichier
	 * @param th arbre dont on ajoute
	 * @return arbre avec les mots ajoutes
	 */
	public static ITrieHybride loadFile(String file, ITrieHybride th){
		File f = new File(file);
		Scanner sc = null;
		String word;
		ITrieHybride construction = th;
		int cpt = 0;
		
		if(th == null){
			construction = TrieHybridePrimitive.initTrieHybride();
		}
		
		try{
			sc = new Scanner(f);
			while(true){
				word = sc.next();
				TrieHybridePrimitive.ajoutMot(word, construction, cpt++);
			}
		}catch(Exception ex){
			sc.close();
		}
		return construction;
		
	}
	
	public static ITrieHybride loadFileShakespeare(ITrieHybride th){
		String prefix = "files/Shakespeare";
		File folder = new File(prefix);
		String[] listOfFiles = folder.list();
		ITrieHybride construction = th;
		for(String file: listOfFiles){
			construction = TrieHybrideLoadFile.loadFile(prefix+"/"+file, construction);
		}
		return construction;

	}
}
