package fr.benjamindanlos.laptimes.AssettoCorsa.Listener;

import fr.benjamindanlos.laptimes.AssettoCorsa.Handler.DataHandler;
import fr.benjamindanlos.laptimes.UDP.UDP;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

@Service
public class ACListener {

	@Value("${game.port.assettocorsa:9996}")
	private int port;
	@Value("${listenAddress}")
	private String address;

	@Autowired
	private DataHandler handler;

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

			UDP udpListener = new UDP(new InetSocketAddress(address, port), handler);
			try {
				udpListener.bind().closeFuture().sync();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}).start();
	}

}
