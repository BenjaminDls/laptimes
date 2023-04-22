package fr.benjamindanlos.laptimes.F1.Listener;

import fr.benjamindanlos.laptimes.F1.Decoder.PacketEventDecoder;
import fr.benjamindanlos.laptimes.UDP.UDP;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

@Service
public class F1Listener {

	@Value("${game.port.f12022:20777}")
	private int port;
	@Value("${listenAddress}")
	private String address;

	@Autowired
	private PacketEventDecoder packetEventDecoder;

	@PostConstruct
	public void init() {
		new Thread(()->{
			if(this.address==null || this.address.isEmpty()){
				try {
					this.address = InetAddress.getLocalHost().getHostAddress();
				} catch (UnknownHostException e) {
					throw new RuntimeException(e);
				}
			}

			UDP udpListener = new UDP(new InetSocketAddress(address, port), packetEventDecoder);
			try {
				udpListener.bind().closeFuture().sync();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}).start();
	}
}
