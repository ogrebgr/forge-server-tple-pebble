package com.bolyartech.forge.server.tple.pebble;

import com.bolyartech.forge.server.misc.TemplateEngine;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.loader.ClasspathLoader;
import com.mitchellbosecke.pebble.loader.Loader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


public class PebbleTemplateEngine implements TemplateEngine {
    private final PebbleEngine mEngine;
    private final Map<String, Object> mAttributes = new HashMap<>();


    public PebbleTemplateEngine(PebbleEngine engine) {
        mEngine = engine;
    }


    @Override
    public void assign(String varName, Object object) {
        mAttributes.put(varName, object);
    }


    @Override
    public String render(String templateName) {
        StringWriter writer = new StringWriter();

        PebbleTemplate template = null;
        try {
            template = mEngine.getTemplate(templateName);
            template.evaluate(writer, mAttributes);
        } catch (PebbleException | IOException e) {
            throw new RuntimeException(e);
        }

        return writer.toString();
    }
}
