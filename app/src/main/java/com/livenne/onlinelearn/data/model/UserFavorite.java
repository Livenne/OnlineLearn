package com.livenne.onlinelearn.data.model;

public class UserFavorite {
    private Long favoriteId;
    private Long courseId;
    private Long userId;

    public UserFavorite() {}

    public UserFavorite(Long favoriteId, Long courseId, Long userId) {
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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
