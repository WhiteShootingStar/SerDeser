package eu.glowacki.utp.assignment04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputParser {

	// 1. Use regular expresssions (Pattern) for validating input data
	// U y regularnych wyra e (Pattern) do walidacji danych wej ciowych
	//
	// 2. Convert input string representing date using SimpleDateFormat "yyyy-MM-dd"
	// Konwersj wej ciowego ci gu znak w reprezentuj cego dat nale y oprze np.
	// DateFormat
	// SimpleDateFormat format "yyyy-MM-dd"
	private static final String FIRSTNAME = "(?<firstname>(?:[A-Z][a-z]+))";
	private static final String SURNAME = "(?<surname>(?:[A-Z][a-z]+))";
	private static final String SPACE = "[ \t]+";
	private static final String BEETWEENDATES = "-";
	private static final String YEAR = "(?:[1-2][0-9]{3})";
	private static final String MONTHS = "(?:(?:0[1-9])|(?:1[0-2]))";
	private static final String DAYS = "(?:(?:0[1-9])|(?:[1-2][0-9])|(?:3[0-1]))";

	private static final String DATE_OF_BIRTH = "(?<date>(?:" + YEAR + BEETWEENDATES + MONTHS + BEETWEENDATES + DAYS + "))";
	private static final String PATTERN = FIRSTNAME + SPACE + SURNAME + SPACE + DATE_OF_BIRTH;
	static Pattern pat = Pattern.compile(PATTERN);
	static Matcher match;
	static SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd");

	public static List<Person> parse(File file) {
		List<Person> list = new ArrayList<>();
		
		try {
			String line;
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));
			while ((line = reader.readLine()) != null) {
				match = pat.matcher(line);
				if (match.matches()) {
					try {
						Person temp = new Person(match.group("firstname"), match.group("surname"),
								dates.parse(match.group("date")));

						/*
						 * DateFormat.getDateInstance()
						 * .parse(match.group("Year").concat(match.group("between"))
						 * .concat(match.group("month")).concat(match.group("between"))
						 * .concat(match.group("days"))))
						 */;
						list.add(temp);
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}

			}
			reader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}