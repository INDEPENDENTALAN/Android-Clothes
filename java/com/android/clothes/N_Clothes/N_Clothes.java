package com.android.clothes.N_Clothes;

public class N_Clothes {

    private int Id;
    private String Name;
    private String Picture;
    private String Price;
    private int Is_Lovely;
    private int Is_Opinion;
    private String Opinion;
    private int IS_Purchases;

    public N_Clothes(int id, String name, String picture, String price, int is_Lovely, int is_Opinion, String opinion, int IS_Purchases) {
        Id = id;
        Name = name;
        Picture = picture;
        Price = price;
        Is_Lovely = is_Lovely;
        Is_Opinion = is_Opinion;
        Opinion = opinion;
        this.IS_Purchases = IS_Purchases;
    }

    public N_Clothes() {

    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setIs_Lovely(int is_Lovely) {
        Is_Lovely = is_Lovely;
    }

    public void setIs_Opinion(int is_Opinion) {
        Is_Opinion = is_Opinion;
    }

    public void setOpinion(String opinion) {
        Opinion = opinion;
    }

    public void setIS_Purchases(int IS_Purchases) {
        this.IS_Purchases = IS_Purchases;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getPicture() {
        return Picture;
    }

    public String getPrice() {
        return Price;
    }

    public int getIs_Lovely() {
        return Is_Lovely;
    }

    public int getIs_Opinion() {
        return Is_Opinion;
    }

    public String getOpinion() {
        return Opinion;
    }

    public int getIS_Purchases() {
        return IS_Purchases;
    }
}
