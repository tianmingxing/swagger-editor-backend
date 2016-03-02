# Swagger Editor Backend

This project will allow back-end storage of files from the [swagger-editor](https://github.com/swagger-api/swagger-editor) application. 
It creates a single WAR file which can be embedded in a container or run directly with the [jetty-runner](http://repo1.maven.org/maven2/org/eclipse/jetty/jetty-runner/9.2.9.v20150224/jetty-runner-9.2.9.v20150224.jar) library.

The output file is configurable by setting an environment variable:

```
SWAGGER_FILE=path/to/output/swagger.yaml
```

### Building

To build the war file:

```
mvn clean package
```

To run the war with the [jetty-runner](http://repo1.maven.org/maven2/org/eclipse/jetty/jetty-runner/9.2.9.v20150224/jetty-runner-9.2.9.v20150224.jar):

```
wget http://repo1.maven.org/maven2/org/eclipse/jetty/jetty-runner/9.2.9.v20150224/jetty-runner-9.2.9.v20150224.jar
SWAGGER_FILE=output/swagger.yaml java -jar jetty-runner-9.2.9.v20150224.jar target/swagger-editor-backend-1.0.0.war
```

This will write to `output/swagger.yaml` and start the application on the default port of 8080.  Opening a browser to
this port will show the editor--the `output/swagger.yaml` will be loaded and changes will write directly to that file.


# License

Copyright 2016 SmartBear Software

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
