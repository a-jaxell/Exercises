package org.fileCrawler;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileCrawler {
    private String query;
    private static String currentDir;
    public String getQuery() {
        return query;
    }
    public FileCrawler(){
       currentDir =  System.getProperty("user.dir");
    }
    public void setQuery(String query) {
        this.query = query;
    }
    public static void findMatches(File[] paths, String query){

        assert paths != null;
        for(File path: paths){
            if(!path.canRead()){
                System.out.println("File: " + path.getAbsolutePath() + " can not be read.");
                continue;
            }
            if(path.isDirectory()){
                findMatches(path.listFiles(), query);
            } else {
                findMatch(path, query);
            }
        }
    }
    public static void findMatch(File path, String query) {
        if(!path.canRead()) return;
        try(Stream<String> lines = java.nio.file.Files.lines(path.toPath(), StandardCharsets.ISO_8859_1)) {
            lines.forEach(line -> {
                if (line.contains(query)) System.out.println(path.toPath().toFile().getAbsolutePath());
            });
        } catch (IOException e) {
            System.out.println( e + "Cant be read, skipping");

        }
    }

    public static void main(String[] args) {

        FileCrawler app = new FileCrawler();

        System.out.print("Vänligen mata in en söksträng: ");
        app.setQuery(readQuery());

        File[] test = new File(currentDir).listFiles();
        findMatches(test, app.getQuery());


    }
    private static String readQuery(){
        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        System.out.println("""
                                    
                                    Söker efter""" + ": " + input);
        return input;
    }

}
