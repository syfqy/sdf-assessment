package sdf.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CsvReader {

    public static LinkedList<Map> readCsv(String csvFile) {

        FileReader fr;
        BufferedReader br;

        String content = "";
        int s1 = 0;
        LinkedList<Map> csvData = new LinkedList<Map>();

        try {

            // init input streams
            fr = new FileReader(csvFile);
            br = new BufferedReader(fr);

            // read content from csv
            while ((s1 = br.read()) != -1) {
                char c = (char) s1;
                content += c;
            }

            // separate to lines
            String[] split = content.split("\n");
            
            // delimit by comma
            LinkedList<String[]> lines = new LinkedList<String[]>();
            for (String l : split) {
                l = l.replace("\\n", "\n"); // insert newline in address
                String[] line = l.split(",");
                lines.add(line);
            }
            
            // assign first row as col headers
            String[] headers = lines.get(0);

            // create person data map (header: data)
            for (int i = 1; i < lines.size(); i++) {
                Map<String, String> personData = new HashMap<>();
                String[] data = lines.get(i);
                for (int j = 0; j < headers.length; j++) {
                    String header = "__" + headers[j] + "__"; // add double underscore to search and
                    // replace in template writer
                    personData.put(header, data[j]);
                }
                csvData.add(personData);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return csvData;
    }
}