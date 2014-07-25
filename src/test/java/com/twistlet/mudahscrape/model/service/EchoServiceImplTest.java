package com.twistlet.mudahscrape.model.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.twistlet.mudahscrape.model.service.EchoServiceImpl;

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
