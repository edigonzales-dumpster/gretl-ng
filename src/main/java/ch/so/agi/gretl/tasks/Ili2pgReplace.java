package ch.so.agi.gretl.tasks;

import ch.ehi.ili2db.gui.Config;
import ch.so.agi.gretl.logging.Ehi2GretlAdapter;
import ch.so.agi.gretl.logging.GretlLogger;
import ch.so.agi.gretl.logging.LogEnvironment;

// TODO: substring... datasets
public class Ili2pgReplace extends Ili2pgAbstractTask {    
    protected GretlLogger log;

    public void execute() {
        log = LogEnvironment.getLogger(Ili2pgReplace.class);
        Ehi2GretlAdapter.init();

        Config settings = createConfig();
        int function = Config.FC_REPLACE;

        // TODO: deal with filesets / datafile(s)
        System.out.println(function);
        
        System.out.println(this.dburi);
        log.info(this.dburi);
        
        
    }
    
    
}
