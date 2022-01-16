package com.f101469.mypastaapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;
import java.util.Objects;

@Entity(tableName = "pastas")
public class Pasta {

  @PrimaryKey private Long idMeal;

  @ColumnInfo(name = "name")
  private String strMeal;

  @ColumnInfo(name = "thumbnail_location")
  private String strMealThumb;

  @ColumnInfo(name = "ingredient1")
  private String strIngredient1;

  @ColumnInfo(name = "ingredient2")
  private String strIngredient2;

  @ColumnInfo(name = "ingredient3")
  private String strIngredient3;

  @ColumnInfo(name = "ingredient4")
  private String strIngredient4;

  @ColumnInfo(name = "ingredient5")
  private String strIngredient5;

  @ColumnInfo(name = "ingredient6")
  private String strIngredient6;

  @ColumnInfo(name = "ingredient7")
  private String strIngredient7;

  @ColumnInfo(name = "ingredient8")
  private String strIngredient8;

  @ColumnInfo(name = "ingredient9")
  private String strIngredient9;

  @ColumnInfo(name = "ingredient10")
  private String strIngredient10;

  @ColumnInfo(name = "price")
  private Double basePrice;

  @ColumnInfo(name = "is_favourite")
  private Boolean isFavourite;

  public Pasta(
      Long idMeal,
      String strMeal,
      String strMealThumb,
      String strIngredient1,
      String strIngredient2,
      String strIngredient3,
      String strIngredient4,
      String strIngredient5,
      String strIngredient6,
      String strIngredient7,
      String strIngredient8,
      String strIngredient9,
      String strIngredient10,
      Double basePrice,
      Boolean isFavourite) {
    this.idMeal = idMeal;
    this.strMeal = strMeal;
    this.strMealThumb = strMealThumb;
    this.strIngredient1 = strIngredient1;
    this.strIngredient2 = strIngredient2;
    this.strIngredient3 = strIngredient3;
    this.strIngredient4 = strIngredient4;
    this.strIngredient5 = strIngredient5;
    this.strIngredient6 = strIngredient6;
    this.strIngredient7 = strIngredient7;
    this.strIngredient8 = strIngredient8;
    this.strIngredient9 = strIngredient9;
    this.strIngredient10 = strIngredient10;
    this.basePrice = basePrice;
    this.isFavourite = isFavourite;
  }

  public Pasta() {}

  public Long getIdMeal() {
    return idMeal;
  }

  public String getStrMeal() {
    return strMeal;
  }

  public String getStrMealThumb() {
    return strMealThumb;
  }

  public String getStrIngredient1() {
    return strIngredient1;
  }

  public String getStrIngredient2() {
    return strIngredient2;
  }

  public String getStrIngredient3() {
    return strIngredient3;
  }

  public String getStrIngredient4() {
    return strIngredient4;
  }

  public String getStrIngredient5() {
    return strIngredient5;
  }

  public String getStrIngredient6() {
    return strIngredient6;
  }

  public String getStrIngredient7() {
    return strIngredient7;
  }

  public String getStrIngredient8() {
    return strIngredient8;
  }

  public String getStrIngredient9() {
    return strIngredient9;
  }

  public String getStrIngredient10() {
    return strIngredient10;
  }

  public Double getBasePrice() {
    return basePrice;
  }

  public Boolean getFavourite() {
    return isFavourite;
  }

  public void setIdMeal(Long idMeal) {
    this.idMeal = idMeal;
  }

  public void setStrMeal(String strMeal) {
    this.strMeal = strMeal;
  }

  public void setStrMealThumb(String strMealThumb) {
    this.strMealThumb = strMealThumb;
  }

  public void setStrIngredient1(String strIngredient1) {
    this.strIngredient1 = strIngredient1;
  }

  public void setStrIngredient2(String strIngredient2) {
    this.strIngredient2 = strIngredient2;
  }

  public void setStrIngredient3(String strIngredient3) {
    this.strIngredient3 = strIngredient3;
  }

  public void setStrIngredient4(String strIngredient4) {
    this.strIngredient4 = strIngredient4;
  }

  public void setStrIngredient5(String strIngredient5) {
    this.strIngredient5 = strIngredient5;
  }

  public void setStrIngredient6(String strIngredient6) {
    this.strIngredient6 = strIngredient6;
  }

  public void setStrIngredient7(String strIngredient7) {
    this.strIngredient7 = strIngredient7;
  }

  public void setStrIngredient8(String strIngredient8) {
    this.strIngredient8 = strIngredient8;
  }

  public void setStrIngredient9(String strIngredient9) {
    this.strIngredient9 = strIngredient9;
  }

  public void setStrIngredient10(String strIngredient10) {
    this.strIngredient10 = strIngredient10;
  }

  public void setBasePrice(Double basePrice) {
    this.basePrice = basePrice;
  }

  public void setFavourite(Boolean favourite) {
    isFavourite = favourite;
  }

  @Override
  public String toString() {
    return "Pasta{"
        + "idMeal="
        + idMeal
        + ", strMeal='"
        + strMeal
        + '\''
        + ", strMealThumb='"
        + strMealThumb
        + '\''
        + ", strIngredient1='"
        + strIngredient1
        + '\''
        + ", strIngredient2='"
        + strIngredient2
        + '\''
        + ", strIngredient3='"
        + strIngredient3
        + '\''
        + ", strIngredient4='"
        + strIngredient4
        + '\''
        + ", strIngredient5='"
        + strIngredient5
        + '\''
        + ", strIngredient6='"
        + strIngredient6
        + '\''
        + ", strIngredient7='"
        + strIngredient7
        + '\''
        + ", strIngredient8='"
        + strIngredient8
        + '\''
        + ", strIngredient9='"
        + strIngredient9
        + '\''
        + ", strIngredient10='"
        + strIngredient10
        + '\''
        + ", basePrice="
        + basePrice
        + ", isFavourite="
        + isFavourite
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pasta pasta = (Pasta) o;
    return idMeal.equals(pasta.idMeal)
        && strMeal.equals(pasta.strMeal)
        && Objects.equals(strMealThumb, pasta.strMealThumb)
        && Objects.equals(strIngredient1, pasta.strIngredient1)
        && Objects.equals(strIngredient2, pasta.strIngredient2)
        && Objects.equals(strIngredient3, pasta.strIngredient3)
        && Objects.equals(strIngredient4, pasta.strIngredient4)
        && Objects.equals(strIngredient5, pasta.strIngredient5)
        && Objects.equals(strIngredient6, pasta.strIngredient6)
        && Objects.equals(strIngredient7, pasta.strIngredient7)
        && Objects.equals(strIngredient8, pasta.strIngredient8)
        && Objects.equals(strIngredient9, pasta.strIngredient9)
        && Objects.equals(strIngredient10, pasta.strIngredient10)
        && Objects.equals(basePrice, pasta.basePrice)
        && Objects.equals(isFavourite, pasta.isFavourite);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        idMeal,
        strMeal,
        strMealThumb,
        strIngredient1,
        strIngredient2,
        strIngredient3,
        strIngredient4,
        strIngredient5,
        strIngredient6,
        strIngredient7,
        strIngredient8,
        strIngredient9,
        strIngredient10,
        basePrice,
        isFavourite);
  }
}
