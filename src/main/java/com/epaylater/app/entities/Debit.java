package com.epaylater.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Debit {
    private Long debit_id;
    private Long customer_id;
    private Long merchant_id;
    private Date debit_date;
    private Double debit_amount;
    private Double remaining_amount;
}
