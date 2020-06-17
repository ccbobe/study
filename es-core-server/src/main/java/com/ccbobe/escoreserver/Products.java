package com.ccbobe.escoreserver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "products",shards = 1, replicas = 0,
        refreshInterval = "-1")
public class Products {
    private @Id Integer id;

    private String name;

    private Double price;

    private Integer count;

    private List<String> tag;
}
