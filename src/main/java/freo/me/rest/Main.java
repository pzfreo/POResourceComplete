package freo.me.rest;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import io.swagger.jersey.listing.ApiListingResourceJSON;

import java.io.File;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class Main extends ResourceConfig {

	
	public Main() {
		packages("freo.me.rest");
		register(ApiListingResourceJSON.class);
		register(SwaggerSerializers.class);
		register(CORSFilter.class);
		
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/");
        beanConfig.setResourcePackage("freo.me.rest");
        beanConfig.setScan(true);
    }


	public static void main(String[] args) throws Exception {
		URI baseUri = UriBuilder.fromUri("http://localhost/").port(8080).build();
		// This is fairly self-explanatory.
		// You can define the URL on which the server will listen.

		ResourceConfig config = new Main();
		// This is where we identify that the class POResource is the JAX-RS
		// Resource (aka Service) that we want to expose.

		Server server = JettyHttpContainerFactory.createServer(baseUri, config);
		// Here is where we create the Jetty Server object.
		
		try {
			server.start();
			
			// This initiates the startup of the server.

			server.join();
			// Wait until the server finishes initiation

		} finally {
			server.destroy();
			// Obvious!
		}

	}
	
	//	    public Set<Class<?>> getClasses() {
	//	        Set<Class<?>> resources = new HashSet<Class<?>>();
	//
	//	        resources.add(POResource.class);
	//
	//	        resources.add(ApiListingResourceJSON.class);
	//	        resources.add(SwaggerSerializers.class);
	//
	//	        return resources;
	//	    }

}
