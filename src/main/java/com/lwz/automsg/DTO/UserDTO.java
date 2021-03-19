package com.lwz.automsg.DTO;

public class UserDTO extends JsonDTO{
    private int id;
    private String type;
    private String login;
    private String name;
    private String avatarUrl;
    private Integer booksCount;
    private Integer publicBooksCount;
    private Integer followersCount;
    private Integer followingCount;
    private String createdAt;
    private String updatedAt;
    private String serializer;

         
    public void setId(Integer id) {
        this.id = id;
    }

         
    public Integer getId() {
        return this.id;
    }

         
    public void setType(String type) {
        this.type = type;
    }

         
    public String getType() {
        return this.type;
    }

         
    public void setLogin(String login) {
        this.login = login;
    }

         
    public String getLogin() {
        return this.login;
    }

         
    public void setName(String name) {
        this.name = name;
    }

         
    public String getName() {
        return this.name;
    }

         
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

         
    public String getAvatarUrl() {
        return this.avatarUrl;
    }

         
    public void setBooksCount(Integer booksCount) {
        this.booksCount = booksCount;
    }

         
    public Integer getBooksCount() {
        return this.booksCount;
    }

         
    public void setPublicBooksCount(Integer publicBooksCount) {
        this.publicBooksCount = publicBooksCount;
    }

         
    public Integer getPublicBooksCount() {
        return this.publicBooksCount;
    }

         
    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

         
    public Integer getFollowersCount() {
        return this.followersCount;
    }

         
    public void setFollowingCount(Integer followingCount) {
        this.followingCount = followingCount;
    }

         
    public Integer getFollowingCount() {
        return this.followingCount;
    }

         
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

         
    public String getCreatedAt() {
        return this.createdAt;
    }

         
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

         
    public String getUpdatedAt() {
        return this.updatedAt;
    }

         
    public void setSerializer(String serializer) {
        this.serializer = serializer;
    }

         
    public String getSerializer() {
        return this.serializer;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", booksCount=" + booksCount +
                ", publicBooksCount=" + publicBooksCount +
                ", followersCount=" + followersCount +
                ", followingCount=" + followingCount +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", serializer='" + serializer + '\'' +
                '}';
    }
}