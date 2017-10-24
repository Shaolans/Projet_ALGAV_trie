package trie.file;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import trieHybride.implementation.TrieHybridePrimitive;
import trieHybride.interfaces.ITrieHybride;


public class TrieLoadFile {

	
	/**
	 * Construit un TrieHybride a partir d'un fichier texte
	 * @param file le nom du fichier
	 * @param th arbre dont on ajoute
	 * @return arbre avec les mots ajoutes
	 */
	public static ITrieHybride loadFileTrieHybride(String file, ITrieHybride th){
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
	
	public static ITrieHybride loadFileShakespeareTrieHybride(ITrieHybride th){
		String prefix = "files/Shakespeare";
		File folder = new File(prefix);
		String[] listOfFiles = folder.list();
		ITrieHybride construction = th;
		for(String file: listOfFiles){
			construction = TrieLoadFile.loadFileTrieHybride(prefix+"/"+file, construction);
		}
		return construction;

	}
	
	public static List<String> loadWords(String file){
		String word;
		List<String> listword = new ArrayList<String>();
		File f = new File(file);
		Scanner sc = null;
		try{
			sc = new Scanner(f);
			while(true){
				word = sc.next();
				listword.add(word);
			}
		}catch(Exception ex){
			sc.close();
		}
		return listword;
	}
	
	public static List<String> loadWordsShakespeare(){
		List<String> listword = new ArrayList<String>();
		String prefix = "files/Shakespeare";
		File folder = new File(prefix);
		String[] listOfFiles = folder.list();
		for(String file: listOfFiles){
			listword.addAll(TrieLoadFile.loadWords(prefix+"/"+file));
		}
		return listword;
	}
}