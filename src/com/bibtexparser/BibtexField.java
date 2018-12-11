package com.bibtexparser;

public class BibtexField {
    private String name;
    private String value;
    private FieldPrerequisite prerequisite;

    public BibtexField(String field, String[] required, String[] optional) {
        this.name = field.substring(0, field.indexOf("=") - 1);
        this.name = this.name.replaceAll("\\s+", "");
        this.value = field.substring(field.indexOf("=") + 2)
                .replaceAll("\"", "")
                .replace(",", "");
        if (this.name.indexOf("author") != -1 || this.name.indexOf("editor") != -1){
            String []authors = this.value.split("and");
            this.value = "";
            for (int i = 0; i < authors.length; i++){
                String author = authors[i];
                author = author.replaceAll("^\\s+", "");
                if (author.indexOf("|") != -1){
                    String []a = author.split("\\|");
                    author = a[1].substring(1) + a[0];
                }
                if (i > 0){
                    author = "\n|                |  - " + author;
                }
                else if (authors.length > 1){
                    author = "- " + author;
                }
                this.value += author;
            }
        }
        for (String req : required){
            if (req.indexOf(this.name) != -1){
                this.prerequisite = FieldPrerequisite.REQUIRED;
                return;
            }
        }
        for (String req : optional){
            if (req.indexOf(this.name) != -1){
                this.prerequisite = FieldPrerequisite.OPTIONAL;
                return;
            }
        }
        this.prerequisite = FieldPrerequisite.IGNORED;
    }

    @Override
    public String toString() {
        int spacer1 = 15 - name.length();
        String str1 = new String(new char[spacer1]).replace("\0", " ");
        return "| " + name +  str1 + "|  " + value;
    }

    public String getName() {
        return name;
    }

    public FieldPrerequisite getPrerequisite() {
        return prerequisite;
    }

    public String getValue() {
        return value;
    }
}
