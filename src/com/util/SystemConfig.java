/*
 * Copyleft 2011 Power by colen.
 *
 * Project: booking
 * Date: Jul 2, 2011
 */
package com.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;


/**
 * System Configure (can auto reload)
 *
 * @author colen
 *
 */
public class SystemConfig {

    /** Constructor */
    private SystemConfig() {}
    /** Instance. */
    private static SystemConfig instance = new SystemConfig();
    /** Properties */
    private volatile Properties props = null;
    /** Is initialized */
    private boolean initialized = false;

    
    /**
     * Get Instance.
     *
     * @return instance.
     */
    public static SystemConfig getInstance() {
    	
        return instance;
    }

    
    /**
     * Initialized
     *
     * @param f sysconfig.properties
     * @param reload
     */
    public boolean init() {
        if (this.initialized) {
          //  LOG.info("SystemConfig already initialized!!");
            return false; 
        }
 
        this.initialized = loadFile();
        
        // out >>>
        return true;
    }


    /**
     * Load File.
     *
     * @return success
     */
    private boolean loadFile() {
      
        try {
            // Properties
            Properties myprops = new Properties();
            
            myprops.load(SystemConfig.class.getResourceAsStream("config.properties"));
          //  myprops.load(inStream);

            // refresh
            this.props = myprops;
        } catch (IOException e) {
            
            return false;
        }
        
        // out >>>
        return true;
    }


    /**
     * Get Value By Key
     *
     * @param key Key.
     * @return value
     */
    public static String getValue(String key) {
        if (!instance.initialized) {
          //  LOG.error("System Configure did not initialized, but illegal invoke!");
            throw new IllegalStateException("System Configure did not Initialized!");
        }
        return (String) instance.props.get(key);
    }
    
    
    /**
     * Get Value By Key And
     *
     * @param key
     * @param args
     * @return
     */
    public static String get(String key, Object ... args) {
        String value = getValue(key);
        if (args != null && args.length > 0) {
            return MessageFormat.format(value, args);
        } else {
            return value;
        }
    }
    
    
    
    /**
     * get long value
     *
     * @param key
     * @return
     */
    public static long getLong(String key) {
        return Long.valueOf(getValue(key));
    }
    
    /**
     * get long value
     *
     * @param key
     * @return
     */
    public static int getInteger(String key) {
        return Integer.valueOf(getValue(key));
    }
    
    /**
     * get boolean value
     *
     * @param key
     * @return
     */
    public static boolean getBoolean(String key) {
        return Boolean.valueOf(getValue(key));
    }

    
}
