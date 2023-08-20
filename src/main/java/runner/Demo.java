package runner;

import annotations.BooleanValue;
import annotations.Email;
import annotations.FirstName;
import annotations.IntegerValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Demo {
    @FirstName
    private String firstName;
    @Email(domain = "@yahoo.com")
    private String email;
    @IntegerValue(min = 5,max = 8)
    private int age;
    @BooleanValue
    private boolean isActive;
}
