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

    // fills template with person data
    public String fillTemplate() {
        String newTemplate = this.template;
        for (String key : this.keysToReplace) {
            String replaceWith = this.personData.get(key);
            newTemplate = newTemplate.replaceAll(key, replaceWith);
        }
        return newTemplate;
    }
    
}
