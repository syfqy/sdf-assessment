package sdf.task01;

import java.util.List;
import java.util.Map;

public class TemplateWriter {
    
    String template;
    List<String> keysToReplace;
    Map<String, String> personData;

    public TemplateWriter(String template, List<String> keysToReplace, Map personData) {
        this.template = template;
        this.keysToReplace = keysToReplace;
        this.personData = personData;
    }

    public String fillTemplate() {
        String newTemplate = template;
        for (String key : keysToReplace) {
            String replaceWith = personData.get(key);
            newTemplate = newTemplate.replaceAll(key, replaceWith);
        }
        // System.out.println(newTemplate);
        return newTemplate;
    }
    
}
