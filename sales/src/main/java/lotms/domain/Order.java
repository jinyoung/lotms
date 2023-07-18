package lotms.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lotms.SalesApplication;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String orderId;

    private String orderType;

    @OneToOne
    private CompanyId customer;

    private Date orderDate;

    private Date deliveryDate;

    private String orderManager;

    private String accountingYearMonth;

    @OneToOne
    private UserId registeredBy;

    private String memo;

    public static OrderRepository repository() {
        OrderRepository orderRepository = SalesApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }
}
