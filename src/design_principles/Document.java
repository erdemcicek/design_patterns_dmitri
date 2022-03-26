package design_principles;

public class Document {

}

interface Machine{
	void print(Document d);
	void fax(Document d);
	void scan(Document d);
}

class MultiFunctionPrinter implements Machine{

	@Override
	public void print(Document d) {
		
	}

	@Override
	public void fax(Document d) {
		
	}

	@Override
	public void scan(Document d) {
		
	}
	
}

class OldFashionedPrinter implements Machine{

	@Override
	public void print(Document d) {
		
	}

	@Override
	public void fax(Document d) { // leave it empty ???  throw Exception ???
		// not supported
	}

	@Override
	public void scan(Document d) { // leave it empty ??? throw Exception ???
		// not supported
	}
	
}

interface Printer{
	void print(Document d);
}

interface Scanner{
	void scan(Document d);
}
