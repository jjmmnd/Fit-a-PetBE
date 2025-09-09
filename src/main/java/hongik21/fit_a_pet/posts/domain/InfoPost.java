package hongik21.fit_a_pet.posts.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@DiscriminatorValue("INFO") // PostCategoryType = Free
@NoArgsConstructor
public class InfoPost extends Post {
}
