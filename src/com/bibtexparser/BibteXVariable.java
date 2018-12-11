package com.bibtexparser;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BibteXVariable {
    private String key;
    private String value;

    public BibteXVariable(String variable) {
        String []var = variable
                .replace("@String", "")
                .replace("(", "")
                .replace(")", "")
                .replace("{", "")
                .replace("}", "")
                .replace("\"", "")
                .replace("}", "")
                .replace(" ", "")
                .split("=");
        this.key = var[0];
        this.value = var[1];
    }

    @Override
    public String toString() {
        return "BibteXVariable{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String substitute(String text){
        text = clear_string(text);
        String regex = "=[ ]*" + this.key + ",";
        Matcher occuriences = Pattern.compile(regex).matcher(text);
        while (occuriences.find()){
            text = text.replaceAll(occuriences.group(), "= \"" + this.value + "\",");
        }
        text = text
                .replaceAll("\\s*#\\s*" + this.key + "\\s*#\\s*", this.value)
                .replaceAll(this.key + "\\s*#\\s*", this.value)
                .replaceAll("\\s*#\\s*" + this.key, this.value);

        return text;
    }

    public String clear_string(String text){
        Matcher comments = Pattern.compile("@comment.+}|@preamble.+}").matcher(text);
        while (comments.find()){
            text.replaceAll(comments.group(), "");
        }
        return text;
    }
}
