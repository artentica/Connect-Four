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
	private Vector line;

	//construtor by default
	public Row(){
		this(7); //By default we call the evolved constructor with a size of 7 (a row of 7 column)
	}

	//We say than a column is
	public Row(int size){
		this.size = size;
		this.line = new Vector<Column>();
	}
}