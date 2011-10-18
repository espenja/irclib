package no.fictive.irclib.control;

import no.fictive.irclib.model.network.Network;
import no.fictive.irclib.model.user.Profile;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;

/**
 * @author Espen Jacobsson
 * Manages {@link Network}s.
 */
public class ConnectionManager {
	
	Profile profile;
	Vector<Network> networks;
	
	/**
	 * 
	 * @param profile A profile representing the user information for 
	 */
	public ConnectionManager(Profile profile) {
		this.profile = profile;
		networks = new Vector<Network>();
	}
	
	
	/**
	 * Requests a connection to an irc server.
	 * @param server Server to connect to.
	 * @param port Port to connect to the server with.
	 * @param serveralias An alias to identify the server with.
	 * @return A new {@link Network}.
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public Network requestConnection(String server, int port, String serveralias) throws UnknownHostException, IOException {
		Network network = new Network(server, port, serveralias, profile);
		network.connect();
		return network;
	}

	/**
	 * Requests a connection to an irc server.
	 * @param server Server to connect to.
	 * @param port Port to connect to the server with.
     * @param bindAddress Address to bind socket to.
	 * @param serveralias An alias to identify the server with.
	 * @return A new {@link Network}.
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public Network requestConnection(String server, int port, InetAddress bindAddress, String serveralias) throws UnknownHostException, IOException {
		Network network = new Network(server, port, bindAddress, serveralias, profile);
		network.connect();
		return network;
	}
	
	
	/**
	 * Removes a session from the manager
	 * @param {@link Network} to be removed.
	 */
	public void removeNetwork(Network network) {
		network.disconnect();
		networks.remove(network);
	}
	
	
	/**
	 * Returns all {@link Network}s currently in the manager.
	 * @return All {@link Network}s currently in the manager.
	 */
	public Vector<Network> getNetworks() {
		return networks;
	}
	
	public void addNetwork(Network network) {
		if(!networks.contains(network)) {
			networks.add(network);
		}
	}
}
