package beans;

import com.opencsv.bean.CsvBindByName;
import util.Logger;

import java.io.File;
import java.util.Objects;

public class Map {

    @CsvBindByName(column = "Name", required = true)
    private String name;

    @CsvBindByName(column = "File", required = true)
    private String file;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFileAsFile() {
        return new File(Objects.requireNonNull(getClass().getClassLoader().getResource(file)).getFile());
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
