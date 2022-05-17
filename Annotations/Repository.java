import java.util.ArrayList;
import java.util.stream.Collectors;

public class Repository<T> {
    public void save(T t) {
        var clazz = t.getClass();

        // Getting the name of our table using the entity annotation to save to our
        // database later

        var classAnnotations = clazz.getAnnotationsByType(Entity.class);
        var tableName = clazz.getSimpleName().toLowerCase(); // This line wil set the table name as user
                                                             // by default if not specified in the value 
        if (classAnnotations.length > 0 && classAnnotations[0].value().length() > 0) {
            tableName = classAnnotations[0].value();
        }

        // Getting the list of fields with field annotations to save it to our database
        // later

        /*
         * var fieldList = Arrays
         * .stream(clazz.getDeclaredFields())
         * .filter(f->f.getAnnotationsByType(Field.class).length>0)
         * .collect(Collectors.toList());
         * System.out.println(fieldList);
         */

        var fields = clazz.getDeclaredFields();
        ArrayList<String> fieldlist= new ArrayList<>();
        for (var field : fields) {
            var annotations = field.getAnnotationsByType(Field.class);
            if (annotations.length == 0) {
                continue;
            }
            var annotation = annotations[0];
            var fieldname = annotation.columnname();
            var iskey = annotation.iskey();
            if (fieldname.length() == 0) {
                fieldname = field.getName();
            }
            System.out.println(fieldname + " " + iskey);
            if(!iskey){ // Adding the non primary key fields to our fieldlist
                fieldlist.add(fieldname);
            }
        }

        // Using stream API to print the list of the non primary key fields
        String sqlfields= fieldlist.stream().collect(Collectors.joining(","));
        //System.out.println(sqlfields);

        // Since we are not connecting to our database, we wil just make a prepared statement so that
        // whenever we connect to our database we could directly add the non primary key fields
        // along with the placeholders (Some value that the user will input)

        String sqlplaceholders= fieldlist.stream().map(s->"?").collect(Collectors.joining(","));
        String sql= String.format("insert into %s (%s) values (%s)", tableName,sqlfields,sqlplaceholders);
        System.out.println(sql);
   }
}
