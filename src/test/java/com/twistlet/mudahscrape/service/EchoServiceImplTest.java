package com.twistlet.mudahscrape.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class EchoServiceImplTest {

	@Test
	public void testEcho() {
		EchoServiceImpl sut = new EchoServiceImpl("--XX--");
		assertEquals("--XX--", sut.echo());
	}

	@Test
	public void testEchoAgain() {
		EchoServiceImpl sut = new EchoServiceImpl("Ping!");
		assertEquals("Ping!", sut.echo());
	}

}
