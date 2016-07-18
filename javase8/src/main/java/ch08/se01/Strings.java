package ch08.se01;

import org.junit.Test;

import java.time.ZoneId;
import java.util.logging.Logger;

public class Strings {

    @Test
    public void join() {
        Logger log = Logger.getGlobal();
        String joined = String.join("/", "usr", "local", "bin");
        log.info(joined);

        String ids = String.join(", ", ZoneId.getAvailableZoneIds());
        log.info(ids);
    }

}
