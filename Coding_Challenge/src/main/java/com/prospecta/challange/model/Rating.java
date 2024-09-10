package com.prospecta.challange.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Represents the rating of a product, consisting of a rate and count.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private Double rate;
    private Integer count;
}

