package Models;

import com.MAVLink.MAVLinkPacket;

public interface IMessageDataRepository {
	public void pushIncoming(MAVLinkPacket packet);
	public void pushOutgoint(MAVLinkPacket packet);
	public int getFullSetLength();
}
