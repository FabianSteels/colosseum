
package components.zeromq;

import components.messaging.MessageBase;
import org.zeromq.ZMQ;
import play.Logger;
import play.Play;

public class BackendPublisher {
    private ZMQ.Socket socket;

    private BackendPublisher() {
        Logger.debug("Initializing ZMQ BackendPublisher");
        ZMQ.Context context = ZMQ.context(1);
        this.socket = context.socket(ZMQ.PUB);
        String server = Play.application().configuration().getString("backend.zmq.publisher");
        this.socket.bind(server);
        Logger.debug("Publishing data on " + server);
    }

    /** Holder */
    private static class BackendPublisherHolder {
        private final static BackendPublisher instance = new BackendPublisher();
    }

    public static BackendPublisher getInstance()
    {
        return BackendPublisherHolder.instance;
    }

    public void publish(String queue, MessageBase message) {
        try {
            Logger.debug((String)("Publishing: " + queue + " " + message.toString()));
            this.socket.send(message.toJson().getBytes(), 0);
            Logger.debug((String)"Message sent");
        }
        catch (Exception ex) {
            Logger.error((String)("Cannot publish on backend: " + ex));
        }
    }
}