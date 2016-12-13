package grid;

import java.util.*;
import grid.Column;

public class Row {

	public int getSize() {
		return size;
	}


	public Vector<Column> getLine() {
		return line;
	}

	// Definition of the variables
	private int size; //total size of the row
	private Vector<Column> line;

	//construtor by default
	public Row(){
		this(7); //By default we call the evolved constructor with a size of 7 (a row of 7 column)
	}

	//We say than a column is
	public Row(int size){
		this.size = size;
		this.line = new Vector<Column>();
	}

	public void setZero(){
		for (int i = 0; i<this.line.size(); i++) {
			this.line.elementAt(i).setFree(this.line.elementAt(i).getLine().size());
			for (int x = 0; x < this.line.elementAt(i).getLine().size(); x++) {
				this.line.elementAt(i).getLine().set(x, new Token("."));
			}
		}
	}
}