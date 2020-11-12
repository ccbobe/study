package com.ccbobe.escoreserver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * @author ccbobe
 */
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TypeAlias("product")
@Document(indexName = "product",shards = 1, replicas = 1, createIndex =true)
public class Products {
    private @Id Integer id;

    @Field(fielddata = true,store = true,index = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word",type = FieldType.Text)
    private String name;

    @Field(fielddata = true,store = true)
    private Double price;

    private Integer count;
    @Field(fielddata = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private List<String> tag;
}
