package Tools;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Read YAML files.
 */
public class YamlReader {
    private Yaml yaml = new Yaml();

    public Object getObj(String path, String first, String second, String name) {
        InputStream testsConf = null;
        try {
            testsConf = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map config = (Map) yaml.load(testsConf);
        Map config1 = (Map<String, Map<String, Map<String, String>>>) ((Map) config.get(first)).get(second);
        return config1.get(name);
    }

    public Object getObj(String path, String first, String name) {
        InputStream testsConf = null;
        try {
            testsConf = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map config = (Map) yaml.load(testsConf);
        Map config1 = (Map<String, Map<String, String>>) ((Map) config.get(first));
        return config1.get(name);
    }

    public Integer getInt(String path, String first, String name) {
        InputStream testsConf = null;
        try {
            testsConf = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map config = (Map) yaml.load(testsConf);
        Map config1 = (Map<String, Map<String, String>>) ((Map) config.get(first));
        Integer end = Integer.parseInt(config1.get(name).toString());
        return end;
    }

    public List<String> getObj(String path, String name) {
        InputStream testsConf = null;
        try {
            testsConf = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map config = (Map) yaml.load(testsConf);
        List<String> a = (List<String>) config.get(name);
        return a;
    }

    public String sysGetObj(String path, String first, String second, String name) {
        InputStream testsConf = null;
        try {
            testsConf = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map config = (Map) yaml.load(testsConf);
        Map config1 = (Map<String, Map<String, Map<String, String>>>) ((Map) config.get(first)).get(second);
        String end = (String) config1.get(name);
        return end;
    }

    public String sysGetObj(String path, String first, String name) {
        InputStream testsConf = null;
        try {
            testsConf = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map config = (Map) yaml.load(testsConf);
        Map config1 = (Map<String, Map<String, String>>) ((Map) config.get(first));
        String end = (String) config1.get(name);
        return end;
    }


//    public static void main(String[] args) {
//        YamlReader reader = new YamlReader();
//        reader.sysGetObj();
//    }
}
