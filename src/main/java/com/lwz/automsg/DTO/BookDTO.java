package com.lwz.automsg.DTO;

/**
 * @author 吕文哲
 */
public class BookDTO extends JsonDTO{
        private Integer id;

        private String type;
        private String slug;
        private String name;
        private Integer userId;
        private String description;
        private Integer creatorId;
        private Integer publicised;
        private Integer itemsCount;
        private Integer likesCount;
        private Integer watchesCount;
        private String contentUpdatedAt;
        private String updatedAt;
        private String createdAt;
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

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getSlug() {
            return this.slug;
        }

           
        public void setName(String name) {
            this.name = name;
        }

           
        public String getName() {
            return this.name;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getUserId() {
            return this.userId;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }

        public void setCreatorId(Integer creatorId) {
            this.creatorId = creatorId;
        }

        public Integer getCreatorId() {
            return this.creatorId;
        }

        public void setPublicised(Integer publicised) {
            this.publicised =publicised ;
        }

        public Integer getPublicised() {
            return this.publicised;
        }

        public void setItemsCount(Integer itemsCount) {
            this.itemsCount = itemsCount;
        }

        public Integer getItemsCount() {
            return this.itemsCount;
        }

        public void setLikesCount(Integer likesCount) {
            this.likesCount = likesCount;
        }

        public Integer getLikesCount() {
            return this.likesCount;
        }

        public void setWatchesCount(Integer watchesCount) {
            this.watchesCount = watchesCount;
        }

        public Integer getWatchesCount() {
            return this.watchesCount;
        }

        public void setContentUpdatedAt(String contentUpdatedAt) {
            this.contentUpdatedAt = contentUpdatedAt;
        }

        public String getContentUpdatedAt() {
            return this.contentUpdatedAt;
        }

           
        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

           
        public String getUpdatedAt() {
            return this.updatedAt;
        }

           
        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getCreatedAt() {
            return this.createdAt;
        }

           
        public void setSerializer(String serializer) {
            this.serializer = serializer;
        }

           
        public String getSerializer() {
            return this.serializer;
        }


    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", slug='" + slug + '\'' +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                ", creatorId=" + creatorId +
                ", publicised=" + publicised +
                ", itemsCount=" + itemsCount +
                ", likesCount=" + likesCount +
                ", watchesCount=" + watchesCount +
                ", contentUpdatedAt='" + contentUpdatedAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", serializer='" + serializer + '\'' +
                '}';
    }
}
