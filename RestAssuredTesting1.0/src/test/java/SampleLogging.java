import org.apache.logging.log4j.*;

public class SampleLogging {

    private static Logger log=LogManager.getLogger(SampleLogging.class.getName());
    public static void main(String args[]){

            log.info("Object is present");
            log.error("Object is not present");
            log.fatal("this is fatal");

    }
}
