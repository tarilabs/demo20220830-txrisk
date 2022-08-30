///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.kie.yard:yard-impl1:1.0-SNAPSHOT
//DEPS org.kie:kie-dmn-backend:8.24.0.Beta

import java.io.File;

import org.drools.util.IoUtils;
import org.kie.dmn.api.marshalling.DMNMarshaller;
import org.kie.dmn.backend.marshalling.v1x.DMNMarshallerFactory;
import org.kie.dmn.model.api.Definitions;
import org.kie.yard.impl1.YaRDParser;

class convert {
    public static void main(String[] args) throws Exception {
        String yamlDecision = IoUtils.readFileAsString(new File("src/main/resources/txrisk.yard.yml"));
    
        DMNMarshaller dmnMarshaller = DMNMarshallerFactory.newDefaultMarshaller();
        YaRDParser parser = new YaRDParser();
        
        Definitions definitions = parser.parse(yamlDecision);
        String xml = dmnMarshaller.marshal(definitions);
        IoUtils.write(new File("src/main/resources/txrisk.yard.dmn"), xml.getBytes());
    }
}