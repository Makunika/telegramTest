package com.test.telegram.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "daily_domains")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Domain {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String domainName;

    private String hotness;

    private String price;

    private Integer old;

    @JsonCreator
    public Domain(@JsonProperty("domainname") String domainName, @JsonProperty("hotness") String hotness,
                  @JsonProperty("price") String price,
                  @JsonProperty("old") Integer old) {
        this.domainName = domainName;
        this.hotness = hotness;
        this.price = price;
        this.old = old;
    }

    public Domain() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getHotness() {
        return hotness;
    }

    public void setHotness(String hotness) {
        this.hotness = hotness;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getOld() {
        return old;
    }

    public void setOld(Integer old) {
        this.old = old;
    }
}
