package com.bibtexparser;

import java.util.ArrayList;
import java.util.List;

public class BibtexRecord {
    private RecordType record_type;
    private String key;
    private List<BibtexField> fields;

    BibtexRecord(String record){
        int i = record.indexOf("@");
        int j = record.indexOf("{");
        String type = record.substring(i + 1, j).replaceAll("\\s+", "");
        switch (type){
            case "ARTICLE":
                this.record_type = RecordType.ARTICLE;
                break;
            case "BOOK":
                this.record_type = RecordType.BOOK;
                break;
            case "INPROCEEDINGS":
                this.record_type = RecordType.INPROCEEDINGS;
                break;
            case "BOOKLET":
                this.record_type = RecordType.BOOKLET;
                break;
            case "INBOOK":
                this.record_type = RecordType.INBOOK;
                break;
            case "INCOLLECTION":
                this.record_type = RecordType.INCOLLECTION;
                break;
            case "MANUAL":
                this.record_type = RecordType.MANUAL;
                break;
            case "MASTERTHESIS":
                this.record_type = RecordType.MASTERTHESIS;
                break;
            case "PHDTHESIS":
                this.record_type = RecordType.PHDTHESIS;
                break;
            case "TECHREPORT":
                this.record_type = RecordType.TECHREPORT;
                break;
            case "MISC":
                this.record_type = RecordType.MISC;
                break;
            case "UNPUBLISHED":
                this.record_type = RecordType.UNPUBLISHED;
                break;
            default:
                throw new IllegalArgumentException("Icorrect type: " + type);
        }
        i = j + 1;
        j = record.indexOf(",");
        key = record.substring(i, j).replaceAll("\\s+", "");
        i = j + 3;
        j = record.indexOf("}") - 3;
        String fields_str = record.substring(i, j);
        fields = new ArrayList<BibtexField>();
        for (String field : fields_str.replace("   ", "").split("\n")){
            BibtexField bf = new BibtexField(field, this.record_type.getRequired(), this.record_type.getOptional());
            this.fields.add(bf);
        }
        for (String required_field : this.record_type.getRequired()){
            boolean present = false;
            for (BibtexField bf : fields){
                if (required_field.indexOf(bf.getName()) != -1){
                    present = true;
                    continue;
                }
            }
            if (!present)
                throw new IllegalArgumentException("Brak pola wymaganego: " + required_field);
        }
    }

    @Override
    public String toString() {
        String header = record_type.name() + " (" + key + ")";
        String body = "";
        for (BibtexField bf : fields){
            if (bf.getPrerequisite() != FieldPrerequisite.IGNORED) {
                body += bf.toString() + "\n";
            }

        }
        return header + "\n" + body;
    }


    public String getAuthors() {
        String res = "";
        for (BibtexField bf : this.fields){
            if (bf.getName().indexOf("author") != -1 ||
                bf.getName().indexOf("editor") != -1){
                res =  bf.getValue();
                break;
            }
        }
        return res;
    }

    public String getRecord_type() {
        return record_type.name();
    }
}
