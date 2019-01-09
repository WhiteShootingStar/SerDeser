package eu.glowacki.utp.assignment04.test;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import eu.glowacki.utp.assignment04.InputParser;
import eu.glowacki.utp.assignment04.Person;

public final class InputParserTest {

	File file = new File("kek.txt");

	
	private List<Person> _people = InputParser.parse(file);

	
	@Test
	public void test() {
		Assert.assertEquals(5, _people.size());	
	}
	
}