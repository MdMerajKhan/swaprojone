package com.example.springsecuritybatchjpa.batch;

import com.example.springsecuritybatchjpa.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class PersonItemProcessor  implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    public Date calculateDOB(int age) {
        LocalDate current_date = LocalDate.now();
        int year = current_date.getYear() - age;
        LocalDate date1 = LocalDate.of(year,1,1);
        Date dob = Date.valueOf(date1);
        return dob;
    }

    @Override
    public Person process(final Person person) throws Exception {
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();
        final double gpa = person.getGpa();
        final int age = person.getAge();
        final Date dob = calculateDOB(age);
        person.setDob(dob);
        SimpleDateFormat sdf = new SimpleDateFormat(
                "MM-dd-yyyy");
        System.out.println(String.format("Converted from [%d] to [%s]",age,sdf.format(dob)));

        final Person transformedPerson = new Person(firstName, lastName, gpa, dob);

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return person;
    }
}
