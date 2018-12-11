package com.bibtexparser;

import org.apache.commons.cli.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        Options options = new Options();
        Option authors = Option.builder()
                .longOpt("authors")
                .hasArgs()
                .desc("Search document by authors, separated by commas")
                .valueSeparator(',')
                .build();
        Option types = Option.builder()
                .longOpt("types")
                .hasArgs()
                .desc("Search document by types, separated by commas")
                .valueSeparator(',')
                .build();
        Option infile = Option.builder()
                .longOpt("file")
                .hasArgs()
                .desc("Path of input file")
                .build();

        options.addOption(authors);
        options.addOption(types);
        options.addOption(infile);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if (!cmd.hasOption("file") || (!cmd.hasOption("authors") && !cmd.hasOption("types"))){
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("BibteX Parser", options);
        }
        else{
            String filename = cmd.getOptionValue("file");
            String bibtext = open_file(filename);
            bibtext = manage_variables(bibtext);
            List<BibtexRecord> records = new ArrayList<BibtexRecord>();
            String []bibtex_str = bibtext.split("@");
            for (String bibtex : Arrays.copyOfRange(bibtex_str, 1, bibtex_str.length)){

                BibtexRecord bibtex_obj = new BibtexRecord(bibtex);
                records.add(bibtex_obj);
            }
            if (cmd.hasOption("authors")){
                String[] sought_authors = cmd.getOptionValue("authors").split(",");
                search_by_authors(sought_authors, records);
            }
            if (cmd.hasOption("types")){
                String[] sought_categories = cmd.getOptionValue("types").split(",");
                search_by_category(sought_categories, records);
            }
        }
    }


    public static String open_file(String filename) throws FileNotFoundException {
        File infile = new File(filename);
        String bibtext = new Scanner(infile).useDelimiter("\\A").next();
        return bibtext;
    }

    public static String manage_variables(String bibtext){
        Matcher variables = Pattern.compile("@String.*[)]{1}").matcher(bibtext);
        while (variables.find()){
            String var = variables.group();
            BibteXVariable variable = new BibteXVariable(var);
            bibtext = bibtext.replace(var, "");
            bibtext = variable.substitute(bibtext);
        }
        return bibtext;
    }

    public static void search_by_authors(String[] authors, List<BibtexRecord> records){
        for (String author : authors){
            System.out.println("Publication(s) written by " + author + ":");
            authors_publications(author, records);
        }
    }

    public static void authors_publications(String author, List<BibtexRecord> records){
        for (BibtexRecord record : records){
            if (record.getAuthors().toLowerCase().indexOf(author.toLowerCase()) != -1){
                System.out.println(record);
            }
        }
    }

    public static void search_by_category(String[] categories, List<BibtexRecord> records){
        for (String category : categories){
            System.out.println("All " + category.toLowerCase() + "s");
            all_from_category(category, records);
        }
    }

    public static void all_from_category(String category, List<BibtexRecord> records){
        for (BibtexRecord record : records){
            if (record.getRecord_type().equals(category.toUpperCase()))
                System.out.println(record);
        }
    }
}
