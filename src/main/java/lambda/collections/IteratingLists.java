package lambda.collections;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class IteratingLists {

    //  return a Predicate from a static method based on a parameter. A higher order function.
    public static Predicate<String> checkIfStartsWith(final String letter) {
        return any -> any.startsWith(letter);
    }

    // Filter names down to a collection using startsWith predicate and a startingLetter,
    // then return the first item in the collection or else a default string.
    public static void pickName(final List<String> names, final String startingLetter) {
        final Optional<String> foundName =
                names.stream().filter(name -> name.startsWith(startingLetter)).findFirst();
        System.out.println(String.format("A name starting with %s: %s",
                startingLetter,
                foundName.orElse("No name found")));
    }

    public static void main(String[] args) {
        final List<String> friends = asList("Mark","Andrew","Hayley","Helen","William");
        final List<String> work = asList("Sergio","Patra","Henry","Horace","Lindsay");

        friends.forEach(System.out::println);

        // Map Transforms an input collection to output collection.
        friends.stream().map(String::toUpperCase).forEach(System.out::println);
        friends.stream().map(String::length).forEach(System.out::println);

        // filter expects a lambda expression that returns a boolean, if true gets added to subset collection under the hood.
        List<String> startsWithH = friends.stream().filter(name -> name.startsWith("H")).collect(Collectors.toList());
        System.out.println(String.format("Found %d names", startsWithH.size()));
        startsWithH.forEach(System.out::println);

        // Save a Lambda as a Predicate.
        final Predicate<String> predStartsWithH =  name -> name.startsWith("H");
        final long friendCountH = friends.stream().filter(predStartsWithH).count();
        final long workCountH = work.stream().filter(predStartsWithH).count();
        System.out.println(String.format("Friends starting with H: %d, Work starting with H: %d", friendCountH, workCountH));

        // Use am Integer Predicate > 10 in the filter.
        final List<Integer> numbers = Arrays.asList(1,7,8,10, 11, 12, 6);
        final Predicate<Integer> greaterThan10 = value -> value > 10;
        final List<Integer> greaterThan10Count = numbers.stream().filter(greaterThan10).collect(Collectors.toList());
        greaterThan10Count.forEach(System.out::println);

        // Filtering on s lexical scoped (closure) function predicate.
        final long friendCountA = friends.stream().filter(checkIfStartsWith("A")).count();
        System.out.println(friendCountA);


        // In-line function version, Accepts a String variable and returns a Predicate
        final Function<String, Predicate<String>> startsWithLetter = (String letter) -> {
          Predicate<String> checkStarts = (String name) -> name.startsWith(letter);
          return checkStarts;
        };

        // Use the In-Line function.
        final long startsWithWCount = friends.stream().filter(startsWithLetter.apply("J")).count();
        System.out.println(startsWithWCount);


        // Calling PickName.
        pickName(friends,"H");
        pickName(friends,"D");

        // Basic mapToInt transforms the collection of String to a type specialised stream of integers - intStream, the intStream can be reduced summed or max.
        System.out.println("Total Number of characters in all names: " + friends.stream()
                .mapToInt(String::length)
                .sum());

        // MapReduce - the result of the first comparison is fed as name1 in to second comparison as reduce iterates over the stream.
        final Optional<String> aLongName =
                friends.stream()
                        .reduce ((name1, name2) -> name1.length() >= name2.length() ? name1: name2);
        aLongName.ifPresent(name -> System.out.println(String.format("A longest name: %s", name)));


        // Efficient ways to add a delimiter.
        System.out.println(String.join(", ", friends));
        System.out.println(friends.stream().map(String::toUpperCase).collect(Collectors.joining(", ")));
        System.out.println(friends.stream().map(String::toUpperCase).collect(Collectors.toList()));


    }

}
