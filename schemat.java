TODO osobna mapa mapa na obowi¹zkowe i ignorowane
TODO parser, jedna klasa
class BibtexRecord{
	private RecordTypes record_type;
	private String key;
	private List<BibtexField> fields;
	private BibtexVariable[] vars;

	public void BibtexRecord(String record);
	public abstract bool ArbitraryFields();
	public abstract bool field_ignored(BibtexField field);
	
	public String toString();

	getters, setters
}

class BibtexField{
	private FieldTypes field;
	private String field_value;
	public void parseLine(String line);
	getters, setters	
}

enum FieldTypes{

}

class BibtexVariable{
	public BibtexString(String line);
	private String key;
	private String value;
	getters, setters
}

class BibtexArticle extends BibtexRecord{
	public bool ArbitraryFields();
	public bool field_ignored(BibtexField field);
}

class BibtexBook extends BibtexRecord{
	public bool ArbitraryFields();
	public bool field_ignored(BibtexField field);
}

...

class BibtexUnpublished extends BibtexRecord{
	public bool ArbitraryFields();
	public bool field_ignored(BibtexField field);
}

enum RecordTypes{

}

class SearchRecord{
	
}

