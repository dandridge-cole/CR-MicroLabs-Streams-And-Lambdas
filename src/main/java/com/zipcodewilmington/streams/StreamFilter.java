package com.zipcodewilmington.streams;

import com.zipcodewilmington.streams.anthropoid.Person;
import com.zipcodewilmington.streams.anthropoid.PersonFactory;
import com.zipcodewilmington.streams.conversions.ArrayConverter;
import com.zipcodewilmington.streams.conversions.StreamConverter;
import com.zipcodewilmington.streams.tools.RandomUtils;
import com.zipcodewilmington.streams.tools.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by leon on 5/2/17.
 */
public class StreamFilter {
    private final Stream<Person> personStream;
    public final String startingCharacter;
    StreamConverter sc;

    /**
     * No arg constructor
     */ //TODO - construct person stream of 100 person objects; startingCharacter is a random capital letter
    public StreamFilter() {
        this(new PersonFactory().createPersonList(100), RandomUtils.createCharacter('A','Z'));
    }

    /**
     * @param people - Array of person objects
     * @param startingCharacter - character to filter by
     */ //TODO
    public StreamFilter(Person[] people, Character startingCharacter) {
        this(Arrays.stream(people), startingCharacter);
    }

    /**
     * @param people - List of person objects
     * @param startingCharacter - character to filter by
     */ //TODO
    public StreamFilter(List<Person> people, Character startingCharacter) {
        this(people.stream(), startingCharacter);
    }


    /**
     * @param people - Stream of person objects
     * @param startingCharacter - character to filter by
     */ // I took care of the easy constructor (͡° ͜ʖ ͡°)
    public StreamFilter(Stream<Person> people, Character startingCharacter) {
        this.personStream = people;
        this.startingCharacter = startingCharacter.toString();
      //  this.sc = new StreamConverter(personStream);
    }

 /*   synchronized public static List<Person> updateList(List<Person> l, Person p) {
        l.add(p);
        return l;
    }*/

    /**
     * Using multi-line lambda syntax
     * @return a list of person object whose name starts with `this.startingCharacter`
     */ //TODO
    public List<Person> toListMultiLine() {
        List<Person> newList = new ArrayList<>();
        Function<List<Person>, List<Person>> func = (List<Person> personList) -> {
            personStream.filter((person) -> (String.valueOf(person.getName().charAt(0))).equals(this.startingCharacter)).forEach(personList::add);
            return personList;
        };
        return func.apply(newList);
//        return newList;
    }

    /**
     * Using one-line lambda syntax
     * @return a list of person objects whose name starts with `this.startingCharacter`
     */ //TODO
    public List<Person> toListOneLine() {
        return personStream.filter((person)->(String.valueOf(person.getName().charAt(0))).equals(this.startingCharacter)).collect(Collectors.toList());
    }

    /**
     * Using one-line lambda syntax
     * @return an array of person object whose name starts with `this.startingCharacter`
     */ //TODO
    public Person[] toArrayOneLine() {
        return personStream.filter((person)->(String.valueOf(person.getName().charAt(0))).equals(this.startingCharacter)).toArray(Person[]::new);
    }
//Alicia's version that doesn't fail randomly
//person.getName().substring(1).contains(String.valueOf(startingCharacter))
    /**
     * Using multi-line lambda syntax
     * @return an array of person object whose name starts with `this.startingCharacter`
     */ //TODO
    public Person[] toArrayMultiLine() {
        List<Person> newList = new ArrayList<>();
        Function<List<Person>, List<Person>> func = (List<Person> personList) -> {
            personStream.filter((person) -> (String.valueOf(person.getName().charAt(0))).equals(this.startingCharacter)).forEach(personList::add);
            return personList;
        };
        return func.apply(newList).toArray(new Person[0]);
 //       return newList.toArray(new Person[0]);
    }
}
