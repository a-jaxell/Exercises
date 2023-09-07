package org.Labb1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataEntry {

    private ArrayList<ArrayList<String>> data = new ArrayList<>();
    public ArrayList<ArrayList<String>> getData() {
        return data;
    }
    public void setData(ArrayList<ArrayList<String>> data) {
        this.data = data;
    }
   public DataEntry(){
       for ( int i = 0; i < 24; i++){
            data.add(new ArrayList<>());
       }
   }
    public void readFromCsv(String csvFile) {

        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int hour = 0;
            while ((line = br.readLine()) != null && hour < 24) {
                String[] dataRead = line.split(csvSplitBy);

                for (String s : dataRead) {
                    this.data.get(hour).clear();
                    this.data.get(hour).add(s);
                    hour++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
