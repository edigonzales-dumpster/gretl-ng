package ch.so.agi.gretl.tasks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.interlis2.validator.Validator;

import ch.ehi.basics.settings.Settings;

public class IliValidator extends AbstractValidatorTask {
    
    public void execute() {
        // TODO: je nachdem, ob ich noch filesets als Parameter zulasse,
        // muss das anders behandelt werden ("||").
        if (dataFiles == null) {
            return;
        }

        List<String> files = new ArrayList<String>();
        for (DataFile dataFile : dataFiles) {
            log(dataFile.getPath());
            
            TaskUtils.getFilePath(getProject(), dataFile.getPath());
            
            File fileObj = TaskUtils.getFilePath(getProject(), dataFile.getPath()); //new File(dataFile.getPath());
            String fileName = fileObj.getAbsolutePath();
            files.add(fileName);             
        }
                
        Settings settings = new Settings();
        initSettings(settings);

        validationOk = new Validator().validate(files.toArray(new String[files.size()]), settings);
        if (!validationOk && failOnError) {
            throw new BuildException("validation failed");
        }
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
