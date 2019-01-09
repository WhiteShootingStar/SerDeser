package eu.glowacki.utp.assignment04.test;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import eu.glowacki.utp.assignment04.Assignment08Exception;
import eu.glowacki.utp.assignment04.InputParser;
import eu.glowacki.utp.assignment04.Person;
import eu.glowacki.utp.assignment04.PersonDatabase;

public class PersonDatabaseTest {

	File file = new File("kek.txt");
	PersonDatabase databse = null;

	@Test
	public void bases() {
		SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd");
		databse = new PersonDatabase(file);
		System.out.println(databse.sortedByBirthdate());
		System.out.println(databse.sortedBySurnameFirstNameAndBirthdate());
		System.out.println(databse.sortedByFirstName());
		try {
			System.out.println(databse.bornOnDay(dates.parse("1967-02-03")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(databse.bornOnDayMap(dates.parse("1967-02-03")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			databse.serialize(new DataOutputStream(new FileOutputStream("BASA.OMEGALUL")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Assignment08Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Assert.assertEquals(5, (PersonDatabase
					.deserialize(new DataInputStream(new FileInputStream("BASA.OMEGALUL"))).get_PEOPLE().size()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Assignment08Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
}