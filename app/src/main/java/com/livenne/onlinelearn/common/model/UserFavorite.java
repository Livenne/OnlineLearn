package com.livenne.onlinelearn.common.model;

public class UserFavorite {
    private Long favoriteId;
    private String courseId;
    private Long userId;

    public UserFavorite() {}

    public UserFavorite(Long favoriteId, String courseId, Long userId) {
        this.favoriteId = favoriteId;
        this.courseId = courseId;
        this.userId = userId;
    }

    public Long getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Long favoriteId) {
        this.favoriteId = favoriteId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
