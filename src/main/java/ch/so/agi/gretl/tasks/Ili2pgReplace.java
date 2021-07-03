package ch.so.agi.gretl.tasks;

import ch.ehi.ili2db.gui.Config;

public class Ili2pgReplace extends Ili2pgAbstractTask {
    // TODO filesets vs datafile ...
    
    public void execute() {
        Config settings = createConfig();
        int function = Config.FC_REPLACE;

        System.out.println(function);
        
        System.out.println(this.dburi);
        
        // GretlLogAdapter muss execute-Methode haben, dann kann sie auf das Project-Objekt zugreifen!
        
    }
    
    
}
