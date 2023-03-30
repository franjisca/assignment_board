package com.assignment.board.domain;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Board {
    public Board() {

    }
    public Board(String writer, String title, String contents, String checked, LocalDateTime writeDate, int readCount) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.checked = checked;
        this.writeDate = writeDate;
        this.readCount = readCount;
    }

        @NonNull
        private Long boardNo;
        @NonNull
        private String writer;
        @NonNull
        private String title;
        @NonNull
        private String contents;

        @NonNull
        private String checked;

        @DateTimeFormat(pattern = "yyyy-mm-dd")
        private LocalDateTime writeDate;

        private int readCount;


}
