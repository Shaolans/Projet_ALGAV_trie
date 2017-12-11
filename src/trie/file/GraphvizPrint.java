package trie.file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import patriciaTrie.structure.PatriciaTrie;
import trieHybride.interfaces.ITrieHybride;

public class GraphvizPrint {
	private static int cptnull = 0;
	
	public static void printTrieHybrideGraph(String file, ITrieHybride th) {
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		try {
			fileWriter = new FileWriter(file);
			printWriter = new PrintWriter(fileWriter);
			printWriter.printf("digraph G{\n");
			GraphvizPrint.printTrieHybrideNode(printWriter,th);
			printWriter.print("}");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			printWriter.close();
		}
	}
	
	public static void printTrieHybrideNode(PrintWriter printWriter, ITrieHybride th) {
		String name = th.parsePointer();
		printWriter.println(name+" [label=\""+th.getChar()+"\\n"+th.getValue()+"\"]");
		if(th.existfg()) {
			printWriter.println(name+" -> "+th.getfg().parsePointer());
			GraphvizPrint.printTrieHybrideNode(printWriter, th.getfg());
		}else {
			printWriter.println(name+" -> null"+cptnull);
			printWriter.println("null"+cptnull+" [label=\"null\"]");
			cptnull++;
		}
		
		if(th.existfc()) {
			printWriter.println(name+" -> "+th.getfc().parsePointer());
			GraphvizPrint.printTrieHybrideNode(printWriter, th.getfc());
		}else {
			printWriter.println(name+" -> null"+cptnull);
			printWriter.println("null"+cptnull+" [label=\"null\"]");
			cptnull++;
		}
		
		if(th.existfd()) {
			printWriter.println(name+" -> "+th.getfd().parsePointer());
			GraphvizPrint.printTrieHybrideNode(printWriter, th.getfd());
		}else {
			printWriter.println(name+" -> null"+cptnull);
			printWriter.println("null"+cptnull+" [label=\"null\"]");
			cptnull++;
		}
	}
	
	
	
	public static void printPatriciaTrieGraph(String file, PatriciaTrie pt) {
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		try {
			fileWriter = new FileWriter(file);
			printWriter = new PrintWriter(fileWriter);
			printWriter.printf("digraph G{\n");
			GraphvizPrint.printPatriciaTrieNode(printWriter,pt);
			printWriter.print("}");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			printWriter.close();
		}
	}
	
	
	public static void printPatriciaTrieNode(PrintWriter printWriter, PatriciaTrie pt) {
		String name = pt.parsePointer();
		PatriciaTrie pattable[] = pt.getPatTries();
		String tableau = "[";
		for(int i = 0; i < 27; i++) {
			if(pattable[i] != null) {
				tableau += (char)(i+97)+"|";
			}
		}
		if(tableau.length() > 1) {
			tableau = tableau.substring(0, tableau.length()-1);
		}
		tableau += "]";
		printWriter.println(name+" [label=\""+pt.getInd()+"\\n"+pt.getVal()+"\\n"+tableau+"\"]");
		for(int i = 0; i < 27; i++) {
			if(pattable[i] != null) {
				printWriter.println(name+" -> "+pattable[i].parsePointer());
				GraphvizPrint.printPatriciaTrieNode(printWriter,pattable[i]);
			}
		}
	}
}
