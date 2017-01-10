package com.shc.easyxml;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author brotazoa
 */
public class XmlInputStream
{
	public static final int DEFAULT_CAPACITY = 128;
	public static final char EOF = '\0';

	private BufferedInputStream input;
	private char current;

	public int line;
	public int column;

	public XmlInputStream(InputStream in)
	{
		this(in, DEFAULT_CAPACITY);
	}

	public XmlInputStream(String xmlString)
	{
		this(new ByteArrayInputStream(xmlString.getBytes(Charset.defaultCharset())));
	}

	public XmlInputStream(InputStream in, int capacity)
	{
		this.input = new BufferedInputStream(in, capacity);
		input.mark(capacity);
		try
		{
			this.current = (char) input.read();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	public char getCurrentChar()
	{
		return current;
	}

	public char getNextChar()
	{
		try
		{
			int read = input.read();
			current = read == -1 ? EOF : (char) read;
			column++;
			if (current == '\n' || current == '\r')
			{
				line++;
				column = 0;
			}
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		return getCurrentChar();
	}

	public char peekNextChar()
	{
		try
		{
			input.mark(1);
			int read = input.read();
			char peek = read == -1 ? EOF : (char) read;
			input.reset();
			return peek;
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		return current;
	}

	public char[] peekNextChars(int amount)
	{
		char[] result = new char[amount];
		try
		{
			input.mark(amount);
			for (int i = 0; i < result.length; i++)
			{
				int read = input.read();
				result[i] = read == -1 ? EOF : (char) read;
			}
			input.reset();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		return result;
	}
}
