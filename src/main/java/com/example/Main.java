package com.example;

import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;


import javax.servlet.ServletContext;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
	public static String url = "http://0.0.0.0:8080/server/";
    public static final String BASE_URI = url;
    public static NaiveBayesModel model;
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
    	
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example package
        final ResourceConfig rc = new ResourceConfig().packages("com.example");
        rc.register(JacksonFeature.class);
        //rc.register(ServletContext.class);
        
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
    	 
        final HttpServer server = startServer();
        
        System.out.println(String.format("Prediction server in ascolto su "+url+"\nPremi invio per stopparlo...\n", BASE_URI));
        System.in.read();
        server.stop();
    }
}

