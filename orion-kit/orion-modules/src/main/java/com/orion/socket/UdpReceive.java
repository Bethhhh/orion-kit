package com.orion.socket;

import com.orion.utils.io.Streams;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * UDP Server 接收
 *
 * @author ljh15
 * @version 1.0.0
 * @date 2020/6/5 16:35
 */
public class UdpReceive {

    /**
     * 端口
     */
    private int port;

    private DatagramSocket ds;

    public UdpReceive(int port) throws IOException {
        this.port = port;
        this.ds = new DatagramSocket(port);
        this.ds.setSendBufferSize(4 * 1024);
        this.ds.setSoTimeout(10 * 1000);
    }

    public UdpReceive bufferSize(int bufferSize) throws SocketException {
        this.ds.setSendBufferSize(bufferSize);
        return this;
    }

    public UdpReceive timeout(int timeOut) throws SocketException {
        this.ds.setSoTimeout(timeOut);
        return this;
    }

    public int receive(byte[] bs) throws IOException {
        DatagramPacket packet = new DatagramPacket(bs, bs.length);
        ds.receive(packet);
        return packet.getLength();
    }

    public int receive(byte[] bs, int len) throws IOException {
        DatagramPacket packet = new DatagramPacket(bs, len);
        ds.receive(packet);
        return packet.getLength();
    }

    public int receive(byte[] bs, int off, int len) throws IOException {
        DatagramPacket packet = new DatagramPacket(bs, off, len);
        ds.receive(packet);
        return packet.getLength();
    }

    public DatagramPacket receivePacket(byte[] bs) throws IOException {
        DatagramPacket packet = new DatagramPacket(bs, bs.length);
        ds.receive(packet);
        return packet;
    }

    public DatagramPacket receivePacket(byte[] bs, int len) throws IOException {
        DatagramPacket packet = new DatagramPacket(bs, len);
        ds.receive(packet);
        return packet;
    }

    public DatagramPacket receivePacket(byte[] bs, int off, int len) throws IOException {
        DatagramPacket packet = new DatagramPacket(bs, off, len);
        ds.receive(packet);
        return packet;
    }

    public void close() {
        Streams.closeQuietly(ds);
    }

    public int getPort() {
        return port;
    }

    public DatagramSocket getDatagramSocket() {
        return ds;
    }

}