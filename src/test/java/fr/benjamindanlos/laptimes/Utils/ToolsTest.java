package fr.benjamindanlos.laptimes.Utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Tools.class)
public class ToolsTest {

	@Test
	public void testLaptimeConversion1(){
		int time = 65124;
		String expected = "01:05.124";
		String res = Tools.laptimeToString(time);
		assertEquals(expected, res);
	}

	@Test
	public void testLaptimeConversion2(){
		int time = 75124;
		String expected = "01:15.124";
		String res = Tools.laptimeToString(time);
		assertEquals(expected, res);
	}

	@Test
	public void testLaptimeConversion3(){
		int time = 45124;
		String expected = "00:45.124";
		String res = Tools.laptimeToString(time);
		assertEquals(expected, res);
	}

	@Test
	public void testLaptimeConversion4(){
		int time = 60127;
		String expected = "01:00.127";
		String res = Tools.laptimeToString(time);
		assertEquals(expected, res);
	}

	@Test
	public void testLaptimeConversion5(){
		int time = 61024;
		String expected = "01:01.024";
		String res = Tools.laptimeToString(time);
		assertEquals(expected, res);
	}

	@Test
	public void testLaptimeConversion6(){
		int time = 61204;
		String expected = "01:01.204";
		String res = Tools.laptimeToString(time);
		assertEquals(expected, res);
	}

	@Test
	public void testLaptimeConversion7(){
		int time = 61241;
		String expected = "01:01.241";
		String res = Tools.laptimeToString(time);
		assertEquals(expected, res);
	}

	@Test
	public void testLaptimeConversion8(){
		int time = 612;
		String expected = "00:00.612";
		String res = Tools.laptimeToString(time);
		assertEquals(expected, res);
	}

	@Test
	public void testLaptimeConversion9(){
		int time = 4612;
		String expected = "00:04.612";
		String res = Tools.laptimeToString(time);
		assertEquals(expected, res);
	}

	@Test
	public void testLaptimeConversion10(){
		int time = 4610;
		String expected = "00:04.610";
		String res = Tools.laptimeToString(time);
		assertEquals(expected, res);
	}

	@Test
	public void testLaptimeConversion11(){
		int time = 10000;
		String expected = "00:10.000";
		String res = Tools.laptimeToString(time);
		assertEquals(expected, res);
	}

	@Test
	public void testLaptimeConversion12(){
		int time = 100;
		String expected = "00:00.100";
		String res = Tools.laptimeToString(time);
		assertEquals(expected, res);
	}
}
