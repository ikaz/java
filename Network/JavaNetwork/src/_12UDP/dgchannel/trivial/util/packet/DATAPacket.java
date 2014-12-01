package _12UDP.dgchannel.trivial.util.packet;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import _12UDP.dgchannel.trivial.util.PacketType;

/**
 * This represents a Data packet
 *	          2 bytes    2 bytes       n bytes
 *	          ---------------------------------
 *	   DATA  | 03    |   Block #  |    Data    |
 *           ---------------------------------
 * @author icastillejos
 * @version 0.0.1
 */
public final class DATAPacket extends TFTPPacket {
	private short blockNumber;
	private byte[] data;
	
	//This constructor takes data as a Str 
	public DATAPacket(short blockNumber, String dataStr) throws UnsupportedEncodingException {
		this(blockNumber, dataStr.getBytes("US-ASCII"));		
	}
	
	//This constructor takes data as a byte[]	
	public DATAPacket(short blockNumber, byte[] data) {
		super(PacketType.DATA);
		this.blockNumber = blockNumber;
				
		byte[] packetTypeArray = PacketType.DATA.getTypeArray();
		
		ByteBuffer blockNumberBB = ByteBuffer.allocate(2);
		blockNumberBB.putShort(this.blockNumber);
		byte[] blockNumberBytes = blockNumberBB.array();
		
		this.data = data;
		
		this.setArray(packetTypeArray, blockNumberBytes, data);
	}

	public byte[] getData() {
		return data;
	}

	public short getBlockNumber() {
		return blockNumber;
	}
}
