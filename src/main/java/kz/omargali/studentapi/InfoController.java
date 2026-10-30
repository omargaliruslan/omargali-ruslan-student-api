package kz.omargali.studentapi;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class InfoController {

    private final ObjectProvider<BuildProperties> buildProperties;

    public InfoController(ObjectProvider<BuildProperties> buildProperties) {
        this.buildProperties = buildProperties;
    }

    @GetMapping({"/", "/api/info"})
    public Map<String, String> info() {
        BuildProperties build = buildProperties.getIfAvailable();
        Map<String, String> info = new LinkedHashMap<>();
        info.put("application", "omargali-ruslan-student-api");
        info.put("student", "Ruslan Omargali");
        info.put("studentId", "37858");
        info.put("version", build != null ? build.getVersion() : "dev");
        return info;
    }
}
