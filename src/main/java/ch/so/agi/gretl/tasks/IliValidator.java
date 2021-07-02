package ch.so.agi.gretl.tasks;

import java.util.ArrayList;
import java.util.List;

import ch.ehi.basics.settings.Settings;

public class IliValidator extends AbstractValidatorTask {
    
    public void execute() {
        System.out.println("fubar");
        System.out.println(dataFiles.size());
        
        for (DataFile dataFile : dataFiles) {
            log(dataFile.getPath());
        }
        
        //TaskUtils.getFilePath(project, description);
        
        Settings settings = new Settings();
        
        
        System.out.println("foo");

    }
    
    List<DataFile> dataFiles = new ArrayList<DataFile>();
    
    public DataFile createDataFile() {
        DataFile dataFile = new DataFile();
        dataFiles.add(dataFile);
        return dataFile;
    }
    
    public class DataFile {
        public DataFile() {}
        
        private String path;
        
        public void setPath(String path) {
            this.path = path;
        }
        
        public String getPath() {
            return path;
        }
    }
}
