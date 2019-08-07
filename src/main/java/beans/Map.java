package beans;

import com.opencsv.bean.CsvBindByName;
import com.sun.istack.internal.Nullable;
import util.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
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

    public Image getFileAsImage() {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(file)));
        } catch (Exception e) {
            return null;
        }
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
