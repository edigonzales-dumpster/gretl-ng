package ch.so.agi.gretl.tasks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;
import org.interlis2.validator.Validator;

import ch.ehi.basics.settings.Settings;
import ch.so.agi.gretl.logging.Ehi2GretlAdapter;
import ch.so.agi.gretl.logging.GretlLogger;
import ch.so.agi.gretl.logging.LogEnvironment;

public class IliValidator extends AbstractValidatorTask {
    private GretlLogger log;
    
    List<DataFile> dataFiles = new ArrayList<DataFile>();
    
    public DataFile createDataFile() {
        DataFile dataFile = new DataFile();
        dataFiles.add(dataFile);
        return dataFile;
    }    
    
    public void execute() {
        log = LogEnvironment.getLogger(this);
        Ehi2GretlAdapter.init(this);
        
        // TODO: je nachdem, ob ich noch filesets als Parameter zulasse,
        // muss das anders behandelt werden ("||").
        if (dataFiles == null) {
            return;
        }

        List<String> files = new ArrayList<String>();
        for (DataFile dataFile : dataFiles) {            
            File fileObj = TaskUtils.getFilePath(getProject(), dataFile.getPath());
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
}
