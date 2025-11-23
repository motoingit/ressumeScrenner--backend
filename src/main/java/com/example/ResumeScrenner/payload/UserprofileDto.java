package  com.example.ResumeScrenner.payload;

import com.example.ResumeScrenner.dao.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserprofileDto {
    private String email;
    private String name;
    private String password;
    private String image;
    private Role role;
}