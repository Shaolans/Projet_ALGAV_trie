package trieHybride.implementation;

import trieHybride.interfaces.ITrieHybride;

public class TrieHybride implements ITrieHybride {
	public static int cpt = 0;
	private char letter;
	private int value;
	private ITrieHybride fg;
	private ITrieHybride fd;
	private ITrieHybride fc;
	
	//constructeur par d�faut (creer un TH vide)
	public TrieHybride(){
		letter = ' ';
		value = -1;
		fg = null;
		fd = null;
		fc = null;
	}
	
	public TrieHybride(char letter, int value, ITrieHybride fg, ITrieHybride fc, ITrieHybride fd){
		this.letter = letter;
		this.value = value;
		this.fg = fg;
		this.fc = fc;
		this.fd = fd;
	}
	
	//creer un TH contenant un mot (ou reste d'un mot)
	public TrieHybride(String word){
		this();
		//si derniere lettre on ne creer pas de nouveau TH
		if(word.length() == 1){
			this.value = cpt++;
			letter = word.charAt(0);
		}else{
			//on attribut la lettre et on creer un nouveau TH fils fc qui contiendra la lettre suivante
			letter = word.charAt(0);
			fc = new TrieHybride(word.substring(1, word.length()));
		}
	}
	
	
	
	@Override
	public char getChar() {
		return letter;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public void setChar(char c) {
		letter = c;
	}

	@Override
	public void setValue(int v) {
		value = v;
	}

	@Override
	public void setfg(ITrieHybride th) {
		fg = th;
	}

	@Override
	public void setfd(ITrieHybride th) {
		fd = th;
	}

	@Override
	public void setfc(ITrieHybride th) {
		fc = th;
	}

	@Override
	public ITrieHybride getfg() {
		return fg;
	}

	@Override
	public ITrieHybride getfd() {
		return fd;
	}

	@Override
	public ITrieHybride getfc() {
		return fc;
	}

	@Override
	public boolean isWord() {
		return !(value == -1);
	}

	@Override
	public boolean isEmpty() {
		return letter == ' ';
	}

	@Override
	public boolean existfg() {
		return fg != null;
	}

	@Override
	public boolean existfd() {
		return fd != null;
	}

	@Override
	public boolean existfc() {
		return fc != null;
	}

	@Override
	public boolean isLeaf() {
		return !this.existfc() && !this.existfg() && !this.existfd();
	}
	
	@Override
	public void turnIntoWord() {
		this.value = cpt++;
	}
	
	public static int getCpt() {
		return cpt++;
	}
	
	public static void resetCpt(){
		cpt=0;
	}
	
	public String parsePointer() {
		String name = super.toString();
		name = name.replaceAll("[.,@]", "");
		return name;
	}

}
