package com.diner.rating.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {
    private String name;
    private String email;
    private List<ReviewDto> reviews;

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public void setId(Long l) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setUsername(String testuser) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
