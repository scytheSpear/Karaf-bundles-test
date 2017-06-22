//package test;
// 
//import static org.junit.Assert.*;
//import static org.ops4j.pax.exam.CoreOptions.*;
// 
//import org.junit.runner.RunWith;
//import org.ops4j.pax.exam.Option;
//import org.ops4j.pax.exam.junit.Configuration;
//import org.ops4j.pax.exam.junit.ExamReactorStrategy;
//import org.ops4j.pax.exam.junit.JUnit4TestRunner;
//import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;
//import javax.inject.Inject;
//import org.junit.Test;
//import tick.TickService;
// 
//@RunWith(JUnit4TestRunner.class)
//@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
//public class SimpleTest {
// 
//    @Inject
//    private TickService tickService;
// 
//    @Configuration
//    public Option[] config() {
// 
//        return options(
//            mavenBundle("com.testing", "command", "1.0.0-SNAPSHOT"),
//            bundle("file:/e:/Workplace/command-1.0-SNAPSHOT.jar"),
//            junitBundles()
//            );
//    }
// 
//    @Test
//    public void getHelloService() {
//        assertNotNull(tickService);
//        assertEquals("test", tickService.testmessage());
//    }
//}