package lotms.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lotms.BasicApplication;

@Entity
@Table(name = "Company_table")
@Data
public class Company {

    private String companyType;

    private String companyName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String companyCode;

    private String registrationNumber;

    private String businessType;

    private String industry;

    private String ceoName;

    private String phone;

    private String faxNumber;

    @Embedded
    private Email email;

    private Integer marginRate;

    private String accountNumber;

    private String bankName;

    private String beneficiary;

    private String paymentTerms;

    private Boolean isActive;

    private String salesRepresentative;

    private String companyIssues;

    @Embedded
    private Address address;

    public static CompanyRepository repository() {
        CompanyRepository companyRepository = BasicApplication.applicationContext.getBean(
            CompanyRepository.class
        );
        return companyRepository;
    }
}
