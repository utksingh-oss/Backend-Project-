package com.epaylater.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credit {
    private Long credit_id;
    private Long customer_id;
    private Long debit_id;
    private Date credit_date;
    private Double credit_amount;
}
