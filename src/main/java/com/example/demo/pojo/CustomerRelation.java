package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.springframework.data.annotation.Id;

@RelationshipEntity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRelation {
    @Id
    @GeneratedValue
    private Long id;
    private Data createTime;
    private String remark;

    @StartNode
    private CustomerNode customerFrom;

    @EndNode
    private CustomerNode customerTo;

    public CustomerRelation(CustomerNode customerFrom,CustomerNode customerTo,String remark){
        this.customerFrom=customerFrom;
        this.customerTo=customerTo;
        this.remark=remark;
    }
}
