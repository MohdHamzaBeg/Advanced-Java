
import java.lang.annotation.*;

@Target(ElementType.FIELD) // Sets on which token the annotation is applicable
@Retention(RetentionPolicy.RUNTIME) // Sets the limit of execution of annotation
public @interface Field {
    // Adding the attributes of our Field annotation
    String columnname() default "";

    boolean iskey() default false;
}
