package fr.benjamindanlos.laptimes.F12022.Listener;

import fr.benjamindanlos.laptimes.F12022.Decoder.PacketEventDecoder;
import fr.benjamindanlos.laptimes.F12022.Handler.PacketEventHandler;
import fr.benjamindanlos.laptimes.UDP.UDP;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.net.InetSocketAddress;

@Service
public class F12022Listener {

	@Value("${game.port.f12022:20777}")
	private int port;
	@Value("${listenAddress:192.168.1.28}")
	private String address;

	@PostConstruct
	public void init() {
		/*new Thread(()->{
			UDP udpListener = new UDP(new InetSocketAddress(address, port), new PacketEventDecoder(), new PacketEventHandler());
			try {
				udpListener.bind().closeFuture().sync();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}).start();*/
	}
}
