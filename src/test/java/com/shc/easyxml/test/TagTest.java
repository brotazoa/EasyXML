package com.shc.easyxml.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.shc.easyxml.Xml;
import com.shc.easyxml.XmlTag;

/**
 * @author Sri Harsha Chilakpati
 */
public class TagTest
{
	private static final String test1 = Util.readToString("Test1.xml");

	@Test
	public void testParse()
	{
		Xml.parse(test1);
	}

	@Test
	public void testRootTagName()
	{
		XmlTag root = Xml.parse(test1);
		assertEquals(root.name, "test");
	}

	@Test
	public void testInputStreamParse()
	{
		Xml.parse(Util.getInputStream("Test1.xml"));
	}

	@Test
	public void testInputStreamRootTagName()
	{
		XmlTag root = Xml.parse(Util.getInputStream("Test1.xml"));
		assertEquals(root.name, "test");
	}
}
