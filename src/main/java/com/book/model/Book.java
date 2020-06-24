package com.book.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Schema(hidden = true, required = false)
    @Hidden
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBook", unique = true, nullable = false)
    private Integer idBook;

    @Column(name = "title", length = 45)
    @Size(min = 2, message = "Book title should have at least 2 characters")
    @Schema(description = "Title of the author.", example = "Spring Boot By Example", required = true)
    private String title;

    @Column(name = "isbn", length = 13, unique = true, nullable = false)
    @Pattern(regexp = "^\\d{13}$", message = "Book ISBN should  should have 13 characters and contain only numbers")
    @Schema(description = "Title of the author.", example = "9783161484103", required = true)
    private String isbn;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", length = 19)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdAt;

    @ArraySchema(arraySchema =  @Schema(name = "authors", type = "number", example = "[1,3]"), minItems = 1)
    @JsonIgnoreProperties(value = "books")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @NotEmpty(message = "Add the authors of the book")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, targetEntity = Author.class)
    @JoinTable(name = "author_book",
            joinColumns = @JoinColumn(name = "idBook", referencedColumnName = "idBook"),
            inverseJoinColumns = @JoinColumn(name = "idAuthor", referencedColumnName = "idAuthor"))
    private Set<Author> authors = new HashSet<Author>();

}
