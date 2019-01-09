package eu.glowacki.utp.assignment04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import eu.glowacki.utp.assignment04.comparators.BirthdateComparator;
import eu.glowacki.utp.assignment04.comparators.FirstNameComparator;

public final class PersonDatabase {
	private final List<Person> _PEOPLE;
	private final Map<Date, List<Person>> _map;

	public PersonDatabase(File file) {
		this(InputParser.parse(file));
	}

	// assignment 8 - factory method based on deserialization
	public static PersonDatabase deserialize(DataInputStream input) throws Assignment08Exception {
		List<Person> peop =null;
		try {
			int temp =input.readInt();
			peop= new ArrayList<>();
			for(int i=0;i<temp;i++) {
				peop.add(Person.deserialize(input));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new PersonDatabase(peop);
	}

	// assignment 8
	public void serialize(DataOutputStream output) throws Assignment08Exception {
		try {
			output.writeInt(_PEOPLE.size());
			for (Person person : _PEOPLE) {
				person.serialize(output);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public PersonDatabase(List<Person> list) {
		_PEOPLE = list;
		_map = _PEOPLE //
				.stream() //
				.collect(Collectors.groupingBy(Person::get_birthdate, TreeMap::new,
						Collectors.mapping(p -> p, Collectors.toList())));
	}

	public List<Person> sortedByFirstName() {

		Collections.sort(_PEOPLE, new FirstNameComparator());

		return _PEOPLE;
	}

	public List<Person> sortedBySurnameFirstNameAndBirthdate() {
		Collections.sort(_PEOPLE, Comparator.naturalOrder());
		return _PEOPLE;
	}

	public List<Person> sortedByBirthdate() {
		Collections.sort(_PEOPLE, new BirthdateComparator());
		return _PEOPLE;
	}

	public List<Person> bornOnDay(Date date) {
		return _PEOPLE.stream().filter(e -> e.get_birthdate().compareTo(date) == 0).collect(Collectors.toList());
	}

	public List<Person> bornOnDayMap(Date date) {
		return _map.get(date);
	}

	public List<Person> get_PEOPLE() {
		return _PEOPLE;
	}
}