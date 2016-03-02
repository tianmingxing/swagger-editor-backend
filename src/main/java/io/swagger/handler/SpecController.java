package io.swagger.handler;

import io.swagger.inflector.models.RequestContext;
import io.swagger.inflector.models.ResponseContext;
import org.apache.commons.io.FileUtils;

import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaInflectorServerCodegen", date = "2016-03-02T00:56:25.570Z")
public class SpecController  {
    public static String FILENAME;

    static {
        String filename = System.getenv("SWAGGER_FILE");
        if(filename == null) {
            filename = "swagger.yaml";
        }
        FILENAME = filename;

        System.out.println("writing to " + FILENAME);

        File file = new File(FILENAME);
        File parentDir = file.getParentFile();
        if(parentDir != null && !parentDir.exists()) {
            if(!parentDir.mkdirs()) {
                throw new RuntimeException("unable to create file " + FILENAME + " for writing!");
            }
        }

        if(!file.exists()) {
            // write a sample file here
            try {
                ClassLoader classLoader = SpecController.class.getClassLoader();
                File o = new File(classLoader.getResource("default-spec.yaml").getFile());
                Files.copy(o.toPath(), file.toPath());
            }
            catch (Exception e) {
                throw new RuntimeException("unable to create file " + FILENAME + " for writing!");
            }
        }
    }

    public ResponseContext getSpec(RequestContext request) {
        try {
            String swaggerAsString = FileUtils.readFileToString(new File(FILENAME));
            return new ResponseContext()
                    .status(200)
                    .contentType("application/yaml")
                    .entity(swaggerAsString);
        }
        catch (Exception e) {
            return new ResponseContext()
                    .status(500);
        }
    }

    public ResponseContext saveSpec(RequestContext request, String swagger) {
        if(swagger != null && !"".equals(swagger.trim())) {
            try {
                FileUtils.writeStringToFile(new File(FILENAME), swagger);
                return new ResponseContext()
                        .status(Response.Status.OK);
            }
            catch (IOException e) {
                return new ResponseContext().status(Response.Status.INTERNAL_SERVER_ERROR).entity( "Unable to save" );
            }
        }
        return new ResponseContext().status(Response.Status.BAD_REQUEST).entity( "Invalid spec supplied" );
    }
}

