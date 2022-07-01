package sdf.task01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {

        // get args
        String csvFile = args[0];
        String templateFile = args[1];
        LinkedList<Person> personsList = new LinkedList<Person>();
        FileReader fr;
        BufferedReader br;

        System.out.printf("csv: %s, template: %s \n", csvFile, templateFile);

        String template = "";
        String line;

        // read template
        try {
            fr = new FileReader(templateFile);
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                // append line to template str
                template += line+"\n";

            }
            br.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(template);

        // read csv
        LinkedList<Map> data = CsvReader.readCsv(csvFile);

        String filledTemplate = "";
        for (int i = 0; i < data.size(); i++) {
            Map<String, String> personData = data.get(i);
            List<String> keys = new ArrayList<String>(personData.keySet());
            for (Map.Entry<String, String> entry : personData.entrySet()) {
                TemplateWriter tw = new TemplateWriter(template, keys, personData);
            filledTemplate = tw.fillTemplate();
            }
            System.out.println(filledTemplate);
        }

    }
}
