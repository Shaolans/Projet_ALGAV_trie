package patriciaTrie.structure;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private String valeur;
	private List<Node> fils;
	
	public Node(String valeur){
		this.valeur = valeur;
		fils=new ArrayList<Node>();
	}
	
	public String getValeur(){
		return valeur;
	}
	
	public List<Node> getFils(){
		return fils;
	}
	
	@Override
	public String toString(){
		
		StringBuilder chaine = new StringBuilder(valeur);
		chaine.append('\n');
		
		if(fils.size()!=0){
			for(Node n : fils){
				chaine.append(n.toString());
			}
		}
		
		return chaine.toString();
		
	}

	public void setValeur(String newValeur) {
		valeur=newValeur;
	}
	
}
