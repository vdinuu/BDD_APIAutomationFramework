package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder(toBuilder = true)
public class AddLocation {
    private Location location;
    private int accuracy;
    private String name;
    private String phone_number;
    private String address;
    private String[] types;
    private String website;
    private String language;
}
