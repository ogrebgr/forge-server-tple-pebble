package com.bolyartech.forge.server.tple.pebble;

import com.bolyartech.forge.server.misc.TemplateEngine;
import com.bolyartech.forge.server.misc.TemplateEngineFactory;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ClasspathLoader;
import com.mitchellbosecke.pebble.loader.Loader;


public class PebbleTemplateEngineFactory implements TemplateEngineFactory {
    private final PebbleEngine mEngine;


    public PebbleTemplateEngineFactory(Loader loader) {
        mEngine = new PebbleEngine.Builder().loader(loader).build();
    }


    public PebbleTemplateEngineFactory(String templatePathPrefix) {
        Loader loader = new ClasspathLoader();
        loader.setPrefix(templatePathPrefix);
        mEngine = new PebbleEngine.Builder().loader(loader).build();
    }


    @Override
    public TemplateEngine createNew() {
        return new PebbleTemplateEngine(mEngine);
    }
}
