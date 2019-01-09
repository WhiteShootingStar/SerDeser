package eu.glowacki.utp.assignment04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

//import eu.glowacki.utp.assignment08.Assignment08Exception;
//import eu.glowacki.utp.assignment08.Person;

public class Person implements Comparable<Person> {

	private final String _firstName;
	private final String _surname;
	private final Date _birthdate;

	public Person(String firstName, String surname, Date birthdate) {
		_firstName = firstName;
		_surname = surname;
		_birthdate = birthdate;
	}

	// assignment 8
	public void serialize(DataOutputStream output) throws Assignment08Exception {
		// serialize birth date with getTime() method
		// encapsulate IOException in Assignment08Exception
		try {
			
			output.writeUTF(_firstName);
			output.writeUTF(_surname);

			output.writeLong(_birthdate.getTime());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new Assignment08Exception("kek", e);
		}
	}

	public static Person deserialize(DataInputStream input) throws Assignment08Exception {
		String name = null;
		String surname = null;
		Date date = null;
		try {
			name = input.readUTF();
			surname = input.readUTF();
			date = new Date(input.readLong());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new Person(name, surname, date);
	}

	@Override
	public int compareTo(Person otherPerson) {
		if (_surname.compareTo(otherPerson._surname) != 0) {
			return _surname.compareTo(otherPerson._surname);
		}

		if (_firstName.compareTo(otherPerson._firstName) != 0) {
			return _firstName.compareTo(otherPerson._firstName);
		}

		return _birthdate.compareTo(otherPerson._birthdate);
	}

	public String get_firstName() {
		return _firstName;
	}

	public String get_surname() {
		return _surname;
	}

	public Date get_birthdate() {
		return _birthdate;
	}

	@Override
	public String toString() {
		return get_surname() + "  " + get_firstName() + "   " + get_birthdate();
	}
}