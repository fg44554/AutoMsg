package com.lwz.automsg.DTO;


/**
 * @author 吕文哲
 */
public class DataDTO extends JsonDTO{
        private Integer id;
        private String slug;
        private String title;
        private Integer bookId;
        private BookDTO book;
        private Integer userId;
        private UserDTO user;
        private String format;
        private String body;
        private String bodyDraft;
        private String bodyHtml;
        private Integer publicised;
        private Integer status;
        private Integer viewStatus;
        private Integer readStatus;
        private Integer likesCount;
        private Integer commentsCount;
        private String contentUpdatedAt;
        private String createdAt;
        private String updatedAt;
        private String publishedAt;
        private String firstPublishedAt;
        private Integer wordCount;
        private String serializer;
        private String path;
        private boolean publish;
        private String actionType;
        private String webhookSubjectType;
        private Integer actorId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBodyDraft() {
        return bodyDraft;
    }

    public void setBodyDraft(String bodyDraft) {
        this.bodyDraft = bodyDraft;
    }

    public String getBodyHtml() {
        return bodyHtml;
    }

    public void setBodyHtml(String bodyHtml) {
        this.bodyHtml = bodyHtml;
    }

    public Integer getPublicised() {
        return publicised;
    }

    public void setPublicised(Integer publicised) {
        this.publicised = publicised;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getViewStatus() {
        return viewStatus;
    }

    public void setViewStatus(Integer viewStatus) {
        this.viewStatus = viewStatus;
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getContentUpdatedAt() {
        return contentUpdatedAt;
    }

    public void setContentUpdatedAt(String contentUpdatedAt) {
        this.contentUpdatedAt = contentUpdatedAt;
    }

         
    public String getCreatedAt() {
        return createdAt;
    }

         
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

         
    public String getUpdatedAt() {
        return updatedAt;
    }

         
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getFirstPublishedAt() {
        return firstPublishedAt;
    }

    public void setFirstPublishedAt(String firstPublishedAt) {
        this.firstPublishedAt = firstPublishedAt;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

         
    public String getSerializer() {
        return serializer;
    }

         
    public void setSerializer(String serializer) {
        this.serializer = serializer;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getWebhookSubjectType() {
        return webhookSubjectType;
    }

    public void setWebhookSubjectType(String webhookSubjectType) {
        this.webhookSubjectType = webhookSubjectType;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    @Override
    public String toString() {
        return "DataDTO{" +
                "id=" + id +
                ", slug='" + slug + '\'' +
                ", title='" + title + '\'' +
                ", bookId=" + bookId +
                ", book=" + book +
                ", userId=" + userId +
                ", user=" + user +
                ", format='" + format + '\'' +
                ", body='" + body + '\'' +
                ", bodyDraft='" + bodyDraft + '\'' +
                ", bodyHtml='" + bodyHtml + '\'' +
                ", publicised=" + publicised +
                ", status=" + status +
                ", viewStatus=" + viewStatus +
                ", readStatus=" + readStatus +
                ", likesCount=" + likesCount +
                ", commentsCount=" + commentsCount +
                ", contentUpdatedAt='" + contentUpdatedAt + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", firstPublishedAt='" + firstPublishedAt + '\'' +
                ", wordCount=" + wordCount +
                ", serializer='" + serializer + '\'' +
                ", path='" + path + '\'' +
                ", publish=" + publish +
                ", actionType='" + actionType + '\'' +
                ", webhookSubjectType='" + webhookSubjectType + '\'' +
                ", actorId=" + actorId +
                '}';
    }
}
