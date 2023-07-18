package lotms.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lotms.BasicApplication;

@Entity
@Table(name = "User_table")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String userName;

    private String id;

    private Password password;

    private String employmentStatus;

    private String position;

    private String department;

    private String phoneNumber;

    private Date joinDate;

    private Boolean isAdmin;

    private Boolean isDeptManager;

    public static UserRepository repository() {
        UserRepository userRepository = BasicApplication.applicationContext.getBean(
            UserRepository.class
        );
        return userRepository;
    }
}
