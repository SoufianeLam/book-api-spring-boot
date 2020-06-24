package com.book.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Hidden
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAuthor", unique = true, nullable = false)
    @Schema(hidden = true,required = false)
    private Integer idAuthor;

    @Column(name = "firstname", length = 45, nullable = false)
    @Size(min = 2, message = "Firstname should have at least 2 characters")
    @Schema(description = "Firstname of the author.", example = "Shawn", required = true)
    private String firstname;

    @Column(name = "lastname", length = 45, nullable = false)
    @Size(min = 2, message = "Lastname should have at least 2 characters")
    @Schema(description = "Lastname of the author.", example = "Willis", required = true)
    private String lastname;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", length = 19)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdAt;

    @JsonIgnoreProperties(value = "authors")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToMany(cascade = CascadeType.REFRESH,
            mappedBy = "authors", fetch = FetchType.LAZY, targetEntity = Book.class)
    private Set<Book> books = new HashSet<Book>();

    @JsonCreator
    public Author(Integer id) {
        this.idAuthor = id;
    }

}