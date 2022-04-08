package ke.co.sample.repositories.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import ke.co.sample.utils.PhoneNumberStatus;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "customer_phone_state")
public class CustomerPhoneState  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PhoneNumberStatus status;

    @JsonIgnore
    @Column(name = "country")
    private Integer countryId;

    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "country", referencedColumnName = "id", insertable = false, updatable = false)
    private Country country;

    @JsonIgnore
    @Column(name = "customer_phone_id", nullable = false)
    private Integer customerPhoneId;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_phone_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Customer customer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CustomerPhoneState state = (CustomerPhoneState) o;
        return id != null && Objects.equals(id, state.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
