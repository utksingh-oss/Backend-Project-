package com.epaylater.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Merchant{
    private Long merchant_id;
    private String name;
    private String address;
    private String emailId;
    private String contact_number;
}
