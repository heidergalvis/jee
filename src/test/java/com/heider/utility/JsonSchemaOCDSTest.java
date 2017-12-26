package com.heider.utility;

import com.heider.dto.Record;
import com.heider.dto.Release;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class JsonSchemaOCDSTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private JsonSchemaValidator jsonSchemaValidator = null;

    @Before
    public void before() throws Exception {

        String resourceNameFile = "/release-package-schema.json";
        this.jsonSchemaValidator = new JsonSchemaValidator(resourceNameFile);

    }

    @After
    public void after() { }

    @Test
    public void validateOkTenderJsonOCDS() throws Exception {

        InputStream inputStreamRectangle =
                this.getClass().getResourceAsStream(
                        "/ocds-213czf-000-00001-02-tender.json");
        JSONObject rawRelease = new JSONObject(
                new JSONTokener(inputStreamRectangle));

        boolean validationState =
                this.jsonSchemaValidator.validate(rawRelease);

        Assert.assertTrue(validationState);
    }

    @Test
    public void validateOkTenderJsonOCDSbuiltToTemplate() {

        String expected = null;
        InputStream inputStreamTender =
                this.getClass().getResourceAsStream(
                        "/ocds-213czf-000-00001-02-tender.json");
        JSONObject rawRelease = new JSONObject(
                new JSONTokener(inputStreamTender));


        expected = rawRelease.toString();


        JSONObject rawReleaseActual = this.getWriter("ocds-tender.vm");

        String actual = rawReleaseActual.toString();

        Assert.assertEquals(expected, actual);

    }


    private JSONObject getWriter(String pathTemplateName)  {

        Record record = new Record();
        Release release = new Release();
        release.setId("ocds-213czf-000-00001-02-tender");
        release.setOcid("ocds-213czf-000-00001");
        release.setTag(new String[]{"tender"});

        List<Release> releases = new ArrayList<>();
        releases.add(release);
        record.setReleases(releases);


        /**
         * Prepare context data
         */
        VelocityContext context = new VelocityContext();

        context.put("record", record);


        StringWriter swOut = new StringWriter();
        Reader templateReader = new BufferedReader(
                new InputStreamReader(this.getStream(pathTemplateName)));


        /**
         * Merge data and template
         */
        Velocity.evaluate(context, swOut, "log tag name", templateReader);

        JSONObject rawRelease = new JSONObject(
                new JSONTokener(swOut.toString()));

        return rawRelease;
    }

    private static InputStream getStream(String resource) {
        return JsonSchemaOCDSTest.class.getClassLoader().getResourceAsStream(resource);
    }



}
