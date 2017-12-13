package trieHybride.interfaces;

public interface ITrieHybride {
	//accesseur de la lettre du TH
	public char getChar();
	//accesseur de la valeur du TH
	public int getValue();
	
	//Modifier la lettre du TH
	public void setChar(char c);
	//Modifier la valeur du TH
	public void setValue(int v);
	
	//Modifier fils gauche
	public void setfg(ITrieHybride th);
	//Modifier fils droit
	public void setfd(ITrieHybride th);
	//Modifier fils central
	public void setfc(ITrieHybride th);
	
	//accesseur fils gauche
	public ITrieHybride getfg();
	//accesseur fils droit
	public ITrieHybride getfd();
	//accesseur fils central
	public ITrieHybride getfc();
	
	//indique si le TH est la derniere lettre d'un mot (value != -1)
	public boolean isWord();
	//indique si le TH est vide (pas de caractere attribu� (letter != ' '))
	public boolean isEmpty();
	//indique si le TH est une feuille (pas de fils)
	public boolean isLeaf();

	//indique s'il existe un fils gauche
	public boolean existfg();
	//indique s'il existe un fils droit
	public boolean existfd();
	//indique s'il existe un fils central
	public boolean existfc();
	//transforme le noeud en mot
	public void turnIntoWord();
	//Retourne le nom du pointeur sans "@" et "."
	public String parsePointer();
	}
