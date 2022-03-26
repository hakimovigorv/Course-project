package com.example.courseproject.mappers;

import com.example.courseproject.dto.*;
import com.example.courseproject.models.Category;
import com.example.courseproject.models.Image;
import com.example.courseproject.models.Review;
import com.example.courseproject.models.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(target = "images", source = "imageUrls")
    Review reviewAddDtoToReview(ReviewAddDto reviewAddDto);

    @Mapping(target = "name", source = "category")
    Category categoryNameToCategory(String category);

    default String categoryToCategoryName(Category category) {
        return category.getName();
    }

    @Mapping(target = "url", source = "imageUrl")
    Image imageUrlToImage(String imageUrl);

    Set<Image> imageUrlsToImages(Set<String> imageUrls);

    default String imageToImageUrl(Image image) {
        return image.getUrl();
    }

    Set<String> imagesToImageUrls(Set<Image> images);

    @Mapping(target = "authorName", source = "review.user.username")
    @Mapping(target = "authorImgUrl", source = "review.user.imageUrl")
    @Mapping(target = "imageUrls", source = "images")
    ReviewDetailsDto reviewToReviewDetailsDto(Review review);

    Set<Tag> tagNamesToTags(Set<String> tags);

    @Mapping(target = "name", source = "tag")
    Tag tagNameToTag(String tag);

    default String tagToTagName(Tag tag) {
        return tag.getName();
    }

    Set<String> tagsToTagNames(Set<Tag> tags);

    @Mapping(target = "images", source = "imageUrls")
    Review reviewEditDtoToReview(ReviewEditDto reviewEditDto);
}
