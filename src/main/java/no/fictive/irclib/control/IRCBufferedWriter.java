package no.fictive.irclib.control;

import no.fictive.irclib.model.network.Network;
import no.fictive.irclib.model.network.State;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Espen Jacobsson
 * A class making it easier to send messages to an IRC server.
 */
public class IRCBufferedWriter extends BufferedWriter {
    static Logger logger = Logger.getLogger(IRCBufferedWriter.class);

    private Network network;

	/**
	 * Creates a new IRCBufferedWriter.
	 * @param writer A {@link Writer}
	 */
	public IRCBufferedWriter(Writer writer, Network network) {
		super(writer);
        this.network = network;
	}
	
	
	/**
	 * Writes a line to an IRC server.
	 * @param line Line to write.
	 */
	public void writeline(String line) {
		try {
			this.write(line + "\r\n");
			flush();
		} catch (IOException e) {

            network.setState(State.DISCONNECTED);
            logger.error(String.format("Failed writing to socket. Tried to write '%s'", line));

            if(line == null) return;

            List<Integer> writtenChars = new ArrayList<Integer>();

            if (!line.isEmpty()) {
                for (int i = 0; i < line.length(); i++)
                    writtenChars.add((int) line.charAt(i));

                logger.error(String.format("Line in decimal: %s", writtenChars));
            }
		}
	}
}
