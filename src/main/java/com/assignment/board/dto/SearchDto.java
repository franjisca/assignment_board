package com.assignment.board.dto;


import lombok.*;

@AllArgsConstructor
@Data
public class SearchDto {

    @NonNull
    private String category;
    @NonNull
    private String searchText;
}
