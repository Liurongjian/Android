package com.rong.common.http;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import okhttp3.Dns;

/**
 * Created by rong on 2017/5/22.
 */

public class DnsClient implements Dns {
	@Override
	public List<InetAddress> lookup(String hostname) throws UnknownHostException {

		//使用dns服务器
		return Dns.SYSTEM.lookup(hostname);
	}
}
