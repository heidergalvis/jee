package com.heider.utility;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

public class JsonSchemaValidator {

    private Schema schema = null;

    public JsonSchemaValidator(String resourceNameFile) throws Exception {
        InputStream inputStreamJsonSchema =
                this.getClass().getResourceAsStream(resourceNameFile);
        JSONObject rawSchema =
                new JSONObject(new JSONTokener(inputStreamJsonSchema));
        this.schema = SchemaLoader.load(rawSchema);
    }


    public boolean validate(JSONObject jsonObject) throws ValidationException {
        schema.validate(jsonObject);
        return true;
    }
}
