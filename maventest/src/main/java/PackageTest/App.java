package PackageTest;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Multiset<String> animals= HashMultiset.create(); // Special class from guava.jar file
        animals.add("dog");
        animals.add("cat");
        animals.add("donkey");
        System.out.println(animals);
        List<Character> list= new ArrayList<>();
        list.add('h');
        list.add('a');
        list.add('m');
        list.add('z');
        System.out.println(list);
    }
}
