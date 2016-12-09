package grid;
import java.util.*;
public class Column {

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getFree() {
		return free;
	}

	public void setFree(int free) {
		this.free = free;
	}


	public Vector<Token> getLine() {
		return line;
	}

	// Definition of the variable
	private Vector<Token> line;

	private int size; //total size of the column
	private int free; //free space for the token

	//construtor by default
	public Column(){
		this(6); //By default we call the evolved constructor with a size of 6
	}
	
	//We say than a column is 
	public Column(int size){
		this.size = size;
		this.free = this.size;

		this.line = new Vector<Token>(this.size);
		setLine();
	}

	private void setLine(){
		for (int i=0; i<this.size;i++){
			this.line.add(new Token("."));
		}
	}

}