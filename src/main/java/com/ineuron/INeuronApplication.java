package com.ineuron;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubspot.dropwizard.guice.GuiceBundle;
import com.ineuron.domain.nlp.service.NLPService;
import com.ineuron.resources.NLPResource;


/**
 * I-Neuron Entry Point!
 * 
 */
public class INeuronApplication extends Application<INeuronConfiguration> {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(INeuronApplication.class);
	
	public static void main(String[] args) throws Exception {
		new INeuronApplication().run(args);
	}

	@Override
	public String getName() {
		return "I-Neuron-NLP";
	}

	@Override
	public void initialize(Bootstrap<INeuronConfiguration> bootstrap) {
		//Guice是Google开发的一个轻量级，基于Java5（主要运用泛型与注释特性）的依赖注入框架(IOC)。
		GuiceBundle<INeuronConfiguration> guiceBundle = GuiceBundle.<INeuronConfiguration>newBuilder()
				.addModule(new INeuronModule())
				.enableAutoConfig(getClass().getPackage().getName())
				.setConfigClass(INeuronConfiguration.class)
				.build();

		bootstrap.addBundle(guiceBundle);
	}

	@Override
	public void run(INeuronConfiguration configuration, Environment environment) {
		NLPService.getInstance();
		LOGGER.info("NLP started successfully!");
		environment.jersey().register(NLPResource.class);
	}

}
