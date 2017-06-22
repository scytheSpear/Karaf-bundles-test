package com.pax.exam.test;

import com.testing.pax.service.test.NumService;

import java.io.File;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.KarafDistributionOption;
import org.ops4j.pax.exam.karaf.options.LogLevelOption.LogLevel;
import org.ops4j.pax.exam.karaf.options.configs.CustomProperties;
import org.ops4j.pax.exam.options.MavenArtifactUrlReference;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.karaf.features.FeaturesService;
import org.osgi.framework.ServiceReference;

import static org.junit.Assert.*;
import static org.ops4j.pax.exam.CoreOptions.bundle;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;


@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class SampleTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SampleTest.class);

        @Inject
        private FeaturesService featuresService;
        
	@Inject
	private BundleContext bundleContext;
        
	
	@Configuration
	public Option[] config() {
		
		String userHome = System.getProperty("user.home");
		
		MavenArtifactUrlReference karafUrl = CoreOptions.maven().groupId("org.apache.karaf").artifactId("apache-karaf").versionAsInProject().type("tar.gz");
		
		return new Option[]{ KarafDistributionOption.karafDistributionConfiguration().frameworkUrl(karafUrl).name("Apache Karaf").
				unpackDirectory(new File("target/paxexam/unpack")).useDeployFolder(false),
				KarafDistributionOption.keepRuntimeFolder(),
				
//				CoreOptions.maven().groupId("org.apache.karaf").artifactId("apache-karaf").type("zip").version("4.0.3"))
//				.karafVersion("4.0.3").name("Apache Karaf").useDeployFolder(false),
				CoreOptions.keepCaches(),
				
				
				CoreOptions.systemProperty("org.ops4j.pax.logging.DefaultServiceLog.level").value("DEBUG"),

				// TODO define settings.xml if behind a proxy
//				CoreOptions.systemProperty("org.ops4j.pax.url.mvn.settings").value(userHome + "/.m2/settings.xml"),
				
				CoreOptions.systemTimeout(30000),
				KarafDistributionOption.editConfigurationFilePut(CustomProperties.KARAF_FRAMEWORK, "equinox"),
				// disable JMX RBAC security, thanks to the KarafMBeanServerBuilder
				KarafDistributionOption.configureSecurity().disableKarafMBeanServerBuilder(),
                KarafDistributionOption.logLevel(LogLevel.INFO),
                
                bundle(
                        "file:/e:/Workplace/pax-service-test-1.0-SNAPSHOT.jar"),
//                       features(
//                        "mvn:valeri.blog.examples.pax-exam-servicemix/pax-exam-servicemix-features/1.0.0-SNAPSHOT/xml/features",
//                        "pax-exam-servicemix"),
//                       features(
//                        "mvn:org.apache.camel/camel-example-osgi/2.10.0/xml/features",
//                               "camel-example-osgi"),
                 //features(  features(
//                        "mvn:org.apache.camel/camel-example-osgi/2.10.0/xml/features",
//                               "camel-example-osgi"),maven().groupId("org.apache.camel.karaf").artifactId("apache-camel").type("xml").classifier("features").version("2.12.1"), "camel-blueprint", "camel-test"),
                      
                mavenBundle()
                .groupId("com.testing")
                .artifactId("pax-service-test")
                .version("1.0-SNAPSHOT").start(),
                        
		};
	}

	@Test
	public void test() throws Exception {
		LOGGER.info("++++ Executing the test!!!!");
//                bundleContext.
		//Assert.assertNotNull(bundleContext);
        ServiceReference serviceReference = bundleContext.getServiceReference(NumService.class.getName());
        assertNotNull(serviceReference);
        
        NumService service = (NumService) bundleContext.getService(serviceReference);
        assertNotNull(service);
        try {
            assertEquals( 2 , service.testadd(1,1));
        }
        finally {
            bundleContext.ungetService(serviceReference);
        }
        try {
            assertEquals("abc", service.testString("a", "bc"));
        }
        finally {
            bundleContext.ungetService(serviceReference);
        }
        
        
//        org.osgi.service.cm.Configuration configuration = configurationAdmin.getConfiguration(
//                "valeri.blog.example.pax-exam-servicemix", null);
//        
//        assertNull(configuration.getProperties());
                
                
               // Assert.assertFalse(featuresService.isInstalled(featuresService.getFeature("pax-exam-servicemix")));
               
//               org.osgi.service.cm.Configuration configuration = configurationAdmin.getConfiguration(
//                "valeri.blog.example.pax-exam-servicemix", null);
//        
//        assertNull(configuration.getProperties());
//        
//        Dictionary<String, Object> dict = new Hashtable<String, Object>();
//        dict.put("hello", "Hola");
//        
//        configuration.update(dict);
//        
//        // Wait a little because the configuration event is asynchronous.
//        Thread.sleep(2000l);
//        
//        
//        serviceReference = bundleContext.getServiceReference(Service.class.getName());
//        assertNotNull(serviceReference);
//        
//        service = (Service) bundleContext.getService(serviceReference);
//        assertNotNull(service);
//        try {
//            assertEquals("Hola Bob.", service.sayHello("Bob"));
//        } finally {
//            bundleContext.ungetService(serviceReference);
//        }

//backup URL:https://github.com/DavidValeri/blog-pax-exam-servicemix/blob/master/itest/src/test/java/valeri/blog/examples/pax_exam_servicemix/impl/DefaultServiceIT.java
//URL2:https://github.com/ops4j/org.ops4j.pax.exam2/blob/master/samples/exam-itest-sample-karaf/src/test/java/org/ops4j/pax/exam/sample/karaf/CalculatorITest.java#L74

	}
}
