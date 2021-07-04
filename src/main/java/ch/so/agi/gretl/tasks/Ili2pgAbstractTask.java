package ch.so.agi.gretl.tasks;

import java.io.File;

import org.apache.tools.ant.Task;

import ch.ehi.basics.logging.EhiLogger;
import ch.ehi.ili2db.base.Ili2db;
import ch.ehi.ili2db.gui.Config;
import ch.so.agi.gretl.api.Connector;

public abstract class Ili2pgAbstractTask extends Task {
    private Connector database;

    protected String dburi = null;
    
    protected String dbusr = null;
    
    protected String dbpwd = null;
    
    protected String dbschema = null;
    
    protected String proxy = null;
    
    protected Integer proxyPort = null;

    protected String modeldir = null;
    
    protected String models = null;
    
    protected Object dataset = null;
    
    protected String baskets = null;
    
    protected String topics = null;
    
    protected boolean importTid = false;
    
    protected String preScript = null;
    
    protected String postScript = null;
    
    protected boolean deleteData = false;
    
    protected String logFile = null;
    
    protected boolean trace = false;
    
    protected String validConfigFile = null;
    
    protected boolean disableValidation = false;
    
    protected boolean disableAreaValidation = false;
    
    protected boolean forceTypeValidation = false;
    
    protected boolean strokeArcs = false;
    
    protected boolean skipPolygonBuilding = false;
    
    protected boolean skipGeometryErrors = false;
    
    protected boolean iligml20 = false;
    
    protected boolean disableRounding = false;  
    
    protected boolean failOnException = true;
    
//    protected Range<Integer> datasetSubstring = null;

    protected void run(int function, Config settings) {
//        log = LogEnvironment.getLogger(Ili2pgAbstractTask.class);
        
        database = new Connector(dburi, dbusr, dbpwd);
        if (database == null) {
            throw new IllegalArgumentException("database must not be null");
        }
        
        settings.setFunction(function);

        if (proxy != null) {
            settings.setValue(ch.interlis.ili2c.gui.UserSettings.HTTP_PROXY_HOST, proxy);
        }
        if (proxyPort != null) {
            settings.setValue(ch.interlis.ili2c.gui.UserSettings.HTTP_PROXY_PORT, proxyPort.toString());
        }
        if (dbschema != null) {
            settings.setDbschema(dbschema);
        }
        if (modeldir != null) {
            settings.setModeldir(modeldir);
        }
        if (models != null) {
            settings.setModels(models);
        }
        if (baskets != null) {
            settings.setBaskets(baskets);
        }
        if (topics != null) {
            settings.setTopics(topics);
        }
        if (importTid) {
            settings.setImportTid(true);
        }
        if (preScript != null) {
            settings.setPreScript(TaskUtils.getFilePath(getProject(), preScript).getAbsolutePath());
        }
        if (postScript != null) {
            settings.setPostScript(TaskUtils.getFilePath(getProject(), preScript).getAbsolutePath());
        }
        if (deleteData) {
            settings.setDeleteMode(Config.DELETE_DATA);
        }
        if(function!=Config.FC_IMPORT && function!=Config.FC_UPDATE && function!=Config.FC_REPLACE) {
            if (logFile != null) {
                settings.setLogfile(TaskUtils.getFilePath(getProject(), logFile).getAbsolutePath());
            }
        }
        if (trace) {
            EhiLogger.getInstance().setTraceFilter(false);
        }
        if (validConfigFile != null) {
            settings.setValidConfigFile(TaskUtils.getFilePath(getProject(), validConfigFile).getAbsolutePath());
        }
        if (disableValidation) {
            settings.setValidation(false);
        }
        if (disableAreaValidation) {
            settings.setDisableAreaValidation(true);
        }
        if (forceTypeValidation) {
            settings.setOnlyMultiplicityReduction(true);
        }
        if (strokeArcs) {
            Config.setStrokeArcs(settings, Config.STROKE_ARCS_ENABLE);
        }
        if (skipPolygonBuilding) {
            Ili2db.setSkipPolygonBuilding(settings);
        }
        if (skipGeometryErrors) {
            settings.setSkipGeometryErrors(true);
        }
        if (iligml20) {
            settings.setTransferFileFormat(Config.ILIGML20);
        }
        if (disableRounding) {
            settings.setDisableRounding(true);;
        }        
        
        System.out.println("Fubar Fubar");        
    }
    
    protected Config createConfig() {
        Config settings = new Config();
        new ch.ehi.ili2pg.PgMain().initConfig(settings);
        return settings;
    }

    public void setDburi(String dburi) {
        this.dburi = dburi;
    }

    public void setDbusr(String dbusr) {
        this.dbusr = dbusr;
    }

    public void setDbpwd(String dbpwd) {
        this.dbpwd = dbpwd;
    }

    public void setDbschema(String dbschema) {
        this.dbschema = dbschema;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    public void setModeldir(String modeldir) {
        this.modeldir = modeldir;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public void setDataset(Object dataset) {
        this.dataset = dataset;
    }

    public void setBaskets(String baskets) {
        this.baskets = baskets;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public void setImportTid(boolean importTid) {
        this.importTid = importTid;
    }

    public void setPreScript(String preScript) {
        this.preScript = preScript;
    }

    public void setPostScript(String postScript) {
        this.postScript = postScript;
    }

    public void setDeleteData(boolean deleteData) {
        this.deleteData = deleteData;
    }

    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    public void setTrace(boolean trace) {
        this.trace = trace;
    }

    public void setValidConfigFile(String validConfigFile) {
        this.validConfigFile = validConfigFile;
    }

    public void setDisableValidation(boolean disableValidation) {
        this.disableValidation = disableValidation;
    }

    public void setDisableAreaValidation(boolean disableAreaValidation) {
        this.disableAreaValidation = disableAreaValidation;
    }

    public void setForceTypeValidation(boolean forceTypeValidation) {
        this.forceTypeValidation = forceTypeValidation;
    }

    public void setStrokeArcs(boolean strokeArcs) {
        this.strokeArcs = strokeArcs;
    }

    public void setSkipPolygonBuilding(boolean skipPolygonBuilding) {
        this.skipPolygonBuilding = skipPolygonBuilding;
    }

    public void setSkipGeometryErrors(boolean skipGeometryErrors) {
        this.skipGeometryErrors = skipGeometryErrors;
    }

    public void setIligml20(boolean iligml20) {
        this.iligml20 = iligml20;
    }

    public void setDisableRounding(boolean disableRounding) {
        this.disableRounding = disableRounding;
    }

    public void setFailOnException(boolean failOnException) {
        this.failOnException = failOnException;
    }
}
