package eu.glowacki.utp.assignment04.comparators;

import java.util.Comparator;

import eu.glowacki.utp.assignment04.Person;

public class FirstNameComparator implements Comparator<Person> {

	@Override
	public int compare(Person person1, Person person2) {
		// TODO Auto-generated method stub
		return person1.get_firstName().compareTo(person2.get_firstName());
	}
}