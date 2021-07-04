package ch.so.agi.gretl.tasks;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.types.FileSet;

import ch.ehi.basics.logging.EhiLogger;
import ch.ehi.ili2db.base.Ili2db;
import ch.ehi.ili2db.gui.Config;
import ch.interlis.iox_j.logging.FileLogger;
import ch.so.agi.gretl.logging.Ehi2GretlAdapter;
import ch.so.agi.gretl.logging.GretlLogger;
import ch.so.agi.gretl.logging.LogEnvironment;

public class Ili2pgImport extends Ili2pgAbstractTask {
    protected GretlLogger log;

    private List<DataFile> dataFiles = new ArrayList<DataFile>();
    
    private List<FileSet> filesets = new ArrayList<>();
    
    public DataFile createDataFile() {
        DataFile dataFile = new DataFile();
        dataFiles.add(dataFile);
        return dataFile;
    }

    public void addFileset(FileSet fileset) {
        filesets.add(fileset);
    }
    
    // TODO: Ist dataset-Support beim Import-Task überhaupt sinnvoll/notwendig?
    // Kann ja genau so gut mit dem Replace-Task gemacht werden (glaubs)?
    
    public void execute() {
        log = LogEnvironment.getLogger(Ili2pgReplace.class);
        Ehi2GretlAdapter.init();

        Config settings = createConfig();
        int function = Config.FC_IMPORT;
        
        if (dataFiles.size() == 0 && filesets.size() == 0) {
            return;
        }
        
        List<String> files = new ArrayList<String>();
        
        for (DataFile dataFile : dataFiles) {
            File fileObj = TaskUtils.getFilePath(getProject(), dataFile.getPath()); //new File(dataFile.getPath());
            String fileName = fileObj.getAbsolutePath();
            files.add(fileName);
        }
        
        for (FileSet fs : filesets) {
            DirectoryScanner ds = fs.getDirectoryScanner(getProject());
            for (String includedFile : ds.getIncludedFiles()) {
                String baseDir = ds.getBasedir().getAbsolutePath();
                String fileName = Paths.get(baseDir, includedFile).toFile().getAbsolutePath();
                files.add(fileName);
            }
        }

        // TODO: falls dataset Support...
        
        ch.ehi.basics.logging.FileListener fileLogger=null;
        if (logFile != null){
            // setup logger here, so that multiple file imports result in one logfile
            java.io.File logFilepath = TaskUtils.getFilePath(getProject(), logFile);
            fileLogger = new FileLogger(logFilepath);
            EhiLogger.getInstance().addListener(fileLogger);
        }

        try {
            int i=0;
            for (String xtfFilename : files) {
                if (Ili2db.isItfFilename(xtfFilename)) {
                    settings.setItfTransferfile(true);
                } else {
                    settings.setItfTransferfile(false);
                }
//                if(datasetNames!=null) {
//                    settings.setDatasetName(datasetNames.get(i));
//                }
                settings.setXtffile(xtfFilename);
                run(function, settings);            
                i++;
            }
        } finally {
            if (fileLogger != null){
                EhiLogger.getInstance().removeListener(fileLogger);
                fileLogger.close();
                fileLogger = null;
            }
        }
    }
}
