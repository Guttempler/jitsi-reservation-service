package org.rapidpm.demo.jitsi.reservation;

import io.javalin.Javalin;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import static java.lang.System.setProperty;

public class ReservationService {


  public static final String CLI_HOST = "host";
  public static final String CLI_PORT = "port";

  public static final String CORE_REST_SERVER_HOST_DEFAULT = "0.0.0.0";
  public static final String CORE_REST_SERVER_PORT_DEFAULT = "7000";

  public static final String CORE_REST_SERVER_HOST = "core-ui-server-host";
  public static final String CORE_REST_SERVER_PORT = "core-ui-server-port";


  public static final String CONFERENCE = "conference";

  private ReservationService() {
  }


  public static void main(String[] args) throws ParseException {

    final Options options = new Options();
    options.addOption(CLI_HOST, true, "host to use");
    options.addOption(CLI_PORT, true, "port to use");

    DefaultParser parser = new DefaultParser();
    CommandLine   cmd    = parser.parse(options, args);

    setProperty(CORE_REST_SERVER_HOST, CORE_REST_SERVER_HOST_DEFAULT);
    setProperty(CORE_REST_SERVER_PORT, CORE_REST_SERVER_PORT_DEFAULT);

    if (cmd.hasOption(CLI_HOST)) {
      setProperty(CORE_REST_SERVER_HOST, cmd.getOptionValue(CLI_HOST));
    }
    if (cmd.hasOption(CLI_PORT)) {
      setProperty(CORE_REST_SERVER_PORT, cmd.getOptionValue(CLI_PORT));
    }

    Javalin app = Javalin.create()
                         .start(Integer.parseInt(System.getProperty(CORE_REST_SERVER_PORT)));

    app.get("/" + CONFERENCE, ctx -> {
      ctx.result("Hello World");
    });
  }
}
