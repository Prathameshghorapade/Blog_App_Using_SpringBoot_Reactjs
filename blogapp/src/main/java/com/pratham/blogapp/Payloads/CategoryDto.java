package com.pratham.blogapp.Payloads;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {

    private int categoryId;
    private String categoryTitle;
    private String categoryDescription;

}
