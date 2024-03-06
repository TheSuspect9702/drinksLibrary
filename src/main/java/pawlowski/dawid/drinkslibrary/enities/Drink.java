package pawlowski.dawid.drinkslibrary.enities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import pawlowski.dawid.drinkslibrary.model.DrinkPower;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drink {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false,nullable = false)
    private UUID id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private Double rating;

    @NotNull
    private DrinkPower power;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max = 10000)
    @Column(length = 10000)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "drink", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;
}
