package trie.file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class TriePrintFile {

	/**
	 * Ecrit les elements contenus dans le fichier cible
	 * @param file Le chemin du fichier a ecrire
	 * @param timelist La liste des elements a ecrire
	 */
	public static void printFile(String file, List<String> timelist) {
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		try {
			fileWriter = new FileWriter(file);
			printWriter = new PrintWriter(fileWriter);
			for(String time: timelist){
				printWriter.printf("%s\n",time);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			printWriter.close();
		}
		
	}
}
