package com.christmas.letter.sender.model;

public enum WishCategory {
  TOYS("Toys"), FOOD("Food"), CLOTHES("Clothes"), ELECTRONICS("Electronics"), OTHER("Other");
//  , BOOKS("Books"), OTHER("Other"), CARS("Cars");

  private String category;

  private WishCategory(String category) {
    this.category = category;
  }

  public static WishCategory fromString(String category) {
    for (WishCategory wishCategory : WishCategory.values()) {
      if (wishCategory.category.equalsIgnoreCase(category)) {
        return wishCategory;
      }
    }
    return OTHER;
  }

  public String getCategory() {
    return category;
  }
}
