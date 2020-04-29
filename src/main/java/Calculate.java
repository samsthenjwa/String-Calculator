import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Calculate {
    public static void main(String[] args) {
        int n8 = Add("1\n12");
        int n7 = Add("//;\n1;2");
        int n6 = Add("//4\n142");
        int n5 = Add("-2,4,-7,-4");
        int n3 = Add("//[abc][777][:(]\n1abc27773:(1");


        System.out.println(n8);
        System.out.println(n7);
        System.out.println(n6);
        System.out.println(n5);
        System.out.println(n3);

    }

    private final String delimiter;
    private final String numbers;

    //Private constructor that receives or private fields
    private Calculate(String delimiter, String numbers) {
        //Assignment of perimeters to the fields
        this.delimiter = delimiter;
        this.numbers = numbers;
    }

    private int sum() {
        int negSum = 0;
        //calling the NegativeNumber Method
        NegativeNumbers();
        //return the sum of negatives

        if(getNumbers().sum() < 0){
            return negSum;
        }else{
            return getNumbers().sum();
        }


    }

    //Negative function
    private void NegativeNumbers() {
        // opening of try and catch
        try {
            // Declaration and initialization of negative numbers variable
            String negativeSequence = getNumbers().filter(n -> n < 0)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(","));

            //Condition of checking negative numbers
            if (!negativeSequence.isEmpty()) {
                //throw exception if condition is met
                throw new Exception("ERROR: negatives not allowed  " + negativeSequence);
            }
        } catch (Exception e) {
            //printing of the caught error
            System.out.println(e);

        }

    }

    private IntStream getNumbers() {
        if (numbers.isEmpty()) {
            return IntStream.empty();
        } else {
            return Stream.of(numbers.split(delimiter))
                    .mapToInt(Integer::parseInt)
                    .map(n -> n % 1000);
        }
    }

    public static int Add(String input) {
        return parse(input).sum();
    }

    private static Calculate parse(String input) {
        if (input.startsWith("//")) {
            String[] headerAndNumberSequence = input.split("\n", 2);
            String delimiter = parseDelimiter(headerAndNumberSequence[0]);
            return new Calculate(delimiter, headerAndNumberSequence[1]);
        } else {
            return new Calculate(",|\n", input);
        }
    }

    private static String parseDelimiter(String header) {
        String delimiter = header.substring(2);
        if (delimiter.startsWith("[")) {
            delimiter = delimiter.substring(1, delimiter.length() - 1);
        }
        return Stream.of(delimiter.split("]\\["))
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));
    }

}