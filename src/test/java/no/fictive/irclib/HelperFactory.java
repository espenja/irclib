package no.fictive.irclib;

import no.fictive.irclib.control.IRCEventPacket;
import no.fictive.irclib.event.container.command.*;
import no.fictive.irclib.model.network.Network;

/**
 * Thiss class contains methods which makes making unit tests easier for projects using irclib.
 *
 * @author Roy Sindre Norangshol
 */
public class HelperFactory {

    public static Network createNetwork(String networkAlias) {
        Network network = new Network();
        network.setServerAlias(networkAlias);
        return network;
    }

    public static IRCEventPacket makePacket(String rawline) {
        return new IRCEventPacket(rawline);
    }

    public static JoinEvent createJoinEvent(String network, String channel, String nick) {
        return new JoinEvent(makePacket(String.format(":%s!ident@unit.test.hostname.%s.%s JOIN %s", nick, network,nick, channel)), createNetwork(network));
    }

    public static PartEvent createPartEvent(String network, String channel, String nick, String message) {
        return new PartEvent(makePacket(String.format(":%s!ident@unit.test.hostname.%s.%s PART %s :%s", nick, network, nick, channel, message)), createNetwork(network));
    }

    public static QuitEvent createQuitEvent(String network, String nick, String message) {
        return new QuitEvent(makePacket(String.format(":%s!ident@unit.test.hostname.%s.%s QUIT :%s", nick, network, nick, message)), createNetwork(network));
    }

    public static NickEvent createNickEvent(String network, String oldNick, String newNick) {
        return new NickEvent(makePacket(String.format(":%s!ident@unit.test.hostname.%s.%s NICK :%s", oldNick, network, oldNick, newNick)), createNetwork(network));
    }

    public static PrivMsgEvent createPrivMsgEvent(String network, String nick, String target, String message) {
        return new PrivMsgEvent(makePacket(String.format(":%s!ident@unit.test.hostname.%s.%s PRIVMSG %s :%s", nick, network, nick, target, message)), createNetwork(network));
    }

    /**
     * @todo create rest of the *Event factory methods.
     */

}
