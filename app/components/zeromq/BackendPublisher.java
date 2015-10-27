package components.zeromq;

import components.messaging.MessageBase;
import org.zeromq.ZMQ;
import play.Logger;
import play.Play;

/**
 * Created by fs on 27/10/15.
 */
public class BackendPublisher {

        private ZMQ.Socket socket;

        private BackendPublisher()
        {
                Logger.debug("Initializing ZMQ BackendPublisher");
                ZMQ.Context context = ZMQ.context(1);
                socket = context.socket(ZMQ.REQ);
                String server = Play.application().configuration().getString("backend.zmq.subscriber");
                socket.connect(server);
                Logger.debug("ZMQ BackendPublisher connected to "+server);
        }

        /** Holder */
        private static class ZeroMQPublisherHolder
        {
            private final static BackendPublisher instance = new BackendPublisher();
        }

        public static BackendPublisher getInstance()
        {
            return ZeroMQPublisherHolder.instance;
        }

        public void publish(String queue, MessageBase message){
                Logger.debug("Publishing to backend: "+queue+" "+message.toString());
                socket.send(message.toString().getBytes(), 0);
                byte[] reply = socket.recv(0);
                Logger.debug("Received " + new String (reply));
        }


}
