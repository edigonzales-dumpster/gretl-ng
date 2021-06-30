package ch.so.agi.gretl.tasks;

import ch.ehi.basics.settings.Settings;

import org.apache.tools.ant.Task;
import org.interlis2.validator.Validator;

public class AbstractValidatorTask extends Task {
    protected String models = null;

    protected String modeldir = null;

    protected String configFile = null;

    protected boolean forceTypeValidation = false;

    protected boolean disableAreaValidation = false;

    protected boolean multiplicityOff = false;

    protected boolean allObjectsAccessible = false;

    protected boolean skipPolygonBuilding = false;

    protected String logFile = null;

    protected String xtflogFile = null;

    protected String pluginFolder = null;

    protected String proxy = null;

    protected Integer proxyPort = null;

    protected boolean failOnError = true;
    
    protected boolean validationOk = true;

    public void setModels(String models) {
        this.models = models;
    }

    public void setModeldir(String modeldir) {
        this.modeldir = modeldir;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public void setForceTypeValidation(boolean forceTypeValidation) {
        this.forceTypeValidation = forceTypeValidation;
    }

    public void setDisableAreaValidation(boolean disableAreaValidation) {
        this.disableAreaValidation = disableAreaValidation;
    }

    public void setMultiplicityOff(boolean multiplicityOff) {
        this.multiplicityOff = multiplicityOff;
    }

    public void setAllObjectsAccessible(boolean allObjectsAccessible) {
        this.allObjectsAccessible = allObjectsAccessible;
    }

    public void setSkipPolygonBuilding(boolean skipPolygonBuilding) {
        this.skipPolygonBuilding = skipPolygonBuilding;
    }

    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    public void setXtflogFile(String xtflogFile) {
        this.xtflogFile = xtflogFile;
    }

    public void setPluginFolder(String pluginFolder) {
        this.pluginFolder = pluginFolder;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    public void setFailOnError(boolean failOnError) {
        this.failOnError = failOnError;
    }

    public void setValidationOk(boolean validationOk) {
        this.validationOk = validationOk;
    }

    protected void initSettings(Settings settings) {
        settings.setValue(Validator.SETTING_DISABLE_STD_LOGGER, Validator.TRUE);
        if (models != null) {
            settings.setValue(Validator.SETTING_MODELNAMES, models);
        }
        if (modeldir != null) {
            settings.setValue(Validator.SETTING_ILIDIRS, modeldir);
        }
        if (configFile != null) {
            settings.setValue(Validator.SETTING_CONFIGFILE, TaskUtils.getFilePath(getProject(), configFile).getAbsolutePath());
        }
        if (forceTypeValidation) {
            settings.setValue(Validator.SETTING_FORCE_TYPE_VALIDATION, Validator.TRUE);
        }
        if (disableAreaValidation) {
            settings.setValue(Validator.SETTING_DISABLE_AREA_VALIDATION, Validator.TRUE);
        }
        if (multiplicityOff) {
            settings.setValue(Validator.SETTING_MULTIPLICITY_VALIDATION,
                    ch.interlis.iox_j.validator.ValidationConfig.OFF);
        }
        if (allObjectsAccessible) {
            settings.setValue(Validator.SETTING_ALL_OBJECTS_ACCESSIBLE, Validator.TRUE);
        }
        if (skipPolygonBuilding) {
            settings.setValue(ch.interlis.iox_j.validator.Validator.CONFIG_DO_ITF_LINETABLES,
                    ch.interlis.iox_j.validator.Validator.CONFIG_DO_ITF_LINETABLES_DO);
        }
        if (logFile != null) {
            settings.setValue(Validator.SETTING_LOGFILE, TaskUtils.getFilePath(getProject(), logFile).getAbsolutePath());
        }
        if (xtflogFile != null) {
            settings.setValue(Validator.SETTING_XTFLOG, TaskUtils.getFilePath(getProject(), xtflogFile).getAbsolutePath());
        }
        if (pluginFolder != null) {
            settings.setValue(Validator.SETTING_PLUGINFOLDER, TaskUtils.getFilePath(getProject(), pluginFolder).getAbsolutePath());
        }
        if (proxy != null) {
            settings.setValue(ch.interlis.ili2c.gui.UserSettings.HTTP_PROXY_HOST, proxy);
        }
        if (proxyPort != null) {
            settings.setValue(ch.interlis.ili2c.gui.UserSettings.HTTP_PROXY_PORT, proxyPort.toString());
        }
    }
}